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

public class patient_info {
	
	
	
	
	
	
	
	@Test
	
	public void patient_info() throws InterruptedException
	{

	WebDriver driver=WebDriverManager.chromedriver().create();
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	driver.get("http://cps2-staging.pharmacists.ca/login");
	
	//driver.get("https://cps2-preprod.pharmacists.ca/login");

	driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

	driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

	driver.manage().window().maximize();


	driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();


	driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")).click();


	driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]/ul/li[10]")).click();           
	
     Thread.sleep(3000);
			
			List<WebElement> all_items= driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/span/a"));
			for (int i = 0; i <all_items.size(); i++) 
			{

				WebElement current_index = all_items.get(i);

				Actions action = new Actions(driver);
				action.moveToElement(current_index).build().perform();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
				wait.until(ExpectedConditions.visibilityOf(current_index));
				current_index.click();
				

				WebElement actual_tab_text=driver.findElement(By.xpath("//span[text()='Recent: ']//following::div[1]"));

				System.out.println("Actual title of tab is :  "+actual_tab_text.getText());


				WebElement expected_tab_text=driver.findElement(By.xpath("//h2"));

				System.out.println("Expected title of the tab is: "+expected_tab_text.getText());

				Thread.sleep(1000);
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
				driver.navigate().back();
				all_items = driver.findElements(By.xpath("//div[@class=\"menu-document-item\"]/span/a"));
				Thread.sleep(1000);
}

			driver.close();


}
	
	

}
