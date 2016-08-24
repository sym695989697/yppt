package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 给车后老用户充值参数对象
 * 
 * @author sunchuanfu
 */
public class RechargeToUserAccountParams implements Serializable {

	private static final long serialVersionUID = -9197021569202281572L;

	private String userId;// 用户Id
	private String collectMoneyAccountNo;// 收款账号
	private String workOrderNo;// 业务订单号
	private String orderAmount;// 转账金额(单位元)
	private String storeCode;// 商户编号(这个值不需要调用接口方赋值)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCollectMoneyAccountNo() {
		return collectMoneyAccountNo;
	}

	public void setCollectMoneyAccountNo(String collectMoneyAccountNo) {
		this.collectMoneyAccountNo = collectMoneyAccountNo;
	}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

}
