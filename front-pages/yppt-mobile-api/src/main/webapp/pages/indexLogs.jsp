<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	String root = request.getContextPath();
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>主页面</title>
    <link href="../scripts/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="../css/index.css" rel="stylesheet" type="text/css" />
</head>
<body style="padding:10px"> 
	<div id="layout">
    	<div position="left">
    		<ul id="tree" style="margin-top:3px;"></ul>
    	</div>
    	<div position="center" title=" ">
    		<form action="">
	    		<div id="query" style="padding:20px;">
	    			<table border="0">
	    				<tr>
	    					<td width="60" height="20">
	    						服务编码：
	    					</td>
	    					<td width="100">
	    						<input type="text" id="servCode" name="servCode" size="30"></input>
	    					</td>
	    					<td width="60" align="center">
	    						状态：
	    					</td>
	    					<td width="100" align="center">
	    						<select id="status">
	    							<option value="-1">--请选择--</option>
	    							<option value="success">成功</option>
	    							<option value="failed">失败</option>
	    						</select>
	    					</td>
	    					<td width="60" align="center">
	    						<input type="button" class="l-button" value="查询" style="width: 50px" onclick="indexLog.query()"></input>
	    					</td>
	    					<td width="60" align="center">
	    						<input id="reset" type="reset" class="l-button" value="重置" style="width: 50px"></input>
	    					</td>
	    					
	    				</tr>
	    			</table>
	    		</div>
	    		
	    	</form>
    		<div id="grid"></div>
        </div>
    </div>
    <script type="text/javascript">
    	var root = "<%=root%>";
    </script>
    
    <script src="../scripts/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
	<script src="../scripts/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <script src="../scripts/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../scripts/webapi/jsonUtil.js" type="text/javascript"></script>
    <script src="../scripts/webapi/index.js" type="text/javascript"></script>
</body>
</html>
