package com.ctfo.yppt.baseservice.dao.trade;

import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExample;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExampleExtended;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ICCardMainTradeInfoDAOImpl extends SqlMapClientDaoSupport implements ICCardMainTradeInfoDAO {

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public ICCardMainTradeInfoDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public void insert(ICCardMainTradeInfo record) {
		getSqlMapClientTemplate().insert(
				"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_insert", record);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int updateByPrimaryKey(ICCardMainTradeInfo record) {
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_updateByPrimaryKey",
						record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int updateByPrimaryKeySelective(ICCardMainTradeInfo record) {
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public List selectByExample(ICCardMainTradeInfoExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_selectByExample",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public ICCardMainTradeInfo selectByPrimaryKey(String id) {
		ICCardMainTradeInfo key = new ICCardMainTradeInfo() {
		};
		key.setId(id);
		ICCardMainTradeInfo record = (ICCardMainTradeInfo) getSqlMapClientTemplate()
				.queryForObject(
						"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int deleteByExample(ICCardMainTradeInfoExample example) {
		int rows = getSqlMapClientTemplate().delete(
				"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_deleteByExample",
				example);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int deleteByPrimaryKey(String id) {
		ICCardMainTradeInfo key = new ICCardMainTradeInfo() {
		};
		key.setId(id);
		int rows = getSqlMapClientTemplate()
				.delete("TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_deleteByPrimaryKey",
						key);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int countByExample(ICCardMainTradeInfoExample example) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_countByExample",
				example);
		return count.intValue();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int updateByExampleSelective(ICCardMainTradeInfo record,
			ICCardMainTradeInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate()
				.update("TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_updateByExampleSelective",
						parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int updateByExample(ICCardMainTradeInfo record,
			ICCardMainTradeInfoExample example) {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = getSqlMapClientTemplate().update(
				"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_updateByExample",
				parms);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public List selectByExampleWithPage(ICCardMainTradeInfoExample example) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_selectByExamplePage",
						example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public List selectByExampleWithPage(
			ICCardMainTradeInfoExampleExtended exampleExtended) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_selectByExampleExtendedPage",
						exampleExtended);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public List getKeyBy(ICCardMainTradeInfoExample example) {
		List list = getSqlMapClientTemplate().queryForList(
				"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_selectKeyBy",
				example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public String getNameSpace() {
		return "TB_IC_MAIN_CARD_TRADE_INFO";
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public List getKeyBy(ICCardMainTradeInfoExampleExtended example) {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_selectKeyByExtended",
						example);
		return list;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	public int countByExample(ICCardMainTradeInfoExampleExtended example) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"TB_IC_MAIN_CARD_TRADE_INFO.abatorgenerated_countByExampleExtended",
						example);
		return count.intValue();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table TB_IC_MAIN_CARD_TRADE_INFO
	 * @abatorgenerated  Tue Feb 03 15:41:39 CST 2015
	 */
	private static class UpdateByExampleParms extends
			ICCardMainTradeInfoExample {
		private Object record;

		public UpdateByExampleParms(Object record,
				ICCardMainTradeInfoExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}