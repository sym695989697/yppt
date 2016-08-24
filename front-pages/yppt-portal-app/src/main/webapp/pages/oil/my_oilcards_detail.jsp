<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
String cardApplyDetailId = request.getParameter("cardApplyDetailId") == null ? "" :  request.getParameter("cardApplyDetailId");
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
	<link rel="stylesheet" href="../../css/oil/eject.css">
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
<div class="main">
    <!-- 导航菜单 -->
		<div class="authen_con_chip">
			<ul class="pa">
			<li class="fl pr2 color_chip">车旺首页</li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
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
				<h3 class="fb f14 inline fl">办卡进度</h3>
			</div>
			<div class="border_rightBar pb30">
				<div class="pt30 process_table pl55" style="text-align:center;">
					<table id="planState">
					
					</table>
			    </div>
			</div>
			<div class="pt20">
				<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl">基本资料</h3>
				</div>
				<div class="border_rightBar pb30 pt30">
				    <div class="lists-form">
						<table>
							<tr>
								<td class="lab"><span class="required"></span>油卡类型：</td>
								<td class="fld" id="cardType"></td>
								<td class="mark"></td>
							</tr>
							<tr>
								<td class="lab"><span class="required"></span>常用地区：</td>
								<td class="fld" id="oftenArea"></td>
								<td class="mark"></td>
							</tr>
							<tr>
								<td class="lab"><span class="required"></span>申请人姓名：</td>
								<td class="fld" id="userName"></td>
								<td class="mark"></td>
							</tr>
							<tr>
								<td class="lab"><span class="required"></span>身份证号：</td>
								<td class="fld" id="idNo"></td>
								<td class="mark"></td>
							</tr>
							<tr>
								<td class="lab"><span class="required"></span><div style="margin-top:-50px;">收件人信息：</div></td>
								<td class="fld form_fld"><span class="block"><span id="receiverName"></span> <span id="receiverPhoneNum"></span></span>
                                    <div class="block" id="receiverArea" style="word-break:break-all; width: 400px;"></div>
								    <span class="block" id="zipCode"></span>
								</td>
								<td class="mark"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			
			<div id="cardApply"></div>
			
			
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var cardApplyDetailId = "<%=cardApplyDetailId%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="../../js/thlevelarea.js"></script>
<script type="text/javascript" src="my_oilcards_detail.js"></script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body> 
</html>