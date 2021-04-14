package testPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AddProductPage {

	public static void addPackedProduct(WebDriver driver, ExtentReports extentReport, ExtentTest addNewProductTest) {
		//Nutrela, Ashirwad, Godrej, Tata
		String var= "//input[@id='mat-input-2']";
		try {

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			driver.findElement(By.xpath(var)).click();
			addNewProductTest.createNode("Verify that Product search field is clickable").log(Status.PASS, "Successfully clicked on Product Search field and a pop-up opened");


			driver.findElement(By.xpath("//input[@placeholder='Search Your Products']")).sendKeys("Tata");

			List<WebElement> columns= driver.findElements(By.xpath("//table/thead/tr[1]/th"));
			//Thread.sleep(5000);
			int columnSize= columns.size();
			System.out.println("Table column count is: "+columnSize);

			List<WebElement>  rows =  driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
			int rowSize=rows.size();
			System.out.println("Table row count is: "+ rowSize);	

			List<WebElement> webElementData= driver.findElements(By.xpath("//table/tbody/tr/td[3]"));
			Actions act = new Actions(driver);
			for (int i=1; i<rowSize; i++) {
				String data= webElementData.get(i-1).getText();
				System.out.println("value in data is: "+data);
				if (data.equalsIgnoreCase("Tata Sampann Unpolished Arhar Dal/Toor Dal, 1 KG")) {
					System.out.println("Before CB Click");
					//driver.findElement(By.xpath("//table/tbody/tr"+"["+i+"]"+"/td"+"[1]")).click();// this also worked, so retaining this code-line
					act.moveToElement(driver.findElement(By.xpath("//table/tbody/tr"+"["+i+"]"+"/td"+"[1]"))).click().perform();
					System.out.println("After CB Click");

					//Get the element of Price in the reference variable yourPrice

					//Below - R&D
					WebElement yourPrice= driver.findElement(By.xpath("//input[starts-with(@class, 'mat-input-element mat-form-field-autofill-control price-input')][@ng-reflect-disabled='false']"));

					//WebElement yourPrice= driver.findElement(By.xpath("//table/tbody/tr"+"["+i+"]"+"/td"+"[6]"));					

					//Center yourPrice for interaction
					JavascriptExecutor js = (JavascriptExecutor)driver;					
					js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", yourPrice);
					//Scroll yourPrice into view for interactions
					JavascriptExecutor je = (JavascriptExecutor)driver;
					je.executeScript("arguments[0].scrollIntoView(false);", yourPrice);

					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					//Perform action on the element (yourPrice) using the object of the Actions class
					//	act.moveToElement(driver.findElement(By.xpath("//table/tbody/tr"+"["+i+"]"+"/td"+"[6]"))).sendKeys("70").build().perform();

					//Below R&D

					yourPrice.sendKeys("51");
					//act.moveToElement(driver.findElement(By.xpath("//input[starts-with(@class, 'mat-input-element mat-form-field-autofill-control price-input')][@ng-reflect-disabled='false']"))).sendKeys("70").build().perform();

					Thread.sleep(3000);
					System.out.println("After entering price");
					break;
				}
			}			
			driver.findElement(By.xpath("//button[normalize-space()='Add to listing']")).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
