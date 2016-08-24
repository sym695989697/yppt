<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据抓取账户管理</title>
<%@include file="/pages/all_for_tyrz.jsp"%>
<script src="catchdata-list.js?v=g_version" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<div class="l-loading" style="display: none" id="pageloading"></div>
	<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 0px;">
		<div position="center">
			<div class="conBoxNoBorder">
				<form id="queryForm">
					<div id="queryCurrencyDiv" style="top: 10px; border-top: 20px;">
						<table style="top: 10px;">
							<tr>
								<td width="10%" align="right">用户名：</td>
								<td width="15%" align="left">
										<input type="text"  id="username"  name="username" />
								</td>
									<td width="15%" align="left">
										<span style="float:left"><input id="query1" type="button" onclick="window.catchdataList.search();" value="查询" class="l-button l-button-submit" /></span>
										<span style="float:left;margin-left:10px"><input id="resettt" type="reset" value="重置" class="l-button l-button-submit" /></span>
									</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		<div id="catchdataList" class="grid"></div>
		<div id="selectdate" style="display:none">
			<table cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>开始日期:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="startdate" id="startdate"/>
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>结束日期:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="enddate" id="enddate"/>
					</td>
				</tr>
			</table>
		</div>
			
</body>
</html>