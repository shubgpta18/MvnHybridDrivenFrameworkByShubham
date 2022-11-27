package DataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import constants.ConstantValues;
import utility.ExcelOperations;

public class LoginDataProvider extends ExcelOperations {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		Object[][] data;
		String fileName = ConstantValues.loginDataProvider;

		try {
			data = ExcelOperations.readExcelData(fileName, ConstantValues.loginDataSheet);
		} catch (IOException e) {
			data = ExcelOperations.readExcelData(".//src//test//resources//testdata//loginData.xlsx", "IncorrectLogin");
		}
		return data;
	}

	@DataProvider(name = "LoginWithIncorrectData")
	public Object[][] getIncorrectLoginData() throws IOException {
		Object[][] data;
		String fileName =  ConstantValues.loginDataProvider;;

		try {
			data = ExcelOperations.readExcelData(fileName, "IncorrectLogin");
		} catch (IOException e) {
			data = ExcelOperations.readExcelData( ConstantValues.loginDataProvider, "IncorrectLogin");
		}
		return data;
	}
}
