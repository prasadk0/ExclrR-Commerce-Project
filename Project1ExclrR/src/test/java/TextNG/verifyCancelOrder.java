package TextNG;

import java.time.Duration;

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

public class verifyCancelOrder {
	WebDriver driver;
	
	@Test
	public void verifyCancelOrder() {
		//adding items
				driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
				//click on the add to cart
				driver.findElement(By.className("shopping_cart_link")).click();
				driver.findElement(By.xpath("//button[@id='checkout']")).click();
				driver.findElement(By.id("first-name")).sendKeys("shard");
				driver.findElement(By.id("last-name")).sendKeys("raj");
				driver.findElement(By.id("postal-code")).sendKeys("001");
				driver.findElement(By.id("continue")).click();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,500)");
		       driver.findElement(By.xpath("//button[@id='cancel']")).click();
		       WebElement ele  = driver.findElement(By.className("app_logo"));
				Assert.assertTrue(ele.isDisplayed());
				

		
	}
	
	
	
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
	

}
