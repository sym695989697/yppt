package com.ctfo.yppt.bean;

import java.io.Serializable;
import java.util.List;

import com.ctfo.yppt.baseservice.card.beans.ICCardApply;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
/**
 * 
 * 
 * IC卡申请复杂对象.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月20日    dxs    新建
 * </pre>
 */
public class ICCardApplyMetaBean  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4199410980037028495L;
	
	/**
	 * IC卡申请实体对象
	 */
	private ICCardApply iCCardApply ;
	/**
	 * IC卡申请明细实体对象
	 * */
    private List<ICCardApplyDetail> iCCardApplyDetail ;
    
    
	public ICCardApply getiCCardApply() {
		return iCCardApply;
	}
	public void setiCCardApply(ICCardApply iCCardApply) {
		this.iCCardApply = iCCardApply;
	}
	public List<ICCardApplyDetail> getiCCardApplyDetail() {
		return iCCardApplyDetail;
	}
	public void setiCCardApplyDetail(List<ICCardApplyDetail> iCCardApplyDetail) {
		this.iCCardApplyDetail = iCCardApplyDetail;
	}
    
}
