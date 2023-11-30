package com.selenium.utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Utility {
	
	public static Object[][] readDataFromExcelFile(String sheetName) {
		XSSFWorkbook wb = null;
		String path = "D:\\java\\MavenAutoProject\\src\\main\\java\\com\\selenium\\testdata\\ExcelTestData.xlsx";
		try {
		FileInputStream fis = new FileInputStream(path);
		 wb = new XSSFWorkbook(fis);
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(1).getLastCellNum();

		Object[][] data = new Object[rowCount][colCount];

		for (int i = 0; i < rowCount; i++) {
			XSSFRow row = sheet.getRow(i+1);
			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);
				switch (cell.getCellType()) {
				case STRING:
					System.out.println(cell.getStringCellValue());
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					System.out.println(cell.getNumericCellValue());
					data[i][j] = cell.getNumericCellValue();
					break;
				}
			}
		}
		return data;
	}
}
