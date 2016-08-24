<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!--left-->


<div class="main_leftsideBar fl w150">
	<div class="leftsideBar">
		<span><img src="<%=basePath%>images/oil/sideBar_icon10.png">
		</span> <span class="f16 vm pl5"><a href="<%=basePath%>pages/oil/oil_home.jsp" style="color: #FFF;text-decoration:none;">我的加油卡</a></span>
	</div>
		<ul class="leftsideBar_border">
			<li class="leftsideBar_title fb f14">油卡管理</li>
			<li><a href="<%=basePath%>pages/oil/oil_introduction.jsp">关于加油卡</a></li>
			<li><a href="<%=basePath%>pages/oil/my_oilcards.jsp">卡片管理</a></li>
			<li><a href="<%=basePath%>pages/card/card_apply.jsp">我要办卡</a></li>
			<li><a href="<%=basePath%>pages/oil/oil_cards.jsp">油卡充值</a></li>
			<li><a href="<%=basePath%>pages/oil/oil_transaction.jsp" id="oil_transaction">油卡交易</a></li>
			<li><a href="<%=basePath%>pages/oil/oil_rebate.jsp" id="oil_rebate">油卡返利</a></li>
			<ul>
				<li class="leftsideBar_title fb f14">油卡发票</li>
				<li><a href="<%=basePath%>pages/billing/billing_apply.jsp">开票申请</a></li>
				<li><a href="<%=basePath%>pages/billing/billings.jsp">开票记录</a></li>
				<li><a href="<%=basePath%>pages/billing/billing_info.jsp">发票资料</a></li>
			</ul>
		</ul>
</div>
