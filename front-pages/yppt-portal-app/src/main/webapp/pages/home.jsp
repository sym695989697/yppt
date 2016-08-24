<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
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
<!DOCTYPE html>
<!-- saved from url=(0030)http://www.95155.com/home.html -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	   <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
	   <title>车旺95155云服务平台— 加油卡频道</title>
	   <meta name="keywords" content="车旺、物流、物流网、物流园、物流平台、物流信息、货运信息、货源、车源、货主、车主、加油、中石化、中石油、返利">
	   <meta name="description" content="车旺O2O云服务平台（www.95155.com），集诚信认证、车源查找、定位服务、轨迹回放、油品服务、全国救援维修服务、即时通讯、手机应用、信息资讯、24小时客服等服务于一体的便捷、全面、高效的商用车车联网应用服务平台">
	   <link rel="stylesheet" href="<%=basePath%>css/common/base.css">
	<link rel="stylesheet" href="<%=basePath%>css/common/common.css">
	<link rel="stylesheet" href="<%=basePath%>css/index/style.css">
	<link type="text/css" rel="stylesheet" href="<%=basePath%>css/oil/oil_pages.css">
<!--[if IE 8]>
<style type="text/css" />
.oil_login{
 position:relative;
 top:-313px; 
 left:470px;
} 
</style>
<![endif]-->
<!--[if IE 9]>
<style type="text/css" />
.oil_login{
 position:relative;
 top:-313px; 
 left:470px;
} 
</style>
<![endif]-->
</head>
<body class="noIframe">
<div class="cw-container">
	<fragment:include url="/page-head-h.html"/>
	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html" />
  	<!--banner-->
    <div class="oil_container">
		<div class="oil_banner_line"></div>
	    <div class="main_slider">  
            <div class="slide-item oil_bg" style="display:block;"><div class="slide_pic"><img src="<%=basePath%>images/oil/oil_banner.png" width="1190" height="340"/></div></div>
            <div class="slide-item oil_bg2"><div class="slide_pic"><img src="<%=basePath%>images/oil/oil_banner2.png" width="1190" height="340" /></div></div>
            <div class="slide-item oil_bg3"><div class="slide_pic"><img src="<%=basePath%>images/oil/oil_banner3.png" width="1190" height="340" /></div></div>
        </div>
	    <div class="thumbs">
            <ul>
		        <li class="thumb1 curr"></li>
		        <li class="thumb2"></li>
		        <li class="thumb3"></li>
	        </ul>
        </div>
		<!--登录框-->
		<div class="oil_login"></div>
		<div class="oil_logins">
		    <div class="oil_login_main">
			    <ul id="noLogin">
				    <li class="oil_login_top"><span class="fl">上月返利</span><span class="fr oil_num"><a href="#">48</a>万元</span></li>
					<li class="clear"></li>
					<li class="oil_login_mid clear mt10"><span class="oil_num2">5000</span>座中石化加油站</li>
				    <li class="oil_login_mid"><span class="oil_num2">22000</span>座中石油加油站</li>
				    <li class="oil_login_btn"><input type="button" value="立即注册" id="register"></li>
					<li class="oil_login_bot">已有账号？<a href="#" onclick="toLogin()">立即登录</a></li>
				</ul>
				<ul id="userLogin" style="display: none;">
				    <li class="tc"><img id="headPicURL" src="<%=basePath%>images/oil/oil_login_icon.png"></li>
				    <li class="tc f16 mt15">欢迎回来，<span id="userName"></span></li>
				    <li class="oil_login_btn"><input class="mt10" id="enterButton" type="button" value="立即办卡"></li>
					<!-- <li class="tr f14 pr25"><a onclick="loginout();">注销</a></li> -->
				</ul>
			</div>
		</div>
    </div>
    <!--3个图标-->
	<div class="oil_icon_cen">
	    <div class="oil_icon_tab">
	    <style>.oil_icon_tab  table tr td{text-align:center}</style>
		    <table width="100%">
			    <tr>
				    <td><img src="<%=basePath%>images/oil/oil_icon_yp.png"></td>
					<td><img src="<%=basePath%>images/oil/oil_icon2_yp.png"></td>
					<td><img src="<%=basePath%>images/oil/oil_icon3_yp.png"></td>
				</tr>
				<tr class="icon_tabtwo">
				    <td>省钱</td>
					<td>省心</td>
					<td>保障</td>
				</tr>
				<tr class="icon_tabtwo2">
				    <td>全国加油跨省优惠<br/>每年综合节省13000元</td>
					<td>专属客户经理<br/>资金变动实时免费短信通知</td>
					<td>权威合作，油质保证<br/>中石化中石油倾力加盟</td>
				</tr>
			</table>
		</div>
	</div>
	<!--banner2-->
	<div class="oil_small_banner">
	    <div id="slide_banner">
		    <ul class="slide_ul">
		    <!--优惠信息查询 开卡申请 立刻充值   -->
              <li><a href="<%=basePath%>pages/oil/oil_introduction.jsp" ><img src="<%=basePath%>images/oil/oil_bnner_bg.png"></a></li>
              <li><a href="<%=basePath%>pages/card/card_apply.jsp"><img src="<%=basePath%>images/oil/oil_bnner_bg2.png"></a></li>
              <li><a href="<%=basePath%>pages/oil/oil_cards.jsp" ><img src="<%=basePath%>images/oil/oil_bnner_bg3.png"></a></li>
            </ul> 
		</div>
	</div>
	<!--手机下载-->
	<div class="oil_phone_down">
	    <div class="phone_down_main">
		    <dl>
			    <dt ><img src="<%=basePath%>images/oil/oil_pic_yp.png"></dt>
				<dd><img src="<%=basePath%>images/oil/oil_pic2_yp.png"></dd>
				<dd class="down_num">车旺APP，轻松开通车旺加油卡，快速知晓加油明细，及时查询月度返利<br/>
				    记录。即刻下载车旺APP，享用会省钱的移动加油站。
				</dd>
				<dd><img class="mr70" src="<%=basePath%>images/oil/oil_pic3_yp.png"><img src="<%=basePath %>images/oil/oil_pic4_yp.png"></dd>
			    <dd class="phone_downbtn"><input class="mr70" type="button" value="从苹果商店下载" onclick="window.location.href='https://itunes.apple.com/cn/app/che-wang/id917562471?mt=8';">
				    <input type="button" value="下载android应用"  onclick="window.location.href='http://file.95155.com/file/app_carriers.apk';" />
				</dd>
			</dl>
		</div>
		<div class="clear"></div>
	</div>
	<div class="oil_problem">
	    <div class="oil_problem_main">
		    <h3>常见问题</h3>
			<ul>
			    <li class="mr60">
				    <dl>
					    <dt>会员通过POS 充值到公司账户后，如何进行充值到油卡的操作？</dt>
						<dd>方式一：客通过登入车旺APP后提交油卡充值申请，并上传签名的刷卡交易小票。</dd>
						<dd>方式二：客户致电客户专属客户经理并将签名的刷卡交易小票和充值的加油卡卡
						    号提交给专属客户经理，由专属的客户经理负责客户的来款充值分配。
					</dl> 
				</li>
			    <li>
				    <dl>
					    <dt>忘记密码怎么办？</dt>
						<dd>会员通过在门户网站或车旺APP上的“找回密码”功能，通过注册手机号设置账
						    号新的密码；或致电24小时客服热线4000966666，客服进行重置密码操作。
					    </dd>
					</dl>
				</li>
			    <li class="mr60">
				    <dl>
					    <dt>我的短信通知号码可以修改吗？可以关闭短信通知吗？</dt>
						<dd>会员通过手机APP或门户网站登入会员账户后在“卡管理”进行修改短信通知号
						    码、关闭短信通知功能。
						</dd>
					</dl>
				</li>
			    <li>
				    <dl>
					    <dt>油卡消费后如何查看消费记录？</dt>
						<dd>可登陆门户网站<a href="http://www.95155.com">www.95155.com</a>，或者登录APP 进入油品频道 “油卡交易记
						    录”中查询车旺加油IC卡消费后的第三天会员能登入车旺平台（APP&WEB）查
							询到加油明细。
						</dd>
					</dl>
				</li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
  <!--底部-->
  <!-- 页脚 -->
	<fragment:include url="/page-foot.html"/>
  <div>
	<iframe src="<%=basePath%>pages/login.jsp" style="display: none" ></iframe>
  </div> 
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/script.js"></script>
<script type="text/javascript" src="<%=basePath%>js/index.js"></script>
<script type="text/javascript">
	$(function(){
		$("#register").click(function(){
			window.location.href='<%=serverName%>/pages/member/member_reg_step1.html';
		});
		$('#__pltp_nav #__pltp_nav_topNav').find('a').eq(4).addClass('crt');
		//$('#noLogin').show();
		//$('#userLogin').hide();
	});
	function loginout(){
		 if(confirm("是否注销当前用户?")){
			 window.location.href='<%=basePath%>pages/logout.jsp';
		 }else{
			 return false;
		 }
	}
	var loginPage = '<%=serverName%>/pages/member/member_login.html';
	function toLogin(){
		$.ajax({
			type:"POST",
			data:{},
			url:"../login/redirectHomePage.action",
			datatype:"JSON",
			success:function(data){
				window.location.href = loginPage;
		    },
		    error:function(e, d, f){
		    	window.location.href = loginPage;
		    }
	    });
	}
</script>
</html>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/plugins.js" ></script>
<script type="text/javascript" src="<%=basePath%>/pages/home.js" ></script>
<script src="//hm.baidu.com/hm.js?8e6580c18559b4c129e3369eae3d209f"></script>
<script src="http://s4.cnzz.com/z_stat.php?id=1254000558&web_id=1254000558" type="text/javascript"></script>
<!-- <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254000558'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1254000558' type='text/javascript'%3E%3C/script%3E"));</script> -->