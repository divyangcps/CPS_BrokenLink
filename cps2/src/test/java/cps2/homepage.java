package cps2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class homepage {


	@Test	
	public void homepage() throws InterruptedException
	{


		WebDriver driver=WebDriverManager.chromedriver().create();

		driver.get("http://cps2-staging.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		Thread.sleep(3000);

		String loggedin_user=driver.findElement(By.xpath("//a[@class=\"dropdown-toggle userdropdown-toggle\"]/span/div")).getText();

		System.out.println("Logged in user is/t "+ loggedin_user);



		List<WebElement> whats_new=driver.findElements(By.xpath("//div[@class=\"home-sidebar-container\"]/div/div/span"));


		for(WebElement whats_new_section: whats_new )
		{



			System.out.println(whats_new_section.getText()+"\n");
		}


		List<WebElement> home_page_links=driver.findElements(By.xpath("//div[@class=\"home-feature-text col-lg-12 col-md-8 col-sm-7 col-xs-6\"]/p"));

		for(WebElement home_page_element: home_page_links)

		{


			String link_displayed=home_page_element.getText().toString();
			System.out.println(link_displayed+"\t"+"is showing on home page");

		}


		List<WebElement> Fav_list = driver.findElements(By.xpath("//body[1]/app-root[1]/main[1]/app-cps-home-page[1]/div[1]/div[3]/div[1]/app-cps-home-history[1]/div[1]/div[1]/div[1]/div/div/p/a"));
		
		for(WebElement fav_item:Fav_list)
		{
			System.out.println("Links under favourite are\t>>  "+fav_item.getText());

		}
		
		List<WebElement> recent_list=driver.findElements(By.xpath("//body[1]/app-root[1]/main[1]/app-cps-home-page[1]/div[1]/div[3]/div[1]/app-cps-home-history[1]/div[1]/div[1]/div[1]/div[1]/div/p/a"));



		for(WebElement recent_item:recent_list)
		{
			System.out.println("Links under recent are\t>> "+recent_item.getText());

		}


		List<WebElement> footer_links=driver.findElements(By.xpath("//div[@class=\"footer-menu col-xl-9 col-lg-9 col-md-12\"]/ul/li/a"));


		for(WebElement footer:footer_links)
		{

			System.out.println("Footer links are\t"+footer.getText());

		}
	}

}
