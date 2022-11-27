package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.DashboardPage;

public class DashboardTests extends TestBase {
	
	Logger log = Logger.getLogger(DashboardTests.class);
	
	// @Parameters({"browserName","env"})
	@Test
	public void verifyNoOfWidgetsOnDashboard() {
		DashboardPage dashboardPage = DashboardPage.getObject();
		log.info("Verify number of widgets present on dashboard page.");
		log.info("Size is " + dashboardPage.getCountOfWidgets());
		int totalWidgets = dashboardPage.getCountOfWidgets();
		Assert.assertEquals(totalWidgets, 9,
				"Widgets count are not matching. Expected was 9 but actual is " + totalWidgets);

		log.info("Verifying widgets label displaying on dashboard page");
		ArrayList<String> expectedWidgetsList = new ArrayList<String>(
				Arrays.asList("Quick Access", "Buzz Latest Posts", "My Actions", "Latest Documents", "Latest News",
						"Employees on Leave Today", "Time At Work", "Headcount by Location", "COVID-19 Report"));

		log.info("Getting actual widget list");
		List<String> ActualWidgetsList = dashboardPage.geAlltWidgetsTitle();

		log.info("Verify actual titles are matching with expected title list");
		Assert.assertEquals(ActualWidgetsList, expectedWidgetsList);
	}

}
