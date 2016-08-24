package com.ctfo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author jichao
 * 
 */
public class DateUtils {

	public static final String FMT_DATETIME = "yyyy-MM-dd hh:mm:ss";
	public static final String FMT_DATE = "yyyy-MM-dd";
	
	 

	/**
	 * 日期字符串转为时间戳
	 * @param dateStr 
	 * @param fmt "yyyy-MM-dd hh:mm:ss"
	 * @return
	 */
	public static long date2TimeStamp(String datetimeStr,String fmt)throws ParseException  {
		if(datetimeStr.length()==10){
			datetimeStr += " 00:00:00";
		}
		if(datetimeStr.length()==16){
			datetimeStr += ":00";
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
		Date date;
		date = simpleDateFormat.parse(datetimeStr);
		return date.getTime();
	}
	
 
    
	

	public static void main(String[] args) throws ParseException {
		System.out.println(date2TimeStamp("2014-9-5 13:05:30","yyyy-MM-dd hh:mm:ss"));
		System.out.println(date2TimeStamp("2014-9-5","yyyy-MM-dd"));
	}
}
