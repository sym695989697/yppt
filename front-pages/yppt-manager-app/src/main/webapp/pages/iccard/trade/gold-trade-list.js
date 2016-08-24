/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var GoldTradeList = function() {
		this.init();
	};
	GoldTradeList.prototype = {
		config : {
			queryGridUrl : root + '/goldTrade/getGoldTradeListPage.action?userName=&userRegPhone=&tradeTime1=&tradeTime2=&tradeType=',
			subHeight : subHeight,
			subWidth : subWidth
		},
		cache : {
			grid : null,
			accordion : null,
			tab : null,
			node : null,
			dialog : null,
			parms : {},
			subPageId : null,
			pCodeId : null,
			reasonId :null,
			userRegPhone : null,
			userId : null,
			userNname :null
		},
		init : function() {
			this.dateInit();
			this.loadGird();
		},
		render : function() {

		},
		// 高度改变
		_heightChanged : function(options) {
			var that = window.goldTradeList;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},
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
			var data = CODE_UTIL.getCodeData('YCFLLX');
			data.unshift({'name':'请选择','code':''});
			$('#tradeType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140
	  		});
			
		},

		loadGird : function() {
			var that = this;
			var myitems = [];
			var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, [ 'export' ]);
			$(funItems).each(function() {
				myitems.push({
					line : true
				});
				var item = {};
				var click = null;
				item['text'] = this.text;
				item['click'] = click;
				item['icon'] = this.icon;
				myitems.push(item);
			});
			// ////////////生成表格上面的按钮菜单////////////////////////////
			var options = {};
			var columns = [ {
				display : '注册手机号',
				name : 'userRegPhone',
				width : '18%',
				align : 'center'
			}, {
				display : '会员名称',
				name : 'userName',
				width : '20%',
				align : 'center'
			}, {
				display : '交易类型',
				name : 'type',
				width : '15%',
				align : 'center',
				render : function(row) {
					return CODE_UTIL.getCodeName('YCFLLX',row.type);
				}
			}, {
				display : '旺金币数量',
				name : 'creditNum',
				width : '15%',
				align : 'right',
				render : function(row) {
					if(row.type=="rebate"){
						return "+"+row.creditNum;
					}else if(row.type=="recharge"){
						return "-"+row.creditNum;
					}else{
						return row.creditNum;
					}
				}  
			}, {
				display : '兑换后金额',
				//name : 'balance', //---这里改为兑换后金额 =旺金币数量/100
				name: 'creditNum',
				width : '10%',
				align : 'center',
				render : function(row) {
					if(row.creditNum){
						return (row.creditNum/100).toFixed(2);
					}else{
						return "0.00";
					}
				} 
				
			}, {
				display : '交易时间',
				name : 'tradeTime',
				width : '10%',
				align : 'center',
				render : function(row) {
					if (row.tradeTime) {
						return new Date(row.tradeTime).format("yyyy-MM-dd hh:mm:ss");;
					} else {
						return "";
					}
				}
			} , {
				display : '操作',
				name : '',
				width : '10%',
				align : 'center',
				render : function(row) {// userRegPhone
					if(row.type=='rebate'){
						return '';
					}
					return '<a href="javascript:window.goldTradeList.showInfo(\''+ row.reasonId+'\',\''+ row.userName +'\',\''+ row.userRegPhone +'\')">详情</a>&nbsp;';
				}
			}];
			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			options['parms'] = [ {} ];
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单
			options['pageSize'] = 30;
			options['sortName'] = 'tradeTime';
			options['sortOrder'] = 'desc';
			MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
			$('#goldTradeList').ligerGrid(options);
			that.cache.grid = $('#goldTradeList').ligerGetGridManager();
			$('#goldTradeList').css('top', '0px');
			$('#pageloading').hide();// 加载图片
		},
		showInfo:function(reasonId,userName,userRegPhone){
			var that = window.goldTradeList;
        	var title = '旺金币交易详情';      
        	var infourl = 'gold-trade-info.jsp';
        	var isResize=false;
        	that.cache.reasonId = reasonId;
        	that.cache.userName = userName;
        	that.cache.userRegPhone = userRegPhone;
           	that.cache.dialog = $.ligerDialog.open({
				url : infourl,
				height : 420,
				width : 800,
				modal : true,
				title : title,
				isResize:true,
				allowClose:true,
				jgrid : goldTradeList.grid,//关闭窗口后，刷新grid列表
				buttons:[
					         { text: '关闭', 
					           onclick: function (item, dialog) { 
					        	   window.goldTradeList.cache.dialog.close();
					           } 
					         } 
				         ]
			});
        },
		// 查询
		search : function() {
			var that = this;
			var parms = {};
			var userRegPhone = $('#userRegPhone').val();
			var userName = $('#userName').val();
			var tradeType = $('#tradeType_val').val();
			var tradeTime1 = getFormatDataToLong($("#tradeTime1").val(),false);
 	        var tradeTime2 = getFormatDataToLong($("#tradeTime2").val(),true);
			if(tradeTime1!=0&&tradeTime2!=0 && tradeTime1>=tradeTime2){
			 JSWK.clewBox('结束时间必须大开始时间','clew_ico_fail');
				 return false;
			}
			if (userRegPhone) {
				parms['requestParam.equal.userRegPhone']=userRegPhone;
			}
			if (userName) {
				parms['requestParam.equal.userName']=userName;
			}
			if (tradeType) {
				parms['requestParam.equal.tradeType']=tradeType;
			}
			
			if (tradeTime1>0) {
				parms['requestParam.startwith.tradeTime']=tradeTime1;
			}
			if (tradeTime2>0) {
				parms['requestParam.endwith.tradeTime']=tradeTime2;
			}
			
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},
		reset : function() {
			$("#queryForm").find("input[type='text']").val('');
			$("#queryForm").find("input[type='hidden']").val('');
		},
		success_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_succ');
		},
		fail_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_fail', failShowTime);
		}
	};
	function getTimeEnd(date) {
		var year = date.getFullYear();
		var month = date.getMonth();
		var day = date.getDate();
		var todayTime = new Date(year, month, day);
		var date = new Date(todayTime.getTime());
		var getMonth = ((date.getMonth() + 1) < 10) ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1);
		var getDate = ((date.getDate() + 1) < 10) ? ("0" + (date.getDate() + 1)) : (date.getDate() + 1);
		var nextDay = date.getFullYear() + "-" + getMonth + "-" + getDate;
		return nextDay;
	}

	$(document).ready(function() {
		window.goldTradeList = new GoldTradeList();
	});
})();