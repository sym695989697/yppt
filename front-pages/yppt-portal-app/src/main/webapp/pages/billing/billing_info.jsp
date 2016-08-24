<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<%@ taglib prefix="fragment" uri="http://www.95155.com/fragment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车旺油品服务-开票资料</title>

<link rel="stylesheet" href="../../css/common/base.css">
<link rel="stylesheet" href="../../css/common/common.css">
<link rel="stylesheet" href="../../css/common/new_common.css">
<link rel="stylesheet" href="../../css/oil/oil_pages.css">
<link rel="stylesheet" href="../../css/oil/eject.css">
<script type="text/javascript">
//页面标记，给菜单栏使用
//1关于加油卡2卡片管理3我要办卡4油卡充值5油卡交易6油卡返利7开票申请8开票记录9发票资料
var pageTag = 9;
</script>	
</head>
<style>
td {
	padding: 7px 5px;
}

.addrselector .addr {
	border: 1px solid #D5D9DE;
}
</style>
<body>
	<!--top-->
	<!-- 窄版页头 -->
	<fragment:include url="/page-head.html" />
	<!-- 首页宽版页头 -->
	<%-- <fragment:include url="/page-head-h.html"/> --%>
	<!-- 频道内页导航栏 -->
	<fragment:include url="/navigator-orange.html" />
	<div class="main">
		<!-- 导航菜单结束-->
		<!-- 导航菜单 -->
		<div class="authen_con_chip">
			<ul class="pa">
				<li class="fl pr2 color_chip">车旺首页</li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a
					href="<%=basePath%>pages/oil/oil_home.jsp">加油卡</a></li>
				<li class="fl pl2 pr2 color_chip">&gt;</li>
				<li class="fl pl2 pr2"><a
					href="<%=basePath%>pages/billing/billing_info.jsp">开票资料</a></li>
			</ul>
		</div>
		<!--content-->
		<div id="main_content" class="main w960 mauto cf help_pages_main">
			<!--left-->
			<jsp:include page="../left.jsp" />
		<!--right-->
		<div class="rightsideBar w800 fr">
			<div class="rightsideBar_title">
					<h3 class="fb f14 inline fl">开票资料</h3>
			</div>
			<div class="sideBar_bg border_rightBar billing_title pl20 pr20">
				<ul>
					<li class="f14 fl mr45 color_sizeA">新增发票信息</li>
					<li class="f14 fr color_sizeA">
						<a href="<%=basePath%>pages/oil/oil_introduction.jsp" class="color_ff pl15">了解发票</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<form id="form">
			<div class="border_rightBar pt30 pb30">
				    <div class="lists-form">
						<table >
						    <tr>
								<td class="lab"><span class="required">*</span>发票类型:</td>
								<td class="fld nextsel">
									<select datatype="*" name="billingType" id="billingType" errormsg="请选择发票类型！" nullmsg="请选择发票类型！">
									</select>
								</td>
								<td class="mark"></td>
							</tr>
							<tr>
								<td class="lab"><span class="required">*</span>发票抬头:</td>
								<td class="fld">
									<input datatype="*" type="text" name="billingTitle" id="billingTitle" value="" placeholder="请输入单位名称" errormsg="请输入发票抬头！" nullmsg="请输入发票抬头！"/>
								</td>
								<td class="mark"></td>
							</tr>
						</table>
					</div>
					<div class="next-lists-form">
						<table id="tableid">
							<tr>
							    <td class="lab"><span class="required">*</span>单位营业执照:</td>
								<td class="nextsel nextsel3">
								    <div id="dwyyzzPicId_file" class="fl img_tops"></div>
								</td>
							</tr>
							<tr>
							    <td class="lab"><span class="required">*</span>一般纳税人资格证:</td>
								<td class="nextsel nextsel3">
								    <div id="nszzPicId_file" class="fl img_tops"></div>
								</td>
							</tr>
							<tr>
							    <td class="lab"><span class="required">*</span>组织机构代码证:</td>
								<td class="nextsel nextsel3">
								    <div id="zzjgdmPicId_file" class="fl img_tops"></div>
								</td>
							</tr>
							<tr>
							    <td class="lab"><span class="required">*</span>税务登记证:</td>
								<td class="nextsel nextsel3">
								    <div id="swdjPicId_file" class="fl img_tops"></div>
								</td>
							</tr>
							<tr>
							    <td class="lab form_fld2"><span class="required">*</span><span>开票信息附件:</span></td>
								<td class="nextsel nextsel2">
								    <div id="kpfjPicId_file" class="fl img_tops"></div>
								    <span class="color_sizeE ml10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（必须上传带红章的图片）</span>
								</td>
							</tr>
						</table>
						<table>
							<tr>
							    <td class="lab"></td>
							    <td class="nextsel nextsel2">
								    <input class="nextselbtn" type="submit" value="保存">
							    </td>
							    <td class="mark"></td>
						    </tr>
						</table>
					</div>
			</div>
			</form>
			
			<div class="pt20 queryBlock">
				<div class="rightsideBar_title ">
					<h3 class="fb f14 inline fl">已保存<span id="exit"></span>条开票资料,还可新增<span id="residue"></span>条</h3>
				</div>
				<input type="hidden" class="pageNum pageParam" name="requestParam.page">
             	<input type="hidden" class="pageSize pageParam" name="requestParam.rows">
             		<!---->
				<div class="pt20">
					<div class="border_rightBar table_border">
					    <table width="100%" class="table_border2">
					   		<thead>
							    <tr>
								    <th class="first_th">发票类型</th>
								    <th>发票抬头</th>
									<th>税务登记证</th>
								    <th>单位营业执照</th>
								    <th>一般纳税人资格证</th>
								    <th>组织机构代码证</th>
								    <th>开票信息附件</th>
								    <th>操作</th>
								</tr>
							</thead>
							<tbody id="dataGrid"></tbody>
						</table>
					</div>
					<!--交易详情弹出-->
	 				<!--充值失败-->
				</div>
			</div>
		</div>
		</div>
	</div>
	<script type="text/template" id="tplGrid">
	{@if data && data.length}
  		{@each data as o,i}
			{@if i % 2 == 1}
 				<tr class="table_bg">
			{@else}
				<tr>
			{@/if}
					<td width="12%" class="first_td">$${o.invoiceType|getCodeName,'IC-INVOICE-TYPE'}</td>
					<td width="20%" style="word-break:break-all;">${o.invoiceName}</td>

					{@if o.taxRegCertificateUrl == null || o.taxRegCertificateUrl == ""}
 						<td width="12%">--</td>
					{@else}
						<td width="12%"><a href="#" href = "javascript:void(0)" onclick = "document.getElementById('light${i}taxRegCertificateUrl').style.display='block';document.getElementById('fade${i}taxRegCertificateUrl').style.display='block'"><font color="blue">查看</font></a>
						<div id="light${i}taxRegCertificateUrl" class="white_content1"><a href = "javascript:void(0)" onclick = "document.getElementById('light${i}taxRegCertificateUrl').style.display='none';document.getElementById('fade${i}taxRegCertificateUrl').style.display='none'"><img class="white_img" src="../../images/oil/close.png"></a><img src="${o.taxRegCertificateUrl}" width="100%"></div> 
                    	<div id="fade${i}taxRegCertificateUrl" class="black_overlay1"></div></td>
					{@/if}

					{@if o.businessLicenseUrl == null || o.businessLicenseUrl == ""}
 						<td width="12%">--</td>
					{@else}
						<td width="12%"><a href="#" href = "javascript:void(0)" onclick = "document.getElementById('light${i}businessLicenseUrl').style.display='block';document.getElementById('fade${i}businessLicenseUrl').style.display='block'"><font color="blue">查看</font></a>
						<div id="light${i}businessLicenseUrl" class="white_content1"><a href = "javascript:void(0)" onclick = "document.getElementById('light${i}businessLicenseUrl').style.display='none';document.getElementById('fade${i}businessLicenseUrl').style.display='none'"><img class="white_img" src="../../images/oil/close.png"></a><img src="${o.businessLicenseUrl}" width="100%"></div> 
                    	<div id="fade${i}businessLicenseUrl" class="black_overlay1"></div></td>
					{@/if}

					{@if o.generalTaxProveUrl == null || o.generalTaxProveUrl == ""}
 						<td width="14%">--</td>
					{@else}
						<td width="14%"><a href="#" href = "javascript:void(0)" onclick = "document.getElementById('light${i}generalTaxProveUrl').style.display='block';document.getElementById('fade${i}generalTaxProveUrl').style.display='block'"><font color="blue">查看</font></a>
						<div id="light${i}generalTaxProveUrl" class="white_content1"><a href = "javascript:void(0)" onclick = "document.getElementById('light${i}generalTaxProveUrl').style.display='none';document.getElementById('fade${i}generalTaxProveUrl').style.display='none'"><img class="white_img" src="../../images/oil/close.png"></a><img src="${o.generalTaxProveUrl}" width="100%"></div> 
                    	<div id="fade${i}generalTaxProveUrl" class="black_overlay1"></div></td>
					{@/if}

					{@if o.orgCodeCertificateUrl == null || o.orgCodeCertificateUrl == ""}
 						<td width="12%">--</td>
					{@else}
						<td width="12%"><a href="#" href = "javascript:void(0)" onclick = "document.getElementById('light${i}orgCodeCertificateUrl').style.display='block';document.getElementById('fade${i}orgCodeCertificateUrl').style.display='block'"><font color="blue">查看</font></a>
						<div id="light${i}orgCodeCertificateUrl" class="white_content1"><a href = "javascript:void(0)" onclick = "document.getElementById('light${i}orgCodeCertificateUrl').style.display='none';document.getElementById('fade${i}orgCodeCertificateUrl').style.display='none'"><img class="white_img" src="../../images/oil/close.png"></a><img src="${o.orgCodeCertificateUrl}" width="100%"></div> 
                    	<div id="fade${i}orgCodeCertificateUrl" class="black_overlay1"></div></td>
					{@/if}

					{@if o.invoiceFileUrl == null || o.invoiceFileUrl == ""}
 						<td width="12%">--</td>
					{@else}
						<td width="12%"><a href="#" href = "javascript:void(0)" onclick = "document.getElementById('light${i}invoiceFileUrl').style.display='block';document.getElementById('fade${i}invoiceFileUrl').style.display='block'"><font color="blue">查看</font></a>
						<div id="light${i}invoiceFileUrl" class="white_content1"><a href = "javascript:void(0)" onclick = "document.getElementById('light${i}invoiceFileUrl').style.display='none';document.getElementById('fade${i}invoiceFileUrl').style.display='none'"><img class="white_img" src="../../images/oil/close.png"></a><img src="${o.invoiceFileUrl}" width="100%"></div> 
                    	<div id="fade${i}invoiceFileUrl" class="black_overlay1"></div></td>
					{@/if}
					<td width="8%"><a href="javascript:window.billinginfo.del('${o.id}')"><font color="blue">删除</font></a></td>
				</tr>
		{@/each}
	{@else}
		<tr><td colspan="7" class="no-data">抱歉！没有找到与您条件匹配的开票记录，请重新搜索</td></tr>
	{@/if}
		{@if totalPage > 1}<tr><td colspan="7">$${start|getPageNumbers,totalPage,5}</td></tr>{@/if}
	</script>
	<script type="text/javascript">
			var basePath = "<%=basePath%>";
	</script>
	<script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../../js/plugins.js"></script>
	<script type="text/javascript" src="../../js/Validform_v5.3.2.js?v=g_version"></script>
	<script type="text/javascript" src="../../js/code_util.js"></script>
	<script type="text/javascript" src="../../js/util_ajax.js"></script>
	<script type="text/javascript" src="billing_info.js"></script>
	<!-- 页脚 -->
	<fragment:include url="/page-foot.html" />
</body>
</html>