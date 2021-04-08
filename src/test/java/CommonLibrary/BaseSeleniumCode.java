//This will be called in the testPackage

package commonLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class BaseSeleniumCode {
	public static WebDriver driver;

	public static WebDriver initializeChromeWebDriver(ExtentTest launchBrowserTest) {
		System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver_win32/chromedriver.exe");
		driver= new ChromeDriver();
		launchBrowserTest.createNode("Verify launch of Browser").log(Status.PASS, "Browser launched sucessfully");
		return driver;
	}
	
	public static WebDriver initializeIEDriver(ExtentTest launchBrowserTest) {
		System.setProperty("webdriver.ie.driver", "./WebDriver/IEDriverServer_Win32_3.150.1/IEDriverServer.exe");
		driver= new InternetExplorerDriver();
		launchBrowserTest.createNode("Verify launch of Browser").log(Status.PASS, "Browser launched sucessfully");
		return driver;
	}
	
	public static WebDriver initializeFirefoxDriver(ExtentTest launchBrowserTest) {
		System.setProperty("webdriver.firefox.driver", "./WebDriver/geckodriver-v0.29.0-win64/geckodriver.exe");
		driver= new InternetExplorerDriver();
		launchBrowserTest.createNode("Verify launch of Browser").log(Status.PASS, "Browser launched sucessfully");
		return driver;
	}

	public static void launchApp(String url, ExtentTest launchApp) {
		driver.manage().deleteAllCookies();
		driver.get(url);
		launchApp.createNode("Verify that Seller Portal gets launched").log(Status.PASS,  "Seller Portal launched successfully");
		driver.manage().window().maximize();
	}

	public static void readTestData() {

	}
}
