<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>发卡机构账户列表</title>
<jsp:include page="../../../all_css.jsp"/>
<jsp:include page="../../../all_js.jsp"/>
<script src="opencardoffice-list.js?v=g_version" type="text/javascript" charset="utf-8"></script>
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
				<form id="queryCommonUserInfoForm">
					<table style="top:2px">
						<tr>
								<td width="100" align="right">名称：</td>
								<td width="100" align="left" class="l-table-edit-td"><input name="opencardofficename" type="text" id="opencardofficename_query_param" /></td>
								<td width="100" align="right"><input id="query1"
									type="button" onclick="window.opencardofficeList.search();"
									value="查询" class="l-button l-button-submit" /></td>
								<td width="100" align="left"><input id="resettt"
									type="button" onclick="window.opencardofficeList.reset();"
									value="重置" class="l-button l-button-submit" /></td>
								<td width="100"><input id ="cardTypeCode" type="hidden" value="${ param.cardTypeCode}"/></td>
						</tr>
					</table>
				</form>
			</div>
			<!--2. 列表页面 -->
			<!-- 表格内容  -->
			<div id="opencardofficeGrid" class="grid"></div>
			<div style="display: none;"></div>
		</div>
	</div>
</body>
</html>
