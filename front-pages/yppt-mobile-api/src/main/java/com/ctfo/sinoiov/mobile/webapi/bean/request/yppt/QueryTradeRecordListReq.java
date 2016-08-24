package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryTradeRecordListReq  extends BaseBeanReq{

	/**
	 * 参数名称	参数编码	是否必填	说明
		用户Id	userId	是	类型：字符串；
		卡号	cardNum	否	类型：字符串；
		消费类型	type	是	0：表示加油；1：表示充值
		日期	startTime	   是	类型：字符串；时间戳 比如： 毫秒
		结束时间	endTime	否	时间戳
		页数	page		类型：int 从1开始
		记录条数	pageSize		类型：int

	 */
	private static final long serialVersionUID = 1L;
	
	
	private String cardNum;//卡号
	
	private int type;//消费类型  0：表示加油；1：表示充值
	
	private String timeType;	//1 本月 2 上月 3近三月
	
	private String startTime;//开始日期
	
	private String endTime;		//结束日期

	private int page;//页数
	
	private int pageSize;//页面记录他条数
	

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	

}
