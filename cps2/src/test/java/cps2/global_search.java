package cps2;

import java.awt.RenderingHints.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class global_search


{
	

@Test
public void global_search() throws InterruptedException
{

	WebDriver driver=WebDriverManager.chromedriver().create();

	driver.get("http://cps2-staging.pharmacists.ca/login");

	driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

	driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

	driver.manage().window().maximize();

	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

         	Thread.sleep(2000);	
	driver.findElement(By.xpath("//input")).sendKeys("acne");
	

	driver.findElement(By.xpath("//input")).sendKeys(Keys.ENTER);
 	Thread.sleep(2000);	
 	

 	
 	List<WebElement> toc_items= driver.findElements(By.xpath("//app-cps-table-of-contents/div/div/ul/li/a"));
 	
 	
 	for(WebElement toc_item:toc_items)
 	{
 		
 		String value=toc_item.getText().toString();
 		
 		
 		System.out.println("TOC content is >>  "+value);
 	}
 	
 	WebElement monoghraph_count=driver.findElement(By.xpath("//div[text()=\"Monographs\"]/following-sibling::span"));
 	
 	System.out.println("monograph count are>> "+ monoghraph_count.getText());
 	
 	WebElement condition_count= driver.findElement(By.xpath("//div[text()=\"Conditions\"]/following-sibling::span"));
 	System.out.println("condition count are>> "+ condition_count.getText());


 	WebElement Patient_Information_count= driver.findElement(By.xpath("//div[text()=\"Patient Information\"]/following-sibling::span"));

 	System.out.println("Patient Information count are >> "+Patient_Information_count.getText());
 	
}






}