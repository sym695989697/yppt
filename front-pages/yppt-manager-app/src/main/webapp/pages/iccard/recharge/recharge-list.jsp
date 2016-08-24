<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>卡申请管理</title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script src="recharge-list.js" type="text/javascript" charset="utf-8"></script>
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
								<td width="120" align="right">注册手机号：</td>
								<td width="120"><input style="width:150px;height:18px" type="text" name="userRegPhone" id="userRegPhone"/>
							    </td>
								<td width="120" align="right">申请渠道：</td>
								<td  width="120"><input name="dataSource" type="text" id="dataSource" /></td>
							</tr>
								<tr>
								<td width="120" align="right">充值状态：</td>
								<td align="left" width="100">
								<input id="state" name="state" type="text" />
								</td>
								<td width="120" align="right">申请时间：</td>
								<td  width="240" >
									<table>
										<tr>
											<td><input name="createTime" type="text" style="width: 90px" id="createTimeStart" /></td>
											<td>&nbsp;&nbsp;&nbsp;</td>
											<td><input name="createTime" type="text" style="width: 90px" id="createTimeEnd" /></td>
										</tr>
									</table>
								</td>
								<td width="120" align="right" colspan="2">
								<input id="query1" type="button" onclick="window.rechargeList.search();" value="查询" class="l-button l-button-submit" /></td>
								<td width="120" align="right" colspan="2">
								<input id="resettt" type="button" onclick="window.rechargeList.reset();" value="重置" class="l-button l-button-submit" /></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 表格内容  -->
			<div id="rechargeGrid" class="grid"></div>
		</div>
	</div>
</body>
</html>
