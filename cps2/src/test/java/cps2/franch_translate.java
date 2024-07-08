package cps2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class franch_translate {

	
	
	@Test
	public void translate() throws InterruptedException
	{
		

		WebDriver driver=WebDriverManager.chromedriver().create();

		driver.get("http://cps2-staging.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		Thread.sleep(3000);
		
		String eng_title=driver.getTitle();
		
		System.out.println("Page title in english is >> "+eng_title);
		
		driver.findElement(By.xpath("//span[text()=\"FR\"]")).click();
		
		String french_title=driver.getTitle();
		
		Thread.sleep(3000);
		System.out.println("Page title in french is >> "+french_title);

		String name_displayed=driver.findElement(By.xpath("//div[@class=\"globalNav-menu\"]/ul/li[1]")).getText().toString();
		System.out.println("subscription displayed>>   "+name_displayed);

		Assert.assertEquals(french_title, eng_title);
		
        driver.close();
		
	}
}
