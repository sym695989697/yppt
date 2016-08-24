/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryAccountInfoByUserIdReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryPayCenterTradeInfoReq;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryPayCenterTradeListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryAccountInfoByUserIdRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryPayCenterTradeInfoRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryPayCenterTradeListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.PayCenterTradeVO;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayCenterTradeInfo;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;

/**
 * 
 * 查询支付中心单条交易信息.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
@Service("queryPayCenterTradeInfoImpl")
public class QueryPayCenterTradeInfoImpl implements IJsonService {
    protected static final Log logger = LogFactory
            .getLog(QueryPayCenterTradeInfoImpl.class);

    /* (non-Javadoc)
     * @see com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService#execute(com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter, java.lang.Object[])
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月6日    dxs    新建
     * </pre>
     */

    @Override
    public Body execute(Parameter request, Object... obj)
            throws ClientException {
        QueryPayCenterTradeInfoReq req = null;
        QueryPayCenterTradeInfoRsp rsp = new QueryPayCenterTradeInfoRsp();
        UppResult uppResult = null;
        try {
            req = (QueryPayCenterTradeInfoReq) request.getBody();
            logger.info(String.format("查询支付中心单条交易信息,查询条件%s",JSONObject.fromObject(req)));
            uppResult = PayManagerUtil.invokeUPP(req, "UPP_QUERY_ACCOUNT_HISTORY_INFO_BY_ID");
            if (uppResult.getResult().equals(UppResult.FAILURE)) {
                throw new ClientException(PayError.E240001, uppResult.getError());
            }
            rsp = (QueryPayCenterTradeInfoRsp) JSONObject.toBean(
                    JSONObject.fromObject(uppResult.getData()), QueryPayCenterTradeInfoRsp.class);
            logger.info(String.format("查询支付中心单条交易信息成功,结果为%s", JSONObject.fromObject(rsp)));
         }catch (ClientException e) {
             logger.error(e.getMessage(), e);
             throw e;
         } catch (Exception e) {
             logger.error("查询支付中心单条交易信息异常",e);
             throw new ClientException("E200005", BillRrror.E200005);
         }
         return rsp;
            
    }

    /* (non-Javadoc)
     * @see com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService#validate(com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter)
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月6日    dxs    新建
     * </pre>
     */

    @Override
    public void validate(Parameter request) throws ClientException {
        if(request == null || request.getBody() == null){
            throw new ClientException("E120001",UserError.E120001);
        }
        
        QueryPayCenterTradeInfoReq  req = (QueryPayCenterTradeInfoReq) request.getBody();
        if(StringUtils.isBlank(req.getId())){
            throw new ClientException("E250003",PayCenterTradeInfo.E250003);
        }
    }

    /* (non-Javadoc)
     * @see com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService#convertParameter(java.lang.String)
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月6日    dxs    新建
     * </pre>
     */

    @Override
    public Parameter convertParameter(String request) {
        try {
            Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
            classMap.put("head", Head.class);
            classMap.put("body", QueryPayCenterTradeInfoReq.class);
            Parameter param = (Parameter) JSONObject.toBean(
                    JSONObject.fromObject(request), Parameter.class, classMap);
            return param;
        } catch (Exception e) {
            throw new ClientException("E000005", Common.E000005);
        }
    }
}
