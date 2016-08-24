<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ctfo.common.models.Index"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%@ page import="java.util.*,java.io.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//获取配置信息
Properties prop = new Properties();
InputStream in = getClass().getResourceAsStream("/system.properties");
prop.load(in);
String serverName = prop.getProperty("yp.serverName");

Index index = (Index) session.getAttribute(Converter.SESSION_INDEX);
String userName = "";
String mobile = "";
String idNo = "";
if(index != null){
    userName = index.getUserName() == null ? "" : index.getUserName();
    mobile = index.getMobile() == null ? "" : index.getMobile();
    idNo = index.getIdcardNo() == null ? "" : index.getIdcardNo();
}
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>车旺油品服务-我要办卡</title>
	<link rel="stylesheet" href="../../css/common/base.css">
	<link rel="stylesheet" href="../../css/common/common.css">
	<link rel="stylesheet" href="../../css/common/new_common.css">
	<link rel="stylesheet" href="../../css/oil/oil_pages.css">
	<link rel="stylesheet" href="../../css/oil/card_handle.css">
<style>
.addrselector .addr{
border: 1px solid #D5D9DE;
height: 28px;
background:url('../../images/handle/arrowupdown.png') no-repeat scroll 176px 11px transparent;
cursor: pointer;
line-height: 28px;
}
.selectDiv{
border: 1px solid #D5D9DE;
height: 28px;
width:199px;
overflow:hidden;
line-height: 28px;
}
.selectDiv select{
border: 0px solid #D5D9DE;
background:url('../../images/handle/arrowupdown.png') no-repeat scroll 176px 11px transparent;
height: 28px;
}
/*提交前上传按钮*/
input.nextselbtn[type="button"] {
	width: 60px;
	height: 24px;
	color: #fff;
	background-color: #ff7200;
	border: 0px;
	border-radius: 2px 2px 2px 2px;
	cursor: pointer;
	font-size: 14px;
	margin-top: 0px;
}
/*提交后上传按钮*/
input.nextselbtn2[type="button"] {
	width: 60px;
	height: 24px;
	border: 0px;
	border-radius: 2px 2px 2px 2px;
	cursor: pointer;
	font-size: 14px;
	margin-top: 0px;
}
#text3{
    word-break:break-all;
}
</style>
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 3;
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
				<li class="fl pl2 pr2"><a href="<%=basePath%>pages/card/card_apply.jsp">我要办卡</a></li>
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
				<h3 class="fb f14 inline fl">我要办卡</h3>
			</div>
			<div class="card-prompt" id="close"><span>当前页面可提交十张开卡信息，如果您有大量的油卡需求，可拨打客服电话4000966666</span><input type="button" class="fc-btn3"  onclick="test()" /></div>
	        <div class="card-box1" id="basic_box">
		        <form>
                         <span class="tex-wid90"><font color="red">*</font>油卡类型：</span>
	                     <div  id="selectOilType" class="selectDiv" style="position:relative;width:198px;float: left;">
                            <select id="cardType">
                            </select>
	                    </div>
	                    
	                    <span class="w160" style="margin-left: 10px; width: 300px;"></span>
	                    <div class="cb"></div>
                        <span class="tex-wid90" style="margin-top: 10px;"><font color="red">*</font>常用地区：</span>
						<div id="oftenUseAddress" class="mt5 zi2 origin" style=" padding-top: 0px; color: #B4B4B4; width: 199px; margin-top: 10px;">
								<input type="hidden" name="openCardProvince" class="term _sendProvinceCode" id="provinceCode" />
								<input type="hidden" name="sendProvinceName" class="term" id="provinceName"/>
								<input type="hidden" name="openCardCity" class="term _sendCityCode" id="cityCode" />
								<input type="hidden" name="sendCityName" class="term" id="cityName"/>
								<input type="hidden" name="openCarddistrict" class="term _sendCountyCode"/>
								<input type="hidden" name="sendCountyName" class="term"/>
						</div>
	                    <ul class="region" style=" margin-left: 10px; margin-top: 15px;" id="oftenusebq">
                	    </ul>
						<span class="w160 notValid" id="area" style="margin-left: -2px;margin-top:10px;color: 666;">最多可选择三个地区</span>
                	    <div class="cb"></div>
                        <span class="tex-wid90" style="margin-top: 9px;"><font color="red">*</font>姓名：</span><input type="text" class="myinput" style=" margin-top: 13px;" id="name">
                        <div class="cb"></div>
                        <span class="tex-wid90"><font color="red">*</font>身份证号：</span><input type="text" class="myinput" id="presonN"/>
                        <div class="cb"></div>
                        <span class="tex-wid100 mt-5" style="display: block;float: left;line-height: 30px;"><font color="red">*</font>收件人信息：</span>
                        <span class="tex-wid500 mt-5">
	                        <ul class="rep-info" style="width: 500px;">
	                            <li id="addressId" style="display: none;"></li>
	                            <li id="addressProvince" style="display: none;"></li>
	                            <li id="addressCity" style="display: none;"></li>
	                            <li id="addressCounty" style="display: none;"></li>
	                            <li id="addressMore" style="display: none;"></li>
	                            <li id="phoneNum" style="display: none;"></li>
	                            <li id="zipCode" style="display: none;"></li>
	                            <li id="receiverName" style="display: none;"></li>
	                		    <li id="text1" ></li>
	                            <li id="text2" ></li>
	                            <li id="text3" ></li>
	                	    </ul>
                        
                        </span>
                       <!--  <ul class="rep-info" >
                            <li id="addressId" style="display: none;"></li>
                            <li id="addressProvince" style="display: none;"></li>
                            <li id="addressCity" style="display: none;"></li>
                            <li id="addressCounty" style="display: none;"></li>
                            <li id="addressMore" style="display: none;"></li>
                            <li id="phoneNum" style="display: none;"></li>
                            <li id="zipCode" style="display: none;"></li>
                            <li id="receiverName" style="display: none;"></li>
                		    <li id="text1" ></li>
                            <li id="text2" ></li>
                            <li id="text3" ></li>
                	    </ul> -->
                	    <div class="cb"></div>
                        <input type="button" value="变更地址" class="button1" id="changeAddress" style="cursor: pointer;margin-bottom:10px;"/>
                </form>
	        </div>
			<!--卡片1-->
			<div class="carddiv card-box2" id="close_1">
				<div class="card-box2-tit">
					<ul class="close-pro">
						<li>卡片<img src="../../images/handle/close1.png" style="cursor: pointer;"><div>确定要删除吗？<br><a  id="1_closecard" class="closecard">确定</a>  <a class="closeeixt">取消</a></div></li>
					</ul>
				</div>
				<div class="cb"></div>
				<form style="margin-bottom: 20px;">
					<span class="tex-wid90"><font color="red">*</font>车牌号：</span><input type="text" class="myinput carNumber" id="carNumber1">
					<div class="cb"></div>
					<span class="tex-wid90"><font color="red">*</font>行驶证：</span>
					<div id="payPicId_file1" class="fl img_tops"></div>
					<div class="cb"></div>
					<span class="tex-wid90"><font color="red">*</font>绑定手机：</span><input type="text" class="myinput telephone" id="telephone1"><span class="w160">此手机号用于交易短信提醒</span>
					<div class="cb"></div>
					<span class="tex-wid90"><font color="red">*</font>短信提醒：</span>
								<div class="selectDiv" style="position:relative;">
									<select id="isSend1">
									  <option value="1">开</option>
									  <option value="0">关</option>
									</select>
								</div>
					<div class="cb"></div>
				</form>
			</div>
		
			<div class="sub-info" style="margin-top: 5px;">
			    <div style="float: right;">
				<a id="addcardDiv">继续添加卡片</a>
				<div id="numcardDiv">已经添加1张卡片</div>
				</div>
				<div class="cb"></div>
				<div class="agree-sub"><input type="checkbox" value="checkbox" checked="checked" class="checkbox" id="isAgree">我已经阅读并同意<span style="cursor: pointer;" onclick="window.open('rules.jsp');">《车旺加油卡服务协议》</span></div>
				<div class="cb"></div>
				<input type="button" value="提交办卡" class="button1" id="openCard" style="cursor: pointer;"/>
			</div>
		</div>	
    </div>	
</div>
</div>
<script type="text/javascript">
var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="../../js/plugins.js" ></script>
<script type="text/javascript" src="../../js/SimpleValidator.js" ></script>
<script type="text/javascript" src="../../js/code_util.js" ></script>
<script type="text/javascript" src="../../js/util_ajax.js" ></script>
<script type="text/javascript" src="../../js/thlevelarea.js"></script>
<script type="text/javascript" src="card_apply.js"></script>
<script type="text/javascript">
var userName = "<%=userName%>";
var mobile = "<%=mobile%>";
var idNo = "<%=idNo%>";

$(function(){
 $(".close-pro li img").click(function(){
  $(this).parent().find("div").show();
 });
 $(".closeeixt").click(function(){
	 $(this).parent().parent().find("div").hide();
 });
});
</script>
<!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
</body>
</html>