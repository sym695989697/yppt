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
	<%-- <base href="<%=basePath%>"> --%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺油卡介绍</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_introduction.css">
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 1;
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
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/oil/oil_introduction.jsp">关于加油卡</a></li>
				</ul>
			</div>
	<!-- 导航菜单结束-->
	<!--content-->
	<div id="main_content" class="main w960 mauto cf help_pages_main">
	    <!--left-->
	    <jsp:include page="../left.jsp"/>
		<!--加油卡-->
		<div class="rightsideBar w800 fr">
		<div class="oil_intro_main">
		    <!--加油卡介绍-->
		    <div class="oil_intro">
				<div class="oil_intro_title h35">
					<span class="color_ff fb f14">车旺加油卡简介</span>
				</div>
				<div class="oil_intro_con border_rightBar">
					<ul>
					    <li>车旺加油卡由车旺与中石油、中石化联合推出，可在中石油、中石化全国网点加油消费，并享受加油站活动优惠。</li>
					    <li>使用车旺加油卡加油，您可获得加油消费返利。根据您的月消费额及加油地区，最高可获得2.5%的返利，返利可直接使用于油卡充值消费。</li>
					    <li>我们为您提供加油卡办理、充值及开票等一系列便捷服务，您仅需安装“车旺”手机APP或访问<a href="" onclick="window.open('http://www.95155.com');">www.95155.com</a>网站即可享受服务。</li>
					    <li class="mt15"><span class="block">我们提供的服务：</span>
						</li>
						<li class="oil_intro_table mt25 mb20">
						   <table>
						    <tr>
							    <td class="intro_pic"></td>
							    <td class="inlineB"></td>
							    <td class="intro_pic2"></td>
							    <td class="inlineB"></td>
							    <td class="intro_pic3"></td>
								<td class="inlineB"></td>
							    <td class="intro_pic4"></td>
								<td class="inlineB"></td>
							    <td class="intro_pic5"></td>
							</tr>
							<tr>
							    <td class="pt10 f14 pl10 pr10 vt">在线办理卡片</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">在线油卡充值</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">在线开票申请</td>
								<td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">消费记录查询及统计</td>
								<td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">免费短信提醒</td>
							</tr>
						</table>
						</li>
					    <li><span class="block">温馨提示：</span>
						    <dl>
							    <dd>返利计算时仅累计当月柴油消费，并在消费后的次月以“旺金币”返还，可直接使用于油卡充值消费。</dd>
							    <dd>车旺加油卡（中石油）可全国加油，全国返利（依据不同省份的返利率）；</dd>
							    <dd>车旺加油卡（中石化）可全国加油，在山东、福建本省消费享受返利。</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<!--如何办理加油卡-->
			<div class="oil_intro mt10">
				<div class="oil_intro_title h35">
					<span class="color_ff fb f14">如何办理加油卡</span>
				</div>
				<div class="oil_intro_con border_rightBar">
					<ul>
					    <li class="indent">通过“车旺”手机APP或登录<a href="" onclick="window.open('http://www.95155.com');">www.95155.com</a>网站，进入加油卡频道，填写申请信息，并上传车辆行驶证照片，提交申请后客服人员进行卡片办理并邮寄到您手中。</li>
                        <li class="oil_intro_table mt25 mb20">
						   <table>
						    <tr>
							    <td class="intro_pic6"></td>
							    <td class="intro_pic14"></td>
							    <td class="intro_pic7"></td>
							    <td class="intro_pic14"></td>
							    <td class="intro_pic8"></td>
							</tr>
							<tr>
							    <td class="pt10 f14 pl10 pr10 vt">填写信息</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">信息审核</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">卡片邮寄</td>
							</tr>
						</table>
						</li>
					    <li><span class="block">温馨提示：</span>
						    <dl>
							    <dd>卡片办理时间为1-3个工作日（节假日除外），您可通过手机APP或登录网站查询办理进度。</dd>
							    <dd>卡片寄出后，我们会向您办卡时登记的手机号发送邮寄短信通知。</dd>
							    <dd>如您需批量办理卡片，可拨打客服热钱400-096-6666咨询办理。</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<!--充值-->
			<div class="oil_intro mt10">
				<div class="oil_intro_title h35">
					<span class="color_ff fb f14">充值</span>
				</div>
				<div class="oil_intro_con border_rightBar">
					<ul>
					    <li>您可通过网上在线支付或银行转账方式对加油卡充值。</li>
                        <li class="mt15"><span class="block">网上在线支付：</span>
						    <dl>
							    <dd>登录<a href="" onclick="window.open('http://www.95155.com');">www.95155.com</a>网站，选中充值加油卡并设定充值金额后，可通过绑定支付银行卡方式（快捷支付）或跳转至网上银行页面（网银支付）进行充值；</dd>
							    <dd>或手机APP在线充值，支持绑定银行卡的“快捷支付”方式。</dd>
							</dl> 
						</li>
					    <li class="mt10"><span class="block">银行转账：</span>
							<span class="block color_intro indent">您通过银行转账方式将资金转至车旺对公账户后，在手机APP或网站填写信息并上传转账凭证照片，工作人员核对无误后资金充入资金账户，即可用于加油卡充值。</span>
						</li>
						<li class="oil_intro_table mt20 mb20">
						   <table>
						    <tr>
							    <td class="intro_pic9"></td>
							    <td class="intro_pic14"></td>
							    <td class="intro_pic10"></td>
							    <td class="intro_pic14"></td>
							    <td class="intro_pic11"></td>
							</tr>
							<tr>
							    <td class="pt10 f14 pl10 pr10 vt">提交充值</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">支付完成</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">充值到账</td>
							</tr>
						</table>
						</li>
						<li><span class="block">温馨提示：</span>
						    <dl>
							    <dd>网银充值只能通过网站操作。</dd>
							    <dd>通过在线支付方式充值，支付成功后，30分钟内充值到加油卡，同时您将收到充值完成短信。</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<!--开票-->
			<div class="oil_intro mt10">
				<div class="oil_intro_title h35">
					<span class="color_ff fb f14">开票</span>
				</div>
				<div class="oil_intro_con border_rightBar">
					<ul>
					    <li class="indent">您可通过手机APP或<a href="" onclick="window.open('http://www.95155.com');">www.95155.com</a>网站提交普通发票或增值税专用发票开具申请，客服人员对信息核实通过后开具发票并邮寄至您手中。</li>
                        <li class="indent">开具增值税专用发票，您需填写“开户行税号”，并上传“营业执照副本”照片、“税务登记证”照片、“组织机构代码证”照片和“一般纳税人证明”照片。
						</li>
					    <li class="oil_intro_table mt20 mb20">
						   <table>
						    <tr>
							    <td class="intro_pic12"></td>
							    <td class="intro_pic14"></td>
							    <td class="intro_pic7"></td>
							    <td class="intro_pic14"></td>
							    <td class="intro_pic13"></td>
							</tr>
							<tr>
							    <td class="pt10 f14 pl10 pr10 vt">填写信息</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">信息审核</td>
							    <td class="pt10"></td>
							    <td class="pt10 f14 pl10 pr10 vt">办理成功</td>
							</tr>
						</table>
						</li>
					</ul>
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/plugins.js"></script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>