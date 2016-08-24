<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车旺油品服务-我的油卡</title>
<link rel="stylesheet" href="../../css/common/base.css">
<link rel="stylesheet" href="../../css/common/common.css">
<link rel="stylesheet" href="../../css/common/new_common.css">
<link rel="stylesheet" href="../../css/oil/oil_pages.css">
<link rel="stylesheet" href="../../css/oil/oil_explain.css">
<link rel="stylesheet" href="../../css/oil/my_oilcards.css"
	type="text/css">
<style type="text/css">
.rechargePage,.updateCarNo,.updatePhoneNo,.exitCarNo,.exitPhoneNo {
	cursor: pointer;
	color: #0D8DCD;
}
</style>
<script type="text/javascript">
var id='<%=request.getParameter("id")%>';
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 4;
</script>

</head>
<body>
	<!--top-->
	<!-- 窄版页头 -->
	<fragment:include url="/page-head.html" />
	<!-- 首页宽版页头 -->
	<%-- <fragment:include url="/page-head-h.html"/> --%>
	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html" />
	<div class="main">
		<!-- 导航菜单 -->
		<div class="main">
			<!-- 导航菜单 -->
			<div class="authen_con_chip">
				<ul class="pa">
					<li class="fl pr2 color_chip">车旺首页</li>
					<li class="fl pl2 pr2 color_chip">&gt;</li>
					<li class="fl pl2 pr2"><a
						href="<%=basePath%>pages/oil/oil_home.jsp">油品服务</a></li>
					<li class="fl pl2 pr2 color_chip">&gt;</li>
					<li class="fl pl2 pr2"><a
						href="<%=basePath%>pages/oil/my_oilcards.jsp">卡片管理</a></li>
				</ul>
			</div>
			<!-- 导航菜单结束-->
			<!--content-->
			<div id="main_content" class="main w960 mauto cf help_pages_main">
				<!--left-->
				<jsp:include page="../left.jsp" />
				<!--right-->
				<div class="rightsideBar w800 fr menu">
				
				
					<div class="rightsideBar w800 fr">
						<div class="rightsideBar_title">
							<h3 class="fb f14 inline fl">油卡充值流程</h3>
						</div>
						<div class="border_rightBar pb30">
							<div class="pt30 process_table pl55">
								<table>
									<tr>
										<td class="pb10">已提交申请</td>
										<td class="pb10">工作人员处理</td>
										<td class="pb10">完成</td>
									</tr>
									<tr>
										<td  id="pb01"></td>
										<td  id="pb02"></td>
										<td  id="pb03"></td>
									</tr>
									<tr>
										<td class="pt10 pl10 pr10 vt" id="startTime"></td>
										<td class="pt10 pl10 pr10 vt">工作人员处理<br>15分钟</td>
										<td class="pt10 pl10 pr10 vt" id="endTime"></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="mt30">
							<p>您已成功提交 油卡充值，工作人员处理后 ，请及时去油站圈存使用</p>
						</div>
					</div>



				</div>
			</div>

		</div>

	</div>
	</div>
	<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../../js/plugins.js"></script>
	<script type="text/javascript" src="../../js/SimpleValidator.js"></script>
	<script type="text/javascript" src="../../js/code_util.js"></script>
	<script type="text/javascript" src="../../js/util_ajax.js"></script>
	<script type="text/javascript" src="recharge_result.js"></script>
	<script type="text/javascript"> 
       var basePath = "<%=basePath%>";
		$(document).ready(
				function() {
					$(".menu .menu_a a").click(
							function() {
								$(this).addClass("title_tabcurr").siblings()
										.removeClass("title_tabcurr");
								$(".menu_main .menu_oil_cards").eq(
										$(".menu .menu_a a").index(this))
										.show().siblings().hide();
							});
				});
	</script>
	<!-- 页脚 -->
	<fragment:include url="/page-foot.html" />
</body>
</html>