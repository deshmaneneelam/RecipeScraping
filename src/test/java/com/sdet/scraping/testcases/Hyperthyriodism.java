package com.sdet.scraping.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sdet.scraping.utilities.Excel;
import com.sdet.scraping.utilities.IngredientsList;

public class Hyperthyriodism extends IngredientsList{

	String[][] allRecipes;
	
	/**
	 * Get recipes from AllRecipes.xls
	 * @return
	 * @throws IOException
	 */
	@BeforeClass
	public String[][] getAllRecipes() throws IOException {
		allRecipes = Excel.ExcelUtil();
		//System.out.println(Arrays.toString(allRecipes));
		return allRecipes;
	}
	
	/**
	 * Check
	 * @throws IOException
	 */
	@Test
	public void checkIngredients() throws IOException {
		
		HashSet<String[]> allowedRecipes = new HashSet<String[]>(); 
		
		int rowLength = allRecipes.length;
		int colLength = allRecipes[0].length;
		int k=0;
		for(int row=0; row<rowLength; row++) {
			for(int col=0; col<colLength; col++) {
				
				if(col==4) {
					//System.out.println("Here:"+allRecipes[row][col]);
					String[] ingredients = String.valueOf(allRecipes[row][col]).split(",") ;
					
					boolean flag = eliminateRecipes(ingredients);
					if(flag==true) {
						//System.out.println("Allowed :::"+Arrays.toString(allRecipes[row]));
						allowedRecipes.add(allRecipes[row]);
						k++;
						
					}
				}
			}
		}
		//Write to excel
		Excel.writeToExcel(allowedRecipes);
		
		
		/*for(String[] i : allowedRecipes) {
			for(String j: i) {
				System.out.println(j);
			}
		}*/
		
		//System.out.println(allowedRecipes);
		
		
	}
	
	/**
	 * Eliminate recipes based on eliminate ingredients
	 * @param ingredients
	 * @return boolean
	 */
	private static boolean eliminateRecipes(String[] ingredients) {
		ArrayList<String> eliminateList = IngredientsList.eliminateHyperthyroid();
		//System.out.println(Arrays.toString(ingredients));
		for(int i=0;i<ingredients.length;i++) {
			for(int j=0;j<eliminateList.size();j++) {
				//System.out.println("Here:"+ingredients[i]+" and  "+eliminateList.get(j));
				if(ingredients[i].indexOf(eliminateList.get(j)) != -1) {
					return false;
				}
			}
		}
		return true;
	}
}
