package com.ctfo.chpt.bean.iccard.vo;

/**
 * 旺金币交易详细页VO
 * 
 * @author jichao
 */
public class GoldTradeDetailsVo {

	/**
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 绑定车辆
	 */
	private String vehicleNo;
	/**
	 * 余额balance（充值后资金账户余额）
	 */
	private String balance;
	/**
	 * 充值金额totalMoney（充值金额）
	 */
	private String totalMoney;
	/**
	 * 旺金币抵扣creditMoney（使用积分所兑换的金额）
	 */
	private String creditMoney;
	/**
	 * 资金账户支付/网银支付/快捷支付（payWay--支付方式）
	 */
	private String payWay;
	/**
	 * 支付金额captialMoney（使用账户充值金额）
	 */
	private String captialMoney;
	
	private String money;
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(String creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getCaptialMoney() {
		return captialMoney;
	}

	public void setCaptialMoney(String captialMoney) {
		this.captialMoney = captialMoney;
	}

}
