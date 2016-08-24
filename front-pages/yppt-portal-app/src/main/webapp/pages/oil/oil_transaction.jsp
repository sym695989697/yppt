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
	<title>车旺油品服务-油卡交易记录</title>
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
var pageTag = 5;
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
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_transaction.jsp">油卡交易</a></li>
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
				<h3 class="fb f14 inline fl">油卡交易记录</h3>
			</div>
			<div class="sideBar_bg border_rightBar p20 h30">
				<ul>
					<li class="f18 fl mr45 color_sizeA">累计充值：<span class="posi color_ff fb f24" id="accountRechargeIntPart"><span class="f18" id="accountRechargeDePart">0</span>元</span></li>
					<li class="f18 fl color_sizeA">累计消费：<span class="posi color_sizeC fb f24" id="accountExpenseIntPart"><span class="f18" id="accountExpenseDePart">0</span>元</span><span class="f12">(累计统计到上月)</span></li>
				</ul>
			</div>
			<!--交易明细-->
			<div class="pt20 queryBlock">
				<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl">交易明细</h3>
				</div>
				<div class="sideBar_deta sideBar_bg border_rightBar p20 h110 " style="height: 110px;">
			     	<div style="position: relative;float: left;width: 750px; ">
						    <ul>
							    <li class="li li_tow">
								    <div class="lab color_sizeB">日期</div>
									<div class="datetime datetimelang input_bar">
									    <input class="mr5" type="text" value="" id="getStartDate1" onchange="javascript:getList();" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'getStartDate2\')}',dateFmt:'yyyy-MM-dd'})"/>至
									    <input class="ml5" type="text" value="" id="getStartDate2" onchange="javascript:getList();" 
		<%-- 							    onClick="WdatePicker({minDate:'#F{$dp.$D(\'getStartDate1\')}',dateFmt:'yyyy-MM-dd',maxDate:'<%=DateUtil.getLastDayOfLastMonthStr() %>'})"/> --%>
									    onClick="WdatePicker({minDate:'#F{$dp.$D(\'getStartDate1\')}',dateFmt:'yyyy-MM-dd'})"/>
									    <!-- maxDate: '2008-12-20' -->
									</div>
								</li>
								<li class="li litab">
								    <a href="#" class="litab_a litab_curr choosemonth">本月</a> 
									<a href="#" class="litab_c choosemonth">历史记录</a> 
									<!-- <a href="#" class="litab_c choosemonth">近3个月</a>  -->
								</li>
								<li class="li2 li_fr">
								    <a class="sidebtn btn-slide"  href="javascript:;" id="ishightselect">返回基本筛选</a>
								</li>
							</ul>
					 </div>
					<div style="margin-top: 5px; width: 800px;float: left;">
							本月交易数据为流水数据，可能存在在途交易数据，历史交易数据为准确数据，是返利结算依据
					</div>
				</div>
				
				<div class="put_away h80" id="panel">
				    <ul>
					    <li class="li li_tow">
						    <div class="lab color_sizeB ">交易类型</div>
							<div class="datetime datetimelang input_bar">
							    <a href="#" class="litab_a paytype litab_curr">充值</a> 
							    <a href="#" class="litab_c paytype ">加油</a>
							</div>
						</li>
						<li class="li li_tow">
						    <div class="lab color_sizeB">交易状态</div>
							<div class="datetime datetimelang input_bar">
							    <a href="#" class="litab_a paystatus">交易成功</a> 
							    <a href="#" class="litab_b paystatus">处理中</a> 
							    <a href="#" class="litab_c paystatus">充值失败</a>
							</div>
						</li>
						<li class="li2 li_fr li_text">
						    <input class="text_titlebtn" type="text" placeholder="请输入卡号/车牌号/手机号" value="<%=cardNumber%>" id="threeswitch">
                            <span class="text_titlespan" onClick="javascript:getList();">查询</span>
						</li>
					</ul>
				</div>
				<input type="hidden" class="pageNum pageParam" name="requestParam.page">
             	<input type="hidden" class="pageSize pageParam" name="requestParam.rows">
			</div>
			
			<!---->
			<div class="pt20">
				<!-- <div class="rightsideBar_title ">
					<span class="mr80">充值总额：<span class="color_ff fb" id="rechargeTotal">0.00</span>元</span>
					<span>消费总额：<span class="color_sizeC fb" id="expenseTotal">0.00</span>元</span>
				</div> -->
				<div class="border_rightBar table_border">
				    <table width="100%" class="table_border2">
					    <tr>
						    <th class="first_th">时间</th>
						    <th>车牌号</th>
						    <th class="w200">卡号</th>
						    <th>交易类型</th>
						    <th>支付方式</th>
						    <th>交易金额丨详情</th>
						    <th>余额（元）</th>
						    <th>交易状态</th>
						</tr>
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
		<td class="first_td"><div class="table_time">$${o.tradeTime|getSmpFormatDateByLong,true}</div></td>
	
	{@if o.vehicleNo != null}
		<td>${o.vehicleNo}</td>
	{@else}
		<td>--</td>
	{@/if}
		<td>${o.cardNo}</td>
	{@if o.tradeType == 'recharge'}
		<td>油卡充值</td>
	{@else}
		<td>加油</td>
	{@/if}
        
	{@if o.tradeType=="recharge" && o.payType != null}
		<td>$${o.payType|getCodeName,'IC-PAY-WAY'}</td>
	{@else}
		<td>--</td>
    {@/if}    
    {@if o.tradeType!="recharge"}
		<td class="table_num">-${o.tradeMoney}
          	<!--交易详情弹出-->
								<div class="bubbleInfo fl" style="position:relative;">
									<div><img id="download" class="trigger" src="../../images/oil/sideBar_icon6.png"></div>
									<div id="dpop" class="sidebar_mydiv popup" style="position:absolute;">
										<img class="sidebar_mydivicon" src="../../images/oil/sideBar_icon7.png">
										<table width="100%" id="" class="">
											<tr>
												<td class="first_mydiv">交易地点</td>
												<td class="last_mydiv">${o.tradeAdress}</td>
											</tr>
											<tr>
												<td class="first_mydiv">交易详情</td>
												<td class="last_mydiv">
													${o.productName} / ${o.productPrice/100}元/L / ${o.productNum/100}L
												</td>
											</tr>
										</table>
									</div>
								</div>
		</td>
    {@else}
    	<td class="table_num2">
			+${o.tradeMoney}
		</td>
    {@/if}
		
	{@if o.cardBalance != null}
		<td>${o.cardBalance}</td>
	{@else}
		<td>--</td>
	{@/if}
		
       
           	<!--充值失败-->
<td class="bar_lose">
{@if o.tradeType=="recharge"}
	{@if o.tradeStatus == '05'}
		交易成功
    {@else if o.tradeStatus == '03'}
                     充值失败
	{@else}
		处理中
	{@/if}
{@else}
	交易成功
{@if o.tradeStatus == '失败'}
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
{@/if}

</td>
	</tr>
{@/each}
{@else}
	<tr><td colspan="8" class="no-data">抱歉！没有找到与您条件匹配的数据，请重新搜索</td></tr>
{@/if}
<tr><td colspan="8">{@if totalPage>1}$${start|getPageNumbers,totalPage,5}{@/if}</td></tr>
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script src="../../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<!-- <script type="text/javascript" src="../../js/floatmessage.js"></script>  -->
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="oil_transaction.js"></script> 
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