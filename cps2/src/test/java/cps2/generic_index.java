package cps2;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class generic_index {


	@Test
	public void genetic_index() throws InterruptedException

	{
		WebDriver driver=WebDriverManager.chromedriver().create();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[5]")).click();           
		Thread.sleep(3000);
		List<WebElement> alphabars = driver.findElements(By.xpath("//div[@class=\"alphaBar\"]/button"));
		// Iterate through each alphabet
		for (WebElement alphabar : alphabars)
		{
			if(alphabar.getText().equalsIgnoreCase("#"))
			{
				continue;
			}
			else
			{
				alphabar.click();
List<WebElement> all_items= driver.findElements(By.xpath("//div[@class=\"genericItem\"]/div"));
				for (int i = 0; i <all_items.size(); i++) 
				{

					WebElement current_index = all_items.get(i);


					Actions action = new Actions(driver);
					action.moveToElement(current_index).build().perform();
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
					wait.until(ExpectedConditions.visibilityOf(current_index));
					current_index.click();

List<WebElement> monographs = driver.findElements(By.xpath("//div[@class=\"genericDocs\"]/p[position() > 1]"));



					for(int j=0; j<monographs.size();j++)

					{

						WebElement monograph= monographs.get(i);

						Actions action2 = new Actions(driver);


						action2.moveToElement(monograph).build().perform();
						WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(3));
						wait.until(ExpectedConditions.visibilityOf(monograph));
						monograph.click();
						

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
						Thread.sleep(2000);
						driver.navigate().back(); // Go back to Monograph Updates

monographs = driver.findElements(By.xpath("//div[@class=\"genericDocs\"]/p[@class=\"genericDocItem\"]"));
						Thread.sleep(1000);		

					}
				}
				alphabars = driver.findElements(By.xpath("//div[@class='alphaBar']/button"));

			}

		}

		driver.close();
	}
}





;
