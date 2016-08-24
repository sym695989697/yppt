package com.ctfo.gatewayservice.aop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.file.boss.IMongoService;
import com.ctfo.gatewayservice.bean.YPPTGatewayBusinessLog;

/**
 * 记录日志拦截器
 * 
 * @author sunchuanfu
 */
@SuppressWarnings("rawtypes")
public class LogServiceHandler implements MethodInterceptor {

	protected static final Log logger = LogFactory.getLog(LogServiceHandler.class);

	private IMongoService mongoService;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = invocation.proceed();
		try {
			Class clazz = invocation.getThis().getClass();
			String clazName = clazz.getSimpleName();
			Object[] args = invocation.getArguments();
			String methodName = invocation.getMethod().getName();			

			String responseContent = "";
			if (result != null) {
				if (result instanceof String) {
					if (result.toString().startsWith("{")) {
						responseContent = JSONObject.fromObject(result).toString();
					} else if (result.toString().startsWith("[")) {
						responseContent = JSONArray.fromObject(result).toString();
					} else {
						responseContent = result.toString();
					}
				} else if (result instanceof List) {
					responseContent = JSONArray.fromObject(result).toString();
				} else {
					responseContent = JSONObject.fromObject(result).toString();
				}
			}
			// 保存日志到MongoDB
			this.saveBusinessLog(clazName, methodName, JSONArray.fromObject(args).toString(),
					responseContent, dateFormat.format(new Date()));
		} catch (Exception e) {
			logger.error("保存调用日志发生异常！", e);
		}

		return result;
	}

	/**
	 * 保存日志到MongoDB
	 * 
	 * @param className
	 * @param requestContent
	 * @param responseContent
	 * @param responseTime
	 */
	@SuppressWarnings("unchecked")
	private void saveBusinessLog(String className, String methodName, String requestContent, String responseContent,
			String responseTime) {
		mongoService.setDatasource("LOGS");
		YPPTGatewayBusinessLog log = new YPPTGatewayBusinessLog();
		log.setId(UUID.randomUUID().toString());
		log.setClassName(className);
		log.setMethodName(methodName);
		log.setRequestContent(requestContent);
		log.setResponseContent(responseContent);
		log.setResponseTime(responseTime);
		try {
			mongoService.save(log);
		} catch (Exception e) {
			logger.error("记录业务系统调用新油品网关日志发生错误：", e);
		}
	}

	public void setMongoService(IMongoService mongoService) {
		this.mongoService = mongoService;
	}

}
