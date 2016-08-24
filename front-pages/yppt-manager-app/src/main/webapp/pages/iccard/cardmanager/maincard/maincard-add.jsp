<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>主卡新增</title>
<jsp:include page="../../../all_css.jsp"/>
<jsp:include page="../../../all_js.jsp"/>
<script type="text/javascript" src="maincard-add.js?v=g_version"></script> 
<script type="text/javascript" src="<%=application.getContextPath()%>/js/DateUtil.js"></script>
</head>
<body style="height: 90%; padding: 20px;">
	<div id="tableDiv" style="overflow: auto; height: 400px;">
	<form name="form1" method="post" id="form1">
		<input type="hidden" id="id" name="id" /> 
		<input type="hidden" id="opencardofficecode" name="opencardofficecode" /> 
		<input type="hidden" id="cardAreaCode" name="cardAreaCode" /> 
		<input type="hidden" id="cardAreaName" name="cardAreaName" /> 
			<table cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>卡号:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="cardNumber" id="cardNumber"/>
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>对应账户:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="accountId" id="accountId"/>
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>发卡类型:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="cardType" id="cardType" />
					</td>
				</tr>
				
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>发卡地区:</td>
					<td align="left" class="l-table-edit-td">
						<input name="opencardofficename" type="text" id="opencardofficename" ltype="combobox"/>
					</td>
				</tr>
				
				<!-- 
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>发卡区域:</td>
					<td align="left" class="l-table-edit-td">
						<input name="openCardArea" type="text" id="openCardArea" ltype="text"/>
					</td>
				</tr>
				 -->
				 
                <tr>
                    <td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>所属组织:</td>
                    <td align="left" class="l-table-edit-td">
                        <input type="text" name="ownOrg" id="ownOrg" />
                    </td>
                </tr>
			</table>
		</form>
	</div>
	<div style="display: none"></div>
</body>
</html>