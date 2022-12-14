package tests;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import base.PredefinedActions;
import constants.ConstantValues;
import pages.LoginPage;
import reports.ExtentManager;
import utility.PropertyFileOperations;

public class TestBase extends ExtentManager {
	Logger log = Logger.getLogger(TestBase.class);

	@BeforeSuite
	public void beforeSuite() {
		ExtentManager.initExtentReport();
		PropertyConfigurator.configure(".//src//main//resources//config//log4j.properties");
	}

	@AfterSuite
	public void afterSuite() {
		ExtentManager.flushReport();
	}

	@BeforeMethod
	public void setUp(ITestResult result) throws IOException {
		ExtentManager.createTest(result.getMethod().getMethodName());

		String url = "";
		String env = System.getProperty("env");
		log.info("Launch Browser and load URL");
		PropertyFileOperations fileOperations = new PropertyFileOperations(ConstantValues.loginPropertyFile);

		switch (env.toLowerCase()) {
		case "qa": {
			url = fileOperations.getValue("QAurl");
			break;
		}
		case "stage": {
			url = fileOperations.getValue("Stageurl");
			break;
		}
		default:
			break;
		}
		log.info("Env is " + env + " and url is: " + url);
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
			ExtentManager.fail(result.getThrowable().getMessage());

			// PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
		} else if (ITestResult.SUCCESS == status) {
			// PredefinedActions.takeScreenshot(result.getMethod().getMethodName());
			ExtentManager.pass();
		} else if (ITestResult.SKIP == status) {
			ExtentManager.skip(result.getThrowable().getMessage());
		}
		PredefinedActions.closeBrowser();
	}
}
