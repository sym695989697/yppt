<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%-- <base href="<%=basePath%>"> --%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>车旺油品服务-卡片管理</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/oil/oil_explain.css">
	<link rel="stylesheet" href="../../css/oil/my_oilcards.css" type="text/css">
	<style type="text/css">
	 .rechargePage,.updateCarNo,.updatePhoneNo,.exitCarNo,.exitPhoneNo{
          cursor: pointer;	 
          color: #0D8DCD;
	 }
	</style>
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 2;
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
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/my_oilcards.jsp">卡片管理</a></li>
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
				<span class="fl color_ff fb f14 mr30">我的油卡</span>
				<div class="fl record_title_tab menu_a">
				    <a href="#" class="f14 title_tabcurr" id="usedTab">使用中</a>
				    <a href="#" class="f14" id="transTab">办理中</a>
				</div>
			</div>
			<div class="menu_main">
			<div class="menu_oil_cards" id="usedTable">
				<div class="sideBar_bg border_rightBar p20 h30">
					<ul>
						<li>
							<input class="text_titlebtn" type="text" id="selecttext" placeholder="请输入卡号/车牌号/手机号">
							<span class="text_titlespan" id="selectclick">查询</span>
						</li>
					</ul>
				</div>
				<div class="mt15" id="cardList">
				
				
				</div> 
				<div style='margin-top:10px;float:left;width:800px;text-align:center; display: none;' id="showframe"><a style='color:#0D8DCD;font-size:14px;' id="showmore">查看更多</a></div>
			</div>
			<div class="menu_oil_cards none" id="transTable">
			    <div class="sideBar_bg border_rightBar p20 h30">
					<ul>
						<li>
							<input class="text_titlebtn" type="text" id="selectapplytext" placeholder="车牌号/手机号">
							<span class="text_titlespan" id="selectapply">查询</span>
						</li>
					</ul>
				</div>
				<div class="mt15" id="cardListApply">
					
					<!--添加油卡结束-->
				</div>
				<div style='margin-top:10px;float:left;width:800px;text-align:center; display: none;' id="showframeApply"><a style='color:#0D8DCD;font-size:14px;' id="showmoreapply">查看更多</a></div>
		    </div>
			</div>
		</div>
		
	</div>
	
</div>
</div>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js"></script>
<script type="text/javascript" src="../../js/SimpleValidator.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="my_oilcards.js"></script>
<script type="text/javascript"> 
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 2;
var basePath = "<%=basePath%>";
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