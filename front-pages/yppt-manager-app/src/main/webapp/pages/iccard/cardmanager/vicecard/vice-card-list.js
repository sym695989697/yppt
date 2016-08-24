/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var ViceCardList = function() {
		this.init();
	};
	ViceCardList.prototype = {
		config : {
			queryUrl : root + '/viceCard/queryViceCardListPage.action',
			getProcessUrl : root + '/viceCard/getProcessByKey.action',
			delUrl : root + '/viceCard/deleteViceCard.action',
			updateBlanceUrl:root+'/viceCard/updateBlance.action'
		},
		cache : {
			subPageId : null,
			grid : null,
			dialog : null,
			accordion : null,
			tree : null,
			tab : null,
			editId : null,
			showId : null,
			searchParms : {},
			alert_message:null
		},
		init : function() {
			this.render();
			this.loadGird();
			CODE_UTIL._initCodeData();
			this.loadSimpleCode();
		},
		loadSimpleCode:function(){
			//卡类型初始化
			var data = CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var towData = [];
			var thridData = [];
	  		data.unshift({'name':'请选择','code':''});
	  		$('#cardType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140,
	  			onSelected: function (value){
	  				towData = CODE_UTIL.getCodeData(value);
	  				towData.unshift({'name':'请选择','code':''});
	  				$('#opencardofficecode').ligerGetComboBoxManager().setData(towData);
	  				liger.get('opencardofficecode').setValue('');
	  				thridData = [];
	  				thridData.unshift({'cardNumber':'请选择','id':''});
	  				$('#mainCard').ligerGetComboBoxManager().setData(thridData);
	  				liger.get('mainCard').setValue('');
	  				
	  			} 
	  		});
	  		//发卡地区
	  		towData.unshift({'name':'请选择','code':''});
	  		$('#opencardofficecode').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140,
	  			onSelected: function (value){
	  				thridData = [];
	  				var params={};
	  				if(value==''){
	  					value="-";
	  				}
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
	  				liger.get('mainCard').setValue('');
	  				
	  			} 
	  		});
	  		//主卡
	  		thridData.unshift({'cardNumber':'请选择','id':''});
	  		$('#mainCard').ligerComboBox({
	  			data: thridData,
	  			textField :"cardNumber",
	  			valueField :"id",
	  			selectBoxWidth: 150,
	  			selectBoxHeight: 180,
	  			width:150
	  		});
	  		//数据来源
	  		var dataSources = CODE_UTIL.getCodeData('CARD-DATASOURCE');
	  		dataSources.unshift({'name':'请选择','code':''});
	  		$('#dataSource').ligerComboBox({
	  			data: dataSources,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 150,
	  			selectBoxHeight: 180,
	  			width:150
	  		});
	  		//是否绑定
	  		$('#isBanding').ligerComboBox({
	  			data: [{name:"请选择",code:""},{name:"已绑定",code:"1"},{name:'未绑定',code:'0'}],
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 150,
	  			selectBoxHeight: 180,
	  			width:150
	  		});
		},
		render : function() {
			var that = this;

			// 布局
			$('#layout1').ligerLayout({
				leftWidth : '210',
				allowLeftResize : false,
				height : '100%',
				heightDiff : -1,
				space : 4,
				onHeightChanged : that._heightChanged
			});
			$('.l-layout-header').show();

			$('.l-layout-header-toggle').css('z-index', '2');

			// $(window).scrollTop() + $(window).height()

		},

		// 高度改变
		_heightChanged : function(options) {
			var that = window.viceCardList;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},

		loadGird : function() {
			var that = this;
			// 生成表格上面的按钮菜单///////////////////////////////
			var myitems = [];
			var funItems = FUNCTION_UTIL.getPageFunction(
					window.frameElement.id, [ 'import-vice-card',
							'open-patch-card' ]);
			$(funItems).each(function() {
				myitems.push({
					line : true
				});
				var item = {};
				var click = null;
				if ('import-vice-card' == this.permId) {
					click = that.importblankcard;
				}
				if('open-patch-card' == this.permId){
					click = that.openpatchcard;
				}
				item['text'] = this.text;
				item['click'] = click;
				item['icon'] = this.icon;
				myitems.push(item);
			});

			// 获取操作列功能权限
			funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, [
					'update-vice-card', 'delete-vice-card','vice-card-blance-update' ]);
			var options = {};
			var columns = [
					{
						display : '卡号',
						name : 'cardNumber',
						align : 'center',
						width : '10%',
						isSort : true,
						render : function(row) {
							 return '<a href="javascript:window.viceCardList.showOrgDetail(\''+ row.id+ '\');">'
							 + row.cardNumber + '</a>';
						}
					},
					{
						//所属主卡赞无字段对应
						display : '所属主卡',
						name : 'parentCardNumber',
						align : 'center',
						width : '10%',
						isSort : true
					},
					{
						display : '车牌号',
						name : 'vehicleNo',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.vehicleNo){
								var color=CODE_UTIL.getCodeName('V-C-COLOR',row.vehicleColor);
								if(color){
									return row.vehicleNo+"-"+color;
								}
								return row.vehicleNo;
							}
							else{
								return "";
							}
						}
					},
					{
						display : '卡类型',
						name : 'cardType',
						align : 'center',
						width : '8%',
						isSort : true,
						render:function(row){
							if(row.cardType)
								return CODE_UTIL.getCodeName('IC-CARD-TYPE',row.cardType);
							else
								return "";
						}
					},
					{
						display : '发卡地区',
						name : 'opencardofficeid',
						align : 'center',
						width : '10%',
						isSort : false,
						render:function(row){
							if(row.opencardofficeid)
								return CODE_UTIL.getCodeName(row.cardType,row.opencardofficeid);
							else
								return "";
						}
					},
					{//暂无对应字段
						display : '会员名称',
						name : 'userName',
						align : 'center',
						width : '10%',
						isSort : false
					},
					{
						display : '注册手机号',
						name : 'userRegPhone',
						align : 'center',
						width : '8%',
						isSort : true
					 
					},
					{
						display : '开卡时间',
						name : 'openCardTime',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.openCardTime)
								return new Date(row.openCardTime).format("yyyy-MM-dd hh:mm:ss");
							else
								return "";
								//return "yyyy-MM-dd --:--:--";
						}
					},
					{
						display : '卡余额',
						name : 'balance',
						align : 'center',
						width : '6%',
						isSort : true,
						render:function(row){
							if(row.balance){
								var bls=row.balance/100+"";
								if(bls.indexOf(".")==-1){
									bls+=".00";
								}else if(bls.indexOf(".")==bls.length-2){
									bls+="0";
								}
								return bls;
							}
							else
								return "--";
						}
					},
