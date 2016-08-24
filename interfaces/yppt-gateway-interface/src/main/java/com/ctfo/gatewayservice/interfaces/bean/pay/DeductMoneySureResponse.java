package com.ctfo.gatewayservice.interfaces.bean.pay;

import java.io.Serializable;

/**
 * 扣款确认返回
 * 
 * @author sunchuanfu
 */
public class DeductMoneySureResponse implements Serializable {

	private static final long serialVersionUID = -1193739150055094582L;

	private String orderNo;// 订单号
	private String result;// 结果(1：成功；－1：失败)
	private String payConfirmDate;// 支付确认时间(时间戳)

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPayConfirmDate() {
		return payConfirmDate;
	}

	public void setPayConfirmDate(String payConfirmDate) {
		this.payConfirmDate = payConfirmDate;
	}

}
