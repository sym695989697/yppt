<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script src="reconciliation-sh-import.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
</style>

</head>
<body style="padding: 10px;">
	<form action="" name="f_form" id="f_form" enctype="multipart/form-data"
		method="post">
		<div id="editForm1" align="center" style="margin-top: 20px">
				<table border="0">
					<tr>
						<td align="right" valign="top"> 选择导入文件：</td>
						<td align="left"  valign="top">
							<table border="0">
								<tr>
									<td valign="top"><input type="file" name="uploadFile" id="uploadFile"/></td>
									<td width="100"><a href="#" id="downLoadModel">模版下载</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
	</form>
</body>


</html>
