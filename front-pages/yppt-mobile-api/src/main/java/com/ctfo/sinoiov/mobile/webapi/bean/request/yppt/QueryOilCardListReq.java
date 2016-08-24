package com.ctfo.sinoiov.mobile.webapi.bean.request.yppt;

public class QueryOilCardListReq extends BaseBeanReq{
	
	/**
	 * 	参数名称	参数编码	是否必填	说明
		用户id	userId	是	类型：字符串；用户的ID
		卡号	cardNum	    否	类型：字符串；
		车牌号	vehicleNum	 否	类型：字符串；
		车牌颜色	vehicleColor	 否	类型：字符串
		 搜索键	queryKey		类型：字符串；模糊查询的关键字
		页数	page	是	类型：int
		页面记录条数	pageSize	是	类型：int


	 */
	private static final long serialVersionUID = 1L;

	
	private String cardNum ;			//卡号
	
	private String vehicleNum ;			//车牌号
	
	private String vehicleColor;		//车牌颜色
	
	private String queryKey;			//模糊查询的关键字
	
	private int page ; 					//页数
	
	private int pageSize ;				//页面记录条数
	
	private String applyType;              //新增 -- 查询不同卡审核状态 0为待审核，1为审核通过，2为审核不通过
	
	

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}


	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
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
	
	
}
