package com.ctfo.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.expression.spel.ast.Operator;

import com.ctfo.common.models.Index;

public class LoginUtil {
	
private static Log logger = LogFactory.getLog(LoginUtil.class);
	
	public static final String OP_SUCCESS="操作成功";
	public static final String OP_FAILED="操作失败";
	public static final String SYS_SIGN="system.code";
	
	public static final String SESSION_INDEX="session-index";
	public static final String SESSION_REMOTE_USER="session-remote-user";
	
	public static final String SESSION_MENU_LIST="session-menuList";
	public static final String SESSION_FUNC_LIST="session-funList";
	public static final String CONTEXT_FULL_PERMISSION="context-fullList";
	
	//存储session，为切换角色准备数据
	public static final String SESSION_ROLE_LIST="session-roleList";
	
	
	@SuppressWarnings("finally")
	public static final Operator getOperator(HttpServletRequest request){
		
		Operator result = null;
		try {
			if( request == null) throw new IllegalArgumentException("the request is null!");
			
			String principal = request.getRemoteUser();
			if( principal == null){
				HttpSession ses = request.getSession(false);
				if( ses != null){
					principal = (String) ses.getAttribute(SESSION_REMOTE_USER);
				}
			} 
			if( principal == null) throw new IllegalStateException("用户还没有成功登录!");
			
			if(request.getSession().getAttribute(SESSION_REMOTE_USER) == null ){
				request.getSession().setAttribute(SESSION_REMOTE_USER, principal);
			}
			result = parseOperator(request,principal);
		} catch (Exception e) {	
			logger.warn("获取登录用户信息异常",e);			
		}
		finally{
			if( result == null) 
				//return new Operator();
				return null;
			else{
				return result;
			}
				
		}
	}
	private static Operator parseOperator(HttpServletRequest request,String principal)throws Exception{
		Index index = (Index)request.getSession().getAttribute(SESSION_INDEX);
		if(index==null){
			return null;
		}
//		Operator oper = new Operator();
//		oper.setRoleId(index.getRoleId());
//		oper.setSystemsign(index.getSystemSign());
//		oper.setUserId(index.getUserId());
		return null;
	}
	

}
