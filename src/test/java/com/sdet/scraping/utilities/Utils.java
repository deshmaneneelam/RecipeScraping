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
				"fruit juice","apple juice","orange juice","pomegranate juice","margarine","peanut butter","ghee",
				"spread","frozen food","flavoured curd","flavoured yogurt","flake","puffed rice","instant oatmeal",
				"cereal","corn flake","puffed rice","bran flake","instant oatmeal","honey","cooked long grain",
				"maple syrup","jaggery","sweet","candie","candy","chocolate","all purpose flour","plain flour",
				"refined flour","multi purpose flour","alcoholic beverage","processed meat","bacon","sausage",
				"hot dog","deli meat","chicken nugget","chicken pattie","chicken patty","jam","jelly","maida",
				"pickled food","pickle","canned","chips","mayonnaise","palmolein oil","dried food","powdered milk",
				"baked food","doughnut","cake","pastries","pastry","cookie","cookey","biscuit","croissant","dates",
				"sweetened tea","sweetened coffee","packaged snack","Packed snack","soft drink","banana","melon",
				"dairy Milk","butter","cheese","cinnamon Toast crunch","granola","basmathi","basmati","rajma",
				"cooked long grain","steamed rice","chawal","raw rice","cooked rice","chinese rice","poha",
				"semolina","idli rawa","kabuli chana","chick pea","kabuli chana","maize flour","2 bread","honey",
				"pita bread","paneer","dabeli","milk","whole green gram","black eyed bean","chawli","cow pea",
				"lobhia","soyabean","soya bean","bengal gram","garbanzo","baked bean","infused water",
				"pineapple","peach","mango","pear","mixed fruit","mandarine orange","cherr","orange","grape",
				"brownie","dessert","jaggery","vanilla cream","khus syrup","sweet","gulkand","ice cream",
				"ice-cream"
				};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
	
	public static ArrayList<String> toAddDiabetes() {
			String[] toadd = {"broccoli","pumpkin","pumpkin seed","chia sedd","chia seed","flaxseed",
					"flax seed","apple","nuts","lady finger","okra","beans","cluster bean","gavarfali",
					"french bean","bean sprout","raspberr","rasp berr","strawberr","egg ","eggs",
					"straw berr","blueberr","blue berr","blackberr","black berr","yogurt","bitter gaurd",
					"rolled oat","steel cut oat","chicken","fish","quinoa","mushroom","badam","almond",
					"walnut","akhrot","cashew","mixed nut"
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
		String[] eliminate = {"salt","snack","chips","pretzels","crackers","caffeine","coffee","tea",
				"soft drink","flavoured drink","alcohol","frozen food","meat","bacon","ham","pickle",
				"processed","canned","fried food","sauce","mayonnaise","bacon","sausage","deli meat","white rice",
				"white bread","bread","basmati","ginger ale","long grained rice","deep fry","deep-fry","samosa",
				"beverage","frozen brownie","frozen muskmelon","sprite","frozen blueberr","liquor",
				"steamed rice","chawal","raw rice","cooked rice","chinese rice","poha","semolina",
				"ice cream","ice-cream","kulfi"
				};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
	
	public static ArrayList<String> toAddHypertension() {
		String[] toadd = {"beetroot","berr","blueberr","blue berr","strawberr","straw berr","potassium rich",
				"banana","avocado","tomato","sweet potato","mushroom","celery","kiwi","dark chocolate",
				"watermelon","spinach","cabbage","romaine lettuce","mustard greens","broccoli","argula",
				"fennel","kale","garlic","pomegranate","cinnamon","pistachio","chia seed","yogurt","curd",
				"unsalted nut","chicken","fish","turkey","quinoa","badam","almond","walnut","akhrot","cashew",
				"nuts","mixed nut","low-fat yogurt","low fat yogurt"};
		ArrayList<String> toaddList = new ArrayList<String>(Arrays.asList(toadd));
		return toaddList;
	}
	
	public static ArrayList<String> allergies() {
		return new ArrayList<String>(Arrays.asList("milk","egg","sesame","peanut","walnut","almond","hazelnut","pecan","cashew","pistachio","shell fish","seafood"));
		
	}
	
	public static String filePath() {
		return System.getProperty("user.dir")+"./src/test/resources/Data/";
	}
	
	public static String driverPath() {
		return System.getProperty("user.dir")+"/src/test/resources/Driver/";
	}
	
}
