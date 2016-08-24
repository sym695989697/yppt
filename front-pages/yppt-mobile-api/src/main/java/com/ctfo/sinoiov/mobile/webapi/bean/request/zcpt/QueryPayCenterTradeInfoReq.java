/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt;

import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.BaseBeanReq;

/**
 * 
 * 类说明.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
public class QueryPayCenterTradeInfoReq  extends BaseBeanReq {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 交易ID
     */
    private String id;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
