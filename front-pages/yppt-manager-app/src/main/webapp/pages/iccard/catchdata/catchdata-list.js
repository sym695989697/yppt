(function() {
	$(document).ready(function() {
		window.catchdataList = new CatchdataList();
	});
	var CatchdataList = function() {
		this.init();
	};
	CatchdataList.prototype = {
		// 配置信息
		config : {
			queryGridUrl : root + '/catchdata/queryCatchdataListPage.action',// 获取列表数据
			deleteUrl : root + '/catchdata/deleteAccount.action',// 删除账户
			getyzmUrl : root + '/catchdata/getyzm.action',// 获取验证码
			loginUrl : root + '/catchdata/login.action',// 登陆
			catchMainAndSubCard : root + '/catchdata/catchMainAndSubCard.action',// 获取主副卡
			catchCardBalance : root + '/catchdata/catchCardBalance.action',// 获取并刷新余额
			catchAccountTrade : root + '/catchdata/catchAccountTrade.action',// 获取账户交易数据
			catchTradeByConsume : root + '/catchdata/catchTradeByConsume.action',// 获取消费记录数据
			subHeight : subHeight,
			subWidth : subWidth
		},

		// 缓存数据
		cache : {
			grid : null,
			tree : null,
			accordion : null,
			tab : null,
			node : null,
			dialog : null,
			parms : {},
			subPageId : null
		},

		// 初始化方法
		init : function() {
			this.loadGird();
		},

		// 初始化表格
		loadGird : function() {
			var options = {};
			var toolbar = {
				items : [ {
					text : "添加",
					click : this.add,
					icon : "add"
				}, {
					text : "刷新余额",
					click : this.refBalance,
					icon : "add"
				} ]
			};
			var columns = [ {
				display : '用户名',
				name : 'usename',
				align : 'center'
			}, {
				display : '验证码',
				name : 'yzm',
				align : 'left',
				width : '17%',
				render : function(row) {
					var str = "<img src='' id='yzmimg" + row.__id + "' />";
					if (row.remark  != "true") {
						str += "<input type='text' id='yzm" + row.__id + "' alt='输入验证码,可以点击按钮获取验证码' style='width:100px;float:right' />";
						str += "<input type='hidden' id='yzmcookie" + row.__id + "'  />";
					}
					return str;
				}
			}, {
				display : '状态',
				name : 'remark',
				align : 'center',
				width : '8%',
				render : function(row) {
					if (row.remark == "true") {
						return "已登录";
						// return "登陆<input type='hidden' value='sucess'
						// id='islogin"+row.__id+"'/>";
					} else {
						return '<a href="javascript:window.catchdataList.getYZM(\'' + row.accounttype + "','" + row.usename + "','" + row.__id + '\')">获取验证码</a>';
						// return "未登陆<input type='hidden' value='fail'
						// id='islogin"+row.__id+"'/>";
					}
				}
			}, {
				display : '类型',
				name : 'accounttype',
				align : 'center',
				render : function(row) {
					if (row.accounttype) {
						return CODE_UTIL.getCodeName('IC-CARD-TYPE', row.accounttype);
					} else {
						return "";
					}
				}
			}, {
				display : '通知手机号',
				name : 'mobile',
				align : 'center'
			}, {
				display : '所属组织',
				name : 'ownOrg',
				align : 'center',
				render : function(row) {
					if (row.ownOrg) {
						return CODE_UTIL.getCodeName('CARD_ORG', row.ownOrg);
					} else {
						return "";
					}
				}
			}, {
				display : '开卡地区',
				name : 'cardAreaCode',
				align : 'center',
				render : function(row) {
					if (row.cardAreaCode) {
						return CODE_UTIL.getCodeName(row.accounttype, row.cardAreaCode);
					} else {
						return "";
					}
				}
			}, {
				display : '主/子卡最后同步时间',
				name : 'mainCardTrade',
				align : 'center',
				width : '17%',
				render : function(row) {
					var time = "";
					if(row.mainCardTrade){
						time = getSmpFormatDateByLong(row.mainCardTrade, true);
					}
					var str="";
					if (row.remark == "true") {
						str+= ">>>";
						str += '<a href="javascript:window.catchdataList.catchMainAndSubCard(\'' + row.__id + "','" + row.usename + "','" + row.accounttype + '\')">抓取主副卡</a>&nbsp;&nbsp;';
					}
					return time + str;
				}
			}, {
				display : '主卡交易最后同步时间',
				name : 'syncMainCardTime',
				align : 'center',
				width : "17%",
				render : function(row) {
					var time ="" ;
					if(row.syncMainCardTime){
						time = getSmpFormatDateByLong(row.syncMainCardTime, true);
					}
					var str="";
					if (row.remark == "true") {
						str+= ">>>";
						str += '<a href="javascript:window.catchdataList.selectDate(\'' + row.__id + "','" + row.usename + "','" + row.accounttype + "',true" + ')">主卡交易</a>&nbsp;&nbsp;';
					}
					return time +  str;

				}
			}, {
				display : '子卡交易最后同步时间',
				name : 'syncSubCardTime',
				align : 'center',
				width : '17%',
				render : function(row) {
					var time = "";
					if(row.syncSubCardTime){
						time = getSmpFormatDateByLong(row.syncSubCardTime, true);
					}
					var str="";
					if (row.remark == "true") {
						str+= ">>>";
						str += '<a href="javascript:window.catchdataList.selectDate(\'' + row.__id + "','" + row.usename + "','" + row.accounttype + "',false" + ')">子卡交易</a>&nbsp;&nbsp;';
					}
					return time + str;
				}
			}, {
				display : "操作",
				width : "15%",
				render : function(row) {
					var str = '<a href="javascript:window.catchdataList.modify(\'' + row.id + '\')">修改</a>&nbsp;&nbsp;';
					str += '<a href="javascript:window.catchdataList.del(\'' + row.id + '\')">删除</a>&nbsp;&nbsp;';
					if (row.remark  != "true") {
						str += '<a href="javascript:window.catchdataList.login(\'' + row.__id + "','" + row.usename + "','" + row.accounttype + '\')">登陆</a>&nbsp;&nbsp;';
					}
//					else{
//						str += '<a href="http://localhost:8080/ICCardManagerApp/catchdata/userloginoutsite.action" target="_blank">进入网站</a>&nbsp;&nbsp;';
//					}
					return str;
				}
			} ];
			options['enabledEdit'] = true;
			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			options['sortName'] = 'username';
			options['toolbar'] = toolbar;
			options['rownumbers'] = false;
			options[' columnWidth'] = "8%";
			MALQ_UI.setGridOptions(options, this.config.queryGridUrl);
			$('#catchdataList').ligerGrid(options);
			this.cache.grid = $('#catchdataList').ligerGetGridManager();
		},

		// 抓取账户交易数据，消费记录数据时候，首先选择开始结束日期
		selectDate : function(trid, userName, hostType, ismain) {
			var that = window.catchdataList;
			
			$.ligerDialog.open({
				url : "catchdata-selectdate.jsp",
				title : "选择开始结束日期",
				height : "400",
				width : "400",
				name:"selectdate",
				buttons : [ {
					text : '确定',
					onclick : function(item, dialog) {
						var startdate=$(window.frames["selectdate"].document).find("#startdate").val(); 
						var enddate=$(window.frames["selectdate"].document).find("#enddate").val(); 
						if (startdate == "" || enddate == "") {
							alert("请选择开始或结束日期");
							return false;
						}
						if (startdate > enddate) {
							alert("开始时间不能大于结束时间");
							return false;
						}
						if (ismain) {
							that.catchAccountTrade(trid, userName, hostType, startdate, enddate);
						} else {
							that.catchTradeByConsume(trid, userName, hostType, startdate, enddate);
						}
						dialog.close();
					}
				}, {
					text : '取消',
					onclick : function(item, dialog) {
						dialog.close();
					}
				} ]
			});
		},
		// 抓取消费记录数据
		catchTradeByConsume : function(trid, userName, hostType, startDate, endDate) {
			var that = window.catchdataList;
			var logincookies = $("#yzmcookie" + trid).val();
			var submitdata = {
				"userName" : userName,
				"hostType" : hostType,
				"startDate" : startDate,
				"endDate" : endDate
			};
			JAjax(that.config.catchTradeByConsume, submitdata, 'json', function(data) {
				var res="抓取消费交易记录出现异常.";
				if(data=="true"){
					res="正在抓取消费交易记录数据.";
				}
				JSWK.clewBox(res, 'clew_ico_succ', failShowTime);
			}, function() {
				JSWK.clewBox('抓取消费记录数据发生异常', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 抓取主卡账户交易数据
		catchAccountTrade : function(trid, userName, hostType, startDate, endDate) {
			var that = window.catchdataList;
			var submitdata = {
				"userName" : userName,
				"hostType" : hostType,
				"startDate" : startDate,
				"endDate" : endDate
			};
			JAjax(that.config.catchAccountTrade, submitdata, 'json', function(data) {
				var res = "抓取账户交易数据出现异常,请稍后重试.";
				if(data=="true"){
					res="正在抓取账户交易数据.";
				}
				JSWK.clewBox(res, 'clew_ico_succ', failShowTime);
			}, function() {
				JSWK.clewBox('抓取账户交易数据发生异常', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 刷新余额
		catchCardBalance : function(trid, userName, hostType) {
			var that = window.catchdataList;
			var logincookies = $("#yzmcookie" + trid).val();
			// if(logincookies==""){
			// alert("请先登陆");
			// return false;
			// }
			var submitdata = {
				"userName" : userName,
				"hostType" : hostType
			};
			JAjax(that.config.catchCardBalance, submitdata, 'json', function(data) {
				var res="刷新余额出现异常,请稍后重试";
				if(data=="true"){
					res="正在抓取卡余额数据.";
				}
				JSWK.clewBox(res, 'clew_ico_succ', failShowTime);
			}, function() {
				JSWK.clewBox('刷新余额异常', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 抓取主副卡数据
		catchMainAndSubCard : function(trid, userName, hostType) {
			var that = window.catchdataList;
			var submitdata = {
				"userName" : userName,
				"hostType" : hostType
			};
			JAjax(that.config.catchMainAndSubCard, submitdata, 'json', function(data) {
				var res="抓取主副卡数据出现异常.";
				if(data=="true"){
					res="正在抓取主副卡数据.";
				} 
				JSWK.clewBox(res, 'clew_ico_succ', failShowTime);
			}, function() {
				JSWK.clewBox('抓取主副卡数据异常', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 登陆
		login : function(trid, username, hostType) {
			var that = window.catchdataList;
			var logincookies = $("#yzmcookie" + trid).val();
			var yzmvalue = $("#yzm" + trid).val();
			if (logincookies == "") {
				alert("请先获取验证码");
				return false;
			}
			if (yzmvalue == "") {
				alert("请输入验证码");
				return false;
			}
			var submitdata = {
				"userName" : username,
				"hostType" : hostType,
				"code" : yzmvalue,
				"cookies" : logincookies
			};
			JAjax(that.config.loginUrl, submitdata, 'json', function(data) {
				JSWK.clewBox(data, 'clew_ico_succ', failShowTime);
				window.catchdataList.cache.grid.loadData(true);
			}, function() {
				JSWK.clewBox('登陆异常', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 获取验证码
		getYZM : function(hostType, userName, trid) {
			var that = window.catchdataList;
			JAjax(that.config.getyzmUrl, {
				'hostType' : hostType,
				'userName' : userName
			}, 'json', function(data) {
				$("#yzmimg" + trid).attr("src", data.yzmUrl+"?"+Math.random());
				$("#yzmcookie" + trid).val(data.cookies);
			}, function() {
				JSWK.clewBox('获取验证码异常', 'clew_ico_fail', failShowTime);
			}, true);
		},

		// 添加账户信息
		add : function() {
			var that = window.catchdataList;
			that.cache.subPageId = "add";
			var title = '添加账户信息';
			var url = 'catchdata-add.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, that.config.subHeight, that.config.subWidth, that.success, that.canel, title);
		},
		// 刷新余额
		refBalance : function() {
			var that = window.catchdataList;
			var rows = that.cache.grid.getSelecteds();
			if (rows.length > 1) {
				alert("请选择一条记录");
			} else {
				that.catchCardBalance(rows[0].__id, rows[0].usename, rows[0].accounttype);
			}
		},
		// 登录
		userLogin : function() {
			var that = window.catchdataList;
			var rows = that.cache.grid.getSelecteds();
			if (rows.length > 1) {
				alert("请选择一条记录");
			} else {
				that.login(rows[0].__id, rows[0].usename, rows[0].accounttype);
			}
		},
		// 修改账户信息
		modify : function(id) {
			var that = window.catchdataList;
			that.cache.subPageId = "edit";
			that.cache.id = id;
			var title = '修改账户信息';
			var url = 'catchdata-add.jsp';
			that.cache.dialog = MALQ_UI.open_button(url, that.config.subHeight, that.config.subWidth, that.success, that.canel, title);
		},

		// 删除用户
		del : function(id) {
			var that = window.catchdataList;
			$.ligerDialog.confirm('是否删除该用户？删除后不可恢复', function(yes) {
				if(yes){
					JAjax(that.config.deleteUrl, {
						'model.id' : id
					}, 'json', function(data) {
						that.cache.grid.loadData(true);
					}, function() {
						JSWK.clewBox('删除数据异常', 'clew_ico_fail', failShowTime);
					}, true);
				}
			});
		},

		// 点击确定按钮时候操作
		success : function(item, dialog) {
			dialog.frame.window.catchdataAdd.f_save();
		},

		// 取消按钮操作
		canel : function() {
			var that = window.catchdataList;
			that.cache.dialog.close();
		},
		// 搜索
		search : function() {
			var parms = [];
			parms['requestParam.equal.usename'] = $("#username").val();
			this.cache.grid.set('parms', parms);
			this.cache.grid.loadData(true);
		}
	}
})();