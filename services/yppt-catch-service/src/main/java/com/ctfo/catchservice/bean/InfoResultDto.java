package com.ctfo.catchservice.bean;

import java.util.List;
/**
 * 
 * @ClassName: InfoResultDto
 * @Description:  为抓取预分配数据，交易记录封装对象，储存返回的数据    
 * @author yuguangyang
 * @date 2015年1月27日 下午2:43:14
 *
 */
public class InfoResultDto {
	// 抓取交易记录使用
	private String state;
	private List<MainCardAndSubcardVo> subCardList;//子卡列表
	private String lastCardNo;
	// 抓取预分配数据使用 充值
	private String resultEndTime;
	private String retFlag;
	// 刷新卡余额使用
	private String cardNum;// 请求条数，默认每次请求99，返回后下次请求，在基础上加99
	private String resultNum;// 请求后返回条数

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastCardNo() {
		return lastCardNo;
	}

	public void setLastCardNo(String lastCardNo) {
		this.lastCardNo = lastCardNo;
	}

	public String getResultEndTime() {
		return resultEndTime;
	}

	public void setResultEndTime(String resultEndTime) {
		this.resultEndTime = resultEndTime;
	}

	public String getRetFlag() {
		return retFlag;
	}

	public void setRetFlag(String retFlag) {
		this.retFlag = retFlag;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getResultNum() {
		return resultNum;
	}

	public void setResultNum(String resultNum) {
		this.resultNum = resultNum;
	}
	public List<MainCardAndSubcardVo> getSubCardList() {
		return subCardList;
	}

	public void setSubCardList(List<MainCardAndSubcardVo> subCardList) {
		this.subCardList = subCardList;
	}
}
