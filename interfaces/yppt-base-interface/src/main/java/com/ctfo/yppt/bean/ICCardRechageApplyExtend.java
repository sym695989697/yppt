package com.ctfo.yppt.bean;

import java.io.Serializable;

import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;

public class ICCardRechageApplyExtend implements Serializable {

	private ICRechargeApply rechargeApply;
	private boolean isOnlyCredit;//当旺金币足够时，只使用旺金币
	
	public ICRechargeApply getRechargeApply() {
		return rechargeApply;
	}
	public void setRechargeApply(ICRechargeApply rechargeApply) {
		this.rechargeApply = rechargeApply;
	}
	public boolean isOnlyCredit() {
		return isOnlyCredit;
	}
	public void setOnlyCredit(boolean isOnlyCredit) {
		this.isOnlyCredit = isOnlyCredit;
	}
	
	
}
