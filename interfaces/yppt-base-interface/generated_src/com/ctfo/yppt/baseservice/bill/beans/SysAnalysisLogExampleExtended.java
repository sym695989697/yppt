package com.ctfo.yppt.baseservice.bill.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysAnalysisLogExampleExtended implements Serializable {
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected List oredCriteria;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected String selectedField;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected int skipNum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected int endNum;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected int limitNum;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public SysAnalysisLogExampleExtended() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected SysAnalysisLogExampleExtended(SysAnalysisLogExampleExtended example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
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
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSelectedField(String selectedField) {
        this.selectedField = selectedField;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getSelectedField() {
        return selectedField;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String tableName() {
        return "TB_SYS_ANALYSIS_LOG";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static String daoInterface() {
        return "com.ctfo.yppt.baseservice.dao.bill.SysAnalysisLogDAO";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setSkipNum(int skipNum) {
        this.skipNum = skipNum;
        this.endNum = this.skipNum + this.limitNum +1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int getSkipNum() {
        return this.skipNum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setEndNum(int endNum) {
        this.endNum = endNum;
        this.limitNum = this.endNum - this.skipNum -1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int getEndNum() {
        return this.endNum;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
        this.endNum = this.skipNum + this.limitNum +1;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int getLimitNum() {
        return this.limitNum;
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table TB_SYS_ANALYSIS_LOG
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public static class Criteria implements Serializable {
        /**
         * This field was generated by Abator for iBATIS.
         * This field corresponds to the database table TB_SYS_ANALYSIS_LOG
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

        public Criteria andObjectIdIsNull() {
            addCriterion("OBJECT_ID is null");
            return this;
        }

        public Criteria andObjectIdIsNotNull() {
            addCriterion("OBJECT_ID is not null");
            return this;
        }

        public Criteria andObjectIdEqualTo(String value) {
            addCriterion("OBJECT_ID =", value, "objectId");
            return this;
        }

        public Criteria andObjectIdNotEqualTo(String value) {
            addCriterion("OBJECT_ID <>", value, "objectId");
            return this;
        }

        public Criteria andObjectIdGreaterThan(String value) {
            addCriterion("OBJECT_ID >", value, "objectId");
            return this;
        }

        public Criteria andObjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_ID >=", value, "objectId");
            return this;
        }

        public Criteria andObjectIdLessThan(String value) {
            addCriterion("OBJECT_ID <", value, "objectId");
            return this;
        }

        public Criteria andObjectIdLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_ID <=", value, "objectId");
            return this;
        }

        public Criteria andObjectIdLike(String value) {
            addCriterion("OBJECT_ID like", value, "objectId");
            return this;
        }

        public Criteria andObjectIdNotLike(String value) {
            addCriterion("OBJECT_ID not like", value, "objectId");
            return this;
        }

        public Criteria andObjectIdIn(List values) {
            addCriterion("OBJECT_ID in", values, "objectId");
            return this;
        }

        public Criteria andObjectIdNotIn(List values) {
            addCriterion("OBJECT_ID not in", values, "objectId");
            return this;
        }

        public Criteria andObjectIdBetween(String value1, String value2) {
            addCriterion("OBJECT_ID between", value1, value2, "objectId");
            return this;
        }

        public Criteria andObjectIdNotBetween(String value1, String value2) {
            addCriterion("OBJECT_ID not between", value1, value2, "objectId");
            return this;
        }

        public Criteria andResultTypeIsNull() {
            addCriterion("RESULT_TYPE is null");
            return this;
        }

        public Criteria andResultTypeIsNotNull() {
            addCriterion("RESULT_TYPE is not null");
            return this;
        }

        public Criteria andResultTypeEqualTo(String value) {
            addCriterion("RESULT_TYPE =", value, "resultType");
            return this;
        }

        public Criteria andResultTypeNotEqualTo(String value) {
            addCriterion("RESULT_TYPE <>", value, "resultType");
            return this;
        }

        public Criteria andResultTypeGreaterThan(String value) {
            addCriterion("RESULT_TYPE >", value, "resultType");
            return this;
        }

        public Criteria andResultTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_TYPE >=", value, "resultType");
            return this;
        }

        public Criteria andResultTypeLessThan(String value) {
            addCriterion("RESULT_TYPE <", value, "resultType");
            return this;
        }

        public Criteria andResultTypeLessThanOrEqualTo(String value) {
            addCriterion("RESULT_TYPE <=", value, "resultType");
            return this;
        }

        public Criteria andResultTypeLike(String value) {
            addCriterion("RESULT_TYPE like", value, "resultType");
            return this;
        }

        public Criteria andResultTypeNotLike(String value) {
            addCriterion("RESULT_TYPE not like", value, "resultType");
            return this;
        }

        public Criteria andResultTypeIn(List values) {
            addCriterion("RESULT_TYPE in", values, "resultType");
            return this;
        }

        public Criteria andResultTypeNotIn(List values) {
            addCriterion("RESULT_TYPE not in", values, "resultType");
            return this;
        }

        public Criteria andResultTypeBetween(String value1, String value2) {
            addCriterion("RESULT_TYPE between", value1, value2, "resultType");
            return this;
        }

        public Criteria andResultTypeNotBetween(String value1, String value2) {
            addCriterion("RESULT_TYPE not between", value1, value2, "resultType");
            return this;
        }

        public Criteria andFailReasonIsNull() {
            addCriterion("FAIL_REASON is null");
            return this;
        }

        public Criteria andFailReasonIsNotNull() {
            addCriterion("FAIL_REASON is not null");
            return this;
        }

        public Criteria andFailReasonEqualTo(String value) {
            addCriterion("FAIL_REASON =", value, "failReason");
            return this;
        }

        public Criteria andFailReasonNotEqualTo(String value) {
            addCriterion("FAIL_REASON <>", value, "failReason");
            return this;
        }

        public Criteria andFailReasonGreaterThan(String value) {
            addCriterion("FAIL_REASON >", value, "failReason");
            return this;
        }

        public Criteria andFailReasonGreaterThanOrEqualTo(String value) {
            addCriterion("FAIL_REASON >=", value, "failReason");
            return this;
        }

        public Criteria andFailReasonLessThan(String value) {
            addCriterion("FAIL_REASON <", value, "failReason");
            return this;
        }

        public Criteria andFailReasonLessThanOrEqualTo(String value) {
            addCriterion("FAIL_REASON <=", value, "failReason");
            return this;
        }

        public Criteria andFailReasonLike(String value) {
            addCriterion("FAIL_REASON like", value, "failReason");
            return this;
        }

        public Criteria andFailReasonNotLike(String value) {
            addCriterion("FAIL_REASON not like", value, "failReason");
            return this;
        }

        public Criteria andFailReasonIn(List values) {
            addCriterion("FAIL_REASON in", values, "failReason");
            return this;
        }

        public Criteria andFailReasonNotIn(List values) {
            addCriterion("FAIL_REASON not in", values, "failReason");
            return this;
        }

        public Criteria andFailReasonBetween(String value1, String value2) {
            addCriterion("FAIL_REASON between", value1, value2, "failReason");
            return this;
        }

        public Criteria andFailReasonNotBetween(String value1, String value2) {
            addCriterion("FAIL_REASON not between", value1, value2, "failReason");
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

        public Criteria andDescIsNull() {
            addCriterion("DESC is null");
            return this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("DESC is not null");
            return this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("DESC =", value, "desc");
            return this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("DESC <>", value, "desc");
            return this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("DESC >", value, "desc");
            return this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("DESC >=", value, "desc");
            return this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("DESC <", value, "desc");
            return this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("DESC <=", value, "desc");
            return this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("DESC like", value, "desc");
            return this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("DESC not like", value, "desc");
            return this;
        }

        public Criteria andDescIn(List values) {
            addCriterion("DESC in", values, "desc");
            return this;
        }

        public Criteria andDescNotIn(List values) {
            addCriterion("DESC not in", values, "desc");
            return this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("DESC between", value1, value2, "desc");
            return this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("DESC not between", value1, value2, "desc");
            return this;
        }
    }
}