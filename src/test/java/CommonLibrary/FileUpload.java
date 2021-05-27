package commonLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class FileUpload {

	@Test
	public static void fileUpload() throws InterruptedException {
		try {
			Robot robot= new Robot();
			System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver_win32/chromedriver.exe");
			WebDriver driver=new ChromeDriver();
			//			System.setProperty("webdriver.gecko.driver", "./WebDriver/geckodriver-v0.29.0-win64/geckodriver.exe");
			//			WebDriver driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("http://demo.automationtesting.in/Register.html");

			//Use browser actions,The input element may not support the click action that's why you are getting the invalid argument error.
			Actions builder = new Actions(driver);
			builder.moveToElement(driver.findElement(By.xpath("//input[@type='file']"))).click().build().perform();

			robot.setAutoDelay(2000);

			//Below 2 lines works like Ctrl+C
			StringSelection stringSelection= new StringSelection("TestFile.txt");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);//To get the file name to the clipboard
			robot.setAutoDelay(1000);

			//Below 2 lines performs Ctrl+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			//Below 2 lines releases the control of Ctrl+V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.setAutoDelay(1000);

			//Below 2 lines performs ENTER
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
