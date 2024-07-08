package cps2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class medical_device {




	@Test
	public void medical_device() throws InterruptedException



	{

		WebDriver driver=WebDriverManager.chromedriver().create();

		driver.get("http://cps2-staging.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		driver.getTitle();

		System.out.println("login is successfull");


		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[4]")).click();           
		Thread.sleep(3000);

		List<WebElement> all_items= driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/following::span/a"));


		for (int i = 0; i <all_items.size(); i++) 

		{

			WebElement current_medicaldevice= all_items.get(i);

			//for (WebElement current_monograph : all_items) {
			Actions act= new Actions(driver);

			act.moveToElement(current_medicaldevice).click().build().perform();
			Thread.sleep(2000);

			WebElement actual_tab_text=driver.findElement(By.xpath("//span[text()='Recent: ']//following::div[1]"));

			Thread.sleep(2000);

			System.out.println("Actual title of tab is :  "+actual_tab_text.getText());


			WebElement expected_tab_text=driver.findElement(By.xpath("//h2[@class=\"brandname\"]"));


			System.out.println("Expected title of the tab is: "+expected_tab_text.getText());


			JavascriptExecutor js = (JavascriptExecutor) driver;
			long lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();

			while (true) {
			    js.executeScript("window.scrollBy(0, 250);");
			    Thread.sleep(500); // Adjust as needed
			    long newHeight = ((Number) js.executeScript("return window.pageYOffset + window.innerHeight")).longValue();
			    if (newHeight >= lastHeight) {
			        break;
			    }
			}

			driver.navigate().back(); // Go back to Monograph Updates

			all_items = driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/following::span/a"));
			//					Thread.sleep(1000);
		}

		driver.close();
	}


}
