<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编码信息新增页面</title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script type="text/javascript" src="invoice-common-open.js?v=g_version"></script> 
</head>
<body style="height: 90%; padding: 20px;">
	<div id="showBigImg" onclick="javascript:$(this).hide();" style="position:absolute;display: none; cursor: pointer; width:400px;height: 400px;z-index: 1000;" title="点击关闭大图"></div>
	<form name="form1" method="post" action="" id="form1">
		<input type="hidden" id="id" name="id" /> 
		<input type="hidden" id="invoiceTypeId" name="invoiceTypeId" /> 
		<input type="hidden" id="statusId" name="statusId" /> 
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
					<td width="100" align="left" class="l-table-edit-td" id="invoiceType" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">开票金额:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceMoney" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">开票抬头:</td>
					<td width="100" align="left" class="l-table-edit-td" id="invoiceName" style="word-break:break-all;"></td>
				</tr>
				<tr>
					<td width="100" align="right" class="l-table-edit-td">注册手机号:</td>
					<td width="100" align="left" class="l-table-edit-td" id="userRegPhone" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">会员名称:</td>
					<td width="100" align="left" class="l-table-edit-td" id="userName" style="word-break:break-all;"></td>
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
						<div id="step3"></div>
					</td>
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td">开票状态:</td>
					<td width="100" align="left" class="l-table-edit-td" id="status" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">备注:</td>
					<td width="100" align="left" class="l-table-edit-td" id="remark" style="word-break:break-all;"></td>
					
					<td width="100" align="right" class="l-table-edit-td">开票金额:</td>
					<td width="100" align="left" class="l-table-edit-td" id="actualInvoiceMoney" style="word-break:break-all;" >
					</td>
					
				</tr>
				
				<tr>
					<td width="100" align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>开票人员:</td>
					<td width="100" align="left" class="l-table-edit-td" style="word-break:break-all;">
						<input type="text" name="invoiceOpenerName" id="invoiceOpenerName" />
					</td>
					
					<td width="100" align="right" class="l-table-edit-td"><span style="color: red;">*&nbsp;</span>快递公司:</td>
					<td width="100"align="left" class="l-table-edit-td" style="word-break:break-all;">
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