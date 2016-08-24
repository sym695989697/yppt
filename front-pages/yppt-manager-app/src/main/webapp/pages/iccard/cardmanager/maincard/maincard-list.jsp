<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>主卡管理</title>

<jsp:include page="../../../all_css.jsp"/>
<jsp:include page="../../../all_js.jsp"/>

<script src="maincard-list.js?v=g_version" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
.l-selected .l-grid-row-cell,.l-selected{
	color:white;
    background:#0066CC;
}
</style>
</head>
<body style="padding: 4px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="layout1" style="width: 100%; margin: 0 auto; margin-top: 0px;">
		<div>
			<!--1. 查询条件区 -->
			<div id="accordion1" class="conBoxNoBorder">
				<form id="queryMainCardForm">
                    <input type="hidden" id="opencardofficecode" name="opencardofficecode" /> 
					<table style="top:2px">
						<tr>
							<td width="100" align="right">卡号：</td>
							<td width="100"><input id="cardNumber_query" name="cardNumber"  /></td>
							
							<!-- 
							<td width="10%" align="right">发卡地区：</td>
							<td width="10%"><input id="openCardArea_query" name="openCardArea" /></td>
							 -->
							 
		                    <td width="100" align="right">发卡类型：</td>
		                    <td width="100">
                                   <input type="text" name="cardType_query" id="cardType_query" />
		                    </td>
		                    
		                    <td width="100" align="right">发卡地区：</td>
		                    <td width="100">
		                        <input name="opencardofficecode_query" type="text" id="opencardofficecode_query" />
		                    </td>
							
							<td width="100" align="right">所属组织：</td>
							<td width="100"><input id="ownOrg_query" name="ownOrg"  /></td>
							
							<td width="100" align="right"><input id="query1"
								type="button" onclick="window.iCCardMain.search();"
								value="查询" class="l-button l-button-submit" /></td>
							<td width="100" align="left"><input id="resettt"
								type="button" onclick="window.iCCardMain.reset();"
								value="重置" class="l-button l-button-submit" /></td>
							<td width="100"></td>
						</tr>
					</table>
				</form>
			</div>
			<!--2. 列表页面 -->
			<div id="iCCardMainGird" class="grid"></div>
		</div>
	</div>
</body>
</html>
