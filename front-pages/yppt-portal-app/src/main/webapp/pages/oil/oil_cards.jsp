<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
String cardNumber = request.getParameter("cardNumber") == null ? "" :  request.getParameter("cardNumber");
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%-- <base href="<%=basePath%>"> --%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺油品服务-油卡充值 </title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/layer.css">
	<link rel="stylesheet" href="../../css/share.css">
	<link rel="stylesheet" href="../../css/common/style.css">
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 4;
</script>	
</head>
<body>
	<!--top-->
	<!-- 窄版页头 -->
	<fragment:include url="/page-head.html"/>
	<!-- 首页宽版页头 -->
 	<%-- <fragment:include url="/page-head-h.html"/> --%>
 	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html"/>
		<div class="main">
	    <!-- 导航菜单 -->
	 		<div class="authen_con_chip">
				<ul class="pa">
				<li class="fl pr2 color_chip">车旺首页</li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_cards.jsp">油卡充值</a></li>
				</ul>
			</div>
		<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	<!-- left -->
		<jsp:include page="../left.jsp"/>
		<!--right-->
		<div class="rightsideBar w800 fr">
			<div class="rightsideBar_title">
				<h3 class="fb f14 inline fl">油卡充值</h3>
			</div>
			<div class="sideBar_bg border_rightBar p20 h30 queryBlock">
				<ul>
					<li class="fl mr45">
					    <input class="text_titlebtn queryParam" type="text" placeholder="请输入卡号/车牌号/手机号" name="requestParam.like.searchInfo" value="<%=cardNumber%>" id="searchInput">
					    <input type="hidden" class="pageNum pageParam" name="requestParam.page">
          				<input type="hidden" class="pageSize pageParam" name="requestParam.rows">
						<span class="text_titlespan" id="exactQueryBtn">查询</span>
					</li>
					<li class="fr"><span>请先勾选油卡再进行充值</span><input class="titlebtn" type="button" value="油卡充值" id="oilCardRecharge"></li>
				</ul>
			</div>
			<div class="">
				<div class="rightsideBar_title "></div>
				<div class="border_rightBar table_border">
				    <table width="100%" class="table_border2">
				    	<thead>
						    <tr>
							    <th class="first_th"><label class="label_check" id="label"><input type="checkbox"  class="vm mr10">卡号</label></th>
							    <th class="">油卡类型</th>
							<!--	<th class="">发卡地区</th>
							    <th class="">卡状态</th>
							    -->
							    <th>卡余额</th>
								<th>绑定车辆</th>
								<th>绑定手机号</th>
								<!--<th>开卡时间</th>
								<th>历史交易</th>-->
							</tr>
						</thead>
						<tbody id="dataGrid">
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/template" id="tplGrid">
{@if  data && data.length}
  {@each data as o,i}
	{@if i % 2 == 1}
 		<tr class="table_bg">
	{@else}
		<tr>
	{@/if}
		<td class="first_td">
			<label class="label_check" id="label${i}">
				<input type="checkbox" class="vm mr10">
				<input type="hidden" value="${o.id}" id="cardId">
				${o.cardNumber}
			</label>
		</td>
		<td>${o.cardType|getCodeName,'IC-CARD-TYPE'}</td>
		
		
		<td>$${o.balance|converMoneyToPage}</td>
		<td>{@if o.vehicleNo != null}${o.vehicleNo}{@/if}</td>
		<td>{@if o.telephoneNumber != null}${o.telephoneNumber}{@/if}</td>
		
		
		</tr>
	{@/each}
{@else}
		<tr><td colspan="9" class="no-data">您还没有申请车旺加油卡，赶快申请吧！</td></tr>
{@/if}
		{@if totalPage > 1}<tr><td colspan="9">$${start|getPageNumbers,totalPage,5}</td></tr>{@/if}
</script>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="../../js/areaData.js" ></script>
<script type="text/javascript" src="../../js/area_code_util.js" ></script>
<!-- <script type="text/javascript" src="../../js/checkbox.js" ></script> -->
<script type="text/javascript" src="oil_cards.js" ></script>
	<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>