<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.models.Index"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%@ page import="java.util.*,java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
String cardNum = request.getParameter("cardNum") == null ? "0" :  request.getParameter("cardNum");
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺油品服务-开卡提交结果（我要办卡）</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/oil/oil_explain.css">
	<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 3;
</script>
</head>
<body>
<!--top-->
<!-- 窄版页头 -->
	<fragment:include url="/page-head.html"/>
	<!-- 首页宽版页头 -->
 	<%-- <fragment:include url="/page-head-h.html"/> --%>
 	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html"/><div class="main">
    <!-- 导航菜单 -->
		<div class="main">
	    <!-- 导航菜单 -->
	 		<div class="authen_con_chip">
				<ul class="pa">
				<li class="fl pr2 color_chip">车旺首页</li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/card/card_apply.jsp">我要办卡</a></li>
				</ul>
			</div>
	<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--left-->
	    <jsp:include page="../left.jsp"/>
		<!--right-->
		<div class="rightsideBar w800 fr menu">
			<div class="rightsideBar_title">
				<h3 class="fb f14 inline fl">办卡流程</h3>
			</div>
			<div class="border_rightBar pb30">
				<div class="pt30 process_table pl55">
					<table>
						<tr>
							<td class="pb10">已提交申请</td>
							<td class="pb10">卡片办理</td>
							<td class="pb10">办理成功</td>
						</tr>
						<tr>
							<td class="pro_icon5"></td>
							<td class="pro_icon4"></td>
							<td class="pro_icon3"></td>
						</tr>  
						<tr>
							<td class="pt10 pl10 pr10 vt">今天</td>
							<td class="pt10 pl10 pr10 vt">1-3个工作日</td>
							<td class="pt10 pl10 pr10 vt"></td>
						</tr>
					</table>
			    </div>
			</div>
			<div class="mt30">
				<p class="mb5">您的办卡申请已经提交，共计办卡<%=cardNum%>张，可以通过办卡记录页面进行查看进度！</p>
				<p class="mt8">您还可以<a href="card_apply.jsp" class="color_sizeD ml2 mr2 unl">继续办卡</a>或者<a href="../billing/billing_info.jsp" class="color_sizeD f16 ml5">完善发票信息</a></p>
			</div> 
			
		</div>
	</div>
	</div>
	</div>
</div>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/jquery.select.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
    $(".menu .menu_a a").click(function(){
	    $(this).addClass("title_tabcurr").siblings().removeClass("title_tabcurr");
		$(".menu_main .menu_oil_cards").eq($(".menu .menu_a a").index(this)).show().siblings().hide();
	});
});
</script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>