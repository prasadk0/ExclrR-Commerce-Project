package TextNG;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class verifyFBLink {
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
	public void verifyFBLink() {
		HttpURLConnection huc = null;
	String url =   driver.findElement(By.xpath("//a[contains(text(),'Facebook')]")).getAttribute("href");
	  
	try {
		huc = (HttpURLConnection)(new URL(url).openConnection());

		huc.setRequestMethod("HEAD");

		huc.connect();

		int respCode = huc.getResponseCode();

		if(respCode >= 400){
		//System.out.println(url+" is a broken link");
		Assert.assertTrue(false);
		
		}
		else{
		//System.out.println(url+" is a valid link");
		Assert.assertTrue(true);
		}
		

		
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
		
	}

}
