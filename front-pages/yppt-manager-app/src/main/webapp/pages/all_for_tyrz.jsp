<%@page import="com.ctfo.common.models.Index"%>
<%@page import="com.ctfo.common.utils.Converter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String root = null; %>
<%
	root = request.getContextPath();
	Index index = (Index)session.getAttribute(Converter.SESSION_INDEX);
	if(index == null){
		response.sendRedirect(root+"/login/login.action?systemSgin=com.ctfo.chpt.iccard.managerApp");
		return;
	}
%>
<head>
    <%-- <title><%=index.getSystemName()%></title> --%>
    <!-- 导航菜单的 css js -->
    <%-- <%@include  file="/pages/all_css.jsp"%> --%>
   	<jsp:include page="/pages/all_css.jsp" />
    <script src="<%=root%>/js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>      
	<script src="<%=root%>/js/ligerUI/ligerui.min.js?v=g_version" type="text/javascript"></script>
	<script src="<%=root%>/js/jquery/jquery.form.js?v=g_version" type="text/javascript"></script>
    <script src="<%=root%>/js/csm/csm-all.js?v=g_version" type="text/javascript"></script>
    <script src="<%=root%>/js/util_ajax.js?v=g_version" type="text/javascript"></script>
	<script src="<%=root%>/js/DateUtil.js?v=g_version" type="text/javascript"></script>
	<script src="<%=root%>/js/csm/csm_validate.js" type="text/javascript"></script>
	<script src="<%=root%>/js/csm/csm_validate.js" type="text/javascript"></script>
    <script type="text/javascript">
		var root = "<%=root%>";
		<% if(index != null) {%>
		//系统信息
		var INDEX_SYS_ID="<%=index.getSystemId()%>";
		var INDEX_SYS_SIGN="<%=index.getSystemSign()%>";
		var INDEX_SYS_NAME="<%=index.getSystemName()%>";
		var INDEX_SYS_PLATFORM="<%=index.getPlatform()%>";
		var INDEX_SYS_PLATFORMNAME="<%=index.getPlatformName()%>";
		//用户信息
		var INDEX_USER_ID="<%=index.getUserId()%>";
		var INDEX_USER_LOGIN="<%=index.getUserLogin()%>";
		var INDEX_USER_NAME="<%=index.getUserName()%>";
		var INDEX_USER_TYPE="<%=index.getUserType()%>";
		var INDEX_USER_OWNINGORG="<%=index.getUserOwningOrgId()%>";
		
		var INDEX_USER_ORGID="<%=index.getOrgId()%>";
		var INDEX_USER_ORGNAME="<%=index.getOrgName()%>";
		
		var INDEX_USER_ROLEID="<%=index.getRoleId()%>";
		var INDEX_USER_ROLENAME="<%=index.getRoleName()%>";
		
		<% }%>
		
		
		var failShowTime = 4000;
		var subWidth=Math.round($(window).width() - ($(window).width() * 0.25));
		var subHeight=Math.round($(window).height() - ($(window).height() * 0.20));
		
		
    </script>

</head>