package testPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.ReaderInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonLibrary.JavaCode;
import commonLibrary.SeleniumUtility;

public class WindowHandling {

	public static WebDriver driver=null;
	public ExtentHtmlReporter extHtmlReporter=null;
	public ExtentReports extReport=null;
	public String url=null;

	@BeforeTest(enabled=false)
	public void startUp() {
		url="https://www.naukri.com/"; //This will be dynamically pulled upon implementation of Apache POI

		//Called Reusable method:
		driver= SeleniumUtility.startUp("webdriver.chrome.driver", "./WebDriver/chromedriver_win32/chromedriver.exe", url);
		String date= JavaCode.getDate();
		extHtmlReporter= new ExtentHtmlReporter("./ExtentReport/TestReport"+"-"+date+".html");
		extReport= new ExtentReports();
		extReport.attachReporter(extHtmlReporter);
	}

	@Test(enabled=false)
	public void manageWindow() throws Exception{
		extReport.createTest("Get Parent window handle").assignCategory("Regression Test");
		String parentWindow=driver.getWindowHandle();
		System.out.println("Parent window identity is: "+parentWindow);

		extReport.createTest("Get all windows");
		Set<String> windowSet=driver.getWindowHandles();
		System.out.println("No. of windows is: "+windowSet.size());
		Iterator<String> itr=windowSet.iterator();
		while(itr.hasNext()) {
			String childHandle= itr.next();
			if(!parentWindow.equalsIgnoreCase(childHandle)) {
				ExtentTest childRep= extReport.createTest("Switch to Child Window").assignCategory("Regression Test");
				driver.switchTo().window(childHandle);
				System.out.println(driver.switchTo().window(childHandle).getTitle());
				String winTitle= driver.switchTo().window(childHandle).getTitle();
				if(winTitle.equalsIgnoreCase("Fujitsu")) {
					driver.manage().window().maximize();

					childRep.createNode("Click on child window webelement");
					driver.findElement(By.xpath("//img[@alt='Fujitsu']")).click();
				}
			}
		}
		ExtentTest parRep= extReport.createTest("Switch back to Parent window").assignCategory("Regression Test");
		driver.switchTo().window(parentWindow);
		parRep.createNode("Click on Login link");
		driver.findElement(By.xpath("//div[contains(text(), 'Login')]")).click();
		parRep.createNode("Enter active Email ID/ Username");

		/*
		//Called Reusable method of explicitWait
		WebElement elementLogin=SeleniumUtility.explicitWait(driver, "//input[@placeholder='Enter your active Email ID / Username']");
		elementLogin.sendKeys("psinha66");
		 */		

		//Called Reusable method of fluentWait
		WebElement elementLogin= SeleniumUtility.fluentWait(driver, "//input[@placeholder='Enter your active Email ID / Username']");
		elementLogin.sendKeys("psinha66");

		parRep.createNode("Enter your password");
		driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("Password");

		//		driver.navigate().back();
		//		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		//		parRep.createNode("Click on Jobs by Skill link");
		//		driver.findElement(By.xpath("//div[contains(@class,'column')]//a[@title='Jobs by Skill'][normalize-space()='Jobs by Skill']")).click();
	}
	@AfterTest(enabled=false)
	public void tearDown() {
		extReport.flush();
		//		driver.quit();
	}

	@Test(enabled=false)
	public void arrayList() throws IOException {
		FileInputStream fis=new FileInputStream("./DataFile/TestNGDataFile.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet ws=wb.getSheetAt(0);

		ArrayList<String> al=new ArrayList<String>();
		// Add elements to the array list.
		al.add("Apple");
		al.add("Orange");
		al.add("Grape");
		al.add("Papaya");
		System.out.println("Contents of al: " + al);
		// Get the array.
		String sa[]=new String [al.size()];
		sa=al.toArray(sa);
		//Way 1:
		for (int i=0; i<sa.length; i++) {
			System.out.println(sa[i]);	
		}
		//Way 2:
		Iterator<String> ai=al.iterator();
		while(ai.hasNext()) {
			System.out.println(ai.next());
		}
	}

	@Test(enabled=true)
	public void hashMap() {
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("Name", "Pankaj");
		hm.put("Dept", "QA");
		hm.put("Role", "Leader");
		hm.put("Location", "Delhi");

		// Get a set of the entries.
		Set<Map.Entry<String, String>> set = hm.entrySet();
		System.out.println(set.size());
		System.out.println(hm.keySet());
		System.out.println(hm.values());
		
		// Display the set.
		for(Map.Entry<String, String> me: set) {
			System.out.print("\t"+me.getKey());
			System.out.println("\t"+me.getValue());
		}
	}
}