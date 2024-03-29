package com.ctfo.yppt.baseservice.recharge.beans;

import com.ctfo.common.models.BaseSerializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class ICRechargeApplyDetail extends BaseSerializable {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.ID DB Comment: 
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "", required = false)
	private String id;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.APPLY_ID DB Comment: 充值申请表ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "充值申请表ID", required = false)
	private String applyId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_ID DB Comment: 充值支付卡ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "充值支付卡ID", required = false)
	private String cardId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_NO DB Comment: 充值支付卡卡号
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "充值支付卡卡号", required = false)
	private String cardNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_TYPE DB Comment: 卡类型(中石油/中石化;码表)
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "卡类型(中石油/中石化;码表)", required = false)
	private String cardType;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_NO DB Comment: 车牌号
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "车牌号", required = false)
	private String vehicleNo;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_COLOR DB Comment: 车牌颜色(码表)
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "车牌颜色(码表)", required = false)
	private String vehicleColor;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.MONEY DB Comment: 充值金额（单位：分）
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "充值金额（单位：分）", required = false)
	private BigDecimal money;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.FAILE_REASON DB Comment: 失败原因
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "失败原因", required = false)
	private String faileReason;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.ACTUAL_DIV_MONEY DB Comment: 实际分配金额
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "实际分配金额", required = false)
	private BigDecimal actualDivMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.PARENT_CARD_NUMBER DB Comment: 主卡卡号
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "主卡卡号", required = false)
	private String parentCardNumber;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_AREA_CODE DB Comment: 开卡地区编码
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	@XmlElement(name = "开卡地区编码", required = false)
	private String cardAreaCode;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.ID
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.ID
	 * @param id  the value for TB_IC_RECHARGE_APPLY_DETAIL.ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldId() {
		return "ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.APPLY_ID
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.APPLY_ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getApplyId() {
		return applyId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.APPLY_ID
	 * @param applyId  the value for TB_IC_RECHARGE_APPLY_DETAIL.APPLY_ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldApplyId() {
		return "APPLY_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_ID
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.CARD_ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_ID
	 * @param cardId  the value for TB_IC_RECHARGE_APPLY_DETAIL.CARD_ID
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldCardId() {
		return "CARD_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_NO
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.CARD_NO
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_NO
	 * @param cardNo  the value for TB_IC_RECHARGE_APPLY_DETAIL.CARD_NO
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldCardNo() {
		return "CARD_NO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_TYPE
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.CARD_TYPE
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_TYPE
	 * @param cardType  the value for TB_IC_RECHARGE_APPLY_DETAIL.CARD_TYPE
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldCardType() {
		return "CARD_TYPE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_NO
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_NO
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_NO
	 * @param vehicleNo  the value for TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_NO
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldVehicleNo() {
		return "VEHICLE_NO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_COLOR
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_COLOR
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getVehicleColor() {
		return vehicleColor;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_COLOR
	 * @param vehicleColor  the value for TB_IC_RECHARGE_APPLY_DETAIL.VEHICLE_COLOR
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldVehicleColor() {
		return "VEHICLE_COLOR";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.MONEY
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.MONEY
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.MONEY
	 * @param money  the value for TB_IC_RECHARGE_APPLY_DETAIL.MONEY
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldMoney() {
		return "MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.FAILE_REASON
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.FAILE_REASON
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getFaileReason() {
		return faileReason;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.FAILE_REASON
	 * @param faileReason  the value for TB_IC_RECHARGE_APPLY_DETAIL.FAILE_REASON
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setFaileReason(String faileReason) {
		this.faileReason = faileReason;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldFaileReason() {
		return "FAILE_REASON";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.ACTUAL_DIV_MONEY
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.ACTUAL_DIV_MONEY
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public BigDecimal getActualDivMoney() {
		return actualDivMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.ACTUAL_DIV_MONEY
	 * @param actualDivMoney  the value for TB_IC_RECHARGE_APPLY_DETAIL.ACTUAL_DIV_MONEY
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setActualDivMoney(BigDecimal actualDivMoney) {
		this.actualDivMoney = actualDivMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldActualDivMoney() {
		return "ACTUAL_DIV_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.PARENT_CARD_NUMBER
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.PARENT_CARD_NUMBER
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getParentCardNumber() {
		return parentCardNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.PARENT_CARD_NUMBER
	 * @param parentCardNumber  the value for TB_IC_RECHARGE_APPLY_DETAIL.PARENT_CARD_NUMBER
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setParentCardNumber(String parentCardNumber) {
		this.parentCardNumber = parentCardNumber;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldParentCardNumber() {
		return "PARENT_CARD_NUMBER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_AREA_CODE
	 * @return  the value of TB_IC_RECHARGE_APPLY_DETAIL.CARD_AREA_CODE
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public String getCardAreaCode() {
		return cardAreaCode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_RECHARGE_APPLY_DETAIL.CARD_AREA_CODE
	 * @param cardAreaCode  the value for TB_IC_RECHARGE_APPLY_DETAIL.CARD_AREA_CODE
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public void setCardAreaCode(String cardAreaCode) {
		this.cardAreaCode = cardAreaCode;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String fieldCardAreaCode() {
		return "CARD_AREA_CODE";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public ICRechargeApplyDetail() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String tableName() {
		return "TB_IC_RECHARGE_APPLY_DETAIL";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_RECHARGE_APPLY_DETAIL
	 * @abatorgenerated  Tue Feb 03 14:39:29 CST 2015
	 */
	public static String daoInterface() {
		return "com.ctfo.yppt.baseservice.dao.recharge.ICRechargeApplyDetailDAO";
	}
}