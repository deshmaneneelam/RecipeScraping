package com.sdet.scraping.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.sdet.scraping.utilities.Excel;
import com.sdet.scraping.utilities.TestBase;
import com.sdet.scraping.utilities.Utils;

public class Diabetes extends Utils{
	
	String[][] allRecipes;
	
	
	/**
	 * Check
	 * @throws IOException
	 */
	@Test
	public void checkIngredients() throws IOException {
		
		HashSet<String[]> allowedRecipes = new HashSet<String[]>(); 
		
		allRecipes = Utils.getAllRecipes();
		int rowLength = allRecipes.length;
		int colLength = allRecipes[0].length;
		int k=0;
		for(int row=0; row<rowLength; row++) {
			for(int col=0; col<colLength; col++) {
		
				if(col==4) {
				
					String[] ingredients = String.valueOf(allRecipes[row][col]).split(",") ;
					
					boolean flag = eliminateRecipes(ingredients);
					if(flag==true) {
				
						allowedRecipes.add(allRecipes[row]);
						k++;
						String toAddIngredients = toAddRecipes(ingredients);
		//				allRecipes[row][9] = "Diabetes";
						allRecipes[row][11] = toAddIngredients;
						
					}
				}
			}
			
		}
		//Write to excel
		Excel.writeToExcel(allowedRecipes,"RecipesByMorbidity.xlsx","Diabetes");
	
	}
	
	/**
	 * Eliminate recipes based on eliminate ingredients
	 * @param ingredients
	 * @return boolean
	 */
	private static boolean eliminateRecipes(String[] ingredients) {
		ArrayList<String> eliminateList = Utils.eliminateDiabetes();
		for(int i=0;i<ingredients.length;i++) {
			for(int j=0;j<eliminateList.size();j++) {
				if(ingredients[i].toLowerCase().indexOf(eliminateList.get(j)) != -1) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Added ingredients based on To add ingredients
	 * @param ingredients
	 * @return String
	 */
	private static String toAddRecipes(String[] ingredients) {
	    ArrayList<String> toAddList = Utils.toAddDiabetes();
	    Set<String> toAddIngredients = new HashSet();
	    
	    for (String ingredient : ingredients) {
	        for (String toAdd : toAddList) {
	            if (ingredient.toLowerCase().contains(toAdd)) {
	            	toAddIngredients.add(toAdd);
	            }
	        }
	    }
	    StringBuilder sb = new StringBuilder();
	    for (String toAdd : toAddIngredients) {
	        sb.append(toAdd);
	        sb.append(", ");
	    }

	    if (sb.length() > 0) {
	        sb.setLength(sb.length() - 2);
	  
	        return sb.toString();
	    } else {
	        return "";
	    }
	}  
}

