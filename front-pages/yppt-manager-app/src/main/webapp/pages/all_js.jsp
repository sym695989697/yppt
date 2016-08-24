<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% 
String root = request.getContextPath();
%>
<script src="<%=root%>/js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>

<script src="<%=root%>/js/jquery/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="<%=root%>/js/jquery/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="<%=root%>/js/jquery/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="<%=root%>/js/csm/csm_validate.js" type="text/javascript"></script>

<script src="<%=root%>/js/jquery/jquery.form.js" type="text/javascript"></script>
 
<script src="<%=root%>/js/ligerUI/ligerui.min.js" type="text/javascript"></script> 
<script src="<%=root%>/js/ligerUI/ligerui.all.js" type="text/javascript"></script> 
<script src="<%=root%>/js/csm/areaData.js?v=g_version" type="text/javascript" charset="utf-8"></script>
<script src="<%=root%>/js/ligerUI/ligerGrid.showFilter.csm.js?v=g_version" type="text/javascript"></script> 

<script src="<%=root%>/js/util_ajax.js?v=g_version" type="text/javascript"></script>
<script src="<%=root%>/js/csm/csm-all.js?v=g_version" type="text/javascript"></script>
<script src="<%=root%>/js/DateUtil.js?v=g_version" type="text/javascript"></script>
<script src="<%=root%>/js/component.js?v=g_version" type="text/javascript"></script>
 
<script type="text/javascript">
	var root = "<%=root%>";
	var failShowTime = 4000;
	var subWidth=Math.round($(window).width() - ($(window).width() * 0.25));
	var subHeight=Math.round($(window).height() - ($(window).height() * 0.20));
</script>
