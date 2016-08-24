<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>副卡管理</title>

<jsp:include page="../../../all_css.jsp" />
<jsp:include page="../../../all_js.jsp" />
<script type="text/javascript" src="../../../../js/datepicker/WdatePicker.js"></script>

<script src="rebate-history-list.js?v=g_version" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 4px; overflow: hidden;">
	<div>
		<div class="l-loading" style="display: block" id="pageloading"></div>
		<div id="layout1"
			style="width: 100%; margin: 0 auto; margin-top: 0px;">
			
			<div position="center">
				<div class="conBoxNoBorder">
					<form id="queryForm">
						<div id="queryCurrencyDiv" style="top: 10px; border-top: 20px;">
							<table style="top: 10px;">
								<tr>
									<td width="100" align="right">注册手机号：</td>
									<td width="100" align="left">
										<input type="text" id="registerMobile" name="registerMobile" />
									</td>
									<td width="100" align="right">会员名称：&nbsp;</td>
									<td width="100" align="left">
										<input type="text" id="ownerName" name="ownerName" />
									</td>
									<td width="100" align="right">账单日期：</td>
									<td width="100" align="left">
											<input type="text" name="dateYearAndMonth"  readonly="readonly" id="dateYearAndMonth" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM'});"/>
									</td>
									<td width="100" align="right">发卡地区：</td>
									<td width="100" align="left">
										<input type="text" id="opencardofficecode" name="opencardofficecode" />
									</td>
									<td width="100" align="right">旺金币结算状态：</td>
									<td width="100" align="left">
										<input type="text" id="status" name="status" />
									</td>
									<td width="100" align="right">
										<span style="float:right"><input id="query1" type="button" onclick="window.rebateHistoryList.search();" value="查询" class="l-button l-button-submit" /></span>
									</td>
									<td width="100" align="left">
										<span style="float:left;margin-left:10px"><input id="resettt" type="button" value="重置" class="l-button l-button-submit" onclick="window.rebateHistoryList.resetForm();"/></span>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
				<!-- 表格内容的DIV  -->
				<div id="rebateHistoryList" class="grid"></div>
				<div style="display: none;"></div>
			</div>
		</div>
</body>
</html>