package com.ctfo.catchservice.interfaces.internal;

import java.util.Map;

import com.ctfo.catchservice.bean.UserStatus;
import com.ctfo.catchservice.exception.CatchException;

/**
 * 登录内部接口
 * 
 * @author jichao
 */
public interface ILoginInternalService {

	/**
	 * 获取验证码与Cookies值
	 * 1.返回Cookies值
	 * 2.生成验证码图片
	 * 
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @return 数组       0:cookies，1：图片路径 
	 */
	public String[] getLoginCookiesAndCheckCode(String hostType,Map<String,String> browerInfo)throws CatchException;
	
	/**
	 * 登录网站
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param cookies  请求验证码时cookies
	 * @param username 用户名称
	 * @param password 登录密码
	 * @param code	   验证码
	 * @param cookie 
	 * @return
	 */
	public String login(String hostType, String cookies,String username, String code)throws CatchException;
	
	/**
	 * 获取指定用户的登录信息
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 * @param username 用户名称
	 */
	public UserStatus getUserStatus(String hostType,String username)throws CatchException;
	
	/**
	 * 获取所有用户的登录信息
	 * @param hostType 主机类型：中石油：ZSY 中石化：ZSH 
	 */
	public Map<String, UserStatus> getAllUserStatus(String hostType)throws CatchException;
}
