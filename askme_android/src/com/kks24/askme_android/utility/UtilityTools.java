package com.kks24.askme_android.utility;

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
	
	public static String longToDateStringToSecondsPrecision(long l) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(l);
		Date d = (Date) c.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a");
		String time = format.format(d);
		return time;
	}
	
	public static String getDate(){

//      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
      Date resultdate = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      return format.format(resultdate);
      
	}
	
	public static String getTime(){

//      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
      Date resultdate = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
      return format.format(resultdate);
      
	}
	
	public static String getDateNTime(){

//      SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
      Date resultdate = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      return format.format(resultdate);
      
	}


	public static int monthConvertToNumber(String str){
		if (str.equals("Jan")) return 1;
		else if (str.equals("Feb")) return 2;
		else if (str.equals("Mar")) return 3;
		else if (str.equals("Apr")) return 4;
		else if (str.equals("May")) return 5;
		else if (str.equals("Jun")) return 6;
		else if (str.equals("Jul")) return 7;
		else if (str.equals("Aug")) return 8;
		else if (str.equals("Sep")) return 9;
		else if (str.equals("Oct")) return 10;
		else if (str.equals("Nov")) return 11;
		else if (str.equals("Dec")) return 12;
		else return -1;
	}
}
