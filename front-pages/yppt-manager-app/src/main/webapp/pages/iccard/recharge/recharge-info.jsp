<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请详情</title>
<%@include file="../../all_for_tyrz.jsp"%>
<script src ="recharge-info.js" type="text/javascript" charset="utf-8"></script>
<style>
.font20{
	font-size:15px;
	font-weight:bold;
}
.font12{
	font-size:12px;
	font-weight:bold;
}
._font12{
	font-size:12px;
}
</style>
</head>

<body>
<table width="90%" border="0" align="center" cellpadding="20" cellspacing="1" style="background-color:#efefef">
  <tr>
    <td bgcolor="#FFFFFF">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	         <tr>
	           <td>
				<span class="font20">卡信息</span>
		        <hr size="1" style="height:1px"/>
	           </td>
	         </tr>
             <tr>
               <td valign="top">
               <table width="100%" border="0" cellspacing="1" cellpadding="0"  style="background-color: #CCC; text-align: center;" >
                 <tr>
                 <td><div id="cardInfoGrid" ></div></td>
                 </tr>
                </table>
                 <table width="50%" border="0" cellspacing="1" cellpadding="5" style="background-color:#F2F2F2">
                   <tr>
                     <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">充值卡数量：<span class="_font12" id="cardNum"></span></td>
                     <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">申请充值总额：<span class="_font12" id="totalMoney"></span></td>
                   </tr>
                   <tr>
                     <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">旺金币抵扣：<span class="_font12" id="creditNum"></span></td>
                     <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">实际分配总额：<span class="_font12" id="actualDivMoney"></span></td>
                   </tr>
                 </table>
                 <br />
               </td>
        </tr>
             <tr>
               <td><span class="font20">申请信息</span>
                 <hr size="1" style="height:1px"/></td>
             </tr>
             <tr>
               <td><table width="50%" border="0" cellspacing="1" cellpadding="5" style="background-color:#F2F2F2">
                 <tr>
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">注册手机号：<span class="_font12" id="userRegPhone"></span></td>
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">会员名称：<span class="_font12" id="userName"></span></td>
                 </tr>
                 <tr>
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">申请渠道：<span class="_font12" id="dataSource"></span></td>
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left"><span class="_font12"></span></td>
                 </tr>
                 <tr>
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">申请人：<span class="_font12" id="applyPersonName"></span></td>
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">&nbsp;</td>
                 </tr>
               </table>
               <br /></td>
             </tr>
             <tr>
               <td><span class="font20">分配凭证</span>
                 <hr size="1" style="height:1px"/></td>
             </tr>
             <tr>
               <td><table width="50%" border="0" cellspacing="1" cellpadding="5" style="background-color:#F2F2F2">
                 <tr>
                   <td width="68%" valign="middle" bgcolor="#FFFFFF" style="text-align: left">
<!--                    	<div id="showBigImg" onclick="javascript:$(this).hide();" style="position:absolute;display: none; cursor: pointer; width:700px;height: 500px;z-index: 1000;" title="点击关闭大图"></div>
                   	<img id ="certFile" src="" width="50" height="50" onclick="javascript:rechargeInfo.showBigImg(this)" title="点击查看大图" style="cursor: pointer;"/> -->
                   		<a id="certFile">凭证下载</a>
                   		<label id="voucher"></label>
                   </td>
                 </tr>
               </table></td>
             </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
