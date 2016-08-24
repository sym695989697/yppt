package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.AccountTradeInfoVo;


public class QueryInAndOutListRsp extends BaseBeanRsp{

	/**
	 * 参数名称	参数编码	是否必填	说明
	收支明细	list	是	类型：list；自定义对象AccountTradeInfoVo
	记录总条数	totalNum	是	 类型：int

	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccountTradeInfoVo> list;  //收支明细
	
	private  long totalNum;

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public List<AccountTradeInfoVo> getList() {
		return list;
	}

	public void setList(List<AccountTradeInfoVo> list) {
		this.list = list;
	}

	
	
}
