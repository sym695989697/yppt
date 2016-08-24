package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

public class QueryAcountBalanceRsp extends BaseBeanRsp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 可用金额
	 */
	private String usableBalance  ;
	/**
	 * 账户余额
	 */
	private String totalBalance;
	/**
	 * 累计支出
	 */
	private String tradeTotal ;
	/**
	 * 累计充值
	 */
	private String rechargeTotal ;
	
	
    /**
     * @return the usableBalance
     */
    public String getUsableBalance() {
        return usableBalance;
    }
    /**
     * @param usableBalance the usableBalance to set
     */
    public void setUsableBalance(String usableBalance) {
        this.usableBalance = usableBalance;
    }
    /**
     * @return the totalBalance
     */
    public String getTotalBalance() {
        return totalBalance;
    }
    /**
     * @param totalBalance the totalBalance to set
     */
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }
    /**
     * @return the tradeTotal
     */
    public String getTradeTotal() {
        return tradeTotal;
    }
    /**
     * @param tradeTotal the tradeTotal to set
     */
    public void setTradeTotal(String tradeTotal) {
        this.tradeTotal = tradeTotal;
    }
    /**
     * @return the rechargeTotal
     */
    public String getRechargeTotal() {
        return rechargeTotal;
    }
    /**
     * @param rechargeTotal the rechargeTotal to set
     */
    public void setRechargeTotal(String rechargeTotal) {
        this.rechargeTotal = rechargeTotal;
    }
	
}
