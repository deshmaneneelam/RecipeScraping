package com.sdet.scraping.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AToZPage  {

	WebDriver driver;
	public AToZPage(WebDriver d) {
		this.driver = d;
		PageFactory.initElements(d, this);
		
	}
	
	@FindBy(xpath="//div[@id='toplinks']/a[5]")
	WebElement atozLink;
	
	@FindBy(xpath="//div[@id='maincontent']/div/div[2]/a[last()]")
	WebElement count;
	
	public void clickAToZLink() {
		atozLink.click();
	}
	
	public int countOfPages(){
		
		int s = Integer.valueOf(count.getText());
		System.out.println("page::"+s);
		return s;
		
	}
}
