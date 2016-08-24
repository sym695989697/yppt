package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;


public class QueryCionCountRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 累计返利金币数量
	 */
	private String totalCionCount;
	
	/**
	 * 上月累计返利金币数量
	 */
	private String precedingMonthCionCount ;
	
	/**
	 * 车旺币和RMB的换算比率
	 */
	private int rate;

    /**
     * @return the totalCionCount
     */
    public String getTotalCionCount() {
        return totalCionCount;
    }

    /**
     * @param totalCionCount the totalCionCount to set
     */
    public void setTotalCionCount(String totalCionCount) {
        this.totalCionCount = totalCionCount;
    }

    /**
     * @return the precedingMonthCionCount
     */
    public String getPrecedingMonthCionCount() {
        return precedingMonthCionCount;
    }

    /**
     * @param precedingMonthCionCount the precedingMonthCionCount to set
     */
    public void setPrecedingMonthCionCount(String precedingMonthCionCount) {
        this.precedingMonthCionCount = precedingMonthCionCount;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }
	
}
