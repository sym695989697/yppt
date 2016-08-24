package com.ctfo.catchservice.bean;
/**
 * 
 * @Description: 定义任务队列工作类型，抓取任务名称
 * @author yuguangyang
 * @date 2015年2月28日 下午1:56:52
 *
 */
public class WorkerTypes {
	/**
	 *抓取主副卡 
	 */
	public static final int CATCH_MAINANDSUBCARD=0;
	/**
	 * 抓取卡余额
	 */
	public static final int CATCH_CARDBALANCE=1;
	/**
	 * 抓取主卡交易记录
	 */
	public static final int CATCH_MAINCARD_TRADEINFO=2;
	/**
	 * 抓取副卡交易记录
	 */
	public static final int CATCH_SUBCARD_TRADEINFO=3;
}
