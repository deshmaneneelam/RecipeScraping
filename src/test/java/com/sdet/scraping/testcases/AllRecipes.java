package com.sdet.scraping.testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class AllRecipes {
		
		public static void main(String[] args) throws InterruptedException {
	        System.setProperty("Webdriver.chrome.driver","//Drivers//chromedriver.exe");
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("https://www.tarladalal.com/");
	        
	        driver.findElement(By.xpath("//a[@href='RecipeAtoZ.aspx']")).click();
	        //driver.manage().window().maximize();
	        
	        for (char c ='A';c<='Z'; c++) {
	        	//String url = "https://www.tarladalal.com/RecipeAtoZ.aspx?beginswith=" +c+ "E&pageindex=1";
	        	//driver.get(url);
	        	
	        	int i=1;
	        	while(true) {
	        		try {
	        			Thread.sleep(3000);
	        			String url = "https://www.tarladalal.com/RecipeAtoZ.aspx?beginswith="+c+"&pageindex="+i;
	        		    driver.get(url);
	        		//driver.manage().window().maximize();
	        		    
                	        		
	        		String recipeName = "";
	        		String recipeCategory = "";
	        		String recipePrepTime = "";
	        		String recipeCookTime = "";
	        		String recipeMth = "";
	        		
	        		List<WebElement> listOfRecipeNames = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
	        		
	        		JavascriptExecutor js = (JavascriptExecutor) driver;
        			for (int k=0; k<listOfRecipeNames.size(); k++)	{
        				List<WebElement> recipelinks= driver.findElements(By.xpath("//span[@class='rcc_recipename']/a"));
        				
        				//recipeNM = recipelinks.get(k).getText();
        				
        				
        				
        				Wait<WebDriver> wt = new FluentWait<WebDriver>(driver)
        						.withTimeout(Duration.ofSeconds(50))
        						.pollingEvery(Duration.ofSeconds(10))
        						.ignoring(NoSuchWindowException.class);
        				
        				WebElement recipelink = wt.until(ExpectedConditions.elementToBeClickable(recipelinks.get(k)));
        				
        				Thread.sleep(2000);
        				
        				// Recipe URL
        				String recipeUrl = recipelink.getAttribute("href");
        				System.out.println(recipeUrl);
        				
        				recipelink.click();
        				
        				//Name
        				WebElement recipeNameElement = driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']"));
            			recipeName = recipeNameElement.getText();
            			System.out.println(recipeName);
            			
            			//ID
            			//WebElement recipeId = driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']"));
            			//recipeName = recipeNameElement.getText();
            			
            			
            			//Recipe category
            			List<WebElement> recipeCats = driver.findElements(By.xpath("//a[@itemprop='recipeCategory']"));
            			List<String> recipeCAT = new ArrayList<String>();
            			for(WebElement recipeCat : recipeCats) {            			
            			recipeCategory = recipeCat.getText();
            			recipeCAT.add(recipeCategory);
            			//System.out.println(recipeCategory);	
            			}
            			System.out.println(recipeCAT);
            			
            			//Food category
            			
            			//Ingredients
            			List<WebElement> ing1 = driver.findElements(By.xpath("//span[@itemprop='recipeIngredient']/span"));
            			List<WebElement> ing2 = driver.findElements(By.xpath("//span[@itemprop='recipeIngredient']/a/span"));
            			
            			List<String> ingCAT = new ArrayList<String>();
           			
            			for(int m=0; m<ing1.size();m++ ) {
            				String ing1Value = ing1.get(m).getText();
            				String ing2Value = ing2.get(m).getText();
            				String ingLine = ing1Value + " " + ing2Value;

            			ingCAT.add(ingLine);
            			}
            			
            			System.out.println(ingCAT);
            			
            			//Prep Time
            			
            			WebElement prepTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
            			recipePrepTime = prepTime.getText();
            			System.out.println(recipePrepTime);
            			
            			//Cook Time
            			
            			WebElement cookTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
            			recipeCookTime = cookTime.getText();
            			System.out.println(recipeCookTime);
            		
            			//Prep Method
            			// //ol[@itemprop='recipeInstructions'][1]/li[@id='proc1']
            			List<String> PrepMTH = new ArrayList<String>();
            			int p = 1;
            			while(true) {
            				
            				try {
            					WebElement PrepMethods = driver.findElement(By.xpath("//ol[@itemprop='recipeInstructions'][1]/li[@id='proc"+p+"']/span"));
            					recipeMth = PrepMethods.getText();
            					PrepMTH.add(recipeMth);           					
            					
            					p++;
            					
            				} catch (org.openqa.selenium.NoSuchElementException e) {
            					break;
            				}
            				
            			}
            			System.out.println(PrepMTH);
            		
            			
            			// Nutrient Value
            			
            			// //table[@id='rcpnutrients']//td
            			try {
            			//WebElement table = driver.findElement(By.xpath("//table[@id='rcpnutrients']"));
                        Thread.sleep(2000);
            			WebElement table = driver.findElement(By.xpath("//table[@id='rcpnutrients']"));
            			
            		
            			List<WebElement> rows = table.findElements(By.tagName("tr"));
            			List<String> NutVal = new ArrayList<String>();
            			for (WebElement row : rows) {
            				List<WebElement> eachrows = row.findElements(By.tagName("td"));
            				for (WebElement eachrow : eachrows) {
            					NutVal.add(eachrow.getText());
            					//System.out.println(eachrow.getText());
            				}
            			}
            			
            			System.out.println(NutVal);
            			
            			} catch (org.openqa.selenium.NoSuchElementException e)
            			{
            				System.out.println("No Nutrients");
            			}
            			
            			// Recipe URL //span[@class='rcc_recipename']/a
            			
            			
            			//System.out.println(recipeName); 
            			Thread.sleep(2000);
            			driver.navigate().back();
            			Thread.sleep(2000);
            			js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
            			
            			
            			
	        		}
        				
 
	        	i++;
	        	
	        		}
	        		catch (org.openqa.selenium.NoSuchElementException e) {
		        		break;
		        		}
	        	}
	        	
	        	
	        
	        
	        

	        driver.quit();

	        // Save data to an Excel file using your preferred Java Excel API
	    }

	

}
		}

