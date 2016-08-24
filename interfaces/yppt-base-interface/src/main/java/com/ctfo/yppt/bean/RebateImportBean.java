package com.ctfo.yppt.bean;

import java.io.Serializable;


/**
 * 人工返利导入记录bean
 * @author zhujianbo
 *
 */
public class RebateImportBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String registerMobile;//注册手机号
	private String ownerId;//所属会员UAAUserId
	private String ownerName;//所属会员名称
	private String grantAreaCode;//发卡地区    码表
	private String importYear;//年份
	private String importMonth;//月份
	private String consumeMoney;//消费金额
	private String currencyCount;//返利旺金币数量
	private String changeMoney;//可兑换金额
	private String status;//旺金币结算状态 0：未结算 1：已结算
	private Long createTime;//操作时间
	private String operatorId;//操作人ID
	private String remark;//描述信息 冗余
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegisterMobile() {
		return registerMobile;
	}
	public void setRegisterMobile(String registerMobile) {
		this.registerMobile = registerMobile;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getGrantAreaCode() {
		return grantAreaCode;
	}
	public void setGrantAreaCode(String grantAreaCode) {
		this.grantAreaCode = grantAreaCode;
	}
	public String getImportYear() {
		return importYear;
	}
	public void setImportYear(String importYear) {
		this.importYear = importYear;
	}
	public String getImportMonth() {
		return importMonth;
	}
	public void setImportMonth(String importMonth) {
		this.importMonth = importMonth;
	}
	public String getConsumeMoney() {
		return consumeMoney;
	}
	public void setConsumeMoney(String consumeMoney) {
		this.consumeMoney = consumeMoney;
	}
	public String getCurrencyCount() {
		return currencyCount;
	}
	public void setCurrencyCount(String currencyCount) {
		this.currencyCount = currencyCount;
	}
	public String getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
