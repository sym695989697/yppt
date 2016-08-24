<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<title>车旺油品服务-油卡返利</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/common/style.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
		<link rel="stylesheet" href="../../css/layer.css">
	<link rel="stylesheet" href="../../css/share.css">
	
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
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/gold.jsp">旺金币</a></li>
			</ul>
		</div>
	<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--left-->
		<!-- 左侧主菜单 -->
		<fragment:include url="/main-menu.html"/>
		<!--right-->
		<div class="rightsideBar w800 fr">
			<div class="rightsideBar_title">
				<h3 class="fb pl10 f14 inline fl">油卡返利</h3>
			</div>
			<div class="sideBar_bg border_rightBar p20 h30">
				<ul>
					<li class="f18 fl mr45 color_sizeA">旺金币：<span class="posi color_ff fb f24" id="wangGold">0</span>
					    <input class="titlebtn" type="button" id="oilCardRecharge" value="油卡充值">
					</li>
					<li class="f18 fl color_sizeA">累计返利：<span class="posi color_ff fb f24" id="accumRebate">0</span><span class="color_ff f18 ml2">旺金币</span><span class="f12">(统计到上个月)</span></li>
				</ul>
			</div>
			<!--返利收支明细-->
			<div class="pt20">
				<div class="rightsideBar_title">
					<h3 class="fb pl10 f14 inline fl">返利收支明细</h3>
				</div>
				<div class="h80 sideBar_deta sideBar_bg border_rightBar p20 queryBlock">
				    <ul>
					    <li class="li">
						    <div class="lab color_sizeB">日期</div>
							<div class="datetime datetimelang2 input_bar" >
								<input class="mr5 Wdate queryParam" type="text" id="startTime" name="requestParam.startwith.operateTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})">
							至
								<input class="ml5 Wdate queryParam" type="text" id="endTime" name="requestParam.endwith.operateTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})">
							</div>
						</li>
						<li class="li">
						    <div class="lab color_sizeB">分类</div>
							<div class="selectDiv" id="classfiy">
							    <select name="requestParam.equal.model" id="model">
								    <option value="">全部</option>
								    <option value="1">收入明细</option>
								    <option value="2">支出明细</option>
								</select>
							</div>
						</li>
						<input type="hidden" class="pageNum pageParam" name="requestParam.page">
          				<input type="hidden" class="pageSize pageParam" name="requestParam.rows">
						<li class="li">
						    <span class="Barsidebtn" ><input type="button" value="查询" style="width:90px;cursor:pointer;" onclick="window.theRebate.search();"></span>
						</li>
					</ul>
				</div>
			</div>
				
			<div class="pt20">
				<!-- <div class="rightsideBar_title ">
					<span class="mr80">进账总额：<span class="color_ff" id="inTotal">0</span></span>
					<span>出账总额：<span class="color_sizeC" id="outTotal">0</span></span>
				</div> -->
				<div class="border_rightBar table_border">
				     <table width="100%" class="table_border2">
						<thead>
							<tr>
							     <th class="first_th w150">时间</th>
								 <th class="w120">旺金币</th>
								 <th class="w120">类型</th>
								 <th>备注</th>
							</tr>
						</thead>
						<tbody id="dataGrid"></tbody>
					</table>
				</div>
			</div>
			<div class="clear"></div>
			<!--旺金币说明-->
					<div class="coin_explain mt30 mb30">
					    <div class="coin_explain_top tc"><span class="fb color_ff">旺金币说明</span></div>
						<div class="coin_explain_con pl20 pt30 pr20">
						    <h3>什么是旺金币？</h3>
							<p>旺金币是车旺用户使用车旺加油卡加油后，在次月给予会员奖励，旺金币不可挂失转让。
							</p>
							<h3>旺金币用途</h3>
							<div class="coin_explain_con2 mt20">
							   <div class="coin_line2">&nbsp;</div>
								<div class="coin_explain_con_tab">
								   <table width="100%">
										<tr>
											<th width="190px">用途</th>
											<th>兑换规则</th>
										</tr>
										<tr class="border_blue2">
										    <td>车旺加油卡充值</td>
										    <td>100个旺金币可抵扣1元</td>
										</tr>
								    </table>
								</div>
							</div>
							<h3>如何获得旺金币</h3>
							<div class="rightBar_layers tc mt30">
					<table>
						<tr>
							<td class="gold_pic"></td>
							<td class="gold_pic5"></td>
							<td class="gold_pic2"></td>
							<td class="gold_pic5"></td>
							<td class="gold_pic3"></td>
							<td class="gold_pic5"></td>
							<td class="gold_pic4"></td>
						</tr>  
						<tr>
							<td class="pt10 f14 fb Barlayers_color pl10 pr10 vt">办理车旺加油卡</td>
							<td class="pt10"></td>
							<td class="pt10 f14 fb Barlayers_color pl10 pr10 vt">充值到卡</td>
							<td class="pt10"></td>
							<td class="pt10 f14 fb Barlayers_color pl10 pr10 vt">消费</td>
							<td class="pt10"></td>
							<td class="pt10 f14 fb Barlayers_color pl10 pr10 vt">下月初发放本月返利旺金币</td>
						</tr>
					</table>	
	            </div>
				<div class="coin_explain_num color_blackA">旺金币规则由中交兴路车联网科技有限公司制定并依据国家相关法律法规及规章制度予以解释和修改，规则以网站公布为准。</div>
				<div class="coin_explain_con_bot">&nbsp;</div>
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
		<td class="first_td">$${o.operateTime|getSmpFormatDateByLong,true}</td>
	{@if o.model == '1'}
 		<td class="table_num2">+
	{@else}
		<td class="table_num">-
	{@/if}
		${o.creditNum}
		</td>
		<td>$${o.type|getCodeName,'YCFLLX'}</td>
		<td>
	{@if o.remark != null}
				${o.remark}
	{@else}
			--
	{@/if}
		</td>
	</tr>
	{@/each}
{@else}
		<tr><td colspan="4" class="no-data">抱歉！没有找到与您条件匹配的收支，请重新搜索</td></tr>
{@/if}
		{@if totalPage > 1}<tr><td colspan="4">$${start|getPageNumbers,totalPage,5}</td></tr>{@/if}
</script>

<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script src="../../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="oil_rebate.js"></script>
	<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body> 
</html>