//					{
//						display : '余额更新时间',
//						name : 'balanceModifiedTime',
//						align : 'center',
//						width : '10%',
//						isSort : true,
//						render:function(row){
//							if(row.balanceModifiedTime)
//								return new Date(row.balanceModifiedTime).format("yyyy-MM-dd hh:mm:ss");
//							else
//								return "";
//						}
//					},
					{
						display : '数据来源',
						name : 'dataSource',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.dataSource)
								return CODE_UTIL.getCodeName("CARD-DATASOURCE",row.dataSource);
							else
								return "";
						}
					},
					{//暂无对应字段
						display : '操作人',
						name : 'modifinguser',
						align : 'center',
						width : '6%',
						isSort : false
					},
					{
						display : '操作',
						name:'opt',
						align : 'center',
						width : '10%',
						isSort : false,
						render : function(row) {
							var str = '';
							// 通过权限唯一标示生成操作列按钮
							$(funItems)
									.each(
											function() {
												if ('update-vice-card' == this.permId && row.userId!=null &&row.userId!='') {
													str += '<a href="javascript:window.viceCardList.modify(\''
															+ row.id
															+ '\')">修改</a>&nbsp;&nbsp;';
												}
//												if ('vice-card-blance-update' == this.permId) {
//													str += '<a href="javascript:window.viceCardList.updateBlance(\''
//															+ row.id
//															+ '\')">刷新余额</a>&nbsp;&nbsp;';
//												}
//												if ('update-vice-card' == this.permId) {
//													str += '<a href="javascript:window.viceCardList.del(\''
//															+ row.id
//															+ '\')">删除</a>&nbsp;&nbsp;';
//												}
											});
							return str;
						}
					} ];

			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			// options['sortName'] = 'code';
			options['parms'] = []; // 设置默认进来的请求参数
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单
			options['usePager'] = true;
			MALQ_UI.setGridOptions(options, that.config.queryUrl);

			$('#viceCardList').ligerGrid(options);
			that.cache.grid = $('#viceCardList').ligerGetGridManager();
			$('#viceCardList').css('top', '3px');
			$('#pageloading').hide();// 加载图片
		},

		// 刷新Grid内容
		refreshGrid : function() {
			var that = window.viceCardList;
			that.cache.grid.loadData(true);
		},
		// 一般查询
		search : function() {
			var that = this;
			var userRegPhone = $("#userRegPhone").val();//注册手机号
			var parentId = $("#mainCard_val").val(); //所属主卡
			var opencardofficeid = $("#opencardofficecode_val").val(); //发卡地区
			var vehicleNo = $("#vehicleNo").val(); //车牌号
			var memberName = $("#memberName").val(); //会员名称
			var cardNumber = $("#cardNumber").val(); //卡号
			var cardType = $("#cardType_val").val(); //发卡类型
			var dataSource = $("#dataSource_val").val(); //数据来源
			var isBanding = $("#isBanding_val").val(); //数据来源
			var parms = {};
			if(userRegPhone){
				parms['requestParam.like.userRegPhone'] ="%"+userRegPhone+"%";
			}
			if(parentId){
				parms['requestParam.equal.parentId'] = parentId;
			}
			if(vehicleNo){
				parms['requestParam.like.vehicleNo']="%"+vehicleNo+"%";
			}
			if(memberName){
				parms['requestParam.like.userName']="%"+memberName+"%";
			}
			if(cardNumber){
				parms['requestParam.like.cardNumber']="%"+cardNumber+"%";
			}
			if(opencardofficeid){
				parms['requestParam.equal.opencardofficeid']=opencardofficeid;
			}
			if(cardType){
				parms['requestParam.equal.cardType']=cardType;
			}
			if(dataSource){
				parms['requestParam.equal.dataSource']=dataSource;
			}
			if(isBanding){
				parms['requestParam.equal.isBanding']=isBanding;
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},	

		successModify : function(item, dialog) {
			dialog.frame.window.viceCardAdd.f_save();
		},
		successShow : function(item, dialog) {
			dialog.close();
		},
		importSuccess : function(item, dialog) {
			dialog.frame.window.importIcCardManager.f_save();
		},
		cancel : function(item, dialog) {
			dialog.close();
		},
		// 在修改页面显示要修改的记录的信息
		modify : function(id) {
			var that = this;
			that.cache.subPageId = 'edit';
			that.cache.editId = id;
			var title = 'IC卡信息修改';
			var url = 'vice-card-add.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, 400, 1000,that.successModify, that.cancel, title);
		},
		//导入空卡
		importblankcard : function(){
			window.viceCardList.cache.subPageId = '0';
			var title = '空卡导入';
			var url = 'vice-card-import.jsp';
			window.viceCardList.cache.dialog = MALQ_UI.open_button(url, 350, 1000,window.viceCardList.importSuccess, window.viceCardList.cancel, title);
		},
		//批量开卡
		openpatchcard : function(){
			window.viceCardList.cache.subPageId = '1';
			var title = '批量开卡';
			var url = 'vice-card-import.jsp';
			window.viceCardList.cache.dialog = MALQ_UI.open_button(url, 300, 1000,window.viceCardList.importSuccess, window.viceCardList.cancel, title);
		},
		//卡详情展示
		showOrgDetail : function(id){
			var that = this;
			that.cache.subPageId = 'show';
			that.cache.editId = id;
			var title = 'IC详情展示';
			var url = 'vice-card-add.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, 300, 1000,window.viceCardList.successShow, window.viceCardList.cancel, title);
		},
		//刷新余额
		updateBlance : function(id){
			var that=this;
			JAjax(that.config.updateBlanceUrl,{"model.id":id}, 'json', function(data) {
				if(data && data=='0'){
					that.search();
					that.alert('操作成功',
					'clew_ico_succ');
				}else{
					JSWK.clewBox('刷新余额异常',
							'clew_ico_fail', failShowTime);
				}
			}, function() {
				JSWK.clewBox('提交数据时发生异常',
						'clew_ico_fail', failShowTime);
			}, true);
		},
		
		// 删除组织结构信息
		del : function(id, text) {
			var that = this;
			$.ligerDialog.confirm(text ? text : '删除的币种不可恢复，是否继续？',
					function(yes) {
						if (yes) {
							JAjax(that.config.delUrl, {
								'ids' : id
							}, 'json',
									function(data) {
										if (data) {
											if (data == -10) {
												JSWK.clewBox('没有操作权限',
														'clew_ico_fail');
												return;
											}
											if (data.message == 0) {
												JSWK.clewBox('删除成功！',
														'clew_ico_succ');
												that.refreshGrid();
											} else if (data.message == -1) {
												JSWK.clewBox('币种已被删除！',
														'clew_ico_fail');
												that.refreshGrid();
											} else if (data.message == -700) {
												JSWK.clewBox('币种已被使用，不允许删除！',
														'clew_ico_fail');
												// that.refreshGrid();
											} else {
												JSWK.clewBox('删除失败！',
														'clew_ico_fail',
														failShowTime);
											}
										} else {
											JSWK.clewBox('删除失败！',
													'clew_ico_fail',
													failShowTime);
										}
									}, function() {
										JSWK.clewBox('提交数据时发生异常',
												'clew_ico_fail', failShowTime);
									}, true);
						}
					});
		},
		resetForm:function(){
			$("#queryForm").find("input[type='text']").val('');
			$("#queryForm").find("input[type='hidden']").val('');
		},
		alert : function(mes, css) {
			JSWK.clewBox(mes, css, failShowTime);
		},
		alert_message : function(mes) {
			var that = this;
			that.refreshGrid();
			that.cache.dialog.close();
			that.cache.alert_message = mes;
			var title = '导入结果';
			var url = 'import-result.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, 300, 1000,window.viceCardList.successShow, window.viceCardList.cancel, title);
		}
	};

	$(document).ready(function() {
		window.viceCardList = new ViceCardList();
	});

})();
