package com.ctfo.yppt.baseservice.bill.beans;

import com.ctfo.common.models.BaseSerializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class TradeCardArea extends BaseSerializable {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.ID
     * DB Comment: 主键
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="主键", required=false )
    private String id;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.USER_ID
     * DB Comment: 会员ID
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="会员ID", required=false )
    private String userId;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.CARD_NO
     * DB Comment: 卡号
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="卡号", required=false )
    private String cardNo;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.TRADE_AREA
     * DB Comment: 消费区域
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="消费区域", required=false )
    private String tradeArea;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.CARD_TYPE
     * DB Comment: 卡类型
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="卡类型", required=false )
    private String cardType;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_MONEY_CY
     * DB Comment: 柴油消费总额
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="柴油消费总额", required=false )
    private Long sumMoneyCy;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_MONEY_QY
     * DB Comment: 汽油消费总额
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="汽油消费总额", required=false )
    private Long sumMoneyQy;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_MONEY_ORTHER
     * DB Comment: 其他消费总额
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="其他消费总额", required=false )
    private Long sumMoneyOrther;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_OIL_CY
     * DB Comment: 柴油消费总量
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="柴油消费总量", required=false )
    private BigDecimal sumOilCy;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_OIL_QY
     * DB Comment: 汽油消费总量
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="汽油消费总量", required=false )
    private BigDecimal sumOilQy;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_ORTHER
     * DB Comment: 全体消费次数
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="全体消费次数", required=false )
    private Long sumOrther;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_CY
     * DB Comment: 柴油返利总额
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="柴油返利总额", required=false )
    private Long sumMoneyRebateCy;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_QY
     * DB Comment: 汽油返利总额
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="汽油返利总额", required=false )
    private Long sumMoneyRebateQy;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.CREATE_TIME
     * DB Comment: 统计时间
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="统计时间", required=false )
    private Long createTime;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database column TS_TRADE_CARD_AREA.TRADE_MONTH
     * DB Comment: 消费月份，例如2012-10
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    @XmlElement(name="消费月份，例如2012-10", required=false )
    private String tradeMonth;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.ID
     *
     * @return the value of TS_TRADE_CARD_AREA.ID
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.ID
     *
     * @param id the value for TS_TRADE_CARD_AREA.ID
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldId() {
        return "ID";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.USER_ID
     *
     * @return the value of TS_TRADE_CARD_AREA.USER_ID
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.USER_ID
     *
     * @param userId the value for TS_TRADE_CARD_AREA.USER_ID
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldUserId() {
        return "USER_ID";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.CARD_NO
     *
     * @return the value of TS_TRADE_CARD_AREA.CARD_NO
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.CARD_NO
     *
     * @param cardNo the value for TS_TRADE_CARD_AREA.CARD_NO
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldCardNo() {
        return "CARD_NO";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.TRADE_AREA
     *
     * @return the value of TS_TRADE_CARD_AREA.TRADE_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getTradeArea() {
        return tradeArea;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.TRADE_AREA
     *
     * @param tradeArea the value for TS_TRADE_CARD_AREA.TRADE_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setTradeArea(String tradeArea) {
        this.tradeArea = tradeArea;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldTradeArea() {
        return "TRADE_AREA";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.CARD_TYPE
     *
     * @return the value of TS_TRADE_CARD_AREA.CARD_TYPE
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.CARD_TYPE
     *
     * @param cardType the value for TS_TRADE_CARD_AREA.CARD_TYPE
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldCardType() {
        return "CARD_TYPE";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_CY
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_MONEY_CY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getSumMoneyCy() {
        return sumMoneyCy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_CY
     *
     * @param sumMoneyCy the value for TS_TRADE_CARD_AREA.SUM_MONEY_CY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumMoneyCy(Long sumMoneyCy) {
        this.sumMoneyCy = sumMoneyCy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumMoneyCy() {
        return "SUM_MONEY_CY";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_QY
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_MONEY_QY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getSumMoneyQy() {
        return sumMoneyQy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_QY
     *
     * @param sumMoneyQy the value for TS_TRADE_CARD_AREA.SUM_MONEY_QY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumMoneyQy(Long sumMoneyQy) {
        this.sumMoneyQy = sumMoneyQy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumMoneyQy() {
        return "SUM_MONEY_QY";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_ORTHER
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_MONEY_ORTHER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getSumMoneyOrther() {
        return sumMoneyOrther;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_ORTHER
     *
     * @param sumMoneyOrther the value for TS_TRADE_CARD_AREA.SUM_MONEY_ORTHER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumMoneyOrther(Long sumMoneyOrther) {
        this.sumMoneyOrther = sumMoneyOrther;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumMoneyOrther() {
        return "SUM_MONEY_ORTHER";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_OIL_CY
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_OIL_CY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public BigDecimal getSumOilCy() {
        return sumOilCy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_OIL_CY
     *
     * @param sumOilCy the value for TS_TRADE_CARD_AREA.SUM_OIL_CY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumOilCy(BigDecimal sumOilCy) {
        this.sumOilCy = sumOilCy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumOilCy() {
        return "SUM_OIL_CY";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_OIL_QY
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_OIL_QY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public BigDecimal getSumOilQy() {
        return sumOilQy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_OIL_QY
     *
     * @param sumOilQy the value for TS_TRADE_CARD_AREA.SUM_OIL_QY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumOilQy(BigDecimal sumOilQy) {
        this.sumOilQy = sumOilQy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumOilQy() {
        return "SUM_OIL_QY";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_ORTHER
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_ORTHER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getSumOrther() {
        return sumOrther;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_ORTHER
     *
     * @param sumOrther the value for TS_TRADE_CARD_AREA.SUM_ORTHER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumOrther(Long sumOrther) {
        this.sumOrther = sumOrther;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumOrther() {
        return "SUM_ORTHER";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_CY
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_CY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getSumMoneyRebateCy() {
        return sumMoneyRebateCy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_CY
     *
     * @param sumMoneyRebateCy the value for TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_CY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumMoneyRebateCy(Long sumMoneyRebateCy) {
        this.sumMoneyRebateCy = sumMoneyRebateCy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumMoneyRebateCy() {
        return "SUM_MONEY_REBATE_CY";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_QY
     *
     * @return the value of TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_QY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getSumMoneyRebateQy() {
        return sumMoneyRebateQy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_QY
     *
     * @param sumMoneyRebateQy the value for TS_TRADE_CARD_AREA.SUM_MONEY_REBATE_QY
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSumMoneyRebateQy(Long sumMoneyRebateQy) {
        this.sumMoneyRebateQy = sumMoneyRebateQy;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldSumMoneyRebateQy() {
        return "SUM_MONEY_REBATE_QY";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.CREATE_TIME
     *
     * @return the value of TS_TRADE_CARD_AREA.CREATE_TIME
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.CREATE_TIME
     *
     * @param createTime the value for TS_TRADE_CARD_AREA.CREATE_TIME
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldCreateTime() {
        return "CREATE_TIME";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method returns the value of the database column TS_TRADE_CARD_AREA.TRADE_MONTH
     *
     * @return the value of TS_TRADE_CARD_AREA.TRADE_MONTH
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getTradeMonth() {
        return tradeMonth;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method sets the value of the database column TS_TRADE_CARD_AREA.TRADE_MONTH
     *
     * @param tradeMonth the value for TS_TRADE_CARD_AREA.TRADE_MONTH
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setTradeMonth(String tradeMonth) {
        this.tradeMonth = tradeMonth;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String fieldTradeMonth() {
        return "TRADE_MONTH";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public TradeCardArea() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String tableName() {
        return "TS_TRADE_CARD_AREA";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD_AREA
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String daoInterface() {
        return "com.ctfo.yppt.baseservice.dao.bill.TradeCardAreaDAO";
    }
}