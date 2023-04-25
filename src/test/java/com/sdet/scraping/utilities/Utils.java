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
	
	public static ArrayList<String> eliminatePCOS() {
		String[] eliminate = {"cakes","Pastries","puff","pies","tarts","sugar","brown sugar","coconut sugar","castor sugar","jaggery","honey","maida","rava","wheat flou","plain flour","deep fry","all-purpose flour","sooji","semolina","White bread","fried food","fried","pizza","burger","carbonated beverages","soft drinks","beef","milk","butter","cheese","yogurt","curd","cream","whey","tofu","soy milk","soy sauce","sweets","sweet","ice creams","soda","juices","red meat","processed meat","dairy","soy products","Gluten","pasta","white rice","doughnuts","eggs","fries","coffee","vegetable oil","soybean oil"," canola oil","rapeseed oil","sunflower oil","safflower oil"};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
	
	public static ArrayList<String> AddtoPCOS() {
		String[] AddTo = {"High fiber fruits","apple","banana","raspberries","pear","orange","strawberries","broccoli","green peas","sweet corn","cauliflowe","carrot", "vegetables"};
		ArrayList<String> AddToList = new ArrayList<String>(Arrays.asList(AddTo));
		return AddToList;
	}
	 
	public static ArrayList<String> eliminateHypertension() {
		String[] eliminate = {"salty food","snack","chips","pretzels","crackers","caffeine","coffee","tea",
				"soft drink","alcohol","frozen food","meat","bacon","ham","pickle","processed","canned",
				"fried food","sauce","mayonnaise","bacon","sausage","deli meat","white rice","white bread"};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
	
	public static ArrayList<String> toAddHypertension() {
		String[] toadd = {"beetroot","blueberr","strawberr","potassium rich","banana","avocado","tomato",
				"sweet potato","mushroom","celery","kiwi","dark chocolate","watermelon","spinach","cabbage",
				"romaine lettuce","mustard greens","broccoli","argula","fennel","kale","garlic","pomegranate",
				"cinnamon","pistachio","chia seed","yogurt","curd","unsalted nut","chicken","fish","turkey","quinoa"};
		ArrayList<String> toaddList = new ArrayList<String>(Arrays.asList(toadd));
		return toaddList;
	}
	
	public static ArrayList<String> allergies() {
		return new ArrayList<String>(Arrays.asList("milk","egg","sesame","peanut","walnut","almond","hazelnut","pecan","cashew","pistachio","shell fish","seafood"));
		
	}
	
	public static String filePath() {
		return System.getProperty("user.dir")+"./src/test/resources/Data/";
	}
	
}
