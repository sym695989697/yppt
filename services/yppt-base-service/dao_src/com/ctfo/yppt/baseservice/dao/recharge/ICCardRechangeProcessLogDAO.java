package com.ctfo.yppt.baseservice.dao.recharge;

import com.ctfo.yppt.baseservice.recharge.beans.ICCardRechangeProcessLog;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardRechangeProcessLogExample;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardRechangeProcessLogExampleExtended;
import java.util.List;

public interface ICCardRechangeProcessLogDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	void insert(ICCardRechangeProcessLog record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int updateByPrimaryKey(ICCardRechangeProcessLog record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int updateByPrimaryKeySelective(ICCardRechangeProcessLog record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	List selectByExample(ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	ICCardRechangeProcessLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int deleteByExample(ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int countByExample(ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int updateByExampleSelective(ICCardRechangeProcessLog record,
			ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int updateByExample(ICCardRechangeProcessLog record,
			ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	List selectByExampleWithPage(ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	List selectByExampleWithPage(
			ICCardRechangeProcessLogExampleExtended exampleExtended);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	List getKeyBy(ICCardRechangeProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	String getNameSpace();

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	List getKeyBy(ICCardRechangeProcessLogExampleExtended example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHANGE_PROCESS
	 * @abatorgenerated  Thu Jan 29 17:56:21 CST 2015
	 */
	int countByExample(ICCardRechangeProcessLogExampleExtended example);
}