package link;

import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class French_MA_BrokenLink_A_to_C {
	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentReports extent;
	private ExtentTest test;

	@BeforeTest
	public void setUp() {
		extent = new ExtentReports();

		ExtentSparkReporter spark = new ExtentSparkReporter("French_MA_AC.html");

		extent.attachReporter(spark);

		test = extent.createTest("Broken External Links", "Report of broken external links");

	

		driver = WebDriverManager.chromedriver().create();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}

	@Test
	public void testBrokenLinks() throws Exception {

		driver.get("http://cps2-staging.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")));

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[9]")));

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[9]")).click();
		
		driver.findElement(By.xpath("//span[normalize-space()='FR']")).click();


		Thread.sleep(3000);
		// 

		List<WebElement> all_items = driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/span/a"));

		// WebElement dysepia=driver.findElement(By.xpath("//div[@class=\"menu-document-item\"]/span/a/span[text()=\"Dyspepsia and Peptic Ulcer Disease\"]"));

		for (int i = 0; i < all_items.size(); i++)
		{


			WebElement current_element = all_items.get(i);

			if(current_element.getText().toString().startsWith("D"))
			{
				test.log(Status.PASS, "Alphabar A to C is executed");
                      break;
			}
			
			else
			{

			Actions action = new Actions(driver);

			action.moveToElement(current_element).build().perform();
			wait.until(ExpectedConditions.visibilityOf(current_element));
			test.log(Status.INFO, "Now executing>>> " + current_element.getText());

			current_element.click();

			Thread.sleep(1500);

			List<WebElement> allLinks = driver.findElements(By.tagName("a"));

			for (WebElement link : allLinks)
			{
				String href = link.getAttribute("href");
				String text = link.getText();
				String classAttribute = link.getAttribute("class");

				if (classAttribute != null && (classAttribute.equalsIgnoreCase("dita-xref ref") || classAttribute.equalsIgnoreCase("dita-xref"))) {

					if (href != null)
					{
						HttpURLConnection connection = null;
						int attempts = 0;

						while(attempts < 5) {
							try {
								URL url = new URL(href);
								connection = (HttpURLConnection) url.openConnection();
								connection.setRequestMethod("HEAD");
								connection.connect();

								int responseCode = connection.getResponseCode();
								if (responseCode == 404 || responseCode >= 500)
								{
									test.log(Status.FAIL, responseCode + " >> Broken external link: " + text + " - " + href);
								}
								break;
							} catch(SocketException se)
							{
								attempts++;
								Thread.sleep(1000); // Wait for 2 seconds before retrying
								continue;
							} catch(Exception e) {
								test.log(Status.FAIL, text + " link is not working and throwing exception: " + e.getMessage());
								break;
							} finally {
								if (connection != null) {
									connection.disconnect();
								}
							}
						}
					} 
					else
					{
						test.log(Status.WARNING, text + " is having null href  " + href);
					}
				}
				else if (classAttribute != null && classAttribute.equalsIgnoreCase("collapsed"))
				{
					// Log TOC link
					// test.log(Status.INFO, text + " is a TOC link >>" + href);
				} 
				else if (classAttribute != null && classAttribute.equalsIgnoreCase("print_link"))
				{
					// Log print link
					// test.log(Status.INFO, text + " is a print link >>" + href);
				}
				else
				{
					// Log other links
					// test.log(Status.INFO, text + " is a link with class: " + classAttribute);
				}
			}

			//            if (current_element.getText().toString().equalsIgnoreCase("d"))
			//            {
			//            	
			//            	test.log(Status.PASS, "List from A to C is verified");
			//                break;
			//            }
			}
			driver.navigate().back();
			all_items = driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/span/a"));
		}
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
		driver.quit();
	}


}
