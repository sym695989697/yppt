<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script type="text/javascript" src="recharge-batch3.js"></script>
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
.red_font12{
	font-size: 12px;
	color:red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div style="position: absolute;z-index: 100;background: white;height: 180px;width: 100%;border-top: 1px solid #999;" id="sumDiv">
	<table style="margin-top: 10px;width: 100%;">
		<tr>
			<td width="150" align="right"><span class="_font12">充值卡数量：</span></td>
			<td width="50"><span class="red_font12" id="cardCount"></span></td>
			<td width="100" align="right"><span class="_font12">充值总额：</span></td>
			<td width="100"><span class="red_font12" id="sumMoney"></span><span class="_font12">&nbsp;元</span></td>
			<td width="120" align="right">
<!-- 				<input type="checkbox" id="isUseCurrency"/><span class="_font12">使用旺金币数量：</span> -->
			</td>
			<td width="50">
<!-- 				<input id="useCurrencyCount" type="text" size="8"/> -->
			</td>
			<td>
<!-- 				<span class="_font12">&nbsp;个(1个旺金币=0.01元)</span> -->
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<span class="font20" style="margin-left: 10px;">申请信息</span>
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<table width="90%"  align="center" cellpadding="20" cellspacing="1" style="background-color: #efefef;margin-top: 5px;" id="table_2">
					<tr>
						<td width="20%" height="40"  align="right">注册手机号：</td>
						<td width="30%" height="40" align="left"><span id="regUserPhone"></span></td>
						<td width="20%" height="40" align="right">会员名称：</td>
						<td width="30%" height="40" align="left"><span id="memberName"></span></td>
					</tr>
					<tr>
						<td width="20%" height="40" align="right">申请渠道：</td>
						<td width="30%" height="40" align="left"><span id="applyDataSource"></span></td>
						<td width="20%" height="40" align="right">申请人：</td>
						<td width="30%" height="40" align="left"><span id="applyPersonName"></span></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
	<div style="width:100%;height:230px;overflow: auto;">
	<span class="font20" style="margin-left: 10px;">卡信息</span>
	<table width="90%" border="1" align="center" cellpadding="20"
		cellspacing="1" style="background-color: #efefef;margin-top: 5px;" id="table_1">
		<tr>
			<td width="25%" height="25" bgcolor="#E4E4E4" class="font12"
				style="text-align: center">IC卡号</td>
			<td width="25%" bgcolor="#E4E4E4" style="text-align: center"
				class="font12">车牌号</td>
			<td width="25%" bgcolor="#E4E4E4" style="text-align: center"
				class="font12">申请充值金额（元）</td>
		</tr>
	</table>
	<div style="height: 60px;">&nbsp;</div>
	</div>
</body>
</html>
