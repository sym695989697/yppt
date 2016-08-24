package com.ctfo.yppt.web.intf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.base.service.intf.IStatDataManager;
import com.ctfo.csm.utils.SpringBUtils;

public class WebInterfaceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(WebInterfaceServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	// http://localhost:8080/gatewayService/openapi?serviceCode=queryAllUserCardsBalance
	// http://localhost:8080/gatewayService/openapi?serviceCode=queryCreditBalance
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			logger.info("开始调用 web service 对外接口!");
			resp.setStatus(200);
			String service = req.getParameter("serviceCode");
			String userId = req.getParameter("userId");
			logger.info("开始调用 web service 对外接口!传入参数为 : " + "service:" + service + "\t userId:" + userId);
			if(StringUtils.isBlank(userId)){
				resp.getOutputStream().print("{ result:1, msg:'userId must be not null !'}");
				return;
			}
			if(StringUtils.isBlank(service)){
				resp.getOutputStream().print("{ result:1, msg:'serviceCode must be not null!'}");
				return;
			}
			// 获取油品后台服务
			IStatDataManager statDataManager = (IStatDataManager) SpringBUtils
					.getBean("StatDataManager");
			if("queryAllUserCardsBalance".equalsIgnoreCase(service)){
				Long balance = statDataManager.queryAllUserCardsBalanceByUserId(userId);
				logger.info("开始调用 web service 对外接口!传入参数为 : " + "service:" + service + "\t userId:" + userId + "\t queryAllUserCardsBalanceByUserId : "+ balance);
				resp.getOutputStream().print("{ result:0, msg:'success!',data:" +  balance + "}");
				return;
			}else if("queryCreditBalance".equalsIgnoreCase(service)){
				Long creditBalance = statDataManager.queryCreditBalanceByUserId(userId);
				logger.info("开始调用 web service 对外接口!传入参数为 : " + "service:" + service + "\t userId:" + userId + "\t queryCreditBalanceByUserId : "+ creditBalance);
				resp.getOutputStream().print("{ result:0, msg:'success!',data: " +  creditBalance + "}");
				return;
			}else{
				resp.getOutputStream().print("{ result:1, msg:'not support this service! please input the real code!'}");
				return;
			}
		} catch (Exception e) {
			logger.error("收银台扣款后的回调再回调油卡后台服务时报错！", e);
			resp.getOutputStream().print("{ result:1, msg:'service happened exception!'}");
		}
	}
}
