package com.ctfo.sinoiov.mobile.webapi.util;

import org.apache.commons.lang.StringUtils;


/**
 * 
 *@Title:地区编码格式化类  
 *@Description:  
 *@Author:fx  
 *@Since:2015-1-20  
 *@Version:1.1.0
 */
public class Zone_Util {
	
	public static String converterZoneToDB(String zoneCode){
		String zoneCodeDB = null;
		if(StringUtils.isNotBlank(zoneCode) && zoneCode.length() ==2){
			zoneCodeDB = zoneCode + "0000";
		}else if(StringUtils.isNotBlank(zoneCode) && zoneCode.length() ==4){
			zoneCodeDB = zoneCode + "00";
		}else{
			zoneCodeDB = zoneCode;
		}
		return zoneCodeDB;
	}
	
	public static String converterZoneToAPP(String zoneCode){
		String zoneCodeAPP = null;
		if(StringUtils.isNotBlank(zoneCode) && zoneCode.contains("0000")){
			zoneCodeAPP = zoneCode.substring(0,2);
		}else if(StringUtils.isNotBlank(zoneCode) && zoneCode.contains("00")){
			zoneCodeAPP = zoneCode.substring(0,4);
		}else{
			zoneCodeAPP = zoneCode;
		}
		return zoneCodeAPP;
		
	}
	
}
