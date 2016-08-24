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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺95155云服务平台— 加油卡频道</title>
	<meta name="keywords" content="车旺、物流、物流网、物流园、物流平台、物流信息、货运信息、货源、车源、货主、车主、加油、中石化、中石油、返利">
	<meta name="description" content="车旺O2O云服务平台（www.95155.com），集诚信认证、车源查找、定位服务、轨迹回放、油品服务、全国救援维修服务、即时通讯、手机应用、信息资讯、24小时客服等服务于一体的便捷、全面、高效的商用车车联网应用服务平台">
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_explain.css">
<style>
   /*弹出整体样式*/
 .sidebar_mydiv{
 background-color:#fff;
 border:1px solid #ff7200;
 padding:5px 10px 10px 10px;
 width:342px;
 position:relative;
 left:-111px; top:5px;
 z-index:9999;
 font-size: 12px;
 font-family:"微软雅黑";
 font-weight: normal;
}
 .sidebar_mydiv2{
 background-color:#fff;
 border:1px solid #e73030;
 padding:10px 10px 10px 10px;
 position:absolute;
 width:114px;
 left:-68px;
 top:30px;
 color:#e73030;
 z-index:9999;
}
 .sidebar_mydiv2 img{
 position:relative;
 top:-23px;left:-15px;
}
 .sidebar_mydiv table, .sidebar_mydiv table td{
 border:1px solid #e9e9e9;
 }
 .sidebar_mydiv table tr{
  line-height:28px;
 }
 .sidebar_mydiv .first_mydiv{
  background-color:#f9f9f9;
  text-align:center;
  color:#555;
 }
 .sidebar_mydiv  .last_mydiv{
 padding-left:10px;
 color:#333;
 background-color:#fff;
}
 .sidebar_mydiv .sidebar_mydivicon{
 float:left;
 position:relative;
 top:-11px; left:100px;
}
</style> 
</head>
<body>
<!--top-->
	<!-- 窄版页头 -->
	<%-- <fragment:include url="/page-head.html"/> --%>
	<!-- 首页宽版页头 -->
	<fragment:include url="/page-head-h.html"/>
 	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html"/>
	<div class="main">
		<div class="authen_con_chip">
			<ul class="pa">
			<li class="fl pr2 color_chip">车旺首页</li>
			<li class="fl pl2 pr2 color_chip">&gt;</li>
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
			</ul>
		</div>
	<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--我的油卡-->
	    <div class="oil_explain">
		    <ul>
			    <li class="fl my_oil mr10">
				    <div class="my_oil_top">
					    <dl>
						    <dt class="fl"><img src="../../images/oil/oil_icon.png"></dt>
							<dd class="f18 oil_topdd color_sizeB">我的油卡</dd>
							<dd class="f14 color_sizeG"><span class="num" id="cardNum" style="cursor: pointer;">0</span>张</dd>
						</dl>
						<div class="clear"></div>
					</div>
					<div class="my_oil_bot">
					    <a href="<%=basePath%>pages/card/card_apply.jsp" class="oil_bota" id="open_card">我要办卡</a>
						<a href="<%=basePath%>pages/oil/oil_cards.jsp" class="oil_botb">油卡充值</a>
						<a href="<%=basePath%>pages/oil/my_oilcards.jsp" class="oil_botc">查看详情</a>
					</div>
				</li>
				<li class="fl my_oil oil_one mr10">
				    <div class="my_oil_top">
					    <dl>
						    <dt class="fl"><img src="../../images/oil/oil_icon2.png"></dt>
							<dd class="f18 oil_topdd color_sizeB">累计加油量</dd>
							<dd class="f14 color_sizeG"><span class="num" id="allAddOil">0</span>升</dd>
						</dl>
						<div class="clear"></div>
					</div>
					<div class="my_oil_bot">
						<a href="<%=basePath%>pages/oil/oil_transaction.jsp" class="oil_botd ">查看详情</a>
					</div>
				</li>
				<li class="fl my_oil oil_tow">
				     <div class="my_oil_top">
					    <dl>
						    <dt class="fl"><img src="../../images/oil/oil_icon3.png"></dt>
							<dd class="f18 oil_topdd color_sizeB">上月返利</dd>
							<dd class="f14 color_sizeG"><span class="fl num" id="lastMonthRe">0</span><span class="f12 fl num2" id="preMonthTime"></span><br/><span class="f14 num3">旺金币</span></dd>
						</dl>
						<div class="clear"></div>
					</div>
					<div class="my_oil_bot">
						<a href="<%=basePath%>pages/oil/oil_rebate.jsp" class="oil_botd ">查看详情</a>
					</div>
				</li>
			</ul>
			
		</div>
		<div class="clear"></div>
		<!--油品优惠入口-->
		<div class="oil_sale"><a href="<%=basePath%>pages/oil/oil_introduction.jsp"><img src="<%=basePath%>/images/ads960X80.jpg"></a></div>
		<!--交易记录-->
		<div class="mt30 oil_record">
		    <div class="oil_record_title h35">
			    <span class="fl color_ff fb f14 mr30 ">交易记录</span>
				<div class="fl record_title_tab">
				    <a href="#" class="f14 addoil title_tabcurr">加油记录</a>
					<a href="#" class="f14 paycharger">充值记录</a>
				</div>
			</div>
			<div class="oil_record_table mt1">
			    <table width="100%">
				    <tr>
					    <th class="first_th">卡号</th>
					    <th>时间</th>
					    <th>交易金额（元）</th>
					    <th>交易后金额（元）</th>
					    <th>交易状态</th>
					</tr>
					<tbody id="dataGrid"></tbody>
				</table>
				<div class="tc lh50 color_sizeA">
				    <a href="<%=basePath%>pages/oil/oil_transaction.jsp" class="ml20 color_sizeD">查看所有交易记录</a>
				</div>
			</div>
			
		</div>
	</div>
