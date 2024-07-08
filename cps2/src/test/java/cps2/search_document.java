package cps2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class search_document {



	@Test
	public void search_document() throws InterruptedException

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

		String monograph_name = driver.findElement(By.xpath("//div[@class=\"menu-document-item\"]/span/a/span[@id=\"monographsAbelcet\"]")).getText();
		driver.findElement(By.xpath("//div[@class=\"menu-document-item\"]/span/a/span[@id=\"monographsAbelcet\"]")).click();

		Thread.sleep(3000);


		WebElement search_box=driver.findElement(By.xpath("//input[@name=\"search-input\"]"));

		search_box.sendKeys("Drug");

		Thread.sleep(3000);

		WebElement search_result=driver.findElement(By.xpath("//span[@class=\"search-results-text\"]"));

		System.out.println("Number of search result is "+search_result.getText());

		String searchResultText = search_result.getText();




		WebElement next_search=driver.findElement(By.xpath("//button[@class=\"btn btn-outline-secondary search-btn down-chevron\"]"));

		WebElement previous=driver.findElement(By.xpath("//button[@class=\"btn btn-outline-secondary search-btn up-chevron\"]"));






		int searchResultCount = Integer.parseInt(searchResultText.split(" ")[0]); // Assuming the count is at the beginning of the text

		// Get the next and previous search result navigation buttons

		// Simulate navigation through search results
		for (int i = 0; i < searchResultCount; i++) {
			// Navigate to the next search result
			next_search.click();
			Thread.sleep(1000); // Adjust as needed
		}

		// Simulate navigating back to the first search result
		for (int i = 0; i < searchResultCount; i++) {
			// Navigate to the previous search result
			previous.click();
			Thread.sleep(1000); // Adjust as needed
		}


	}
}
