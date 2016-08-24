package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.SimpleCodeVo;


public class QueryCreateCardDeptRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<SimpleCodeVo> simpleCode;  //开卡机关名称
	
	private int totalNum;	//总记录数
	


	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<SimpleCodeVo> getSimpleCode() {
		return simpleCode;
	}

	public void setSimpleCode(List<SimpleCodeVo> simpleCode) {
		this.simpleCode = simpleCode;
	}
	
	

}
