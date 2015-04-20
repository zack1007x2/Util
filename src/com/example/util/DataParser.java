package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DataParser {
	//JSON PARSER
	
	//DateFormateParser
	/**
	 * if month||day||yaer is null return current time
	 * @param SimpleDateFormat
	 * @param month
	 * @param day
	 * @param year
	 * @return daytime of format you set
	 */
	public static String formatedDateTime(String SimpleDateFormat,Integer month, Integer day,Integer year){
		SimpleDateFormat sdf = new SimpleDateFormat(SimpleDateFormat);
		if(month!=null&&day!=null&&year!=null){
			Calendar calendar=Calendar.getInstance();
			calendar.set(year,month-1, day);
			return sdf.format(calendar.getTime());
		}else{
			Date current = new Date();
			return sdf.format(current);	
		}
		
	}
	
	//XML PARSER
	
	

}
