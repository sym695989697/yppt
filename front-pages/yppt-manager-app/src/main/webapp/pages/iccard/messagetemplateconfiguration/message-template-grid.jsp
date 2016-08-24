<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>短信模板配置</title>

<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />

<script src="message-template-grid.js" type="text/javascript" charset="utf-8"></script>
</head>
<%
	String root = request.getContextPath();
%>
<body style="padding: 4px;overflow: auto">
	<div style="width: 1300px; border: 1px solid #666; margin: auto auto">
		<div class="div_title">
			<span class="span_title">短信模板配置</span>
		</div>
		<div style="margin-top: 20px" align="center">
			<label>短信签名：</label>
			<input type="text" id="sign" name="sign"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="sign_button" name="sign_button" onclick="javascript:modifySign()" value="修改"/>
		</div>
		<div id="div_step" style="margin-top: 20px;margin-bottom: 20px">
			<table id="table_template" align="center">
				<tr>
					<td align="center" height="30" width="200" bgcolor="#D4D4D4">模板编码</td>
					<td align="center" width="200" bgcolor="#D4D4D4">模板名称</td>
					<td align="center" width="600" bgcolor="#D4D4D4">内容（参数标识：<_>，分短信拆分标识：>_<）</td>
					<td align="center" width="50" bgcolor="#D4D4D4">状态</td>
					<td align="center" width="200" bgcolor="#D4D4D4">操作</td>
				</tr>
				<tr>
					<td align="center" height="40"><input id="templateCode" name="templateCode" type="text"></input></td>
					<td align="center"><input id="templateName" name="templateName" type="text"></input></td>
					<td align="center"><input id="content" name="content" type="text" size="100"></input></td>
					<td></td>
					<td align="center"><a href="javascript:add()">添加</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>