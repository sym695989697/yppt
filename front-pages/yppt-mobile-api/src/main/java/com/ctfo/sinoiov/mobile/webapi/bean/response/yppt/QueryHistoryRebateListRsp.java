package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.RebateVo;


public class QueryHistoryRebateListRsp extends BaseBeanRsp{

	/**
	 * 参数名称	参数编码	是否必填	说明
		历史返利列表	list	是	类型：list；每月返利的旺金币集合,list中的实体是Rebate对象。
		记录条数量	totalNum	是	类型：整型；
		累计返利旺金币数量	totalCoinCount	是	类型：整型； 

	 */
	private static final long serialVersionUID = 1L;
	

	private List<RebateVo> list;  //历史返利列表
	
	private int totalNum;		//记录条数量
	
	private int totalCoinCount;  //累计返利旺金币数量
	
	private String result;	//申请状态
	
	private String msg;  //申请反馈信息
	
	public List<RebateVo> getList() {
		return list;
	}
	public void setList(List<RebateVo> list) {
		this.list = list;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getTotalCoinCount() {
		return totalCoinCount;
	}
	public void setTotalCoinCount(int totalCoinCount) {
		this.totalCoinCount = totalCoinCount;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
