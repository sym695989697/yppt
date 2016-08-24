<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
try{
	//设置session失效
	session.invalidate();
}catch(Exception e){
	// ingore this error
}
String casLogoutUrl = null;
try{
	Properties prop = new Properties();
	prop.load(getClass().getResourceAsStream("/system.properties"));
	casLogoutUrl = prop.getProperty("cas.casServerLogoutUrl");
}catch(Exception e){
	System.err.print(e);
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>跳转页面</title>
</head>

<body>
正在退出......

<script type="text/javascript"> 
	top.location="<%=casLogoutUrl%>";
</script>
</body>
</html>