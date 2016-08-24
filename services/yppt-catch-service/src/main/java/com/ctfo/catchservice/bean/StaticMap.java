package com.ctfo.catchservice.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardMain;

/**
 * 保存所有登录用户信息的MAP
 * 
 * @author jichao
 */
public class StaticMap {
	//保留用户session，登录状态等数据 username(username为加hosttype前缀的名字）：session
	public static Map<String, ClientSession> clientMap = new HashMap<String, ClientSession>();
	//缓存本地主卡
	public static Map<String,Map<String,ICCardMain>> getlocalMainCard = new HashMap<String,Map<String,ICCardMain>>();
	//最后发送短信时间
	public static long getLastSendMessage=0;
	//缓存主卡余额   卡号:主卡信息
	public static List<ICCardMain> cardMainBalance = new ArrayList<ICCardMain>();
	//本地主卡 数据 数据结构为(key=主卡卡号:value=副卡列表)
	public static Map<String,List<ICCard>> getLocalSubCard = new HashMap<String,List<ICCard>>();
	public static Map<String,List<String>> suncardlist = new HashMap<String,List<String>>();
	
	//缓存中石油主卡;key=用户名 value为该用户的主卡
	public static Map<String,String> zsyUserMainCard = new HashMap<String,String>();
	//记录用户发送短信信息
	public static Map<String,Long> userLastSendMessage = new HashMap<String,Long>();
}
 