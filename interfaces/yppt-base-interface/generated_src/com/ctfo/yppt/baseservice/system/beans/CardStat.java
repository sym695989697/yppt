package com.ctfo.yppt.baseservice.system.beans;

import com.ctfo.common.models.BaseSerializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class CardStat extends BaseSerializable {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.ID DB Comment: 唯一标识
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "唯一标识", required = false)
	private String id;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.CARD_ID DB Comment: 卡id
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "卡id", required = false)
	private String cardId;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.COMSUME_MONEY DB Comment: 历史消费金额(元/100)
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "历史消费金额(元/100)", required = false)
	private BigDecimal comsumeMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.COMSUME_ALL_MONEY DB Comment: 历史消费金额(包括加油和其他消费)(元/100)
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "历史消费金额(包括加油和其他消费)(元/100)", required = false)
	private BigDecimal comsumeAllMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.OIL_NUM DB Comment: 加油数量(升/100)
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "加油数量(升/100)", required = false)
	private BigDecimal oilNum;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_MONEY DB Comment: 上月加油金额
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "上月加油金额", required = false)
	private BigDecimal lastMonthComsumeMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.LAST_MONTH_OIL_NUM DB Comment: 上月加油数量(升/100)
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "上月加油数量(升/100)", required = false)
	private BigDecimal lastMonthOilNum;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_ALL_MONEY DB Comment: 上月加油金额(包括加油和其他消费)(元/100)
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "上月加油金额(包括加油和其他消费)(元/100)", required = false)
	private BigDecimal lastMonthComsumeAllMoney;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column TS_CARD_COMSUME_STAT.UPDATE_TIME DB Comment: 更新时间
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	@XmlElement(name = "更新时间", required = false)
	private Long updateTime;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.ID
	 * @return  the value of TS_CARD_COMSUME_STAT.ID
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.ID
	 * @param id  the value for TS_CARD_COMSUME_STAT.ID
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldId() {
		return "ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.CARD_ID
	 * @return  the value of TS_CARD_COMSUME_STAT.CARD_ID
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.CARD_ID
	 * @param cardId  the value for TS_CARD_COMSUME_STAT.CARD_ID
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldCardId() {
		return "CARD_ID";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.COMSUME_MONEY
	 * @return  the value of TS_CARD_COMSUME_STAT.COMSUME_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public BigDecimal getComsumeMoney() {
		return comsumeMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.COMSUME_MONEY
	 * @param comsumeMoney  the value for TS_CARD_COMSUME_STAT.COMSUME_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setComsumeMoney(BigDecimal comsumeMoney) {
		this.comsumeMoney = comsumeMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldComsumeMoney() {
		return "COMSUME_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.COMSUME_ALL_MONEY
	 * @return  the value of TS_CARD_COMSUME_STAT.COMSUME_ALL_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public BigDecimal getComsumeAllMoney() {
		return comsumeAllMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.COMSUME_ALL_MONEY
	 * @param comsumeAllMoney  the value for TS_CARD_COMSUME_STAT.COMSUME_ALL_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setComsumeAllMoney(BigDecimal comsumeAllMoney) {
		this.comsumeAllMoney = comsumeAllMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldComsumeAllMoney() {
		return "COMSUME_ALL_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.OIL_NUM
	 * @return  the value of TS_CARD_COMSUME_STAT.OIL_NUM
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public BigDecimal getOilNum() {
		return oilNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.OIL_NUM
	 * @param oilNum  the value for TS_CARD_COMSUME_STAT.OIL_NUM
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setOilNum(BigDecimal oilNum) {
		this.oilNum = oilNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldOilNum() {
		return "OIL_NUM";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_MONEY
	 * @return  the value of TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public BigDecimal getLastMonthComsumeMoney() {
		return lastMonthComsumeMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_MONEY
	 * @param lastMonthComsumeMoney  the value for TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setLastMonthComsumeMoney(BigDecimal lastMonthComsumeMoney) {
		this.lastMonthComsumeMoney = lastMonthComsumeMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldLastMonthComsumeMoney() {
		return "LAST_MONTH_COMSUME_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.LAST_MONTH_OIL_NUM
	 * @return  the value of TS_CARD_COMSUME_STAT.LAST_MONTH_OIL_NUM
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public BigDecimal getLastMonthOilNum() {
		return lastMonthOilNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.LAST_MONTH_OIL_NUM
	 * @param lastMonthOilNum  the value for TS_CARD_COMSUME_STAT.LAST_MONTH_OIL_NUM
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setLastMonthOilNum(BigDecimal lastMonthOilNum) {
		this.lastMonthOilNum = lastMonthOilNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldLastMonthOilNum() {
		return "LAST_MONTH_OIL_NUM";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_ALL_MONEY
	 * @return  the value of TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_ALL_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public BigDecimal getLastMonthComsumeAllMoney() {
		return lastMonthComsumeAllMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_ALL_MONEY
	 * @param lastMonthComsumeAllMoney  the value for TS_CARD_COMSUME_STAT.LAST_MONTH_COMSUME_ALL_MONEY
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setLastMonthComsumeAllMoney(BigDecimal lastMonthComsumeAllMoney) {
		this.lastMonthComsumeAllMoney = lastMonthComsumeAllMoney;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldLastMonthComsumeAllMoney() {
		return "LAST_MONTH_COMSUME_ALL_MONEY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column TS_CARD_COMSUME_STAT.UPDATE_TIME
	 * @return  the value of TS_CARD_COMSUME_STAT.UPDATE_TIME
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column TS_CARD_COMSUME_STAT.UPDATE_TIME
	 * @param updateTime  the value for TS_CARD_COMSUME_STAT.UPDATE_TIME
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String fieldUpdateTime() {
		return "UPDATE_TIME";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public CardStat() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String tableName() {
		return "TS_CARD_COMSUME_STAT";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	public static String daoInterface() {
		return "com.ctfo.yppt.baseservice.dao.system.CardStatDAO";
	}
}