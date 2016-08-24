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
			checkUrl : root+'/apply/check.action',
			checkCardUrl : root+'/apply/getICCardOrg.action',
            queryObjectUrl :  root+'/apply/getApplyMeta.action'
		},
		cache : {
			dialog : null,
			flag:false,
			cardArray:[],
			type:null,
			applyId:null
		},
		init : function() {
			//对发卡地区进行初始化
			var towData = [];//
			towData.unshift({'name':'请选择','code':''});
			$('#openCardOrgCode').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 180,
	  			width:180
	  		});
		    this.initMessage();
		    this.showMesssage();
		},
		initMessage : function() {
			var that = this;
			var id = parent.window.applyList.cache.applyId;
			var wfs = parent.window.applyList.cache.wfs;
			var params = {
					'model.id' : id,
					'model.status' : wfs
				};
			JAjax(that.config.queryObjectUrl, params, 'json',
					function(data) {
						var phoneNum = null;//绑定手机号
						var acceptMessage = null;//是否发送短信
						var oftenArea = null;//常用地区
						var idNo = null;//身份证号
						var cardType = null;//卡类型
						var userName = null;//用户姓名
						var applyUserName = null;//申请人姓名
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
						var zipCode=null;
						var cardTypeName = null;
						if (data && data.dataObject) {
							f=data.dataObject;
							if(f.iCCardApply){
								userRegPhone = f.iCCardApply.userRegPhone;
								cardType = f.iCCardApply.cardType;
								applyUserName = f.iCCardApply.applyUserName;
								cardTypeName = CODE_UTIL.getCodeName('IC-CARD-TYPE',cardType);
								$("#cardTypeName").val(cardTypeName);
								var codeData = CODE_UTIL.getCodeData(cardType);
								$('#openCardOrgCode').ligerGetComboBoxManager().setData(codeData);
								oftenArea = f.iCCardApply.oftenArea;
								idNo = f.iCCardApply.idNo;
								userName = f.iCCardApply.userName;
								receiverPhoneNum = f.iCCardApply.receiverPhoneNum;
								receiverName = f.iCCardApply.receiverName;
								province = f.iCCardApply.province;
								city = f.iCCardApply.city;
								district = f.iCCardApply.district;
								address = f.iCCardApply.address;
								status = f.iCCardApply.status;
								zipCode = f.iCCardApply.zipCode;
								openCardOrgCode = f.iCCardApply.openCardOrgCode;
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
									temp.find("#seq").append(i);
									temp.find("#vehicleNo").append(vehicleNo);
									if(vehicleLicense!=null && vehicleLicense!=''){
										var vehicleLicenses = vehicleLicense.split(",");
										temp.find("#vehicleLicense").append("<a href='"+vehicleLicenses[1]+"' target='_blank'><img src='"+vehicleLicenses[0]+"' /></a>");
									}
									temp.find("#phoneNum").append(phoneNum);
									if(acceptMessage){
										temp.find("#acceptMessage").append(acceptMessage);
									}
									liger.get('openCardOrgCode').setValue(openCardOrgCode);
									if(status != '05' && status != '06' && status !='07'){
										temp.find("#cardNum").append("");
									}else{
										temp.find("#cardNum").append("<input name='cardNum' item='"+detail.id+"' type='text' class='cardNum' />");
									}
									$("#cardInfo").append(temp);
									$(".cardNum").css("width","95%");
									$(".cardNum").focus();
								//检验卡号
								$(".cardNum").each(function(){
									var card = $(this);
//									card.on("keyup",function(i){
//										that.checkCardNum(card.val(),card,cardType);
//									});
									card.change(function(){
										that.checkCardNum(card.val(),card,cardType);
									});
								});
									
							}
							$("#dataTemp").hide();
						}
							
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
							//$("#info").append(receiverName+'&nbsp&nbsp&nbsp'+receiverPhoneNum+'<br/>'+province+'&nbsp'+city+'&nbsp'+district+'&nbsp'+address+'<br/>'+zipCode);
						}
					}, function() {
						JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
					}, false);
			$("#expressInfo").change(function(){
				var num = $(this).val();
				if(!/^[0-9]*$/.exec(num)){
					JSWK.clewBox('快递单号必须为正整数', 'clew_ico_fail', 1000);
					$("#expressInfo").val("");
					 $("#expressInfo").focus();
					return;
				}
			});
				 
				
		},
		checkCardNum : function(cardNum,obj,cardType) {
			var that = this;
			//var code = liger.get('openCardOrgCode').getValue();
			//判断卡号重复
			if(!cardNum){
				return;
			}
			
			var cardNumbers = $(".cardNum");
			$(cardNumbers).each(function(){
				if($(obj).attr("item")==$(this).attr("item")||obj.value=='')return true;
				if(cardNum==this.value){
					window.applyCheck.cache.flag=true;
					return false;
				}	
			});
			if(window.applyCheck.cache.flag==false){
				var param={
						'cardNum' : cardNum,
						'cardType': cardType
					};
					JAjax(root+'/apply/checkICCardNum.action', param, 'json',
							function(data) {
								if (data && data.dataObject =="0") {
									
								}else if(data&&data.dataObject=="1"){
									window.applyCheck.cache.flag = false;
									JSWK.clewBox('卡与卡类型不匹配！', 'clew_ico_fail', 1000);
									obj.val("");
									$(obj).focus();
								}else if(data&&data.dataObject==null){
									window.applyCheck.cache.flag = false;
									JSWK.clewBox('卡信息有误！', 'clew_ico_fail', 1000);
									obj.val("");
									$(obj).focus();
								}else if(data&&data.dataObject=='3'){
									window.applyCheck.cache.flag = false;
									JSWK.clewBox('卡被审核过！！', 'clew_ico_fail', 1000);
									obj.val("");
									$(obj).focus();
								}else if(data&&data.dataObject=='2'){
									window.applyCheck.cache.flag = false;
									JSWK.clewBox('卡信息不存在！请重新选择', 'clew_ico_fail', 1000);
									obj.val("");
									$(obj).focus();
								}else{
									window.applyCheck.cache.flag = false;
									JSWK.clewBox('系统出现异常！', 'clew_ico_fail', 1000);
									obj.val("");
									$(obj).focus();
								}
							}, function() {
								JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
							}, true);
			}else{
				JSWK.clewBox('卡号不能重复', 'clew_ico_fail', 1000);
				obj.val('');
				window.applyCheck.cache.flag=false;
			}
			//alert(cardNum);
			
		},
		
		showMesssage:function(){
			var status=parent.window.applyList.cache.wfs;
			if(status=='00'||status=='01'){
				$("#infoCardTable").hide();
			}else if(status=='02'||status=='03'||status=='04'){
				$(".tr_address").hide();
			}else if(status=='05'||status=='06'||status=='07'){
				liger.get('openCardOrgCode')._setReadonly(true);
				liger.get('openCardOrgCode').setDisabled(true);
				$('#openCardOrgCode').css("color","#000");
				$(".l-trigger").hide();
			}
		},
		
		passSubmit:function(){
			var params={};
			params['model.id']=parent.window.applyList.cache.applyId;
			params['model.status']=parent.window.applyList.cache.wfs;
			var status = parent.window.applyList.cache.wfs;
			//卡务审核
			if(status == '02' || status == '03' || status =='04'){
				var code = liger.get('openCardOrgCode').getValue();
				if(code=='' || code==null){
					JSWK.clewBox('请选择发卡地区', 'clew_ico_fail', 1000);
					return ;
				}
				params['model.openCardOrgCode']=liger.get('openCardOrgCode').getValue();
			}
			var cards='';
			var cardNums = [];
			var flag=true;
			//行政审核
			if(status == '05' || status == '06' || status =='07'){
				var expressInfo = $("#expressInfo").val();
				var expressCompany = $("#expressCompany").val();
				if(!expressCompany){
					JSWK.clewBox('快递公司部能为空', 'clew_ico_fail', 1000);
					return;
				}
				if(!expressInfo){
					JSWK.clewBox('快递单号不能为空', 'clew_ico_fail', 1000);
					return;
				}
				params['model.expressCompany']=expressCompany;
				params['model.expressInfo']=expressInfo;
				$(".cardNum").each(function(){
					if(this.value.trim()==''){
						$(this).focus();
						flag=false;
						return ;
					}
					var detailId = $(this).attr("item");
					cards+=this.value+"_"+detailId+",";
					cardNums.push(this.value);
				});
				this.cache.cardArray = cardNums;
				
				if(cards!=''){
					cards=cards.substring(0,cards.length-1);
					params['cards']=cards;
				}
				
			
			}
			
			if(!flag){
				JSWK.clewBox('卡号不能为空', 'clew_ico_fail', 1000);
				return ;
			}
			var mark = $("#mark").val();
			if(mark){
				params['model.mark']=mark;
			}
			$(".l-button-submit").attr("disabled",true);
			$(".l-button-cance").attr("disabled",true);
			JAjax(root+'/apply/check.action', params, 'json',function(data) {
				if (data && data.dataObject =="0") {
					parent.window.applyList.cache.grid.loadData();
					parent.window.applyList.success_alert("操作成功");
					parent.window.applyList.cache.dialog.close();
				}else {
					$(".l-button-submit").attr("disabled",false);
					$(".l-button-cance").attr("disabled",false);
					JSWK.clewBox("审核失败", 'clew_ico_fail', 1000);
				}
				
			}, function() {
				$(".l-button-submit").attr("disabled",false);
				$(".l-button-cance").attr("disabled",false);
				JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
			}, true);
		},
		refuse:function(){
			var mark = $("#mark").val();
			if(mark==null || mark==''){
				JSWK.clewBox('备注信息不能为空', 'clew_ico_fail', 1000);
				return ;
			}
			var params = {};
			params['model.status']='10';
			params['model.id']=parent.window.applyList.cache.applyId;
			params['model.mark']=mark;
			$(".l-button-cance").attr("disabled",true);
			$(".l-button-submit").attr("disabled",true);
			JAjax(root+'/apply/check.action', params, 'json',function(data) {
				if (data && data.dataObject =="0") {
					parent.window.applyList.cache.grid.loadData();
					parent.window.applyList.success_alert("操作成功");
					parent.window.applyList.cache.dialog.close();
				}else {
					$(".l-button-cance").attr("disabled",false);
					$(".l-button-submit").attr("disabled",false);
					JSWK.clewBox("审核失败", 'clew_ico_fail', 1000);
				}
				
			}, function() {
				$(".l-button-cance").attr("disabled",false);
				$(".l-button-submit").attr("disabled",false);
				JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
			}, true);
		}
	};

	$(document).ready(function() {
		debugger;
		window.applyCheck = new ApplyCheck();
	});
})();