package com.ctfo.catchservice.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.httpclient.methods.GetMethod;

import com.ctfo.catchservice.bean.LoginCode;
import com.ctfo.catchservice.bean.StaticMap;

/**
 * 
 * @ClassName: CatchDataUtils
 * @Description: 抓取数据工具类
 * @author yuguangyang
 * @date 2015年1月27日 上午10:31:33
 *
 */
public class CatchDataUtils {

	private static ResourceBundle resource = ResourceBundle.getBundle("syslogin");

	/**
	 * 根据网站类型获取所有的用户名称
	 * 
	 * @param hostType
	 * @return
	 */
	public static synchronized List<String> getUserNameByType(String hostType) {
		List<String> userNames = new ArrayList<String>();
		for (Iterator<String> itr = StaticMap.clientMap.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
			if (key.contains(hostType)) {
				userNames.add(key);
			}
		}
		return userNames;
	}

	/**
	 * 获取用户存储用户名称  根据usertype不同，为存储的用户名加入相应的前缀
	 * 
	 * @param userType
	 *            用户类型
	 * @param username
	 *            用户名称
	 * @return
	 */
	public static synchronized String getUserStoreName(String userType, String username) {
		// 保存到StaticMap.clientMap中的用户名称
		String userPrefix = userType.toUpperCase().equals("ZSY") ? LoginCode.USERPRE_ZSY : LoginCode.USERPRE_ZSH;
		String userStoreName = userPrefix + username;
		return userStoreName;
	}

	/**
	 * 设置GET头信息（通用头部信息）
	 */
	public static void setRequestHeaderByGet(GetMethod getMethod, String userType) {

		getMethod.setRequestHeader("Host", CatchDataUtils.getHost(userType));
		getMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0");
		getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		getMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		getMethod.setRequestHeader("Connection", "keep-alive");
		getMethod.setRequestHeader("Cache-Control", "max-age=0");
	}

	/**
	 * 根据用户类型获取HOST
	 */
	public static synchronized String getHost(String hostType) {
		return hostType.toUpperCase().equals("ZSY") ? resource.getString("login.zsy.host") : resource.getString("login.zsh.host");
	}

	/**
	 * 获取登录提交来源网址
	 */
	public static synchronized String getPosturi(String hostType) {
		return hostType.toUpperCase().equals("ZSY") ? resource.getString("login.zsy.posturi") : resource.getString("login.zsh.posturi");
	}

	/**
	 * 获取验证码URL地址
	 */
	public static synchronized String getCheckCodeUrl(String hostType) {
		return hostType.toUpperCase().equals("ZSY") ? resource.getString("login.zsy.checkcodeurl") : resource.getString("login.zsh.checkcodeurl");
	}
	//根据传入的hostType返回用户对应的类型，此处对应卡类型
	public static synchronized String getHostType(String hostType){
		String type="";
		if(LoginCode.ZSY.equals(hostType)){
			type="CARD-TYPE-01";
		}else if(LoginCode.ZSH.equals(hostType)){
			type="CARD-TYPE-02";
		}
		return type;
	}
	
	
}
