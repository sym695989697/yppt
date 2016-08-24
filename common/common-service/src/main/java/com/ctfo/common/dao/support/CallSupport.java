package com.ctfo.common.dao.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CallSupport {
	
	private static final Log logger = LogFactory.getLog(CallSupport.class);
	
	public static Object staticCall(Class claz, String method, Class[] ptypes, Object[] params) throws Exception {
		try {
			Method m = claz.getMethod(method, ptypes);
			if( Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers()) ){
				return m.invoke(null, params);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("这个调用有问题：" + claz.getName() + "[" + method + "]", e);
			throw new Exception("这个调用有问题：" + claz.getName() + "[" + method + "]", e);
		}
		return null;
	}
	
	public static Object publicCall(Object obj, String method, Class[] ptypes, Object[] params) throws Exception {
		try {
			Method m = obj.getClass().getMethod(method, ptypes);
			if(Modifier.isPublic(m.getModifiers())){
				return m.invoke(obj, params);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("这个调用有问题：" + obj.getClass().getName() + "[" + method + "]", e);
			throw new Exception("这个调用有问题：" + obj.getClass().getName() + "[" + method + "]", e);
		}
		return null;
	}
}
