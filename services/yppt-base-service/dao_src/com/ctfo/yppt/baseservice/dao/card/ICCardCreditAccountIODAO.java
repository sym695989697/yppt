package com.ctfo.yppt.baseservice.dao.card;

import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExample;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import java.util.List;

public interface ICCardCreditAccountIODAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	void insert(ICCardCreditAccountIO record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int updateByPrimaryKey(ICCardCreditAccountIO record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int updateByPrimaryKeySelective(ICCardCreditAccountIO record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	List selectByExample(ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	ICCardCreditAccountIO selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int deleteByExample(ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int countByExample(ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int updateByExampleSelective(ICCardCreditAccountIO record,
			ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int updateByExample(ICCardCreditAccountIO record,
			ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	List selectByExampleWithPage(ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	List selectByExampleWithPage(
			ICCardCreditAccountIOExampleExtended exampleExtended);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	List getKeyBy(ICCardCreditAccountIOExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	String getNameSpace();

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	List getKeyBy(ICCardCreditAccountIOExampleExtended example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_CREDIT_ACCOUNT_IO
	 * @abatorgenerated  Sat Feb 07 14:56:31 CST 2015
	 */
	int countByExample(ICCardCreditAccountIOExampleExtended example);
}