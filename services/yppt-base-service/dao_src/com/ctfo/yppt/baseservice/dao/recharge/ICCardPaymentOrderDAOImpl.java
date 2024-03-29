package com.ctfo.yppt.baseservice.dao.recharge;

import com.ctfo.yppt.baseservice.recharge.beans.ICCardPaymentOrder;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardPaymentOrderExample;
import com.ctfo.yppt.baseservice.recharge.beans.ICCardPaymentOrderExampleExtended;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ICCardPaymentOrderDAOImpl extends SqlMapClientDaoSupport implements ICCardPaymentOrderDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public ICCardPaymentOrderDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public void insert(ICCardPaymentOrder record) {
		getSqlMapClientTemplate().insert(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int updateByPrimaryKey(ICCardPaymentOrder record) {
		int rows = getSqlMapClientTemplate().update(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int updateByPrimaryKeySelective(ICCardPaymentOrder record) {
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public List selectByExample(ICCardPaymentOrderExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_selectByExample",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public ICCardPaymentOrder selectByPrimaryKey(String id) {
		ICCardPaymentOrder key = new ICCardPaymentOrder() {
		};
		key.setId(id);
		ICCardPaymentOrder record = (ICCardPaymentOrder) getSqlMapClientTemplate()
				.queryForObject(
						"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int deleteByExample(ICCardPaymentOrderExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_deleteByExample",
				example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int deleteByPrimaryKey(String id) {
		ICCardPaymentOrder key = new ICCardPaymentOrder() {
		};
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_deleteByPrimaryKey",
				key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int countByExample(ICCardPaymentOrderExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_countByExample",
				example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int updateByExampleSelective(ICCardPaymentOrder record,
			ICCardPaymentOrderExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int updateByExample(ICCardPaymentOrder record,
			ICCardPaymentOrderExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_updateByExample",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public List selectByExampleWithPage(ICCardPaymentOrderExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_selectByExamplePage",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public List selectByExampleWithPage(
			ICCardPaymentOrderExampleExtended exampleExtended) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_selectByExampleExtendedPage",
						exampleExtended);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public List getKeyBy(ICCardPaymentOrderExample example) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_selectKeyBy",
						example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public String getNameSpace() {
		return "TB_IC_CARD_PAYMENT_ORDER";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public List getKeyBy(ICCardPaymentOrderExampleExtended example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_selectKeyByExtended",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	public int countByExample(ICCardPaymentOrderExampleExtended example) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"TB_IC_CARD_PAYMENT_ORDER.abatorgenerated_countByExampleExtended",
						example);
		return count.intValue();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table TB_IC_CARD_PAYMENT_ORDER
	 * @abatorgenerated  Thu Feb 05 21:08:30 CST 2015
	 */
	private static class UpdateByExampleParms extends ICCardPaymentOrderExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				ICCardPaymentOrderExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}