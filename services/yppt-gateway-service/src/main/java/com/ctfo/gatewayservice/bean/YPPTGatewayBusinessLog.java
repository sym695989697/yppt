package com.ctfo.gatewayservice.bean;

import java.io.Serializable;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Transient;
import com.google.code.morphia.utils.IndexDirection;

/**
 * 网关服务系统与其它系统的交互日志
 * 
 * @author sunchuanfu
 */
@Entity(value = "TL_yppt_gateway_business_log", noClassnameStored = true)
public class YPPTGatewayBusinessLog implements Serializable {

	@Transient
	private static final long serialVersionUID = -764714326688760124L;

	@Id
	private String id;

	private String className;

	private String methodName;

	private String requestContent;

	private String responseContent;

	@Indexed(value = IndexDirection.DESC, unique = true, dropDups = true)
	private String responseTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
