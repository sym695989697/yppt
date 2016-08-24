package com.ctfo.yppt.baseservice.system.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ICCardAccountConfigExampleExtended implements Serializable {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected List oredCriteria;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected String selectedField;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected int skipNum;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected int endNum;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected int limitNum;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public ICCardAccountConfigExampleExtended() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected ICCardAccountConfigExampleExtended(
			ICCardAccountConfigExampleExtended example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void setSelectedField(String selectedField) {
		this.selectedField = selectedField;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public String getSelectedField() {
		return selectedField;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public static String tableName() {
		return "TB_IC_CARD_ACCOUNT_CONFIG";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public static String daoInterface() {
		return "com.ctfo.yppt.baseservice.dao.system.ICCardAccountConfigDAO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void setSkipNum(int skipNum) {
		this.skipNum = skipNum;
		this.endNum = this.skipNum + this.limitNum + 1;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public int getSkipNum() {
		return this.skipNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
		this.limitNum = this.endNum - this.skipNum - 1;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public int getEndNum() {
		return this.endNum;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
		this.endNum = this.skipNum + this.limitNum + 1;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public int getLimitNum() {
		return this.limitNum;
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table TB_IC_CARD_ACCOUNT_CONFIG
	 * @abatorgenerated  Sat Jan 31 18:49:20 CST 2015
	 */
	public static class Criteria implements Serializable {
		protected List criteriaWithoutValue;
		protected List criteriaWithSingleValue;
		protected List criteriaWithListValue;
		protected List criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList();
			criteriaWithSingleValue = new ArrayList();
			criteriaWithListValue = new ArrayList();
			criteriaWithBetweenValue = new ArrayList();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List values,
				String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("ID =", value, "id");
			return this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("ID <>", value, "id");
			return this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("ID >", value, "id");
			return this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("ID >=", value, "id");
			return this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("ID <", value, "id");
			return this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("ID <=", value, "id");
			return this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("ID like", value, "id");
			return this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("ID not like", value, "id");
			return this;
		}

		public Criteria andIdIn(List values) {
			addCriterion("ID in", values, "id");
			return this;
		}

		public Criteria andIdNotIn(List values) {
			addCriterion("ID not in", values, "id");
			return this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("ID between", value1, value2, "id");
			return this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("ID not between", value1, value2, "id");
			return this;
		}

		public Criteria andUsenameIsNull() {
			addCriterion("USENAME is null");
			return this;
		}

		public Criteria andUsenameIsNotNull() {
			addCriterion("USENAME is not null");
			return this;
		}

		public Criteria andUsenameEqualTo(String value) {
			addCriterion("USENAME =", value, "usename");
			return this;
		}

		public Criteria andUsenameNotEqualTo(String value) {
			addCriterion("USENAME <>", value, "usename");
			return this;
		}

		public Criteria andUsenameGreaterThan(String value) {
			addCriterion("USENAME >", value, "usename");
			return this;
		}

		public Criteria andUsenameGreaterThanOrEqualTo(String value) {
			addCriterion("USENAME >=", value, "usename");
			return this;
		}

		public Criteria andUsenameLessThan(String value) {
			addCriterion("USENAME <", value, "usename");
			return this;
		}

		public Criteria andUsenameLessThanOrEqualTo(String value) {
			addCriterion("USENAME <=", value, "usename");
			return this;
		}

		public Criteria andUsenameLike(String value) {
			addCriterion("USENAME like", value, "usename");
			return this;
		}

		public Criteria andUsenameNotLike(String value) {
			addCriterion("USENAME not like", value, "usename");
			return this;
		}

		public Criteria andUsenameIn(List values) {
			addCriterion("USENAME in", values, "usename");
			return this;
		}

		public Criteria andUsenameNotIn(List values) {
			addCriterion("USENAME not in", values, "usename");
			return this;
		}

		public Criteria andUsenameBetween(String value1, String value2) {
			addCriterion("USENAME between", value1, value2, "usename");
			return this;
		}

		public Criteria andUsenameNotBetween(String value1, String value2) {
			addCriterion("USENAME not between", value1, value2, "usename");
			return this;
		}

		public Criteria andPasswordIsNull() {
			addCriterion("PASSWORD is null");
			return this;
		}

		public Criteria andPasswordIsNotNull() {
			addCriterion("PASSWORD is not null");
			return this;
		}

		public Criteria andPasswordEqualTo(String value) {
			addCriterion("PASSWORD =", value, "password");
			return this;
		}

		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("PASSWORD <>", value, "password");
			return this;
		}

		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("PASSWORD >", value, "password");
			return this;
		}

		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("PASSWORD >=", value, "password");
			return this;
		}

		public Criteria andPasswordLessThan(String value) {
			addCriterion("PASSWORD <", value, "password");
			return this;
		}

		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("PASSWORD <=", value, "password");
			return this;
		}

		public Criteria andPasswordLike(String value) {
			addCriterion("PASSWORD like", value, "password");
			return this;
		}

		public Criteria andPasswordNotLike(String value) {
			addCriterion("PASSWORD not like", value, "password");
			return this;
		}

		public Criteria andPasswordIn(List values) {
			addCriterion("PASSWORD in", values, "password");
			return this;
		}

		public Criteria andPasswordNotIn(List values) {
			addCriterion("PASSWORD not in", values, "password");
			return this;
		}

		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("PASSWORD between", value1, value2, "password");
			return this;
		}

		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("PASSWORD not between", value1, value2, "password");
			return this;
		}

		public Criteria andAccounttypeIsNull() {
			addCriterion("ACCOUNTTYPE is null");
			return this;
		}

		public Criteria andAccounttypeIsNotNull() {
			addCriterion("ACCOUNTTYPE is not null");
			return this;
		}

		public Criteria andAccounttypeEqualTo(String value) {
			addCriterion("ACCOUNTTYPE =", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeNotEqualTo(String value) {
			addCriterion("ACCOUNTTYPE <>", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeGreaterThan(String value) {
			addCriterion("ACCOUNTTYPE >", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeGreaterThanOrEqualTo(String value) {
			addCriterion("ACCOUNTTYPE >=", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeLessThan(String value) {
			addCriterion("ACCOUNTTYPE <", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeLessThanOrEqualTo(String value) {
			addCriterion("ACCOUNTTYPE <=", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeLike(String value) {
			addCriterion("ACCOUNTTYPE like", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeNotLike(String value) {
			addCriterion("ACCOUNTTYPE not like", value, "accounttype");
			return this;
		}

		public Criteria andAccounttypeIn(List values) {
			addCriterion("ACCOUNTTYPE in", values, "accounttype");
			return this;
		}

		public Criteria andAccounttypeNotIn(List values) {
			addCriterion("ACCOUNTTYPE not in", values, "accounttype");
			return this;
		}

		public Criteria andAccounttypeBetween(String value1, String value2) {
			addCriterion("ACCOUNTTYPE between", value1, value2, "accounttype");
			return this;
		}

		public Criteria andAccounttypeNotBetween(String value1, String value2) {
			addCriterion("ACCOUNTTYPE not between", value1, value2,
					"accounttype");
			return this;
		}

		public Criteria andRemarkIsNull() {
			addCriterion("REMARK is null");
			return this;
		}

		public Criteria andRemarkIsNotNull() {
			addCriterion("REMARK is not null");
			return this;
		}

		public Criteria andRemarkEqualTo(String value) {
			addCriterion("REMARK =", value, "remark");
			return this;
		}

		public Criteria andRemarkNotEqualTo(String value) {
			addCriterion("REMARK <>", value, "remark");
			return this;
		}

		public Criteria andRemarkGreaterThan(String value) {
			addCriterion("REMARK >", value, "remark");
			return this;
		}

		public Criteria andRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("REMARK >=", value, "remark");
			return this;
		}

		public Criteria andRemarkLessThan(String value) {
			addCriterion("REMARK <", value, "remark");
			return this;
		}

		public Criteria andRemarkLessThanOrEqualTo(String value) {
			addCriterion("REMARK <=", value, "remark");
			return this;
		}

		public Criteria andRemarkLike(String value) {
			addCriterion("REMARK like", value, "remark");
			return this;
		}

		public Criteria andRemarkNotLike(String value) {
			addCriterion("REMARK not like", value, "remark");
			return this;
		}

		public Criteria andRemarkIn(List values) {
			addCriterion("REMARK in", values, "remark");
			return this;
		}

		public Criteria andRemarkNotIn(List values) {
			addCriterion("REMARK not in", values, "remark");
			return this;
		}

		public Criteria andRemarkBetween(String value1, String value2) {
			addCriterion("REMARK between", value1, value2, "remark");
			return this;
		}

		public Criteria andRemarkNotBetween(String value1, String value2) {
			addCriterion("REMARK not between", value1, value2, "remark");
			return this;
		}

		public Criteria andCreatTimeIsNull() {
			addCriterion("CREAT_TIME is null");
			return this;
		}

		public Criteria andCreatTimeIsNotNull() {
			addCriterion("CREAT_TIME is not null");
			return this;
		}

		public Criteria andCreatTimeEqualTo(Long value) {
			addCriterion("CREAT_TIME =", value, "creatTime");
			return this;
		}

		public Criteria andCreatTimeNotEqualTo(Long value) {
			addCriterion("CREAT_TIME <>", value, "creatTime");
			return this;
		}

		public Criteria andCreatTimeGreaterThan(Long value) {
			addCriterion("CREAT_TIME >", value, "creatTime");
			return this;
		}

		public Criteria andCreatTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("CREAT_TIME >=", value, "creatTime");
			return this;
		}

		public Criteria andCreatTimeLessThan(Long value) {
			addCriterion("CREAT_TIME <", value, "creatTime");
			return this;
		}

		public Criteria andCreatTimeLessThanOrEqualTo(Long value) {
			addCriterion("CREAT_TIME <=", value, "creatTime");
			return this;
		}

		public Criteria andCreatTimeIn(List values) {
			addCriterion("CREAT_TIME in", values, "creatTime");
			return this;
		}

		public Criteria andCreatTimeNotIn(List values) {
			addCriterion("CREAT_TIME not in", values, "creatTime");
			return this;
		}

		public Criteria andCreatTimeBetween(Long value1, Long value2) {
			addCriterion("CREAT_TIME between", value1, value2, "creatTime");
			return this;
		}

		public Criteria andCreatTimeNotBetween(Long value1, Long value2) {
			addCriterion("CREAT_TIME not between", value1, value2, "creatTime");
			return this;
		}

		public Criteria andModifiedTimeIsNull() {
			addCriterion("MODIFIED_TIME is null");
			return this;
		}

		public Criteria andModifiedTimeIsNotNull() {
			addCriterion("MODIFIED_TIME is not null");
			return this;
		}

		public Criteria andModifiedTimeEqualTo(Long value) {
			addCriterion("MODIFIED_TIME =", value, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeNotEqualTo(Long value) {
			addCriterion("MODIFIED_TIME <>", value, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeGreaterThan(Long value) {
			addCriterion("MODIFIED_TIME >", value, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("MODIFIED_TIME >=", value, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeLessThan(Long value) {
			addCriterion("MODIFIED_TIME <", value, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeLessThanOrEqualTo(Long value) {
			addCriterion("MODIFIED_TIME <=", value, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeIn(List values) {
			addCriterion("MODIFIED_TIME in", values, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeNotIn(List values) {
			addCriterion("MODIFIED_TIME not in", values, "modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeBetween(Long value1, Long value2) {
			addCriterion("MODIFIED_TIME between", value1, value2,
					"modifiedTime");
			return this;
		}

		public Criteria andModifiedTimeNotBetween(Long value1, Long value2) {
			addCriterion("MODIFIED_TIME not between", value1, value2,
					"modifiedTime");
			return this;
		}

		public Criteria andMobileIsNull() {
			addCriterion("MOBILE is null");
			return this;
		}

		public Criteria andMobileIsNotNull() {
			addCriterion("MOBILE is not null");
			return this;
		}

		public Criteria andMobileEqualTo(String value) {
			addCriterion("MOBILE =", value, "mobile");
			return this;
		}

		public Criteria andMobileNotEqualTo(String value) {
			addCriterion("MOBILE <>", value, "mobile");
			return this;
		}

		public Criteria andMobileGreaterThan(String value) {
			addCriterion("MOBILE >", value, "mobile");
			return this;
		}

		public Criteria andMobileGreaterThanOrEqualTo(String value) {
			addCriterion("MOBILE >=", value, "mobile");
			return this;
		}

		public Criteria andMobileLessThan(String value) {
			addCriterion("MOBILE <", value, "mobile");
			return this;
		}

		public Criteria andMobileLessThanOrEqualTo(String value) {
			addCriterion("MOBILE <=", value, "mobile");
			return this;
		}

		public Criteria andMobileLike(String value) {
			addCriterion("MOBILE like", value, "mobile");
			return this;
		}

		public Criteria andMobileNotLike(String value) {
			addCriterion("MOBILE not like", value, "mobile");
			return this;
		}

		public Criteria andMobileIn(List values) {
			addCriterion("MOBILE in", values, "mobile");
			return this;
		}

		public Criteria andMobileNotIn(List values) {
			addCriterion("MOBILE not in", values, "mobile");
			return this;
		}

		public Criteria andMobileBetween(String value1, String value2) {
			addCriterion("MOBILE between", value1, value2, "mobile");
			return this;
		}

		public Criteria andMobileNotBetween(String value1, String value2) {
			addCriterion("MOBILE not between", value1, value2, "mobile");
			return this;
		}

		public Criteria andLastLoginTimeIsNull() {
			addCriterion("LAST_LOGIN_TIME is null");
			return this;
		}

		public Criteria andLastLoginTimeIsNotNull() {
			addCriterion("LAST_LOGIN_TIME is not null");
			return this;
		}

		public Criteria andLastLoginTimeEqualTo(Long value) {
			addCriterion("LAST_LOGIN_TIME =", value, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeNotEqualTo(Long value) {
			addCriterion("LAST_LOGIN_TIME <>", value, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeGreaterThan(Long value) {
			addCriterion("LAST_LOGIN_TIME >", value, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("LAST_LOGIN_TIME >=", value, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeLessThan(Long value) {
			addCriterion("LAST_LOGIN_TIME <", value, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeLessThanOrEqualTo(Long value) {
			addCriterion("LAST_LOGIN_TIME <=", value, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeIn(List values) {
			addCriterion("LAST_LOGIN_TIME in", values, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeNotIn(List values) {
			addCriterion("LAST_LOGIN_TIME not in", values, "lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeBetween(Long value1, Long value2) {
			addCriterion("LAST_LOGIN_TIME between", value1, value2,
					"lastLoginTime");
			return this;
		}

		public Criteria andLastLoginTimeNotBetween(Long value1, Long value2) {
			addCriterion("LAST_LOGIN_TIME not between", value1, value2,
					"lastLoginTime");
			return this;
		}

		public Criteria andSyncMainCardTimeIsNull() {
			addCriterion("SYNC_MAIN_CARD_TIME is null");
			return this;
		}

		public Criteria andSyncMainCardTimeIsNotNull() {
			addCriterion("SYNC_MAIN_CARD_TIME is not null");
			return this;
		}

		public Criteria andSyncMainCardTimeEqualTo(Long value) {
			addCriterion("SYNC_MAIN_CARD_TIME =", value, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeNotEqualTo(Long value) {
			addCriterion("SYNC_MAIN_CARD_TIME <>", value, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeGreaterThan(Long value) {
			addCriterion("SYNC_MAIN_CARD_TIME >", value, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("SYNC_MAIN_CARD_TIME >=", value, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeLessThan(Long value) {
			addCriterion("SYNC_MAIN_CARD_TIME <", value, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeLessThanOrEqualTo(Long value) {
			addCriterion("SYNC_MAIN_CARD_TIME <=", value, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeIn(List values) {
			addCriterion("SYNC_MAIN_CARD_TIME in", values, "syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeNotIn(List values) {
			addCriterion("SYNC_MAIN_CARD_TIME not in", values,
					"syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeBetween(Long value1, Long value2) {
			addCriterion("SYNC_MAIN_CARD_TIME between", value1, value2,
					"syncMainCardTime");
			return this;
		}

		public Criteria andSyncMainCardTimeNotBetween(Long value1, Long value2) {
			addCriterion("SYNC_MAIN_CARD_TIME not between", value1, value2,
					"syncMainCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeIsNull() {
			addCriterion("SYNC_SUB_CARD_TIME is null");
			return this;
		}

		public Criteria andSyncSubCardTimeIsNotNull() {
			addCriterion("SYNC_SUB_CARD_TIME is not null");
			return this;
		}

		public Criteria andSyncSubCardTimeEqualTo(Long value) {
			addCriterion("SYNC_SUB_CARD_TIME =", value, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeNotEqualTo(Long value) {
			addCriterion("SYNC_SUB_CARD_TIME <>", value, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeGreaterThan(Long value) {
			addCriterion("SYNC_SUB_CARD_TIME >", value, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("SYNC_SUB_CARD_TIME >=", value, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeLessThan(Long value) {
			addCriterion("SYNC_SUB_CARD_TIME <", value, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeLessThanOrEqualTo(Long value) {
			addCriterion("SYNC_SUB_CARD_TIME <=", value, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeIn(List values) {
			addCriterion("SYNC_SUB_CARD_TIME in", values, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeNotIn(List values) {
			addCriterion("SYNC_SUB_CARD_TIME not in", values, "syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeBetween(Long value1, Long value2) {
			addCriterion("SYNC_SUB_CARD_TIME between", value1, value2,
					"syncSubCardTime");
			return this;
		}

		public Criteria andSyncSubCardTimeNotBetween(Long value1, Long value2) {
			addCriterion("SYNC_SUB_CARD_TIME not between", value1, value2,
					"syncSubCardTime");
			return this;
		}

		public Criteria andMainCardTradeIsNull() {
			addCriterion("MAIN_CARD_TRADE is null");
			return this;
		}

		public Criteria andMainCardTradeIsNotNull() {
			addCriterion("MAIN_CARD_TRADE is not null");
			return this;
		}

		public Criteria andMainCardTradeEqualTo(Long value) {
			addCriterion("MAIN_CARD_TRADE =", value, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeNotEqualTo(Long value) {
			addCriterion("MAIN_CARD_TRADE <>", value, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeGreaterThan(Long value) {
			addCriterion("MAIN_CARD_TRADE >", value, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeGreaterThanOrEqualTo(Long value) {
			addCriterion("MAIN_CARD_TRADE >=", value, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeLessThan(Long value) {
			addCriterion("MAIN_CARD_TRADE <", value, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeLessThanOrEqualTo(Long value) {
			addCriterion("MAIN_CARD_TRADE <=", value, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeIn(List values) {
			addCriterion("MAIN_CARD_TRADE in", values, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeNotIn(List values) {
			addCriterion("MAIN_CARD_TRADE not in", values, "mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeBetween(Long value1, Long value2) {
			addCriterion("MAIN_CARD_TRADE between", value1, value2,
					"mainCardTrade");
			return this;
		}

		public Criteria andMainCardTradeNotBetween(Long value1, Long value2) {
			addCriterion("MAIN_CARD_TRADE not between", value1, value2,
					"mainCardTrade");
			return this;
		}

		public Criteria andSubCardTradeTimeIsNull() {
			addCriterion("SUB_CARD_TRADE_TIME is null");
			return this;
		}

		public Criteria andSubCardTradeTimeIsNotNull() {
			addCriterion("SUB_CARD_TRADE_TIME is not null");
			return this;
		}

		public Criteria andSubCardTradeTimeEqualTo(Long value) {
			addCriterion("SUB_CARD_TRADE_TIME =", value, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeNotEqualTo(Long value) {
			addCriterion("SUB_CARD_TRADE_TIME <>", value, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeGreaterThan(Long value) {
			addCriterion("SUB_CARD_TRADE_TIME >", value, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeGreaterThanOrEqualTo(Long value) {
			addCriterion("SUB_CARD_TRADE_TIME >=", value, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeLessThan(Long value) {
			addCriterion("SUB_CARD_TRADE_TIME <", value, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeLessThanOrEqualTo(Long value) {
			addCriterion("SUB_CARD_TRADE_TIME <=", value, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeIn(List values) {
			addCriterion("SUB_CARD_TRADE_TIME in", values, "subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeNotIn(List values) {
			addCriterion("SUB_CARD_TRADE_TIME not in", values,
					"subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeBetween(Long value1, Long value2) {
			addCriterion("SUB_CARD_TRADE_TIME between", value1, value2,
					"subCardTradeTime");
			return this;
		}

		public Criteria andSubCardTradeTimeNotBetween(Long value1, Long value2) {
			addCriterion("SUB_CARD_TRADE_TIME not between", value1, value2,
					"subCardTradeTime");
			return this;
		}

		public Criteria andOwnOrgIsNull() {
			addCriterion("OWN_ORG is null");
			return this;
		}

		public Criteria andOwnOrgIsNotNull() {
			addCriterion("OWN_ORG is not null");
			return this;
		}

		public Criteria andOwnOrgEqualTo(String value) {
			addCriterion("OWN_ORG =", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgNotEqualTo(String value) {
			addCriterion("OWN_ORG <>", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgGreaterThan(String value) {
			addCriterion("OWN_ORG >", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgGreaterThanOrEqualTo(String value) {
			addCriterion("OWN_ORG >=", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgLessThan(String value) {
			addCriterion("OWN_ORG <", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgLessThanOrEqualTo(String value) {
			addCriterion("OWN_ORG <=", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgLike(String value) {
			addCriterion("OWN_ORG like", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgNotLike(String value) {
			addCriterion("OWN_ORG not like", value, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgIn(List values) {
			addCriterion("OWN_ORG in", values, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgNotIn(List values) {
			addCriterion("OWN_ORG not in", values, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgBetween(String value1, String value2) {
			addCriterion("OWN_ORG between", value1, value2, "ownOrg");
			return this;
		}

		public Criteria andOwnOrgNotBetween(String value1, String value2) {
			addCriterion("OWN_ORG not between", value1, value2, "ownOrg");
			return this;
		}

		public Criteria andCardAreaCodeIsNull() {
			addCriterion("CARD_AREA_CODE is null");
			return this;
		}

		public Criteria andCardAreaCodeIsNotNull() {
			addCriterion("CARD_AREA_CODE is not null");
			return this;
		}

		public Criteria andCardAreaCodeEqualTo(String value) {
			addCriterion("CARD_AREA_CODE =", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeNotEqualTo(String value) {
			addCriterion("CARD_AREA_CODE <>", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeGreaterThan(String value) {
			addCriterion("CARD_AREA_CODE >", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeGreaterThanOrEqualTo(String value) {
			addCriterion("CARD_AREA_CODE >=", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeLessThan(String value) {
			addCriterion("CARD_AREA_CODE <", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeLessThanOrEqualTo(String value) {
			addCriterion("CARD_AREA_CODE <=", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeLike(String value) {
			addCriterion("CARD_AREA_CODE like", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeNotLike(String value) {
			addCriterion("CARD_AREA_CODE not like", value, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeIn(List values) {
			addCriterion("CARD_AREA_CODE in", values, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeNotIn(List values) {
			addCriterion("CARD_AREA_CODE not in", values, "cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeBetween(String value1, String value2) {
			addCriterion("CARD_AREA_CODE between", value1, value2,
					"cardAreaCode");
			return this;
		}

		public Criteria andCardAreaCodeNotBetween(String value1, String value2) {
			addCriterion("CARD_AREA_CODE not between", value1, value2,
					"cardAreaCode");
			return this;
		}
	}
}