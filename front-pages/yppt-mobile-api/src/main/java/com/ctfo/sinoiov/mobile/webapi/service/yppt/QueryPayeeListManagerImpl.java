package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.PaginationResult;
import com.ctfo.csm.boss.beans.SimpleCode;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryPayeeListReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryPayeeListRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.PayeeVo;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.service.zcpt.PayManagerUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;

/**
 * 
 * 查询公司对公帐号信息
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月6日    dxs    新建
 * </pre>
 */
@Service("queryPayeeListManagerImpl")
public class QueryPayeeListManagerImpl implements IJsonService {
	
	protected static final Log logger = LogFactory.getLog(QueryPayeeListManagerImpl.class);//日志
	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		QueryPayeeListRsp queryPayeeListRsp = new QueryPayeeListRsp();
		UppResult uppResult = null;
		try {
			List<PayeeVo> list = null;

            logger.info(String.format("查询支付中心对公帐号列表（收件人列表）,查询条件%s","ZJXL_BACK_ACCOUNT"));
            Map<String,String> params=new HashMap<String,String>();
            params.put("type", "ZJXL_BACK_ACCOUNT");
            uppResult = PayManagerUtil.invokeUPP(params, "UPP_QUERY_ACCOUNT_LIST");
            if (uppResult.getResult().equals(UppResult.FAILURE)) {
                throw new ClientException(PayError.E240001, uppResult.getError());
            }		
            
            PaginationResult mp = (PaginationResult) JSONObject.toBean(
                    JSONObject.fromObject(uppResult.getData()), PaginationResult.class);
            if(uppResult.getData()!=null){
            	list=new ArrayList<PayeeVo>();
            	List<SimpleCode> beanList=(List<SimpleCode>) JSONArray.toCollection(JSONArray.fromObject(mp.getData()), SimpleCode.class);
            	SimpleCode simpleCode  = null;
				for(int i=0;i<beanList.size();i++){
					PayeeVo payeeVo = new PayeeVo();
					simpleCode = (SimpleCode)beanList.get(i);
					String desc = simpleCode.getDescription();
					if(StringUtils.isNotBlank(desc)){
						String[] str = desc.split(":");
						payeeVo.setBankName(str[0]);
						payeeVo.setCardNum(str[1]);
						payeeVo.setPayeeId(simpleCode.getCode());
						payeeVo.setCompanyName(simpleCode.getName());
					}
					list.add(payeeVo);
				}
            }
			queryPayeeListRsp.setList(list);
		} catch (ClientException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("查询收款人列表失败：", e);
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			throw new ClientException("查询收款人列表失败：",e);
		}
		
		return queryPayeeListRsp;
		
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryPayeeListReq req = (QueryPayeeListReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryPayeeListReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
