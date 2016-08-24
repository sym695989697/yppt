package com.ctfo.yppt.baseservice.card.beans;

import com.ctfo.common.models.BaseSerializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

public class ICCardMain extends BaseSerializable {

    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.ID DB Comment: 主键
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "主键", required = false)
    private String id;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.CARD_NUMBER DB Comment: 卡号
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "卡号", required = false)
    private String cardNumber;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.BALANCE DB Comment: 卡余额
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "卡余额", required = false)
    private BigDecimal balance;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.OPENCARDOFFICECODE DB Comment: 发卡机构编码(中石油商户,中石化商户)
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "发卡机构编码(中石油商户,中石化商户)", required = false)
    private String opencardofficecode;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.OPENCARDOFFICENAME DB Comment: 发卡机构名称
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "发卡机构名称", required = false)
    private String opencardofficename;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.CREATED_TIME DB Comment: 创建时间
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "创建时间", required = false)
    private Long createdTime;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.MODIFIED_TIME DB Comment: 修改时间
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "修改时间", required = false)
    private Long modifiedTime;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.CTRDITUSER DB Comment: 创建人
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "创建人", required = false)
    private String ctrdituser;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.MODIFINGUSER DB Comment: 修改人
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "修改人", required = false)
    private String modifinguser;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.STATE DB Comment: 状态
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "状态", required = false)
    private String state;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.CARD_TYPE DB Comment: 卡类型(中石油,中石化卡)
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "卡类型(中石油,中石化卡)", required = false)
    private String cardType;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.OWN_ORG DB Comment: 所属组织(码表5577:'北京中交,重庆中交')
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "所属组织(码表5577:'北京中交,重庆中交')", required = false)
    private String ownOrg;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.ACCOUNT_ID DB Comment: 中石油/石化账户id(数据抓取配置表中)
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "中石油/石化账户id(数据抓取配置表中)", required = false)
    private String accountId;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.BALANCE_MODIFIED_TIME DB Comment: 卡余额变更时间
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "卡余额变更时间", required = false)
    private Long balanceModifiedTime;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.CARD_AREA_CODE DB Comment: 发卡地区编码（省份）
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "发卡地区编码（省份）", required = false)
    private String cardAreaCode;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database column TB_IC_CARD_MAIN.CARD_AREA_NAME DB Comment: 发卡地区名称（省份）
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    @XmlElement(name = "发卡地区名称（省份）", required = false)
    private String cardAreaName;
    /**
     * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.ID
     * @return  the value of TB_IC_CARD_MAIN.ID
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.ID
     * @param id  the value for TB_IC_CARD_MAIN.ID
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldId() {
        return "ID";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.CARD_NUMBER
     * @return  the value of TB_IC_CARD_MAIN.CARD_NUMBER
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.CARD_NUMBER
     * @param cardNumber  the value for TB_IC_CARD_MAIN.CARD_NUMBER
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldCardNumber() {
        return "CARD_NUMBER";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.BALANCE
     * @return  the value of TB_IC_CARD_MAIN.BALANCE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.BALANCE
     * @param balance  the value for TB_IC_CARD_MAIN.BALANCE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldBalance() {
        return "BALANCE";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.OPENCARDOFFICECODE
     * @return  the value of TB_IC_CARD_MAIN.OPENCARDOFFICECODE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getOpencardofficecode() {
        return opencardofficecode;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.OPENCARDOFFICECODE
     * @param opencardofficecode  the value for TB_IC_CARD_MAIN.OPENCARDOFFICECODE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setOpencardofficecode(String opencardofficecode) {
        this.opencardofficecode = opencardofficecode;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldOpencardofficecode() {
        return "OPENCARDOFFICECODE";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.OPENCARDOFFICENAME
     * @return  the value of TB_IC_CARD_MAIN.OPENCARDOFFICENAME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getOpencardofficename() {
        return opencardofficename;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.OPENCARDOFFICENAME
     * @param opencardofficename  the value for TB_IC_CARD_MAIN.OPENCARDOFFICENAME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setOpencardofficename(String opencardofficename) {
        this.opencardofficename = opencardofficename;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldOpencardofficename() {
        return "OPENCARDOFFICENAME";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.CREATED_TIME
     * @return  the value of TB_IC_CARD_MAIN.CREATED_TIME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.CREATED_TIME
     * @param createdTime  the value for TB_IC_CARD_MAIN.CREATED_TIME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldCreatedTime() {
        return "CREATED_TIME";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.MODIFIED_TIME
     * @return  the value of TB_IC_CARD_MAIN.MODIFIED_TIME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.MODIFIED_TIME
     * @param modifiedTime  the value for TB_IC_CARD_MAIN.MODIFIED_TIME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldModifiedTime() {
        return "MODIFIED_TIME";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.CTRDITUSER
     * @return  the value of TB_IC_CARD_MAIN.CTRDITUSER
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getCtrdituser() {
        return ctrdituser;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.CTRDITUSER
     * @param ctrdituser  the value for TB_IC_CARD_MAIN.CTRDITUSER
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setCtrdituser(String ctrdituser) {
        this.ctrdituser = ctrdituser;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldCtrdituser() {
        return "CTRDITUSER";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.MODIFINGUSER
     * @return  the value of TB_IC_CARD_MAIN.MODIFINGUSER
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getModifinguser() {
        return modifinguser;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.MODIFINGUSER
     * @param modifinguser  the value for TB_IC_CARD_MAIN.MODIFINGUSER
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setModifinguser(String modifinguser) {
        this.modifinguser = modifinguser;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldModifinguser() {
        return "MODIFINGUSER";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.STATE
     * @return  the value of TB_IC_CARD_MAIN.STATE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.STATE
     * @param state  the value for TB_IC_CARD_MAIN.STATE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldState() {
        return "STATE";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.CARD_TYPE
     * @return  the value of TB_IC_CARD_MAIN.CARD_TYPE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.CARD_TYPE
     * @param cardType  the value for TB_IC_CARD_MAIN.CARD_TYPE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldCardType() {
        return "CARD_TYPE";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.OWN_ORG
     * @return  the value of TB_IC_CARD_MAIN.OWN_ORG
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getOwnOrg() {
        return ownOrg;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.OWN_ORG
     * @param ownOrg  the value for TB_IC_CARD_MAIN.OWN_ORG
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setOwnOrg(String ownOrg) {
        this.ownOrg = ownOrg;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldOwnOrg() {
        return "OWN_ORG";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.ACCOUNT_ID
     * @return  the value of TB_IC_CARD_MAIN.ACCOUNT_ID
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.ACCOUNT_ID
     * @param accountId  the value for TB_IC_CARD_MAIN.ACCOUNT_ID
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldAccountId() {
        return "ACCOUNT_ID";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.BALANCE_MODIFIED_TIME
     * @return  the value of TB_IC_CARD_MAIN.BALANCE_MODIFIED_TIME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public Long getBalanceModifiedTime() {
        return balanceModifiedTime;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.BALANCE_MODIFIED_TIME
     * @param balanceModifiedTime  the value for TB_IC_CARD_MAIN.BALANCE_MODIFIED_TIME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setBalanceModifiedTime(Long balanceModifiedTime) {
        this.balanceModifiedTime = balanceModifiedTime;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldBalanceModifiedTime() {
        return "BALANCE_MODIFIED_TIME";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.CARD_AREA_CODE
     * @return  the value of TB_IC_CARD_MAIN.CARD_AREA_CODE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getCardAreaCode() {
        return cardAreaCode;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.CARD_AREA_CODE
     * @param cardAreaCode  the value for TB_IC_CARD_MAIN.CARD_AREA_CODE
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setCardAreaCode(String cardAreaCode) {
        this.cardAreaCode = cardAreaCode;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldCardAreaCode() {
        return "CARD_AREA_CODE";
    }

    /**
     * This method was generated by Abator for iBATIS. This method returns the value of the database column TB_IC_CARD_MAIN.CARD_AREA_NAME
     * @return  the value of TB_IC_CARD_MAIN.CARD_AREA_NAME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public String getCardAreaName() {
        return cardAreaName;
    }

    /**
     * This method was generated by Abator for iBATIS. This method sets the value of the database column TB_IC_CARD_MAIN.CARD_AREA_NAME
     * @param cardAreaName  the value for TB_IC_CARD_MAIN.CARD_AREA_NAME
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public void setCardAreaName(String cardAreaName) {
        this.cardAreaName = cardAreaName;
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String fieldCardAreaName() {
        return "CARD_AREA_NAME";
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public ICCardMain() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String tableName() {
        return "TB_IC_CARD_MAIN";
    }

    /**
     * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_MAIN
     * @abatorgenerated  Sun Jan 25 16:48:52 CST 2015
     */
    public static String daoInterface() {
        return "com.ctfo.yppt.baseservice.dao.card.ICCardMainDAO";
    }
}