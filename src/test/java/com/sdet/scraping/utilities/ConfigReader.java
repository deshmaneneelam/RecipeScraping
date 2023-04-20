package com.sdet.scraping.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {

	Properties property;
	
	/**
	 * call loadConfig while instantiate 
	 * 
	 */
	public ConfigReader() {
		try {
			loadConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Load properties 
	 * @throws Exception
	 */
	public void loadConfig() throws Exception {
		
		try {
			property = new Properties();
			
			String filePath = System.getProperty("user.dir") + "/src/test/resources/Config/config.properties";
			FileInputStream FP = new FileInputStream(filePath);
			property.load(FP);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	/**
	 * get url from properties
	 * @return
	 */
	public String getUrl() {	
		return property.getProperty("u");
	}
	
}
