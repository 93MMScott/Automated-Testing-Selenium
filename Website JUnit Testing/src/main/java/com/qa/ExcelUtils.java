package com.qa;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet excelSheet;
	private static XSSFWorkbook excelWorkbook;
	private static XSSFRow row;
	private static XSSFCell cell;
	
	// set excel file
	public static void setExcelSheet(String Path, int sheetIndex)
	{
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			excelWorkbook = new XSSFWorkbook(ExcelFile);
			excelSheet = excelWorkbook.getSheetAt(sheetIndex);
		}
		catch(Exception e) {	e.printStackTrace(); }
	}
	
	// get current sheet
	public static XSSFSheet getExcelSheet()
	{
		return excelSheet;
	}
	
	// get cell data
	public static String getCellData(int row, int column)
	{
		try {
			cell = excelSheet.getRow(row).getCell(column);
			String CellData = cell.getStringCellValue();

			return CellData;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	// set cell data
	public static void setCellData(String result, int theRow, int theColumn) {
		try {
			row = excelSheet.getRow(theRow);
			cell = row.getCell(theColumn, MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if(cell == null) {
				cell = row.createCell(theColumn);
				cell.setCellValue(result);
			}
			else {
				cell.setCellValue(result);
			}
			
			FileOutputStream fileOut = new FileOutputStream(Constants.pathTestData + Constants.fileTestData);
			excelWorkbook.write(fileOut);
			fileOut.close();
			fileOut.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
