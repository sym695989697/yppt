package com.ctfo.common.models;

import java.io.Serializable;

public class Rule implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 字段名
	 */
	private String field;
	/**
	 * 条件
	 */
	private String op;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * or and 
	 */
	private String ruleOp;
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRuleOp() {
		return ruleOp;
	}

	public void setRuleOp(String ruleOp) {
		this.ruleOp = ruleOp;
	}

	
	
	

}
