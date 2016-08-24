//普通账户列表
(function() {
	var OpencardofficeList = function() {
		this.init();
	};
	OpencardofficeList.prototype = {
		config : {
			queryGridUrl : root + '/code/queryListPage.action',
			subHeight : subHeight,
			subWidth : subWidth,
		},
		cache : {
			grid : null,
			accordion : null,
			tab : null,
			node : null,
			dialog : null,
			parms : {},
			checkedStoreId:[],
		},

		init : function() {
			this.loadGird();
			this.render();
			this.initSelectData();
		},
		initSelectData : function() {
		},
		render : function() {
			var that = this;
			// 布局
			$('#layout1').ligerLayout({
				leftWidth : 210,
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
			var that = window.opencardofficeList;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},
		loadGird : function() {
			var that = this;
			var typeCode = $.trim($("#cardTypeCode").val());
			var myitems = [];
			var options = {};
			var columns = [ {
				display : '编码',
				name : 'code',
				width : '30%',
				align : 'left'
			}, {
				display : '名称',
				name : 'name',
				width : '30%',
				align : 'left'
			}, {
				display : '地区',
				name : 'description',
				width : '30%',
				align : 'center'
			}];
			options['enabledEdit'] = true;
			options['columns'] = columns;
			options['checkbox'] = false;
			options['frozenCheckbox'] = false;
			options['sortName'] = 'name';
			options['rownumbers']=true;
			options['parms'] = {'requestParam.equal.typeCode': typeCode}; // 设置默认进来的请求参数
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单
			options['onSelectRow']=that.__onSelectRow;
			MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
			$('#opencardofficeGrid').ligerGrid(options);
			that.cache.grid = $('#opencardofficeGrid').ligerGetGridManager();
			$('#opencardofficeGrid').css('top', '0px');
			$('#pageloading').hide();// 加载图片
		},
		// 查询
		search : function() {
			var that = this;
			var typeCode = $.trim($("#cardTypeCode").val());
			var name = $.trim($("#opencardofficename_query_param").val());
			var parms = {};
			var tem = [];
			parms = tem;
			parms['requestParam.equal.typeCode']=typeCode;
			parms['requestParam.like.name']='%' + name + '%';
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},
		f_select : function() {
			var ids = window.opencardofficeList.cache.checkedStoreId;
			if (ids.length == 0) {
				JSWK.clewBox("请选择行数据！", 'clew_ico_fail', failShowTime);
			} else {
				return {
					ids : ids
				};
			}
		},
		__onSelectRow : function(data, rowindex, rowobj) {
			var that = window.opencardofficeList;
			that.cache.checkedStoreId.push({
				code : data.code,
				name : data.name,
				cardAreaCode:data.description,
				cardAreaName:data.name
			});
		},
		reset : function() {
			$('#opencardofficename_query_param').val('');
		}

	};

	$(document).ready(function() {
		window.opencardofficeList = new OpencardofficeList();
	});
})();