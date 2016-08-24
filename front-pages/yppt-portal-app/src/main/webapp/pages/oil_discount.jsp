<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%
String root = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + root + "/";
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>车旺油品服务-油卡优惠信息</title>
	<link rel="stylesheet" href="<%=basePath%>/css/common/base.css">
	<link rel="stylesheet" href="<%=basePath%>/css/common/common.css">
	<link rel="stylesheet" href="<%=basePath%>/css/common/new_common.css">
	<link rel="stylesheet" href="<%=basePath%>/css/oil/oil_pages.css">
</head>
<body>
<!--top-->
<!-- 窄版页头 -->
<fragment:include url="/page-head.html"/>
<fragment:include url="/navigator-orange.html"/>
<div class="main">
	<div class="authen_con_chip">
			<ul class="pa">
			<li class="fl pr2 color_chip">车旺首页</li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">油品服务</a></li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil_discount.jsp">油卡优惠信息</a></li>
			</ul>
		</div>
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--left-->
	 <!--left-->
	    <jsp:include page="left.jsp"/>
		<!--right-->
		<div class="rightsideBar w800 fr">
			<div>
				<div class="rightsideBar_title ">
				    <h3 class="fb f14 fl">中石化加油卡柴油优惠信息</h3>
				</div>
				<div class="border_rightBar table_border">
				    <table width="100%" class="table_border2">
					    <tr>
						    <th class="first_th">省份</th>
						    <th class="">返利政策</th>
						</tr>
						<tr>
						    <td class="first_td">福建</td>
						    <td>每消费1升按照0.15元的标准次月进行返利</td>
						</tr>
						<tr class="table_bg">
						    <td class="first_td">山东</td>
						    <td>每消费100元按照1.5%的标准次月进行返利</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>	
</div>
<fragment:include url="/page-foot.html"/>
</body>
</html>