package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.RebateVo;


public class GetRebateListRsp extends BaseBeanRsp{

	/**
	 * 参数名称	参数编码	是否必填	说明
		历史返利列表	list	是	类型：list；每月返利的旺金币集合,list中的实体是Rebate对象。
		记录条数量	totalNum	是	类型：整型；
		累计返利旺金币数量	totalCoinCount	是	类型：整型； 

	 */
	private static final long serialVersionUID = 1L;

	private List<RebateVo> list;		//历史返利列表
	
	private long totalNum;		//记录条数量
	
	private long totalCoinCount;    //累计返利旺金币数量
	
	public long getTotalCoinCount() {
		return totalCoinCount;
	}

	public void setTotalCoinCount(long totalCoinCount) {
		this.totalCoinCount = totalCoinCount;
	}


	public List<RebateVo> getList() {
		return list;
	}

	public void setList(List<RebateVo> list) {
		this.list = list;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

}
