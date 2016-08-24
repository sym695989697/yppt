<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>卡申请管理</title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script src="apply-list.js" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="pageloading"></div>
	<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 4px;">
		<div>
			<div id="accordion1" class="conBoxNoBorder">
				<form id="queryForm">
					<div id="queryCodeDiv" style="top: 10px; border-top: 20px;">
						<table style="top: 5px;" border="0px">
							<tr>
								<td width="10%" align="right">卡类型：</td>
								<td width="10%"><input type="text" name="cardType" id="cardType"/>
							    </td>
								<td width="10%" align="right">申请时间：</td>
								<td ><input name="createTime" type="text" style="width: 90px" id="createTimeStart" /></td>
								<td align="left"><input name="createTime" type="text" style="width: 90px"
									id="createTimeEnd" />
								</td>
								<td width="10%" align="right">申请类型：</td>
								<td width="10%"><input id="applyType" name="applyType" type="text" /></td>
							
							<td width="10%" align="right">审核状态：</td>
								<td width="10%"><input id="status" name="status" type="text" /></td>
							
								<td width="10%" align="right">
								<input id="query1" type="button" onclick="window.applyList.search();" value="查询" class="l-button l-button-submit" /></td>
								<td width="10%" align="left"><input id="resettt" type="button" onclick="window.applyList.reset();" value="重置" class="l-button l-button-submit" /></td>
								<td width="10%"></td>
								<td width="10%"></td>
							</tr>
							
								<tr>
								<td width="10%" align="right">发卡地区：</td>
								<td align="left">
								<input id="openCardOrgCode" name="openCardOrgCode" type="text" />
								</td>
								
								<td width="10%" align="right">注册手机号：</td>
								<td width="10%" colspan="2">
									<input name="userRegPhone1" style="width: 200px" type="text" id="userRegPhone1" />
								</td>
								<td width="10%" align="right">会员名：</td>
								<td width="10%">
									<input name="userName" type="text" id="userName" />
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 表格内容  -->
			<div id="applyGrid" class="grid"></div>
		</div>
	</div>
</body>
</html>
