package com.ctfo.sinoiov.mobile.webapi.base.filter.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.sinoiov.mobile.webapi.base.filter.IClientFilter;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;

public class SignValueFilter implements IClientFilter{
	
	protected static final Log log = LogFactory.getLog(SecurityFilter.class);

	public void doFilter(Parameter param) {
		this.validateSign("", "", "", "");
	}

	private void validateSign(String code, String password, String ext, String signValue){
		
	}
}
