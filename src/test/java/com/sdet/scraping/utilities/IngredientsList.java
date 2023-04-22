package com.sdet.scraping.utilities;

import java.util.ArrayList;
import java.util.Arrays;

public class IngredientsList {

	public static ArrayList<String> eliminateHyperthyroid() {
		String[] eliminate = {"tofu","edamame","tempeh","cauliflower","cabbage","broccoli","kale","spinach","sweet potatoes","straberries","pine nuts","penuts","peaches","green tea","coffee","alcohol","soy milk","white bread","cake","pastries","fried food","sugar","gluten","soda","caffeine","noodle","soup","salad dressing","sauce","candies"};
		ArrayList<String> eliminateList = new ArrayList<String>(Arrays.asList(eliminate));
		return eliminateList;
	}
}

