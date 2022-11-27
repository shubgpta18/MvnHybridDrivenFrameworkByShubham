package testScripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import constants.ConstantValues;

public class AutoComplete {
	
	@Test
	public void autoCompletePractice() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-notifications");
		System.setProperty(ConstantValues.chromeDriverKey, ConstantValues.chromeDeiverPath);
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.google.com/");
		
		driver.findElement(By.xpath("//input[@class='gLFyf']")).sendKeys("Tech");
		
		List<WebElement> suggestions= driver.findElements(By.xpath("//ul[@role='listbox']/li//div[@class='pcTkSc']"));
		
		for(WebElement ele:suggestions) {
			if(ele.getText().equalsIgnoreCase("technology")) {
				ele.click();
			}
		}
	}

}
