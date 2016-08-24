package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

import java.io.File;
import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;


public class RechargeApplyReq extends BaseBeanReq implements Body{
	/**
	 *  参数名称	参数编码	是否必填	说明
		用户ID	userId	是	类型：字符串；用户Id
		卡号	cardNum	是	类型：字符串；用逗号分隔每一张卡号
		卡ID	cardId	是	类型：字符串
		充值金额	money	是	类型：字符串；用逗号分隔每一笔金额;
		金额与卡号一一对应
		旺金币数量	coinCount		类型：double；使用旺金币数量
		账户余额	balance		类型：double；使用账户余额钱数
		是否对公充值	DGrecharge		类型：Boolean；表明用户是否对公充值
		付款人姓名	payer	是	类型：字符串；
		付款账号	payAccountNumber	是	类型：字符串；付款账号的唯一标示
		收款人	payee	是	类型：字符串；
		汇款金额	remitAmount		类型：double；
		付款日期	payTime		类型：字符串；
		付款凭证上传	imageClass	是	类型：String 格式："transportPermitImg:1,vehicleImage:1",请参照车旺APP图片传输方式
		付款凭证上传文件流	----(无参数)	是	参照车旺APP图片传输方式

	 */
	private static final long serialVersionUID = 1L;
	//RechargeApply.applyPersonId
	
//	RechargeApply.remittanceAccount
	private String cardNum;//卡号
	
	private List<ICRechargeApplyDetail> rechargeApplyDetails;//卡申请明细
	
	//RechargeApply.totalMoney
	private String	money;//去除旺金币需要支付的金额
	
	private String totalMoney;//充值总额
	
//	RechargeApply.creditNum ,积分数量
	private String coinCount;//旺金币数量
	
//	RechargeApply.availableBalance
	private String balance;//账户余额
	
	private String vechileNum;  //车牌号
	
	private int dgrecharge;//是否对公充值
	
//	RechargeApply.remitter
	private String payer;//付款人姓名
	
//	RechargeApply.remittanceAccount
	private String payAccountNumber;//付款账号
	
	private String payee;//收款人
	
//	RechargeApply.fundsArriveMoney
	private String remitAmount;//汇款金额
	
//	RechargeApply.applyTime
	private String payTime;//付款日期
	
	private File upload;//付款凭证上传
	
	private int YErecharge; //是否余额充值   1是 0不是
	
	private int  WGBrecharge;//是否旺金币充值 1是 0 不是
	
	private String  attachId; //  付款凭证附件名称
	

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getVechileNum() {
		return vechileNum;
	}

	public void setVechileNum(String vechileNum) {
		this.vechileNum = vechileNum;
	}

	public int getYErecharge() {
		return YErecharge;
	}

	public void setYErecharge(int yErecharge) {
		YErecharge = yErecharge;
	}

	public int getWGBrecharge() {
		return WGBrecharge;
	}

	public void setWGBrecharge(int wGBrecharge) {
		WGBrecharge = wGBrecharge;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	

	public String getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(String coinCount) {
		this.coinCount = coinCount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public List<ICRechargeApplyDetail> getRechargeApplyDetails() {
		return rechargeApplyDetails;
	}

	public void setRechargeApplyDetails(
			List<ICRechargeApplyDetail> rechargeApplyDetails) {
		this.rechargeApplyDetails = rechargeApplyDetails;
	}

	public int getDgrecharge() {
		return dgrecharge;
	}

	public void setDgrecharge(int dgrecharge) {
		this.dgrecharge = dgrecharge;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getPayAccountNumber() {
		return payAccountNumber;
	}

	public void setPayAccountNumber(String payAccountNumber) {
		this.payAccountNumber = payAccountNumber;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getRemitAmount() {
		return remitAmount;
	}

	public void setRemitAmount(String remitAmount) {
		this.remitAmount = remitAmount;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

}
