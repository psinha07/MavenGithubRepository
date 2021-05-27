package testPackage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testNGPracticePackage.IOPracticeTestNG;

public class PracticeTestSet{

	public static WebDriver driver= null;


	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test
	public static void login(String username, String password, String appUrl) throws InterruptedException {
		launchBrowser();
		driver.get(appUrl);
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		driver.findElement(By.id("email")).sendKeys(username);
		Thread.sleep(3000);
		driver.findElement(By.id("passwd")).sendKeys(password);
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.close();
	}

	@AfterTest
	public void clear() {
		driver.quit();
	}
}
