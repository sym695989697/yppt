(function() {
	var ImportIcCardManager = function() {
		this.init();
	};
	
	// *************************************************方法**************************************
	ImportIcCardManager.prototype = {
		config : {
			getProcessUrl:root+'/viceCard/getProcessByKey.action',
			importUrl:null
		},
		cache:{
			processKey:null
		},
		init:function(){
			var that=this;
			if(parent.viceCardList.cache.subPageId=='0'){
				this.config.importUrl = root+'/viceCard/icCardImport.action';
				this.cache.processKey = 'blank_card_import_process_key';
				$("#downLoadModel").attr("href",(root+"/viceCard/downLoadModel.action"));
			}else{
				this.config.importUrl = root+'/viceCard/icCardOpen.action';
				this.cache.processKey = 'open_patch_card_process_key';
				$(".hideTr").hide();
				$("#downLoadModel").attr("href",(root+"/viceCard/downLoadOpenPatchModel.action"));
			}
			
			//卡类型初始化
			var data = CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var towData = [];
			var thridData = [];
	  		data.unshift({'name':'请选择','code':''});
	  		$('#cardType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 120,
	  			width:180,
	  			onSelected: function (value){
	  				towData = CODE_UTIL.getCodeData(value);
	  				towData.unshift({'name':'请选择','code':'-'});
	  				$('#opencardofficecode').ligerGetComboBoxManager().setData(towData);
	  				thridData = [];
	  				thridData.unshift({'cardNumber':'请选择','id':''});
	  				$('#mainCard').ligerGetComboBoxManager().setData(thridData);
	  				
	  			} 
	  		});
	  		//发卡地区
	  		towData.unshift({'name':'请选择','code':'-'});
	  		$('#opencardofficecode').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 120,
	  			width:180,
	  			onSelected: function (value){
	  				var obj = CODE_UTIL.getCode($('#cardType_val').val(),value);
	  				$("#cardAreaCode").val(obj.description);
	  				$("#cardAreaName").val(CODE_UTIL.getZoneNameByCode(obj.description));
	  				thridData = [];
	  				var params={};
	  				params['requestParam.equal.opencardofficecode']=value;
	  	  			$.ajax({
	  		    		   type: "POST",
	  		    		   url:  root+'/viceCard/queryMainCardByCardAreaCode.action',
	  		    		   data:params,
	  		    		   dataType: "json",	    		
	  		    		   cache : false,
	  		        	   async : false,	    		   	    		   
	  		    		   success: function(data){
	  		    			   if(data){
	  		    				 thridData = data;
	  		    			   }
	  		    		   },
	  		    		   error : function(e, s){        			
	  		    			  // $.ligerDialog.alert('!');	
	  		    		   }
	  		    		}); 
	  				thridData.unshift({'cardNumber':'请选择','id':''});
	  				$('#mainCard').ligerGetComboBoxManager().setData(thridData);
	  				
	  			} 
	  		});
	  		//主卡
	  		thridData.unshift({'cardNumber':'请选择','id':''});
	  		$('#mainCard').ligerComboBox({
	  			data: thridData,
	  			textField :"cardNumber",
	  			valueField :"id",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 120,
	  			width:180
	  		});
	  		
	  		//ajaxFrom
	  		
	  		var options = {
					url:that.config.importUrl,
					success:function(data){
						if((data+"").indexOf('0')!=-1){
							window.importIcCardManager.alert_process();
						}else if((data+"").indexOf('1')!=-1){
							JSWK.clewBox('导入失败',
							'clew_ico_fail');
						}else{
							JSWK.clewBox('模版不正确，请重新下载模版',
							'clew_ico_fail');
						}
					}
			};
			$("#f_form").ajaxForm(options);
		},
		validateForm:function(){
			var flag=true;
			var cardType=liger.get("cardType").getValue();
			var opencardofficecode=liger.get("opencardofficecode").getValue();
			var mainCard=liger.get("mainCard").getValue();
			var file=$("#uploadFile").val();
			if(parent.viceCardList.cache.subPageId=='0'){
				if(!cardType){
					JSWK.clewBox('请选择卡类型','clew_ico_fail', failShowTime);
					flag = false;
				}else if(!opencardofficecode){
					JSWK.clewBox('请选择发卡地区','clew_ico_fail', failShowTime);
					flag = false;
				}else if(!mainCard){
					JSWK.clewBox('请选择所属主卡','clew_ico_fail', failShowTime);
					flag = false;
				}
			}
			if(!file||file.length<5||file.substring(file.length-5)!='.xlsx'){
				JSWK.clewBox('请选择导入文件(2007版excel *.xlsx)','clew_ico_fail', failShowTime);
				flag = false;
			}
			return flag;
		},
		f_save : function(){
		  if(!this.validateForm())return ; 
          $("#f_form").submit();
        },
        alert_process : function() {
			var content="<div style=\"width: 250px;height: 10px;border: 1px solid #EE7AE9;\" id=\"allProcess\"><div id=\"indexProcess\" style=\"background-color: #EE7AE9;height: 10px;width:0px;\"></div></div>";
			$.ligerDialog.waittingProcess('进度',content);
			$(".l-dialog-title").html("0%");
			this.checkProgress();
		},
		checkProgress:function(){
			var that=this;
			JAjax(that.config.getProcessUrl,{"processKey":that.cache.processKey}, 'json', function(data) {
				if(isNaN(data)){
					$.ligerDialog.closeWaittingProcess();
					parent.viceCardList.alert_message(data);
					
				}else{
					$("#indexProcess").css("width",(250*(Math.round(data)/100)));
					$(".l-dialog-title").html(Math.round(data*100)/100+"%");
					setTimeout("window.importIcCardManager.checkProgress()",1500);
				}
			}, function() {
				JSWK.clewBox('提交数据时发生异常',
						'clew_ico_fail', failShowTime);
			}, true);
		}
	};
	
	$(function() {
		var importIcCardManager = new ImportIcCardManager();
		window.importIcCardManager = importIcCardManager;
	});

})();
