/**
 * IC卡副卡修改
 */
(function() {
	var ViceCardAdd = function() {
		this.init();
	};
	ViceCardAdd.prototype = {
		config : {
//			addUrl : root + '/viceCard/updateViceCard.action', 
			modeifyUrl : root + '/viceCard/updateViceCard.action',
			queryObjUrl : root + '/viceCard/queryViceCardById.action'
//			uploadImage : root + '/viceCard/uploadImage.action'
		},
		cache : {			
			dialog_001 : null,
			form : null,
			tags : []
		},
		init :function(){
			this.initHtml();
			this.initLayoutFrom();
			this.initFormData();
			this.registShowBigImage();
		},                 
		initHtml : function(){			
			var html='';
			html+='<div id="table_detailDiv" class="l-table-edit" style="margin-top: 10px"><form id="form1" enctype="multipart/form-data" method="post">';
			html+='			 <div id="viceCardAddform"></div> <br />';
			html+='';
			html+='</form></div>';
			$('#layout').html(html);
		},
		initLayoutFrom : function(){
			var isMessageCode=[];
			var isSends = CODE_UTIL.getCodeData('IS-SEND-MESSAGE');
			$(isSends).each(function(){
				var isSend={};
				isSend['text']=this.name;
				isSend['id']=this.code;
				isMessageCode.push(isSend);
			});
			var vecheNoColors=[];
			var colors = CODE_UTIL.getCodeData('V-C-COLOR');
			$(colors).each(function(){
				var color={};
				color['text']=this.name;
				color['id']=this.code;
				vecheNoColors.push(color);
			});
			var that = this;
			var fields =[
							{ name: 'model.id', type: 'hidden'},
							{ label: '卡号', name: 'model.cardNumber', type: 'text'},
							{ label: '所属主卡', name: 'model.parentCardNumber',newline:false, type: 'text'},
							{ label: '卡类型', name: 'model.cardType',  type: 'text'},
							{ label: '绑定手机号', name: 'model.telephoneNumber',attr:{regExp:'^[1][0-9]{10}$',message:'请输入正确的手机号码'}, newline: false, type: 'text' },
							{ label: '发卡地区', name: 'model.opencardofficeid',  type: 'text'},
							{ label: '会员名称', name: 'model.userName', newline: false, type: 'text'}, //暂无匹配字段
							{ label: '是否接收短信', name: 'messageOrNot',type: 'combobox' ,comboboxName: "messageOrNot", options: {data:isMessageCode}},
							{ label: '绑定车牌号', name: 'model.vehicleNo',validate: {required:true},attr:{regExp:'^[\u4e00-\u9fa5]{1}[A-Z0-9]{6,7}$',message:'1个中文+6~7位数字大写字母组合'},newline:false, type: 'text'},
							{ label: '余额', name: 'model.balance', type: 'text'}, //暂无匹配字段
							{ label: '车牌颜色', newline: false,name: 'vehicleColor',type: 'combobox' ,comboboxName: "vehicleColor", options: {data:vecheNoColors}},
							{ label: '注册手机号', name: 'model.userRegPhone', type: 'text'},
							{ label: '开卡时间', name: 'createdTime', newline: false, type: 'text'}
			             ];
				that.cache.form = $('#viceCardAddform').ligerForm({    
				labelCss: 'span_notNull',
	            fieldCss: 'l-table-edit-td',
	            inputWidth: 180,
	            labelWidth: 100,
	            fields: fields,
	            space: 40,
	            validate:false//停用ligerform验证
	      	});
				var form = liger.get("viceCardAddform");
				 
			CSM_VALIDATE.initValidate();//初始化自定义验证
		},
		
		initFormData : function(){
			var that=this;
			$("#viceCardAddform").append('<ul><li style="text-align: left; width: 100px;">行驶证:</li><li style="text-align: left; width: 180px;"><div id="imageDiv" style="overflow:hidden;width:120px;text-align:center;padding:10px;"><a href="#" id="imageLink" target="_blank"><img src="" style="border:3px solid #eeeeee;" id="imageId"/></a></div><div id="enlarge_images" style="position:absolute;display:none;z-index:2;"></div></li><li style="width: 40px;"/><li style="text-align: left; width: 100px;display:none;" class="reupload">重新上传:</li><li style="text-align: left; width: 180px;display:none;"  class="reupload"><input type="file" name="uploadFile" style="width:180px;"/></li></ul>');
			var options = {
					url:that.config.modeifyUrl,
					success:function(data){
						parent.viceCardList.refreshGrid();
						parent.viceCardList.alert('操作成功',
						'clew_ico_succ');
						parent.viceCardList.cache.dialog.close();
					}
			};
			$("#form1").ajaxForm(options);
			//$('.l-text-field').attr("readonly","readonly");
			var that = this;
			
				var id = parent.viceCardList.cache.editId;
				JAjax(that.config.queryObjUrl, {'model.id':id}, 'json', function(data){
					if(data){
						$("input[name='model.id']").val(data.id);
						liger.get('model.cardNumber').setValue(data.cardNumber);
						liger.get('model.cardNumber')._setReadonly(true);
						liger.get('model.parentCardNumber').setValue(data.parentCardNumber);
						liger.get('model.parentCardNumber')._setReadonly(true);
						liger.get('model.cardType').setValue(CODE_UTIL.getCodeName('IC-CARD-TYPE',data.cardType));
						liger.get('model.cardType')._setReadonly(true);
						liger.get('messageOrNot').setValue(data.messageOrNot);
						liger.get('vehicleColor').setValue(data.vehicleColor);
						liger.get('model.telephoneNumber').setValue(data.telephoneNumber);
						liger.get('model.telephoneNumber')._setReadonly(true);
						liger.get('model.opencardofficeid').setValue(CODE_UTIL.getCodeName(data.cardType,data.opencardofficeid));
						liger.get('model.opencardofficeid')._setReadonly(true);
						liger.get('model.vehicleNo').setValue(data.vehicleNo);
						liger.get('model.vehicleNo')._setReadonly(true);
						liger.get('model.userRegPhone').setValue(data.userRegPhone);
						liger.get('model.userRegPhone')._setReadonly(true);
						var bls='0.00';
						if(data.balance){
							bls=data.balance/100+"";
							if(bls.indexOf(".")==-1){
								bls+=".00";
							}else if(bls.indexOf(".")==bls.length-2){
								bls+="0";
							}
						}
						liger.get('model.balance').setValue(bls);
						liger.get('model.balance')._setReadonly(true);
						liger.get('createdTime').setValue(new Date(data.createdTime).format("yyyy-MM-dd hh:mm:ss"));
						liger.get('createdTime')._setReadonly(true);
						liger.get('model.userName').setValue(data.userName);
						liger.get('model.userName')._setReadonly(true);
						if(data.vehicleLicense){
							$("#imageId").attr("src",data.vehicleLicense);
						}else{
							$("#imageId").hide();
						}
						$("#imageLink").attr("href",data.vehicleLicense.replace('min_',''));
						if(parent.window.viceCardList.cache.subPageId == "edit"){
							liger.get('model.vehicleNo')._setReadonly(false);
							liger.get('model.telephoneNumber')._setReadonly(false);
							$(".reupload").show();
						}else{
							liger.get('messageOrNot')._setReadonly(true);
							liger.get('messageOrNot').setDisabled(true);
							$('#messageOrNot').css("color","#000");
							liger.get('vehicleColor')._setReadonly(true);
							liger.get('vehicleColor').setDisabled(true);
							$('#vehicleColor').css("color","#000");
							$(".l-trigger").hide();
							
						}
						$(".l-text-field[readOnly='readOnly']").focus(function(){
							$(this).blur();
						});
					}						
				},function(){JSWK.clewBox('查询卡详情时异常','clew_ico_fail',failShowTime);}, true);
		},
		
		f_save : function(){
			if(! CSM_VALIDATE.validateForm()) return ;//自定义验证
			$("#form1").submit();
		},
		registShowBigImage:function(){
			var demo = document.getElementById("imageDiv");
			var gg = demo.getElementsByTagName("img");
			var ei = document.getElementById("enlarge_images");
			for(i=0; i<gg.length; i++){
				var ts = gg[i];
				ts.onmouseover = function(event){
					event = event || window.event;
					ei.style.display = "block";
					ei.innerHTML = '<img src="' + this.src.replace('min_','') + '"  style="width:400px;height:300px;"/></a>';
					ei.style.top  = document.body.scrollTop + /*event.clientY - 10*/30 + "px";
					ei.style.left = document.body.scrollLeft + event.clientX + 10 + "px";
				};
				ts.onmouseout = function(){
					ei.innerHTML = "";
					ei.style.display = "none";
				};
			}

		}
	};
	$(document).ready(function() {
		window.viceCardAdd = new ViceCardAdd();
	});
})();