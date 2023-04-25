package com.sdet.scraping.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sdet.scraping.pageobjects.AToZPage;
import com.sdet.scraping.utilities.ConfigReader;
import com.sdet.scraping.utilities.Excel;
import com.sdet.scraping.utilities.TestBase;


public class ScrapRecipes extends TestBase {

	//WebDriver driver;
	AToZPage az;
	
	@BeforeMethod
	public void initialize() throws Exception{
		az= new AToZPage(driver);
	}
	
	@Test
	public void ClickA() {
		az.clickAToZLink();
	}
	
	@Test
	public void scrape() throws InterruptedException, IOException {
		
		ConfigReader config = new ConfigReader();		
		List<WebElement> recipeNameList;
		
		for(char c='B';c<='Z';c++) {
			try {
				
				int page = 1;
				do {
					//System.out.println("##### Char:" + c + ", page: " + page);
					driver.navigate().to(config.getUrl() + "RecipeAtoZ.aspx?beginswith="+c+"&pageindex="+page);
					recipeNameList = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
					int i=0;
					if(c=='A')
						i=3;
	
					for(;i<recipeNameList.size();i++) {
						
						System.out.println("##### Char:" + c + ", page: " + page + ", count: " + i);
						//if(i>3) break;
						System.out.println(recipeNameList.get(i).getText());
						recipeNameList.get(i).click();
						Thread.sleep(2000);
						
						ArrayList<String> recipeDetails = getRecipeDetails();
						
						appendToExcel(recipeDetails);
						
						driver.navigate().back();
						Thread.sleep(2000);
						js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
						recipeNameList = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
						
						//System.out.println("NExt::"+recipeNameList.get(i).getText());
						
					}
					page++;
				} while(page <=az.countOfPages());
			} catch(WebDriverException e) {	
				e.printStackTrace();
			}
		}
	
	}
	/**
	 * Write to excel
	 * @param recipeDetails
	 * @throws IOException
	 */
	private void appendToExcel(ArrayList<String> recipeDetails) throws IOException {
		Excel.appendToExcel(recipeDetails, "Recipes.xlsx","recipe");
	}

	/**
	 * Get recipe details
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getRecipeDetails() {
		
		ArrayList<String> recipeDetails = new ArrayList<String>();
		
		
		//Wait until element loads
		String recipeTitle=null;
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(50));
			
			recipeTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='ctl00_cntrightpanel_lblrecipeNameH2']"))).getText().split("-")[0];
			//System.out.println("Recipe name:"+recipeTitle);
		}catch(TimeoutException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String[]hrefLink = driver.findElement(By.xpath("//a[@id='signinlink']")).getAttribute("href").split("-");
		String recipeId = hrefLink[hrefLink.length-1];
		String id = recipeId.substring(0, recipeId.length()-1);
		
		
		//Recipe Id
		recipeDetails.add(id);
		
		//Recipe title
		recipeDetails.add(recipeTitle);
		
		
		//Recipe Category
		String recipeCategory = recipeCategory();
		recipeDetails.add(recipeCategory);
		
		//Food category
		String foodCategory = foodCategory(recipeTitle);
		recipeDetails.add(foodCategory);
		//System.out.println("foodCategory::"+foodCategory);
		
		//Ingredients
		String ingredient = driver.findElement(By.xpath("//div[@id='recipe_ingredients']")).getText();
		//System.out.println("Ingredients::"+ingredient);
		recipeDetails.add(ingredient);
		
		//Cooking and other times
		recipeDetails.add(driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText());
		recipeDetails.add(driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText());
		
		
		//Method
		String method = driver.findElement(By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']")).getText();
		//System.out.println(method);
		recipeDetails.add(method);
		
		//Nutrient values
		String nutrientValues = nutrientValues();
		recipeDetails.add(nutrientValues);
		
		//Target morbidity
		String targetMorbid = morbidity();
		recipeDetails.add(targetMorbid);
		
		//Recipe url
		recipeDetails.add(driver.getCurrentUrl());
		
		return recipeDetails;
	}
	
	/**
	 * Get recipe category
	 * @return
	 */
	private String recipeCategory() {
		String recipeCategory=null;
		List<WebElement> recipeTags = driver.findElements(By.xpath("//div[@id='recipe_tags']/a/span"));
		for(WebElement i: recipeTags) {
			
			String tags = i.getText().toLowerCase();
			
			if(tags.contains("curry") || tags.contains("lunch") || tags.contains("dinner")) {
				recipeCategory = "Lunch/Dinner";
				break;
			} else if(tags.contains("breakfast")) {
				recipeCategory = "Breakfast";
				break;
			} else if(tags.contains("dessert") || tags.contains("sweet") || tags.contains("mithai")) {
				recipeCategory = "Dessert";
				break;
			} else if(tags.contains("snacks") || tags.contains("namkin")) {
				recipeCategory = "Snacks";
				break;
			} else if(tags.contains("pickle") || tags.contains("achaar")) {
				recipeCategory = "Pickle";
				break;
			}
		}
		//System.out.println("recipeCategory:::"+recipeCategory);
		return recipeCategory;
	}
	
	/**
	 * Find out morbidity
	 * @return String
	 */
	private String morbidity() {
		String morbidity = null;
		try {
			List<WebElement> image = driver.findElements(By.xpath("//div[@class='rcc_caticons']/img"));
			//System.out.println("image size::"+image.size());
			if(image.size()>0) {
				
				for(WebElement i: image) {
					//System.out.println("image src::"+i.getAttribute("src"));
					String[] imgSrc =  i.getAttribute("src").split("/");
					//System.out.println(Arrays.toString(imgSrc));
					String imageName = imgSrc[imgSrc.length-1].toLowerCase();
					//System.out.println("imageName::"+imageName);
					if(imageName.equals("diabetic.gif")) {
						morbidity = "Diabeties";
						break;
					}else if(imageName.equals("healthy-heart.gif")){
						morbidity = "Hypertension";
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("morbidity::"+morbidity);
		return morbidity;
	}
	/**
	 * get nutrient values
	 * @return String
	 */
	private String nutrientValues() {
		
		String nutrientValue = null;
		try {
			
			List<WebElement> el  = driver.findElements(By.xpath("//div[@id='rcpnuts']"));
			if(el.size() > 0) {
				nutrientValue = el.get(0).getText();				
				//System.out.println(el.get(0).getText());
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return nutrientValue;
	}
	
	/**
	 * get food category
	 * @param title
	 * @return String
	 */
	public String foodCategory(String title) {
		String foodCategory = null;
		if(title.contains("eggs")) {
			foodCategory = "Eggitarian";
		}else {
			List<WebElement> breadCrumb = driver.findElements(By.xpath("//div[@class='breadcrumb']/span/a"));
			for(WebElement i: breadCrumb) {
					if(i.getText().toLowerCase().contains("jain")) {
						foodCategory = "Jain";
					}else if(i.getText().toLowerCase().contains("eggs")) {
						foodCategory = "Eggitarian";
					}else {
						foodCategory = "Veg";
					}
				}
			}
		return foodCategory;
	}
}