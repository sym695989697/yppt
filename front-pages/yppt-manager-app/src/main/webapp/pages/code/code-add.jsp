<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编码信息新增页面</title>
<jsp:include page="../all_css.jsp" />
<jsp:include page="../all_js.jsp" />
<script type="text/javascript" src="code-add.js?v=g_version"></script> 
</head>
<body style="height: 90%; padding: 20px;">
	<form name="form1" method="post" action="" id="form1">
		<input type="hidden" id="id" name="id" /> 
		<div id="tableDiv" style="overflow: auto; height: 350px;">
			<table cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>名称:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="name" id="name" ltype="text" validate="{required:true,minlength:1,maxlength:30}" />
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>编码:</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" name="code" id="code" ltype="text" validate="{required:true,minlength:1,maxlength:20}" />
					</td>
				</tr>				
			 	<tr>
					<td align="right" class="l-table-edit-td">类型:</td>
					<td align="left" class="l-table-edit-td">
						<input type="hidden" name="typeCode" id="typeCode"/>
						<input type="hidden" name="typeName" id="typeName"/>
						<input type="hidden" name="pid" id="pid"/>
						<span id="typeNameText"></span>
					</td>
				</tr> 
				<tr>
					<td align="right" class="l-table-edit-td" style="width: 15%;">描述:</td>
					<td align="left" class="l-table-edit-td" colspan="1">
						<textarea name="description" id="description" rows="5" cols="40" validate="{required:false,maxlength:100}"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="button" value="保存" id="subBtn" onclick="javascript:window.codeAdd.formSubmit();" class="l-button l-button-submit" />
						<input type="button" value="重置" class="l-button l-button-reset" onclick="window.codeAdd.reset();" />
					</td>
				</tr>
			</table>
		</div>
	</form>
	<div style="display: none"></div>
</body>

</html>