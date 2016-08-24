<%@page import="com.ctfo.common.utils.Converter"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>油品管理系统</title>
    <%
		String loginName = request.getRemoteUser();
		if(loginName == null){
			//从session中获取
			loginName = (String)session.getAttribute(Converter.SESSION_REMOTE_USER);
			if(loginName ==null){//第一次登录,需从cas中获取
				Object object = session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
				if(object != null){
					Assertion assertion = (Assertion)object;
					if(assertion != null && assertion.getPrincipal() != null){
						loginName = assertion.getPrincipal().getName();
						//设置到session中
						response.sendRedirect(request.getContextPath() + "/login/login.action?systemSgin=com.ctfo.chpt.iccard.managerApp");
					}
				}
			}
		}
		if(loginName !=null){
			session.setAttribute(Converter.SESSION_REMOTE_USER, loginName);
		}
	%>
    <script type="text/javascript">
      window.top.location.href="login/login.action?systemSgin=com.ctfo.chpt.iccard.managerApp";
    </script>
</head>

<body> 
 
<div> 跳转页面...</div> 

</body>
</html>
