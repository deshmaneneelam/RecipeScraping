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
	
	/**
	 * Launch browser
	 * @throws IOException
	 */
	@BeforeClass
	public void setup() throws IOException {
		launchBrowser();
	}
	
	private void launchBrowser() throws IOException {
		/*WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();*/
		
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		
		ConfigReader config = new ConfigReader();	
		openUrl(config.getUrl());		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	/**
	 * Open project url
	 * @param url
	 */
	private void openUrl(String url) {
		driver.get(url);
	}
	
	/**
	 * Quit browser
	 */
	@AfterClass
	public void closeBrowser() {
		//driver.quit();
	}
	
	/*@Test
	public void testMethod() {
		System.out.println("Here");
	}*/
}