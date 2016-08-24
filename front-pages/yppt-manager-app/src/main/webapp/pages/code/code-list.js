/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var CodeList = function() {
		this.init();
	};
	CodeList.prototype = {
		config : {
			queryTreeUrl : root + '/code/queryList.action',
			queryGridUrl : root + '/code/queryListPage.action',
			modifyStatusUrl : root + '/code/modifyStatus.action',
			deleteUrl : root + '/code/deleteCode.action',
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
			subPageId : null,
			pCodeId : null,
			pCode:null,
			pCodeName:null
		},
		init : function() {
			this.render();
			this.loadGird();
		},
		render : function() {
			var that = this;
			$('#pCodeId').val('0');
			$('#pCode').val('root');
			$('#pCodeName').text('码表信息');
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

			that.initTree();

			$('.l-layout-header-toggle').css('z-index', '2');
			$('#pageloading').hide();

		},
		// 高度改变
		_heightChanged : function(options) {
			var that = window.codeList;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},

		initTree : function() {
			var that = this;
			that.cache.accordion1=$('#accordion1').treeSelect({
				title : '码表信息',
				top : '-25',
				url : that.config.queryTreeUrl,
				// params:{'requestParam.equal.pid':'0'},
				idFieldName : 'id',
				textFieldName : 'name',
				parentIDFieldName : 'pid',
				attribute : [ 'name', 'id' ],
				onClick : function(node) {
					that.cache.node = node;
					if (!node.data.name)
						return;
					var code = node.data.code;
					if (!code) {
						$(data).each(function() {
							if (this.id == node.data.id)
								code = this.code;
						});
					}
					$('#pCodeId').val(node.data.id);
					$('#pCode').val(code);
					$('#pCodeName').text(node.data.name);
					$('#queryCodeDiv').find('#query1').click();// 触发查询按钮

				}
			});
		},

		loadGird : function() {
			var that = this;
			// ////////////生成表格上面的按钮菜单///////////////////////////////
			var myitems = [];
			var funItems = [];
			funItems = FUNCTION_UTIL.getPageFunction(
					window.frameElement.id, [ 'code-add']);
			$(funItems).each(function() {
				myitems.push({
					line : true
				});
				var item = {};
				var click = null;
				if ('code-add' == this.permId) {
					click = that.add;
				}
				item['text'] = this.text;
				item['click'] = click;
				item['icon'] = this.icon;
				myitems.push(item);
			});
			// 获取操作列功能权限
			funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, [
					'code-update', 'code-delete' ]);
			var options = {};
			var columns = [ {
				display : '编码值',
				name : 'code',
				width : '15%',
				align : 'left'
			}, {
				display : '编码名称',
				name : 'name',
				width : '15%',
				align : 'left'
			}, {
				display : '类型编码',
				name : 'typeCode',
				width : '10%',
				align : 'center'
			}, {
				display : '类型名称',
				name : 'typeName',
				width : '15%',
				align : 'left'
			}, 
			{
				display : '描述',
				name : 'description',
				width : '25%',
				align : 'left',
				type : 'none'
			}, {
				display : '操作',
				align : 'center',
				width : '15%',
				render : function(row) {
					var str = '';
					$(funItems).each(function() {
						if (this.permId == 'code-update')
							str += '<a href="javascript:window.codeList.modify(\'' + row.id + '\')">修改</a>&nbsp;&nbsp;';
						if (this.permId == 'code-delete')
							str += '<a href="javascript:window.codeList.del(\'' + row.id + '\',\'' + row.status + '\')">删除</a>&nbsp;&nbsp;';
						
					});
					return str;
				}
			} ];
			options['enabledEdit'] = true;
			options['columns'] = columns;
			options['checkbox'] = false;
			options['frozenCheckbox'] = false;
			options['sortName'] = 'name';
			options['rownumbers']=true;
			options['parms'] = [ {
				name : 'requestParam.equal.pid',
				value : '0'
			} ]; // 设置默认进来的请求参数
			options['toolbar'] = {
				items : myitems
			};// 表格上面菜单

			MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
			$('#codeGrid').ligerGrid(options);
			that.cache.grid = $('#codeGrid').ligerGetGridManager();
			$('#codeGrid').css('top', '0px');
			$('#pageloading').hide();// 加载图片
		},

		// 查询
		search : function() {
			var that = this;
			var parms = {};
			var tem = [];
			parms = tem;

			var codeName = $('#queryCodeDiv').find('#codeName').val();
			var code = $('#queryCodeDiv').find('#code').val();
			var pid = $('#queryCodeDiv').find('#pCodeId').val();
			if (pid) {
				parms.push({
					name : 'requestParam.equal.pid',
					value : pid
				});
			}
			if (codeName) {
				parms.push({
					name : 'requestParam.like.name',
					value : '%' + codeName + '%'
				});
			}
			if (code) {
				parms.push({
					name : 'requestParam.like.code',
					value : '%' + code + '%'
				});
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},
		// 添加信息
		add : function() {
			var that = window.codeList;
			that.cache.pCodeId = $('#pCodeId').val();
			that.cache.pCode = $('#pCode').val();
			that.cache.pCodeName = $('#pCodeName').text();
			that.cache.subPageId = 'add';
			var title = '添加编码信息';
			var url = 'code-add.jsp';
			that.cache.dialog = MALQ_UI.open(url, that.config.subHeight, that.config.subWidth, title);
		},
		// 修改信息
		modify : function(id) {
			var that = window.codeList;
			that.cache.subPageId = 'edit';
			that.cache.pCodeId = $('#pCodeId').val();
			that.cache.pCode = $('#pCode').val();
			that.cache.pCodeName = $('#pCodeName').text();
			var title = '修改编码信息';
			var url = 'code-add.jsp';
			that.cache.codeId = id;
			that.cache.dialog = MALQ_UI.open(url, that.config.subHeight, that.config.subWidth, title);
		},
		// 删除
		del : function(id, status) {
			JSWK.clewBox('码表信息暂时不允许删除!', 'clew_ico_fail', failShowTime);
		},

		flushCodeList : function(row, op) {
		 	if(parent.window.CodeData && parent.window.CodeData.length>0){
        		if(op=='add'){
        			row['id']='upp-tem-'+new Date().getTime();
        			var rootParent=parent.window.CodeData;
        			rootParent.push(row);
        		}else if(op=='delete'){
        			var tem = [];
        			$(parent.window.CodeData).each(function(){
        				if(this.id==row.id)
        					return true;
        				tem.push(this);
        			});
        			parent.window.CodeData=tem;
        		}else if(op=='edit'){
        			$(parent.window.CodeData).each(function(){
        				if(this.id==row.id){
        					if(row.code && row.code != this.code)
        						this['code']=row.code;
        					if(row.name && row.name != this.name)
        						this['name']=row.name;
        					if(row.sort && row.sort != this.sort)
        						this['sort']=row.sort;
        					if(row.description && row.description != this.description)
        						this['description']=row.description;
        					return false;
        				}
        			});
        		}
        	}	
        	//刷新本页面
        	this.cache.accordion1._initTree();
		},

		reset : function() {
			$('#codeName').val('');
			$('#code').val('');
		},
		success_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_succ');
		},
		fail_alert : function(data) {
			JSWK.clewBox(data, 'clew_ico_fail', failShowTime);
		}
	};

	$(document).ready(function() {
		window.codeList = new CodeList();

	});
})();