package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.OilCardVo;


public class QueryOilCardListRsp extends BaseBeanRsp{
	
	/**
	 * 参数名称	参数编码	是否必填	说明
		我的油卡	list	是	类型：list；一个用户下的所有油卡列表集合 ,list中的对象是OilCardVo
		油卡总数	totalNum	是	类型：int

	 */
	private static final long serialVersionUID = 1L;
	
	private List<OilCardVo> list;
	
	private  long totalNum;
	
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	public List<OilCardVo> getList() {
		return list;
	}
	public void setList(List<OilCardVo> list) {
		this.list = list;
	}


}

