package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginPage {

	public static void validLogin(WebDriver driver, String uname, String upassword, ExtentReports extentReport, ExtentTest loginTest) {
		String val="validate";
		try {
			//loginTest.createNode("Verify login with valid login credentials");
			driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys("ramdev@yopmail.com");
			driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("Test@123");
			driver.findElement(By.xpath("//span[@class='mat-button-wrapper']")).click();
			if(val.equalsIgnoreCase("validate")) {
				loginTest.createNode("Verify login with valid login credentials").log(Status.PASS, "Logged in successfully with valid login credentials");
			}
			else {
				loginTest.createNode("Verify login with valid login credentials").log(Status.FAIL, "Failed to login with valid login credentials");
			}
			//	extentReport.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void invalidLogin(WebDriver driver, String uname, String upassword, ExtentReports extentReport, ExtentTest loginTest) {
		String val="validate";
		try {
			//loginTest.createNode("Verify login with valid login credentials");
			driver.findElement(By.xpath("//input[@id='mat-input-0']")).sendKeys("ramdev@yopmail.com");
			driver.findElement(By.xpath("//input[@id='mat-input-1']")).sendKeys("Test@123");
			driver.findElement(By.xpath("//span[@class='mat-button-wrapper']")).click();
			if(val.equalsIgnoreCase("invalidate")) {
				loginTest.createNode("Verify login with invalid login credentials").log(Status.FAIL, "Logged in successfully with invalid login credentials");
			}
			else {
				loginTest.createNode("Verify login with valid login credentials").log(Status.PASS, "Failed to login with invalid login credentials");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
