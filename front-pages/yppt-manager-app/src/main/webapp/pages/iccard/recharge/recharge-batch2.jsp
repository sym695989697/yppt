<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script type="text/javascript" src="recharge-batch2.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请详情</title>
<style>
.font20 {
	font-size: 20px;
	font-weight: bold;
}

.font12 {
	font-size: 12px;
	font-weight: bold;
}
.red_font20 {
	color:red;
	font-size: 20px;
	font-weight: bold;
}
._font12 {
	font-size: 12px;
}
</style>
</head>
<body>
	<div style="position: absolute;z-index: 100;background: white;height: 80px;width: 100%;border-top: 1px solid #999;" id="sumDiv">
	<table style="margin-top: 10px;">
		<tr>
			<td width="140" align="right"><span class="font20">批量充值：</span></td>
			<td width="100"><input type="text" id="batchMoney"/></td>
			<td width="150" align="right"><span class="font20">已选择卡数目：</span></td>
			<td width="50"><span class="red_font20" id="cardCount"></span></td>
			<td width="100" align="right"><span class="font20">充值总额：</span></td>
			<td width="100"><span class="red_font20" id="sumMoney"></span><span class="font20">&nbsp;元</span></td>
		</tr>
	</table>
	</div>
	<div style="width:100%;height:90%;overflow: auto;">
	<table width="90%" border="1" align="center" cellpadding="20"
		cellspacing="1" style="background-color: #efefef;margin-top: 20px;" id="table_1">
		<tr>
			<td width="25%" height="25" bgcolor="#E4E4E4" class="font12"
				style="text-align: center">IC卡号</td>
			<td width="25%" bgcolor="#E4E4E4" style="text-align: center"
				class="font12">车牌号</td>
			<td width="25%" bgcolor="#E4E4E4" style="text-align: center"
				class="font12">充值金额（元）</td>
			<td width="25%" bgcolor="#E4E4E4" style="text-align: center"
				class="font12">操作</td>
		</tr>
	</table>
	<div style="height: 60px;">&nbsp;</div>
	</div>
</body>
</html>
