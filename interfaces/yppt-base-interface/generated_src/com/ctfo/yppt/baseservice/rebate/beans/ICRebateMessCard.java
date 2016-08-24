package com.ctfo.yppt.baseservice.rebate.beans;

import com.ctfo.common.models.BaseSerializable;
import javax.xml.bind.annotation.XmlElement;

public class ICRebateMessCard extends BaseSerializable {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.ID DB Comment: ????
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "????", required = false)
	private String id;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.USER_REBATE_ID DB Comment: 会员返利信息id
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "会员返利信息id", required = false)
	private String userRebateId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.USER_ID DB Comment: 会员id
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "会员id", required = false)
	private String userId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.BEGIN_DATE DB Comment: 账单开始时间
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "账单开始时间", required = false)
	private Long beginDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.END_DATE DB Comment: 账单结束时间
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "账单结束时间", required = false)
	private Long endDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.TOTAL_MONEY DB Comment: 交易总金额
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "交易总金额", required = false)
	private Long totalMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.REBATE_MONEY DB Comment: 返利总金额
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "返利总金额", required = false)
	private Long rebateMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.APPLY_TIME DB Comment: 申请时间
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "申请时间", required = false)
	private Long applyTime;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.SELLTY_STATE DB Comment: 审核状态
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "审核状态", required = false)
	private String selltyState;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.REMARK DB Comment: 编码
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "编码", required = false)
	private String remark;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.CARD_ID DB Comment: 卡id
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "卡id", required = false)
	private String cardId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.CARD_TYPE DB Comment: 卡类型
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "卡类型", required = false)
	private String cardType;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.CARD_NO DB Comment: 卡号
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "卡号", required = false)
	private String cardNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.VEHICLE_ID DB Comment: 车id
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "车id", required = false)
	private String vehicleId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.VEHICLE_NO DB Comment: 车牌号
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "车牌号", required = false)
	private String vehicleNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.VEHICLE_COLOR DB Comment: 车辆颜色
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "车辆颜色", required = false)
	private String vehicleColor;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_CARD.REABTE_MONTH_MESS_ID DB Comment: 月账单返利ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "月账单返利ID", required = false)
	private String reabteMonthMessId;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.ID
	 * @return  the value of TB_IC_REBATE_MESS_CARD.ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.ID
	 * @param id  the value for TB_IC_REBATE_MESS_CARD.ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldId() {
		return "ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.USER_REBATE_ID
	 * @return  the value of TB_IC_REBATE_MESS_CARD.USER_REBATE_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getUserRebateId() {
		return userRebateId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.USER_REBATE_ID
	 * @param userRebateId  the value for TB_IC_REBATE_MESS_CARD.USER_REBATE_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setUserRebateId(String userRebateId) {
		this.userRebateId = userRebateId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldUserRebateId() {
		return "USER_REBATE_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.USER_ID
	 * @return  the value of TB_IC_REBATE_MESS_CARD.USER_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.USER_ID
	 * @param userId  the value for TB_IC_REBATE_MESS_CARD.USER_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldUserId() {
		return "USER_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.BEGIN_DATE
	 * @return  the value of TB_IC_REBATE_MESS_CARD.BEGIN_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getBeginDate() {
		return beginDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.BEGIN_DATE
	 * @param beginDate  the value for TB_IC_REBATE_MESS_CARD.BEGIN_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setBeginDate(Long beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldBeginDate() {
		return "BEGIN_DATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.END_DATE
	 * @return  the value of TB_IC_REBATE_MESS_CARD.END_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getEndDate() {
		return endDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.END_DATE
	 * @param endDate  the value for TB_IC_REBATE_MESS_CARD.END_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldEndDate() {
		return "END_DATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.TOTAL_MONEY
	 * @return  the value of TB_IC_REBATE_MESS_CARD.TOTAL_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getTotalMoney() {
		return totalMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.TOTAL_MONEY
	 * @param totalMoney  the value for TB_IC_REBATE_MESS_CARD.TOTAL_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldTotalMoney() {
		return "TOTAL_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.REBATE_MONEY
	 * @return  the value of TB_IC_REBATE_MESS_CARD.REBATE_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getRebateMoney() {
		return rebateMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.REBATE_MONEY
	 * @param rebateMoney  the value for TB_IC_REBATE_MESS_CARD.REBATE_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setRebateMoney(Long rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldRebateMoney() {
		return "REBATE_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.APPLY_TIME
	 * @return  the value of TB_IC_REBATE_MESS_CARD.APPLY_TIME
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getApplyTime() {
		return applyTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.APPLY_TIME
	 * @param applyTime  the value for TB_IC_REBATE_MESS_CARD.APPLY_TIME
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldApplyTime() {
		return "APPLY_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.SELLTY_STATE
	 * @return  the value of TB_IC_REBATE_MESS_CARD.SELLTY_STATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getSelltyState() {
		return selltyState;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.SELLTY_STATE
	 * @param selltyState  the value for TB_IC_REBATE_MESS_CARD.SELLTY_STATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setSelltyState(String selltyState) {
		this.selltyState = selltyState;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldSelltyState() {
		return "SELLTY_STATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.REMARK
	 * @return  the value of TB_IC_REBATE_MESS_CARD.REMARK
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.REMARK
	 * @param remark  the value for TB_IC_REBATE_MESS_CARD.REMARK
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldRemark() {
		return "REMARK";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.CARD_ID
	 * @return  the value of TB_IC_REBATE_MESS_CARD.CARD_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.CARD_ID
	 * @param cardId  the value for TB_IC_REBATE_MESS_CARD.CARD_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldCardId() {
		return "CARD_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.CARD_TYPE
	 * @return  the value of TB_IC_REBATE_MESS_CARD.CARD_TYPE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.CARD_TYPE
	 * @param cardType  the value for TB_IC_REBATE_MESS_CARD.CARD_TYPE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldCardType() {
		return "CARD_TYPE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.CARD_NO
	 * @return  the value of TB_IC_REBATE_MESS_CARD.CARD_NO
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.CARD_NO
	 * @param cardNo  the value for TB_IC_REBATE_MESS_CARD.CARD_NO
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldCardNo() {
		return "CARD_NO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.VEHICLE_ID
	 * @return  the value of TB_IC_REBATE_MESS_CARD.VEHICLE_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.VEHICLE_ID
	 * @param vehicleId  the value for TB_IC_REBATE_MESS_CARD.VEHICLE_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldVehicleId() {
		return "VEHICLE_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.VEHICLE_NO
	 * @return  the value of TB_IC_REBATE_MESS_CARD.VEHICLE_NO
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.VEHICLE_NO
	 * @param vehicleNo  the value for TB_IC_REBATE_MESS_CARD.VEHICLE_NO
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldVehicleNo() {
		return "VEHICLE_NO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.VEHICLE_COLOR
	 * @return  the value of TB_IC_REBATE_MESS_CARD.VEHICLE_COLOR
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getVehicleColor() {
		return vehicleColor;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.VEHICLE_COLOR
	 * @param vehicleColor  the value for TB_IC_REBATE_MESS_CARD.VEHICLE_COLOR
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldVehicleColor() {
		return "VEHICLE_COLOR";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_CARD.REABTE_MONTH_MESS_ID
	 * @return  the value of TB_IC_REBATE_MESS_CARD.REABTE_MONTH_MESS_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getReabteMonthMessId() {
		return reabteMonthMessId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_CARD.REABTE_MONTH_MESS_ID
	 * @param reabteMonthMessId  the value for TB_IC_REBATE_MESS_CARD.REABTE_MONTH_MESS_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setReabteMonthMessId(String reabteMonthMessId) {
		this.reabteMonthMessId = reabteMonthMessId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldReabteMonthMessId() {
		return "REABTE_MONTH_MESS_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public ICRebateMessCard() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String tableName() {
		return "TB_IC_REBATE_MESS_CARD";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_CARD
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String daoInterface() {
		return "com.ctfo.yppt.baseservice.dao.rebate.ICRebateMessCardDAO";
	}
}