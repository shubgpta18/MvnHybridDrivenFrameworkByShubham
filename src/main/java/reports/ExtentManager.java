package reports;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.PredefinedActions;

public class ExtentManager {
	static ExtentReports extentReports;
	static ExtentTest extentTest;

	public static void initExtentReport() {
		extentReports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
		extentReports.attachReporter(spark);

		spark.config().setTheme(Theme.DARK);
		spark.config().setTimeStampFormat("MMM,dd yyyy  HH:mm:ss a");
		spark.config().setReportName("Shubham");

		extentReports.setSystemInfo("Browser", "Chrome");
		extentReports.setSystemInfo("OS", "Window 10");
		extentReports.setSystemInfo("Env", "QA");

	}

	public static void createTest(String testName) {
		extentTest=extentReports.createTest(testName).createNode(testName);
	}
	public static void log(String msg) {
		extentTest.info(msg);
	}

	public static void pass() {
		extentTest.log(Status.PASS, "Test Case Executed Scuesssfully");
	}

	public static void fail(String msg) {
		extentTest.log(Status.FAIL, msg);
		extentTest.log(Status.FAIL,
				MediaEntityBuilder.createScreenCaptureFromBase64String(PredefinedActions.takeScreenshot()).build());
	}

	public static void skip(String msg) {
		extentTest.log(Status.SKIP, msg);
		extentTest.log(Status.SKIP,
				MediaEntityBuilder.createScreenCaptureFromBase64String(PredefinedActions.takeScreenshot()).build());
	}

	public static void warn(String msg) {
		extentTest.warning(msg);
	}

	
	public static void flushReport() {
		extentReports.flush();

	}


}
