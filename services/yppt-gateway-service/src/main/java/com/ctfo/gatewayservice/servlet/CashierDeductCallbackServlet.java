package com.ctfo.gatewayservice.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.csm.utils.SpringBUtils;
import com.ctfo.gatewayservice.util.PayConstants;
import com.ctfo.upp.security.ConvertUtils;
import com.ctfo.yppt.baseservice.recharge.intf.IICRechargeApplyManager;

/**
 * 收银台扣款后的回调
 * 
 * @author sunchuanfu
 */
public class CashierDeductCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 8118869891222708732L;
	private static final Log logger = LogFactory.getLog(CashierDeductCallbackServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	// http://localhost:8080/gatewayService/callback.action?
	// workOrderNo=123321&result=1&merchantOrderAmount=0.01&orderNo=11&payType=NET
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream outStream = null;
		String data = null;
		String encryptkey = null;
		String dataSource = null;
		try {
			data = req.getParameter("data");
			dataSource = req.getParameter("dataSource");// 数据来源
			encryptkey = req.getParameter("encryptkey");
			// String merchantcode = req.getParameter("merchantcode");
			logger.info("接收到回调参数：data--" + data + ", encryptkey--" + encryptkey);
			// 返回SUCCESS
			outStream = resp.getOutputStream();
			outStream.print("SUCCESS");
			outStream.flush();
		} catch (Exception e) {
			logger.error("回复支付平台回调时报错！", e);
			outStream.print("FAIL");
			outStream.flush();
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
				}
			} catch (Exception e) {
				logger.error("关闭输入流出现异常!", e);
			}
		}

		if (data == null || encryptkey == null)
			return;

		try {
			// 根据不同的数据来源,hessian调用油品后台服务接口
			String privateKey = null;
			if (Integer.parseInt(dataSource) == 0) {
				// 使用本系统的私钥
				privateKey = PayConstants.myPrivateKey;
			} else {
				// 使用web-api系统的私钥
				privateKey = PayConstants.myWebApiPrivateKey;
			}
			
			// 继续回调油品后台服务
			String json = ConvertUtils.decodeParamJson(data, encryptkey, privateKey,
					PayConstants.uppPublicKey);

			@SuppressWarnings("unchecked")
			Map<String, String> mp = (Map<String, String>) JSONObject.toBean(JSONObject.fromObject(json), Map.class);
			// 传业务订单
			String workOrderNo = mp.get("workOrderNo");
			// 结果
			String result = mp.get("result");
			// 金额
			String merchantOrderAmount = mp.get("merchantOrderAmount");
			// 支付订单号
			String orderNo = mp.get("orderNo");
			// 支付方式（NET FASTPAY ACCOUNT）
			String payType = mp.get("payType");
			// 支付确认时间
			// TODO 这个时间还没使用
			try {
				String payConfirmDate = mp.get("payConfirmDate");
			} catch (Exception e) {
				logger.warn("支付返回的payConfirmDate 为空！");
			}
			// String workOrderNo = req.getParameter("workOrderNo");
			// String result = req.getParameter("result");
			// String merchantOrderAmount =
			// req.getParameter("merchantOrderAmount");
			// String orderNo = req.getParameter("orderNo");
			// String payType = req.getParameter("payType");
			// 获取油品后台服务
			IICRechargeApplyManager iCRechargeApplyManager = (IICRechargeApplyManager) SpringBUtils
					.getBean("iCRechargeApplyManager");

			// 根据不同的数据来源,hessian调用油品后台服务接口
			if (Integer.parseInt(dataSource) == 0) {
				// web
				iCRechargeApplyManager.callBackPay(workOrderNo, result, new BigDecimal(merchantOrderAmount), orderNo,
						payType);
			} else {
				// app
				iCRechargeApplyManager.callBackPayForApp(workOrderNo, result, new BigDecimal(merchantOrderAmount),
						orderNo, payType);
			}
		} catch (Exception e) {
			logger.error("收银台扣款后的回调再回调油卡后台服务时报错！", e);
		}
	}

}
