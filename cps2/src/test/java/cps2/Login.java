package cps2;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {



	public static void main(String[] args) throws InterruptedException {
		
	//	WebDriver driver=WebDriverManager.chromedriver().create();

		WebDriver driver=	WebDriverManager.chromedriver().clearDriverCache().create();
		//WebDriverManager.chromedriver().clearResolutionCache().setup();

		//WebDriver driver=WebDriverManager.chromedriver().create();

		driver.get("http://cps2-staging.pharmacists.ca/login");

		driver.findElement(By.id("username")).sendKeys("Tester1-etpc");

		driver.findElement(By.id("password")).sendKeys("Tester1-etpc");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

		driver.getTitle();

		System.out.println("login is successfull");

		Thread.sleep(400);
		

		driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[1]")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[text()='Monograph Updates']")).click();

		Thread.sleep(1000);



		//	List<WebElement> all_monograph=driver.findElements(By.xpath("//table//tr//span"));




		List<WebElement> all_monograph_titles = driver.findElements(By.xpath("//table//tr/td[1]//span"));

		for (int i = 0; i < all_monograph_titles.size(); i++) 

		{
			WebElement monographTitle = driver.findElements(By.xpath("//table//tr/td[1]//span")).get(i);


			
			
			if(monographTitle.getAttribute("class").toString().equals("no-link-text"))


			{
				System.out.println("monograph\t"+monographTitle.getText()+"\tis not clickable");
                      continue;

			}


			else

			{
				monographTitle.click();

				Thread.sleep(2000); // Wait for the page to load
				// Add code here to perform actions after clicking if needed


				WebElement actual_tab_text=driver.findElement(By.xpath("//span[text()='Recent: ']//following::div[1]"));

				System.out.println("Actual title of tab is :  "+actual_tab_text.getText());
				
				
				WebElement expected_tab_text=driver.findElement(By.xpath("//h2[@class=\"brandname\"]"));
				
				System.out.println("Expected title of the tab is: "+expected_tab_text.getText());

				driver.navigate().back(); // Go back to Monograph Updates
				Thread.sleep(1000);
				
				
			}

		}
		


		driver.close();

	}
}