package com.ctfo.catchservice.interfaces.internal;

import com.ctfo.catchservice.bean.CatchDataVO;

/**
 * 抓取数据内部接口
 * @author jichao
 */
public interface ICatchInternalService {
	/**
	 * 根据卡获取卡余额 
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 司机卡号
	 * @param userName
	 */
	public void getOilCardBalance(String hostType, String driverCardNo,String userName) throws Exception;
	
	/**
	 * 账户交易查询 
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH
	 * @param driverCardNo 司机卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param tradeType 交易类型  1：充值   2:圈存圈提  3:分配汇总  4:其它
	 * @param userName 用户名
	 */
	public void getAccountTrade(String hostType, String driverCardNo,String startDate, String endDate,String tradeType,String userName)throws Exception ;
	
	/**
	 * 消费交易查询
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 司机卡号
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param userName 用户名
	 */
	public void getTradeByConsume(String hostType, String driverCardNo,String startDate, String endDate,String userName)throws Exception ;
	
	
	/**
	 * 获取中主卡及主卡下的子卡接口
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param driverCardNo 司机卡号
	 * @param cardStatus 卡状态：""：全部  2：正常 3：黑名单 4：注销
	 * @param  username  用户名 抓取哪个用户下的主副卡
	 */
	public void getMainCardAndSubcard(String hostType, String driverCardNo,String cardStatus,String username)throws Exception;

	
	////保存抓取/////////////////
	
	/**
	 * 按主机类型名添加主卡与子卡的所有信息
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 */
	public void addMainCardAndSubcardByHostType(CatchDataVO catchdatavo )throws Exception;
	
	/**
	 * 
	 * @Description:更新卡余额
	 * @param  CatchDataVO 卡数据
	 * @param  hostType 网站类型
	 */
	public void updateBalance(CatchDataVO catchdatavo);
	
	/**
	 *  
	 * @Description:添加账户交易信息
	 * @param   CatchDataVO  账户交易信息数据
	 * @param hostType 网站类型
	 */
	public void addAccountTradeData(CatchDataVO catchdatavo);
	/**
	 * 
	 * @Description:添加消费记录信息
	 * @param    CatchDataVO  消费记录信息
	 * @param hostType 网站类型
	 */
	public void addTradeByConsumeData(CatchDataVO catchdatavo);
	
	/**
	 * 
	 * @Description:刷新用户在线状态
	 * @throws
	 */
	public void updateAccountStatus();
	/**
	 * 
	 * @Description: 定时抓取数据,只有在用户当前登陆的情况下
	 * @param    参数
	 * @return     返回类型
	 * @throws
	 */
	public void timimgTaskCatchData();
}
