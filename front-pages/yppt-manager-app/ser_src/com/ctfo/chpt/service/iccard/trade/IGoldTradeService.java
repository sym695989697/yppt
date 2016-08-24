package com.ctfo.chpt.service.iccard.trade;

import java.util.List;
import java.util.Map;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIOExampleExtended;
import com.ctfo.yppt.bean.ICardRechageMetaBean;

/**
 * 旺金币交易记录服务
 * 
 * @author jichao
 */
public interface IGoldTradeService {

	/**
	 * 获取列表
	 */
	public PaginationResult<?> getListPage(Map<String, Object> param) throws Exception;

	/**
	 * 旺金币交易查询
	 */
	public PaginationResult<ICCardCreditAccountIO> getICCardCreditAccountIO(ICCardCreditAccountIOExampleExtended exampleExtended) throws Exception;

	/**
	 * 按用户ID获取交易记录（含详细信息）
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ICardRechageMetaBean> getICardRechageMetaBeanListByReasonId(String reasonId) throws Exception;

	
	public List getGoldTradeDetailsVoList(String reasonId)throws Exception;
}
