package cps2;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class print {



	@Test
	public void print() throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver=WebDriverManager.chromedriver().create();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("http://cps2-staging.pharmacists.ca/login");

		String current_window=driver.getWindowHandle();
		System.out.println("current window handle >> "+current_window);

		//driver.get("https://cps2-preprod.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();


		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();


		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")).click();

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[3]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//body//app-root//div[@class='menu-document-body']//div//div[1]//div[1]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class=\"menu-document-item\"]/span/a/span[@id=\"monographsAbelcet\"]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@class='print-group']")).click();

		
		System.out.println("Print pop up opened sucessfully");
		

		Thread.sleep(2000);
		driver.close(); 

	} 


}


