package com.ctfo.yppt.baseservice.rebate.intf;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import com.ctfo.yppt.bean.RebateImportBean;
/**
 * 
 * 
 * 旺金币结算接口.(基本操作父类有实现)
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public interface IRebateSettleCurrencyManager extends
		IBaseManager<ICCardCreditAccountIO, ICCardCreditAccountIOExampleExtended> {
	/***
	 * 结算返利到旺金币
	 * @param rebateImport 返利对象 ; opId 操作人ID
	 * @return 0 结算成功；1结算失败
	 */
	public String settleCurrency(RebateImportBean rebateImport,String opId)throws Exception;
}
