package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.AddressVo;


public class PostAddressListRsp extends BaseBeanRsp implements Body {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AddressVo> list;
	private  long totalNum;
	public List<AddressVo> getList() {
		return list;
	}
	public void setList(List<AddressVo> list) {
		this.list = list;
	}
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	

	
}
