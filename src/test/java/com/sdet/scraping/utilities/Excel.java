package com.sdet.scraping.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	
	/**
	 * Extract data from excel
	 * @return
	 * @throws IOException
	 */
	public static String[][] ExcelUtil() throws IOException {
		
		DataFormatter dataFormatter = new DataFormatter();
		String ExcelPath = System.getProperty("user.dir")+"./src/test/resources/Data/FinalRecipeScrapeData.xlsx";

		FileInputStream inputFile = new FileInputStream(ExcelPath);		
		XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
		XSSFSheet sheet = workbook.getSheetAt(0);
				
		String[][] data = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) {				
				data[i][k] = dataFormatter.formatCellValue(sheet.getRow(i+1).getCell(k));
			}
		}
		
		workbook.close();
		return data;
	}
	
	private static XSSFWorkbook createSheet(String fileName, String sheetName) {
		
		File file = null;		
		XSSFWorkbook workbook = null;
		
		try {
		
			//XSSFSheet sheet = null;
			file = new File(Utils.filePath()+fileName);
			//System.out.println(file.exists());
			if (file.exists()) {
				workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(file));
			} else {
				workbook = new XSSFWorkbook();
				System.out.println("hhhh:"+workbook);
			}
			
			//System.out.println(sheetName);
			XSSFSheet spreadsheet = workbook.getSheet(sheetName);
			 if(spreadsheet==null) {
				spreadsheet =  workbook.createSheet(sheetName);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return workbook;
	}
	/**
	 * Write data to excel
	 * @param recipes
	 * @throws IOException
	 */
	public static void writeToExcel(HashSet<String[]> recipes, String fileName, String sheetName) throws IOException  {
		
		//System.out.println(fileName);
		
		int rowCount = 1;
		//XSSFWorkbook workbook = new XSSFWorkbook();
		//XSSFSheet sheet = workbook.createSheet(sheetName);
		
		//create workbook and sheet
		XSSFWorkbook workbook = createSheet(fileName, sheetName);
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		//Add header to excel
		createHeader(sheet);
		
		for(String[] recipe:recipes) {
			int columnCount = 0;
			Row row = sheet.createRow(rowCount);	
		
			for(String j:recipe) {					
				Cell cell = row.createCell(columnCount++);
				//System.out.println("Cell value::"+j);
				cell.setCellValue((String) j); 
			}
			rowCount++;
		}
		
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(Utils.filePath()+fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
		
	}
	
	/**
	 * Create header
	 * @param sheet
	 */
	private static void createHeader(XSSFSheet sheet) {
		
		String[] header = {"Recipe ID","Recipe Name","Recipe Category","Food Category","Ingredients","Preparation Time","Cooking Time","Preparation Method","Nutrient values","Targeted morbid conditions","Recipe URL","To Add"};
		
		int columnCount = 0;
		Row row = sheet.createRow(0);	
		
		for(String heading:header) {
			sheet.setColumnWidth(columnCount, 25*256);
			row.createCell(columnCount++).setCellValue((String) heading); 
		}
		
	}
	
	
	public static void appendToExcel(ArrayList<String> recipe,  String fileName, String sheetName) throws IOException {
		
		
		XSSFWorkbook workbook = createSheet(fileName, sheetName);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		XSSFCellStyle cs=workbook.createCellStyle();
		 cs.setWrapText(true);
		cs.setVerticalAlignment(VerticalAlignment.TOP);
		createHeader(sheet);
		
		
		int lastRow = sheet.getLastRowNum();
		
		//System.out.println("last row ::"+ lastRow);
		
		int rowCount=lastRow;
		
			int columnCount = 0;
			Row row = sheet.createRow(++rowCount);	
			row.setHeightInPoints(100);
		
			for(String j:recipe) {		
				
				Cell cell = row.createCell(columnCount++);
				
				//System.out.println("Cell value::"+j);
				cell.setCellValue((String) j);
				cell.setCellStyle(cs);
				
				
			}
			//rowCount++;
		
		
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(Utils.filePath()+fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
		
		
	}	
}
