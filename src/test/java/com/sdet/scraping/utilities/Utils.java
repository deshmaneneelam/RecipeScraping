package com.sdet.scraping.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.BeforeClass;

public class Utils {

	@BeforeClass
	public static String[][] getAllRecipes() throws IOException {
		 String[][] allRecipes = Excel.ExcelUtil();
		//System.out.println(Arrays.toString(allRecipes));
		return allRecipes;
	}
	
	public static ArrayList<String> eliminateHyperthyroid() {
		String[] eliminate = {"tofu","edamame","tempeh","cauliflower","cabbage","broccoli","kale","spinach","sweet potatoes","straberries","pine nuts","penuts","peaches","green tea","coffee","alcohol","soy milk","white bread","cake","pastries","fried food","sugar","gluten","soda","caffeine","noodle","soup","salad dressing","sauce","candies"};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
	
	public static ArrayList<String> allergies() {
		return new ArrayList<String>(Arrays.asList("milk","egg","sesame","peanut","walnut","almond","hazelnut","pecan","cashew","pistachio","shell fish","seafood"));
		
	}
	
	public static String filePath() {
		return System.getProperty("user.dir")+"./src/test/resources/Data/";
	}
	
}
