package com.ctfo.chpt.service.iccard.trade;

import java.util.Map;

import com.ctfo.base.service.IService;
import com.ctfo.chpt.bean.iccard.catchdata.vo.CatchdataGetYZMVO;
import com.ctfo.yppt.baseservice.system.beans.ICCardAccountConfig;

/**
 * 
 * @ClassName: ICatchdataService
 * @Description:抓取交易记录账户管理 service接口管理
 * @author yuguangyang
 * @date 2015年1月22日 下午2:04:36
 *
 */
public interface ICatchdataService extends IService {
	
	/**
	 * 
	 * @Description: 根据ID，查询账户信息
	 * @param    参数
	 * @return     返回类型
	 * @throws
	 */
	public ICCardAccountConfig queryAccountByID(Object model);
	/**
	 * 
	 * @Description:更新用户信息
	 * @param    参数
	 * @return     返回类型
	 * @throws
	 */
	public String updateAccount(Object model);
	/**
	 * 
	 * @Description:删除用户名信息
	 * @param    参数
	 * @return     返回类型
	 * @throws
	 */
	public String deleteAccount(Object model);
	
	/**
	 * 
	 * @Description: 用户登陆，获取验证码
	 * @param    hostType 网站类型  中石化/中石油
	 * @param    userName 请求验证码用户
	 * @return     CatchdataGetYZMVO 储存验证码路径，cookies等信息
	 */
	public CatchdataGetYZMVO getYZM(String hostType,String userName,Map<String,String> browerInfo);
	
	/**
	 * 
	 * @Description:用户登陆
	 * @param    hostType  网站类型  中石化或中石油
	 * @param   	cookies    请求验证码时cookies
	 * @param  	username 用户名
	 * @param    password 密码
	 * @param    code         验证码值
	 * @return     返回类型
	 * @throws
	 */
	public String login(String hostType, String cookies, String username, String code);
	
	/**
	 * 
	 * @Description: 获取主副卡数据
	 * @param		hostType    网站类型
	 * @param   userName 用户名 
	 * @return     返回状态
	 * @throws
	 */
	public String catchMainAndSubCard(String hostType,String userName);
	
	/**
	 * 
	 * @Description:刷新卡余额
	 * @param    hostType  网站类型
	 * @param  userName  用户名 
	 * @return     返回类型
	 * @throws
	 */
	public String catchCardBalance(String hostType,String userName);
	
	
	/**
	 * 
	 * @Description: 抓取账户交易记录数据  主卡数据
	 * @param    hostType   网站类型
	 * @param    startDate  开始日期
	 * @param		endDate   结束日期
	 * @param    userName 用户名
	 * @return     返回类型
	 * @throws
	 */
	public String catchAccountTrade(String hostType, String startDate, String endDate,String userName);
	
	/**
	 * 
	 * @Description: 抓取消费交易记录数据    子卡数据
	 * @param    hostType   网站类型
	 * @param    startDate  开始日期
	 * @param		endDate   结束日期
	 * @param    userName 用户名
	 * @return     返回类型
	 * @throws
	 */
	public String catchTradeByConsume(String hostType, String startDate, String endDate,String userName);
	
}
