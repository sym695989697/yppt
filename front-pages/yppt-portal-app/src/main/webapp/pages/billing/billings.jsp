<%@page import="com.ctfo.common.utils.DateUtil"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺油品服务-开票申请记录</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/common/style.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/layer.css">
	<link rel="stylesheet" href="../../css/share.css">
	<style type="text/css">
	  #panel ul li .input_bar .litab_curr{
	  background-color:#FF7200;
	  color:#FFF;
	  text-decoration:none;
	  }
	</style>
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 8;
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
			<li class="fl pr2 pr2">车旺首页</li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/billing/billings.jsp">开票申请记录</a></li>
			</ul>
		</div>
	<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--left-->
	    <jsp:include page="../left.jsp"/>
		<!--right-->
		<div class="rightsideBar w800 fr">
			<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl">开票申请记录</h3>
			</div>
			<div class="sideBar_bg border_rightBar p20 h30">
				<ul>
					<li class="f18 fl mr45 color_sizeA">累计开票金额：<span class="posi color_ff fb f24" id="totalOpenMoneyIntPart"><span class="f18" id="totalOpenMoneyDePart">0</span>元</span></li>
				</ul>
			</div>
			<!--交易明细-->
			<div class="pt20 queryBlock">
				<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl">开票记录</h3>
				</div>
				<div class="h80 sideBar_deta sideBar_bg border_rightBar p20 ">
				    <ul>
					    <li class="li li_tow">
						    <div class="lab color_sizeB">日期</div>
							<div class="datetime datetimelang input_bar">
							    <input class="mr5" type="text" value="" id="startDate" onchange="javascript:getList();" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd'})"/>至
							    <input class="ml5" type="text" value="" id="endDate" onchange="javascript:getList();" 
							    onClick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd'})"/>
							    <!-- maxDate: '2008-12-20' -->
							</div>
						</li>
						<li class="li litab">
						    <a href="#" class="litab_a litab_curr choosemonth">本月</a> 
							<a href="#" class="litab_c choosemonth">上月</a> 
							<!-- <a href="#" class="litab_c choosemonth">近3个月</a>  -->
						</li>
					</ul>
				</div>
				<div class="put_away h80" id="panel">
				    <ul>
					    <li class="li li_tow">
						    <div class="lab color_sizeB ">状态</div>
							<div class="datetime datetimelang input_bar">
							    <a href="#" class="litab_a paytype">开票成功</a> 
							    <a href="#" class="litab_b paytype">审核中</a> 
							    <a href="#" class="litab_c paytype">开票失败</a>
							</div>
						</li>
					</ul>
				</div>
				<input type="hidden" class="pageNum pageParam" name="requestParam.page">
             	<input type="hidden" class="pageSize pageParam" name="requestParam.rows">
			</div>
			
			<!---->
			<div class="pt20">
				<div class="border_rightBar table_border">
				    <table width="100%"  class="table_border2">
				   		<thead>
						    <tr>
							    <th class="first_th">申请时间</th>
								<th>发票类型</th>
							    <th>发票抬头</th>
								<th>开票日期</th>
							    <th>发票金额</th>
							    <th>状态</th>
							    <th>详情</th>
							</tr>
						</thead>
						<tbody id="dataGrid"></tbody>
					</table>
				</div>
				<!--交易详情弹出-->
 				<!--充值失败-->
			</div>
		</div>
	</div>
	</div>
</div>
<script type="text/template" id="tplGrid">
{@if data && data.length}
  		{@each data as o,i}
			{@if i % 2 == 1}
 				<tr class="table_bg">
			{@else}
				<tr>
			{@/if}
					<td width="15%" class="first_td">$${o.createTime|getSmpFormatDateByLong,false}</td>
					<td width="10%">$${o.invoiceType|getCodeName,'IC-INVOICE-TYPE'}</td>
					<td width="35%" style="word-break:break-all;">${o.invoiceName}</td>
					{@if o.invoiceOpenTime == null}
						<td width="10%">--</td>
					{@else}
						<td width="10%" >$${o.invoiceOpenTime|getSmpFormatDateByLong,false}</td>
					{@/if}
					<td width="10%" >
						{@if (o.invoiceMoney+"").indexOf(".") > 0}
							${o.invoiceMoney+"元"}
						{@else}
							${o.invoiceMoney+".00元"}
						{@/if}
							
					</td>
					<td width="12%" class="bar_lose" >
						{@if o.status == "3"}
							$${o.status|getCodeName,'INVOICE-STATUS-APP'}
							<div class="bubbleInfo2 clear fr" style="position:relative;float:left; ">
								<div><img class="trigger2" src="../../images/oil/sideBar_icon8.png"></div>
								<div id="dpop2" class="sidebar_mydiv2 popup2" style="position:absolute; margin-left:50px;">
									<span class="display:block;">
                                    {@if o.mark==""|| o.mark=="null"}
                                                                                                     失败原因为空
                                     {@else}
                                     	${o.mark}
                                     {@/if}
                                    </span>
									<img src="../../images/oil/sideBar_icon9.png">
								</div>
							</div>
						{@else}
                              {@if o.status == "0"}
                                                                审核中
                              {@else}
                                $${o.status|getCodeName,'INVOICE-STATUS-APP'}
                            {@/if}
							
                      
						{@/if}
                  	</td>
					<td width="10%" ><a href="<%=basePath%>pages/billing/billing_detail.jsp?id=${o.id}"><font color="blue">查看</font></a></td>
				</tr>
		{@/each}
	{@else}
		<tr><td colspan="7" class="no-data">抱歉！没有找到与您条件匹配的开票记录，请重新搜索</td></tr>
	{@/if}
		{@if totalPage > 1}<tr><td colspan="7">$${start|getPageNumbers,totalPage,5}</td></tr>{@/if}
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script src="../../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<!-- <script type="text/javascript" src="../../js/floatmessage.js"></script>  -->
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="billings.js"></script> 
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript"> 

$(document).ready(function(){ 
$(".sidebtn").click(function(){ 
$("#panel").slideToggle("fast"); 

if($(this).toggleClass("active").html()=="返回基本筛选"){
    $(this).toggleClass("active").html("高级筛选"); 
    $(this).toggleClass("active");
}else{
  $(this).toggleClass("active").html("返回基本筛选"); 
 $(this).removeClass("active");
}
});
}); 
</script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>