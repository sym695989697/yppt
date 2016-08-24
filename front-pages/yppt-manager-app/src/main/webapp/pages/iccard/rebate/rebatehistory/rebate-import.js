(function() {
	var ImportRebateManager = function() {
		this.init();
	};
	
	// *************************************************方法**************************************
	ImportRebateManager.prototype = {
		config : {
			getProcessUrl:root+'/viceCard/getProcessByKey.action',
			importUrl:root+'/rebateHistory/rebateImport.action'
		},
		cache:{
			processKey:'rebate_history_import_process_key'
		},
		init:function(){
			var that=this;
			//发卡地区
			var cardTypes=CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var areas=[];
			$(cardTypes).each(function(){
				var temp = CODE_UTIL.getCodeData(this.code);
				$(temp).each(function(){
					areas.push(this);
				});
			});
			$('#opencardofficecode').ligerComboBox({
	  			data: areas,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140
	  		});
			//日期
//			$("#dateYearAndMonth").ligerDateEditor({showTime: false,format:"yyyy-MM",width:140});
			//模版下载
			$("#downLoadModel").attr("href",(root+"/rebateHistory/downLoadModel.action"));
			
			
			//ajaxFrom
	  		var options = {
					url:that.config.importUrl,
					success:function(data){
						if((data+"").indexOf('0')!=-1){
							window.importRebateManager.alert_process();
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
			var opencardofficecode=liger.get("opencardofficecode").getValue();
			var yeayAndMonth=$("#dateYearAndMonth").val();
			var file=$("#uploadFile").val();
				if(!opencardofficecode){
					JSWK.clewBox('请选择发卡地区','clew_ico_fail', failShowTime);
					flag = false;
				}else if(!yeayAndMonth){
					JSWK.clewBox('请选择账单期','clew_ico_fail', failShowTime);
					flag = false;
				}else if(!file||file.length<5||file.substring(file.length-5)!='.xlsx'){
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
					parent.rebateHistoryList.alert_message(data);
					
				}else{
					$("#indexProcess").css("width",(250*(Math.round(data)/100)));
					$(".l-dialog-title").html(Math.round(data*100)/100+"%");
					setTimeout("window.importRebateManager.checkProgress()",1500);
				}
			}, function() {
				JSWK.clewBox('提交数据时发生异常',
						'clew_ico_fail', failShowTime);
			}, true);
		}
	};
	
	$(function() {
		var importRebateManager = new ImportRebateManager();
		window.importRebateManager = importRebateManager;
	});

})();
