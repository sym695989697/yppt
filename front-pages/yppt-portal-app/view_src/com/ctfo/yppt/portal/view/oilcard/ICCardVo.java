package com.ctfo.yppt.portal.view.oilcard;  

import java.math.BigDecimal;

import com.ctfo.yppt.baseservice.card.beans.ICCard;
  
public class ICCardVo extends ICCard{

	private BigDecimal nowMonthRecharge;
	private BigDecimal lastMonthExpense;
	
	public BigDecimal getNowMonthRecharge() {
		return nowMonthRecharge;
	}
	public void setNowMonthRecharge(BigDecimal nowMonthRecharge) {
		this.nowMonthRecharge = nowMonthRecharge;
	}
	public BigDecimal getLastMonthExpense() {
		return lastMonthExpense;
	}
	public void setLastMonthExpense(BigDecimal lastMonthExpense) {
		this.lastMonthExpense = lastMonthExpense;
	}
	
}
