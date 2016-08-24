package com.ctfo.yppt.baseservice.dao.invoice;

import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLog;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLogExample;
import com.ctfo.yppt.baseservice.invoice.beans.ICCardInvoiceApplyProcessLogExampleExtended;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ICCardInvoiceApplyProcessLogDAOImpl extends SqlMapClientDaoSupport implements ICCardInvoiceApplyProcessLogDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public ICCardInvoiceApplyProcessLogDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public void insert(ICCardInvoiceApplyProcessLog record) {
		getSqlMapClientTemplate().insert(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int updateByPrimaryKey(ICCardInvoiceApplyProcessLog record) {
		int rows = getSqlMapClientTemplate().update(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int updateByPrimaryKeySelective(ICCardInvoiceApplyProcessLog record) {
		int rows = getSqlMapClientTemplate()
				.update("TB_INVOICE_APPLY_PROCESS.abatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public List selectByExample(ICCardInvoiceApplyProcessLogExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_selectByExample",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public ICCardInvoiceApplyProcessLog selectByPrimaryKey(String id) {
		ICCardInvoiceApplyProcessLog key = new ICCardInvoiceApplyProcessLog() {
		};
		key.setId(id);
		ICCardInvoiceApplyProcessLog record = (ICCardInvoiceApplyProcessLog) getSqlMapClientTemplate()
				.queryForObject(
						"TB_INVOICE_APPLY_PROCESS.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int deleteByExample(ICCardInvoiceApplyProcessLogExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_deleteByExample",
				example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int deleteByPrimaryKey(String id) {
		ICCardInvoiceApplyProcessLog key = new ICCardInvoiceApplyProcessLog() {
		};
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_deleteByPrimaryKey",
				key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int countByExample(ICCardInvoiceApplyProcessLogExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_countByExample",
				example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int updateByExampleSelective(ICCardInvoiceApplyProcessLog record,
			ICCardInvoiceApplyProcessLogExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update("TB_INVOICE_APPLY_PROCESS.abatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int updateByExample(ICCardInvoiceApplyProcessLog record,
			ICCardInvoiceApplyProcessLogExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_updateByExample",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public List selectByExampleWithPage(
			ICCardInvoiceApplyProcessLogExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_selectByExamplePage",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public List selectByExampleWithPage(
			ICCardInvoiceApplyProcessLogExampleExtended exampleExtended) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_INVOICE_APPLY_PROCESS.abatorgenerated_selectByExampleExtendedPage",
						exampleExtended);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public List getKeyBy(ICCardInvoiceApplyProcessLogExample example) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_INVOICE_APPLY_PROCESS.abatorgenerated_selectKeyBy",
						example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public String getNameSpace() {
		return "TB_INVOICE_APPLY_PROCESS";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public List getKeyBy(ICCardInvoiceApplyProcessLogExampleExtended example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_INVOICE_APPLY_PROCESS.abatorgenerated_selectKeyByExtended",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	public int countByExample(
			ICCardInvoiceApplyProcessLogExampleExtended example) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"TB_INVOICE_APPLY_PROCESS.abatorgenerated_countByExampleExtended",
						example);
		return count.intValue();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table TB_INVOICE_APPLY_PROCESS
	 * @abatorgenerated  Sat Jan 31 13:37:00 CST 2015
	 */
	private static class UpdateByExampleParms extends
			ICCardInvoiceApplyProcessLogExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				ICCardInvoiceApplyProcessLogExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}