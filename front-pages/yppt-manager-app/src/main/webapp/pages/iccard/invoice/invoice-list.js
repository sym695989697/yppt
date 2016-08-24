/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var InvoiceList = function() {
		this.init();
	};
	InvoiceList.prototype = {
		config : {
			queryUrl : root + '/invoice/queryICCardInvoiceApplyListPage.action',
			checkUrl : root + '/invoice/checkICCardInvoiceApply.action',
			viewUrl : root + '/invoice/viewICCardInvoiceApply.action',
			subHeight : subHeight,
			subWidth : subWidth
		},
		cache : {
			grid : null,
			tree : null,
			accordion : null,
			tab : null,
			node : null,
			dialog : null,
			parms : {},
			id : null,
			subPageId : null
		},
		init : function() {
			this.render();
			this.loadGird();
			CODE_UTIL._initCodeData();
			this.loadSimpleCode();
		},
		
		loadSimpleCode:function(){
			//发票类型
			var data = CODE_UTIL.getCodeData('IC-INVOICE-TYPE');
	  		data.unshift({'name':'请选择','code':''});
	  		$('#invoiceType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140,
	  			onSelected: function (value){
	  			} 
	  		});
			
			//开卡状态
			var datastatus = CODE_UTIL.getCodeData('INVOICE-STATUS');
			datastatus.unshift({'name':'请选择','code':''});
	  		$('#status').ligerComboBox({
	  			data: datastatus,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140,
	  			onSelected: function (value){
	  			} 
	  		});
			
			$("#createTime1").ligerDateEditor({
				width:100,
				showTime :true,
				format  : "yyyy-MM-dd"
			}); 
			$("#createTime2").ligerDateEditor({
				width:100,
				showTime :true,
				format  : "yyyy-MM-dd"
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

		},
		// 高度改变
		_heightChanged : function(options) {
			var that = window.invoiceList;
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
			var funItems = [];

			// 获取操作列功能权限
			funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, ['invoice-open','invoice-check', 'invoice-view' ]);
			var options = {};
			var columns = [
					{ display : '注册手机号',name : 'userRegPhone',align : 'center',width : '10%',isSort : false,
						render : function(row) {
							return row.userRegPhone;
						}
					},
					{ display : '会员名称',name : 'userName',align : 'center',width : '10%',isSort : false},
					{ display : '申请时间',name : 'createTime',align : 'center',width : '10%',isSort : false,
						render : function(row) {
							if(row.createTime != null){
								return  getSmpFormatDateByLong(row.createTime,true);
							}else{
								return null;
							}
	    					
	    				}
					},
					{ display : '发票类型',name : 'invoiceType',align : 'center',width : '10%',isSort : false,
						render:function(row){
	                		if(row.invoiceType)
	                			return CODE_UTIL.getCodeName('IC-INVOICE-TYPE',row.invoiceType);
							else
								return "";
	                	}
					},
					{ display : '发票抬头',name : 'invoiceName',align : 'center',width : '10%',isSort : false},
					{ display : '开票金额',name : 'invoiceMoney',align : 'right',width : '10%',isSort : false,
						render : function(row) {
	                		if(row.invoiceMoney){
	                			return convertFen2Yuan(row.invoiceMoney);
	                		}else{
	                			return "--";
	                		}
	    				}
					},
					{ display : '收件人',name : 'receiverName',align : 'center',width : '10%',isSort : false},
					{ display : '联系电话',name : 'receiverPhoneNum',align : 'center',width : '10%',isSort : false},
					{ display : '开票状态',name : 'status',align : 'center',width : '10%',isSort : false,
						render:function(row){
	                		if(row.status)
	                			return CODE_UTIL.getCodeName('INVOICE-STATUS',row.status);
							else
								return "";
	                	}
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
												if ('invoice-open' == this.permId && row.status == "2") {
													str += '<a href="javascript:window.invoiceList.open(\''
															+ row.id+
															'\',\''+row.invoiceType+'\')">开票</a>&nbsp;&nbsp;';
												}
												if ('invoice-check' == this.permId && row.status == "1") {
													str += '<a href="javascript:window.invoiceList.check(\''
															+ row.id+
															'\',\''+row.invoiceType+'\')">审核</a>&nbsp;&nbsp;';
												}
												if ('invoice-view' == this.permId) {
													str += '<a href="javascript:window.invoiceList.view(\''
															+ row.id+
															'\',\''+row.invoiceType+'\')">查看</a>&nbsp;&nbsp;';
												}
											});
							return str;
						}
					} ];

			options['columns'] = columns;
			options['checkbox'] = true;
			options['frozenCheckbox'] = false;
			options['parms'] = []; // 设置默认进来的请求参数
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单
			options['usePager'] = true;
			MALQ_UI.setGridOptions(options, that.config.queryUrl);

			$('#InvoiceGird').ligerGrid(options);
			that.cache.grid = $('#InvoiceGird').ligerGetGridManager();
			$('#InvoiceGird').css('top', '3px');
			$('#pageloading').hide();// 加载图片
		},

		// 查询
		search : function() {
			var that = this;
			var queryType = $("#queryType").val();//查询条件类型
			var queryName = $("#queryName").val(); //查询条件数值
			var invoiceType = $("#invoiceType_val").val(); //发卡类型
			var invoiceStatus = $("#status_val").val(); //开票状态
			var invoiceName = $("#invoiceName").val(); //发票抬头
			var createTime1 = $("#createTime1").val(); //申请时间开始
			var createTime2 = $("#createTime2").val(); //申请时间结束
			var parms = {};
			
			if(queryName){
				if(queryType == "userName"){
					parms['requestParam.like.userName'] ="%"+queryName+"%";
				}else{
					parms['requestParam.like.receiverPhoneNum'] ="%"+queryName+"%";
				}
			}
			
			if(invoiceType){
				parms['requestParam.equal.invoiceType'] = invoiceType;
			}
			if(invoiceStatus){
				parms['requestParam.equal.invoiceStatus'] = invoiceStatus;
			}
			if(invoiceName){
				parms['requestParam.like.invoiceName']="%"+invoiceName+"%";
			}
			if(createTime1){
				parms['requestParam.equal.startTime'] = createTime1;
			}
			if(createTime2){
				parms['requestParam.equal.endTime'] = createTime2;
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},
		// 查看信息
		view : function(id,invoiceType) {
			var that = window.invoiceList;
			that.cache.subPageId = 'view';
			that.cache.id = id;
			var title = '';
			var url = '';
			if(invoiceType == "01"){
				title = "普通发票";
				url = "invoice-common-view.jsp";
			}else{
				title = "增值税发票";
				url = "invoice-vat-view.jsp";
			}
			that.cache.dialog = MALQ_UI.open(url, that.config.subHeight+100, that.config.subWidth+50, title);
		},
		// 审核信息
		check : function(id,invoiceType) {
			var that = window.invoiceList;
			that.cache.subPageId = 'check';
			that.cache.id = id;
			var title = '';
			var url = '';
			if(invoiceType == "01"){
				title = "普通发票";
				url = "invoice-common.jsp";
			}else{
				title = "增值税发票";
				url = "invoice-vat.jsp";
			}
			that.cache.dialog = MALQ_UI.open(url, that.config.subHeight+100, that.config.subWidth+50, title);
		},
		open : function(id,invoiceType){
			var that = window.invoiceList;
			that.cache.subPageId = 'open';
			that.cache.id = id;
			var title = '';
			var url = '';
			if(invoiceType == "01"){
				title = "普通发票";
				url = "invoice-common-open.jsp";
			}else{
				title = "增值税发票";
				url = "invoice-vat-open.jsp";
			}
			that.cache.dialog = MALQ_UI.open(url, that.config.subHeight+100, that.config.subWidth+50, title);
		},
		reset : function() {
			$("#queryCodeForm").find("input[type='text']").val('');
			$("#queryCodeForm").find("input[type='hidden']").val('');
		},
		success_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_succ');
		},
		fail_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_fail', failShowTime);
		}
	};

	$(document).ready(function() {
		window.invoiceList = new InvoiceList();

	});
})();