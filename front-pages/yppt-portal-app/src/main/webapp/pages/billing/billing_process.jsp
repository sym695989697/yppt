<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
String id = request.getParameter("id") == null ? "" :  request.getParameter("id");
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>车旺油品服务-办理中油卡详情查看</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/common/style.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/layer.css">
	<link rel="stylesheet" href="../../css/share.css">
	<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 7;
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
	<div class="main">
    <!-- 导航菜单 -->
	<div class="authen_con_chip">
		<ul class="pa">
		<li class="fl pr2 pr2">车旺首页</li>
		<li class="fl pl2 pr2 color_chip">&gt;</li>
		<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">我的加油卡</a></li>
		<li class="fl pl2 pr2 color_chip">&gt;</li>
		<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_rebate.jsp">办卡详情</a></li>
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
				<h3 class="fb f14 inline fl">开票进度</h3>
			</div>
			<div class="border_rightBar pb30">
				<div class="pt30 process_table pl55" style="text-align:center;">
					<table id="planState">
					
					</table>
			    </div>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var id = "<%=id%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="../../js/thlevelarea.js"></script>
<script type="text/javascript" src="billing_process.js"></script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body> 
</html>