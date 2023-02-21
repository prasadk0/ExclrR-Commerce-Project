package TextNG;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
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
	public void brokenLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
//		for(WebElement e:links) {
//			System.out.println(e);
//		}
		String url = "";
		HttpURLConnection huc = null;
		Iterator<WebElement> it = links.iterator();
   String homePage="https://www.saucedemo.com";
		while(it.hasNext()){

		url = it.next().getAttribute("href");

		//System.out.println(url);

		if(url == null || url.isEmpty()){
		System.out.println("URL is either not configured for anchor tag or it is empty");
		continue;
		}

		if(!url.startsWith(homePage)){
		System.out.println("URL belongs to another domain, skipping it.");
		continue;
		}

		try {
		huc = (HttpURLConnection)(new URL(url).openConnection());

		huc.setRequestMethod("HEAD");

		huc.connect();

		int respCode = huc.getResponseCode();

		if(respCode >= 400){
		System.out.println(url+" is a broken link");
		}
		else{
		System.out.println(url+" is a valid link");
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


}
