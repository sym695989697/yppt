var Reconciliation = function() {
	this.grid = null, this.accordion = null, this.tab = null, this.node = null, this.dialog = null, this.parms = {}, this.message = null, this.subPageId = null, this.pCodeId = null, this.grid = null, this.dialog = null, this.accordion = null, this.tree = null, this.tab = null, this.editId = null, this.showId = null, this.searchParms = {};
};
Reconciliation.prototype = {
	// 初始化数据
	init : function() {
		// 初始化日期控件
		this.dateInit();
		// 初始化表格
		this.loadGird();
		// 绑定事件
		this.bindEvent();
		// 初始化下拉框
		this.initComboBox();
		this.initProvince(); 
	},
	// 绑定事件
	bindEvent : function() {
		var model = this;
		// 充值按钮
		$("#resettt").click(function() {
			model.resetSeach();
		});
		// 查询按钮
		$("#searchList").click(function() {
			model.search();
		});

	},
	// 重置按钮
	resetSeach : function() {
		$("#queryForm").find("input[type='text']").val('');
		$("#queryForm").find("input[type='hidden']").val('');
	},
	// 初始化配置
	config : {
		// 分页查询石化副卡对账单
		queryGridUrl : root + '/reconciliation/queryReconciliationListPage.action',
		subHeight : subHeight,
		subWidth : subWidth
	},
	// 日期设置
	dateInit : function() {
		$("#tradeTime1").ligerDateEditor({
			width : 130,
			showTime : true,
			format : "yyyy-MM-dd"
		});
		$("#tradeTime2").ligerDateEditor({
			width : 130,
			showTime : true,
			format : "yyyy-MM-dd"
		});
	},
	// 导入对账文件
	importReconciliation : function() {
		var title = '导入账单';
		var url = 'reconciliation-sy-import.jsp';
		window.reconciliation.dialog = MALQ_UI.open_button(url, 100, 500, window.reconciliation.importSuccess, window.reconciliation.cancel, title);
	},
	importSuccess : function(item, dialog) {
		window.reconciliation.dialog.frame.window.importRebateManager.f_save();
	},
	cancel : function(item, dialog) {
		window.reconciliation.dialog.close();
	},
	// 初始化列表框
	initComboBox : function() {
		// 加载交易类型
		CODE_UTIL.getSelectByCodeType($('#tradeType'), 'IC-TRADE-TYPE', 180, 80);
		// 加载省份
		$('#province').ligerComboBox({
			data : CODE_UTIL.getZoneData('0'),
			textField : "n",
			valueField : "c",
			selectBoxWidth : 140,
			selectBoxHeight : 180,
			width : 140
		});
	},
	// 初始化列表
	loadGird : function() {
		var that = this;
		// 生成表格上面的按钮菜单///////////////////////////////
		var myitems = [];
		var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, [ 'import-reconciliation-sy' ]);
		$(funItems).each(function() {
			myitems.push({
				line : true
			});
			var item = {};
			var click = null;
			if ('import-reconciliation-sy' == this.permId) {
				click = that.importReconciliation;
			}
			item['text'] = this.text;
			item['click'] = click;
			item['icon'] = this.icon;
			myitems.push(item);
		});

		var options = {};
		var columns = [ {
			display : '交易流水号',
			name : 'tradeSeqNo',
			width : '10%',
			align : 'center'
		},{
			display : '交易时间',
			name : 'tradeTime',
			width : '12%',
			align : 'center',
			render:function(row){
				if(row.tradeTime)
					return new Date(row.tradeTime).format("yyyy-MM-dd hh:mm:ss");
				else
					return "";
			}
		}, {
			display : '主卡卡号',
			name : 'mainCardNum',
			width : '14%',
			align : 'center'
		}, {
			display : '卡号',
			name : 'cardNo',
			width : '8%',
			align : 'center'
		}, {
			display : '注册手机号',
			name : 'userRegPhone',
			width : '12%',
			align : 'center'
		}, {
			display : '会员名称',
			name : 'userName',
			width : '8%',
			align : 'center'
		},  {
			display : '金额',
			name : 'tradeMoney',
			width : '8%',
			align : 'center',
			render : function(row) {
				var tradeMoney = row.tradeMoney;
				if (tradeMoney != null && tradeMoney != "") {
					return (parseFloat(tradeMoney) / 100).toFixed(2);
				} else {
					return "0";
				}
			}
		}, {
			display : '交易类型',
			name : 'tradeType',
			width : '8%',
			align : 'center',
			render : function(row) {
				var tradeType = row.tradeType;
				return CODE_UTIL.getCodeName('IC-TRADE-TYPE', String(tradeType));
			}
		}, {
			display : '交易状态',
			name : 'tradeState',
			width : '8%',
			align : 'center',
			render : function(row) {
				if(row.tradeState)
				return CODE_UTIL.getCodeName('IC-TRADE-STATUS', row.tradeState);
				else{
					return '';
				}
			}
		}, {
			display : '商品种类',
			name : 'productName',
			width : '8%',
			align : 'center'
		}, {
			display : '油量（升）',
			name : 'productNum',
			width : '8%',
			align : 'center',
			render : function(row) {
				var productNum = row.productNum;
				if (productNum != null && productNum != "") {
					return (parseFloat(productNum) / 100).toFixed(2);
				} else {
					return "0";
				}
			}
		}, {
			display : '车牌号',
			name : 'vehicleNo',
			width : '8%',
			align : 'center'
		}, {
			display : '省份',
			name : 'tradeProvinceCode',
			width : '8%',
			align : 'center',
			render : function(row) {
				 var tradeProvinceCode = row.tradeProvinceCode;
				 return CODE_UTIL.getZoneNameByCode(tradeProvinceCode);
			}
		}, {
			display : '受理机构',
			name : 'tradeAdress',
			width : '14%',
			align : 'center'
		} ];
		options['columns'] = columns;
		options['checkbox'] = true;
		options['frozenCheckbox'] = false;
		options['toolbar'] = {
			items : myitems
		};// 表格上面菜单
		options['pageSize'] = 15;
		options['sortName'] = 'tradeTime';
		options['sortOrder'] = 'desc';
		options['parms'] = [{name:'requestParam.equal.cardType',value:'CARD-TYPE-01'}]; // 设置默认进来的请求参数

		MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
		$('#reconciliationList').ligerGrid(options);
		that.grid = $('#reconciliationList').ligerGetGridManager();
		$('#reconciliationList').css('top', '0px');
		$('#pageloading').hide();// 加载图片
	},
	// 查询
	search : function() {
		var that = this;
		var parms = [];
		var mainCardNum = $('#mainCardNum').val();
		var tradeType = $('#tradeType_val').val();
		var userRegPhone = $('#userRegPhone').val();
		var provinceCode = $('#province_val').val();
		var userName = $('#userName').val();
		var tradeAdress = $('#tradeAdress').val();
		var tradeTime1 = getFormatDataToLong($("#tradeTime1").val(),false);
	    var tradeTime2 = getFormatDataToLong($("#tradeTime2").val(),true);
		if(tradeTime1!=0&&tradeTime2!=0 && tradeTime1>=tradeTime2){
		 JSWK.clewBox('结束时间必须大开始时间','clew_ico_fail');
			 return false;
		}
		if (tradeTime1>0) {
			parms['requestParam.startwith.tradeTime']=tradeTime1;
		}
		if (tradeTime2>0) {
			parms['requestParam.endwith.tradeTime']=tradeTime2;
		}
		if (userName) {
			parms['requestParam.like.userName']="%"+userName+"%";
		}
		if (mainCardNum) {
			parms['requestParam.like.mainCardNum']="%"+mainCardNum+"%";
		}
		if (tradeType) {
			parms['requestParam.equal.tradeType']=tradeType;
		}
		if (userRegPhone) {
			parms['requestParam.like.userRegPhone']="%"+userRegPhone+"%";
		}
		// 开卡机构
		if (tradeAdress) {
			parms['requestParam.like.tradeAdress']="%"+tradeAdress+"%";
		}
		
		//省份
		if (provinceCode) {
			parms['requestParam.equal.tradeProvinceCode']=provinceCode;
		}
		parms['requestParam.equal.cardType']='CARD-TYPE-01';
		that.grid.set('parms', parms);
		that.grid.changePage('first');
		that.grid.loadData(true);
	},
	initProvince:function(){
		CODE_UTIL.getCodeSelect_p($("#province"),180,180);
	},
	flushCodeList : function(row, op) {
		window.parent.indexPage.initAllCodeInfo();
	},
	// 日期转化
	getTimeEnd : function(date) {
		var year = date.getFullYear();
		var month = date.getMonth();
		var day = date.getDate();
		var todayTime = new Date(year, month, day);
		var date = new Date(todayTime.getTime());
		var getMonth = ((date.getMonth() + 1) < 10) ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1);
		var getDate = ((date.getDate() + 1) < 10) ? ("0" + (date.getDate() + 1)) : (date.getDate() + 1);
		var nextDay = date.getFullYear() + "-" + getMonth + "-" + getDate;
		return nextDay;
	},
	success_alert : function(data) {
		JSWK.clewBox(data, 'clew_ico_succ');
	},
	fail_alert : function(data) {
		JSWK.clewBox(data, 'clew_ico_fail', failShowTime);
	},
	// 刷新Grid内容
	refreshGrid : function() {
		var that = window.reconciliation;
		that.grid.loadData(true);
	},
	alert_message : function(mes) {
		var that = this;
		that.refreshGrid();
		that.dialog.close();
		that.message = mes;
		var title = '导入结果';
		var url = 'import-result.jsp';
		var options = {btnOptions:[{ text: '确定', onclick:window.reconciliation.cancel  }
        ]
			};
		that.dialog = MALQ_UI.open_many_button(url, 300, 800,options, title);
	}
};
$(function() {
	var reconciliation = new Reconciliation();
	window.reconciliation = reconciliation;
	reconciliation.init();
});