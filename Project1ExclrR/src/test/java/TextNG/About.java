package TextNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class About {
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
	public void About() throws InterruptedException {
	
	//Login();
	driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
	driver.findElement(By.xpath("//a[@id='about_sidebar_link']")).click();
	String expected = "Cross Browser Testing, Selenium Testing, Mobile Testing";
	Thread.sleep(3000);
	String actual =driver.getTitle();
	  Assert.assertEquals(expected, actual);
	//System.out.println(actual +" "+expected);
}
}
