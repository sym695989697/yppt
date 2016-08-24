package com.ctfo.yppt.bean;

import java.io.Serializable;

import com.ctfo.yppt.baseservice.recharge.beans.ICCardPaymentOrder;
/**
 * 
 * @author rao yongbing 
 * @Description 订单bean
 * 2015年1月25日
 */
public class ICCardPaymentOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ICCardPaymentOrder iCCardPaymentOrder;

	public ICCardPaymentOrder getiCCardPaymentOrder() {
		return iCCardPaymentOrder;
	}

	public void setiCCardPaymentOrder(ICCardPaymentOrder iCCardPaymentOrder) {
		this.iCCardPaymentOrder = iCCardPaymentOrder;
	}
	
	
	

	
}
