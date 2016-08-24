(function() {
	var ICCardMain = function() {
		this.init();
	};
	ICCardMain.prototype = {
		config : {
			queryGridUrl : root+'/mainCard/getMainCardListPage.action',
			deleteUrl: root+'/mainCard/deleteMaincard.action',
			refreshUrl: root+'/mainCard/refreshBalance.action',
			subHeight : subHeight,
			subWidth : subWidth
		},
		cache : {
			subPageId : null,
			grid : null,
			dialog : null,
			accordion : null,
			tab : null,
			editId : null,
			id : null,
			parentId:null
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
	  		data.unshift({'name':'请选择','code':''});
	  		$('#cardType_query').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 120,
	  			selectBoxHeight: 180,
	  			width:120,
	  			onSelected: function (value){
	  				towData = CODE_UTIL.getCodeData(value);
	  				towData.unshift({'name':'请选择','code':''});
	  				$('#opencardofficecode_query').ligerGetComboBoxManager().setData(towData);
	  			} 
	  		});
	  		//发卡地区
	  		towData.unshift({'name':'请选择','code':''});
	  		$('#opencardofficecode_query').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 120,
	  			selectBoxHeight: 180,
	  			width:120,
	  			onSelected: function (value){
	  			} 
	  		});
	  		CODE_UTIL.getSelectByCodeType($('#ownOrg_query'), 'CARD_ORG',  180, 80);
	  		
	  		$('#cardNumber_query').val('');
			$('#cardType_query_val').val('');
			$('#opencardofficecode_query_val').val('');
			$('#ownOrg_query_val').val('');
			$('#cardType_query').val('');
			$('#opencardofficecode_query').val('');
			$('#ownOrg_query').val('');
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
		},
		// 高度改变
		_heightChanged : function(options) {
			var that = window.iCCardMain;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},
		loadGird : function() {
			var that = this;
			var myitems = [];
			//var funItems = MALQ_FUNCTION.getPageFunction(window.frameElement.id, [ 'com.tyzfpt.upp.App.code.add' ]);
			
			var myitems = [];
	   		 var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id,['iccardmain-add','iccardmain-delete']);
	   		 $(funItems).each(function(){  
	   				myitems.push({line:true});
	   				var item = {};
	   				var click = null;
	   				if('iccardmain-add'==this.permId){
	   					click = that.add;
	   				}
	   				if('iccardmain-delete'==this.permId){
	   					click = that.del;
	   				}
	   				item['text']=this.text;
	   				item['click']=click;
	   				item['icon']=this.icon;
	   				myitems.push(item);
	   			});

			// 获取操作列功能权限
			funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id,['iccardmain-update','iccardmain-refresh']);
			var options = {};
			var columns = [ 
			                {display : '卡ID',name : 'id',hide: true,align : 'center'},
			                {display : '卡号',name : 'cardNumber',width : '10%',align : 'center'},
			                {display : '所属组织',name : 'ownOrg',width : '10%',align : 'center',
			                	render:function(row){
			                		if(row.ownOrg)
			                			return CODE_UTIL.getCodeName('CARD_ORG',row.ownOrg);
									else
										return "";
			                	}
			                },
			                {display : '卡类型',name : 'cardType',width : '15%',align : 'center',
			                	render : function(row) {
			                		if(row.cardType)
			                			return CODE_UTIL.getCodeName('IC-CARD-TYPE',row.cardType);
									else
										return "";
			    				}
			                },
			                {display : '发卡地区',name : 'opencardofficecode',width : '15%',align : 'center',
		        			render : function(row) {
		        				var opencardofficecode = row.opencardofficecode;
		        				return CODE_UTIL.getCodeName(row.cardType, String(opencardofficecode));
		        			}},
			                {display : '副卡数量',name : 'childCardNum',width : '8%',align : 'center',
			                	render : function(row) {
									 return '<a href="javascript:window.iCCardMain.showViceCard(\''+ row.id+ '\');">'
									 + row.childCardNum + '</a>';
								}
			                },
			                {display : '开卡时间',name : 'createdTime',width : '15%',align : 'center',isSort : true,
			                	render : function(row) {
			    					return  getSmpFormatDateByLong(row.createdTime,true);
			    				}
			                },
			                {display : '备付金余额',name : 'balance',width : '8%',align : 'right',
			                	render : function(row) {
			                		if(row.balance){
			                			return convertFen2Yuan(row.balance);
			                		}else{
			                			return "--";
			                		}
			    				}
			                },
			                {display : '余额更新时间',name : 'balanceModifiedTime',width : '15%',align : 'center',
			                	render : function(row) {
			    					return  getSmpFormatDateByLong(row.createdTime,true);
			    				}
			                },
			                {display : '对应账户',name : 'accountId',width : '12%',align : 'center'},
			                {display : '操作人',name : 'modifinguser',width : '10%',align : 'center'},
			                {display : '操作',align : 'center',width : '20%',isSort : false,
								render : function(row) {
									var str = '';
									$(funItems).each(function() {
										if ('iccardmain-update' == this.permId)
											str += '<a href="javascript:window.iCCardMain.modify(\'' + row.id + '\')">修改</a>&nbsp;&nbsp;';
										if ('iccardmain-refresh' == this.permId)
											str += '<a href="javascript:window.iCCardMain.refresh(\'' + row.id + '\')">余额刷新</a>&nbsp;&nbsp;';
									});
									return str;
								}
							} 
			                ];
			options['enabledEdit'] = true;
			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			options['sortName'] = 'createTime';
			options['sortOrder'] = 'desc';
			options['rownumbers'] = true;
			options['frozenRownumbers'] = true;
			options['parms'] = [ {// 默认不查中交的账户
			} ];
			options['toolbar'] = {
					items : myitems
			};
			options['onSelectRow']=that.__onSelectRow;
			MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
			$('#iCCardMainGird').ligerGrid(options);
			that.cache.grid = $('#iCCardMainGird').ligerGetGridManager();
			$('#pageloading').hide();
		},
		// 刷新Grid内容
		refreshGrid : function() {
			var that = window.iCCardMain;
			that.cache.grid.loadData(true);
		},
		// 一般查询
		search : function() {
			var that = this;
			var parms = {};
			var tem = [];
			parms = tem;
			
			if($("#cardNumber_query").val()){
				parms['requestParam.equal.cardNumber']=$("#cardNumber_query").val();
			}
			if($("#cardType_query_val").val()){
				parms['requestParam.equal.cardType']=$("#cardType_query_val").val();
			}
			if($("#opencardofficecode_query_val").val()){
				parms['requestParam.equal.opencardofficecode']=$("#opencardofficecode_query_val").val();
			}
			if($("#ownOrg_query_val").val()){
				parms['requestParam.equal.ownOrg']=$("#ownOrg_query_val").val();
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},
		// 重置查询条件
		reset : function() {
			$('#cardNumber_query').val('');
			$('#cardType_query_val').val('');
			$('#opencardofficecode_query_val').val('');
			$('#ownOrg_query_val').val('');
			$('#cardType_query').val('');
			$('#opencardofficecode_query').val('');
			$('#ownOrg_query').val('');
			
			$('#opencardofficecode_query').ligerGetComboBoxManager().setData(null);
		},
		
		// 增加
		add : function() {
			var that = window.iCCardMain;
			that.cache.subPageId = 'add';
			var title = '新增主卡信息';
			var url = 'maincard-add.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, subHeight, subWidth-300, that.success, that.cancel, title);
		},
		success : function(item, dialog) {
			dialog.frame.window.maincardAdd.f_save();
		},
		cancel : function(item, dialog) {
			dialog.close();
		},
		// 在修改页面显示要修改的记录的信息
		modify : function(id) {
			var that = this;
			that.cache.subPageId = 'edit';
			var title = '修改主卡信息';
			that.cache.id = id;
			var url = 'maincard-add.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, subHeight, subWidth,that.success, that.cancel, title);
		},
		// 在修改页面显示要修改的记录的信息
		refresh : function(id) {
			var that = this;
			JAjax(that.config.refreshUrl, {'model.id' : id
			}, 'json', function(data) {
				if (data.message=="1") {
					JSWK.clewBox("更新中,请稍后．．．", 'clew_ico_succ', failShowTime);
					that.cache.grid.loadData();// 刷新grid
				} else {
					JSWK.clewBox('刷新失败!', 'clew_ico_fail', failShowTime);
				}
			}, function() {
				JSWK.clewBox('提交数据时发生异常!', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 删除
		del : function() {
			var ids = "";
			var that = window.iCCardMain;
			var rows = that.cache.grid.getSelecteds();
			for (var i in rows) {
				ids+=rows[i].id+";";
				if(rows[i].childCardNum && rows[i].childCardNum > 0){
					window.iCCardMain.alert(rows[i].cardNumber+" 已关联副卡，无法删除!");
					return ;
				}
			}
			if(ids && $.trim(ids).length>0){
				$.ligerDialog.confirm('确定要删除吗？', function(yes) {
					if (yes) {
						JAjax(that.config.deleteUrl, {
							'ids' : ids
						}, 'json', function(data) {
							if (data=="success") {
								JSWK.clewBox("删除成功！", 'clew_ico_succ', failShowTime);
								//that.flushCodeList({'id':id},'delete');//刷新缓存  
								that.cache.grid.loadData();// 刷新grid
							} else {
								JSWK.clewBox('删除主卡信息失败!', 'clew_ico_fail', failShowTime);
							}
						}, function() {
							JSWK.clewBox('提交数据时发生异常!', 'clew_ico_fail', failShowTime);
						}, true);
					}
				});
			}
		},
		dealSuccess : function(item, dialog){
			dialog.frame.window.dealList.f_save();
		},
		finaceDealSuccess : function(item,dialog){
			dialog.frame.window.finaceDeal.f_save();
		},
		//显示副卡列表
		showViceCard : function(id){
			var that = this;
			that.cache.subPageId = 'show';
			that.cache.showId = id;
			var title = 'IC卡显示列表';
			var url = 'vice-card-by-main.jsp';
			that.cache.parentId=id;
			that.cache.dialog = MALQ_UI.open_button(url, subHeight, subWidth,null, window.iCCardMain.cancel, title);
		},
		alert : function(mes, css) {
			JSWK.clewBox(mes, css, failShowTime);
		}
	};

	$(document).ready(function() {
		window.iCCardMain = new ICCardMain();
	});
})();