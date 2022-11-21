package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import base.PredefinedActions;
import pages.LoginPage;
import utility.PropertyFileOperations;

public class TestBase {

	@BeforeMethod
	public void setUp() throws IOException {
		System.out.println("Launch Browser and load URL");
		PropertyFileOperations fileOperations = new PropertyFileOperations(".//src//main//resources//config//settings.properties");
		String url = fileOperations.getValue("url");
		PredefinedActions.start(url);

		LoginPage login = new LoginPage();
		login.login(fileOperations.getValue("username"), fileOperations.getValue("password"));
		String actualTitle = "Employee Management";
		String expectedTitle = PredefinedActions.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);

	}

	@AfterMethod
	public void closeBrowser(ITestResult result) {
		int status = result.getStatus();
		if (ITestResult.FAILURE == status) {
			PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
		} else if (ITestResult.SUCCESS == status) {
			PredefinedActions.takeScreenshot(result.getMethod().getMethodName());

		}
		PredefinedActions.closeBrowser();
	}
}
