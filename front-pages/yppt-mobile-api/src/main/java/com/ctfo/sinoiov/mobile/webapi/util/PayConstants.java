package com.ctfo.sinoiov.mobile.webapi.util;

import com.ctfo.util.EnvironmentUtil;

/**
 * 支付参数常量类
 * 
 * @author sunchuanfu
 */
public class PayConstants {
	private static EnvironmentUtil resourceInstance = EnvironmentUtil.getInstance();

	// 本系统私钥
	public static String myPrivateKey = resourceInstance.getPropertyValue("YYPT_MOBILE_PRIVATE_KEY");
	// 支付中心公钥
	public static String uppPublicKey = resourceInstance.getPropertyValue("INTERFACE_PUBLIC_KEY");
	// 本系统商户编码
	public static String myMerchantCode = resourceInstance.getPropertyValue("MERCHANT_CODE");
	// 支付系统URL
	public static String uppURL = resourceInstance.getPropertyValue("UPP_INTERFACE_URL");
	// 获取手机快捷支付URL地址
	// public static String uppDeductMoneyUrlForMobile =
	// resourceInstance.getPropertyValue("UPP_GET_DEDUCTMONEY_URL_FOR_MOBILE");
	// 扣款后的回调URL
	public static String deductCallbackURL = resourceInstance.getPropertyValue("UPP_DEDUCT_CALLBACK_URL");
	// 跳到易宝后的支付Logo
	public static String uppPayLogo = resourceInstance.getPropertyValue("UPP_PAY_LOGO");

}
