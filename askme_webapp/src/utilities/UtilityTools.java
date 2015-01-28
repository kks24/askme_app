package utilities;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilityTools {
	public static String longToDateString(long l) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(l);
		Date d = (Date) c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy");
		String time = format.format(d);
		return time;
	}
	
	
	public static String longToDateStringForReport(long l) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(l);
		Date d = (Date) c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		String time = format.format(d);
		return time;
	}
	
	public static String getDate(){
		
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		      Date resultdate = new Date(System.currentTimeMillis());
	      return format.format(resultdate);
	      
		}
	
	public static int monthConvertToNumber(String str){
		switch(str){
		case "Jan": return 1;
		case "Feb": return 2;
		case "Mar": return 3;
		case "Apr": return 4;
		case "May": return 5;
		case "Jun": return 6;
		case "Jul": return 7;
		case "Aug": return 8;
		case "Sep": return 9;
		case "Oct": return 10;
		case "Nov": return 11;
		case "Dec": return 12;
		default: return -1;
		}
	}
}
