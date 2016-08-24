package com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt;

import java.io.Serializable;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;

/**
 * 支付中心返回的订单信息
 * 
 * @author sunchuanfu
 */
public class PayOrderResponse implements Body  {
	private static final long serialVersionUID = -6889742154460455427L;

	private String orderNo;// 订单号
	private String payConfirmDate;// 支付确认时间(时间戳)

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayConfirmDate() {
		return payConfirmDate;
	}

	public void setPayConfirmDate(String payConfirmDate) {
		this.payConfirmDate = payConfirmDate;
	}

}
