package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;


public class QueryTradeRecordInfoRsp extends BaseBeanRsp{

	/**
	 * 参数名称	参数编码	是否必填	说明
		卡号	cardNum	是	类型：字符串；卡的唯一标示
		交易类型	tradeType	是	类型：字符串；
		交易金额	tradeMoney	是	类型：double；单笔消费金额
		油品	oil	是	类型：字符串；油品种类，可汉字直接展示
		数量	count	是	类型：double；单笔消费油的量（L）
		单价	price	是	类型：double；油品单价
		交易状态	tradeState	是	类型：字符串
		交易后金额	balance	是	类型：double；完成交易后卡的余额
		交易时间	tradeTime	是	类型：字符串；时间戳
		交易地点	tradeplace	是	类型：字符串；

	 */
	private static final long serialVersionUID = 1L;
	
	private String cardNum;  //卡号
	
	private String tradeMoney; //交易金额
	
	private String oil;  //油品
	
	private String count;  //数量  单笔消费油的量（L）
	
	private String price; //单价
	
	private String balance;//交易后金额 
	
	private long tradeTime; //交易时间 
	
	private String tradePlace; //交易地点 
	
	private String tradeState; //交易状态
	
	private String tradeType;	//是	类型：字符串；
	
	private String payMode;		//支付方式	充值用到
	
	private String vehicleNo;	//车牌号
	

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeState() {
		return tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public long getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(long tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradePlace() {
		return tradePlace;
	}

	public void setTradePlace(String tradePlace) {
		this.tradePlace = tradePlace;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	
	
	

}
