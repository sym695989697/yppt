package com.ctfo.common.utils;

import java.io.Serializable;

public class GlobalMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String E_SUCCESS = "success";
	
	public static final String E_FAIL = "fail";
	
	public static final String SUCCESS = "操作成功";

	public static final String FAIL = "操作失败";
		
	public static final String REMO_START_OBJECT = "实例化后台接口对象开始...";
		
	public static final String REMO_END_OBJECT = "实例化后台接口对象结束";
		
	public static final String REMO_EXCEPTION = "调用后台失败";
	
	public static final String REMO_CALL_START = "调用后台服务开始...";
	
	public static final String REMO_CALL_END = "调用后台服务结束";
	
	public static final String REMO_CALL_IS_ONLY = "已经存在";
	
	public static final String SPLIT_CHAR = "@";
	

}
