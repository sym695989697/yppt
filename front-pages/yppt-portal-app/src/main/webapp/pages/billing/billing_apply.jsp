<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车旺油品服务-开票申请</title>
<link rel="stylesheet" href="../../css/common/base.css">
<link rel="stylesheet" href="../../css/common/common.css">
<link rel="stylesheet" href="../../css/common/new_common.css">
<link rel="stylesheet" href="../../css/oil/oil_pages.css">
<link rel="stylesheet" href="../../css/oil/eject.css">
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 7;
</script>
</head>
<style>
td {
	padding: 7px 5px;
}

.addrselector .addr {
	border: 1px solid #D5D9DE;
}
</style>
<body>
	<!--top-->
	<!-- 窄版页头 -->
	<fragment:include url="/page-head.html" />
	<!-- 首页宽版页头 -->
	<%-- <fragment:include url="/page-head-h.html"/> --%>
	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html" />
	<div class="main">
		<!-- 导航菜单结束-->
		<!-- 导航菜单 -->
		<div class="authen_con_chip">
			<ul class="pa">
				<li class="fl pr2 color_chip">车旺首页</li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a
					href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a
					href="<%=basePath%>pages/billing/billing_apply.jsp">开票申请</a></li>
			</ul>
		</div>
		<!--content-->
		<div id="main_content" class="main w960 mauto cf help_pages_main">
			<!--left-->
			<jsp:include page="../left.jsp" />
			<!--right-->
			<div class="rightsideBar w800 fr">
				<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl">开票申请</h3>
				</div>
				
				<div class="sideBar_bg border_rightBar billing_title pl20 pr20">
					<ul>
						<li class="f14 fl mr45 color_sizeA">选择发票信息</li>
						<li class="f14 fr color_sizeA">
							<a href="<%=basePath%>pages/oil/oil_introduction.jsp" class="color_ff pl15">了解发票</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<form  id="form">
				<div class="border_rightBar pb30" id="dataGrid">
				</div>
				<div class="pt20">
					<div class="rightsideBar_title ">
						<h3 class="fb f14 inline fl">开票信息</h3>
					</div>
					<div class="border_rightBar pb30 pt30">
						<div class="lists-form">
							<table>
								<tr>
									<td class="lab"><span class="required">*</span>开票金额：</td>
									<td class="fld">
										<input type="text" value="" id="billingMoney" name="billingMoney" datatype="billingMoney" errormsg="开票金额格式不对！" nullmsg="请填写开票金额！"/>
									</td>
									<td class="mark">元</td>
								</tr>
								<tr>
									<td class="lab">
										<span class="required">*</span>
										<span class="form_fld">收件人信息：</span>
									</td>
									<td class="fld">
										<span class="block"><span id="receiveName"></span> <span id="receiveMobi"></span></span>
										<span class="block"><span id="receivePCD"></span></span>
										<span class="block"><span id="receiveZipCode" ></span></span>
									</td>
									<td class="nextsel nextsel2">
										<input class="titlebtn titlebtn2" type="button" value="变更" id="changeAddr"/>
									</td>
									<td class="mark"></td>
								</tr>
							</table>
						</div>
						<div class="next-lists-form">
							<table>
								<tr>
									<td class="lab"></td>
									<td class="nextsel nextsel2">
										<input class="titlebtn titlebtn2" type="button" value="新增" id="addAddr"/>
									</td>
									<td class="mark"></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="mt30 f14 tc">
						<input type="hidden" id="recName" name="recName"/>
						<input type="hidden" id="recMobi" name="recMobi"/>
						<input type="hidden" id="recProvince" name="recProvince"/>
						<input type="hidden" id="recCity" name="recCity"/>
						<input type="hidden" id="recDistrict" name="recDistrict"/>
						<input type="hidden" id="recAddress" name="recAddress"/>
						<input type="hidden" id="recZipCode" name="recZipCode">
						<input type="hidden" id="recAddressId" name="recAddressId">
						<input type="hidden" id="billingInfoId" name="billingInfoId"/>
						<input class="billing_titlesbm" type="submit" value="提交" id="billingSubmitBtn"/>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/template" id="tplGrid">
	{@if data && data.length}
		<div class="inv-cont">
			<ul>
  				{@each data as o,i}
					<li>
						{@if i == 0}
							<a class="inv-selected">
						{@else}
							<a>
						{@/if}
							<span class="inv-cont-span1" width="20%" style="word-break:break-all;">${o.invoiceName}</span>
							<span class="inv-cont-span2">${o.invoiceType|getCodeName,'IC-INVOICE-TYPE'}</span>
							<input type="hidden" id="billingInfoId${i}" value="${o.id}"/>
						</a>
					</li>
				{@/each}
			</ul>
			<div class="clear"></div>
			<div class="pt30 f14 pl25 billing_titlebtn">
				<input class="titlebtn titlebtn2" type="button" value="使用新的抬头" id="useNewBillingInfo" onClick="setBillingInfo()"/>
			</div>
		</div>
	{@else}
		<div class="pt30 f14 pl25 billing_titlebtn">
			您还没有完善发票抬头<input class="titlebtn titlebtn2" type="button" value="完善发票资料" id="setBillingInfoBtn" onClick="setBillingInfo()"/>
		</div>
	{@/if}
	</script>
	<script type="text/javascript">
			var basePath = "<%=basePath%>";
	</script>
	<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../../js/plugins.js"></script>
	<script type="text/javascript" src="../../js/Validform_v5.3.2.js?v=g_version"></script>
	<script type="text/javascript" src="../../js/code_util.js"></script>
	<script type="text/javascript" src="../../js/util_ajax.js"></script>
	<script type="text/javascript" src="../../js/thlevelarea.js" ></script>
	<script type="text/javascript" src="billing_apply.js"></script>
	<!-- 页脚 -->
	<fragment:include url="/page-foot.html" />
</body>
</html>