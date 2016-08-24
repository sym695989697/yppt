package com.ctfo.yppt.baseservice.dao.bill;

import com.ctfo.yppt.baseservice.bill.beans.TradeUser;
import com.ctfo.yppt.baseservice.bill.beans.TradeUserExample;
import com.ctfo.yppt.baseservice.bill.beans.TradeUserExampleExtended;
import java.util.List;

public interface TradeUserDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    void insert(TradeUser record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int updateByPrimaryKey(TradeUser record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int updateByPrimaryKeySelective(TradeUser record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    List selectByExample(TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    TradeUser selectByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int deleteByExample(TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int countByExample(TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int updateByExampleSelective(TradeUser record, TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int updateByExample(TradeUser record, TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    List selectByExampleWithPage(TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    List selectByExampleWithPage(TradeUserExampleExtended exampleExtended);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    List getKeyBy(TradeUserExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    String getNameSpace();

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    List getKeyBy(TradeUserExampleExtended example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_USER
     *
     * @abatorgenerated Tue Mar 24 15:20:56 CST 2015
     */
    int countByExample(TradeUserExampleExtended example);
}