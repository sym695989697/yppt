package com.ctfo.gatewayservice.util;

/**
 * 支付参数常量类
 * 
 * @author sunchuanfu
 */
public class PayConstants {
	private static EnvironmentUtil resourceInstance = EnvironmentUtil.getInstance();

	// 本系统私钥
	public static String myPrivateKey = resourceInstance.getPropertyValue("YYPT_GATEWAY_PRIVATE_KEY");
	// web-api系统的私钥
	public static String myWebApiPrivateKey = resourceInstance.getPropertyValue("YYPT_MOBILE_PRIVATE_KEY");
	// 支付中心公钥
	public static String uppPublicKey = resourceInstance.getPropertyValue("INTERFACE_PUBLIC_KEY");
	// 本系统商户编码
	public static String myMerchantCode = resourceInstance.getPropertyValue("MERCHANT_CODE");
	// 支付系统URL
	public static String uppURL = resourceInstance.getPropertyValue("UPP_INTERFACE_URL");
	// 收银台URL
	public static String uppCashierUrl = resourceInstance.getPropertyValue("UPP_CASHIER_URL");
	// 收银台扣款后的回调URL
	public static String cashierDeductCallbackURL = resourceInstance.getPropertyValue("UPP_CASHIER_DEDUCT_CALLBACKURL");
	// 跳到易宝后的支付Logo
	public static String uppPayLogo = resourceInstance.getPropertyValue("UPP_PAY_LOGO");

}
