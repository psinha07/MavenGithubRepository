package testPage;

import java.util.Iterator;
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
			addNewProductTest.createNode("Verify that TATA enters in Lookup Screen").log(Status.PASS,  "TATA entered successfully in the Lookup Screen");

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

					yourPrice.sendKeys("48");
					//act.moveToElement(driver.findElement(By.xpath("//input[starts-with(@class, 'mat-input-element mat-form-field-autofill-control price-input')][@ng-reflect-disabled='false']"))).sendKeys("70").build().perform();
					addNewProductTest.createNode("Verify that Price enters in Your Price field").log(Status.PASS,  "Price entered in Your Price field successfully");
					Thread.sleep(3000);
					System.out.println("After entering price");
					break;
				}
			}			
			driver.findElement(By.xpath("//button[normalize-space()='Add to listing']")).click();
			addNewProductTest.createNode("Verify a Product is finally added to the Product List").log(Status.PASS, "A Product was successfully added to the Product List");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addBrandByFilter(WebDriver driver, ExtentReports extentReport, ExtentTest addNewProuctTest) {
		try {
			driver.findElement(By.xpath("//input[@placeholder='Select Brand']")).click();
			System.out.println("Clicked on Brand drop down");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			List<WebElement> brandList= driver.findElements(By.xpath("//table/tbody[@role='rowgroup']/tr"));
			System.out.println("Size of brandList is: "+brandList.size());

			List<WebElement> columnElement= driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			int colSize= columnElement.size();
			System.out.println("No. of columns is: "+colSize);

			List<WebElement> rowElement= driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
			int rowSize= rowElement.size();
			System.out.println("No. of row is: "+rowSize);

			int i=0;
			Iterator<WebElement> itr= brandList.iterator();

			while(itr.hasNext()) {
				String brandName= itr.next().getText();	
				System.out.println(i+". Brand Name is: "+brandName);

				if(brandName.equalsIgnoreCase("Dhara")) {
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

					WebElement brandElement= driver.findElement(By.xpath("//table/tbody/tr["+(i+1)+"]"+"/td[1]"));
					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("arguments[0].scrollIntoView(true);", brandElement);
					js.executeScript("arguments[0].click();", new Object[] {
							brandElement
					});
					System.out.println("Brand has been selected in the web table");
					addNewProuctTest.createNode("Verify that Brand name is selected in the 'Select Brand' web table").log(Status.PASS, "A Brand has been selected successfully in the 'Select Brand' web table");

					driver.findElement(By.xpath("//button[@class='add-buton']")).click();
					addNewProuctTest.createNode("Verify that the selected Brand is populated in the 'Select Brand' drop-down from the 'Select Brand' webtable").log(Status.PASS, "Brand has been selected successfully in the 'Select Brand' drop-down");
					break;
				}
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Selection of Category
	public static void selectCategory(WebDriver driver, ExtentReports extentReport, ExtentTest addNewProuctTest) {
		try {
			driver.findElement(By.xpath("//input[@placeholder='Select Category']")).click();
			System.out.println("Select Category drop-down has been clicked");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			List<WebElement> catList= driver.findElements(By.xpath("//table/tbody[@role='rowgroup']/tr"));
			System.out.println("No.of Categories is: "+catList.size());

			List<WebElement> catColumn= driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			int colCount= catColumn.size();
			System.out.println("No. of columns in Select Category is: "+colCount);

			List<WebElement> catRow= driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
			int rowCount= catRow.size();
			System.out.println("No. of rows in Select Category is: "+rowCount);

			int i=0;
			Iterator<WebElement> itr= catList.iterator();
			while(itr.hasNext()) {
				String categoryName= itr.next().getText();
				if (categoryName.equalsIgnoreCase("Staple food, Oils & Spices")) {
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					WebElement categoryElement= driver.findElement(By.xpath("//table/tbody/tr["+(i+1)+"]"+"/td[1]"));

					JavascriptExecutor js = (JavascriptExecutor)driver;		
					js.executeScript("arguments[0].scrollIntoView(true);", categoryElement);
					js.executeScript("arguments[0].click();", new Object[] {
							categoryElement
					});
					System.out.println("CB of a category has been selected");
					driver.findElement(By.xpath("//button[text()='Add']")).click();
					System.out.println("Category has been selected");
					addNewProuctTest.createNode("Verify that Category is successfully selected from the 'Select Category' webtable").log(Status.PASS, "Category has been selected successfully");
					break;
				}
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Selection of Sub Category
	public static void selectSubCategory(WebDriver driver, ExtentReports extentReport, ExtentTest addNewProuctTest) {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@id='mat-input-5']")).click();

			System.out.println("Select Sub Category drop-down has been clicked");


			List<WebElement> subCatList= driver.findElements(By.xpath("//table/tbody[@role='rowgroup']/tr"));
			System.out.println("No.of Sub Categories is: "+subCatList.size());

			List<WebElement> subCatColumn= driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			int colCount= subCatColumn.size();
			System.out.println("No. of columns in Select Sub Category is: "+colCount);

			List<WebElement> catRow= driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
			int rowCount= catRow.size();
			System.out.println("No. of rows in Select Sub Category is: "+rowCount);

			int i=0;
			Iterator<WebElement> itr= subCatList.iterator();
			while(itr.hasNext()) {
				String categoryName= itr.next().getText();
				if (categoryName.equalsIgnoreCase("Cooking Oils & Ghee")) {
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					WebElement subCategoryElement= driver.findElement(By.xpath("//table/tbody/tr["+(i+1)+"]"+"/td[1]"));

					//Center element for interaction
					JavascriptExecutor js = (JavascriptExecutor)driver;					
					js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", subCategoryElement);
					//Scroll element into view for interactions
					JavascriptExecutor je = (JavascriptExecutor)driver;
					je.executeScript("arguments[0].scrollIntoView(true);", subCategoryElement);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					subCategoryElement.click();
					System.out.println("CB of a Sub category has been selected");
					driver.findElement(By.xpath("//button[text()='Add']")).click();
					System.out.println("Sub Category has been selected");
					addNewProuctTest.createNode("Verify that Sub Category is successfully selected from the 'Select Sub Category' webtable").log(Status.PASS, "Sub Category has been selected successfully");
					break;
				}
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Selection of Product
	public static void selectProduct(WebDriver driver, ExtentReports extentReport, ExtentTest addNewProuctTest) {
		try {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@id='mat-input-6']")).click();
			System.out.println("Select Product drop-down has been clicked");

			List<WebElement> selectProductList= driver.findElements(By.xpath("//table/tbody[@role='rowgroup']/tr"));
			System.out.println("No.of Products is: "+selectProductList.size());

			List<WebElement> prdColumn= driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
			int colCount= prdColumn.size();
			System.out.println("No. of columns in 'Select Product' web table is: "+colCount);

			List<WebElement> prdRow= driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
			int rowCount= prdRow.size();
			System.out.println("No. of rows in 'Select Product' web table is: "+rowCount);

			int i=0;
			Iterator<WebElement> itr= selectProductList.iterator();
			System.out.println("Counting after Iterator line");
			while(itr.hasNext()) {
				String productName= itr.next().getText();
				System.out.println(i+". Product Name is: "+productName);

				if (productName.startsWith("Dhara Mustard Oil (Bottle)")) {
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					WebElement productElement= driver.findElement(By.xpath("//table/tbody/tr["+(i+1)+"]"+"/td[1]"));

					JavascriptExecutor js = (JavascriptExecutor)driver;
					js.executeScript("arguments[0].scrollIntoView(true);", productElement);
					productElement.click();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

					/*
					js.executeScript("arguments[0].click();", new Object[] {
							productElement
					});
					 */

					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					System.out.println("CB of a Product has been selected");

					WebElement prdPrice= driver.findElement(By.xpath("//input[starts-with(@class, 'mat-input-element mat-form-field-autofill-control ng-tns-c119')][@ng-reflect-disabled='false']"));
					//Center prdPrice for interaction
					JavascriptExecutor je = (JavascriptExecutor)driver;				
					je.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", prdPrice);
					//Scroll yourPrice into view for interactions
					je.executeScript("arguments[0].scrollIntoView(false);", prdPrice);
					prdPrice.sendKeys("87");
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					System.out.println("Debug: reached till coded");
					driver.findElement(By.xpath("//button[contains(text(), 'Add to listing')]")).click();

					addNewProuctTest.createNode("Verify that Product is successfully selected from the 'Select Product' webtable").log(Status.PASS, "Product has been selected successfully");
					break;
				}
				i++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}