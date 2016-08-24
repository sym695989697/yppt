package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.OilCardTradeRecordVo;


public class QueryTradeRecordListRsp extends BaseBeanRsp implements Body{

	/**
	 * 参数名称	参数编码	是否必填	说明
		累计加油/充值	total	是	类型：double；当type为0时，显示统计总加油消费；当type为1时，表示累计充值总金额
		交易记录	list	是	类型：list；TradeInfoVo
		总记录数	totalNum		类型：int
		时间段标示	TimeType		

	 */
	private static final long serialVersionUID = 1L;
	
	private long totalNum;
	
	private String total;   //累计加油/充值
	
	private List<OilCardTradeRecordVo> list; //卡尾号
	
	private int TimeType;  //1:表示本月，2：表示上月  3：表示前三个月
	
	
	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public int getTimeType() {
		return TimeType;
	}

	public void setTimeType(int timeType) {
		TimeType = timeType;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<OilCardTradeRecordVo> getList() {
		return list;
	}

	public void setList(List<OilCardTradeRecordVo> list) {
		this.list = list;
	}


}
