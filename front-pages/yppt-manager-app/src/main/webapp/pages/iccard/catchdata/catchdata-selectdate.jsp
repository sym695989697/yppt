<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/pages/all_css.jsp" />
<jsp:include page="/pages/all_js.jsp" />
<script type="text/javascript" src="../../../js/datepicker/WdatePicker.js"></script>
</head>
<body>
	<table cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
			<tr>
				<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>开始日期:</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" name="startdate" id="startdate"  readonly  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>结束日期:</td>
				<td align="left" class="l-table-edit-td">
					<input type="text" name="enddate" id="enddate" readonly  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
		</table>
</body>
</html>