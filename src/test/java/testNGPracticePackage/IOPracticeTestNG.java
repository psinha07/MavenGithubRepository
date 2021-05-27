package testNGPracticePackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testPackage.PracticeTestSet;



public class IOPracticeTestNG {
	public  FileInputStream fis=null;
	public  XSSFWorkbook wb= null;
	public  XSSFSheet ws= null;

	/*
	public static WebDriver driver= null;
	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	 */

	@Test(dataProvider = "DataSource")
	public void dataUser(String username, String password, String team, String url) {
		try {
			PracticeTestSet.login(username, password, url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		ArrayList<Object> alObject= new ArrayList<Object>();
		alObject.add(username);
		alObject.add(password);
		alObject.add(team);
		alObject.add(url);
		 */
		//	System.out.println("alObject is: "+alObject.size());
		//System.out.println("alObject contains: "+alObject);
		//Object[][] getData=readData();
		//int size=getData.length;
		//System.out.println("Size is: "+size);;

		/*		
		driver.get(url);
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.close();
		 */
		//return alObject;
	}



	@DataProvider(name="DataSource")
	public Object[][] readData() {
		Object[][] data=null;
		try {
			File fileName = new File ("./DataFile/TestNGDataFile.xlsx");
			fis = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fis);
			ws = wb.getSheetAt(0);

			int rowCount = ws.getLastRowNum(); // gives total row count
			int colCount = ws.getRow(0).getLastCellNum(); // gives total column count
			data=new Object[rowCount][colCount];

			for (int i=0; i<rowCount; i++) {
				for (int j=0; j<colCount; j++) {
					data[i][j]=ws.getRow(i+1).getCell(j).getStringCellValue();
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/*@AfterTest
	public void clear() {
		driver.quit();
	}*/
}