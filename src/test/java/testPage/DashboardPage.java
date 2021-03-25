package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DashboardPage {

	public static void logOut(WebDriver driver, ExtentReports extentReort, ExtentTest logOutTest){
		try {
			driver.findElement(By.xpath("//img[@class='avatar']")).click();
			driver.findElement(By.xpath("//span[normalize-space()='Log out']")).click();
			driver.findElement(By.xpath("//span[normalize-space()='Yes']")).click();
			System.out.println(driver.findElement(By.xpath("//h4[normalize-space()='Login']")).getText());

			//if (driver.findElement(By.xpath("//h4[normalize-space()='Login']")).getText())
			logOutTest.createNode("Verify that user logs out successfully").log(Status.PASS, "User logged out successfully");  
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
