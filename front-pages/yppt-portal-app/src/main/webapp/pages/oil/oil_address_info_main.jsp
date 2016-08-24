<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>车旺油品服务-地址管理</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/common/style.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/layer.css">
	<link rel="stylesheet" href="../../css/share.css">
	<style type="text/css">
	 table tr td{
	 word-break:break-all;
	 }
	</style>
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
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">个人中心</a></li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_rebate.jsp">地址管理</a></li>
			</ul>
		</div>
	<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--left-->
		<jsp:include page="../left.jsp"/>
		<!--right-->
		<div class="rightsideBar w800 fr">
		    <div>
				<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl" >地址管理</h3>
				</div>
				<div class="border_rightBar table_border">
				    <div class="rightsideBar_bg" id="addressTitle">已保存地址</div>
				    <table width="100%" class="table_border2" id="addressList">
					</table>
				</div>
			</div>
			<div class="rightsideBar_title pt20">
				<h3 class="fb f14 inline fl"id="title_new">新增地址信息</h3>
			</div>
			<div class="mian_rightsideBar pt30 pb30">
			    <div class="lists-form">
					<table>
						<tr>
							<td class="lab"><span class="required">*</span>收件人：</td>
							<td class="fld">
								<input type="text" name="" value="" id="recieverName"/>
							</td>
							<td class="mark"></td>
						</tr>
						<tr>
							<td class="lab"><span class="required">*</span>手机号：</td>
							<td class="fld">
								<input type="text" name="" value="" id="phoneNum"/>
							</td>
							<td class="mark"></td>
						</tr>
						
					</table>
				</div>
				<div class="next-lists-form">
				    <table>
					    <tr>
							<td class="lab"><span class="required">*</span>地区：</td>
							<td class="nextsel" style="width: 300px;">
							<div id="addressLevel">
							    <input type="hidden" name="openCardProvince" class="term _sendProvinceCode" id="provinceCode" />
								<input type="hidden" name="sendProvinceName" class="term"/>
								<input type="hidden" name="openCardCity" class="term _sendCityCode" id="cityCode" />
								<input type="hidden" name="sendCityName" class="term" id="cityName"/>
								<input type="hidden" name="openCarddistrict" class="term _sendCountyCode" id="countyCode"/>
								<input type="hidden" name="sendCountyName" class="term"/>
							</div> 
							</td>
							<td class="mark"><input type="text" name="" value="" style="width: 250px;" id="addressmore" placeholder="详细地址"/></td>
						</tr>
					</table>
				</div>
				<div class="lists-form">
					<table>
						<tr>
							<td class="lab"><span class="required">*</span>邮编：</td>
							<td class="fld">
								<input type="text" name="" value="" id="zipCode"/>
							</td>
							<td class="mark"></td>
						</tr>
					</table>
				</div>
				<div class="next-lists-form">
				    <table>
						<tr>
							<td class="lab"></td>
							<td class="nextsel nextsel2">
								<input class="nextselbtn2" style=" width: 159px; height: 36px;" type="button" id="addAddress" value="保存">
							</td>
							<td class="mark"></td>
						</tr>
					</table>
					<input type="hidden" id="addressId"/>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</idv>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var pagetype = "<%=request.getParameter("pagetype")%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="../../js/SimpleValidator.js" ></script>
<script type="text/javascript" src="../../js/thlevelarea.js" ></script>
 <script type="text/javascript" src="oil_address_info_main.js"></script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body> 
</html>