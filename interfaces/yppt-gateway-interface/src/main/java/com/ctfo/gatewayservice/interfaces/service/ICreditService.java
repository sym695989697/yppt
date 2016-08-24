package com.ctfo.gatewayservice.interfaces.service;

import java.util.Map;

import com.ctfo.common.models.PaginationResult;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.credit.CreditIO;

/**
 * 旺金币 对接接口服务
 * 
 * @author 徐宝
 */
public interface ICreditService {
	
	/**
	 * 校验是否存在旺金币账户
	 * 
	 * @param userid
	 *            用户id
	 * @return 存在: Boolean.TRUE 不存在 Boolean.FALSE
	 * @throws YpptGatewayException
	 */
	public Boolean checkExistCreditAccount(String userId) throws YpptGatewayException;
	
	/**
	 * 创建旺金币账户
	 * 
	 * @param userid
	 *            用户id
	 * @return 旺金币账户Id
	 * @throws YpptGatewayException
	 */
	public String createCreditAccount(String userId) throws YpptGatewayException;

	/**
	 * 查询旺金币余额
	 * 
	 * @param userid
	 *            用户id
	 * @return 旺金币余额
	 * @throws YpptGatewayException
	 */
	public Long queryBalance(String userid) throws YpptGatewayException;

	/**
	 * 冻结积分
	 * 
	 * @param userId
	 *            用户id
	 * @param creditNum
	 *            冻结积分个数
	 * @param reasonNo
	 *            业务订单号
	 * @return 冻结成功 返回冻结订单id
	 * @throws YpptGatewayException
	 */
	public String freeze(String userId, Long creditNum, String reasonNo)
			throws YpptGatewayException;

	/**
	 * 解冻冻结积分
	 * 
	 * @param freezeOrderId
	 *            冻结订单id
	 * @return 是否解冻成功 返回解冻订单id
	 * @throws YpptGatewayException
	 */
	public String unfreeze(String userId, String freezeOrderId) throws YpptGatewayException;

	/**
	 * 解冻冻结积分
	 * 
	 * @param freezeOrderId
	 *            冻结订单id
	 * @return 是否解冻成功 返回解冻订单id
	 * @throws YpptGatewayException
	 */
	public String unfreeze(String userId, Long creditNum, String reasonNo) throws YpptGatewayException;
	
	/**
	 * 扣除积分
	 * 
	 * @param userId
	 *            用户id
	 * @param creditNum
	 *            积分个数
	 * @param reasonNo
	 *            业务订单号
	 * @return 是否扣除成功 返回扣除订单id
	 * @throws YpptGatewayException
	 */
	public String deduct(String userId, Long creditNum, String reasonNo)
			throws YpptGatewayException;

	/**
	 * 根据冻结积分id,扣除账户积分
	 * 
	 * @param 冻结订单id
	 * @return 是否解冻成功
	 * @throws YpptGatewayException
	 */
	public String deductByFreezeOrder(String userId, String freezeOrderId)
			throws YpptGatewayException;

	/**
	 * 增加积分
	 * 
	 * @param userId
	 *            用户id
	 * @param creditNum
	 *            积分个数
	 * @param reasonNo
	 *            业务订单号
	 * @return 是否增加成功 返回增加订单id
	 * @throws YpptGatewayException
	 */
	public String add(String userId, Long creditNum, String reasonNo)
			throws YpptGatewayException;

	/**
	 * 
	 * @param parmaMap
	 * 			分页查询对象集 
	 * 			userId : 用户id	---->等于条件
	 * 			regPhone : 注册手机号	---->等于条件
	 * 			userName : 用户名称	---->等于条件
	 * 			dealStartTime ： 日期 开始时间   ---->大于条件
	 * 			dealEndTime ： 日期 结束时间	 ---->小于条件
	 * 			model ： 分类 1：收入 2： 支出  ---->等于条件
	 * 			type : 交易类型 : rechagre :充值, rebate : 返利   ---->等于条件
	 * 			page ： 当前页面页数 
	 * 			pageSize ： 每页显示数量
	 * 
	 * @return PaginationResult 分页结果参数列表包含 CreditIO
	 * 
	 * @throws YpptGatewayException
	 */
	public PaginationResult<CreditIO> queryCreditIO(Map<String, Object> parmaMap)
			throws YpptGatewayException;

}
