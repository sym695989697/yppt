/*
 * 中石化副卡交易数据
 */
(function() {
	var MemberTradeList = function() {
		this.init();
	};
	MemberTradeList.prototype = {
		config : {
			queryGridUrl : root + '/trade/queryListPage.action',
			outputUrl : root + '/trade/outputMemberTradeList.action',
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
			pCodeId : null
		},
		init : function() {
			this.initMsg();
			this.dateInit();
			this.loadGird();
		},
		render : function() {

		},
		// 高度改变
		_heightChanged : function(options) {
			var that = window.memberTradeList;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},
		initMsg : function() {
			CODE_UTIL.getSelectByCodeType($('#tradeType'), 'IC-TRADE-TYPE', 180, 120);
			CODE_UTIL.getSelectByCodeType($('#org'), 'CARD_ORG', 180, 120);
			//卡类型初始化
			var data = CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var towData = [];
	  		data.unshift({'name':'请选择','code':''});
	  		$('#cardType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 120,
	  			selectBoxHeight: 180,
	  			width:120,
	  			onSelected: function (value){
	  				towData = CODE_UTIL.getCodeData(value);
	  				towData.unshift({'name':'请选择','code':''});
	  				$('#openCardOfficeCode').ligerGetComboBoxManager().setData(towData);
	  			} 
	  		});
	  		//发卡地区
	  		towData.unshift({'name':'请选择','code':''});
	  		$('#openCardOfficeCode').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 120,
	  			selectBoxHeight: 180,
	  			width:120,
	  			onSelected: function (value){
	  			} 
	  		});
			
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
				if ('export' == this.permId) {
					click = that.output;
				}
				item['text'] = this.text;
				item['click'] = click;
				item['icon'] = this.icon;
				myitems.push(item);
			});
			// ////////////生成表格上面的按钮菜单////////////////////////////
			var options = {};
			var columns = [ {
				display : '抓取时间',
				name : 'createTime',
				width : '14%',
				align : 'center',
				render : function(row) {
					if (row.tradeTime != null) {
						return getSmpFormatDateByLong(row.createTime, true);
					} else {
						return "";
					}
				}
			}, {
				display : '交易时间',
				name : 'tradeTime',
				width : '14%',
				align : 'center',
				render : function(row) {
					if (row.tradeTime != null) {
						return getSmpFormatDateByLong(row.tradeTime, true);
					} else {
						return "";
					}
				}
			}, {
				display : '卡号',
				name : 'cardNo',
				width : '12%',
				align : 'center'
			}, {
				display : '会员名称',
				name : 'userName',
				width : '12%',
				align : 'center'
			}, {
				display : '注册手机号',
				name : 'userRegPhone',
				width : '12%',
				align : 'center'
			}, {
				display : '所属主卡',
				name : 'mainCardNum',
				width : '8%',
				align : 'left'
			}, {
				display : '所属组织',
				name : 'ownOrg',
				width : '8%',
				align : 'left',
				render : function(row) {
					var ownOrg = row.ownOrg;
					return CODE_UTIL.getCodeName('CARD_ORG', String(ownOrg));
				}
			}, {
				display : '发卡地区',
				name : 'openCardOfficeCode',
				width : '10%',
				align : 'center',
				render : function(row) {
					var cardType = row.cardType;
					return CODE_UTIL.getCodeName(cardType, String(row.openCardOfficeCode));
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
				display : '交易金额（元）',
				name : 'tradeMoney',
				width : '10%',
				align : 'center',
				render : function(row) {
					var tradeMoney = row.tradeMoney;
					if (tradeMoney != null && tradeMoney != "") {
						return (parseFloat(tradeMoney) / 100).toFixed(2);
					} else {
						return "0.00";
					}

				}
			}, {
				display : '商品数量',
				name : 'productNum',
				width : '10%',
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
				display : '商品名称',
				name : 'productName',
				width : '15%',
				align : 'center'
			}, {
				display : '交易后余额',
				name : 'cardBalance',
				width : '10%',
				align : 'center',
				render : function(row) {
					var cardBalance = row.cardBalance;
					if (cardBalance != null && cardBalance != "") {
						return (parseFloat(cardBalance) / 100).toFixed(2);
					} else {
						return "0";
					}
				}
			}, 
			{
				display : '交易地点',
				name : 'tradeAdress',
				width : '12%',
				align : 'center'
			} 
			,{
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
			}
				 ];
			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			options['parms'] = [ {
				name : 'requestParam.equal.isIcMain',
				value : '1'
			} ];
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单
			options['pageSize'] = 30;
			options['sortName'] = 'tradeTime';
			options['sortOrder'] = 'desc';
			MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
			$('#memberTradeList').ligerGrid(options);
			that.cache.grid = $('#memberTradeList').ligerGetGridManager();
			$('#memberTradeList').css('top', '0px');
			$('#pageloading').hide();// 加载图片
		},
		// 查询
		search : function() {
			var that = this;
			var parms = [];
			var cardNo = $('#cardNo').val();
			var tradeType = $('#tradeType_val').val();
			var org = $('#org_val').val();
			var cardType = $('#cardType_val').val();
			var openCardOfficeCode = $('#openCardOfficeCode_val').val();
			var userRegPhone =$("#userRegPhone").val();
			var userName = $("#userName").val();
			
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
			if (cardNo) {
				parms['requestParam.like.cardNo']="%"+cardNo+"%";
			}
			if (tradeType) {
				parms['requestParam.equal.tradeType']=tradeType;
			}
			if (org) {
				parms['requestParam.equal.ownOrg']=org;
			}
			// 卡类型
			if(cardType){
				parms['requestParam.equal.cardType']=cardType;
			}
			// 开卡机构
			if(openCardOfficeCode){
				parms['requestParam.equal.openCardOfficeCode']=openCardOfficeCode;
			}
			
			if(userName){
				parms['requestParam.like.userName']="%"+userName+"%";
			}
			if(userRegPhone){
				parms['requestParam.like.userRegPhone']="%"+userRegPhone+"%";
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},

		flushCodeList : function(row, op) {
			window.parent.indexPage.initAllCodeInfo();
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
		},
		output : function() {
			// $("#loadingExcel").show();
			var parms = window.memberTradeList.cache.grid.get('parms');
			var parmStr = "";
			var i = 0;
			$(parms).each(function() {

				if (this.name == 'requestParam.equal.cardNum' || this.name == 'requestParam.equal.ownOrg' || this.name == 'requestParam.equal.tradeType' || this.name == 'requestParam.startwith.tradeTime' || this.name == 'requestParam.endwith.tradeTime'

				)
				// 此处对like条件进行特殊处理，便于使用普通方式传参，后台再进行转换处理
				if (this.value && i > 0) {
					if (this.value != "请选择") {
						parmStr += "&" + this.name + "=" + this.value;
					}

				} else {
					if (this.value && i == 0) {
						if (this.value != "请选择") {
							parmStr += this.name + "=" + this.value;
						}
					}
				}
				i++;

			});
			var excelUrl = root + '/trade/outputMemberTradeList.action';
			if (parmStr != "") {
				excelUrl += "?" + parmStr;
			}
			$("#loadExcel").attr("src", excelUrl);
			// window.location.href=excelUrl;
			// $("#loadingExcel").hide();
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
		window.memberTradeList = new MemberTradeList();
	});
})();