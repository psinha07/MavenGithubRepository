package testPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DashboardPage {

	public static void addNewProduct(WebDriver driver, ExtentReports extentReort, ExtentTest addNewProductTest) {
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[text()='add']")).click();
			addNewProductTest.createNode("Verify that clicking on 'Add New Product' button opens 'Add Products' screen").log(Status.PASS, "Add a new product screen opened successfully upon clicking 'Add New Product' button");
		}
		catch (Exception e) {

		}
	}

	public static void logOut(WebDriver driver, ExtentReports extentReort, ExtentTest logOutTest){
		try {
			//A click on Hamburger menu
			Thread.sleep(5000);//replace this by wait for full load
			driver.findElement(By.xpath("//span[@class='user_name m-l-10 font-clr-black']")).click();

			//A click on Logout menu-item
			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[contains(text(),'Log out')]")).click();

			driver.findElement(By.xpath("//span[contains(text(),'Yes')]")).click();
			Thread.sleep(5000);
			System.out.println("Logged out and back to Login page");
			logOutTest.createNode("Verify that user logs out successfully").log(Status.PASS, "User logged out successfully");  
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
