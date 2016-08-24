<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>审核</title>
<jsp:include page="../../all_css.jsp" />
<jsp:include page="../../all_js.jsp" />
<script src="apply-check.js" type="text/javascript" charset="utf-8"></script>
 <script type="text/javascript">
 var id = '<%=request.getParameter("id")%>';
 var userId = '<%=request.getParameter("userId")%>';
 </script>
 <style type="text/css">
 	td{
 		height:25px;
 	}
 </style>
</head>
<body>
	<div style="width:100%;height:100%;overflow: auto;">
	<form name="form1" method="post" action="" id="form1">
		<div id="tableDiv">
			<table border="0"  width="93%" class="l-table-edit">
				<tr>
					<td width="20%" align="right" class="l-table-edit-td">油卡类型:</td>
					<td width="80%" align="left" class="l-table-edit-td"><label id="cardType"></label>
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">常用地区:</td>
					<td align="left" class="l-table-edit-td"><label id="oftenArea"></label>
					</td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">申请人姓名:</td>
					<td align="left" class="l-table-edit-td"><label id="applyUserName"></label></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">身份证号:</td>
					<td align="left" class="l-table-edit-td"><label id="idNo"></label></td>
				</tr>
				<tr>
					<td align="right" height="30" class="l-table-edit-td">收件人信息:</td>
					<td>
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
					<td align="right" class="l-table-edit-td">卡片信息:</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="cardInfo" width="93%" align="center" border="1">
							<thead align="center">
								<tr>
									<td width="50">序号</td>
									<td width="100">车牌号</td>
									<td width="100">行使证</td>
									<td width="100">绑定手机号</td>
									<td width="120">是否接收短信</td>
									<td width="120">卡号</td>
								</tr>
								<tr id="dataTemp">
									<td align="center"><label id="seq"></label></td>
									<td align="center"><label id="vehicleNo"></label></td>
									<td align="center"><label id="vehicleLicense"></label></td>
									<td align="center"><label id="phoneNum"></label></td>
									<td align="center"><label id="acceptMessage"></label></td>
									<td align="center"><label id="cardNum"></label></td>
								</tr>
							</thead>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" width="100%"  align="center"  height="40px" id="openCardOrgName_title">
						<table id="infoCardTable">
						<tr style="height:10px"></tr>
						<tr>
							<td>
								发卡地区：
							</td>
							<td>
								<input id="openCardOrgCode" name="openCardOrgCode" align="center" type="text"/>
							</td>
						</tr>
						<tr style="height:10px"></tr>
							<tr class="tr_address">
								<td colspan="2">
									<table>
										<tr>
											<td>
												快递公司：
											</td>
											<td>
												<input id="expressCompany" name="expressCompany" style="width:180px;height:20px" type="text" class="address"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr style="height:10px"></tr>
							<tr class="tr_address">
								<td>
									快递单号：
								</td>
								<td>
									<input id="expressInfo" name="address" style="width:180px;height:20px" type="text" class="address"/>
								</td>
							</tr>
						</table>
						
						</td>
					
				</tr>
				<tr style="height:10px"></tr>
				<tr>
					<td colspan="2" align="center">
						<table border="0" width="100%">
							<tr>
								<td height="30px"  align="right" width="42.5%">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
								<td><input style="width:180px;height:20px" type="text" id="mark" name="mard" type="text"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr style="height:10px"></tr>
				<tr>
					<td colspan="2" align="center">
					<input type="hidden" id="openCardOrgCode"/>
						<input type="button" value="通过"id="Button1" onclick="javascript:window.applyCheck.passSubmit();" class="l-button l-button-submit" />
						<input type="button" value="拒绝" class="l-button l-button-cance" id="refuse" status="10" onclick="javascript:window.applyCheck.refuse();"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	</div>
	<div style="display: none"></div>
</body>

</html>
