package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.CardTypeVo;


public class QueryOilCardTypeRsp extends BaseBeanRsp{

	/**
	 * 参数名称	参数编码	是否必填	说明
		油卡类型列表	List	是	类型：CardTypeVo对象，卡类类型对象
		记录总条数	tatolNum		类型：int

	 */
	private static final long serialVersionUID = 1L;
	

	private List<CardTypeVo> list; //油卡类型列表   CardType；自定义对象
	
	private int tatolNum;

	public int getTatolNum() {
		return tatolNum;
	}


	public void setTatolNum(int tatolNum) {
		this.tatolNum = tatolNum;
	}


	public List<CardTypeVo> getList() {
		return list;
	}


	public void setList(List<CardTypeVo> list) {
		this.list = list;
	}


	

}
