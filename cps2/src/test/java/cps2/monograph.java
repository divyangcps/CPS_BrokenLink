package cps2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class monograph
{

	@Test
	public void monograph() throws InterruptedException 


	{

		WebDriver driver=WebDriverManager.chromedriver().create();

		driver.get("http://cps2-staging.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[3]")).click();           
		Thread.sleep(3000);

		List<WebElement> all_items= driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/following::span/a"));


		for (int i = 0; i <all_items.size(); i++) 

		{

			all_items = driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/following::span/a"));


			WebElement current_monograph= driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/following::span/a")).get(i);

			//for (WebElement current_monograph : all_items) {
			Actions act= new Actions(driver);

			act.moveToElement(current_monograph).build().perform();
			current_monograph.click();
			Thread.sleep(1000);

			WebElement actual_tab_text=driver.findElement(By.xpath("//span[text()='Recent: ']//following::div[1]"));

			System.out.println("Actual title of tab is :  "+actual_tab_text.getText());


			WebElement expected_tab_text=driver.findElement(By.xpath("//h2[@class=\"brandname\"]"));

			System.out.println("Expected title of the tab is: "+expected_tab_text.getText());

			Thread.sleep(2000);
			driver.navigate().back(); // Go back to Monograph Updates

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

			all_items = driver.findElements(By.xpath("//div[@class='menu-document-item']/following::span/a"));
			Thread.sleep(1000);




		}



	}
}