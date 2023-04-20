package com.sdet.scraping.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException {
		launchBrowser();	
	}
	
	private void launchBrowser() throws IOException {
		/*WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();*/
		
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		/*ConfigReader config = new ConfigReader();	
		String u = config.getUrl(); 
		System.out.println("url::"+u);*/
		
		openUrl("https://www.tarladalal.com/");
	
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	private void openUrl(String url) {
		driver.get(url);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
	
	@Test
	public void testMethod() {
		System.out.println("Here");
	}
}