/**
 * 添加账户信息
 */
(function() {
	$(document).ready(function() {
		window.catchdataAdd = new CatchdataAdd();
	});
	var CatchdataAdd = function() {
		this.init();
	};
	CatchdataAdd.prototype = {
		//配置信息
		config : {
			addUrl : root + '/catchdata/addAccount.action',
			queryById:root+"/catchdata/queryCatchdataUserById.action",
			updateUrl:root+"/catchdata/updateAccount.action"
		},
		
		//缓存数据
		cache : {			
			dialog_add : null,
			form : null,
			tags : []
		},
		
		//初始化方法
		init :function(){
			this.initHtml();
			this.initLayoutFrom();
			this.initFormData();
			this.init2();
		},
		
		init2:function(){
			var that=this;
			var data = CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var towData = [];
			data.unshift({'name':'请选择','code':'-'});
			$('#accounttype').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 180,
	  			width:180,
	  			onSelected: function (value){
	  				towData = CODE_UTIL.getCodeData(value);
	  				towData.unshift({'name':'请选择','code':'-'});
	  				$('#cardAreaCode').ligerGetComboBoxManager().setData(towData);
	  			} 
	  		});
			//发卡地区
	  		towData.unshift({'name':'请选择','code':'-'});
	  		$('#cardAreaCode').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  		});
		},
		
		
		//初始化表单
		initHtml : function(){			
			var html='';
			html+='<div id="table_detailDiv" class="l-table-edit" style="margin-top: 10px"><form id="addform">';
			html+='			 <div id="cachdataAddform"></div>';
			html+='</form></div>';
			$('#layout').html(html);
		},
		
		//生成 ligui下拉框选择所需值，返回{data:[ { text: '请选择', id: '' }]}类型
		creatAccountType:function(type){
			var data = CODE_UTIL.getCodeData(type);
			var result={data:[ { text: '请选择', id: '' }]};
			data.forEach(function(value, index, array) {
				result.data.push({text:value.name,id:value.code});
			});
			return result;
		},
		//初始化表单内字段
		initLayoutFrom : function(){
			var that = this;
			var fields =[
							{ name: 'id', type: 'hidden'},
							{ label: '用户名', name: 'username', type: 'text',validate:{required:true }},
							{ label: '密码', name: 'password',newline: false, type: 'password',validate:{required:true }},
							{label:'通知手机号',name:'mobile',newline: true, type: 'text'},
							{label:'网站类型',name:'accounttype',newline: true,type: 'combobox' ,comboboxName: "accounttype",options:this.creatAccountType("IC-CARD-TYPE")},
							{label:'所属组织',name:'ownOrg',newline: false,type: 'combobox' ,comboboxName: "ownOrg",options:this.creatAccountType("CARD_ORG")},
							{label:'发卡地区',name:'cardAreaCode',newline: true,type: 'combobox' ,comboboxName: "cardAreaCode",options:{data:[ { text: '请选择', id: '' }]}}
			             ];
			that.cache.form = $('#cachdataAddform').ligerForm({ 
	            labelCss: 'span_notNull',
	            fieldCss: 'l-table-edit-td',
	            inputWidth: 180,
	            labelWidth: 100,
	            fields: fields,
	            space: 40,
	            validate:false//停用ligerform验证
	      	});
		},
		
		//界面显示  如果是修改回显数据
		initFormData : function(){
			var subPageId=parent.window.catchdataList.cache.subPageId;
			var id = parent.window.catchdataList.cache.id;
			if("edit"==subPageId){
				  JAjax(this.config.queryById,{'model.id':id}, 'json', function(data){
	        		 //回显
						$("input[name='id']").val(data.id);
						liger.get('username').setValue(data.usename);
						liger.get('password').setValue(data.password);
						liger.get('mobile').setValue(data.mobile);
						liger.get('accounttype').setValue(data.accounttype);
						liger.get('ownOrg').setValue(data.ownOrg);
						liger.get('cardAreaCode').setValue(data.cardAreaCode);
	        	   }, function(){JSWK.clewBox('查询充值数据失败','clew_ico_fail',failShowTime);}, true);  
			}else{
				console.info("添加数据，不做处理");
			}
		},
		
		//提交表单数据操作
		f_save : function(){
			var subPageId=parent.window.catchdataList.cache.subPageId;
			var submitUrl="";
			if("edit"==subPageId){
				submitUrl =this.config.updateUrl;
			}else{
				submitUrl=this.config.addUrl;
			}
			var id = $("#id").val()
			var userName=$("#username").val();
			var passaWord = $("#password").val();
			var mobile = $("#mobile").val();
			var accountType=$("#accounttype_val").val();
			var ownOrg = $("#ownOrg_val").val();
			var cardAreaCode = $("#cardAreaCode_val").val();
			var data={
					"model.usename":userName,
					"model.password":passaWord,
					"model.mobile":mobile,
					"model.accounttype":accountType,
					"model.ownOrg":ownOrg,
					"model.cardAreaCode":cardAreaCode,
					"model.id":id
				};
			JAjax(submitUrl,data, 'json', function(success){
				parent.window.catchdataList.cache.grid.loadData(true);
				parent.window.catchdataList.cache.dialog.close();
			},function(){JSWK.clewBox('添加用户信息异常','clew_ico_fail',failShowTime);}, true);
		}
		
	};
})();