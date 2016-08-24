package com.ctfo.yppt.baseservice.card.intf;

import java.util.Map;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.card.beans.ICCardCreditAccountIO;

/**
 * IC卡充值管理Manager
 * @author Administrator
 *
 */
public interface IBaseLocalCreditManager{
	/**
	 * 分页查询对象集 
	 * dealStartTime ： 日期 开始时间
	 * dealEndTime ： 日期 结束时间
	 * model ： 分类 1：收入 2： 支出
	 * page ： 当前页面
	 * pageSize ： 每页显示数量
	 * 
	 * @param exampleExtended
	 * @return PaginationResult 列表包含 ICCardCreditAccountIO
	 * 
	 * @throws Exception
	 */
	public PaginationResult<ICCardCreditAccountIO> paginateCreditIORecords(Map<String,Object> queryMap) throws BusinessException;
}
