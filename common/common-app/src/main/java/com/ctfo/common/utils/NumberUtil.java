package com.ctfo.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil {
	
	private static NumberFormat defaultMoneyFormat = new DecimalFormat("0.00");
	
	/**
	 * 金额保留两位显示. 如1.00元
	 * @param money
	 * @return
	 */
	public static String converMoneyDefaultFormat(BigDecimal money) {
		return defaultMoneyFormat.format(converMoneyToPage(money));
	}
	
	public static String converMoneyDefaultFormat(Long money) {
		return defaultMoneyFormat.format(converMoneyToPage(money));
	}
	
	public static String converMoneyDefaultFormat(double money) {
		return defaultMoneyFormat.format(converMoneyToPage(money));
	}
	
	private static double converMoneyToPage(Double money) {
		if(money !=null ){
			return new BigDecimal(money).divide(Constants.BIG_DECIMAL_100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0D;
	}

	/**
	 * 转换系统中数量,除以100后取两位小数
	 * @param productNum 金额
	 * @return 转换后金额,money为空,返回0;
	 */
	public static double converProductNumToPage(Long productNum){
		if(productNum !=null ){
			return new BigDecimal(productNum).divide(Constants.BIG_DECIMAL_100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0D;
	}
	
	/**
	 * 转换系统中金额,除以100后取两位小数
	 * @param money 金额
	 * @return 转换后金额,money为空,返回0;
	 */
	public static double converMoneyToPage(BigDecimal money){
		if(money !=null ){
			return money.divide(Constants.BIG_DECIMAL_100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return 0D;
	}
	
	public static double converMoneyToPage(Long money){
		return converMoneyToPage(new BigDecimal(money));
	}
	
	/**
	 * 转换系统中金额,乘以100后取两位小数
	 * @param money 金额
	 * @return 转换后金额,money为空,返回0;
	 */
	public static long converMoneyToDB(String money){
		return converMoneyToDB(new BigDecimal(money));
	}
	
	public static long converMoneyToDB(double money){
		return converMoneyToDB(new BigDecimal(money));
	}
	
	public static long converMoneyToDB(BigDecimal money){
		return money.multiply(Constants.BIG_DECIMAL_100).longValue();
	}
}
