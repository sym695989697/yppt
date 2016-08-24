package com.ctfo.catchservice.interfaces.external;

import java.util.Map;

import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.bean.UserStatus;
import com.ctfo.catchservice.exception.CatchException;

/**
 * 服务器相关信息服务接口
 * 
 * @author jichao
 */
public interface IServerService {
	/**
	 * 获取指定用户的登录信息
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param username 用户名称
	 */
	public UserStatus getUserStatus(String hostType,String username) throws CatchException;

	/**
	 * 获取所有用户的登录信息
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 */
	public Map<String, UserStatus> getAllUserStatus(String hostType)throws CatchException;
	
	/**
	 * 获取验证码与Cookies值
	 * 1.返回Cookies值
	 * 2.生成验证码图片
	 * 
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 */
	public String[] getLoginCookiesAndCheckCode(String hostType,Map<String,String> browerInfo)throws CatchException;
	
	/**
	 * 用户登录接口
	 * @param userType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param cookies 请求验证码cookies
	 * @param username 用户名
	 * @param code 验证码值
	 */
	public String login(String userType, String cookies, String username, String code) throws CatchException;

	/**
	 * 当前服务运行状态接口
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 */
	public JResult getServiceStatus(String hostType)throws CatchException;
}
