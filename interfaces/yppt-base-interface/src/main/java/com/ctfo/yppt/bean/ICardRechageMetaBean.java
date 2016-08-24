package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.util.List;

import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;

public class ICardRechageMetaBean implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private ICRechargeApply  rechargeApply;
	
	
    private List<ICRechargeApplyDetail> icRechargeApplyDetails;

	public ICRechargeApply getRechargeApply() {
		return rechargeApply;
	}

	public void setRechargeApply(ICRechargeApply rechargeApply) {
		this.rechargeApply = rechargeApply;
	}

	public List<ICRechargeApplyDetail> getIcRechargeApplyDetails() {
		return icRechargeApplyDetails;
	}

	public void setIcRechargeApplyDetails(
			List<ICRechargeApplyDetail> icRechargeApplyDetails) {
		this.icRechargeApplyDetails = icRechargeApplyDetails;
	}

	
    
    
}
