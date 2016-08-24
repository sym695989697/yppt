package com.ctfo.yppt.baseservice.dao.invoice;

import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLogExample;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLogExampleExtended;
import java.util.List;

public interface ICCardInvoiceApplyProcessLogDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	void insert(ICCardInvoiceApplyProcessLog record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int updateByPrimaryKey(ICCardInvoiceApplyProcessLog record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int updateByPrimaryKeySelective(ICCardInvoiceApplyProcessLog record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	List selectByExample(ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	ICCardInvoiceApplyProcessLog selectByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int deleteByExample(ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int countByExample(ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int updateByExampleSelective(ICCardInvoiceApplyProcessLog record,
			ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int updateByExample(ICCardInvoiceApplyProcessLog record,
			ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	List selectByExampleWithPage(ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	List selectByExampleWithPage(
			ICCardInvoiceApplyProcessLogExampleExtended exampleExtended);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	List getKeyBy(ICCardInvoiceApplyProcessLogExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	String getNameSpace();

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	List getKeyBy(ICCardInvoiceApplyProcessLogExampleExtended example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:01 CST 2015
	 */
	int countByExample(ICCardInvoiceApplyProcessLogExampleExtended example);
}