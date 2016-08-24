<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>副卡信息同步管理--车辆</title>
<%@include  file="../../all_for_tyrz.jsp"%>
<script src="sync-vehiclelist.js" type="text/javascript" charset="utf-8"></script>
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
								<td width="10%" align="right">车辆ID:</td>
								<td width="10%"><input type="text" name="vehicleId" id="vehicleId" style="width: 150px"/>
							    </td>
								<td width="10%" align="right">车牌号：</td>
								<td ><input name="vehicleNo" type="text" style="width: 100px" id="vehicleNo" /></td>
								<td align="left">
								</td>
								<td width="10%" align="right">车牌颜色：</td>
								<td width="10%">
								<select id="vehicleColor" name="vehicleColor" >
								
									<option value="">请选择</option>
									<option value="0">蓝色</option>
									<option value="1">黄色</option>
									<option value="2">黑色</option>
									<option value="3">白色</option>
								</select>
								<!-- <input id="vehicleColor" name="vehicleColor" type="text" style="width: 100px" /> --></td>
							
							<td width="10%" align="right">车辆行驶证：</td>
								<td width="10%"><input id="vehicleLicense" name="vehicleLicense" type="text" style="width: 100px" /></td>
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
