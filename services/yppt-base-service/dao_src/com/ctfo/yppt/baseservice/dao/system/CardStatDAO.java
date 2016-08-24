package com.ctfo.yppt.baseservice.dao.system;

import com.ctfo.yppt.baseservice.system.beans.CardStat;
import com.ctfo.yppt.baseservice.system.beans.CardStatExample;
import com.ctfo.yppt.baseservice.system.beans.CardStatExampleExtended;
import java.util.List;

public interface CardStatDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	void insert(CardStat record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int updateByPrimaryKey(CardStat record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int updateByPrimaryKeySelective(CardStat record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	List selectByExample(CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	CardStat selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int deleteByExample(CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int countByExample(CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int updateByExampleSelective(CardStat record, CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int updateByExample(CardStat record, CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	List selectByExampleWithPage(CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	List selectByExampleWithPage(CardStatExampleExtended exampleExtended);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	List getKeyBy(CardStatExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	String getNameSpace();

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	List getKeyBy(CardStatExampleExtended example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TS_CARD_COMSUME_STAT
	 * @abatorgenerated  Mon Feb 02 17:58:55 CST 2015
	 */
	int countByExample(CardStatExampleExtended example);
}