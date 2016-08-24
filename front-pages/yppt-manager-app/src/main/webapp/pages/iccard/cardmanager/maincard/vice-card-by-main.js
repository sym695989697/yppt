/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var ViceCardByMain = function() {
		this.init();
	};
	ViceCardByMain.prototype = {
		config : {
			queryUrl : root + '/viceCard/queryViceCardListPage.action'
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
			searchParms : {}
		},
		init : function() {
			this.render();
			this.loadGird();
			CODE_UTIL._initCodeData();
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
			var that = window.viceCardByMain;
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
			var options = {};
			var columns = [
					{
						display : '卡号',
						name : 'cardNumber',
						align : 'center',
						width : '20%',
						isSort : false,
						render : function(row) {
							 return row.cardNumber;
						}
					},{
						display : '开卡时间',
						name : 'createdTime',
						align : 'center',
						width : '20%',
						isSort : true,
						render:function(row){
							if(row.createdTime)
								return new Date(row.createdTime).format("yyyy-MM-dd hh:mm:ss");
							else
								return "";
						}
					},{
						display : '车牌号',
						name : 'vehicleNo',
						align : 'center',
						width : '20%',
						isSort : false
					},
					{
						display : '注册手机号',
						name : 'userRegPhone',
						align : 'center',
						width : '20%',
						isSort : false
					 
					},
					{//暂无对应字段
						display : '会员名称',
						name : 'userName',
						align : 'center',
						width : '20%',
						isSort : false
					}
					];

			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			options['parms'] = {
					'requestParam.equal.parentId': parent.window.iCCardMain.cache.parentId
					}; // 设置默认进来的请求参数
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单
			options['usePager'] = true;
			MALQ_UI.setGridOptions(options, that.config.queryUrl);

			$('#viceCardByMainGrid').ligerGrid(options);
			that.cache.grid = $('#viceCardByMainGrid').ligerGetGridManager();
			$('#viceCardByMainGrid').css('top', '3px');
			$('#pageloading').hide();// 加载图片
		},

		// 刷新Grid内容
		refreshGrid : function() {
			var that = window.viceCardByMain;
			that.cache.grid.loadData(true);
		},
		// 一般查询
		search : function() {
			var that = this;
			var parentId = parent.window.iCCardMain.cache.parentId;
			var userRegPhone = $("#userRegPhone_main").val();//注册手机号
			var vehicleNo = $("#vehicleNo_main").val(); //车牌号
			var memberName = $("#memberName_main").val(); //会员名称
			var cardNumber = $("#cardNumber_main").val(); //卡号
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
			
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},	

		cancel : function(item, dialog) {
			dialog.close();
		},

		alert : function(mes, css) {
			JSWK.clewBox(mes, css, failShowTime);
		}
	};

	$(document).ready(function() {
		window.viceCardByMain = new ViceCardByMain();
	});

})();
