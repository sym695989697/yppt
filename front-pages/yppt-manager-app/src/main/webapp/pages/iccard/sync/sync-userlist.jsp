<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>副卡信息同步管理--用户</title>
<%@include  file="../../all_for_tyrz.jsp"%>
<script src="sync-userlist.js" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="pageloading"></div>
	<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 4px;">
		<div>
			<div id="accordion1" class="conBoxNoBorder">
				<form id="queryCodeForm">
					<div id="queryCodeDiv" style="top: 10px; border-top: 20px;">
						<table style="top: 5px;" border="0px">
							<tr>
								<td width="10%" align="right">用户中心ID:</td>
								<td width="10%"><input type="text" name="userId" id="userId" style="width: 150px"/>
							    </td>
								<td width="10%" align="right">用户类型：</td>
								<td >
								<select id="userType" name="userType" >
									<option value="">请选择</option>
									<option value="1">企业用户</option>
									<option value="2">个人用户</option>
								</select>
								<!-- <input name="userType" type="text" style="width: 100px" id="userType" /> --></td>
								<td align="left">
								</td>
								<td width="10%" align="right">用户名称：</td>
								<td width="10%"><input id="userName" name="userName" type="text" style="width: 100px" /></td>
							
							<td width="10%" align="right">手机号：</td>
								<td width="10%"><input id="userRegPhone" name="userRegPhone" type="text" style="width: 100px" /></td>
								<td width="10%" align="right">
								<input id="query1" type="button" onclick="window.SyncList.search();" value="查询" class="l-button l-button-submit" /></td>
								<td width="10%" align="left"><input id="resettt" type="button" onclick="window.SyncList.reset();" value="重置" class="l-button l-button-submit" /></td>
								<td width="10%"></td>
								<td width="10%"></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 表格内容  -->
			<div id="syncGrid" class="grid"></div>
		</div>
	</div>
</body>
</html>
