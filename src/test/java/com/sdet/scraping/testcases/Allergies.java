package com.sdet.scraping.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.testng.annotations.Test;

import com.sdet.scraping.utilities.Excel;
import com.sdet.scraping.utilities.Utils;

public class Allergies extends Utils{

	@Test
	public void checkIngredients() throws IOException {
		
		HashSet<String[]> allowedRecipes = new HashSet<String[]>(); 
		
		String[][] allRecipes = Utils.getAllRecipes();
		int rowLength = allRecipes.length;
		int colLength = allRecipes[0].length;
		int k=0;
		for(int row=0; row<rowLength; row++) {
			for(int col=0; col<colLength; col++) {
				
				if(col==4) {
					//System.out.println("Here:"+allRecipes[row][col]);
					String[] ingredients = String.valueOf(allRecipes[row][col]).split(",") ;
					
					boolean flag = eliminateRecipes(ingredients, "egg");
					if(flag==true) {
						//System.out.println("Allowed :::"+Arrays.toString(allRecipes[row]));
						allowedRecipes.add(allRecipes[row]);
						k++;
						 
					}
				}
			}
		}
		//Write to excel
		Excel.writeToExcel(allowedRecipes,"Allergies.xlsx", "Egg");
		
		
		/*for(String[] i : allowedRecipes) {
			for(String j: i) {
				System.out.println(j);
			}
		}*/
		
		//System.out.println(allowedRecipes);
		
		
	}
	
	@Test
	public void checkMilkIngredients() throws IOException {
		
		HashSet<String[]> allowedRecipes = new HashSet<String[]>(); 
		
		String[][] allRecipes = Utils.getAllRecipes();
		int rowLength = allRecipes.length;
		int colLength = allRecipes[0].length;
		int k=0;
		for(int row=0; row<rowLength; row++) {
			for(int col=0; col<colLength; col++) {
				
				if(col==4) {
					//System.out.println("Here:"+allRecipes[row][col]);
					String[] ingredients = String.valueOf(allRecipes[row][col]).split(",") ;
					
					boolean flag = eliminateRecipes(ingredients, "Milk");
					if(flag==true) {
						//System.out.println("Allowed :::"+Arrays.toString(allRecipes[row]));
						allowedRecipes.add(allRecipes[row]);
						k++;
						 
					}
				}
			}
		}
		//Write to excel
		Excel.writeToExcel(allowedRecipes,"Allergies.xlsx", "Milk");
	}
	
	public static boolean eliminateRecipes(String[] ingredients, String allergyKey) {
		
		//System.out.println(Arrays.toString(ingredients));
		for(int i=0;i<ingredients.length;i++) {
				if(ingredients[i].indexOf(allergyKey) != -1) {
					return false;
				}
			
		}
		return true;
	}
}
