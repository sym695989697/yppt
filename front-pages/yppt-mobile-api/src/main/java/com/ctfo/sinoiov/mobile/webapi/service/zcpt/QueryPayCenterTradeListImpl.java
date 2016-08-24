/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.QueryPayCenterTradeListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryPayCenterTradeListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.PayCenterTradeVO;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.BillRrror;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayCenterTradeInfo;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.PayConstants;
import com.ctfo.upp.http.HttpUtils;
import com.ctfo.util.EnvironmentUtil;

/**
 * 
 * 查询支付中心交易列表信息.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
@Service("queryPayCenterTradeListImpl")
public class QueryPayCenterTradeListImpl implements IJsonService {
    protected static final Log logger = LogFactory
            .getLog(QueryPayCenterTradeListImpl.class);

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
        QueryPayCenterTradeListReq req = null;
        QueryPayCenterTradeListRsp rsp = new QueryPayCenterTradeListRsp();
        UppResult uppResult = null;
        try {
            req = (QueryPayCenterTradeListReq) request.getBody();
            if(StringUtils.isBlank(req.getAccountNo())){
            	  logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>用户帐号为空<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            	  return rsp;

            }
            logger.info(String.format("查询支付中心交易列表信息,查询条件%s",JSONObject.fromObject(req)));
            DynamicSqlParameter dsp=new DynamicSqlParameter();
            dsp.setPage(Integer.valueOf(req.getPage()));
            dsp.setRows(Integer.valueOf(req.getRows()));
            
            Map<String,String> mapP=new HashMap<String,String>();
            mapP.put("insideAccountNo", req.getAccountNo());
//            mapP.put("userId", req.getUserId());
            
            if(StringUtils.isNotEmpty(req.getBookAccountTypr())){
                mapP.put("bookaccountType", req.getBookAccountTypr());                
            }
            dsp.setEqual(mapP);
            
            uppResult = invokeUPP(dsp, "UPP_QUERY_ACCOUNT_HISTORY_LIST_BY_ACCOUNT");
            if (uppResult.getResult().equals(UppResult.FAILURE)) {
                throw new ClientException(PayError.E240001, uppResult.getError());
            }
            PaginationResult mp = (PaginationResult) JSONObject.toBean(
                    JSONObject.fromObject(uppResult.getData()), PaginationResult.class);
            
            if(mp.getData()!=null && mp.getData().size()>0){
                List<PayCenterTradeVO> list=(List<PayCenterTradeVO>) JSONArray.toCollection(JSONArray.fromObject(mp.getData()), PayCenterTradeVO.class);
                toObject(list,rsp);
               
            }
            rsp.setTotal(String.valueOf(mp.getTotal()));
            logger.info(String.format("查询支付中心交易列表信息成功,结果为%s", JSONObject.fromObject(rsp)));
            
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }catch (Exception e) {
            logger.error("查询支付中心交易列表信息异常",e);
            throw new ClientException("E200005", BillRrror.E200005);
        }
        return rsp;
    }

    public UppResult invokeUPP(Object param, String methodUrlKey) throws Exception{
        UppResult uppResult = null ;
        try {
            String methodUrlValue = EnvironmentUtil.getInstance()
                    .getPropertyValue(methodUrlKey);
            String requestJson = JSONObject.fromObject(param).toString();
            // 发送POST消息(明文的数据在下面的方法中做了加密，实际POST发送给支付平台是加密后的数据)
            String returnJson = HttpUtils.sendRequest(requestJson,
                    PayConstants.uppURL + methodUrlValue,
                    PayConstants.myPrivateKey, PayConstants.uppPublicKey,
                    PayConstants.myMerchantCode);
            uppResult = (UppResult) JSONObject.toBean(
                    JSONObject.fromObject(returnJson), UppResult.class);
            
            
        } catch (Exception e) {
            logger.error("调用支付中接口出错：", e);
            throw e;
        }
        return uppResult;
    }
    /**
     * 对象转换
     * @param list
     * @param response
     */
    public void toObject(List<PayCenterTradeVO> list,QueryPayCenterTradeListRsp response){
    	if(list != null){
    		List<PayCenterTradeVO> payList = new ArrayList<PayCenterTradeVO>();
    		for(PayCenterTradeVO bean:list){
    			PayCenterTradeVO payBean = new PayCenterTradeVO();
    			payBean.setId(bean.getId());
    			payBean.setBookAccountMoney(PublicStaticParam.fen2Yuan(new BigDecimal(StringUtils.isNotBlank(bean.getBookCurrentMoney())==true?bean.getBookAccountMoney():"0")).toString());
    			payBean.setBookAccountTime(bean.getBookAccountTime());
    			payBean.setBookAccountTypr(bean.getBookAccountTypr());
    			payBean.setBookCurrentMoney(PublicStaticParam.fen2Yuan(new BigDecimal(StringUtils.isNotBlank(bean.getBookCurrentMoney())==true?bean.getBookCurrentMoney():"0")).toString());
    			payBean.setOrderNo(bean.getOrderNo());
    			payBean.setOrderRemarks(bean.getOrderRemarks());
    			payBean.setOrderType(bean.getOrderNo());
    			payBean.setOrderTypeLuc(bean.getOrderTypeLuc());
    			payList.add(payBean);
    		}
    		response.setData(payList);
    		response.setTotal(list.size()+"");
    	}
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
        
        QueryPayCenterTradeListReq  req = (QueryPayCenterTradeListReq) request.getBody();
        if(StringUtils.isBlank(req.getUserId())){
            throw new ClientException("E250001",PayCenterTradeInfo.E250001);
        }
        /*if(StringUtils.isBlank(req.getAccountNo())){
            throw new ClientException("E250002",PayCenterTradeInfo.E250002);
        }*/
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
            classMap.put("body", QueryPayCenterTradeListReq.class);
            Parameter param = (Parameter) JSONObject.toBean(
                    JSONObject.fromObject(request), Parameter.class, classMap);
            return param;
        } catch (Exception e) {
            throw new ClientException("E000005", Common.E000005);
        }
    }
}
