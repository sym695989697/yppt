package com.ctfo.yppt.baseservice.bill.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeUserForRebateExample implements Serializable {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected List oredCriteria;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected int skipNum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected int endNum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected int limitNum;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public TradeUserForRebateExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected TradeUserForRebateExample(TradeUserForRebateExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public static String tableName() {
        return "TS_TRADE_USER_FOR_REBATE";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public static String daoInterface() {
        return "com.ctfo.yppt.baseservice.dao.bill.TradeUserForRebateDAO";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public void setSkipNum(int skipNum) {
        this.skipNum = skipNum;
        this.endNum = this.skipNum + this.limitNum +1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public int getSkipNum() {
        return this.skipNum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public void setEndNum(int endNum) {
        this.endNum = endNum;
        this.limitNum = this.endNum - this.skipNum -1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public int getEndNum() {
        return this.endNum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
        this.endNum = this.skipNum + this.limitNum +1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public int getLimitNum() {
        return this.limitNum;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table TS_TRADE_USER_FOR_REBATE
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    public static class Criteria implements Serializable {
        /**
         * This field was generated by Abator for iBATIS.
         * This field corresponds to the database table TS_TRADE_USER_FOR_REBATE
         *
         * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
         */
        private static final long serialVersionUID = 1L;

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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return this;
        }

        public Criteria andUserIdIn(List values) {
            addCriterion("USER_ID in", values, "userId");
            return this;
        }

        public Criteria andUserIdNotIn(List values) {
            addCriterion("USER_ID not in", values, "userId");
            return this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return this;
        }

        public Criteria andSumMoneyCySyIsNull() {
            addCriterion("SUM_MONEY_CY_SY is null");
            return this;
        }

        public Criteria andSumMoneyCySyIsNotNull() {
            addCriterion("SUM_MONEY_CY_SY is not null");
            return this;
        }

        public Criteria andSumMoneyCySyEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SY =", value, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyNotEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SY <>", value, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyGreaterThan(Long value) {
            addCriterion("SUM_MONEY_CY_SY >", value, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SY >=", value, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyLessThan(Long value) {
            addCriterion("SUM_MONEY_CY_SY <", value, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyLessThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SY <=", value, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyIn(List values) {
            addCriterion("SUM_MONEY_CY_SY in", values, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyNotIn(List values) {
            addCriterion("SUM_MONEY_CY_SY not in", values, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_CY_SY between", value1, value2, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyCySyNotBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_CY_SY not between", value1, value2, "sumMoneyCySy");
            return this;
        }

        public Criteria andSumMoneyQySyIsNull() {
            addCriterion("SUM_MONEY_QY_SY is null");
            return this;
        }

        public Criteria andSumMoneyQySyIsNotNull() {
            addCriterion("SUM_MONEY_QY_SY is not null");
            return this;
        }

        public Criteria andSumMoneyQySyEqualTo(Long value) {
            addCriterion("SUM_MONEY_QY_SY =", value, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyNotEqualTo(Long value) {
            addCriterion("SUM_MONEY_QY_SY <>", value, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyGreaterThan(Long value) {
            addCriterion("SUM_MONEY_QY_SY >", value, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_QY_SY >=", value, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyLessThan(Long value) {
            addCriterion("SUM_MONEY_QY_SY <", value, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyLessThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_QY_SY <=", value, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyIn(List values) {
            addCriterion("SUM_MONEY_QY_SY in", values, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyNotIn(List values) {
            addCriterion("SUM_MONEY_QY_SY not in", values, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_QY_SY between", value1, value2, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyQySyNotBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_QY_SY not between", value1, value2, "sumMoneyQySy");
            return this;
        }

        public Criteria andSumMoneyCyShIsNull() {
            addCriterion("SUM_MONEY_CY_SH is null");
            return this;
        }

        public Criteria andSumMoneyCyShIsNotNull() {
            addCriterion("SUM_MONEY_CY_SH is not null");
            return this;
        }

        public Criteria andSumMoneyCyShEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SH =", value, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShNotEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SH <>", value, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShGreaterThan(Long value) {
            addCriterion("SUM_MONEY_CY_SH >", value, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SH >=", value, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShLessThan(Long value) {
            addCriterion("SUM_MONEY_CY_SH <", value, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShLessThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_CY_SH <=", value, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShIn(List values) {
            addCriterion("SUM_MONEY_CY_SH in", values, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShNotIn(List values) {
            addCriterion("SUM_MONEY_CY_SH not in", values, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_CY_SH between", value1, value2, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyCyShNotBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_CY_SH not between", value1, value2, "sumMoneyCySh");
            return this;
        }

        public Criteria andSumMoneyQyShIsNull() {
            addCriterion("SUM_MONEY_QY_SH is null");
            return this;
        }

        public Criteria andSumMoneyQyShIsNotNull() {
            addCriterion("SUM_MONEY_QY_SH is not null");
            return this;
        }

        public Criteria andSumMoneyQyShEqualTo(BigDecimal value) {
            addCriterion("SUM_MONEY_QY_SH =", value, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShNotEqualTo(BigDecimal value) {
            addCriterion("SUM_MONEY_QY_SH <>", value, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShGreaterThan(BigDecimal value) {
            addCriterion("SUM_MONEY_QY_SH >", value, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SUM_MONEY_QY_SH >=", value, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShLessThan(BigDecimal value) {
            addCriterion("SUM_MONEY_QY_SH <", value, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SUM_MONEY_QY_SH <=", value, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShIn(List values) {
            addCriterion("SUM_MONEY_QY_SH in", values, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShNotIn(List values) {
            addCriterion("SUM_MONEY_QY_SH not in", values, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUM_MONEY_QY_SH between", value1, value2, "sumMoneyQySh");
            return this;
        }

        public Criteria andSumMoneyQyShNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SUM_MONEY_QY_SH not between", value1, value2, "sumMoneyQySh");
            return this;
        }

        public Criteria andTsYearMonthIsNull() {
            addCriterion("TS_YEAR_MONTH is null");
            return this;
        }

        public Criteria andTsYearMonthIsNotNull() {
            addCriterion("TS_YEAR_MONTH is not null");
            return this;
        }

        public Criteria andTsYearMonthEqualTo(String value) {
            addCriterion("TS_YEAR_MONTH =", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthNotEqualTo(String value) {
            addCriterion("TS_YEAR_MONTH <>", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthGreaterThan(String value) {
            addCriterion("TS_YEAR_MONTH >", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthGreaterThanOrEqualTo(String value) {
            addCriterion("TS_YEAR_MONTH >=", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthLessThan(String value) {
            addCriterion("TS_YEAR_MONTH <", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthLessThanOrEqualTo(String value) {
            addCriterion("TS_YEAR_MONTH <=", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthLike(String value) {
            addCriterion("TS_YEAR_MONTH like", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthNotLike(String value) {
            addCriterion("TS_YEAR_MONTH not like", value, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthIn(List values) {
            addCriterion("TS_YEAR_MONTH in", values, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthNotIn(List values) {
            addCriterion("TS_YEAR_MONTH not in", values, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthBetween(String value1, String value2) {
            addCriterion("TS_YEAR_MONTH between", value1, value2, "tsYearMonth");
            return this;
        }

        public Criteria andTsYearMonthNotBetween(String value1, String value2) {
            addCriterion("TS_YEAR_MONTH not between", value1, value2, "tsYearMonth");
            return this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeIn(List values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotIn(List values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return this;
        }

        public Criteria andTsStartTimeIsNull() {
            addCriterion("TS_START_TIME is null");
            return this;
        }

        public Criteria andTsStartTimeIsNotNull() {
            addCriterion("TS_START_TIME is not null");
            return this;
        }

        public Criteria andTsStartTimeEqualTo(Long value) {
            addCriterion("TS_START_TIME =", value, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeNotEqualTo(Long value) {
            addCriterion("TS_START_TIME <>", value, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeGreaterThan(Long value) {
            addCriterion("TS_START_TIME >", value, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("TS_START_TIME >=", value, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeLessThan(Long value) {
            addCriterion("TS_START_TIME <", value, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeLessThanOrEqualTo(Long value) {
            addCriterion("TS_START_TIME <=", value, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeIn(List values) {
            addCriterion("TS_START_TIME in", values, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeNotIn(List values) {
            addCriterion("TS_START_TIME not in", values, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeBetween(Long value1, Long value2) {
            addCriterion("TS_START_TIME between", value1, value2, "tsStartTime");
            return this;
        }

        public Criteria andTsStartTimeNotBetween(Long value1, Long value2) {
            addCriterion("TS_START_TIME not between", value1, value2, "tsStartTime");
            return this;
        }

        public Criteria andTsEndTimeIsNull() {
            addCriterion("TS_END_TIME is null");
            return this;
        }

        public Criteria andTsEndTimeIsNotNull() {
            addCriterion("TS_END_TIME is not null");
            return this;
        }

        public Criteria andTsEndTimeEqualTo(Long value) {
            addCriterion("TS_END_TIME =", value, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeNotEqualTo(Long value) {
            addCriterion("TS_END_TIME <>", value, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeGreaterThan(Long value) {
            addCriterion("TS_END_TIME >", value, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("TS_END_TIME >=", value, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeLessThan(Long value) {
            addCriterion("TS_END_TIME <", value, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeLessThanOrEqualTo(Long value) {
            addCriterion("TS_END_TIME <=", value, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeIn(List values) {
            addCriterion("TS_END_TIME in", values, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeNotIn(List values) {
            addCriterion("TS_END_TIME not in", values, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeBetween(Long value1, Long value2) {
            addCriterion("TS_END_TIME between", value1, value2, "tsEndTime");
            return this;
        }

        public Criteria andTsEndTimeNotBetween(Long value1, Long value2) {
            addCriterion("TS_END_TIME not between", value1, value2, "tsEndTime");
            return this;
        }
    }
}