package cps2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class login_ecps
{
	
@Test

public void login_ecps_test()


{
	
	
	WebDriver driver=WebDriverManager.chromedriver().create();

	driver.get("http://cps2-staging.pharmacists.ca/login");

	driver.findElement(By.id("username")).sendKeys("Tester1-ecps");

	driver.findElement(By.id("password")).sendKeys("Tester1-ecps");

	driver.manage().window().maximize();

	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

	driver.getTitle();

	System.out.println("login is successfull");
	
	String name_displayed=driver.findElement(By.xpath("//div[@class=\"globalNav-menu\"]/ul/li[1]")).getText().toString();
System.out.println("subscription displayed>>   "+name_displayed);

String username=driver.findElement(By.xpath("//div[@class=\"nav-username\"]")).getText(); 

System.out.println("logged in user is>> " +username);

String home_section=driver.findElement(By.xpath("//div[@class=\"home-sidebar-panel\"]")).getText().toString();

System.out.println("home section text/t >> "+home_section);

Assert.assertEquals("Tester1 ECPS", username);



driver.close();
	


}

}