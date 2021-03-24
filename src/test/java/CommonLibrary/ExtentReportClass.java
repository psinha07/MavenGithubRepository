package commonLibrary;

import java.util.TimeZone;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportClass {

	public static ExtentHtmlReporter htmlReporter= null;
	public static ExtentReports extentReport= null;
	public static ExtentTest logger= null;

	public static ExtentReports setUpExtentReport() {

		String date= JavaCode.getDate();	
		htmlReporter= new ExtentHtmlReporter("./ExtentReportRepository/"+date+"_SettingReport.html");
		htmlReporter.config().setReportName("Practice Project Test Report");
		htmlReporter.config().setDocumentTitle("Custom Extent Report");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		TimeZone timeZone = JavaCode.getTimeZone();
		extentReport.setSystemInfo("Time Zone", timeZone.getDisplayName());
		extentReport.setSystemInfo("Computer Owner: ", "Pankaj Sinha");
		return extentReport;
	}

	public static ExtentReports flushExtentReport() {
		extentReport.flush();
		return extentReport;
	}
}
