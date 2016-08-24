<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.models.Index"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@ page import="java.util.*,java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//获取配置信息
Properties prop = new Properties();
InputStream in = getClass().getResourceAsStream("/system.properties");
prop.load(in);
String serverName = prop.getProperty("yp.serverName");
//获取登录信息
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
			}
		}
	}
}
if(loginName != null){
	//设置到session中
	session.setAttribute(Converter.SESSION_REMOTE_USER, loginName);
}
Index index = (Index) session.getAttribute(Converter.SESSION_INDEX);
String userName = "";
String headPicURL = "";
if(index != null){
    userName = index.getUserName() == null ? "" : index.getUserName();
    headPicURL = index.getHeadPicURL() == null ? "" : index.getHeadPicURL();
} else {
    //response.sendRedirect(serverName + "/pages/member/member_login.html");
}
%>
<html>
<head>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	var userName = '<%=userName%>';
	var loginName ='<%=loginName%>';
	var headPicURL = '<%=headPicURL%>'
	if(loginName && loginName != null) {
		//$('#noLogin', window.parent.document).addClass("display", "none");
		//$('#userLogin', window.parent.document).removeClass("display");
		$('#noLogin', window.parent.document).hide();
		$('#userLogin', window.parent.document).show();
		if(userName && userName != null && userName != "") {
			$('#userName', window.parent.document).html(userName);
		} else {
			$('#userName', window.parent.document).html(loginName);
		}
		if(headPicURL){
			$('#headPicURL', window.parent.document).attr('src', headPicURL);
		}
	} else {
		$('#userLogin', window.parent.document).hide();
		$('#noLogin', window.parent.document).show();
	}
</script>
</head>
<body>

</body>
</html>