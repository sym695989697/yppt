package com.ctfo.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 项目名称：yppt-manager-app<br>
 * *********************************<br>
 * <P>类名称：DateTimeUtils</P>
 * *********************************<br>
 * <P>类描述：时间工具类</P>
 * 创建人：李飞<br>
 * 创建时间：2015年1月27日 下午11:08:55<br>
 * 修改人：李飞<br>
 * 修改时间：2015年1月27日 下午11:08:55<br>
 * 修改备注：<br>
 * @version 1.0<br>
 */
public class DateTimeUtils {
	
	// 短日期格式
	public static String DATE_FORMAT = "yyyy-MM-dd";
	
	// 长日期格式
	public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 
	 * <p>方法描述: 将日期格式的字符串转换为长整型</p>
	 *
	 * <p>方法备注: 将日期格式的字符串转换为长整型</p>
	 *
	 * @param date
	 * @param format
	 * @return
	 *
	 * <p>创建人：李飞</p>
	 *
	 * <p>创建时间：2015年1月27日 下午11:10:41</p>
	 *
	 */
	 public static Long convert2long(String date, String format) {
	  
		 try {
			 	
			 if (!StringUtils.isBlank(date)) {
				 if (StringUtils.isBlank(format))
					 format = DateTimeUtils.TIME_FORMAT;

				 SimpleDateFormat sf = new SimpleDateFormat(format);
				 return sf.parse(date).getTime();
			 }
		 } catch (ParseException e) {
			 
			 e.printStackTrace();
		 }
		 
		 return null;
	 }
	 
	 /**
	  * 
	  * <p>方法描述: 将长整型数字转换为日期格式的字符串</p>
	  *
	  * <p>方法备注: 将长整型数字转换为日期格式的字符串</p>
	  *
	  * @param time
	  * @param format
	  * @return
	  *
	  * <p>创建人：李飞</p>
	  *
	  * <p>创建时间：2015年1月27日 下午11:13:02</p>
	  *
	  */
	 public static String convert2String(long time, String format) {
	  
		 if (time > 0l) {
	   
			 if (StringUtils.isBlank(format))
	    
				 format = DateTimeUtils.TIME_FORMAT;

			 	 SimpleDateFormat sf = new SimpleDateFormat(format);
			 	 
			 	 Date date = new Date(time);

			 	 return sf.format(date);
		 }
		 
		 return null;
	 }
	 
	public static void main(String[] args) {
		
		System.out.println(convert2String(1422892800000l, ""));
	}

}
