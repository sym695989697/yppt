<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>石化副卡对账单</title>
<%@include  file="/pages/all_for_tyrz.jsp"%>
<script src="<%=root %>/pages/iccard/reconciliation/reconciliation-sh-list.js?v=g_version" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="pageloading"></div>
	<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 4px;">
		<div position="center">
			<div id="accordion1" class="conBoxNoBorder">
				<form id="queryForm">
					<div id="queryCodeDiv" style="top: 10px; border-top: 20px;">
						<table style="top: 5px;">
							<tr>
								<td width="10%" align="right">主卡卡号：</td>
								<td width="10%" colspan="2"><input id="mainCardNum" name="mainCardNum" type="text" style="width: 250px" />
							    </td>
								<td width="10%" align="right">交易类型：</td>
								<td width="10%"><input name="tradeType" type="text" id="tradeType" /></td>
								
								<td width="10%" align="center">注册手机号：</td>
								<td width="10%"><input name="userRegPhone" type="text" id="userRegPhone" /></td>
							</tr>
								<tr>
								<td width="10%" align="right">交易时间：</td>
								<td align="left">
								<input type="text" style="width: 80px" id="tradeTime1"/>
								</td>
								<td align="left">
								<input type="text" style="width: 80px" id="tradeTime2"/>
							    </td>
								<td width="10%" align="right">会员名称：</td>
								<td width="10%"><input name="userName" type="text" id="userName" /></td>
								<td width="10%" align="right"><input id="searchList" type="button"
									value="查询" class="l-button l-button-submit" /></td>
								<td width="10%" align="left"><input id="resettt" type="button"
									value="重置" class="l-button l-button-submit" /></td>
								<td width="10%"></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 表格内容  -->
			<div id="reconciliationList" class="grid"></div>
			 <iframe id="loadExcel"  style="display: none;"></iframe>
		</div>
	</div>
</body>
</html>
