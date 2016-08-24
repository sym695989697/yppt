package com.ctfo.sinoiov.mobile.webapi.service.yppt;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureResponse;
import com.ctfo.gatewayservice.interfaces.bean.pay.PayOrderResponse;
import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.base.intf.impl.ImplementationSupport;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.PublicStaticParam;
import com.ctfo.sinoiov.mobile.webapi.bean.request.yppt.AccountToOilCardReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.QueryAccountInfoByUserIdRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.service.zcpt.PayManagerUtil;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.TradeInfoError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.UserError;
import com.ctfo.sinoiov.mobile.webapi.util.PayConstants;
import com.ctfo.yppt.bean.ICardRechageMetaBean;
/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：帐卡充值
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
 * <tr><td>1.0</td><td>2015-2-8</td><td>JiangXF</td><td>创建</td></tr>
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
@Service
public class AccountToOilCardManagerImpl implements IJsonService {

	protected static final Log logger = LogFactory.getLog(AccountToOilCardManagerImpl.class);

	
	@Override
	public Body execute(Parameter request, Object... obj)
			throws ClientException {
		logger.info(">>>>>>>>>>>>>>>>>>>>调有帐户支付失败<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		UppResult uppResult = null;
		AccountToOilCardReq req = (AccountToOilCardReq) request.getBody();
		try{
			logger.info(">>>>>>>>>>>>>>>>>>>>业务单ID["+req.getId()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			ICardRechageMetaBean metaBean = ImplementationSupport.getICRechargeApplyManager().getICardRechageMetaBeanByapplyId(req.getId());
			String orderAmount = null;
			logger.info(">>>>>>>>>>>>>>>>>>>>业务单ID["+req.getId()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			if(metaBean != null && metaBean.getRechargeApply() != null){
				logger.info(">>>>>>>>>>>>>>>>>>>>帐户扣款金额转换前["+metaBean.getRechargeApply().getCreditMoney()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				logger.info(">>>>>>>>>>>>>>>>>>>>帐户扣款金额转换后["+PublicStaticParam.fen2Yuan(metaBean.getRechargeApply().getCreditMoney())+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				orderAmount = PublicStaticParam.fen2Yuan(metaBean.getRechargeApply().getCaptialMoney()).toString();
			}
			if(metaBean != null && metaBean.getRechargeApply() != null){
				logger.info(">>>>>>>>>>>>>>>>>>>>业务单号["+metaBean.getRechargeApply().getOrderNo()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				//req.setAccountNo(metaBean.getRechargeApply().getOrderNo());
				req.setOrderNo(metaBean.getRechargeApply().getOrderNo());
			}
			
			logger.info(">>>>>>>>>>>>>>>>>>>>根据用户ID查询帐户信息<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			// 查询帐户的基本信息
			Map<String, String> accountMap = new HashMap<String, String>();
			accountMap.put("userId", req.getUserId());
			uppResult = PayManagerUtil.invokeUPP(accountMap, "UPP_QUERY_BY_USERID");
			QueryAccountInfoByUserIdRsp rsp = (QueryAccountInfoByUserIdRsp) JSONObject.toBean(JSONObject.fromObject(uppResult.getData()),
					QueryAccountInfoByUserIdRsp.class);
			if(rsp == null){
				logger.info(">>>>>>>>>>>>>>>>>>>>该用户暂无帐户<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("该用户暂无帐户！");
			}
			logger.info(">>>>>>>>>>>>>>>>>>>>查询结束:帐户号["+rsp.getAccountNo()+"],帐户金额["+rsp.getUsableBalance()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			Map<String, String> mapParam = new HashMap<String, String>();
			mapParam.put("accountNo", rsp.getAccountNo());
			mapParam.put("MD5", req.getPayPwd());// 注：这里的"MD5"要大写
			mapParam.put("smsCode", req.getMessageCode());
			mapParam.put("mobileNo", req.getPhone());
			uppResult = PayManagerUtil.invokeUPP(mapParam, "UPP_IS_PAYPASSWORD");
			if (uppResult.getResult().equals(UppResult.FAILURE)) {
				logger.info(">>>>>>>>>>>>>>>>>>>>验证帐户支付密码/验证码失败,原因["+uppResult.getError()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				throw new ClientException(uppResult.getError());
			}
			String checkPassResult=(String)JSONObject.fromObject(uppResult.getData()).get("data");
			if(!"1".equals(checkPassResult)){
			    logger.info(">>>>>>>>>>>>>>>>>>>>验证帐户支付密码,密码不正确！<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    throw new ClientException(PayError.E240024);
			}
			logger.info(">>>>>>>>>>>>>>>>>>>>查询结束:帐户号["+rsp.getAccountNo()+"],帐户金额["+rsp.getUsableBalance()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			Map<String, String> params = new HashMap<String, String>();
			//userId 付款用户Id 
			//accountNo 付款账号
			//workOrderNo 业务订单号
			//orderAmount 转账金额(单位元)
			params.put("accountNo", rsp.getAccountNo());
			params.put("userId", req.getUserId());
			params.put("workOrderNo", req.getOrderNo());
			params.put("orderAmount", orderAmount);
			params.put("storeCode", PayConstants.myMerchantCode);
			logger.info(">>>>>>>>>>>>>>>>>>>>进行扣款操作<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			UppResult payResult = PayManagerUtil.invokeUPP(params, "UPP_DEDUCTMONEY");
			if(UppResult.FAILURE.equals(payResult.getResult())){
				logger.info(">>>>>>>>>>>>>>>>>>>>扣款失败，原因["+payResult.getError()+"]<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				throw new ClientException(payResult.getError());
			}
			PayOrderResponse payResponse = (PayOrderResponse) JSONObject.toBean(JSONObject.fromObject(payResult.getData()),
					PayOrderResponse.class);
			logger.info(">>>>>>>>>>>>>>>>>>>>调用扣款结束<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			String payStatus = "-1";
			if(payResponse != null && StringUtils.isNotBlank(payResponse.getOrderNo())){
				logger.info(">>>>>>>>>>>>>>>>>>>>调用扣款成功<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				payStatus = "1";
			}
			logger.info(">>>>>>>>>>>>>>>>>>>>扣款确认开始<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			Map<String,String> payMap = new HashMap<String,String>();
			//accountNo;// 帐户名称
			//orderNo;// 支付订单号
			//merchantOrderNo;// 业务订单号
			//orderAmount;// 金额
			//result;// 结果(1：成功；－1：失败)
			//payType;// 支付类型（NET FASTPAY ACCOUNT）
			/*payMap.put("accountNo", rsp.getAccountNo());
			payMap.put("orderNo", payResponse.getOrderNo());
			payMap.put("merchantOrderNo", req.getOrderNo());
			payMap.put("orderAmount", orderAmount);
			payMap.put("result", payStatus);
			payMap.put("payType", PublicStaticParam.ACCOUNT_MODE);
			UppResult result = PayManagerUtil.invokeUPP(payMap, "UPP_DEDUCT_MONEY_SURE");
			DeductMoneySureResponse response = (DeductMoneySureResponse) JSONObject.toBean(
					JSONObject.fromObject(result.getData()), DeductMoneySureResponse.class);

			if(response == null){
				logger.info(">>>>>>>>>>>>>>>>>>>>扣款操作返回为空<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("扣款操作失败");
			}
			if(UppResult.FAILURE.equals(response.getResult())){
				logger.info(">>>>>>>>>>>>>>>>>>>>扣款确认失败<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("扣款确认失败");
			}*/
			
			logger.info(">>>>>>>>>>>>>>>>>>>>扣款确认结束<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			logger.info(">>>>>>>>>>>>>>>>>>>>回调修改业务单状态开始<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			//扣款成功后调用后台 1成功、-1失败
			String id = ImplementationSupport.getICRechargeApplyManager().callBackPayForApp(req.getOrderNo(), payStatus, new BigDecimal(orderAmount), payResponse.getOrderNo(), PublicStaticParam.ACCOUNT_MODE);
			logger.info(">>>>>>>>>>>>>>>>>>>>回调修改业务单状态结束<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			if(StringUtils.isBlank(id)){
				logger.info(">>>>>>>>>>>>>>>>>>>>修改业务单状态失败<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				throw new ClientException("修改业务单状态失败");
			}
		}catch(ClientException e){
			logger.error("支付失败",e);
			String error = "支付失败";
			if(StringUtils.isNotBlank(e.getErrorCode())){
				error = e.getErrorCode();
			}
			throw new ClientException(error, e);
		}catch(Exception e){
			logger.error(">>>>>>>>>>>>>>>>>>支付失败<<<<<<<<<<<<<<<<<<<<<",e);
			throw new ClientException("支付异常", e);
		}
		
		
		return null;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if(request == null || request.getBody() == null){
			throw new ClientException("E120001",UserError.E120001);
		}
		AccountToOilCardReq req = (AccountToOilCardReq) request.getBody();
		if(StringUtils.isBlank(req.getUserId())){
			throw new ClientException("E120002",UserError.E120002);
		}
		if(StringUtils.isBlank(req.getId())){
			throw new ClientException("E230007",TradeInfoError.E230007);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try{
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	        classMap.put("head", Head.class);
	        classMap.put("body", AccountToOilCardReq.class);
	        Parameter param = (Parameter)JSONObject.toBean(JSONObject.fromObject(request),Parameter.class , classMap);
	        return param;
		}catch(Exception e){
			throw new ClientException("E000005",Common.E000005);
		}
	}

}
