<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%@page import="com.ctfo.yppt.portal.view.oilcard.OilCardChargeVos"%>
<%@page import="com.ctfo.yppt.portal.view.oilcard.OilCardChargeVo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>车旺油品服务-选择充值方式</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/common/style.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 4;
</script>	
</head>
<style>
#Validform_msg{
display: none;
}
</style>
<body>
<!--top-->
<!-- 首页宽版页头 -->
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
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_cards.jsp">油卡充值</a></li>
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
				<h3 class="fb f14 inline fl">充值信息</h3>
			</div>
			<div class="border_rightBar">
				<table width="100%" class="table_border2">
					<thead>
					<tr>
					     <th class="first_th w250">IC卡号</th>
						 <th class="w200">绑定车辆</th>
						 <th class="w200">余额</th>
						 <th>充值金额（元）</th>
					</tr>
				</thead>
				<tbody id="dataGrid">
				<%
				    OilCardChargeVos cardChargeVos= (OilCardChargeVos) session.getAttribute(Converter.SESSION_OIL_CARD_CHARGE_DATA);
												    		if(cardChargeVos != null && cardChargeVos.getCardRecharges() != null && cardChargeVos.getCardRecharges().size() > 0)  {
												    		    int index = 0;
												    		    for(OilCardChargeVo cardCharge : cardChargeVos.getCardRecharges()) {
												    		        index++;
												    		         if(index % 2 ==0) {
				%>
						    		     <tr class="table_bg">
						    		  <%
				    		         } else {
				    		        %>
				    		        	<tr>
				    		        	<%
				    		         }
				    		         %>
				    		        <td class="first_td"><%=cardCharge.getCardNO()%></td>
				    		        <td><%=cardCharge.getCarNO()%></td>
				    		        <td><%=cardCharge.getBalance()%></td>
				    		        <td class="inputmoney"><%=cardCharge.getRechargeAmount() == null  ? "" : cardCharge.getRechargeAmount()%></td>
				    		        </tr>
				    		        <%
				    		    }
				    		}
				    	%>
				</tbody>
			</table>
			<div class="pr25 pt20 h20">
				<span class="color_sizeA f14 fb fr">充值总额：<span class="color_ff"><span id="inputpaynum">0.00</span>元</span></span>
			</div>
			</div>
			<form id="affrimForm">
				<div class="mt20 pl25">
				    <ul>
				        <li class="fl f14 mr50 ">
				        <!-- <label class="label_check" id="paycheckwang">
				        <input type="checkbox" class="vm mr10 paycheck" id="paycheck1" enable="false">
						可用旺金币 <span id="totalWangjinbi">0.00</span>元
				        </label>
				         -->
						可用旺金币 <span id="totalWangjinbi">0.00</span>元
				        		使用 : <input type="text" id="wangjinbi" value="0" style="width: 80px;text-align: center;"/> 元
				        </li>
				        <li class="fl f14 mr50 pb25">还需支付：<span id="zhangfuyue">0.00</span>元</li>
					</ul>
						<input type="hidden" id="paycheck1" name="paycheck1" value="off"/>
						<input type="hidden" id="paycheck2" name="paycheck2" value="off"/>
						<input type="hidden" name="paychecknum1" value=""/>
						<input type="hidden" name="paychecknum2" value=""/>
						<input type="hidden" name="orderId" id="orderId" value=""/>
					<div class="clear f14 tc"><input class="titlebtn titlebtn2" type="submit" value="提交充值"></div>
				</div>  
			</form>  
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script> 
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script src="../../js/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
<!-- <script type="text/javascript" src="../../js/checkbox.js"></script> -->
<script type="text/javascript" src="card_recharge_way.js"></script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>