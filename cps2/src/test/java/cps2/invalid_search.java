package cps2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class invalid_search
{
	
	@Test	
	
public void invalid_search() throws InterruptedException
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
	
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//input")).sendKeys("****",Keys.ENTER);

	Thread.sleep(2000);

	String error_message=driver.findElement(By.xpath("//div[@class=\"no-result-found\"]")).getText();
	
	System.out.println("Error message displayed is >> "+error_message);


}

}