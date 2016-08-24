package com.ctfo.yppt.baseservice.dao.bill;

import com.ctfo.yppt.baseservice.bill.beans.TradeCard;
import com.ctfo.yppt.baseservice.bill.beans.TradeCardExample;
import com.ctfo.yppt.baseservice.bill.beans.TradeCardExampleExtended;
import java.util.List;

public interface TradeCardDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    void insert(TradeCard record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int updateByPrimaryKey(TradeCard record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int updateByPrimaryKeySelective(TradeCard record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    List selectByExample(TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    TradeCard selectByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int deleteByExample(TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int countByExample(TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int updateByExampleSelective(TradeCard record, TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int updateByExample(TradeCard record, TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    List selectByExampleWithPage(TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    List selectByExampleWithPage(TradeCardExampleExtended exampleExtended);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    List getKeyBy(TradeCardExample example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    String getNameSpace();

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    List getKeyBy(TradeCardExampleExtended example);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table TS_TRADE_CARD
     *
     * @abatorgenerated Tue Mar 24 15:20:55 CST 2015
     */
    int countByExample(TradeCardExampleExtended example);
}