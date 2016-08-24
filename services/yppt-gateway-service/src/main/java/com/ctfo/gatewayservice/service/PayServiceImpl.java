package com.ctfo.gatewayservice.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.gatewayservice.bean.UppResult;
import com.ctfo.gatewayservice.interfaces.bean.EnumYpptGateWayErrorCodes;
import com.ctfo.gatewayservice.interfaces.bean.YpptGatewayException;
import com.ctfo.gatewayservice.interfaces.bean.pay.Account;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.CreateAccountSmsCodeParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneyParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.DeductMoneySureResponse;
import com.ctfo.gatewayservice.interfaces.bean.pay.FreezeBalanceParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.GetCashierDeductUrlParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.PayOrderResponse;
import com.ctfo.gatewayservice.interfaces.bean.pay.RechargeToUserAccountParams;
import com.ctfo.gatewayservice.interfaces.bean.pay.TransferAccountParams;
import com.ctfo.gatewayservice.interfaces.service.IPayService;
import com.ctfo.gatewayservice.util.EnvironmentUtil;
import com.ctfo.gatewayservice.util.MD5Encoder;
import com.ctfo.gatewayservice.util.PayConstants;
import com.ctfo.upp.http.HttpUtils;

/**
 * 与支付中心对接
 * 
 * @author sunchuanfu
 */
@Service("IPayService")
public class PayServiceImpl implements IPayService {

	private static final Log logger = LogFactory.getLog(PayServiceImpl.class);

	/**
	 * POST请求支付中心
	 * 
	 * @param mapParam
	 *            Map结构参数
	 * @param methodUrlKey
	 *            方法URL(例如/account/createAccount)
	 * @return UppResult对象
	 * @throws YpptGatewayException
	 */
	private UppResult invokeUPP(Map<String, String> mapParam, String methodUrlKey) throws YpptGatewayException {
		String methodUrlValue = EnvironmentUtil.getInstance().getPropertyValue(methodUrlKey);
		UppResult uppResult = null;
		try {
			String requestJson = JSONObject.fromObject(mapParam).toString();

			// 发送POST消息(明文的数据在下面的方法中做了加密，实际POST发送给支付平台是加密后的数据)
			String returnJson = HttpUtils.sendRequest(requestJson, PayConstants.uppURL + methodUrlValue,
					PayConstants.myPrivateKey, PayConstants.uppPublicKey, PayConstants.myMerchantCode);

			uppResult = (UppResult) JSONObject.toBean(JSONObject.fromObject(returnJson), UppResult.class);
		} catch (Exception e) {
			logger.error("调用支付中接口出错：", e);
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.ZF_FAIL.getCode(),
					EnumYpptGateWayErrorCodes.ZF_FAIL.getMsg());
		}

		if (uppResult.getResult().equals(UppResult.FAILURE)) {
			// 把支付平台系统的错误码转换为网关系统的错误码
			uppResult.setErrorcode("ZF" + uppResult.getErrorcode());
			throw new YpptGatewayException(uppResult.getErrorcode(), uppResult.getError());
		}

