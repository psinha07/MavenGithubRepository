package testPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import commonLibrary.BaseSeleniumCode;
import commonLibrary.ExtentReportClass;

public class TestSuite_One {

	private static ExtentReports extentReport;
	ExtentTest launchBrowser, launchApp;
	
	@BeforeTest
	public void callExtent() {
		extentReport= ExtentReportClass.setUpExtentReport();
	}
	
	@Test(priority=1)
	public void testCaseDriver() {
		launchBrowser= extentReport.createTest("Launch Browser");
		WebDriver driver= BaseSeleniumCode.initializeChromeWebDriver();
		String text = driver.getPageSource();
		System.out.println("Code Test: "+text);
		BaseSeleniumCode.launchBrowser("https://qa.beepnbuy.com/beepnbuy/");
	}
}
