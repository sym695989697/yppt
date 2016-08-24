<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申请详情</title>
<%@include file="../../all_for_tyrz.jsp"%>
<script src ="recharge-handle.js" type="text/javascript" charset="utf-8"></script>
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
<div style="width:100%;height:100%;overflow: auto;">
<form id="myForm" action="" method="post" enctype="multipart/form-data">
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
                   <td valign="middle" bgcolor="#FFFFFF" style="text-align: left">申请人：<span class="_font12" id="applyPersonName"></span></td>
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
                   <td width="32%" valign="middle" bgcolor="#FFFFFF" style="text-align: left"><span class="_font12">副卡分配凭证附件</span></td>
                   <td width="68%" valign="middle" bgcolor="#FFFFFF" style="text-align: left">
                   		<input type="hidden" id="applyId" name ="applyId"/>
                   		<input type="file" id="certFile" name ="certFile"/>
                   </td>
                 </tr>
               </table></td>
             </tr>
    </table></td>
  </tr>
  <tr><td style="padding-left:200px;height:50px;"><input id="apply" type="button" value="提交" class="l-button l-button-submit"/></td></tr>
</table>
</form>
</div>
</body>
</html>
