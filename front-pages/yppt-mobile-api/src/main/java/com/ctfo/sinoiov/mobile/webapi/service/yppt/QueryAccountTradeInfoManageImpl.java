package com.ctfo.sinoiov.mobile.webapi.service.yppt;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.chpt.boss.beans.AccountIO;
import com.ctfo.chpt.boss.intf.ICHPTGenericManager;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.QueryAccountTradeInfoReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.yppt.QueryAccountTradeInfoRsp;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApply;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetail;
import com.ctfo.yppt.baseservice.recharge.beans.ICRechargeApplyDetailExampleExtended;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：帐户交易详情
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
@Service("queryAccountTradeInfoManageImpl")
public class QueryAccountTradeInfoManageImpl implements IJsonService{

	//打日志信息
	protected static final Log logger = LogFactory.getLog(QueryAccountTradeInfoManageImpl.class);
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		//响应客户端的大对象
		QueryAccountTradeInfoRsp tradeInfoRsp = new QueryAccountTradeInfoRsp();
		//客户端请求数据的大对象
		QueryAccountTradeInfoReq tradeInfoReq = (QueryAccountTradeInfoReq) request.getBody();
		
		//拼接查询条件
		AccountIO accountIO = new AccountIO();
		accountIO.setId(tradeInfoReq.getTradeId());
		//获取数据库中的数据
		AccountIO accountIO2 = new AccountIO();
		try {
			accountIO2 = null;//(AccountIO) iCHPTManager.getModelById(accountIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.error("移动服务端通过tradeId查询cardtradeinfo表失败tradeid为："+tradeInfoReq.getTradeId());
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		//实际返回给手机客户端的数据
		if(accountIO2!=null){
			if(accountIO2.getAccountBal()!=null){
				BigDecimal a = accountIO2.getAccountBal().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
				tradeInfoRsp.setBalance(""+a);
			}
			if(accountIO2.getOperateTime()!=null){
				tradeInfoRsp.setTradeTime(accountIO2.getOperateTime().toString());
			}
			if(accountIO2.getAmount()!=null){
				BigDecimal b = accountIO2.getAmount().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
				tradeInfoRsp.setTradeMoney(""+b);
			}
		}
		
		ICRechargeApply icRechargeApply = new ICRechargeApply();
		icRechargeApply.setId(accountIO2.getApplyId());
		ICRechargeApply cardApply2 = null;
		try {
			cardApply2 = null;// (ICRechargeApply) iicCardGenericManager.getModelById(icRechargeApply);
			
//			tradeInfoRsp.setCardNum(icRechargeApply.getRemittanceAccount());
//			tradeInfoRsp.setVhicleNum(icRechargeApply.());
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
			throw new  ClientException("通过卡号查询卡交易对象失败 cardid="+accountIO2.getApplyId(),e);
		}
		
		if("10".equals(accountIO2.getTradeMode())){ //账户充值
			tradeInfoRsp.setTradeType(1);
			
		}else if("13".equals(accountIO2.getTradeMode())||"23".equals(accountIO2.getTradeMode())){  //卡充值
			tradeInfoRsp.setTradeType(2);
			
			
			List list = new ArrayList();
			ICRechargeApplyDetailExampleExtended exampleExtended = new ICRechargeApplyDetailExampleExtended();
			exampleExtended.createCriteria().andApplyIdEqualTo(accountIO2.getApplyId());
			
			
			List cardList = new ArrayList();
			List vhicleList = new ArrayList();
			List tradeList = new ArrayList();
			try {
				list = null;//iicCardGenericManager.getModels(exampleExtended);
				
				if(!list.isEmpty()){
					for (Object o : list) {
						ICRechargeApplyDetail detail = (ICRechargeApplyDetail)o;
						if(StringUtils.isNotBlank(detail.getCardNo())){
							cardList.add(detail.getCardNo());
						}
						if(detail.getMoney()!=null){
							BigDecimal b = detail.getMoney().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
							tradeList.add(b.doubleValue());
						}
						if(StringUtils.isNotBlank(detail.getVehicleNo())&&!"null".equals(detail.getVehicleNo())){
							vhicleList.add(detail.getVehicleNo());
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
				throw new  ClientException("通过卡号查询卡交易詳情对象失败 cardid="+accountIO2.getApplyId(),e);
			}
			
			tradeInfoRsp.setCardList(cardList);
			
			tradeInfoRsp.setVhicleList(vhicleList);
			
			tradeInfoRsp.setTradeList(tradeList);
		}
		
		
/*      参数名称	参数编码	是否必填	说明
		交易后余额	balance	是	类型：double
		交易类型	tradeType	是	类型：int  1：表示卡充值 2：表示账户充值 
		交易金额	tradeMoney	是	类型：double
		卡号	crdNum	是	类型：字符串
		车牌号	vhicleNum	是	类型：字符串
		交易时间	tradeTime	是	类型：字符串：时间戳
*/		
		
		return tradeInfoRsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		QueryAccountTradeInfoReq req = (QueryAccountTradeInfoReq) request.getBody();
		/*if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120011",UserError.E120011);
		}*/
		if(StringUtils.isBlank(req.getTradeId())){
			throw new ClientException("E120001",UserError.E120001);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", QueryAccountTradeInfoReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
