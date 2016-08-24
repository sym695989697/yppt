<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="com.ctfo.csm.utils.SpringBUtils"%>

<%
System.out.println("================session time out=====================");
try{  
   session.invalidate();
}catch(Exception ex){
	//ignore this error
}    
System.out.println("================logout=====================");
String casServerLogoutUrl = ((Map)SpringBUtils.getBean("casMap")).get("casServerLogoutUrl").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>跳转页面</title>
</head>

<body>

<script type="text/javascript"> 
	//超时提示
	//alert('操作时间过长，请重新登录后再操作!');
	
	top.location="<%=casServerLogoutUrl%>";
	
</script>


</body>
</html>