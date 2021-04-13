package testPage;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AddProductPage {

	public static void addPackedProduct(WebDriver driver, ExtentReports extentReport, ExtentTest addNewProductTest) {
		//Nutrela, Ashirwad, Godrej, Tata
		String var= "//input[@id='mat-input-2']";
		try {
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			Thread.sleep(5000);
			String parent= driver.getWindowHandle();
			System.out.println("Parent window is: "+parent);
			driver.findElement(By.xpath(var)).click();
			addNewProductTest.createNode("Verify that Product search field is clickable").log(Status.PASS, "Successfully clicked on Product Search field and a pop-up opened");




			/*
			 * Set<String> lookupHandle= driver.getWindowHandles(); Iterator<String>
			 * lookupIterator= lookupHandle.iterator(); while (lookupIterator.hasNext()) {
			 * String lookupChildItem= lookupIterator.next();
			 * 
			 * // driver.switchTo().window(lookupChildItem);
			 * driver.findElement(By.xpath("//input[@placeholder='Search Your Products']")).
			 * sendKeys("Tata"); addNewProductTest.
			 * createNode("Verify that product name can be entered in lookup").log(Status.
			 * PASS, "Product name entered successfully"); }
			 */
			driver.findElement(By.xpath("//input[@placeholder='Search Your Products']")).sendKeys("Tata");
			//Thread.sleep(10000);
			/*Set<String> tableHandle= driver.getWindowHandles();
			Iterator<String> tableIterator= tableHandle.iterator();
			while (tableIterator.hasNext()) {
				String tableChildItem= tableIterator.next();
				driver.switchTo().window(tableChildItem); // this has been added new
				System.out.println("table handle: "+tableChildItem);
				System.out.println("After switch to table");*/

				List<WebElement> columns= driver.findElements(By.xpath("//table/thead/tr[1]/th"));
				//Thread.sleep(5000);
				int columnSize= columns.size();
				System.out.println("Table column count is: "+columnSize);

				List<WebElement>  rows =  driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
				//Thread.sleep(3000);
				int rowSize=rows.size();
				System.out.println("Table row count is: "+ rowSize);	
				List<WebElement> webElementData= driver.findElements(By.xpath("//table/tbody/tr/td[3]"));
				for (int i=1; i<rowSize; i++) {
					String data= webElementData.get(i-1).getText();
					System.out.println("value in data is: "+data);
					if (data.equalsIgnoreCase("Tata Sampann Unpolished Arhar Dal/Toor Dal, 1 KG")) {
						System.out.println("Before click");
						driver.findElement(By.xpath("//table/tbody/tr"+"["+i+"]"+"/td"+"[1]")).click();
						System.out.println("After Click");
						break;
					}
				}			
			//}	
			if(driver.findElement(By.xpath("//input[@id='mat-input-45']")).isDisplayed()) {
				driver.findElement(By.xpath("//input[@id='mat-input-45']")).sendKeys("99");	
			}
			driver.findElement(By.xpath("//button[normalize-space()='Add to listing']")).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
