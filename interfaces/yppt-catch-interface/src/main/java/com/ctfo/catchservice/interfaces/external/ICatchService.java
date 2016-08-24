package com.ctfo.catchservice.interfaces.external;

import com.ctfo.catchservice.bean.JResult;

/**
 * 抓取数据外部接口
 * @author jichao
 */
public interface ICatchService {

	/**
	 * 测试
	 */
	public String hello() throws Exception;

	/**
	 * 根据卡获取卡余额 
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH
	 * @param driverCardNo 司机卡号
	 * @param userName 用户名  请求刷新余额用户
	 */
	public JResult getOilCardBalance(String hostType, String driverCardNo,String userName) throws Exception;
	
	
	/**
	 * 账户交易查询 
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH
	 * @param driverCardNo 司机卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tradeType 交易类型  1：充值   2:圈存圈提  3:分配汇总  4:其它
	 * @param userName 用户名
	 */
	public JResult getAccountTrade(String hostType, String driverCardNo,String startDate, String endDate,String tradeType,String userName) throws Exception;
	
	/**
	 * 消费交易查询
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 司机卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param userName 用户名
	 */
	public JResult getTradeByConsume(String hostType, String driverCardNo,String startDate, String endDate,String userName) throws Exception;
	
	/**
	 * 积分交易查询
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 司机卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tradeType 消费类型  1：消费   2:累计  3:兑换  4:圈存圈提 5:分配汇总 6:其他
	 */
	//public JResult getTradeByIntegral(String hostType, String driverCardNo,String startDate, String endDate,String tradeType) throws Exception;
	
	
	/**
	 * 交易统计
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param cardNo 卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tradeType 交易类型 1:消费  2:充值
	 */
	//public JResult getTradeByConsumeStatistic(String hostType, String cardNo,String startDate, String endDate,String tradeType) throws Exception;
	
	/**
	 * 积分交易统计
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tradeType 交易类型 1:累计  2:兑换 3：消费
	 */
	//public JResult getTradeByIntegralStatistic(String hostType, String driverCardNo,String startDate, String endDate,String tradeType) throws Exception;
	
	/**
	 * 获取中主卡及主卡下的子卡接口
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 司机卡号
	 * @param cardStatus 卡状态：""：全部  2：正常 3：黑名单 4：注销
	 * @param   userName 用户名 抓取哪个用户的主副卡
	 */
	public JResult getMainCardAndSubcard(String hostType, String driverCardNo,String cardStatus,String userName)throws Exception;

	
}
