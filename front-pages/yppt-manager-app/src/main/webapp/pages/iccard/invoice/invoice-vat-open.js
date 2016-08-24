/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var Invoicevatopen = function() {
		this.init();
	};
	Invoicevatopen.prototype = {
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
			CODE_UTIL.titleBlock($('#step2'), '开票基本资质', imgUrl,true);
			$('#step2').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step3'), '开票结果', imgUrl,true);
			$('#step3').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step4'), '开票审核', imgUrl,true);
			$('#step4').css('width',(parent.window.invoiceList.config.subWidth-100));
			CODE_UTIL.titleBlock($('#step5'), '操作记录', imgUrl,true);
			$('#step5').css('width',(parent.window.invoiceList.config.subWidth-100));
			
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
        			   $("#taxName").text(data.dataObject.taxName);
        			   $("#taxNo").text(data.dataObject.taxNo);
        			   $("#invoiceArea").text(data.dataObject.invoiceArea);
        			   $("#invoiceOpenBankName").text(data.dataObject.invoiceOpenBankName);
        			   $("#invoiceOpenBankAccount").text(data.dataObject.invoiceOpenBankAccount);
        			   $("#invoicePhone").text(data.dataObject.invoicePhone);
        			   
        			   //收件人信息
	   					$("#receiverName").append(data.dataObject.receiverName);
	   					$("#receiverPhoneNum").append(data.dataObject.receiverPhoneNum);
	   					$("#province").append(CODE_UTIL.getZoneNameByCode(data.dataObject.province));
	   					$("#city").append(CODE_UTIL.getZoneNameByCode(data.dataObject.city));
	   					$("#district").append(CODE_UTIL.getZoneNameByCode(data.dataObject.district));
	   					$("#address").append(data.dataObject.address);
	   					$("#zipCode").append(data.dataObject.zipCode);
   					
        			   //开票资质信息
        			   $("#businessLicenseDeadlines").text(data.dataObject.businessLicenseDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.businessLicenseDeadlines,false));
        			   $("#orgCodeCertificateDeadlines").text(data.dataObject.orgCodeCertificateDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.orgCodeCertificateDeadlines,false));
        			   $("#taxRegCertificateDeadlines").text(data.dataObject.taxRegCertificateDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.taxRegCertificateDeadlines,false));
        			   $("#generalTaxProveDeadlines").text(data.dataObject.generalTaxProveDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.generalTaxProveDeadlines,false));
//        			   $("#invoiceLogInfoDeadlines").text(data.dataObject.invoiceLogInfoDeadlines == null ? null :getSmpFormatDateByLong(data.dataObject.invoiceLogInfoDeadlines,false));
        			   
        			   //资质状态信息
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
        			   $("#invoiceOpenerName").val(data.dataObject.invoiceOpenerName==null?"":data.dataObject.invoiceOpenerName);
        			   $("#invoiceOpenTime").text(data.dataObject.invoiceOpenTime == null ? "" :getSmpFormatDateByLong(data.dataObject.invoiceOpenTime,true));
        			   $("#expressCompany").val(data.dataObject.expressCompany==null?"":data.dataObject.expressCompany);
        			   $("#expressOrderNo").val(data.dataObject.expressOrderNo==null?"":data.dataObject.expressOrderNo);
        			   
        			   
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
		        		invoiceOpenerName:{
		        			 required : false
		        		},
		        		expressCompany:{
		        			 required : false
		        		},
		        		expressOrderNo:{
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
		            		status = "4";
		            	}
		            	if(disagree){
		            		status = "5";
		            	}
		            	if(status == ""){
		            		parent.invoiceList.success_alert("请选择意见！");
		            		return;
		            	}
		            	if(status == "5"&&($("#description").val()==null||$("#description").val()=="")){
		            		parent.invoiceList.success_alert("请输入审核意见或建议！");
		            		return;
		            	}
		            	
		            	if(status == "4"){
		            		if($("#expressCompany").val()==null||$("#expressCompany").val()==""){
		            			parent.invoiceList.success_alert("请输入快递公司名称！");
			            		return;
		            		}
		            		if($("#expressOrderNo").val()==null||$("#expressOrderNo").val()==""){
		            			parent.invoiceList.success_alert("请输入快递单号！");
			            		return;
		            		}
		            		if($("#invoiceOpenerName").val()==null||$("#invoiceOpenerName").val()==""){
		            			parent.invoiceList.success_alert("请输入开票名称！");
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
		   						params['model.status']=status;
		   						params['model.mark']=$("#description").val();
		   						params['model.receiverPhoneNum']=$("#receiverPhoneNum").text();
		   						//开票资质信息
		   						params['model.invoiceOpenerName']=$("#invoiceOpenerName").val();
		   						params['model.expressCompany']=$("#expressCompany").val();
		   						params['model.invoiceOpenTime']=new Date().getTime();
		   						params['model.expressOrderNo']=$("#expressOrderNo").val();
		   						var expressInfo = $("#invoiceOpenerName").val() + "  " +$("#invoicePhone").text();
		   						params['model.expressInfo']=expressInfo;
		   						
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
		window.invoicevatopen = new Invoicevatopen();
	});
})();