</div>

	<input type="hidden" class="pageNum pageParam" name="requestParam.page">
    <input type="hidden" class="pageSize pageParam" name="requestParam.rows">
	<script type="text/template" id="tplGrid">
{@if data.length}
  {@each data as o,i}
	{@if i % 2 == 1}
 		<tr class="table_bg">
	{@else}
		<tr>
	{@/if}
        <td class="first_td" style="padding-left:5px;">${o.cardNo}</td>
		<td><div class="table_time">$${o.tradeTime|getSmpFormatDateByLong,true}</div></td>
    
        <td class="table_num">${o.tradeMoney}
             {@if o.tradeType!="recharge"}
                  	<!--交易详情弹出-->
								<div class="bubbleInfo fl" style="position:relative;">
									<div><img id="download" style="margin-top:5px; margin-right:5px;" class="trigger" src="../../images/oil/sideBar_icon6.png"></div>
									<div id="dpop" class="sidebar_mydiv popup" style="position:absolute;">
										<img class="sidebar_mydivicon" src="../../images/oil/sideBar_icon7.png">
										<table width="100%" id="" class="">
											<tr>
												<td class="first_mydiv">交易地点</td>
												<td class="last_mydiv">${o.tradeAdress}</td>
											</tr>
											<tr>
												<td class="first_mydiv">交易详情</td>
												<td class="last_mydiv">${o.productName} / ${o.productPrice/100}元/L / ${o.productNum/100}L</td>
											</tr>
										</table>
									</div>
								</div>
               {@/if}
        </td>
       
		<td class="table_num">
		{@if o.cardBalance != null}
			${o.cardBalance}
		{@else}
			--
		{@/if}
		</td>
		<td>
{@if o.tradeType !="recharge"}
<img src="../../images/oil/oil_button1.png">
{@else}

	{@if o.tradeStatus == '05'}
			<img src="../../images/oil/oil_btn2.png">
     {@else if o.tradeStatus == '03'}
        	<img src="../../images/oil/oil_btn3.png">
	{@else}
			<img src="../../images/oil/oil_btn.png">
	{@/if}
{@/if}

{@if o.tradeStatus == '03'}
	<!--充值失败-->
	<div class="bubbleInfo2 clear fr" style="position:relative;float:left; ">
	<div><img class="trigger2" src="../../images/oil/sideBar_icon8.png"></div>
	<div id="dpop2" class="sidebar_mydiv2 popup2" style="position:absolute;">
	<span class="display:block;">
	{@if o.faileReason==""|| o.faileReason=="null"}
                       失败原因为空
    {@else}
         ${o.faileReason}
    {@/if}
    </span>
	<img src="../../images/oil/sideBar_icon9.png">
	</div>
	</div>
{@/if}
        </td>
	</tr>
{@/each}
{@else}
	<tr><td colspan="5" class="no-data">抱歉！没有找到与您条件匹配的数据，请重新搜索</td></tr>
{@/if}
</script>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="oil_home.js" ></script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>