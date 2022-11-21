package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.DashboardPage;

public class DashboardTests extends TestBase {

	@Test
	public void verifyNoOfWidgetsOnDashboard() {
		DashboardPage dashboardPage = DashboardPage.getObject();
		System.out.println("Verify number of widgets present on dashboard page.");
		System.out.println("Size is " + dashboardPage.getCountOfWidgets());
		int totalWidgets = dashboardPage.getCountOfWidgets();
		Assert.assertEquals(totalWidgets, 9,
				"Widgets count are not matching. Expected was 9 but actual is " + totalWidgets);

		System.out.println("Verifying widgets label displaying on dashboard page");
		ArrayList<String> expectedWidgetsList = new ArrayList<String>(
				Arrays.asList("Quick Access", "Buzz Latest Posts", "My Actions", "Latest Documents", "Latest News",
						"Employees on Leave Today", "Time At Work", "Headcount by Location", "COVID-19 Report"));

		System.out.println("Getting actual widget list");
		List<String> ActualWidgetsList = dashboardPage.geAlltWidgetsTitle();

		System.out.println("Verify actual titles are matching with expected title list");
		Assert.assertEquals(ActualWidgetsList, expectedWidgetsList);
	}

}
