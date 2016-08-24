package com.ctfo.yppt.baseservice.trade.intf;

import java.math.BigDecimal;
import java.util.Map;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistoryExampleExtended;
import com.ctfo.yppt.bean.ICCardTradeInfoBean;

/**
 * @author rao yongbing
 * @Description 副卡历史交易管理Manager (基本操作父类有实现)
 *              增加调用：add 删除调用：remove 修改调用：update 查询单个对象调用：getById
 *              分页查询调用：paginate 查询所有记录调用 ：getList 2015年1月20日
 */
public interface IICCardTradeInfoHistoryManager extends IBaseManager<ICCardTradeInfoHistory, ICCardTradeInfoHistoryExampleExtended> {

	/**
	 * 
	 * @author rao yongbing
	 * @Description 增加副卡交易记录
	 * @param model
	 *            副卡交易实体
	 * @return
	 * @throws BusinessException
	 */
	public String addTradeInfoHistory(ICCardTradeInfoHistory model)
			throws BusinessException;
	
	/**
	 * 
	 * @author rao yongbing 
	 * @Description 分页查询历史交易信息
	 * @param params page    第几页
			         pageSize  每页行数
			         userId   用户id
			         threeSwitch  卡号/车牌号/电话号
			         startTime   开始时间
			         endTime     结束时间
			         status   状态
	 * @return
	 * @throws BusinessException
	 */
	public PaginationResult<ICCardTradeInfoBean> paginateTradeInfoHistory(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @author rao yongbing 
	 * @Description 指定时间段消费总额
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public BigDecimal statTradeInfoHistory(Map<String, Object> params) throws BusinessException;

	/**
	 * 
	 * @author rao yongbing 
	 * @Description  指定时间段加油总额
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public BigDecimal statOilTradeInfoHistory(Map<String, Object> params) throws BusinessException;


	/**
	 * 
	 * @author rao yongbing
	 * @Description 分页取得加油交易记录(包括交易历史记录)
	 * @param params
	 * @return
	 * @throws BusinessException
	 */
	public PaginationResult<?> paginateTradeInfoAndTradeInfoHistory(
			Map<String, Object> params) throws BusinessException;

}
