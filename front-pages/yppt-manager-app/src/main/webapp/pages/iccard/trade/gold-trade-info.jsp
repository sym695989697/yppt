<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>旺金币交易详情</title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<style>
._font12{
	font-size: 12px;
	font-weight: bold;
	text-align: center;
}
.font12{
	font-size: 12px;
}
 
</style>

<script src="gold-trade-info.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<br/> 
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="_font12">
	<tr>
		<td width="150px">注册手机号:<span id="juserRegPhone"></span>  </td>
		<td width="150px">会员名称:<span id="juserName"></span></td>
	</tr>
</table>
<br/> 
<br/> 
<div id="maingrid"></div>
<br/> 
<br/> 
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="25%" class="_font12">旺金币抵扣：<span id="creditMoney"></span>元</td>
    <td width="25%" class="_font12"><span id="payWay"></span>：<span id="captialMoney"></span>元</td>
    <td width="25%" class="_font12"> 充值总额：<span id="totalMoney"></span>元</td>
  </tr>
</table>

</body>

</html>
