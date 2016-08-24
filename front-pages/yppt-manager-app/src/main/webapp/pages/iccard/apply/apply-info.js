/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var ApplyCheck = function() {
		this.init();
	};
	ApplyCheck.prototype = {
		config : {
            queryObjectUrl :  root+'/apply/getApplyInfo.action'
		},
		cache : {
			dialog : null
		},
		init : function() {
		    this.initMessage();
		},
		initMessage : function() {
			var that = this;
			$("#infoCardTable").hide();
			var id = parent.window.applyList.cache.codeId;
			
			var params = {
					'model.id' : id
				};
			JAjax(that.config.queryObjectUrl, params, 'json',
					function(data) {
				var phoneNum = null;//绑定手机号
				var acceptMessage = null;//是否发送短信
				var oftenArea = null;//常用地区
				var idNo = null;//身份证号
				var applyUserName = null;//申请人姓名
				var cardType = null;//卡类型
				var userName = null;//用户姓名
				var receiverPhoneNum = null;//邮寄手机号
				var province = null;//省
				var city = null;//区
				var district = null;//县
				var address = null;//详细地址
				var vehicleNo = null;//车牌号
				var cardNum = null;//卡号
				var vehicleLicense = null;//行使证
				var receiverName = null;//收件人姓名
				var openCardOrgCode = null;//发卡地区
				var status = null;//操作状态
				var zipCode=null;//邮编
				var cardTypeName = null;//卡类型名称
				var expressInfo=null;//快递单号
				var expressCompany = null;//快递公司
				if (data && data.dataObject) {
					f=data.dataObject;
					if(f.iCCardApply){
						userRegPhone = f.iCCardApply.userRegPhone;
						cardType = f.iCCardApply.cardType;
						cardTypeName = CODE_UTIL.getCodeName('IC-CARD-TYPE',cardType);
						$("#cardTypeName").val(cardTypeName);
						oftenArea = f.iCCardApply.oftenArea;
						idNo = f.iCCardApply.idNo;
						applyUserName = f.iCCardApply.applyUserName;
						userName = f.iCCardApply.userName;
						receiverPhoneNum = f.iCCardApply.receiverPhoneNum;
						province = f.iCCardApply.province;
						city = f.iCCardApply.city;
						district = f.iCCardApply.district;
						address = f.iCCardApply.address;
						status = f.iCCardApply.status;
						receiverName = f.iCCardApply.receiverName;
						zipCode = f.iCCardApply.zipCode;
						openCardOrgCode = f.iCCardApply.openCardOrgCode;
						expressInfo = f.iCCardApply.expressInfo;
						expressCompany = f.iCCardApply.expressCompany;
					}
					
					if(status=='05'){
						$("#infoCardTable").show();
						$(".tr_address").hide();
					}else if(status=='08'){
						$("#infoCardTable").show();
					}else if(status=='06'||status=='07'){
						$("#infoCardTable").show();
						$(".tr_address").hide();
					}
					var details = f.iCCardApplyDetail;
					if(details){
						//$("#cardInfo").append(tds);
						for(var i=0;i<details.length;i++){
							var detail = details[i];
							var temp = $("#dataTemp").clone();
							vehicleLicense = detail.vehicleLicense;
							vehicleNo = detail.vehicleNo;
							phoneNum = detail.phoneNum;
							acceptMessage = CODE_UTIL.getCodeName('IS-SEND-MESSAGE',detail.acceptMessage);
							
							temp.find("#seq").append(i+1);
							temp.find("#vehicleNo").append(vehicleNo);
							if(vehicleLicense!=null && vehicleLicense!=''){
								var vehicleLicenses = vehicleLicense.split(",");
								temp.find("#vehicleLicense").append("<a href='"+vehicleLicenses[1]+"' target='_blank'><img src='"+vehicleLicenses[0]+"' /></a>");
							}
							temp.find("#phoneNum").append(phoneNum);
							if(acceptMessage){
								temp.find("#acceptMessage").append(acceptMessage);
							}
							if(detail.cardNum != null){
								temp.find("#cardNum").append(detail.cardNum);
							}
							$("#cardInfo").append(temp);
						}
						$("#dataTemp").hide();
					
					$("#cardType").append("<input style='border:0;' readonly='readonly' value='"+cardTypeName+"'/>");
					if(oftenArea){
						var areas = oftenArea.split(",");
						var areaNames = '';
						for(var i=0;i<areas.length;i++){
							if(areas[i]==''){
								continue;
							}
							var name = CODE_UTIL.getZoneNameByCode(areas[i]);
							areaNames += name+",";
						}
						if(areaNames!=''){
							areaNames=areaNames.substring(0, areaNames.length-1);
						}
						$("#oftenArea").append(areaNames);
					}
					$("#applyUserName").append(applyUserName);
					$("#idNo").append(idNo);
					$("#receiverName").append(receiverName);
					$("#receiverPhoneNum").append(receiverPhoneNum);
					$("#province").append(CODE_UTIL.getZoneNameByCode(province));
					$("#city").append(CODE_UTIL.getZoneNameByCode(city));
					$("#district").append(CODE_UTIL.getZoneNameByCode(district));
					$("#address").append(address);
					$("#zipCode").append(zipCode);
					$("#openCardOrgCode").val(CODE_UTIL.getCodeName(cardType,openCardOrgCode));
					$("#expressInfo").val(expressInfo);
					$("#expressCompany").val(expressCompany);
//					$("#info").append(CODE_UTIL.getZoneNameByCode(province)+" "+CODE_UTIL.getZoneNameByCode(city)+" "+CODE_UTIL.getZoneNameByCode(district)+" "+address+" "+zipCode);
					if(data && data.data){
						var logs = data.data;
						var stepNo = 0;
						if(logs.length<3){
							$("#logInfo").hide();
						}
						for(var i=0;i<logs.length;i++){
							var log = logs[i];
							var temp = $("#logTemp").clone();
							
							if(log.approvalState=='10' | log.approvalState=='02' | log.approvalState=='05' | log.approvalState=='08'){
								var time=getFormatDate(new Date(log.approvalTime), 'yyyy-MM-dd hh:mm:ss');
								var status = CODE_UTIL.getCodeName('IC-APPLY-STAT',log.approvalState);
								if(log.suggestion=='系统导入'){
									$("#logInfo").show();
								}
								stepNo = stepNo+1;
								temp.find("#stepNo").append(stepNo);
								temp.find("#approvalPerson").append(log.approvalPerson);
								temp.find("#approvalTime").append(time);
								temp.find("#approvalState").append(status);
								temp.find("#suggestion").append(log.suggestion);
								$("#logInfo").append(temp);
							}
						};
						$("#logTemp").hide();
					}else{
						$("#head").hide();
						$("#logInfo").hide();
					}
					//$("#info").append(receiverName+'&nbsp&nbsp&nbsp'+receiverPhoneNum+'<br/>'+province+'&nbsp'+city+'&nbsp'+district+'&nbsp'+address+'<br/>'+zipCode);
					
					}
				}
				}, function() {
						JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
					}, true);
		}
				
	};
	$(document).ready(function() {
		debugger;
		window.applyCheck = new ApplyCheck();
	});
})();