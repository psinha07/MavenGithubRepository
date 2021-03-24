//This will be called in the testPackage

package commonLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseSeleniumCode {
	protected static WebDriver driver;

	public static WebDriver initializeChromeWebDriver() {
		System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	public static void launchBrowser(String url) {
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
	}

	public static void readTestData() {

	}
}
