package cps2;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class favourite {


	@Test
	public void favourite() throws InterruptedException
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

		driver.findElement(By.xpath("//span[@class=\"d-none d-sm-block\"]")).click();

		Thread.sleep(3000);

		driver.navigate().to("http://cps2-staging.pharmacists.ca/home");

		Thread.sleep(3000);

		List<WebElement> fav_item = driver.findElements(By.xpath("//div[@class=\"tab-pane active\"]/div/div/p/a"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		List<WebElement> recent_items=driver.findElements(By.xpath("//div[@class=\"tab-pane active\"]/div/div/p/a"));

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,document.body.scrollHeight)");


		for(WebElement recent:recent_items)
		{


			String recent_text=recent.getText().toString();



			if(recent_text.equalsIgnoreCase(monograph_name))
			{

				System.out.println(monograph_name+" is showing in recent list");
			}

		}

		
		
		WebElement element=driver.findElement(By.xpath("//*[@id=\"ngb-nav-2\"]"));
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		

            
		
		
		for(WebElement favourite:fav_item)
		{


			String fav_text=favourite.getText().toString();



			if(fav_text.equalsIgnoreCase(monograph_name))
			{

				System.out.println(monograph_name+" is showing in favourite list");
			}

		}





	}

}
