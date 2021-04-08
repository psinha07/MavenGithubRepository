package testPackage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import commonLibrary.BaseSeleniumCode;
import commonLibrary.ExtentReportClass;
import testPage.DashboardPage;
import testPage.LoginPage;

public class TestSuite_One {

	public static ExtentReports extentReport= null;
	ExtentTest launchBrowserTest, launchAppTest, loginTest, logOutTest;

	public static WebDriver driver;

	public String browsername = "Chrome";
	public String url="https://qa.beepnbuy.com/beepnbuy/";
	String uname= "ramdev@yopmail.com";
	String upassword="Test@123";

	@BeforeTest
	public void initializeTestEnvironment() {
		extentReport= ExtentReportClass.setUpExtentReport();
		if (browsername.equalsIgnoreCase("Chrome")) {
			launchBrowserTest= extentReport.createTest("Launch Browser");
			driver= BaseSeleniumCode.initializeChromeWebDriver(launchBrowserTest);
			launchAppTest=extentReport.createTest("Test Application Launch");
			BaseSeleniumCode.launchApp(url, launchAppTest);
		}
		else if (browsername.equalsIgnoreCase("IE")) {
			launchBrowserTest= extentReport.createTest("Launch Browser");
			driver= BaseSeleniumCode.initializeIEDriver(launchBrowserTest);
			launchAppTest=extentReport.createTest("Test Application Launch");
			BaseSeleniumCode.launchApp(url, launchAppTest);
		}
		else if (browsername.equalsIgnoreCase("Firefox")) {
			launchBrowserTest= extentReport.createTest("Launch Browser");
			driver= BaseSeleniumCode.initializeFirefoxDriver(launchBrowserTest);
			launchAppTest=extentReport.createTest("Test Application Launch");
			BaseSeleniumCode.launchApp(url, launchAppTest);
		}
	}

	@Test(priority=1, enabled= true)
	public void validLoginTest() {				
		loginTest=extentReport.createTest("Test Login functionality");
		LoginPage.validLogin(driver, uname, upassword, extentReport, loginTest);
		/* DashboardPage.logOut(driver, extentReport, logOutTest); */
	}
	
	@Test(priority=2, enabled= false)
	public void invalidLoginTest() {				
		LoginPage.invalidLogin(driver, uname, "sinha", extentReport, loginTest);
	}
	
	@Test(priority=3, enabled= true)
	public void logOutTest() {
		logOutTest= extentReport.createTest("Test LogOut functionality");
		DashboardPage.logOut(driver, extentReport, logOutTest);
	}
	
	@AfterTest
	public static void closeReport() {
		extentReport = ExtentReportClass.flushExtentReport();
	}
}


