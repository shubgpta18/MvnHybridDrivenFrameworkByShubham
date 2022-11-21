package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelOperations {

	public static  Object[][] readExcelData(String inputFile, String sheetName) throws IOException {
		File file = new File(inputFile); // Open File
		FileInputStream inputStream = new FileInputStream(file); // Giving read access to file
		Workbook wb = new XSSFWorkbook(inputStream); // Open workbook as excel
		
		Sheet sheet = wb.getSheet(sheetName); // Go to data sheet
		int totalRows = sheet.getLastRowNum(); // get total rows count
		int totalColumn = sheet.getRow(0).getLastCellNum(); // get total number of columns

		Object[][] data = new Object[totalRows][totalColumn]; // created a 2d array

		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalColumn; colIndex++) {
				Cell cell = sheet.getRow(rowIndex+1).getCell(colIndex); // Storing the data in cell
				if (cell.getCellType() == CellType.STRING) { // Checking if data in cell type is String
					data[rowIndex][colIndex] = cell.getStringCellValue(); // Storing the cell data in row and index
				} else if (cell.getCellType() == CellType.BOOLEAN) { // Checking if data in cell type is Boolean
					data[rowIndex][colIndex] = cell.getBooleanCellValue(); // Storing the cell data in row and index
				} else if (cell.getCellType() == CellType.NUMERIC) { // Checking if data in cell type is String
					if (DateUtil.isCellDateFormatted(cell)) { // checking if data is in date format
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						data[rowIndex][colIndex] = cell.getDateCellValue(); // Storing the cell data in row and index
					} else {
						data[rowIndex][colIndex] = cell.getNumericCellValue(); // Storing the cell data in row and index
					}
				}

			}
		}
		return data;
	}
}