		return uppResult;
	}

	@Override
	public String createAccount(CreateAccountParams createAccountParams) throws YpptGatewayException {
		try {
			// 支付密码MD5加密
			createAccountParams.setPayPassword(MD5Encoder.getMD5String(createAccountParams.getPayPassword()));

			UppResult result = this.invokeUPP(this.putValueIntoMap(createAccountParams), "UPP_CREATE_ACCOUNT");

			@SuppressWarnings("unchecked")
			Map<String, String> mp = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					Map.class);

			return mp.get("accountNo");
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public String createAccountSmsCode(CreateAccountSmsCodeParams createAccountSmsCodeParams)
			throws YpptGatewayException {
		try {
			String payPassword = createAccountSmsCodeParams.getMD5();
			if (StringUtils.isNotBlank(payPassword)) {
				// 支付密码MD5加密
				createAccountSmsCodeParams.setMD5(MD5Encoder.getMD5String(createAccountSmsCodeParams.getMD5()));
			}
			UppResult result = this.invokeUPP(this.putValueIntoMap(createAccountSmsCodeParams),
					"UPP_CREATE_ACCOUNT_SMSCODE");

			@SuppressWarnings("unchecked")
			Map<String, String> mp = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					Map.class);

			return mp.get("accountNo");
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public Account queryAccountByAccountNo(String accountNo) throws YpptGatewayException {
		Account account = null;

		Map<String, String> mapParam = new HashMap<String, String>();
		mapParam.put("accountNo", accountNo);

		try {
			UppResult result = this.invokeUPP(mapParam, "UPP_QUERY_BY_ACCOUNTNO");
			account = (Account) JSONObject.toBean(JSONObject.fromObject(result.getData()), Account.class);
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}

		return account;
	}

	@Override
	public Account queryAccountByUserId(String userId) throws YpptGatewayException {
		Account account = null;
		Map<String, String> mapParam = new HashMap<String, String>();
		mapParam.put("userId", userId);

		try {
			UppResult result = this.invokeUPP(mapParam, "UPP_QUERY_BY_USERID");
			account = (Account) JSONObject.toBean(JSONObject.fromObject(result.getData()), Account.class);
		} catch (YpptGatewayException e) {
			if (e.getErrorcode().equals(EnumYpptGateWayErrorCodes.ZF_USER_NOT_EXISTED.getCode())) {
				logger.error("帐户不存在", e);
				// 如果帐户不存在返回null
				return null;
			}
			// 其它异常抛出
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}

		return account;
	}

	@Override
	public String transferAccounts(TransferAccountParams transferAccountParams) throws YpptGatewayException {
		try {
			// 新油品的商户编码
			transferAccountParams.setStoreCode(PayConstants.myMerchantCode);

			UppResult result = this.invokeUPP(this.putValueIntoMap(transferAccountParams), "UPP_TRANSFER_ACCOUNTS");

			PayOrderResponse rsp = (PayOrderResponse) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					PayOrderResponse.class);
			return rsp.getOrderNo();
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public String deductMoney(DeductMoneyParams params) throws YpptGatewayException {
		try {
			// 新油品的商户编码
			params.setStoreCode(PayConstants.myMerchantCode);

			UppResult result = this.invokeUPP(this.putValueIntoMap(params), "UPP_DEDUCTMONEY");

			PayOrderResponse rsp = (PayOrderResponse) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					PayOrderResponse.class);
			return rsp.getOrderNo();
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public DeductMoneySureResponse deductMoneySure(DeductMoneySureParams params) throws YpptGatewayException {
		try {
			UppResult result = this.invokeUPP(this.putValueIntoMap(params), "UPP_DEDUCT_MONEY_SURE");
			DeductMoneySureResponse response = (DeductMoneySureResponse) JSONObject.toBean(
					JSONObject.fromObject(result.getData()), DeductMoneySureResponse.class);
			return response;
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public String freezeBalance(FreezeBalanceParams freezeBalanceParams) throws YpptGatewayException {
		try {
			UppResult result = this.invokeUPP(this.putValueIntoMap(freezeBalanceParams), "UPP_FREEZE_BALANCE");

			PayOrderResponse rsp = (PayOrderResponse) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					PayOrderResponse.class);
			return rsp.getOrderNo();
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public String unFreezeBalance(FreezeBalanceParams unFreezeBalanceParams) throws YpptGatewayException {
		try {
			UppResult result = this.invokeUPP(this.putValueIntoMap(unFreezeBalanceParams), "UPP_UNFREEZE_BALANCE");

			PayOrderResponse rsp = (PayOrderResponse) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					PayOrderResponse.class);
			return rsp.getOrderNo();
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@Override
	public List<String> merchantOrderClearing() throws YpptGatewayException {
		// TODO 对帐后期版本做
		return null;
	}

	@Override
	public String getCashierDeductMoneyUrl(GetCashierDeductUrlParams params) throws YpptGatewayException {
		// 收银台扣款后的回调URL
		params.setCallbackURL(PayConstants.cashierDeductCallbackURL);
		params.setUserUa(PayConstants.uppPayLogo);// 这里不是一个有效的Logo，线上不会替换页面中的"一键支付"
		try {
			return HttpUtils.getCashierDeductMoneyUrl(JSONObject.fromObject(params).toString(),
					PayConstants.uppCashierUrl, PayConstants.myPrivateKey, PayConstants.uppPublicKey,
					PayConstants.myMerchantCode);
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GET_CASHIER_URL_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GET_CASHIER_URL_ERROR.getMsg());
		}
	}

	@Override
	public String rechargeToUserAccount(RechargeToUserAccountParams params) throws YpptGatewayException {
		try {
			// 新油品的商户编码
			params.setStoreCode(PayConstants.myMerchantCode);

			UppResult result = this.invokeUPP(this.putValueIntoMap(params), "UPP_RECHARGE_USERACCOUNT");

			PayOrderResponse rsp = (PayOrderResponse) JSONObject.toBean(JSONObject.fromObject(result.getData()),
					PayOrderResponse.class);
			return rsp.getOrderNo();
		} catch (YpptGatewayException e) {
			throw e;
		} catch (Exception e) {
			throw new YpptGatewayException(EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getCode(),
					EnumYpptGateWayErrorCodes.YPPT_GATEWAY_ERROR.getMsg());
		}
	}

	@SuppressWarnings("all")
	private Map<String, String> putValueIntoMap(Object obj) {
		Map<String, String> mp = new HashMap<String, String>();
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		String fieldName = null;
		String getterMethodName = null;
		String fieldValue = null;
		for (Field f : fields) {
			fieldName = f.getName();
			if (fieldName.equals("serialVersionUID"))
				continue;
			getterMethodName = "get" + String.valueOf(fieldName.charAt(0)).toUpperCase() + fieldName.substring(1);
			try {
				fieldValue = (String) clazz.getMethod(getterMethodName, null).invoke(obj, null);
			} catch (Exception e) {
				//
			}
			if (fieldValue != null) {
				mp.put(fieldName, fieldValue);
			}
		}
		return mp;
	}

}
