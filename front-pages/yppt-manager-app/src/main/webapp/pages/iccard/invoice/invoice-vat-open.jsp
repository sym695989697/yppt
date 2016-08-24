<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编码信息新增页面</title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script type="text/javascript" src="invoice-vat-open.js?v=g_version"></script> 
</head>
<body style="height: 90%; padding: 20px;">
	<div id="showBigImg" onclick="javascript:$(this).hide();" style="position:absolute;display: none; cursor: pointer; width:900px;height: 500px;z-index: 1000;" title="点击关闭大图"></div>
	<form name="form1" method="post" action="" id="form1">
		<input type="hidden" id="id" name="id" /> 
		<input type="hidden" id="businessLicenseUrl" name="businessLicenseUrl" /> 
		<input type="hidden" id="orgCodeCertificateUrl" name="orgCodeCertificateUrl" /> 
		<input type="hidden" id="taxRegCertificateUrl" name="taxRegCertificateUrl" /> 
		<input type="hidden" id="generalTaxProveUrl" name="generalTaxProveUrl" /> 
		<input type="hidden" id="invoiceFileUrl" name="invoiceFileUrl" /> 
		<input type="hidden" id="invoiceMoney_value" name="invoiceMoney_value" /> 
		<div id="tableDiv" style="overflow: auto; height: 350px;">
			<table cellpadding="0" cellspacing="0" class="l-table-edit" align="center">
				<tr>
					<td colspan="6">
						<div id="step1"></div>
					</td>
				</tr>
				<tr>
					<td width="100" align="right" class="l-table-edit-td">开票类型:</td>
					<td width="100"  align="left" class="l-table-edit-td" id="invoiceType" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">开票金额:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceMoney" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">开票抬头:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceName" style="word-break:break-all;"></td>
				</tr>
				<tr>
					<td width="100" align="right" class="l-table-edit-td">纳税人名称:</td>
					<td width="100" align="left" class="l-table-edit-td" id="taxName" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">税号:</td>
					<td width="100" align="left" class="l-table-edit-td" id="taxNo" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">地址:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceArea" style="word-break:break-all;"></td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">开户行:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceOpenBankName" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">账号:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceOpenBankAccount" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">电话:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoicePhone" style="word-break:break-all;"></td>
				</tr>
				
				<tr>
					<td align="right" height="30" class="l-table-edit-td">收件人信息:</td>
					<td width="200" style="word-break:break-all;">
						<label id="receiverName"></label>
						<label id="receiverPhoneNum"></label><br/>
						<label id="province"></label>
						<label id="city"></label>
						<label id="district"></label>
						<label id="address"></label><br/>
						<label id="zipCode"></label>
					</td>
				</tr>
			 	<tr>
					<td colspan="6">
						<div id="step2"></div>
					</td>
				</tr>
				<tr>
					<td width="100" align="right" class="l-table-edit-td">营业执照:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<a href="javascript:void(0)" id="imgHref" title="点击查看大图" onclick="window.invoicevatopen.queryBigImg('businessLicenseUrl')">查看</a>
						<img id="_businessLicenseUrl"  height="40" width="40" style="cursor: pointer;display: none;"/>
					</td>
					
					<td width="100" align="right" class="l-table-edit-td">证件截止日期:</td>
					<td width="100" align="left" class="l-table-edit-td" id="businessLicenseDeadlines" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">状态:</td>
					<td width="100" align="left" class="l-table-edit-td" id="businessLicenseStatus" style="word-break:break-all;"></td>
					
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">组织机构代码证:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<a href="javascript:void(0)" id="imgHref" title="点击查看大图" onclick="window.invoicevatopen.queryBigImg('orgCodeCertificateUrl')">查看</a>
						<img id="_orgCodeCertificateUrl"  height="40" width="40" style="cursor: pointer;display: none;"/>
					</td>
					
					<td width="100" align="right" class="l-table-edit-td">证件截止日期:</td>
					<td width="100" align="left" class="l-table-edit-td" id="orgCodeCertificateDeadlines" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">状态:</td>
					<td width="100" align="left" class="l-table-edit-td" id="orgCodeCertificateStatus" style="word-break:break-all;"></td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">一般纳税人税务登记证:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<a href="javascript:void(0)" id="imgHref" title="点击查看大图" onclick="window.invoicevatopen.queryBigImg('taxRegCertificateUrl')">查看</a>
						<img id="_taxRegCertificateUrl"  height="40" width="40" style="cursor: pointer;display: none;"/>
					</td>
					
					<td width="100" align="right" class="l-table-edit-td">证件截止日期:</td>
					<td width="100" align="left" class="l-table-edit-td" id="taxRegCertificateDeadlines" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">状态:</td>
					<td width="100" align="left" class="l-table-edit-td" id="taxRegCertificateStatus" style="word-break:break-all;"></td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">一般纳税人证明:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<a href="javascript:void(0)" id="imgHref" title="点击查看大图" onclick="window.invoicevatopen.queryBigImg('generalTaxProveUrl')">查看</a>
						<img id="_generalTaxProveUrl"  height="40" width="40" style="cursor: pointer;display: none;"/>
					</td>
					
					<td width="100" align="right" class="l-table-edit-td">证件截止日期:</td>
					<td width="100" align="left" class="l-table-edit-td" id="generalTaxProveDeadlines" style="word-break:break-all;">
					</td>
					
					<td width="100" align="right" class="l-table-edit-td">状态:</td>
					<td width="100" align="left" class="l-table-edit-td" id="generalTaxProveStatus" style="word-break:break-all;"></td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">开票信息:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<a href="javascript:void(0)" id="imgHref" title="点击查看大图" onclick="window.invoicevatopen.queryBigImg('invoiceFileUrl')">查看</a>
						<img id="_invoiceFileUrl"  height="40" width="40" style="cursor: pointer;display: none;"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<div id="step3"></div>
					</td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">开票状态:</td>
					<td width="100" align="left" class="l-table-edit-td" id="status" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">备注:</td>
					<td width="100" align="left" class="l-table-edit-td" id="remark" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">开票金额:</td>
					<td width="100" align="left" class="l-table-edit-td" id="actualInvoiceMoney" style="word-break:break-all;">
					</td>
					
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>开票人员:</td>
					<td width="100" align="left" class="l-table-edit-td" >
						<input type="text" name="invoiceOpenerName" id="invoiceOpenerName" />
					</td>
					
					<td width="100" align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>快递公司:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<input type="text" name="expressCompany" id="expressCompany" />
					</td>
					
					<td width="100" align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>快递单号:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<input type="text" name="expressOrderNo" id="expressOrderNo" />
					</td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">开票时间:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceOpenTime" style="word-break:break-all;"></td>
				</tr>
				
				<tr>
					<td colspan="6">
						<div id="step4"></div>
					</td>
				</tr>
				
				<tr>
	                <td width="100" align="right" class="l-table-edit-td">意见:</td>
	                <td width="100" align="left">
	                	<input type="radio" id="agree" name="approvalResult"  checked="checked" value="4"/>同意&nbsp;&nbsp;
						<input type="radio" id="disagree" name="approvalResult" value="5" />不同意
	                </td>
	            </tr>
				
				
				<tr>
					<td colspan="6" align="left">
						<textarea name="description" id="description" rows="4" cols="90"></textarea>
					</td>
				</tr>
				
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="开票" id="submit" class="l-button l-button-submit" />
					</td>
				</tr>
				
				<tr>
					<td colspan="6">
						<div id="step5"></div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div id="operateresult" class="grid"></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
	 <div style="display: none"></div>
</body>

</html>