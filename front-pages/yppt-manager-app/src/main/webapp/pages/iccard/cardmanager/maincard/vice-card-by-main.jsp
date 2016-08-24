<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>副卡信息列表</title>

<%@include  file="/pages/all_for_tyrz.jsp"%>

<script src="vice-card-by-main.js?v=g_version" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 4px; overflow: hidden;">
	<div>
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 0px;">
			<div>
				<div class="conBoxNoBorder">
					<form id="queryForm">
						<div id="queryCurrencyDiv" style="top: 10px; border-top: 20px;">
							<table style="top: 10px;">
								<tr>
									<!-- 表格内容的DIV  -->
									<td width="100" align="right">卡号：</td>
									<td width="100" align="left"> 
										<input type="text" id="cardNumber_main" name="cardNumber" />
									</td>
									<td width="100" align="right">注册手机号：</td>
									<td width="100" align="left">
										<input type="text" id="userRegPhone_main" name="userRegPhone" />
									</td>
									<td width="100" align="right">会员名称：</td> <!--暂无对应字段  -->
									<td width="100" align="left"> 
										<input type="text" id="memberName_main" name="memberName" />
									</td>
									<td width="100" align="right">车牌号：</td>
									<td width="100" align="left"> 
										<input type="text" id="vehicleNo_main" name="vehicleNo" />
									</td>
									
									<td width="100" align="right">
										<span style="float:right"><input id="query1" type="button" onclick="window.viceCardByMain.search();" value="查询" class="l-button l-button-submit" /></span>
									</td>
									<td width="100" align="left">
										<span style="float:left;margin-left:10px"><input id="resettt" type="reset" value="重置" class="l-button l-button-submit" /></span>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
				<!-- 表格内容的DIV  -->
				<div id="viceCardByMainGrid" class="grid"></div>
				<div style="display: none;"></div>
			</div>
		</div>
	</div>
</body>
</html>