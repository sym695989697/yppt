/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var RebateHistoryList = function() {
		this.init();
	};
	RebateHistoryList.prototype = {
		config : {
			queryUrl : root + '/rebateHistory/queryRebateHistoryListPage.action',
			changePatchCurrencyUrl : root + '/rebateHistory/changePatchCurrency.action'
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
			//发卡地区
			var cardTypes=CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var areas=[];
			$(cardTypes).each(function(){
				var temp = CODE_UTIL.getCodeData(this.code);
				$(temp).each(function(){
					areas.push(this);
				});
			});
			areas.unshift({'name':'请选择','code':''});
			$('#opencardofficecode').ligerComboBox({
	  			data: areas,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140
	  		});
			var statuses=CODE_UTIL.getCodeData('CURRENCY-STATUS');
			statuses.unshift({'name':'请选择','code':''});
			$('#status').ligerComboBox({
	  			data: statuses,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140
	  		});
//			$("#dateYearAndMonth").ligerDateEditor({width:140,showTime: false,format:"yyyy-MM"});
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
			var that = window.rebateHistoryList;
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
					window.frameElement.id, [ 'import-rebate-history',
							'patch-change-currency' ]);
			$(funItems).each(function() {
				myitems.push({
					line : true
				});
				var item = {};
				var click = null;
				if ('import-rebate-history' == this.permId) {
					click = that.importRebate;
				}
				if('patch-change-currency' == this.permId){
					click = that.changePatchCurrency;
				}
				item['text'] = this.text;
				item['click'] = click;
				item['icon'] = this.icon;
				myitems.push(item);
			});
			var options = {};
			var columns = [
					{
						display : '注册手机号',
						name : 'registerMobile',
						align : 'center',
						width : '10%',
						isSort : true
					},
					{
						display : '发卡地区',
						name : 'grantAreaCode',
						align : 'center',
						width : '11%',
						isSort : true,
						render:function(row){
							//发卡地区
							var cardTypes=CODE_UTIL.getCodeData('IC-CARD-TYPE');
							var grantAreaCode="";
							$(cardTypes).each(function(){
								var t = CODE_UTIL.getCodeName(this.code,row.grantAreaCode);
								if(t){
									grantAreaCode=t;
									return false;
								}
							});
							return grantAreaCode;
						}
					},
					{
						display : '会员名称',
						name : 'ownerName',
						align : 'center',
						width : '10%',
						isSort : false
					},
					{
						display : '账单期',
						name : 'importYear',
						align : 'center',
						width : '8%',
						isSort : false,
						render:function(row){
							if(row.importYear && row.importMonth)
								return row.importYear+"-"+row.importMonth;
							else
								return "";
						}
					 
					},
					{//暂无对应字段
						display : '消费金额(元)',
						name : 'consumeMoney',
						align : 'center',
						width : '10%',
						isSort : false,
						render:function(row){
							if(row.consumeMoney){
								var bls=parseFloat(row.consumeMoney)/100+"";
								if(bls.indexOf(".")==-1){
									bls+=".00";
								}else if(bls.indexOf(".")==bls.length-2){
									bls+="0";
								}
								return bls;
							}else{
								return "--";
							}
						}
					},
					{
						display : '返利旺金币数量',
						name : 'currencyCount',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.currencyCount)
								return row.currencyCount;
							else
								return "--";
						}
					},
					{
						display : '可兑换金额(元)',
						name : 'changeMoney',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.changeMoney){
								var bls=parseFloat(row.changeMoney)/100+"";
								if(bls.indexOf(".")==-1){
									bls+=".00";
								}else if(bls.indexOf(".")==bls.length-2){
									bls+="0";
								}
								return bls;
							}else{
								return "--";
							}
						}
					},
					{
						display : '旺金币结算状态',
						name : 'status',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.status)
							return CODE_UTIL.getCodeName("CURRENCY-STATUS",row.status);
						}
					},
					{
						display : '导入时间',
						name : 'createTime',
						align : 'center',
						width : '10%',
						isSort : true,
						render:function(row){
							if(row.createTime)
								return new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss");
							else
								return "";
						}
					},
					{
						display : '操作人',
						name : 'operatorId',
						align : 'center',
						width : '10%',
						isSort : false
					}
					];

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

			$('#rebateHistoryList').ligerGrid(options);
			that.cache.grid = $('#rebateHistoryList').ligerGetGridManager();
			$('#rebateHistoryList').css('top', '3px');
			$('#pageloading').hide();// 加载图片
		},

		// 刷新Grid内容
		refreshGrid : function() {
			var that = window.rebateHistoryList;
			that.cache.grid.loadData(true);
		},
		// 一般查询
		search : function() {
			var that = this;
			var registerMobile=$("#registerMobile").val();
			var ownerName=$("#ownerName").val();
			var dateYearAndMonth=$("#dateYearAndMonth").val();
			var opencardofficecode=$("#opencardofficecode_val").val();
			var status=$("#status_val").val();
			var parms = {};
			if(registerMobile){
				parms['requestParam.like.registerMobile']="%"+registerMobile+"%";
			}
			if(ownerName){
				parms['requestParam.like.ownerName']="%"+ownerName+"%";
			}
			if(dateYearAndMonth){
				parms['requestParam.equal.importYear']=dateYearAndMonth.split("-")[0];
				parms['requestParam.equal.importMonth']=dateYearAndMonth.split("-")[1];
			}
			if(opencardofficecode){
				parms['requestParam.equal.grantAreaCode']=opencardofficecode;
			}
			if(status){
				parms['requestParam.equal.status']=status;
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},	
		importSuccess : function(item, dialog) {
			dialog.frame.window.importRebateManager.f_save();
		},
		successShow : function(item, dialog) {
			dialog.close();
		},
		cancel : function(item, dialog) {
			dialog.close();
		},
		
		//导入
		importRebate : function(){
			window.rebateHistoryList.cache.subPageId = '0';
			var title = '导入';
			var url = 'rebate-import.jsp';
			window.rebateHistoryList.cache.dialog = MALQ_UI.open_button(url, 300, 1000,window.rebateHistoryList.importSuccess, window.rebateHistoryList.cancel, title);
		},
		//结算到到旺金币
		changePatchCurrency : function(){
			var selectRows = window.rebateHistoryList.cache.grid.selected;
			if(selectRows.length==0){
				window.rebateHistoryList.alert("请选择行","clew_ico_fail");
			}else{
				var ids='';
				var flag=true;
				$(selectRows).each(function(){
					if(this.status=='1'){
						flag=false;
					}else{
						ids+=this.id+',';
					}
				});
				if(ids){
					ids.substring(0, ids.length-1);
				}else{
					window.rebateHistoryList.alert("请选择未结算的返利","clew_ico_fail");
					return ;
				}
				if(!flag){
					$.ligerDialog.confirm('所选中已结算的返利将不会再结算，是否继续',function(yes) {
						if (yes) {
							$.ligerDialog.confirm('确定要结算所选中返利到旺金币吗？',
									function(yes) {
										if (yes) {
											JAjax(window.rebateHistoryList.config.changePatchCurrencyUrl, {'ids' : ids}, 'json',function(data) {
														if (data) {
															if(data=='0'){
																window.rebateHistoryList.refreshGrid();
																JSWK.clewBox('操作成功！','clew_ico_succ',failShowTime);
															}else if(data=='1'){
																JSWK.clewBox('操作失败！请检查会员积分账户是否正常','clew_ico_fail',failShowTime);
															}else if(data=='2'){
																JSWK.clewBox('部分操作失败！请检查会员积分账户是否正常','clew_ico_fail',failShowTime);
															}
														} else {
															JSWK.clewBox('操作失败！','clew_ico_fail',failShowTime);
														}
											}, function() {
												JSWK.clewBox('提交数据时发生异常','clew_ico_fail', failShowTime);
											}, true);
										}
									});
						}
					});
				}else{
					$.ligerDialog.confirm('确定要结算所选中返利到旺金币吗？',
							function(yes) {
								if (yes) {
									JAjax(window.rebateHistoryList.config.changePatchCurrencyUrl, {'ids' : ids}, 'json',function(data) {
												if (data) {
													if(data=='0'){
														window.rebateHistoryList.refreshGrid();
														JSWK.clewBox('操作成功！','clew_ico_succ',failShowTime);
													}else if(data=='1'){
														JSWK.clewBox('操作失败！','clew_ico_fail',failShowTime);
													}else if(data=='2'){
														JSWK.clewBox('部分失败！','clew_ico_fail',failShowTime);
													}
												} else {
													JSWK.clewBox('操作失败！','clew_ico_fail',failShowTime);
												}
									}, function() {
										JSWK.clewBox('提交数据时发生异常','clew_ico_fail', failShowTime);
									}, true);
								}
							});
				}
				
			}
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
			that.cache.dialog = MALQ_UI.open_button(url, 300, 1000,window.rebateHistoryList.successShow, window.rebateHistoryList.cancel, title);
		}
	};

	$(document).ready(function() {
		window.rebateHistoryList = new RebateHistoryList();
	});

})();
