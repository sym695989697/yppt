<%@page import="com.ctfo.common.utils.Converter"%>
<%@page import="com.ctfo.common.models.Index"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	String root = request.getContextPath();
	Index index = (Index)session.getAttribute(Converter.SESSION_INDEX);
	if(index == null){
		response.sendRedirect(root+"/login/login.action?systemSgin=com.ctfo.chpt.iccard.managerApp");
		return;
	}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
    <title><%=index.getSystemName()%></title>
   
     <link rel="shortcut icon" href='/images/favicon.ico' type="image/x-icon" />
     <link rel="Bookmark" href='/images/favicon.ico' type="image/x-icon" />
     <link rel="icon" href='/images/favicon.ico' type="image/x-icon" />
    <!-- 导航菜单的 css js -->
    <link href="<%=root%>/css/wijmoUI/rocket/jquery-wijmo.css?v=g_version" rel="stylesheet" type="text/css" /> 
    <link href="<%=root%>/css/wijmoUI/wijmo/jquery.wijmo.wijmenu.css?v=g_version" rel="stylesheet" type="text/css" />
   	<jsp:include page="all_css.jsp" />
    <script src="<%=root%>/js/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>      
      
	<script src="<%=root%>/js/ligerUI/ligerui.min.js?v=g_version" type="text/javascript"></script>
	<script src="<%=root%>/js/jquery/jquery.form.js?v=g_version" type="text/javascript"></script>
	   
    <script src="<%=root%>/js/jquery/wijmoUI/jquery-ui-1.8.18.custom.min.js?v=g_version" type="text/javascript"></script>
    <script src="<%=root%>/js/jquery/wijmoUI/jquery.wijmo.wijmenu.js?v=g_version" type="text/javascript"></script>
	
    <script src="<%=root%>/js/csm/csm-all.js?v=g_version" type="text/javascript"></script>
    <script src="<%=root%>/js/util_ajax.js?v=g_version" type="text/javascript"></script>
    
    <script src="<%=root%>/pages/index.js?v=g_version" type="text/javascript"></script>
    
    <script src="<%=root%>/js/csm/areaData.js?v=g_version" type="text/javascript" charset="utf-8"></script>
    <script src="<%=root%>/js/csm/codeData.js?v=g_version" type="text/javascript" charset="utf-8"></script>
    
    <script type="text/javascript">
    
	     var root = "<%=root%>";
	     
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
	     $(document).ready(function() {
	    	 $("#topOrgName").text(INDEX_USER_ORGNAME);
	    	 $("#toRoleName").text(INDEX_USER_ROLENAME);
	    	 $("#topUserName").text(INDEX_USER_NAME);
	    	 JSWK.clewBox("当前登录用户组织角色："+INDEX_USER_ORGNAME+" - "+INDEX_USER_ROLENAME,'clew_ico_succ',3000);
	 	});
    </script>

</head>

<body style="padding:0px; padbackground:#EAEEF5;"> 
 
   <!-- 顶部页面 -->
   <jsp:include page="top.jsp" />
 
   <!-- 主页面 -->
   <div id="layout1" style="width:99.2%; margin:0 auto; margin-top:0px; "> 
                
        <div position="center" id="framecenter"> 
            <div tabid="home" title="首页" >
                <iframe frameborder="0" name="home_iframe" id="home_iframe" src="<%=root%>/pages/welcome.jsp"></iframe>
            </div> 
        </div>        
   </div>
     
    <!-- 底部页面  -->
   <jsp:include page="bottom.jsp" />
   
</body>
</html>
