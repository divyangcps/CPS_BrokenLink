package link;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class lexi_interact {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void verify() throws Exception {
     driver.get("http://cps-preprod.pharmacists.ca/login");
    	//driver.get("http://cps2-staging.pharmacists.ca/login");
        driver.findElement(By.id("username")).sendKeys("Tester1-etpc");
        driver.findElement(By.id("password")).sendKeys("Tester1-etpc");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='globalNav-menu']/ul/li[4]")));
        driver.findElement(By.xpath("//div[@class='globalNav-menu']/ul/li[4]")).click();
        
        Thread.sleep(3000);
        
        
        driver.navigate().refresh();
        driver.navigate().refresh();

        Thread.sleep(3000);

        driver.switchTo().frame(driver.findElement(By.id("diFrame")));
        System.out.println("Switched frame");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);
        
        try {
            WebElement print = driver.findElement(By.xpath("//*[@id='context-bar-holder']/div/div[4]/a"));
            
            if (!print.isDisplayed()) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File src = ts.getScreenshotAs(OutputType.FILE);
                File dest = new File("./lexi_interact_" + timestamp + ".png");
                org.openqa.selenium.io.FileHandler.copy(src, dest);
                System.out.println("Lexi interact is not loading properly, screenshot is taken");
            } else {
                System.out.println("Lexi-interact page got loaded successfully");
            }
        } catch (NoSuchElementException e) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File("./lexi_interact_" + timestamp + ".png");
            org.openqa.selenium.io.FileHandler.copy(src, dest);
            System.out.println("Lexi interact page is not loading correctly, screenshot is taken");
        }
    }
    
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
