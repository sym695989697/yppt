package com.ctfo.sinoiov.mobile.webapi.base.filter;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Index;


/**
 * tokenId校验
 * @author 徐宝
 *
 */
public class TokenAuthenticationUtil {
	private static ThreadLocal<Index> tokenAuthenticationThreadLocalMap = new ThreadLocal<Index>();
	
	/**
	 * 將用戶信息登錄信息与请求线程关联
	 * @param index
	 */
	public static void put(Index index){
		tokenAuthenticationThreadLocalMap.set(index);
	}
	
	/**
	 * 获取用户登录信息
	 * @return
	 */
	public static Index getUserLogin(){
		Index index = null;
		index = tokenAuthenticationThreadLocalMap.get();
		if(index == null){
			index = new Index();
		}
		return index;
	}
	
	/**
	 * 获取用户登录信息
	 * @return
	 */
	public static void removeUserLogin(){
		tokenAuthenticationThreadLocalMap.remove();
	}
	
	
}
