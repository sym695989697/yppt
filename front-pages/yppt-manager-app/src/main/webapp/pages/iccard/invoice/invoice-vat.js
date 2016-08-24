/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var Invoicevat = function() {
		this.init();
	};
	Invoicevat.prototype = {
		config : {
			queryObjectUrl : root + '/invoice/getICCardInvoiceApplyById.action',
			queryUrl : root + '/invoice/getICCardInvoiceApplyLogById.action',
			auditingUrl : root + '/invoice/auditingICCardInvoiceApply.action'
		},
		cache : {
			dialog : null
		},
		init : function(){
			this.initAddOrEdit();
			this.initTitleBlock();
			this.loadCss();
			this.loadGird();
			this.render();
		},
		initTitleBlock:function(){
			var imgUrl = '../../../images/spanIoc.png';		 
			CODE_UTIL.titleBlock($('#step1'), '开票基本信息', imgUrl,true);
			$('#step1').css('width',(parent.window.invoiceList.config.subWidth-100));
//			$('#step1_togglebtn').css('width',(parent.window.invoiceList.config.subWidth-100));
//			$('#step1_null').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step2'), '开票基本资质', imgUrl,true);
			$('#step2').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step3'), '开票结果', imgUrl,true);
			$('#step3').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step4'), '开票审核', imgUrl,true);
			$('#step4').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step5'), '操作记录', imgUrl,true);
			$('#step5').css('width',(parent.window.invoiceList.config.subWidth-100));
			
			$("#businessLicenseDeadlines").ligerDateEditor({
				format  : "yyyy-MM-dd",
				onChangeDate: function (value){
					var time1 = getSmpFormatDateByLong(value,false);
					var time2 = getSmpFormatNowDate(false);
					var result = dateCompare(time1,time2);
					if(result==0){
						$("#businessLicenseStatus").html("<font color='red'>"+CODE_UTIL.getCodeName('FILE-STATUS',result)+"</font>");
					}else{
						$("#businessLicenseStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',result));
					}
					
				}
			}); 
			$("#orgCodeCertificateDeadlines").ligerDateEditor({
				format  : "yyyy-MM-dd",
				onChangeDate: function (value){
					var time1 = getSmpFormatDateByLong(value,false);
					var time2 = getSmpFormatNowDate(false);
					var result = dateCompare(time1,time2);
					if(result==0){
						$("#orgCodeCertificateStatus").html("<font color='red'>"+CODE_UTIL.getCodeName('FILE-STATUS',result)+"</font>");
					}else{
						$("#orgCodeCertificateStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',result));
					}
				}
		    });
			$("#taxRegCertificateDeadlines").ligerDateEditor({
				format  : "yyyy-MM-dd",
				onChangeDate: function (value){
					var time1 = getSmpFormatDateByLong(value,false);
					var time2 = getSmpFormatNowDate(false);
					var result = dateCompare(time1,time2);
					if(result==0){
						$("#taxRegCertificateStatus").html("<font color='red'>"+CODE_UTIL.getCodeName('FILE-STATUS',result)+"</font>");
					}else{
						$("#taxRegCertificateStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',result));
					}
				}
			}); 
			$("#generalTaxProveDeadlines").ligerDateEditor({
				format  : "yyyy-MM-dd",
				onChangeDate: function (value){
					var time1 = getSmpFormatDateByLong(value,false);
					var time2 = getSmpFormatNowDate(false);
					var result = dateCompare(time1,time2);
					if(result==0){
						$("#generalTaxProveStatus").html("<font color='red'>"+CODE_UTIL.getCodeName('FILE-STATUS',result)+"</font>");
					}else{
						$("#generalTaxProveStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',result));
					}
				}
		    });
		},
		initAddOrEdit:function(){
			var pageId =parent.window.invoiceList.cache.subPageId;
			var that = this;
			
			var id = parent.window.invoiceList.cache.id;
			JAjax(that.config.queryObjectUrl,{'model.id':id}, 'json', function(data){
        		   if(data){
        			   //开票基本信息
        			   $("#id").val(parent.window.invoiceList.cache.id);
        			   //主要用作提交审核--短信提醒用
        			   $("#invoiceMoney_value").val(data.dataObject.invoiceMoney);
        			   $("#invoiceTypeId").val(data.dataObject.invoiceType);
        			   $("#invoiceType").text(CODE_UTIL.getCodeName('IC-INVOICE-TYPE',data.dataObject.invoiceType));
        			   $("#invoiceMoney").text(convertFen2Yuan(data.dataObject.invoiceMoney));
        			   $("#invoiceName").text(data.dataObject.invoiceName);
        			   $("#taxName").val(data.dataObject.taxName);
        			   $("#taxNo").val(data.dataObject.taxNo);
        			   $("#invoiceArea").val(data.dataObject.invoiceArea);
        			   $("#invoiceOpenBankName").val(data.dataObject.invoiceOpenBankName);
        			   $("#invoiceOpenBankAccount").val(data.dataObject.invoiceOpenBankAccount);
        			   $("#invoicePhone").val(data.dataObject.invoicePhone);
        			   
	        			 //收件人信息
	   					$("#receiverName").append(data.dataObject.receiverName);
	   					$("#receiverPhoneNum").append(data.dataObject.receiverPhoneNum);
	   					$("#province").append(CODE_UTIL.getZoneNameByCode(data.dataObject.province));
	   					$("#city").append(CODE_UTIL.getZoneNameByCode(data.dataObject.city));
	   					$("#district").append(CODE_UTIL.getZoneNameByCode(data.dataObject.district));
	   					$("#address").append(data.dataObject.address);
	   					$("#zipCode").append(data.dataObject.zipCode);
        			   //开票资质信息
        			   $("#businessLicenseDeadlines").val(data.dataObject.businessLicenseDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.businessLicenseDeadlines,false));
        			   $("#orgCodeCertificateDeadlines").val(data.dataObject.orgCodeCertificateDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.orgCodeCertificateDeadlines,false));
        			   $("#taxRegCertificateDeadlines").val(data.dataObject.taxRegCertificateDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.taxRegCertificateDeadlines,false));
        			   $("#generalTaxProveDeadlines").val(data.dataObject.generalTaxProveDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.generalTaxProveDeadlines,false));
        			   
        			   //资质状态信息
        			   if(data.dataObject.businessLicenseDeadlines){
        				   var status = dateCompare(getSmpFormatDateByLong(data.dataObject.businessLicenseDeadlines,false),getSmpFormatNowDate(false));
        				   if(status == "0"){
        					   $("#businessLicenseStatus").html("<font color='red'>" +CODE_UTIL.getCodeName('FILE-STATUS',status) + "</font>");
        				   }else{
        					   $("#businessLicenseStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',status));
        				   }
        			   }
        			   if(data.dataObject.orgCodeCertificateDeadlines){
        				   var status = dateCompare(getSmpFormatDateByLong(data.dataObject.orgCodeCertificateDeadlines,false),getSmpFormatNowDate(false));
        				   if(status == "0"){
        					   $("#orgCodeCertificateStatus").html("<font color='red'>" +CODE_UTIL.getCodeName('FILE-STATUS',status) + "</font>");
        				   }else{
        					   $("#orgCodeCertificateStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',status));
        				   }
        			   }
        			   if(data.dataObject.taxRegCertificateDeadlines){
        				   var status = dateCompare(getSmpFormatDateByLong(data.dataObject.taxRegCertificateDeadlines,false),getSmpFormatNowDate(false));
        				   if(status == "0"){
        					   $("#taxRegCertificateStatus").html("<font color='red'>" +CODE_UTIL.getCodeName('FILE-STATUS',status) + "</font>");
        				   }else{
        					   $("#taxRegCertificateStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',status));
        				   }
        			   }
        			   if(data.dataObject.generalTaxProveDeadlines){
        				   var status = dateCompare(getSmpFormatDateByLong(data.dataObject.generalTaxProveDeadlines,false),getSmpFormatNowDate(false));
        				   if(status == "0"){
        					   $("#generalTaxProveStatus").html("<font color='red'>" +CODE_UTIL.getCodeName('FILE-STATUS',status) + "</font>");
        				   }else{
        					   $("#generalTaxProveStatus").html(CODE_UTIL.getCodeName('FILE-STATUS',status));
        				   }
        			   }
        			   
        			   //开票结果
        			   $("#statusId").val(data.dataObject.status);
        			   $("#status").text(CODE_UTIL.getCodeName('INVOICE-STATUS',data.dataObject.status));
        			   $("#remark").text(data.dataObject.mark == null ? "": data.dataObject.mark);
        			   $("#actualInvoiceMoney").text(convertFen2Yuan(data.dataObject.invoiceMoney));
        			   $("#invoiceOpenerName").text(data.dataObject.invoiceOpenerName==null?"":data.dataObject.invoiceOpenerName);
        			   $("#invoiceOpenTime").text(data.dataObject.invoiceOpenTime == null ? "" :getSmpFormatDateByLong(data.dataObject.invoiceOpenTime,true));
        			   $("#expressCompany").text(data.dataObject.expressCompany==null?"":data.dataObject.expressCompany);
        			   $("#expressOrderNo").text(data.dataObject.expressOrderNo==null?"":data.dataObject.expressOrderNo);
        			   
        			   //加载图片的URL
        			   $("#orgCodeCertificateUrl").val(data.dataObject.orgCodeCertificateUrl);
        			   $("#_orgCodeCertificateUrl").attr("src",data.dataObject.orgCodeCertificateUrl);
        			   
        			   $("#businessLicenseUrl").val(data.dataObject.businessLicenseUrl);
        			   $("#_businessLicenseUrl").attr("src",data.dataObject.businessLicenseUrl);
        			   
        			   $("#invoiceFileUrl").val(data.dataObject.invoiceFileUrl);
        			   $("#_invoiceFileUrl").attr("src",data.dataObject.invoiceFileUrl);
        			   
        			   $("#taxRegCertificateUrl").val(data.dataObject.taxRegCertificateUrl);
        			   $("#_taxRegCertificateUrl").attr("src",data.dataObject.taxRegCertificateUrl);
        			   
        			   $("#generalTaxProveUrl").val(data.dataObject.generalTaxProveUrl);
        			   $("#_generalTaxProveUrl").attr("src",data.dataObject.generalTaxProveUrl);
        			   
        		   } 
        	   }, function(){JSWK.clewBox('查询码表对象失败','clew_ico_fail',failShowTime);}, true); 
		 },
		 loadGird : function() {
			 var that = this;
				var myitems = [];
				var options = {};
				var columns = [
						{
							display : '操作时间',
							name : 'approvalTime',
							align : 'center',
							width : '18%',
							isSort : false, 
							render : function(row) {
		    					return  getSmpFormatDateByLong(row.approvalTime,true);
		    				}
						},{
							display : '操作人',
							name : 'approvalPerson',
							align : 'center',
							width : '18%',
							isSort : false
						},{
							display : '操作',
							name : 'approvalState',
							align : 'center',
							width : '18%',
							isSort : false,
							render : function(row) {
								if(row.approvalState == "2" || row.approvalState == "3")
		                			return "审核";
								else
									return "开票";
							}
						},{
							display : '处理结果',
							name : 'approvalState',
							align : 'center',
							width : '18%',
							isSort : false,
							render : function(row) {
								if(row.approvalState)
		                			return CODE_UTIL.getCodeName('INVOICE-STATUS',row.approvalState);
								else
									return "";
							}
						},{
							display : '备注',
							name : 'remark',
							align : 'center',
							width : '18%',
							isSort : false
						}
						];
				options['enabledEdit'] = true;
				options['columns'] = columns;
				options['frozenCheckbox'] = false;
				options["height"] = 100;
				options["width"] = 850;
				options['usePager'] = false;
				options['parms'] = {
						'model.id': parent.window.invoiceList.cache.id
				}; // 设置默认进来的请求参数
				MALQ_UI.setGridOptions(options, that.config.queryUrl);

				$('#operateresult').ligerGrid(options);
				that.cache.grid = parent.window.invoiceList.cache.clickrow;
				$('#operateresult').css('top', '3px');
				$('#pageloading').hide();// 加载图片
		 },
		 render : function(){
				var that = this;
			    //初始化页面检验规则
			    $.metadata.setType('attr', 'validate');
		        $('#form1').validate({
		            rules: {
		            	description:{
		        			 required : false,
		        			 maxlength : 60
		        		},
		        		taxName:{
		        			 required : false
		        		},
		        		taxNo:{
		        			 required : false
		        		},
		        		invoiceArea:{
		        			 required : false
		        		},
		        		invoiceOpenBankName:{
		        			 required : false
		        		},
		        		invoiceOpenBankAccount:{
		        			 required : false
		        		},
		        		invoicePhone:{
		        			 required : false
		        		},
		        		businessLicenseDeadlines:{
		        			 required : false
		        		},
		        		orgCodeCertificateDeadlines:{
		        			 required : false
		        		},
		        		taxRegCertificateDeadlines:{
		        			 required : false
		        		},
		        		generalTaxProveDeadlines:{
		        			 required : false
		        		}
		        	},
		        	messages: {
		        		description:{maxlength: '长度不能大于60个字'}
		        	},
		        	errorPlacement: function (lable, element){
		        		CSM_VALIDATE.errorPlacement(lable, element);
			        },
		            success: function (lable){
		            	CSM_VALIDATE.success(lable);
		            },
		            submitHandler: function(){
		            	var agree = liger.get('agree').getValue();
		            	var disagree = liger.get('disagree').getValue();
		            	var status = "";
		            	if(agree){
		            		status = "2";
		            	}
		            	if(disagree){
		            		status = "3";
		            	}
		            	if(status == ""){
		            		parent.invoiceList.success_alert("请选择意见！");
		            		return;
		            	}
		            	//审核不通过
		            	if(status == "3"&&($("#description").val()==null||$("#description").val()=="")){
		            		parent.invoiceList.success_alert("请输入审核意见或建议！");
		            		return;
		            	}
		            	//审核通过
		            	if(status == "2"){
		            		if($("#taxName").val()==null||$("#taxName").val()==""){
		            			parent.invoiceList.success_alert("请输入纳税人名称！");
			            		return;
		            		}
							if($("#taxNo").val()==null||$("#taxNo").val()==""){
								parent.invoiceList.success_alert("请输入税号！");
			            		return;	            			
		            		}
							if($("#invoiceArea").val()==null||$("#invoiceArea").val()==""){
								parent.invoiceList.success_alert("请输入地址！");
			            		return;	
							}
							if($("#invoiceOpenBankName").val()==null||$("#invoiceOpenBankName").val()==""){
								parent.invoiceList.success_alert("请输入开户行！");
			            		return;
							}
							if($("#invoiceOpenBankAccount").val()==null||$("#invoiceOpenBankAccount").val()==""){
								parent.invoiceList.success_alert("请输入账号！");
			            		return;
							}
							if($("#invoicePhone").val()==null||$("#invoicePhone").val()==""){
								parent.invoiceList.success_alert("请输入电话！");
			            		return;
							}
							if($("#businessLicenseDeadlines").val()==null||$("#businessLicenseDeadlines").val()==""){
								parent.invoiceList.success_alert("请输入营业执照证件截止日期！");
			            		return;
							}
							if($("#orgCodeCertificateDeadlines").val()==null||$("#orgCodeCertificateDeadlines").val()==""){
								parent.invoiceList.success_alert("请输入组织机构代码证证件截止日期！");
			            		return;					
							}
							if($("#taxRegCertificateDeadlines").val()==null||$("#taxRegCertificateDeadlines").val()==""){
								parent.invoiceList.success_alert("请输入一般纳税人税务登记证证件截止日期！");
			            		return;
							}
							if($("#generalTaxProveDeadlines").val()==null||$("#generalTaxProveDeadlines").val()==""){
								parent.invoiceList.success_alert("请输入一般纳税人证明证件截止日期！");
			            		return;
							}
		            	}
		            	$.ligerDialog.confirm('确定对记录进行审核吗？', function(yes) {
							 if(yes){
								var params={};
								
								//基础信息
								params['model.id']=$("#id").val();
								params['model.invoiceType']=$("#invoiceTypeId").val();
		   						params['model.invoiceMoney']=$("#invoiceMoney_value").val();
								params['model.invoiceName']=$("#invoiceName").text();
								params['model.taxName']=$("#taxName").val();
								params['model.taxNo']=$("#taxNo").val();
								params['model.status']=status;
								params['model.mark']=$("#description").val();
								
								params['model.invoiceArea']=$("#invoiceArea").val();
								params['model.invoiceOpenBankName']=$("#invoiceOpenBankName").val();
								params['model.invoiceOpenBankAccount']=$("#invoiceOpenBankAccount").val();
								params['model.invoicePhone']=$("#invoicePhone").val();
								params['model.receiverPhoneNum']=$("#receiverPhoneNum").text();
								//开票资质信息
								params['model.businessLicenseDeadlines']=getFormatDataToLong($("#businessLicenseDeadlines").val());
								params['model.orgCodeCertificateDeadlines']=getFormatDataToLong($("#orgCodeCertificateDeadlines").val());
								params['model.taxRegCertificateDeadlines']=getFormatDataToLong($("#taxRegCertificateDeadlines").val());
								params['model.generalTaxProveDeadlines']=getFormatDataToLong($("#generalTaxProveDeadlines").val());
//									params['model.invoiceLogInfoDeadlines']=getFormatDataToLong($("#invoiceLogInfoDeadlines").val());
								
								JAjax(that.config.auditingUrl,params, 'json', function(data){
									if(data.message){
									    parent.invoiceList.search();
										parent.invoiceList.success_alert(data.message);
										parent.invoiceList.cache.dialog.close();
									}else{
										parent.invoiceList.success_alert(data.message);
									}
					        	}, function(){JSWK.clewBox('开票审核出现异常！','clew_ico_fail',failShowTime);}, true); 
							 }
						 });
		            }
	        });
		},
		queryBigImg:function(urlName){
			var o=document.getElementById("_"+urlName);
			showBigImg(o);
		},
		loadCss : function(){
			 $('form').ligerForm();
		},
		reset : function(){
			window.location='invoice-vat.jsp';
		}
	};
	
	$(document).ready(function() {
		window.invoicevat = new Invoicevat();
	});
})();