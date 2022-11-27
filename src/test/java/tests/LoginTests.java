package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import DataProvider.LoginDataProvider;
import base.PredefinedActions;
import pages.LoginPage;

public class LoginTests {
	WebDriver driver;
	Logger log = Logger.getLogger(LoginTests.class);

	@Test(dataProvider = "LoginData", dataProviderClass = LoginDataProvider.class)
	public void TC1_VerifyLoginFunctionality(String url, String uname, String password, boolean isLoginSuccessful) {
		log.info("Step 1: Launch your Browser and load URL");
		PredefinedActions.start(url);
		LoginPage login = new LoginPage();

		log.info("Step 2: Verify Logo displayed on Login Page");
		Assert.assertEquals(login.isLogoPresent(), true, "Logo is not present");
		log.info("Logo is present");

		log.info("Step 3: Login with given credential");
		login.login(uname, password);
		if (isLoginSuccessful) {

			log.info("Step 4:User should navigate to home page");
			String expectedTitle = "Employee Management";
			String ActualTitle = login.getPageTitle();
			Assert.assertEquals(expectedTitle, ActualTitle,
					"Expected Title is " + expectedTitle + "where as Actual Title is " + ActualTitle);
			log.info("Login is successful and user is navigated to home page");
		} else {
			log.info("User logged in with invalid credentials");
			log.info("Step4: Verify if retry Login page is present");
			String ExpectedUrlContent = "retryLogin";
			String ActualUrl = login.isUserOnRetryPage();
			log.info(ActualUrl);
			Assert.assertTrue(ActualUrl.endsWith(ExpectedUrlContent));
			log.info("Retry page is loaded");
		}
	}

	@Test(dataProvider = "LoginWithIncorrectData", dataProviderClass = LoginDataProvider.class)
	public void TC1_1_VerifyErrorMessageWithoutEnteringUserNamePassword(String url, String uname, String password) {
		log.info("Step 1: Launch your Browser and load URL");
		PredefinedActions.start(url);
		LoginPage login = new LoginPage();
		log.info("Verify Error Message for blank userName and Password");
		login.clickOnSubmitBtn();

		String ExpectedUserNameError = "Username cannot be empty";
		String ActualUserNameError = login.getEmptyUsernameErrorMessage();
		log.info(ActualUserNameError);
		Assert.assertEquals(ActualUserNameError, ExpectedUserNameError,
				"Actual error message is " + ActualUserNameError + " where as expected is " + ExpectedUserNameError);

		String ExpectedPasswordError = "Password cannot be empty";
		String ActualPasswordError = login.getEmptyPasswordErrorMessage();
		log.info(ActualPasswordError);
		Assert.assertEquals(ActualPasswordError, ExpectedPasswordError,
				"Actual error message is " + ActualPasswordError + " where as expected is " + ExpectedPasswordError);
	}

	@AfterMethod
	public void closeBrowser() {
		log.info("Closing the browser");
		PredefinedActions.closeBrowser();
	}
}