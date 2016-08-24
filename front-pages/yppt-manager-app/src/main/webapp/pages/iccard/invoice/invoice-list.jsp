<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>码表管理</title>
<jsp:include page="../../all_css.jsp"/>
<jsp:include page="../../all_js.jsp"/>
<script src="invoice-list.js?v=g_version" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 4px; overflow: hidden;">
<div>
	<div class="l-loading" style="display: block" id="pageloading"></div>
		<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 0px;">
		<div>
			<div class="conBoxNoBorder">
			<form id="queryCodeForm">
				<div id="queryCurrencyDiv" style="top: 10px; border-top: 20px;">
					<table style="top: 10px;">
						<tr>
							<td width="100" align="left">
								<select name="queryType" id="queryType">
									<option value="userName">会员名称</option>
									<option value="receiverPhoneNum">注册手机号</option>
								</select>
							</td>
							<td width="100" align="left">
								<input type="text" id="queryName" name="queryName"/>
							</td>
								
							<td width="100" align="right">发票类型：</td>
							<td width="100" align="left">
								<input type="text" id="invoiceType" name="invoiceType" />
							</td>
							
							<td width="100" align="right">开票状态：</td>
							<td width="100" align="left">
								<input type="text" id="status" name="status" />
							</td>
							<td width="100" align="right">发票抬头：</td>
							<td width="100" align="left">
								<input name="invoiceName" type="text" id="invoiceName"/>
							</td>
							<td width="100" align="right">申请时间：</td>
							<td >
								<input name="createTime" type="text" style="width: 90px" id="createTime1" />
							</td>
							<td align="left">
								<input name="createTime" type="text" style="width: 90px" id="createTime2" />
							</td>
							
							<td width="100" align="right">
								<span style="float:right">
									<input id="query1" type="button" onclick="window.invoiceList.search();" value="查询" class="l-button l-button-submit" />
								</span>
							</td>
							<td width="100" align="left">
								<span style="float:left;margin-left:10px">
									<input id="resettt" type="reset" onclick="window.invoiceList.reset();" value="重置" class="l-button l-button-submit" />
								</span>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<!-- 表格内容的DIV  -->
		<div id="InvoiceGird" class="grid"></div>
		<div style="display: none;"></div>
	</div>
	</div>
</div>
</body>
</html>
