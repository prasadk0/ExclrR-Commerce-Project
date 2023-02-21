package TextNG;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyTheExactCount {
	WebDriver driver;	
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		 driver  = new ChromeDriver();
	driver.get("https://www.saucedemo.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	driver.findElement(By.id("user-name")).sendKeys("standard_user");
	driver.findElement(By.id("password")).sendKeys("secret_sauce");
	driver.findElement(By.id("login-button")).click();
		
	}
	
	@AfterMethod
	public void close() {
		driver.close();
		}
	
	
	
	@Test
	public void verifyCount() throws InterruptedException {
		 List<WebElement>	ls ;
		 int count = 0;
		 ls = driver.findElements(By.className("pricebar"));
		
		for(WebElement wb :ls) {
		   wb.findElement(By.tagName("button")).click();
		   count++;
		 
			if(wb==null) {
				Assert.assertTrue(false);
			}
		}
		String exp = Integer.toString(count);
		WebElement cart = driver.findElement(By.className("shopping_link"));
		String actual = cart.getText();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-300)","");
		//System.out.println(exp+" "+actual);
		if(exp==actual) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		
		
	}
	

}
