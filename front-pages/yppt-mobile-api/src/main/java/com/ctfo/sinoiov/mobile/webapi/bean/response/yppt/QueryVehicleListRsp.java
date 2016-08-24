package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.VehicleVo;


public class QueryVehicleListRsp extends BaseBeanRsp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     private long totalNum ;
 	private List<VehicleVo> list;
	
	public long getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}


	public List<VehicleVo> getList() {
		return list;
	}


	public void setList(List<VehicleVo> list) {
		this.list = list;
	}


	

}
