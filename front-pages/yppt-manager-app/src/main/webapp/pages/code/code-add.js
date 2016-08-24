/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var CodeAdd = function() {
		this.init();
	};
	CodeAdd.prototype = {
		config : {
			queryObjectUrl : root+'/code/queryObjectById.action',
			submitUrl : null,
			//检查属性是否唯一
			chackerUrl : root+'/code/checkExist.action'
		},
		cache : {
			dialog : null
		},
		init : function(){
			this.initAddOrEdit();
			this.loadCss();
		},
		
		initAddOrEdit:function(){
			var pageId =parent.window.codeList.cache.subPageId;
			var that = this;
			if(pageId == 'add'){
				that.config .submitUrl=root+'/code/add.action';
				$("#typeCode").val(parent.window.codeList.cache.pCode);
				$("#typeName").val(parent.window.codeList.cache.pCodeName);
				$("#typeNameText").text(parent.window.codeList.cache.pCodeName);
				$("#pid").val(parent.window.codeList.cache.pCodeId);
			}else{
				that.config.submitUrl=root+'/code/updateCode.action';
				var id = parent.window.codeList.cache.codeId;
				JAjax(that.config.queryObjectUrl,{'model.id':id}, 'json', function(data){
	        		   if(data){
							$('#name').val(data.name);
							$('#code').val(data.code);
						    $('#description').text(data.description);
						    $('#typeName').val(data.typeName);
						    $('#typeNameText').text(data.typeName);
						    $('#typeCode').val(data.typeCode);	
						    $("#pid").val(data.pid);
						    $('#id').val(data.id); 
	        		   } 
	        	   }, function(){JSWK.clewBox('查询码表对象失败','clew_ico_fail',failShowTime);}, true); 
			}
		 },
		 formSubmit:function(){
			 var that=this;
			 $.ligerDialog.confirm('提交会改变子编码的类型，确定要提交吗？', function(yes) {
				 if(yes){
						var params={};
						params['model.id']=$("#id").val();
						params['model.code']=$("#code").val();
						params['model.name']=$("#name").val();
						params['model.typeCode']=$("#typeCode").val();
						params['model.typeName']=$("#typeName").val();
						params['model.description']=$("#description").val();
						params['model.pid']=$("#pid").val();
						JAjax(that.config.submitUrl,params, 'json', function(data){
							if(data=='0'){
								
							    parent.codeList.search();
								parent.codeList.success_alert('操作成功');
								parent.codeList.cache.dialog.close();
							}else{
								if($("#id").val()==''){
									parent.codeList.search();
									parent.codeList.success_alert('操作成功');
									parent.codeList.cache.dialog.close();
								}else{
									JSWK.clewBox('修改失败','clew_ico_fail',failShowTime);
								}
							}
			        	}, function(){JSWK.clewBox('查询码表对象失败','clew_ico_fail',failShowTime);}, true); 
				 }
				
			 });
		 },
		 loadCss : function(){
			 $('form').ligerForm();
		     $('#name').focus();
		 },
		 reset : function(){
			 window.location='code-add.jsp';
		 }
	};
	
	$(document).ready(function() {
		window.codeAdd = new CodeAdd();
	});
})();