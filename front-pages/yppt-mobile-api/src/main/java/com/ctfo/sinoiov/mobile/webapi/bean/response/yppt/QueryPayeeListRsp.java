package com.ctfo.sinoiov.mobile.webapi.bean.response.yppt;

import java.util.List;

import com.ctfo.sinoiov.mobile.webapi.bean.vo.PayeeVo;

/**
 * 
 * 收款人列表
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
public class QueryPayeeListRsp extends BaseBeanRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 收款人列表	
     */
	private List<PayeeVo> list; 
    /**
     * 总条数	
     */
	private  long totalNum;
	
	/**
	 * @return the list
	 */
	public List<PayeeVo> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<PayeeVo> list) {
		this.list = list;
	}
	/**
	 * @return the totalNum
	 */
	public long getTotalNum() {
		return totalNum;
	}
	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	


}
