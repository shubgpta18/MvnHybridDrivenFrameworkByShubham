package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.PredefinedActions;

public class DashboardPage extends PredefinedActions {
	private static DashboardPage dashboardPage;
	@FindBy(xpath = "//div[contains(@class,'oxd dashboard-widget-shell') and not(contains(@class,'ng-hide'))]//div[@class='widget-header']//span//following-sibling::span")
	private List<WebElement> dashboardWidgetsHeader;

	@FindBy(xpath = "//span[text()='Quick Access']")
	private WebElement firstDashboardWidget;

	private DashboardPage() {

	}

	public static DashboardPage getObject() {
		if (dashboardPage == null) {
			dashboardPage = new DashboardPage();
		}
		PageFactory.initElements(driver, dashboardPage);
		return dashboardPage;
	}

	public int getCountOfWidgets() {
		waitTillDashboardgetsLoaded();
		return dashboardWidgetsHeader.size();

	}

	public void waitTillDashboardgetsLoaded() {
		waitForVisibilityOfElement(firstDashboardWidget);
	}

	public List<String> geAlltWidgetsTitle() {

		return getListOfWebElementText(dashboardWidgetsHeader);

	}
}
