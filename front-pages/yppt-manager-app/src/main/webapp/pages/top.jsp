<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="../css/top/top.css" rel="stylesheet" type="text/css" />
<style>
.mr10 {
	margin-right: 3px;
	margin-left: 3px;
}
</style>
<script type="text/javascript">
 var logoutUrl = "<%=request.getContextPath() %>/logout.jsp";
 function logout(){
	 $.ligerDialog.confirm('您确认要退出系统吗？', function (yes){
  	   if(yes){      
  		 top.location=logoutUrl;
  	   }
     }); 
 };
</script>

<div class="header">
	<input type="hidden" id="subPageOrgId" name="subPageOrgId" /> 
	<img src="../images/chpt/bg_01.png" alt="油品管理系统" class="logo" />
	<ul class="header_info">
		<li><span>当前用户：</span> <span class="mr10"> <a href="#"><span
					id="topOrgName">中交兴路科车联网信息技有限公司</span></a>
		</span>- <span class="mr10"> <a href="#"><span id="toRoleName">角色信息</span></a>
		</span>- <span class="mr10"> <a href="#"><span id="topUserName">平台管理员</span></a>
		</span></li>
		<li>
<!-- 		<a href="#" class="mr10" onclick="indexPage.showUpdatePassPage();">修改密码</a>  -->
			<a
			href='javascript:void(0);' onclick="logout();">退出</a></li>
	</ul>

	<!-- 菜单 -->
	<ul id="my_menu"></ul>
</div>

