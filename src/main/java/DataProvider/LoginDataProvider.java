package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utility.ExcelOperations;

public class LoginDataProvider extends ExcelOperations {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		Object[][] data;
		String fileName = ".//testData//loginData.xlsx";

		try {
			data = ExcelOperations.readExcelData(fileName, "Data");
		} catch (IOException e) {
			data = ExcelOperations.readExcelData(".//testData//loginData.xlsx", "Data");
		}
		return data;
	}
	
	@DataProvider(name = "LoginWithIncorrectData")
	public Object[][] getIncorrectLoginData() throws IOException {
		Object[][] data;
		String fileName = ".//testData//loginData.xlsx";

		try {
			data = ExcelOperations.readExcelData(fileName, "IncorrectLogin");
		} catch (IOException e) {
			data = ExcelOperations.readExcelData(".//testData//loginData.xlsx", "IncorrectLogin");
		}
		return data;
	}
}
