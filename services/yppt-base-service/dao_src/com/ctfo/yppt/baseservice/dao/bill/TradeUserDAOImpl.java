package com.ctfo.yppt.baseservice.dao.bill;

import com.ctfo.yppt.baseservice.bill.beans.TradeUser;
import com.ctfo.yppt.baseservice.bill.beans.TradeUserExample;
import com.ctfo.yppt.baseservice.bill.beans.TradeUserExampleExtended;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TradeUserDAOImpl extends SqlMapClientDaoSupport implements TradeUserDAO {

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public TradeUserDAOImpl() {
        super();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public void insert(TradeUser record) {
        getSqlMapClientTemplate().insert("TS_TRADE_USER.abatorgenerated_insert", record);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int updateByPrimaryKey(TradeUser record) {
        int rows = getSqlMapClientTemplate().update("TS_TRADE_USER.abatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int updateByPrimaryKeySelective(TradeUser record) {
        int rows = getSqlMapClientTemplate().update("TS_TRADE_USER.abatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List selectByExample(TradeUserExample example) {
        List list = getSqlMapClientTemplate().queryForList("TS_TRADE_USER.abatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public TradeUser selectByPrimaryKey(String id) {
        TradeUser key = new TradeUser(){};
        key.setId(id);
        TradeUser record = (TradeUser) getSqlMapClientTemplate().queryForObject("TS_TRADE_USER.abatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int deleteByExample(TradeUserExample example) {
        int rows = getSqlMapClientTemplate().delete("TS_TRADE_USER.abatorgenerated_deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int deleteByPrimaryKey(String id) {
        TradeUser key = new TradeUser(){};
        key.setId(id);
        int rows = getSqlMapClientTemplate().delete("TS_TRADE_USER.abatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int countByExample(TradeUserExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TS_TRADE_USER.abatorgenerated_countByExample", example);
        return count.intValue();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int updateByExampleSelective(TradeUser record, TradeUserExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TS_TRADE_USER.abatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int updateByExample(TradeUser record, TradeUserExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("TS_TRADE_USER.abatorgenerated_updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List selectByExampleWithPage(TradeUserExample example) {
        List list = getSqlMapClientTemplate().queryForList("TS_TRADE_USER.abatorgenerated_selectByExamplePage", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List selectByExampleWithPage(TradeUserExampleExtended exampleExtended) {
        List list = getSqlMapClientTemplate().queryForList("TS_TRADE_USER.abatorgenerated_selectByExampleExtendedPage", exampleExtended);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List getKeyBy(TradeUserExample example) {
        List list = getSqlMapClientTemplate().queryForList("TS_TRADE_USER.abatorgenerated_selectKeyBy", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public String getNameSpace() {
         return "TS_TRADE_USER";
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public List getKeyBy(TradeUserExampleExtended example) {
        List list = getSqlMapClientTemplate().queryForList("TS_TRADE_USER.abatorgenerated_selectKeyByExtended", example);
        return list;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    public int countByExample(TradeUserExampleExtended example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("TS_TRADE_USER.abatorgenerated_countByExampleExtended", example);
        return count.intValue();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    private static class UpdateByExampleParms extends TradeUserExample {
        private Object record;

        public UpdateByExampleParms(Object record, TradeUserExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}