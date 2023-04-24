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
	
	public static ArrayList<String> eliminateDiabetes() {
		String[] eliminate = {"processed grain","cream of rice","rice flour","rice rava","corn","sugar",
				"white rice","white bread","pasta","sweetened beverage","soda","flavoured water","gatorade",
				"fruit juice","apple juice","orange juice","pomegranate juice","flavoured curd","flavoured yogurt",
				"breakfast cereal","corn flake","puffed rice","bran flake","instant oatmeal","honey",
				"maple syrup","jaggery","sweet","candie","candy","chocolate","all purpose flour",
				"refined flour","multi purpose flour","alcoholic beverage","processed meat","bacon","sausage",
				"hot dos","deli meat","chicken nugget","chciken pattie","chciken patty","jam","jelly",
				"pickled food","mango pickle","cucumber pickle","tomatoes pickle","tomatoe pickle","chips",
				"mayonnaise","palmolein oil","baked food","doughnut","cake","pastries","pastry","cookies",
				"cookey","biscuit","croissant","sweetened tea","sweetened coffee","packaged snack",
				"Packed snack","banana","melon","soft drink","dairy Milk","butter","cheese",
				"cinnamon Toast crunch","granola",
				"canned fruit","Canned vegetable","canned","powdered milk"
				};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
	
	public static ArrayList<String> toAddDiabetes() {
			String[] toadd = {"broccoli","pumpkin","pumpkin seed","chia sedd","chia seed","flaxseed",
					"flax seed","apple","nuts","lady finger","okra","beans","raspberr","rasp berr","strawberr",
					"straw berr","blueberr","blue berr","blackberr","black berr","egg","yogurt","bitter gaurd",
					"rolled oat","steel cut oat","chicken","fish","quinoa","mushroom"
					};
			ArrayList<String> toaddList = new ArrayList<String>(Arrays.asList(toadd));
			return toaddList;
		}
	
	public static ArrayList<String> eliminateHyperthyroid() {
		String[] eliminate = {"tofu","edamame","tempeh","cauliflower","cabbage","broccoli","kale","spinach","sweet potatoes","straberries","pine nuts","penuts","peaches","green tea","coffee","alcohol","soy milk","white bread","cake","pastries","fried food","sugar","gluten","soda","caffeine","noodle","soup","salad dressing","sauce","candies"};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
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
