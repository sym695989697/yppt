package com.ctfo.base.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ctfo.csm.soa.ServiceFactory;

public abstract class AbstractService{
	
	private static Log logger = LogFactory.getLog(AbstractService.class);
	/**
	 * 获取远程对象方法
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <X> X getRemoManager(Class<?> cls){
		return (X)ServiceFactory.getFactory().getService(cls);
	}
	
	public com.ctfo.csm.uaa.intf.support.Operator getOnlieOperator(){
		HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//return Converter.getOperator(request);
		return null;
	}
	
	
}
