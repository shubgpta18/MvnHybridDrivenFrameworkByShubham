package base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ConstantValues;

public class PredefinedActions {
	protected static WebDriver driver;
	protected static Actions action;
	protected static Select select;
	static WebDriverWait wait;

	protected PredefinedActions() {

	}
	 static Logger log =Logger.getLogger(PredefinedActions.class);

	public static void start(String url) {
		String browser = System.getProperty("browserName");
		// String env = System.getProperty("env");
       log.trace("browser is " + browser);

		switch (browser.toLowerCase()) {
		case "chrome": {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			System.setProperty(ConstantValues.chromeDriverKey, ConstantValues.chromeDeiverPath);
			driver = new ChromeDriver(options);
			break;
		}

		case "firefox": {
			System.setProperty(ConstantValues.chromeDriverKey, ConstantValues.chromeDeiverPath);
			driver = new FirefoxDriver();
			break;

		}

		case "safari": {
			System.setProperty(ConstantValues.chromeDriverKey, ConstantValues.chromeDeiverPath);
			driver = new SafariDriver();
			break;

		}

		case "ie": {
			System.setProperty(ConstantValues.chromeDriverKey, ConstantValues.chromeDeiverPath);
			driver = new InternetExplorerDriver();
			break;

		}

		default:
			break;
		}

		driver.manage().window().maximize();
		driver.get(url);

	}

	// method to scroll until element is visible
	protected void scrollTillElement(WebElement element) {
		if (!element.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView();", element);
		}
	}

	static public void uploadFile(String url) throws AWTException, InterruptedException {

		StringSelection ss = new StringSelection(url);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	protected void setText(WebElement e, String text) {
		scrollTillElement(e);
		if (e.isEnabled())
			e.sendKeys(text);
	}

	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebElement element = null;

		switch (locatorType.toLowerCase()) {
		case "id":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			} else
				element = driver.findElement(By.id(locatorValue));
			break;

		case "xpath":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			} else
				element = driver.findElement(By.xpath(locatorValue));
			break;

		case "cssselector":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			} else
				element = driver.findElement(By.cssSelector(locatorValue));
			break;

		case "name":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			} else
				element = driver.findElement(By.name(locatorValue));
			break;

		case "linktext":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			} else
				element = driver.findElement(By.linkText(locatorValue));
			break;

		case "partiallinktext":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			} else
				element = driver.findElement(By.partialLinkText(locatorValue));
			break;

		case "classname":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			} else
				element = driver.findElement(By.className(locatorValue));
			break;

		case "tagname":
			if (isWaitRequired) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			} else
				element = driver.findElement(By.tagName(locatorValue));
			break;
		}
		return element;
	}

	protected boolean waitForVisibilityOfElement(WebElement e) {
		wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	protected void clickOnElement(WebElement e, boolean isWaitRequired) {
		scrollTillElement(e);
		if (isWaitRequired) {
			wait.until(ExpectedConditions.elementToBeClickable(e));
			e.click();
		}
		e.click();
	}

	protected void clickOnElement(WebElement e) {
		log.trace("Scrolling utill element is visivle for clicking");
		scrollTillElement(e);
		e.click();
	}

	protected boolean elementIsDisplayed(WebElement e) {
		scrollTillElement(e);
		return e.isDisplayed();
	}

	protected List<String> getListOfWebElementText(List<WebElement> list) {
		List<String> listOfElementText = new ArrayList<String>();
		for (WebElement e : list) {
			listOfElementText.add(e.getText());
		}
		return listOfElementText;
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public String getcurrentURL() {
		return driver.getCurrentUrl();
	}

	public String getParticularText(WebElement e) {
		return e.getText();
	}

	public static void takeScreenshot(String testCaseName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcfile, new File("./failedTestCases/" + testCaseName + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	final static public void closeBrowser() {
		driver.close();
	}

	final static public void closeAllBrowsers() {
		driver.quit();
	}
}
