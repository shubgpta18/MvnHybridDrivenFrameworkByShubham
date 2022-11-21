package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import DataProvider.LoginDataProvider;
import base.PredefinedActions;
import pages.LoginPage;

public class LoginTests {
	WebDriver driver;

	@Test(dataProvider = "LoginData", dataProviderClass = LoginDataProvider.class)
	public void TC1_VerifyLoginFunctionality(String url, String uname, String password, boolean isLoginSuccessful) {
		System.out.println("Step 1: Launch your Browser and load URL");
		PredefinedActions.start(url);
		LoginPage login = new LoginPage();

		System.out.println("Step 2: Verify Logo displayed on Login Page");
		Assert.assertEquals(login.isLogoPresent(), true, "Logo is not present");
		System.out.println("Logo is present");

		System.out.println("Step 3: Login with given credential");
		login.login(uname, password);
		if (isLoginSuccessful) {

			System.out.println("Step 4:User should navigate to home page");
			String expectedTitle = "Employee Management";
			String ActualTitle = login.getPageTitle();
			Assert.assertEquals(expectedTitle, ActualTitle,
					"Expected Title is " + expectedTitle + "where as Actual Title is " + ActualTitle);
			System.out.println("Login is successful and user is navigated to home page");
		} else {
			System.out.println("User logged in with invalid credentials");
			System.out.println("Step4: Verify if retry Login page is present");
			String ExpectedUrlContent = "retryLogin";
			String ActualUrl = login.isUserOnRetryPage();
			System.out.println(ActualUrl);
			Assert.assertTrue(ActualUrl.endsWith(ExpectedUrlContent));
			System.out.println("Retry page is loaded");
		}
	}

	@Test(dataProvider = "LoginWithIncorrectData", dataProviderClass = LoginDataProvider.class)
	public void TC1_1_VerifyErrorMessageWithoutEnteringUserNamePassword(String url, String uname, String password) {
		System.out.println("Step 1: Launch your Browser and load URL");
		PredefinedActions.start(url);
		LoginPage login = new LoginPage();
		System.out.println("Verify Error Message for blank userName and Password");
		login.clickOnSubmitBtn();

		String ExpectedUserNameError = "Username cannot be empty";
		String ActualUserNameError = login.getEmptyUsernameErrorMessage();

		Assert.assertEquals(ActualUserNameError, ExpectedUserNameError,
				"Actual error message is " + ActualUserNameError + " where as expected is " + ExpectedUserNameError);

		String ExpectedPasswordError = "Password cannot be empty";
		String ActualPasswordError = login.getEmptyPasswordErrorMessage();

		Assert.assertEquals(ActualPasswordError, ExpectedPasswordError,
				"Actual error message is " + ActualPasswordError + " where as expected is " + ExpectedPasswordError);
	}

	@AfterMethod
	public void closeBrowser() {
		System.out.println("Closing the browser");
		PredefinedActions.closeBrowser();
	}
}