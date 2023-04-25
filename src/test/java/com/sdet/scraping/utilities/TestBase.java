package com.sdet.scraping.utilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends Utils {

	public WebDriver driver;

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
		
		System.setProperty("webdriver.gecko.driver",driverPath()+"geckodriver.exe");
		driver = new FirefoxDriver();
		
		//WebDriverManager.firefoxdriver().setup();
		//driver = new FirefoxDriver();
		
		ConfigReader config = new ConfigReader();	
		openUrl(config.getUrl());		
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
		driver.quit();
	}
	
	
	public void goBack() {
		System.out.println("kkkkkkkkkkkkk");
		driver.navigate().back();
	}
	
	
}