/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var MaincardAdd = function() {
		this.init();
	};
	MaincardAdd.prototype = {
		config : {
			addUrl : root + '/mainCard/addMaincard.action',
			modeifyUrl : root + '/mainCard/updateMaincard.action',
			queryObjUrl : root + '/mainCard/getMaincardById.action',
			subHeight:subHeight, 
			subWidth:subWidth
		},
		cache : {
			form : null,
			dialog_001 : null,
			imgUrl:[],
			money : 10000000,
			voucherFileName:null
		},
		init : function(){
			this.initSelectData();
			this.initAddOrEdit();
			this.loadCss();
			this.render();
			this.initPageData();
		},
		//初始化卡类型选择框
		initSelectData:function(){
			CODE_UTIL.getSelectByCodeType($('#ownOrg'), 'CARD_ORG',  180, 80);
			var data = CODE_UTIL.getCodeData('IC-CARD-TYPE');
			data.unshift({'name':'请选择','code':''});
	  		$('#cardType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			autocomplete: true,
	  			onSelected: function (value){
	  				$('#opencardofficename').val('');
	  				$('#opencardofficecode').val('');
	  			} 
	  		});
		},
		//初始化账户编码选择框
		initPageData : function (){
			 var that = this;
			 $('#opencardofficename').ligerComboBox({
	               onBeforeOpen: that._selectStore, 
	               valueFieldID: 'storeText001',autocomplete: true
	         });
			 $('#opencardofficename').ligerGetComboBoxManager();		
		 },
		 //初始化点击发卡机构input框时打开新页面
		 _selectStore:function(){	
			 var that=maincardAdd;
			 var cardTypeCode = $.trim($("#cardType_val").val());
			 if( cardTypeCode && cardTypeCode.length > 0){
				 var url = 'opencardoffice-list.jsp?cardTypeCode='+ cardTypeCode;
				 var title = '发卡机构列表';
				 that.cache.dialog_001 = MALQ_UI.open_button(url, subHeight, subWidth+100, that.f_selectStoreOK, 
						 function(item, dialog){dialog.close();},
						 title);
			 }else{
				 alert("请选择卡类型！");
			 }
			 return false;
		 },
		 //点击发卡机构操作
		 f_selectStoreOK: function (item, dialog){
			 var fn = dialog.frame.opencardofficeList.f_select || dialog.frame.window.opencardofficeList.f_select; 
	         var data = fn();
	         if(data){
	        	 var opencardofficename='';
	        	 var opencardofficecode='';
	        	 var cardAreaCode='';
	        	 var cardAreaName='';
	        	 $(data.ids).each(function(){
	        		 opencardofficename= this.name;
	        		 opencardofficecode= this.code;
	        		 cardAreaCode= this.cardAreaCode;
	        		 cardAreaName = this.cardAreaName;
	        	 });
	        	 $('#opencardofficecode').val(opencardofficecode?opencardofficecode:"");
	        	 $('#opencardofficename').val(opencardofficename?opencardofficename:"");
	        	 $('#cardAreaCode').val(cardAreaCode?cardAreaCode:"");
	        	 $('#cardAreaName').val(cardAreaName?cardAreaName:"");
	        	 dialog.close();
	         }        
	     },
		 
		render : function(){
			var that = this;
		    //初始化页面检验规则
		    $.metadata.setType('attr', 'validate');
	        $('#form1').validate({
	            rules: {
	            	cardNumber:{
	            		required: true
	            	},
	            	cardType:{
	            		required:true
	            	},
	            	opencardofficename:{
	            		required:true
	        		 },
	        		 ownOrg:{
	        			 required:true
	        		 },
	        		 ownOrg_val:{
	        			 required:true
	        		 },
	        		 cardType_val:{
	        			 required : true,
	        		 },
	        		 accountId:{
	        			 required : true,
	        		 }
	        	},
	        	messages: {
	        		tradeAmount:{remote: '不能为空'}
	        	},
	        	errorPlacement: function (lable, element){
	        		CSM_VALIDATE.errorPlacement(lable, element);
		        },
	            success: function (lable){
	            	CSM_VALIDATE.success(lable);
	            },
	            submitHandler: function(){
	                 var temData = {};
	                 var cardNumber = $('#cardNumber').val(); //卡号
	                 var ownOrg = $('#ownOrg_val').val();//所属组织
	                 var cardType = $("#cardType_val").val();
	                 //var openCardArea = $("#openCardArea").val();//所属区域
	                 //var opencardofficetype = $('#opencardofficetype').val();//
	                 var opencardofficename = $('#opencardofficename').val();//发卡机构名称
	                 var opencardofficecode = $("#opencardofficecode").val();//发卡机构编码
	                 var cardAreaCode = $("#cardAreaCode").val();//省市编码
	                 var cardAreaName = $("#cardAreaName").val();//省市名称
	                 var accountId = $("#accountId").val();//省市名称
	                 //var balance = $("#balance").val();
	                 var params = {
			            'model.cardNumber': cardNumber,
		     			'model.cardType': cardType,
		     			'model.opencardofficename':opencardofficename ,
		     			'model.opencardofficecode':opencardofficecode,
		     			'model.cardAreaCode':cardAreaCode,
		     			'model.cardAreaName':cardAreaName,
		     			'model.ownOrg': ownOrg,
		     			'model.accountId': accountId
		         	};
	                 temData['cardNumber']=cardNumber;
	                 temData['ownOrg']=ownOrg;
	                 temData['cardType']=cardType;
	                 //temData['balance']=balance;
	                 temData['opencardofficename']=opencardofficename;
	                 temData['opencardofficecode']=opencardofficecode;
	                 temData['cardAreaCode']=cardAreaCode;
	                 temData['cardAreaName']=cardAreaName;
	                 temData['accountId']=accountId;
	                 
	                 var pageId =parent.window.iCCardMain.cache.subPageId; 
	                 var url;
	                 if(pageId == "add"){
	                	 url = that.config.addUrl;  
	                 }else{
	                	 params['model.id'] = $("#id").val();
	                	 temData['id']=$("#id").val();
	                	 url = that.config.modeifyUrl;
	                 }
	                 
                     JAjax(url, params, 'json', function(data){   
                    	 if(data){    
                    		 var msg = "";
                    		 if(data.message.indexOf("CARD-TYPE-01") > 0){
                    			 msg = "卡号:"+cardNumber+" 卡类型:"+CODE_UTIL.getCodeName('IC-CARD-TYPE',"CARD-TYPE-01") +",已经存在!";
                    		 }else if(data.message.indexOf("CARD-TYPE-02") > 0){
                    			 msg = "卡号:"+cardNumber+" 卡类型:"+CODE_UTIL.getCodeName('IC-CARD-TYPE',"CARD-TYPE-02") +",已经存在!";
                    		 }else{
                    			 msg = data.message;
                    		 }
                    		 parent.window.iCCardMain.alert(msg, 'clew_ico_succ');
                      		 parent.window.iCCardMain.cache.grid.loadData();
                      		 parent.window.iCCardMain.cache.dialog.close();                		
                      	 }else{
                      		 JSWK.clewBox(data,'clew_ico_fail',failShowTime);
                      	 }
     				 }, function(){JSWK.clewBox('提交数据时发生异常','clew_ico_fail',failShowTime);}, true);
	            }
	        });
		},
		f_save:function(){
			$('#form1').submit();
		},
		initAddOrEdit:function(){
			var that = this;
        	var id = parent.window.iCCardMain.cache.id;
        	$("#id").val(id);
		    //取值判断是增加还是修改
	        var pageId =parent.window.iCCardMain.cache.subPageId; 
	        //取值判断是增加还是修改
	        if(pageId == 'edit'){
        	    JAjax(that.config.queryObjUrl,{'model.id':id}, 'json', function(data){
        		   if(data){
					    //$('#balance').val(data.dataObject.balance);//卡余额
        			   $('#cardNumber').attr("readonly","readonly");
        			   $('#cardNumber').val(data.dataObject.cardNumber);//卡号
        			  
					   $('#opencardofficecode').val(data.dataObject.opencardofficecode);//发卡机构code
					   liger.get('cardType').setValue(data.dataObject.cardType);
					   liger.get('ownOrg').setValue(data.dataObject.ownOrg);//所属组织机构
						//liger.get('opencardofficename').setValue(data.dataObject.opencardofficename);//发卡机构
					   var opencardofficename = CODE_UTIL.getCodeName(data.dataObject.cardType,data.dataObject.opencardofficecode);
					   $('#opencardofficename').val(opencardofficename);
					   $('#cardAreaName').val(data.dataObject.cardAreaName);
					   $('#cardAreaCode').val(data.dataObject.cardAreaCode);
					   $('#accountId').val(data.dataObject.accountId);
        		   } 
        	   }, function(){JSWK.clewBox('查询充值数据失败','clew_ico_fail',failShowTime);}, true);  
			}
		 },
		 loadCss : function(){
			$('form').ligerForm();
		 },
		success : function(item, dialog) {
			dialog.frame.window.rechargeUpload.upload();
		},
		cancel : function(item, dialog) {
			dialog.close();
		},
		success_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_succ');
		},
		fail_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_fail', failShowTime);
		},
	};
	
	$(document).ready(function() {
		window.maincardAdd = new MaincardAdd();
	});
})();