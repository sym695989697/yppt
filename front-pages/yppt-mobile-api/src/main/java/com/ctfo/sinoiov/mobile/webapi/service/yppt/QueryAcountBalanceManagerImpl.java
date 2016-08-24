package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.chpt.boss.beans.CapitalAccount;
import com.ctfo.chpt.boss.beans.CapitalAccountExampleExtended;
import com.ctfo.chpt.boss.intf.ICHPTGenericManager;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.utils.BeanCopierUtils;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryAcountBalanceReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryAcountBalanceRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryAccountInfoByUserIdRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.service.zcpt.PayManagerUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.util.DateUtil;
import com.ctfo.yppt.baseservice.recharge.cons.ICCardRechargeCons;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：帐户余额查询
 * <br>
 * 描述：
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2015-1-23</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
//基本通过
@Service("queryAcountBalanceManagerImpl")
public class QueryAcountBalanceManagerImpl implements IJsonService {
	
	//打日志信息
	protected static final Log logger = LogFactory.getLog(QueryAcountBalanceManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		
		QueryAcountBalanceRsp queryAcountBalanceRsp = new QueryAcountBalanceRsp();
		QueryAcountBalanceReq queryAcountBalanceReq = (QueryAcountBalanceReq) request.getBody();
		
		
		logger.debug("手机APP客户端调用【查询账户余额（Y300012）】接口，用户ID：" + queryAcountBalanceReq.getUserId());
			try {
			    Map<String,Object> params=new HashMap<String,Object>();
			    params.put("userId",queryAcountBalanceReq.getUserId());
			    params.put("status",ICCardRechargeCons.RECHARGE_APPLY_STATE_DISTR_SUCC);
			    
			    logger.info("查询累计收入");
			    BigDecimal rechargeTotal=ImplementationSupport.getICRechargeApplyManager().countRecharge(params);
			    if(rechargeTotal==null){
			        rechargeTotal=new BigDecimal("0");
                }
			    queryAcountBalanceRsp.setRechargeTotal(PublicStaticParam.fen2Yuan(rechargeTotal).toString());
			    
			    logger.info("查询累计支出");
			    params.put("status","IC-TRADE-SUCCESS");
			    BigDecimal tradeTotal=ImplementationSupport.getICCardTradeInfoManager().countTradeInfo(params);
			    if(tradeTotal==null){
			        tradeTotal=new BigDecimal("0");
			    }
			    queryAcountBalanceRsp.setTradeTotal(PublicStaticParam.fen2Yuan(tradeTotal).toString());
			    
			    logger.info("查询账户余额与可用余额");
			    UppResult  uppResult = PayManagerUtil.invokeUPP(queryAcountBalanceReq, "UPP_QUERY_BY_USERID");
			    if (uppResult.getResult().equals(UppResult.FAILURE)) {
                    // 抛出支付平台的错误信息
                    queryAcountBalanceRsp.setTotalBalance("0");
                    queryAcountBalanceRsp.setUsableBalance("0");
			        logger.error("根据用户Id" + queryAcountBalanceReq.getUserId() + "查询帐户时报错！" + uppResult.getError());
//                    throw new ClientException("根据用户Id" + queryAcountBalanceReq.getUserId() + "查询帐户时报错！" + uppResult.getError());
			    }else{
	                Object data = uppResult.getData();
	                if (data != null && StringUtils.isNotBlank(data.toString())) {
	                    QueryAcountBalanceRsp queryAcountBalanceRspResult = (QueryAcountBalanceRsp) JSONObject
	                            .toBean(JSONObject.fromObject(uppResult.getData()),
	                                    QueryAcountBalanceRsp.class);
	                    BigDecimal totalBalance=PublicStaticParam.fen2Yuan(new BigDecimal(queryAcountBalanceRspResult.getTotalBalance())) ;
	                    BigDecimal usableBalance=PublicStaticParam.fen2Yuan(new BigDecimal(queryAcountBalanceRspResult.getUsableBalance()));
	                    queryAcountBalanceRsp.setTotalBalance(totalBalance.toString());
	                    queryAcountBalanceRsp.setUsableBalance(usableBalance.toString());
	                }
			    }
			    

                
		    } catch (Exception e) {
				logger.error(">>>>>>>>>>>>>>>>>>>>");
				logger.error("查询帐户余额失败", e);
				logger.error(">>>>>>>>>>>>>>>>>>>>");
				throw new ClientException(String.format("查询帐户余额失败,userId=%s", queryAcountBalanceReq.getUserId()), e);
			}
	
		
		return queryAcountBalanceRsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryAcountBalanceReq req = (QueryAcountBalanceReq) request.getBody();
		if(StringUtils.isBlank(req.getMemberId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryAcountBalanceReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
