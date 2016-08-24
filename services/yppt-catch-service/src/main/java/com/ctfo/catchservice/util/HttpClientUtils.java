package com.ctfo.catchservice.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * HTTP工具类
 * 
 * @author jichao
 */
public class HttpClientUtils {
	static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	private static final Integer CONNECTION_TIMEOUT = 5 * 1000; // 请求超时时间
	private static final Integer SO_TIMEOUT =8 * 1000; // 等待数据超时时间2秒钟
	private static final Long CONN_MANAGER_TIMEOUT = 500L;

	/**
	 * 
	 * @Description:初始化httpclient
	 * @return HttpClient
	 * 
	 */
	public static HttpClient getHttpClient() {
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(CONNECTION_TIMEOUT);
		params.setSoTimeout(SO_TIMEOUT);
		// 最大连接数
		params.setMaxTotalConnections(200);
		params.setDefaultMaxConnectionsPerHost(200);
		params.setStaleCheckingEnabled(true);
		connectionManager.setParams(params);
		HttpClientParams httpClientParams = new HttpClientParams();
		// 设置httpClient的连接超时
		httpClientParams.setConnectionManagerTimeout(CONN_MANAGER_TIMEOUT);
		// 设置http client的重试次数，默认是3次
		httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.setParams(httpClientParams);
		return httpClient;
	}

}