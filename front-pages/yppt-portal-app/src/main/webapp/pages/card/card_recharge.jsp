<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%@page import="com.ctfo.yppt.portal.view.oilcard.OilCardChargeVos"%>
<%@page import="com.ctfo.yppt.portal.view.oilcard.OilCardChargeVo"%>
<%@ page import="java.util.*,java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//获取配置信息
Properties prop = new Properties();
InputStream in = getClass().getResourceAsStream("/system.properties");
prop.load(in);
String serverName = prop.getProperty("yp.serverName");
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺油品服务-油卡充值</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/layer.css">
	<link rel="stylesheet" href="../../css/share.css">
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
			<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_cards.jsp">我的油卡</a></li>
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
				<h3 class="fb f14 inline fl">已选油卡信息如下</h3>
			</div>
			<div class="border_rightBar">
				<table width="100%" class="table_border2">
					<thead>
						<tr>
							<th class="first_th w200">IC卡号</th>
							<th class="w120">绑定车辆</th>
							<th class="w120">余额</th>
							<th>充值金额（元）</th>
							<th>操作</th>
					    </tr>
				    </thead>
				    <tbody id="dataGrid">
				    	<%
				    	    OilCardChargeVos cardChargeVos= (OilCardChargeVos) session.getAttribute(Converter.SESSION_OIL_CARD_CHARGE_DATA);
				    					    					    		if(cardChargeVos != null && cardChargeVos.getCardRecharges() != null && cardChargeVos.getCardRecharges().size() > 0)  {
				    					    					    		    int indexNum = 0;
				    					    					    		    for(OilCardChargeVo cardCharge : cardChargeVos.getCardRecharges()) {
				    					    					    		    	indexNum++;
				    					    					    		         if(indexNum % 2 ==0) {
				    	%>
						    		     <tr class="table_bg">
						    		  <%
				    		         } else {
				    		        %>
				    		        	<tr>
				    		        	<%
				    		         }
				    		         %>
				    		        <td class="first_td"><input type="hidden" value="<%=cardCharge.getId()%>">
				    		        <%=cardCharge.getCardNO()%></td>
				    		        <td><%=cardCharge.getCarNO()%></td>
				    		        <td><%=cardCharge.getBalance()%></td>
				    		        <td><input class="table_text rechargeAmount" type="text" value=""></td></td>
				    		        <td><a href="#" class="color_sizeD">移除</a></td>
				    		        </tr>
				    		        <%
				    		    }
				    		}
				    	%>
				    </tbody>
				</table>
				<div class="pt25 pb20 pl20 pr20 h20">
				    <ul>
					    <li class="color_ff f14 fb fl" style="position:relative;top:-3px;">批量设置充值金额
						    <input class="table_text ml10" type="text" value="" id="batchRecharge">
						</li>
						<li class="color_sizeA f14 fb fr">充值总额：<span class="color_ff"><span id="rechargeAmount">0.00</span>元</span></li>
					</ul>
				</div>
			</div>
			<div class="tc mt30">
			    <input class="sideBar_btn btn_bra" type="button" value="重新选卡" id="reSelectCard">
				<input class="sideBar_btn btn_bra2" type="button" value="确定充值" id="comfirmRecharge">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="card_recharge.js" ></script>  
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>