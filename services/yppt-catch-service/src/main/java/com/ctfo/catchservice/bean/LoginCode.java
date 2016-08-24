package com.ctfo.catchservice.bean;


/**
 * 登录返回状态码
 * 
 * @author jichao
 */
public class LoginCode {

	/**
	 * 登录失败
	 */
	public final static String LOGIN_FAILURE="A0000";
	
	/**
	 * 登录成功
	 */
	public final static String LOGIN_SUCESS="A0001";
	
	/**
	 * 验证码为空
	 */
	public final static String CHECKCODE_NULL="C0000";
	
	/**
	 * 验证码错误
	 */
	public final static String CHECKCODE_ERROR="C0001";
	
	/**
	 * 用户类型为空
	 */
	public final static String USERTYPE_NULL="T0000";
	
	/**
	 * 用户类型不合法
	 */
	public final static String USERTYPE_ERROR="T0001";
	
	/**
	 * 用户名不合法
	 */
	public final static String USERNAME_ERROR="U0001";
	
	/**
	 * 用户名为空
	 */
	public final static String USERNAME_NULL="U0000";
	
	/**
	 * 密码为空
	 */
	public final static String PASSWORD_NULL="P0000";
	
	/**
	 * 密码不合法
	 */
	public final static String PASSWORD_ERROR="P0001";
	
	/**
	 * 省为空
	 */
	public final static String PROVINCE_NULL = "S0000";
	
	/**
	 * 中石油(区分用户类型)
	 */
	public final static String USERPRE_ZSY="ZSY_";
	/**
	 * 中石化(区分用户类型)
	 */
	public final static String USERPRE_ZSH="ZSH_";
	
	/**
	 * 中石油
	 */
	public final static String ZSY="ZSY";
	
	/**
	 * 中石化
	 */
	public final static String ZSH="ZSH";
	
}
