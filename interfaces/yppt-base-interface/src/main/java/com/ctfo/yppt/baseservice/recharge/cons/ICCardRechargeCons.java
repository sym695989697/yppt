package com.ctfo.yppt.baseservice.recharge.cons;

public class ICCardRechargeCons {

	//充值申请--待支付
	public static final String RECHARGE_APPLY_STATE_PAY_WAITING= "01";
	//充值申请--支付成功
	public static final String RECHARGE_APPLY_STATE_PAY_SUCC = "02";
	//充值申请--支付失败
	public static final String RECHARGE_APPLY_STATE_PAY_FAIL = "03";
	//充值申请--待分配
	public static final String RECHARGE_APPLY_STATE_DISTR_WAITING= "04";
	//充值申请--通过分配
	public static final String RECHARGE_APPLY_STATE_DISTR_SUCC = "05";
	//充值申请--分配中
	public static final String RECHARGE_APPLY_STATE_DISTR_DOING= "06";
	//充值申请--分配失败
	public static final String RECHARGE_APPLY_STATE_DISTR_FAIL= "07";
		
	
	//冻结失败
	public static final String RECHARGE_APPLY_FREEZE_FAIL="0";
	//冻结失败
    public static final String RECHARGE_APPLY_FREEZE_SUCC="1";
    //解冻失败
   	public static final String RECHARGE_APPLY_UNFREEZE_FAIL="3";
    //冻结失败
    public static final String RECHARGE_APPLY_UNFREEZE_SUCC="4";
	//扣款 --成功
    public static final String RECHARGE_APPLY_DEDUCT_FAIL="0";
    //扣款 --失败
    public static final String RECHARGE_APPLY_DEDUCT_SUCC="1";
  
	
	
	public static final String  RECHARGE_APPLY_FREEZE="1";
	public static final String  RECHARGE_APPLY_DEDUCT="2";
	
	public static final String RECHARGE_CALL_BACK_SUCC = "1";
	public static final String RECHARGE_CALL_BACK_FAIL = "-1";
	//充值申请--确定扣款
	public static final String RECHARGE_APPLY_SURE_DIV = "1";
	
	// 支付订单表 tableName
	public static final String  PAY_ORDER_TABLE_NAME="ICRechargeApply";
	
	//旺金币抵扣
	public static final String  PAY_ORDER_PAY_WAY_CREDIT="CREDIT";
	//账户支付
	public static final String  PAY_ORDER_PAY_WAY_ACCOUNT="ACCOUNT";
	//快捷支付
	public static final String  PAY_ORDER_PAY_WAY_FASTPAY="FASTPAY";
	//网银支付
	public static final String  PAY_ORDER_PAY_WAY_NET="NET";
	
	//开票--待审核
	public static final String INVOICE_APPLY_STATE_WAITING= "1";
	//开票--审核通过
	public static final String INVOICE_APPLY_STATE_AUDITING_SUCC= "2";
	//开票--审核失败
	public static final String INVOICE_APPLY_STATE_AUDITING_FAIL= "3";
	//开票--开票通过
    public static final String INVOICE_APPLY_STATE_OPENING_SUCC= "4";
	//开票--开票失败
	public static final String INVOICE_APPLY_STATE_OPENING_FAIL= "5";
	
	//开票--审核失败
	public static final String INVOICE_REVIEW_STATE_WAITING= "1";
	//开票--开票通过
    public static final String INVOICE_REVIEW_STATE_SUCC= "2";
	//开票--开票失败
	public static final String INVOICE_REVIEW_STATE_FAIL= "3";
	//开票--开票已提交
	public static final String INVOICE_REVIEW_STATE_SUB= "0";
	
	//网关参数
	public static final String PRODUCT_NAME="油卡充值";
	public static final String PRODUCT_CATALOG="1";
	public static final String IDENTITY_TYPE="2";
	public static final String CLENT_TYPE="2";

    //成功
	public static final String FLAG_FAIL= "1";
	//失败
	public static final String FLAG_SUCC = "0";
	
}
