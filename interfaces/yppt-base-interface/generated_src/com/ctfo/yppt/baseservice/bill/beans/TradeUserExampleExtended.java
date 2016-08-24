package com.ctfo.yppt.baseservice.bill.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeUserExampleExtended implements Serializable {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected List oredCriteria;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected String selectedField;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected int skipNum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected int endNum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected int limitNum;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public TradeUserExampleExtended() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected TradeUserExampleExtended(TradeUserExampleExtended example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
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
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSelectedField(String selectedField) {
        this.selectedField = selectedField;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getSelectedField() {
        return selectedField;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String tableName() {
        return "TS_TRADE_USER";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String daoInterface() {
        return "com.ctfo.yppt.baseservice.dao.bill.TradeUserDAO";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSkipNum(int skipNum) {
        this.skipNum = skipNum;
        this.endNum = this.skipNum + this.limitNum +1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int getSkipNum() {
        return this.skipNum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setEndNum(int endNum) {
        this.endNum = endNum;
        this.limitNum = this.endNum - this.skipNum -1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int getEndNum() {
        return this.endNum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
        this.endNum = this.skipNum + this.limitNum +1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int getLimitNum() {
        return this.limitNum;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static class Criteria implements Serializable {
        /**
         * This field was generated by Abator for iBATIS.
         * This field corresponds to the database table TS_TRADE_USER
         *
         * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
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

        public Criteria andToAccountMoneyIsNull() {
            addCriterion("TO_ACCOUNT_MONEY is null");
            return this;
        }

        public Criteria andToAccountMoneyIsNotNull() {
            addCriterion("TO_ACCOUNT_MONEY is not null");
            return this;
        }

        public Criteria andToAccountMoneyEqualTo(Long value) {
            addCriterion("TO_ACCOUNT_MONEY =", value, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyNotEqualTo(Long value) {
            addCriterion("TO_ACCOUNT_MONEY <>", value, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyGreaterThan(Long value) {
            addCriterion("TO_ACCOUNT_MONEY >", value, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("TO_ACCOUNT_MONEY >=", value, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyLessThan(Long value) {
            addCriterion("TO_ACCOUNT_MONEY <", value, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyLessThanOrEqualTo(Long value) {
            addCriterion("TO_ACCOUNT_MONEY <=", value, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyIn(List values) {
            addCriterion("TO_ACCOUNT_MONEY in", values, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyNotIn(List values) {
            addCriterion("TO_ACCOUNT_MONEY not in", values, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyBetween(Long value1, Long value2) {
            addCriterion("TO_ACCOUNT_MONEY between", value1, value2, "toAccountMoney");
            return this;
        }

        public Criteria andToAccountMoneyNotBetween(Long value1, Long value2) {
            addCriterion("TO_ACCOUNT_MONEY not between", value1, value2, "toAccountMoney");
            return this;
        }

        public Criteria andSumMoneyRebateIsNull() {
            addCriterion("SUM_MONEY_REBATE is null");
            return this;
        }

        public Criteria andSumMoneyRebateIsNotNull() {
            addCriterion("SUM_MONEY_REBATE is not null");
            return this;
        }

        public Criteria andSumMoneyRebateEqualTo(Long value) {
            addCriterion("SUM_MONEY_REBATE =", value, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateNotEqualTo(Long value) {
            addCriterion("SUM_MONEY_REBATE <>", value, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateGreaterThan(Long value) {
            addCriterion("SUM_MONEY_REBATE >", value, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_REBATE >=", value, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateLessThan(Long value) {
            addCriterion("SUM_MONEY_REBATE <", value, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateLessThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY_REBATE <=", value, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateIn(List values) {
            addCriterion("SUM_MONEY_REBATE in", values, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateNotIn(List values) {
            addCriterion("SUM_MONEY_REBATE not in", values, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_REBATE between", value1, value2, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumMoneyRebateNotBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY_REBATE not between", value1, value2, "sumMoneyRebate");
            return this;
        }

        public Criteria andSumCardMoneyIsNull() {
            addCriterion("SUM_CARD_MONEY is null");
            return this;
        }

        public Criteria andSumCardMoneyIsNotNull() {
            addCriterion("SUM_CARD_MONEY is not null");
            return this;
        }

        public Criteria andSumCardMoneyEqualTo(Long value) {
            addCriterion("SUM_CARD_MONEY =", value, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyNotEqualTo(Long value) {
            addCriterion("SUM_CARD_MONEY <>", value, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyGreaterThan(Long value) {
            addCriterion("SUM_CARD_MONEY >", value, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_CARD_MONEY >=", value, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyLessThan(Long value) {
            addCriterion("SUM_CARD_MONEY <", value, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyLessThanOrEqualTo(Long value) {
            addCriterion("SUM_CARD_MONEY <=", value, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyIn(List values) {
            addCriterion("SUM_CARD_MONEY in", values, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyNotIn(List values) {
            addCriterion("SUM_CARD_MONEY not in", values, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyBetween(Long value1, Long value2) {
            addCriterion("SUM_CARD_MONEY between", value1, value2, "sumCardMoney");
            return this;
        }

        public Criteria andSumCardMoneyNotBetween(Long value1, Long value2) {
            addCriterion("SUM_CARD_MONEY not between", value1, value2, "sumCardMoney");
            return this;
        }

        public Criteria andSumMoneyIsNull() {
            addCriterion("SUM_MONEY is null");
            return this;
        }

        public Criteria andSumMoneyIsNotNull() {
            addCriterion("SUM_MONEY is not null");
            return this;
        }

        public Criteria andSumMoneyEqualTo(Long value) {
            addCriterion("SUM_MONEY =", value, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyNotEqualTo(Long value) {
            addCriterion("SUM_MONEY <>", value, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyGreaterThan(Long value) {
            addCriterion("SUM_MONEY >", value, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY >=", value, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyLessThan(Long value) {
            addCriterion("SUM_MONEY <", value, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyLessThanOrEqualTo(Long value) {
            addCriterion("SUM_MONEY <=", value, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyIn(List values) {
            addCriterion("SUM_MONEY in", values, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyNotIn(List values) {
            addCriterion("SUM_MONEY not in", values, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY between", value1, value2, "sumMoney");
            return this;
        }

        public Criteria andSumMoneyNotBetween(Long value1, Long value2) {
            addCriterion("SUM_MONEY not between", value1, value2, "sumMoney");
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

        public Criteria andTradeMonthIsNull() {
            addCriterion("TRADE_MONTH is null");
            return this;
        }

        public Criteria andTradeMonthIsNotNull() {
            addCriterion("TRADE_MONTH is not null");
            return this;
        }

        public Criteria andTradeMonthEqualTo(String value) {
            addCriterion("TRADE_MONTH =", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthNotEqualTo(String value) {
            addCriterion("TRADE_MONTH <>", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthGreaterThan(String value) {
            addCriterion("TRADE_MONTH >", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_MONTH >=", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthLessThan(String value) {
            addCriterion("TRADE_MONTH <", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthLessThanOrEqualTo(String value) {
            addCriterion("TRADE_MONTH <=", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthLike(String value) {
            addCriterion("TRADE_MONTH like", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthNotLike(String value) {
            addCriterion("TRADE_MONTH not like", value, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthIn(List values) {
            addCriterion("TRADE_MONTH in", values, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthNotIn(List values) {
            addCriterion("TRADE_MONTH not in", values, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthBetween(String value1, String value2) {
            addCriterion("TRADE_MONTH between", value1, value2, "tradeMonth");
            return this;
        }

        public Criteria andTradeMonthNotBetween(String value1, String value2) {
            addCriterion("TRADE_MONTH not between", value1, value2, "tradeMonth");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return this;
        }

        public Criteria andStatusIn(List values) {
            addCriterion("STATUS in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("STATUS not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return this;
        }

        public Criteria andSumOrtherIsNull() {
            addCriterion("SUM_ORTHER is null");
            return this;
        }

        public Criteria andSumOrtherIsNotNull() {
            addCriterion("SUM_ORTHER is not null");
            return this;
        }

        public Criteria andSumOrtherEqualTo(Long value) {
            addCriterion("SUM_ORTHER =", value, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherNotEqualTo(Long value) {
            addCriterion("SUM_ORTHER <>", value, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherGreaterThan(Long value) {
            addCriterion("SUM_ORTHER >", value, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherGreaterThanOrEqualTo(Long value) {
            addCriterion("SUM_ORTHER >=", value, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherLessThan(Long value) {
            addCriterion("SUM_ORTHER <", value, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherLessThanOrEqualTo(Long value) {
            addCriterion("SUM_ORTHER <=", value, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherIn(List values) {
            addCriterion("SUM_ORTHER in", values, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherNotIn(List values) {
            addCriterion("SUM_ORTHER not in", values, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherBetween(Long value1, Long value2) {
            addCriterion("SUM_ORTHER between", value1, value2, "sumOrther");
            return this;
        }

        public Criteria andSumOrtherNotBetween(Long value1, Long value2) {
            addCriterion("SUM_ORTHER not between", value1, value2, "sumOrther");
            return this;
        }
    }
}