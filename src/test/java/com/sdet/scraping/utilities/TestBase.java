package com.sdet.scraping.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



//import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException, NoSuchElementException, InterruptedException {
		launchBrowser();	
	}
	
	private void launchBrowser() throws InterruptedException, IOException, org.openqa.selenium.NoSuchElementException {
		System.setProperty("webdriver.chrome.driver",".//Drivers/chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver","\\Users\\bhawn\\OneDrive\\Desktop\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		
		/*WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();*/
		
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
	
	@SuppressWarnings("resource")
	@Test
	public void testMethod() throws InterruptedException, IOException, org.openqa.selenium.NoSuchElementException {
		driver.findElement(By.xpath("//a[@href='RecipeAtoZ.aspx']")).click();
        //driver.manage().window().maximize();
        
     // Set up the Excel workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("AllRecipe");
        File file = new File(".\\datafiles\\AllRecipes2.xlsx");
        
     // Write the workbook to a file

        // Set up the header row
        XSSFRow headerRow = sheet.createRow(0);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true); // Wrap text in cells
        //String[] headers = {"recipeId", "recipeUrl", "recipeName","recipeCategory","recipeIngredients","recipePrepTime","recipeCookTime","recipeMth","recipeNutrients"};
        String[] headers = {"recipeId","recipeName","recipeCategory","foodCategory","recipeIngredients","recipePrepTime","recipeCookTime","recipeUrl","recipeMth","recipeNutrients","targetMorbid"};
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }

		
        
        int rowIndex = 1;
        
       for (char c ='A';c<='Z'; c++) {
        	//for (char c ='A';c<='Z'; c++) {
        
    		        	
        	int i=1;
        	//int i=16;
        	while(true) {
        		//while(i<21) {
        		try {
        			Thread.sleep(1000);
        			String url = "https://www.tarladalal.com/RecipeAtoZ.aspx?beginswith="+c+"&pageindex="+i;
        		    driver.get(url);
        		//driver.manage().window().maximize();
        		    
            	String recipeId = "";
            	String recipeUrl = "";
        		String recipeName = "";
        		String recipeCategory = "";
        		String recipePrepTime = "";
        		String recipeCookTime = "";
        		//String recipeMth = "";
        		
        		List<WebElement> listOfRecipeNames = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
        		
        		JavascriptExecutor js = (JavascriptExecutor) driver;
        		
		
        		
    			for (int k=0; k<listOfRecipeNames.size(); k++)	{
        		//for (int k=0; k<3; k++)	{
    				
    				
    				List<Object> allRecipeList = new ArrayList<Object>();
    				
    				
 

                    // Recipe ID
    				
    				List<WebElement> recipeIDs= driver.findElements(By.xpath("//*[contains(text(), 'Recipe#')]"));

    				List<WebElement> recipelinks= driver.findElements(By.xpath("//span[@class='rcc_recipename']/a"));
    				
    				//recipeNM = recipelinks.get(k).getText();
    				
    				
    				
    				Wait<WebDriver> wt = new FluentWait<WebDriver>(driver)
    						.withTimeout(Duration.ofSeconds(50))
    						.pollingEvery(Duration.ofSeconds(10))
    						.ignoring(NoSuchWindowException.class);
    				
    				
    				WebElement recipeID = wt.until(ExpectedConditions.elementToBeClickable(recipeIDs.get(k)));
    				
    				recipeId = recipeID.getText().split(" ")[1].split("\n")[0];
    				allRecipeList.add(recipeId);
    				//System.out.println(allRecipeList);
    				
    				
    				WebElement recipelink = wt.until(ExpectedConditions.elementToBeClickable(recipelinks.get(k)));
    				
    				//Thread.sleep(1000);
    				
    				// Recipe URL
    				recipeUrl = recipelink.getAttribute("href");
    				//System.out.println(recipeUrl);
    				
    				//System.out.println(allRecipeList);

    				
    				recipelink.click();
    				
    				//Name
    				WebElement recipeNameElement = driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']"));
        			recipeName = recipeNameElement.getText();
        			//System.out.println(recipeName);
        			
        			allRecipeList.add(recipeName);
        			//System.out.println(allRecipeList);
        			         			
        			
        			//Recipe category
        			List<WebElement> recipeCats = driver.findElements(By.xpath("//a[@itemprop='recipeCategory']"));
        			List<String> recipeCAT = new ArrayList<String>();
        			for(WebElement recipeCat : recipeCats) {            			
        			recipeCategory = recipeCat.getText();
        			recipeCAT.add(recipeCategory);
        			//System.out.println(recipeCategory);	
        			}
        			//System.out.println(recipeCAT);
        			//allRecipeList.add(recipeCAT);
        			allRecipeList.add(String.join("\n", recipeCAT));
        			//System.out.println(allRecipeList);
        			
        			//Food category
        			allRecipeList.add("");
        			
        			
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
        			
        			//System.out.println(ingCAT);
        			//allRecipeList.add(ingCAT);
        			allRecipeList.add(String.join("\n", ingCAT));
        			//System.out.println(allRecipeList);
        			
        			//Prep Time
        			
        			WebElement prepTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
        			recipePrepTime = prepTime.getText();
        			//System.out.println(recipePrepTime);
        			allRecipeList.add(recipePrepTime);
        			//System.out.println(allRecipeList);
        			
        			//Cook Time
        			
        			WebElement cookTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
        			recipeCookTime = cookTime.getText();
        			//System.out.println(recipeCookTime);
        			allRecipeList.add(recipeCookTime);
        			//System.out.println(allRecipeList);
        			
        			//RecipeURL
        			allRecipeList.add(recipeUrl);
        		
        			//Prep Method
        			// //ol[@itemprop='recipeInstructions'][1]/li[@id='proc1']
        			List<String> PrepMTH = new ArrayList<String>();
        			int p = 1;
        			while(true) {
        				
        				try {
        					WebElement PrepMethods = driver.findElement(By.xpath("//ol[@itemprop='recipeInstructions'][1]/li[@id='proc"+p+"']/span"));
        					String recipeMth = PrepMethods.getText();
        					PrepMTH.add(recipeMth);           					
        					
        					p++;
        					
        				} catch (org.openqa.selenium.NoSuchElementException e) {
        					break;
        				}
        				
        			}
        			//System.out.println(PrepMTH);
        			//allRecipeList.add(PrepMTH);
        			//System.out.println(allRecipeList);
        			allRecipeList.add(String.join("\n", PrepMTH));
        			
        		
        			
        			// Nutrient Value
        			
        			// //table[@id='rcpnutrients']//td
        			try {
                    //Thread.sleep(2000);
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
        			
        			//System.out.println(NutVal);
        			//allRecipeList.add(NutVal);
        			allRecipeList.add(String.join("\n", NutVal));
        			
        			} catch (org.openqa.selenium.NoSuchElementException e)
        			{
        				System.out.println("No Nutrients");
        			}
        			
        			// Recipe URL //span[@class='rcc_recipename']/a
        			
        			//TargetMorbid
        			allRecipeList.add("");
        			
        			//System.out.println(recipeName); 
        			//Thread.sleep(2000);
        			driver.navigate().back();
        			Thread.sleep(1000);
        			//js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
        			
        			//System.out.println(allRecipeList.size());
        			
        			Row row = sheet.createRow(rowIndex++);
                    for (int u = 0; u < allRecipeList.size(); u++) {
                        Cell cell = row.createCell(u);
                        cell.setCellValue((String) allRecipeList.get(u).toString());
                    }
        			
        			System.out.println(allRecipeList);
        			Thread.sleep(1000);
        			try {
        	            //File file = new File(".\\datafiles\\AllRecipes2.xlsx");
        	            FileOutputStream outputStream = new FileOutputStream(file);
        	            workbook.write(outputStream);
        	            //outputStream.close();
        	            //workbook.close();  
        	        	} catch(IOException e) {
        	        		System.out.println("Continue");
        	        	}
        			
        			
        				
    			}
    			Thread.sleep(1000);
   				

        	i++;
        	
    			
    			}
        		catch (org.openqa.selenium.WebDriverException e) {
	        		break;
	        		}
        	}
        		Thread.sleep(1000);
        	
        
        	/*// Write the workbook to a file
        	try {
            File file = new File(".\\datafiles\\AllRecipes2.xlsx");
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();  
        	} catch(IOException e) {
        		System.out.println("Continue");
        	}*/
            
        

        //driver.quit();

        // Save data to an Excel file using your preferred Java Excel API
    }
      // driver.quit();



}
	}

