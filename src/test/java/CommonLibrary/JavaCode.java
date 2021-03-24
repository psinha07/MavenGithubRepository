package commonLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JavaCode {

	public static TimeZone getTimeZone() {
		TimeZone zone = TimeZone.getDefault();
		String name = zone.getDisplayName();
		System.out.println("Display name for default time zone: "+ name);
		return zone;
	}

	public static String getDate() {
		Date date = new Date();
		System.out.println("Date is: "+date);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy"+"_"+"kk.mm.ss"); 
		String strDate = formatter.format(date);
		return strDate;
	}
}