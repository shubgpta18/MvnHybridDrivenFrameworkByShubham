package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class LoginPage extends PredefinedActions {

	@FindBy(xpath = "//input[@name='txtUsername']")
	private WebElement userNameElement;

	@FindBy(xpath = "//input[@name='txtPassword']")
	private WebElement passwordElement;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitElement;

	@FindBy(xpath = "//div[@class='organization-logo shadow']/img")
	private WebElement logoElement;

	@FindBy(css = "dashboardCard-title-for-card")
	private WebElement retryPageElement;

	@FindBy(id = "txtUsername-error")
	private WebElement userNameErrorElement;
	
	@FindBy(id = "txtPassword-error")
	private WebElement passwordErrorElement;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void login(String userName, String password) {
		enterUsername(userName);
		enterPassword(password);
		clickOnSubmitBtn();

	}

	public void enterUsername(String username) {
		setText(userNameElement, username);

	}

	public void enterPassword(String password) {
		setText(passwordElement, password);
	}

	public void clickOnSubmitBtn() {
		clickOnElement(submitElement);
	}

	public boolean isLogoPresent() {
		return elementIsDisplayed(logoElement);
	}

	public String isUserOnRetryPage() {
		return getcurrentURL();
	}
	
	public String getEmptyUsernameErrorMessage() {
		return  getParticularText(userNameErrorElement);
	}
	
	public String getEmptyPasswordErrorMessage() {
		return  getParticularText(passwordErrorElement);
	}
}
