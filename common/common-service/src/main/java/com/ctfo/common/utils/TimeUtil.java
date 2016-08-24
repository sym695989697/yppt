package com.ctfo.common.utils;

import java.util.Calendar;

public class TimeUtil {
	// 获得前一天第一秒点时间	
	public static Long getTimesmorning() {		
		Calendar cal = Calendar.getInstance();	
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);		
		cal.set(Calendar.SECOND, 0);		
		cal.set(Calendar.MINUTE, 0);		
		cal.set(Calendar.MILLISECOND, 0);		
		return cal.getTime().getTime();	
	}
	
	// 获得前一天最后一秒点时间	
	public static Long getTimesnight() {		
		Calendar cal = Calendar.getInstance();	
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 24);		
		cal.set(Calendar.SECOND, 0);		
		cal.set(Calendar.MINUTE, 0);		
		cal.set(Calendar.MILLISECOND, 0);		
		return cal.getTime().getTime()-1;	
	}
	
	/**
	 * 获取上一月1号零点时间
	 * 
	 * @return
	 */
	public static long getLastMonthOneDateZeroUtc() {
		Calendar lastMonth = Calendar.getInstance();
		
		lastMonth.add(Calendar.MONTH, -1);// 减一个月
		lastMonth.set(Calendar.DAY_OF_MONTH, 0);
		lastMonth.set(Calendar.HOUR_OF_DAY, 24);		
		lastMonth.set(Calendar.SECOND, 0);		
		lastMonth.set(Calendar.MINUTE, 0);		
		lastMonth.set(Calendar.MILLISECOND, 0);	
		return lastMonth.getTimeInMillis();
	}
	
	/**
	 * 获取本月1号零点时间
	 * 
	 * @return
	 */
	public static long getMonthOneDateZeroUtc() {
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.set(Calendar.DAY_OF_MONTH, 0);
		lastMonth.set(Calendar.HOUR_OF_DAY, 24);		
		lastMonth.set(Calendar.SECOND, 0);		
		lastMonth.set(Calendar.MINUTE, 0);		
		lastMonth.set(Calendar.MILLISECOND, 0);	
		return lastMonth.getTimeInMillis();
	}
	
    /**
     * 
     * @param time 时间戳
     * @return
     * 获取该月第一天的毫秒数
     */
    public static long getFirstMillisecondOfMonth(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 
     * @param time 时间戳
     * @return
     * 获取该月最后一天的毫秒数
     */
    public static long getLastMillisecondOfMonth(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.AM_PM, Calendar.PM);
        cal.set(Calendar.HOUR, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }
	
	public static void main(String a[]){
		System.out.println(getMonthOneDateZeroUtc());
	}
}
