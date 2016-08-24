<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>IC卡主卡交易记录</title>
<%@include  file="/pages/all_for_tyrz.jsp"%>
<script src="<%=root %>/pages/trade/trade-list.js?v=g_version" type="text/javascript" charset="utf-8"></script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="pageloading"></div>
	<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 4px;">
		<div position="center">
			<div id="accordion1" class="conBoxNoBorder">
				<form id="queryCodeForm">
					<div id="queryCodeDiv" style="top: 10px; border-top: 20px;">
						<table style="top: 5px;">
							<tr>
								<td width="10%" align="right">卡号：</td>
								<td width="10%" colspan="2"><input id="cardNum" name="cardNum" type="text" style="width: 250px;" />
							    </td>
								<td width="10%" align="right">交易类型：</td>
								<td width="10%"><input name="tradeType" type="text" id="tradeType" /></td>
								<td width="10%" align="right">发卡机构：</td>
								<td width="10%"><input name="merchantCode" type="text"
									id="merchantCode" />
									
								</td>
								<td width="10%" align="right"><input id="query1"
									type="button" onclick="window.tradeList.search();"
									value="查询" class="l-button l-button-submit" /></td>
								
								<td width="10%"></td>
							</tr>
							
								<tr>
								<td width="10%" align="right">交易时间：</td>
								<td align="left">
								<input type="text" style="width: 80px" id="tradeTime1"/>
								</td>
								<td align="left">
								<input type="text" style="width: 80px" id="tradeTime2"/>
							    </td>
								<td width="10%" align="right">所属组织：</td>
								<td width="10%"><input name="org" type="text" id="org" /></td>
								<td width="10%" align="right"></td>
								<td width="10%" align="right"></td>
								<td width="10%" align="left"><input id="resettt"
									type="button" onclick="window.tradeList.reset();"
									value="重置" class="l-button l-button-submit" /></td>
								<td width="10%"></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<!-- 表格内容  -->
			<div id="tradeGrid" class="grid"></div>
			 <iframe id="loadExcel"  style="display: none;"></iframe>
		</div>
	</div>
</body>
</html>
