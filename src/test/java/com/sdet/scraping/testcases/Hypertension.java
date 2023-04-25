package com.sdet.scraping.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.sdet.scraping.utilities.Excel;
import com.sdet.scraping.utilities.Utils;

public class Hypertension extends Utils{

	String[][] allRecipes;
	
	/**
	 * Get recipes from AllRecipes.xls
	 * @return
	 * @throws IOException
	 */
	/*@BeforeClass
	public String[][] getAllRecipes() throws IOException {
		allRecipes = Excel.ExcelUtil();
		//System.out.println(Arrays.toString(allRecipes));
		return allRecipes;
	}*/
	
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
			boolean flag=false;
			String[] ingredients;
			for(int col=0; col<colLength; col++)
			{
				if(col < allRecipes[row].length-1) {
					 ingredients = String.valueOf(allRecipes[row][col]).split(",") ;
					
					 flag = eliminateRecipes(ingredients);
				}	
				if(flag==false) {
					 break;
				 }
				if(col==4) {
						 ingredients = String.valueOf(allRecipes[row][col]).split(",") ;
						 String toAddIngredients = toAddRecipes(ingredients);
						 allRecipes[row][9] = "High Blood Pressure";
						 allRecipes[row][11] = toAddIngredients;			 
				}
			}
			if(flag==true) 
			{					
						allowedRecipes.add(allRecipes[row]);
						k++;
			}
			
		}
		//Write to excel
		Excel.writeToExcel(allowedRecipes,"RecipesByMorbidity.xlsx","Hypertension");
	}
	
	/**
	 * Eliminate recipes based on eliminate ingredients
	 * @param ingredients
	 * @return boolean
	 */
	private static boolean eliminateRecipes(String[] ingredients) {
		ArrayList<String> eliminateList = Utils.eliminateHypertension();
		//System.out.println(Arrays.toString(ingredients));
		for(int i=0;i<ingredients.length;i++) {
			for(int j=0;j<eliminateList.size();j++) {
				//System.out.println("Here:"+ingredients[i]+" and  "+eliminateList.get(j));
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
	    ArrayList<String> toAddList = Utils.toAddHypertension();
	    Set<String> toAddIngredients = new HashSet();
	    
	    for (String ingredient : ingredients) {
	        for (String toAdd : toAddList) {
	            if (ingredient.toLowerCase().contains(toAdd)) {
	            	toAddIngredients.add(toAdd);
	 //           	System.out.println("toAddIngredients.add(toAdd)"+toAddIngredients.add(toAdd));
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
	//        System.out.println("sb.toString()"+sb.toString());
	        return sb.toString();
	    } else {
	        return "";
	    }
	}  
}	
