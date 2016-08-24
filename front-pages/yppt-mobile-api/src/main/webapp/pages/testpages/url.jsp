<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>app接口测试</title>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
			<script type="text/javascript" src="../../js/testbase.js"></script>
			<style>
html {
	overflow-x: hidden;
}

body {
	font-family: "微软雅黑";
	font-size: 12px;
}
</style>
</head>
<body>
	<div
		style="font-size: 20px; margin: auto; position: relative; margin-top: 30px; width: 100%; text-align: center;">app接口调用测试</div>
	<div
		style="float: left; width: 100%; border: 1px solid #A1A1A1; margin-top: 15px;"></div>
	<div
		style="font-size: 12px; font-weight: bold; float: left; margin-top: 5px; margin-left: 100px; width: 100%">流程：</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 80%; word-wrap: break-word; word-break: normal;">1.新打开页面使用下方常用地址中的md5对您的密码进行加密2.新打开页面通过访问下方生成tokenId地址获取tokenId，并保持页面不刷新
		3.通过xls中提供的json串替换对应接口中的参数为您需要输入的值同时替换tokenId4.将拼接好的json串填入下方地址输入框中5.点击调用6.调用结果会在输出结果中显示6.为更好的校对返回参数正确性，请将返回结果在下方的json在线格式化工具中进行格式化</div>
	<div
		style="font-size: 12px; font-weight: bold; float: left; margin-top: 5px; margin-left: 100px; width: 100%">注意事项：</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 40%">1.输入地址为app接口端服务访问地址，等同于app客户端调用</div>
	<div
		style="font-size: 12px; float: left;; margin-left: 100px; width: 40%">2.输出为服务端返回给客户端参数，等同于客户端接口参数</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 40%">3.请按照接口文档参数来拼接url地址</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 40%; color: red;">4.调用app服务端接口之前，请先访问XXX生成tokenid并且保持页面不刷新</div>
	<div
		style="font-size: 12px; font-weight: bold; float: left; margin-top: 5px; margin-left: 100px; width: 100%">常用地址和工具：</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 40%">
		1.json在线格式化<a href="http://www.bejson.com" target="_blank">http://www.bejson.com</a>
	</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 40%">
		2.md5密码加密<a href="http://www.qqxiuzi.cn/bianma/md5.htm"
			target="_blank">http://www.qqxiuzi.cn/bianma/md5.htm</a>
	</div>
	<div
		style="font-size: 12px; float: left; margin-left: 100px; width: 100%">3.生成tokenId地址:http://192.168.110.108:18080/mobile/remoteLoginApp?username=您的用户名&password=加密后的密码</div>
	<div
		style="float: left; width: 100%; border: 1px solid #A1A1A1; margin-top: 15px;"></div>

	<div
		style="width: 40%; margin-left: 100px; float: left; margin-top: 10px; font-size: 14px;">输入地址></div>
	<div
		style="width: 40%; margin-left: 100px; float: left; margin-top: 10px; font-size: 14px;">输出结果></div>

	<div
		style="width: 40%; margin-left: 100px; float: left; margin-top: 10px; font-size: 12px; height: 380px;">
		<textarea rows="20" cols="70" id="testurl" style="font-size: 14px;"></textarea>
	</div>
	<div style="width: 100px; float: left; height: 300px; height: 300px;">
		<input type="button" value="调用接口"
			style="margin-top: 150px; width: 80px; height: 30px;"
			onclick="window.testBase.testUrl();" />
	</div>
	<div
		style="width: 40%; float: left; margin-top: 10px; font-size: 12px; height: 380px;">
		<textarea rows="20" cols="70" id="result" style="font-size: 14px;"></textarea>
	</div>
	<div
		style="float: left; width: 100%; border: 1px solid #A1A1A1; margin-top: 5px;"></div>




</body>
</html>
