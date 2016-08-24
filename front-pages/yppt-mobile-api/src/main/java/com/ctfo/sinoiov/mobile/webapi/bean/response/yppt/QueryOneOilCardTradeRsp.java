package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.OneOilCardTradeVo;


public class QueryOneOilCardTradeRsp extends BaseBeanRsp implements Body{

	/**
	 * 参数名称	参数编码	是否必填	说明
		消费	list	是	类型：OilCardTradeinfoVo卡消费记录的实体
		总记录条数	TotalNum	是	类型：int

	 */
	private static final long serialVersionUID = 1L;
	
	private long totalNum;

	private List<OneOilCardTradeVo> list;  //單張卡消费记录的实体
	

	public long getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}


	public List<OneOilCardTradeVo> getList() {
		return list;
	}


	public void setList(List<OneOilCardTradeVo> list) {
		this.list = list;
	}



	
} 
