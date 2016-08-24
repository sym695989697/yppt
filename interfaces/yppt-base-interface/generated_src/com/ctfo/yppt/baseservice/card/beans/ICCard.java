package com.ctfo.yppt.baseservice.card.beans;

import com.ctfo.common.models.BaseSerializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class ICCard extends BaseSerializable {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.ID DB Comment: 主键
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "主键", required = false)
	private String id;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.CARD_NUMBER DB Comment: 卡号
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "卡号", required = false)
	private String cardNumber;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.PARENT_ID DB Comment: 父卡(主卡)id
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "父卡(主卡)id", required = false)
	private String parentId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.BALANCE DB Comment: 卡余额
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "卡余额", required = false)
	private BigDecimal balance;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.OPENCARDOFFICEID DB Comment: 发卡机构id(同主卡修改)
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "发卡机构id(同主卡修改)", required = false)
	private String opencardofficeid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.CREATED_TIME DB Comment: 创建时间
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "创建时间", required = false)
	private Long createdTime;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.MODIFIED_TIME DB Comment: 修改时间
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "修改时间", required = false)
	private Long modifiedTime;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.BALANCE_MODIFIED_TIME DB Comment: 卡余额更新时间
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "卡余额更新时间", required = false)
	private Long balanceModifiedTime;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.CTRDITUSER DB Comment: 创建人
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "创建人", required = false)
	private String ctrdituser;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.MODIFINGUSER DB Comment: 修改人
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "修改人", required = false)
	private String modifinguser;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.MESSAGE_OR_NOT DB Comment: 是否发送短信
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "是否发送短信", required = false)
	private String messageOrNot;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.TELEPHONE_NUMBER DB Comment: 绑定电话号码
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "绑定电话号码", required = false)
	private String telephoneNumber;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.USER_ID DB Comment: 用户统一认证(用户中心)ID;user_id
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "用户统一认证(用户中心)ID;user_id", required = false)
	private String userId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.VEHICLE_ID DB Comment: 车辆中心的车辆ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "车辆中心的车辆ID", required = false)
	private String vehicleId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.USER_TYPE DB Comment: 用户类型:(2:企业,3:个人）
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "用户类型:(2:企业,3:个人）", required = false)
	private String userType;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.USER_NAME DB Comment: 用户名称
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "用户名称", required = false)
	private String userName;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.USER_REG_PHONE DB Comment: 用户注册手机号
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "用户注册手机号", required = false)
	private String userRegPhone;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.VEHICLE_NO DB Comment: 车牌号
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "车牌号", required = false)
	private String vehicleNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.VEHICLE_COLOR DB Comment: 车牌颜色
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "车牌颜色", required = false)
	private String vehicleColor;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.STATE DB Comment: 状态
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "状态", required = false)
	private String state;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.DATA_SOURCE DB Comment: 数据来源(0:门户,1:手机APP,2:后台管理系统)
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "数据来源(0:门户,1:手机APP,2:后台管理系统)", required = false)
	private String dataSource;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.CARD_TYPE DB Comment: 卡类型(中石油,中石化卡)
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "卡类型(中石油,中石化卡)", required = false)
	private String cardType;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.OWN_ORG DB Comment: 所属组织(码表5577)
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "所属组织(码表5577)", required = false)
	private String ownOrg;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.VEHICLE_LICENSE DB Comment: 车辆行驶证
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "车辆行驶证", required = false)
	private String vehicleLicense;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.CARD_AREA_CODE DB Comment: 发卡地区编码（省份）
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "发卡地区编码（省份）", required = false)
	private String cardAreaCode;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.CARD_AREA_NAME DB Comment: 发卡地区名称（省份）
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "发卡地区名称（省份）", required = false)
	private String cardAreaName;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.PARENT_CARD_NUMBER DB Comment: 父卡卡号
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "父卡卡号", required = false)
	private String parentCardNumber;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD.OPEN_CARD_TIME DB Comment: 开卡时间
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	@XmlElement(name = "开卡时间", required = false)
	private Long openCardTime;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.ID
	 * @return  the value of TB_IC_CARD.ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.ID
	 * @param id  the value for TB_IC_CARD.ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldId() {
		return "ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.CARD_NUMBER
	 * @return  the value of TB_IC_CARD.CARD_NUMBER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.CARD_NUMBER
	 * @param cardNumber  the value for TB_IC_CARD.CARD_NUMBER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldCardNumber() {
		return "CARD_NUMBER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.PARENT_ID
	 * @return  the value of TB_IC_CARD.PARENT_ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.PARENT_ID
	 * @param parentId  the value for TB_IC_CARD.PARENT_ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldParentId() {
		return "PARENT_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.BALANCE
	 * @return  the value of TB_IC_CARD.BALANCE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.BALANCE
	 * @param balance  the value for TB_IC_CARD.BALANCE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldBalance() {
		return "BALANCE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.OPENCARDOFFICEID
	 * @return  the value of TB_IC_CARD.OPENCARDOFFICEID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getOpencardofficeid() {
		return opencardofficeid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.OPENCARDOFFICEID
	 * @param opencardofficeid  the value for TB_IC_CARD.OPENCARDOFFICEID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setOpencardofficeid(String opencardofficeid) {
		this.opencardofficeid = opencardofficeid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldOpencardofficeid() {
		return "OPENCARDOFFICEID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.CREATED_TIME
	 * @return  the value of TB_IC_CARD.CREATED_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public Long getCreatedTime() {
		return createdTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.CREATED_TIME
	 * @param createdTime  the value for TB_IC_CARD.CREATED_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldCreatedTime() {
		return "CREATED_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.MODIFIED_TIME
	 * @return  the value of TB_IC_CARD.MODIFIED_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public Long getModifiedTime() {
		return modifiedTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.MODIFIED_TIME
	 * @param modifiedTime  the value for TB_IC_CARD.MODIFIED_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setModifiedTime(Long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldModifiedTime() {
		return "MODIFIED_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.BALANCE_MODIFIED_TIME
	 * @return  the value of TB_IC_CARD.BALANCE_MODIFIED_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public Long getBalanceModifiedTime() {
		return balanceModifiedTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.BALANCE_MODIFIED_TIME
	 * @param balanceModifiedTime  the value for TB_IC_CARD.BALANCE_MODIFIED_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setBalanceModifiedTime(Long balanceModifiedTime) {
		this.balanceModifiedTime = balanceModifiedTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldBalanceModifiedTime() {
		return "BALANCE_MODIFIED_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.CTRDITUSER
	 * @return  the value of TB_IC_CARD.CTRDITUSER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getCtrdituser() {
		return ctrdituser;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.CTRDITUSER
	 * @param ctrdituser  the value for TB_IC_CARD.CTRDITUSER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setCtrdituser(String ctrdituser) {
		this.ctrdituser = ctrdituser;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldCtrdituser() {
		return "CTRDITUSER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.MODIFINGUSER
	 * @return  the value of TB_IC_CARD.MODIFINGUSER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getModifinguser() {
		return modifinguser;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.MODIFINGUSER
	 * @param modifinguser  the value for TB_IC_CARD.MODIFINGUSER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setModifinguser(String modifinguser) {
		this.modifinguser = modifinguser;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldModifinguser() {
		return "MODIFINGUSER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.MESSAGE_OR_NOT
	 * @return  the value of TB_IC_CARD.MESSAGE_OR_NOT
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getMessageOrNot() {
		return messageOrNot;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.MESSAGE_OR_NOT
	 * @param messageOrNot  the value for TB_IC_CARD.MESSAGE_OR_NOT
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setMessageOrNot(String messageOrNot) {
		this.messageOrNot = messageOrNot;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldMessageOrNot() {
		return "MESSAGE_OR_NOT";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.TELEPHONE_NUMBER
	 * @return  the value of TB_IC_CARD.TELEPHONE_NUMBER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.TELEPHONE_NUMBER
	 * @param telephoneNumber  the value for TB_IC_CARD.TELEPHONE_NUMBER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldTelephoneNumber() {
		return "TELEPHONE_NUMBER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.USER_ID
	 * @return  the value of TB_IC_CARD.USER_ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.USER_ID
	 * @param userId  the value for TB_IC_CARD.USER_ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldUserId() {
		return "USER_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.VEHICLE_ID
	 * @return  the value of TB_IC_CARD.VEHICLE_ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.VEHICLE_ID
	 * @param vehicleId  the value for TB_IC_CARD.VEHICLE_ID
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldVehicleId() {
		return "VEHICLE_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.USER_TYPE
	 * @return  the value of TB_IC_CARD.USER_TYPE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.USER_TYPE
	 * @param userType  the value for TB_IC_CARD.USER_TYPE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldUserType() {
		return "USER_TYPE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.USER_NAME
	 * @return  the value of TB_IC_CARD.USER_NAME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.USER_NAME
	 * @param userName  the value for TB_IC_CARD.USER_NAME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldUserName() {
		return "USER_NAME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.USER_REG_PHONE
	 * @return  the value of TB_IC_CARD.USER_REG_PHONE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getUserRegPhone() {
		return userRegPhone;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.USER_REG_PHONE
	 * @param userRegPhone  the value for TB_IC_CARD.USER_REG_PHONE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setUserRegPhone(String userRegPhone) {
		this.userRegPhone = userRegPhone;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldUserRegPhone() {
		return "USER_REG_PHONE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.VEHICLE_NO
	 * @return  the value of TB_IC_CARD.VEHICLE_NO
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.VEHICLE_NO
	 * @param vehicleNo  the value for TB_IC_CARD.VEHICLE_NO
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldVehicleNo() {
		return "VEHICLE_NO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.VEHICLE_COLOR
	 * @return  the value of TB_IC_CARD.VEHICLE_COLOR
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getVehicleColor() {
		return vehicleColor;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.VEHICLE_COLOR
	 * @param vehicleColor  the value for TB_IC_CARD.VEHICLE_COLOR
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldVehicleColor() {
		return "VEHICLE_COLOR";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.STATE
	 * @return  the value of TB_IC_CARD.STATE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getState() {
		return state;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.STATE
	 * @param state  the value for TB_IC_CARD.STATE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldState() {
		return "STATE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.DATA_SOURCE
	 * @return  the value of TB_IC_CARD.DATA_SOURCE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.DATA_SOURCE
	 * @param dataSource  the value for TB_IC_CARD.DATA_SOURCE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldDataSource() {
		return "DATA_SOURCE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.CARD_TYPE
	 * @return  the value of TB_IC_CARD.CARD_TYPE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.CARD_TYPE
	 * @param cardType  the value for TB_IC_CARD.CARD_TYPE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldCardType() {
		return "CARD_TYPE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.OWN_ORG
	 * @return  the value of TB_IC_CARD.OWN_ORG
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getOwnOrg() {
		return ownOrg;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.OWN_ORG
	 * @param ownOrg  the value for TB_IC_CARD.OWN_ORG
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setOwnOrg(String ownOrg) {
		this.ownOrg = ownOrg;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldOwnOrg() {
		return "OWN_ORG";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.VEHICLE_LICENSE
	 * @return  the value of TB_IC_CARD.VEHICLE_LICENSE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getVehicleLicense() {
		return vehicleLicense;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.VEHICLE_LICENSE
	 * @param vehicleLicense  the value for TB_IC_CARD.VEHICLE_LICENSE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setVehicleLicense(String vehicleLicense) {
		this.vehicleLicense = vehicleLicense;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldVehicleLicense() {
		return "VEHICLE_LICENSE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.CARD_AREA_CODE
	 * @return  the value of TB_IC_CARD.CARD_AREA_CODE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getCardAreaCode() {
		return cardAreaCode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.CARD_AREA_CODE
	 * @param cardAreaCode  the value for TB_IC_CARD.CARD_AREA_CODE
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setCardAreaCode(String cardAreaCode) {
		this.cardAreaCode = cardAreaCode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldCardAreaCode() {
		return "CARD_AREA_CODE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.CARD_AREA_NAME
	 * @return  the value of TB_IC_CARD.CARD_AREA_NAME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getCardAreaName() {
		return cardAreaName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.CARD_AREA_NAME
	 * @param cardAreaName  the value for TB_IC_CARD.CARD_AREA_NAME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setCardAreaName(String cardAreaName) {
		this.cardAreaName = cardAreaName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldCardAreaName() {
		return "CARD_AREA_NAME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.PARENT_CARD_NUMBER
	 * @return  the value of TB_IC_CARD.PARENT_CARD_NUMBER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public String getParentCardNumber() {
		return parentCardNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.PARENT_CARD_NUMBER
	 * @param parentCardNumber  the value for TB_IC_CARD.PARENT_CARD_NUMBER
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setParentCardNumber(String parentCardNumber) {
		this.parentCardNumber = parentCardNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldParentCardNumber() {
		return "PARENT_CARD_NUMBER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD.OPEN_CARD_TIME
	 * @return  the value of TB_IC_CARD.OPEN_CARD_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public Long getOpenCardTime() {
		return openCardTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD.OPEN_CARD_TIME
	 * @param openCardTime  the value for TB_IC_CARD.OPEN_CARD_TIME
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public void setOpenCardTime(Long openCardTime) {
		this.openCardTime = openCardTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String fieldOpenCardTime() {
		return "OPEN_CARD_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public ICCard() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String tableName() {
		return "TB_IC_CARD";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD
	 * @abatorgenerated  Tue Feb 03 19:07:43 CST 2015
	 */
	public static String daoInterface() {
		return "com.ctfo.yppt.baseservice.dao.card.ICCardDAO";
	}
}