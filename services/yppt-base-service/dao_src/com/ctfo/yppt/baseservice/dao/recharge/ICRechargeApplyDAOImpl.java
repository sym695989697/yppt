package com.ctfo.yppt.baseservice.dao.recharge;

import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyExample;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyExampleExtended;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ICRechargeApplyDAOImpl extends SqlMapClientDaoSupport implements ICRechargeApplyDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public ICRechargeApplyDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public void insert(ICRechargeApply record) {
		getSqlMapClientTemplate().insert(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int updateByPrimaryKey(ICRechargeApply record) {
		int rows = getSqlMapClientTemplate().update(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int updateByPrimaryKeySelective(ICRechargeApply record) {
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public List selectByExample(ICRechargeApplyExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_selectByExample",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public ICRechargeApply selectByPrimaryKey(String id) {
		ICRechargeApply key = new ICRechargeApply() {
		};
		key.setId(id);
		ICRechargeApply record = (ICRechargeApply) getSqlMapClientTemplate()
				.queryForObject(
						"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int deleteByExample(ICRechargeApplyExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_deleteByExample",
				example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int deleteByPrimaryKey(String id) {
		ICRechargeApply key = new ICRechargeApply() {
		};
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_deleteByPrimaryKey",
				key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int countByExample(ICRechargeApplyExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_countByExample",
				example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int updateByExampleSelective(ICRechargeApply record,
			ICRechargeApplyExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int updateByExample(ICRechargeApply record,
			ICRechargeApplyExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_updateByExample",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public List selectByExampleWithPage(ICRechargeApplyExample example) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_selectByExamplePage",
						example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public List selectByExampleWithPage(
			ICRechargeApplyExampleExtended exampleExtended) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_selectByExampleExtendedPage",
						exampleExtended);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public List getKeyBy(ICRechargeApplyExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_selectKeyBy",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public String getNameSpace() {
		return "TB_IC_CARD_RECHARGE_APPLY";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public List getKeyBy(ICRechargeApplyExampleExtended example) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_selectKeyByExtended",
						example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	public int countByExample(ICRechargeApplyExampleExtended example) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"TB_IC_CARD_RECHARGE_APPLY.abatorgenerated_countByExampleExtended",
						example);
		return count.intValue();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table TB_IC_CARD_RECHARGE_APPLY
	 * @abatorgenerated  Sun Feb 08 20:11:47 CST 2015
	 */
	private static class UpdateByExampleParms extends ICRechargeApplyExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				ICRechargeApplyExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}