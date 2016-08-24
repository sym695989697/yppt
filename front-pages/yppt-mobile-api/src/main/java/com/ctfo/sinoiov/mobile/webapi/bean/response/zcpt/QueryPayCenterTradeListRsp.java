/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt;
import java.io.Serializable;
import java.util.List;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.PayCenterTradeVO;

/**
 * 
 * 类说明.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
public class QueryPayCenterTradeListRsp  implements Body, Serializable {
    
    /**
     * 总条数
     */
    private String total;
    /**
     * 数据
     */
    private List<PayCenterTradeVO>  data ;

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the data
     */
    public List<PayCenterTradeVO> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<PayCenterTradeVO> data) {
        this.data = data;
    }
    
}
