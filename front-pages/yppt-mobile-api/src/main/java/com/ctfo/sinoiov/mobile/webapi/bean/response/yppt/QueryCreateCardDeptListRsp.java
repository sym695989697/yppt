package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.CardCreateDpetVo;


public class QueryCreateCardDeptListRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<CardCreateDpetVo> list;  //开卡机关名称
	
	private int totalNum;	//总记录数
	

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<CardCreateDpetVo> getList() {
		return list;
	}

	public void setList(List<CardCreateDpetVo> list) {
		this.list = list;
	}

	

}
