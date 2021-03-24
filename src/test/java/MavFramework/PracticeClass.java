package MavFramework;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CommonLibrary.ExtentReportClass;


public class PracticeClass {
	private static ExtentReports extentReport;
	ExtentTest launchBrowser, launchApp;

	@BeforeTest
	public void callExtent() {
		extentReport= ExtentReportClass.setUpExtentReport();
	}

	@Test(priority=1)
	public void launchBrowser() {
		String fname= "Pankaj";
		String lname="Sinha";

		launchBrowser = extentReport.createTest("Launch Browser Test");
		try {
			//	Assert.assertEquals("Sinha", "Sinha");
			if (fname.equalsIgnoreCase(lname)) {
				launchBrowser.createNode("Verify that browser gets launched successfully").log(Status.PASS, "Browser has been launched successfully");
			}
			else {
				launchBrowser.createNode("Verify that browser gets launched successfully").log(Status.FAIL, "Browser failed to launch successfully");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Test(priority=2)
	public void launchApplication() {
		launchApp = extentReport.createTest("Launch Application Test");
		try {
			Assert.assertEquals("Sinha", "Sinha");
			launchApp.createNode("verify that application gest launched successfully").log(Status.PASS, "Application has been launched successfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@AfterTest
	public static void closeReport() {
		extentReport = ExtentReportClass.flushExtentReport(); }

}
