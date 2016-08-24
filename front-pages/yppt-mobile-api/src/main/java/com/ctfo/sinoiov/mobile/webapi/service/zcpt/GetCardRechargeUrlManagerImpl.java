package com.ctfo.sinoiov.mobile.webapi.service.zcpt;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.intf.IJsonService;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Body;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Head;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;
import com.ctfo.sinoiov.mobile.webapi.bean.request.zcpt.GetCardRechargeUrlReq;
import com.ctfo.sinoiov.mobile.webapi.bean.response.zcpt.GetRechargeUrlRsp;
import com.ctfo.sinoiov.mobile.webapi.bean.vo.UppResult;
import com.ctfo.sinoiov.mobile.webapi.exception.ClientException;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Common;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.Login;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.MoneyError;
import com.ctfo.sinoiov.mobile.webapi.util.ErrorMsgConstants.PayError;
import com.ctfo.sinoiov.mobile.webapi.util.PayConstants;

/**
 * 获取"油卡充值"易宝快捷支付URL地址
 * 
 * @author sunchuanfu
 */
@Service("getCardRechargeUrlManagerImpl")
public class GetCardRechargeUrlManagerImpl implements IJsonService {
	protected static final Log logger = LogFactory.getLog(GetCardRechargeUrlManagerImpl.class);

	@Override
	public Body execute(Parameter request, Object... obj) throws ClientException {
		GetRechargeUrlRsp rsp = new GetRechargeUrlRsp();

		try {
			GetCardRechargeUrlReq req = (GetCardRechargeUrlReq) request.getBody();
			req.setCallbackURL(PayConstants.deductCallbackURL);// 回调地址
			req.setUserUa(PayConstants.uppPayLogo);// 跳到易宝后的支付Logo

			UppResult uppResult = PayManagerUtil.invokeUPP(req, "UPP_GET_CARD_URL_FOR_MOBILE");

			if (uppResult.getResult().equals(UppResult.FAILURE)) {
				throw new ClientException(PayError.E240001, uppResult.getError());
			}

			rsp = (GetRechargeUrlRsp) JSONObject.toBean(JSONObject.fromObject(uppResult.getData()),
					GetRechargeUrlRsp.class);
		} catch (ClientException e) {
			logger.error("获取油卡充值易宝快捷支付URL地址时报错！", e);
			throw e;
		} catch (Exception e) {
			logger.error("获取油卡充值易宝快捷支付URL地址时报错！", e);
			throw new ClientException(PayError.E240001, "获取油卡充值易宝快捷支付URL地址时报错！");
		}

		return rsp;
	}

	@Override
	public void validate(Parameter request) throws ClientException {
		if (request == null || request.getBody() == null) {
			throw new ClientException("E240004", PayError.E240004);
		}

		GetCardRechargeUrlReq req = (GetCardRechargeUrlReq) request.getBody();
		// 账户
		if (StringUtils.isBlank(req.getAccountNo())) {
			throw new ClientException("E240005", PayError.E240005);
		}
		// 业务订单号
		if (StringUtils.isBlank(req.getMerchantOrderNo())) {
			throw new ClientException("E240010", PayError.E240010);
		}
		// 金额
		if (StringUtils.isBlank(req.getMerchantOrderAmount())) {
			throw new ClientException("E160001", MoneyError.E160001);
		}
		// 支付平台扣款后的前台回调地址
		if (StringUtils.isBlank(req.getFcallbackURL())) {
			throw new ClientException("E240011", PayError.E240011);
		}
		// 用户id
		if (StringUtils.isBlank(req.getUserId())) {
			throw new ClientException("E240009", PayError.E240009);
		}
		// 商品类型(例如"1")
		if (StringUtils.isBlank(req.getProductCatalog())) {
			throw new ClientException("E240012", PayError.E240012);
		}
		// 商品名称(例如"油卡充值")
		if (StringUtils.isBlank(req.getProductName())) {
			throw new ClientException("E240013", PayError.E240013);
		}
		// 标识类型(例如"2")
		if (StringUtils.isBlank(req.getIdentityType())) {
			throw new ClientException("E240014", PayError.E240014);
		}
		// 标识ID(如果标识类型为2，则这里即为用户id)
		if (StringUtils.isBlank(req.getIdentityId())) {
			throw new ClientException("E240015", PayError.E240015);
		}
		// 终端类型(油品使用值2)(0:IMEI，1:MAC，2:UUID，3:其他)
		if (StringUtils.isBlank(req.getClentType())) {
			throw new ClientException("E240016", PayError.E240016);
		}
		// 终端标示ID(如果终端类型为2，则这里即为用户id)
		if (StringUtils.isBlank(req.getClentId())) {
			throw new ClientException("E240017", PayError.E240017);
		}
		// 登录用户名
		if (StringUtils.isBlank(req.getUserLoginName())) {
			throw new ClientException("E110002", Login.E110002);
		}
		// 客户端IP
		String userIp = req.getUserIP();
		if (StringUtils.isBlank(userIp) || userIp.startsWith("127.") || userIp.startsWith("0.")) {
			// 如果客户端IP传值为空
			// 或者数值为127.*.*.*，或者为0.0.0.0，则设置一个随意的默认值
			req.setUserIP("192.168.1.1");
			// throw new ClientException("E240018", PayError.E240018);
		}
	}

	@Override
	public Parameter convertParameter(String request) {
		try {
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("head", Head.class);
			classMap.put("body", GetCardRechargeUrlReq.class);
			Parameter param = (Parameter) JSONObject.toBean(JSONObject.fromObject(request), Parameter.class, classMap);
			return param;
		} catch (Exception e) {
			throw new ClientException("E000005", Common.E000005);
		}
	}

}
