package TextNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyCheckoutButton {
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
	public void verifyCheckoutButton() {
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
		//click on the add to cart
		driver.findElement(By.className("shopping_cart_link")).click();
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		WebElement ele = driver.findElement(By.className("checkout_info"));
		Assert.assertTrue(ele.isDisplayed());
		

	}
	
}
