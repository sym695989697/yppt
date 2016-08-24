package com.ctfo.sinoiov.mobile.webapi.base.filter;


import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.sinoiov.mobile.webapi.bean.common.Parameter;


/**
 * 通知服务过滤器，在WebService中使用的过滤器应都 继承于该过滤器。
 * 
 * @author tommy
 * @version $id$
 */
public interface IClientFilter {
	
	@AnnotationName(name="校验外部接口参数")
	public void doFilter(Parameter param);
}
