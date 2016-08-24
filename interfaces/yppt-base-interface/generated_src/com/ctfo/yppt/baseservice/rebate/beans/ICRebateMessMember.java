package com.ctfo.yppt.baseservice.rebate.beans;

import com.ctfo.common.models.BaseSerializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class ICRebateMessMember extends BaseSerializable {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.ID DB Comment: uuid
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "uuid", required = false)
	private String id;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.USER_ID DB Comment: 会员id
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "会员id", required = false)
	private String userId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.USER_TYPE DB Comment: 用户类型(2:企业,3:个人）
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "用户类型(2:企业,3:个人）", required = false)
	private String userType;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.USER_NAME DB Comment: 会员名称
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "会员名称", required = false)
	private String userName;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.ACCOUNTDATE DB Comment: 账单期(格式如 201408)
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "账单期(格式如 201408)", required = false)
	private String accountdate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.ACCOUNT_START_DATE DB Comment: 账单期开始时间
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "账单期开始时间", required = false)
	private Long accountStartDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.ACCOUNT_END_DATE DB Comment: 账单期结算时间
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "账单期结算时间", required = false)
	private Long accountEndDate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.TOTAL_MONEY DB Comment: 交易总金额
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "交易总金额", required = false)
	private Long totalMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.TOTAL_NUM DB Comment: 交易总数量
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "交易总数量", required = false)
	private BigDecimal totalNum;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.REBATE_MONEY DB Comment: 返利总金额
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "返利总金额", required = false)
	private Long rebateMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.CARD_NUM DB Comment: 会员本次返利卡数量
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "会员本次返利卡数量", required = false)
	private Integer cardNum;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.APPLY_TIME DB Comment: 申请时间
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "申请时间", required = false)
	private Long applyTime;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.SELLTY_STATE DB Comment: 结算状态(0:未结算,1:结算通过;2:结算失败)
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "结算状态(0:未结算,1:结算通过;2:结算失败)", required = false)
	private String selltyState;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.REMARK DB Comment: 备注
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "备注", required = false)
	private String remark;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_REBATE_MESS_USER.ADJUST_MONEY DB Comment: 返利调整后金额
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	@XmlElement(name = "返利调整后金额", required = false)
	private BigDecimal adjustMoney;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.ID
	 * @return  the value of TB_IC_REBATE_MESS_USER.ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.ID
	 * @param id  the value for TB_IC_REBATE_MESS_USER.ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldId() {
		return "ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.USER_ID
	 * @return  the value of TB_IC_REBATE_MESS_USER.USER_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.USER_ID
	 * @param userId  the value for TB_IC_REBATE_MESS_USER.USER_ID
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldUserId() {
		return "USER_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.USER_TYPE
	 * @return  the value of TB_IC_REBATE_MESS_USER.USER_TYPE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.USER_TYPE
	 * @param userType  the value for TB_IC_REBATE_MESS_USER.USER_TYPE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldUserType() {
		return "USER_TYPE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.USER_NAME
	 * @return  the value of TB_IC_REBATE_MESS_USER.USER_NAME
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.USER_NAME
	 * @param userName  the value for TB_IC_REBATE_MESS_USER.USER_NAME
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldUserName() {
		return "USER_NAME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.ACCOUNTDATE
	 * @return  the value of TB_IC_REBATE_MESS_USER.ACCOUNTDATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getAccountdate() {
		return accountdate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.ACCOUNTDATE
	 * @param accountdate  the value for TB_IC_REBATE_MESS_USER.ACCOUNTDATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setAccountdate(String accountdate) {
		this.accountdate = accountdate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldAccountdate() {
		return "ACCOUNTDATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.ACCOUNT_START_DATE
	 * @return  the value of TB_IC_REBATE_MESS_USER.ACCOUNT_START_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getAccountStartDate() {
		return accountStartDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.ACCOUNT_START_DATE
	 * @param accountStartDate  the value for TB_IC_REBATE_MESS_USER.ACCOUNT_START_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setAccountStartDate(Long accountStartDate) {
		this.accountStartDate = accountStartDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldAccountStartDate() {
		return "ACCOUNT_START_DATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.ACCOUNT_END_DATE
	 * @return  the value of TB_IC_REBATE_MESS_USER.ACCOUNT_END_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getAccountEndDate() {
		return accountEndDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.ACCOUNT_END_DATE
	 * @param accountEndDate  the value for TB_IC_REBATE_MESS_USER.ACCOUNT_END_DATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setAccountEndDate(Long accountEndDate) {
		this.accountEndDate = accountEndDate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldAccountEndDate() {
		return "ACCOUNT_END_DATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.TOTAL_MONEY
	 * @return  the value of TB_IC_REBATE_MESS_USER.TOTAL_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getTotalMoney() {
		return totalMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.TOTAL_MONEY
	 * @param totalMoney  the value for TB_IC_REBATE_MESS_USER.TOTAL_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldTotalMoney() {
		return "TOTAL_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.TOTAL_NUM
	 * @return  the value of TB_IC_REBATE_MESS_USER.TOTAL_NUM
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public BigDecimal getTotalNum() {
		return totalNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.TOTAL_NUM
	 * @param totalNum  the value for TB_IC_REBATE_MESS_USER.TOTAL_NUM
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldTotalNum() {
		return "TOTAL_NUM";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.REBATE_MONEY
	 * @return  the value of TB_IC_REBATE_MESS_USER.REBATE_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getRebateMoney() {
		return rebateMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.REBATE_MONEY
	 * @param rebateMoney  the value for TB_IC_REBATE_MESS_USER.REBATE_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setRebateMoney(Long rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldRebateMoney() {
		return "REBATE_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.CARD_NUM
	 * @return  the value of TB_IC_REBATE_MESS_USER.CARD_NUM
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Integer getCardNum() {
		return cardNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.CARD_NUM
	 * @param cardNum  the value for TB_IC_REBATE_MESS_USER.CARD_NUM
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldCardNum() {
		return "CARD_NUM";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.APPLY_TIME
	 * @return  the value of TB_IC_REBATE_MESS_USER.APPLY_TIME
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public Long getApplyTime() {
		return applyTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.APPLY_TIME
	 * @param applyTime  the value for TB_IC_REBATE_MESS_USER.APPLY_TIME
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldApplyTime() {
		return "APPLY_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.SELLTY_STATE
	 * @return  the value of TB_IC_REBATE_MESS_USER.SELLTY_STATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getSelltyState() {
		return selltyState;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.SELLTY_STATE
	 * @param selltyState  the value for TB_IC_REBATE_MESS_USER.SELLTY_STATE
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setSelltyState(String selltyState) {
		this.selltyState = selltyState;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldSelltyState() {
		return "SELLTY_STATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.REMARK
	 * @return  the value of TB_IC_REBATE_MESS_USER.REMARK
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.REMARK
	 * @param remark  the value for TB_IC_REBATE_MESS_USER.REMARK
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldRemark() {
		return "REMARK";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_REBATE_MESS_USER.ADJUST_MONEY
	 * @return  the value of TB_IC_REBATE_MESS_USER.ADJUST_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public BigDecimal getAdjustMoney() {
		return adjustMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_REBATE_MESS_USER.ADJUST_MONEY
	 * @param adjustMoney  the value for TB_IC_REBATE_MESS_USER.ADJUST_MONEY
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public void setAdjustMoney(BigDecimal adjustMoney) {
		this.adjustMoney = adjustMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String fieldAdjustMoney() {
		return "ADJUST_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public ICRebateMessMember() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String tableName() {
		return "TB_IC_REBATE_MESS_USER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_REBATE_MESS_USER
	 * @abatorgenerated  Wed Jan 21 20:45:54 CST 2015
	 */
	public static String daoInterface() {
		return "com.ctfo.yppt.baseservice.dao.rebate.ICRebateMessMemberDAO";
	}
}