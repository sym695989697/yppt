/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 */
(function() {
	var SyncList = function() {
		this.init();
	};
	SyncList.prototype = {
		config : {
			queryGridUrl : root+'/syncUser/getSyncListPage.action',
			refreshUrl : root+'/syncUser/updateUserById.action',
			refreshAllUrl : root + '/syncUser/updateUserAll.action',
			subHeight : subHeight,
			subWidth : subWidth
		},
		cache : {
			grid : null,
			accordion : null,
			tab :null,
			node : null,
			dialog : null,
			codeId:null,
			applyId:null,
			wfs:null,
			parms : {},
			subPageId : null,
			pCodeId : null
		},
		init : function(){
			this.loadGird();
		},
		render : function(){
			
		    
		},
		//高度改变
		_heightChanged : function(options){
			var that=window.SyncList;
	        if(that.cache.tab){
	          that.cache.tab.addHeight(options.diff);	
	        }
	        if (that.cache.accordion && options.middleHeight - 24 > 0){
	          that.cache.accordion.setHeight(options.middleHeight - 24);	
	        }  
	    },
        loadGird : function(){
	   		 var that=this;
	   		 //////////////生成表格上面的按钮菜单///////////////////////////////
	   		 var myitems = [];
	   		 myitems.push({
				line : true
			 });
	   		var item = {};
	   		var flag = true;
			var click = function(){
				if(flag){
					flag = false;
					window.SyncList.refreshAll();
				}else{
					JSWK.clewBox('请不要重复点击', 'clew_ico_fail', 1000);
				}
			};
			item['text'] = "刷新所有记录";
			item['click'] = click;
			item['icon'] = "refresh";
			myitems.push(item);
	   		 
	   		var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, ['apply-check-01','apply-check-02','apply-check-03', 'apply-info' ]);
	   		 
	   		//////////////生成表格上面的按钮菜单////////////////////////////	
	   		 var options = {};
	   		 var columns = [
	   		                { display: '卡号', name: 'cardNumber', width:'15%', align: 'center'},
	   		    		    { display: '用户中心ID', name: 'userId', width:'15%', align: 'center'},
	   		    		    { display: '用户名称', name: 'userName', width:'10%',align: 'center'},
	   		    			{ display: '用户类型', name: 'userType', width:'10%',align: 'center',
	   		    				render:function(row){
	   		    					var userType = row.userType;
	   		    					if(userType=='1'){
	   		    						return '企业用户';
	   		    					}else if(userType=='2'){
	   		    						return '个人用户';
	   		    					}
	   		    					return ''; 
	   		    				}
	   		    			
	   		    			},
	   		    		    { display: '注册手机号', name: 'userRegPhone', width:'15%',align: 'center'},
	   		    			{ display: '创建时间', name: 'createdTime', width:'10%',align: 'center',
	   		    				render:function(row){
	   		    					var time = row.createdTime;
	   		    					return getFormatDate(new Date(time), 'yyyy-MM-dd hh:mm:ss'); 
	   		    				}
	   		    			},
	   		    			{ display: '修改时间', name: 'modifiedTime', width:'10%',align: 'center',
	   		    				render:function(row){
	   		    					var time = row.modifiedTime;
	   		    					return getFormatDate(new Date(time), 'yyyy-MM-dd hh:mm:ss'); 
	   		    				}
	   		    			},
	   		    			{ display: '操作', name: '',width:'12%', align: 'center',
	                            render : function (row){
	                            	/*var str = '';
	                            	var wfs = '';
	                            	// 通过权限唯一标示生成操作列按钮
	    							$(funItems)
	    									.each(
	    											function() {
	    												if ('apply-check-01' == this.permId) {
	    													wfs=row.status;
	    													if(row.status=='00' || row.status=='01'){
	    														str+='<a href="javascript:window.SyncList.check(\''+ row.id+'\',\''+wfs+'\')">审核</a>&nbsp;&nbsp;';
	    													}
	    												}
	    												if ('apply-info' == this.permId) {
	    													str+='<a href="javascript:window.SyncList.showInfo(\''+ row.id+'\')">查看</a>&nbsp;'; 
	    												}
	    											});
	    							return str;*/
	                            	return '<a href="javascript:window.SyncList.refresh(\'' + row.id + '\')">刷新数据</a>&nbsp;&nbsp;';
	                            	
	   		    				}
                          }
	   		    		 	
	   		             ]; 
	   		
            
            //options['enabledEdit']=true;
	   		 options['columns']=columns;
	   		 options['checkbox']=false;
	   		 options['rownumbers']=true;
	   		 options['frozenCheckbox']=false;
	   		 options['sortName']='createTime';	
	   		 options['sortOrder']='desc';	 
	   		 //options['parms']=[{name: 'requestParam.equal.pid', value: '0'}]; //设置默认进来的请求参数 
	   	     options['toolbar']={ items: myitems};//表格上面菜单
	   		 options['pageSize']=15;
	   		 options['usePager']=true;
	   		
	   		 
	   		MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
	   		$('#syncGrid').ligerGrid(options);		 
           that.cache.grid =$('#syncGrid').ligerGetGridManager();          
           $('#syncGrid').css('top','0px');
           $('#pageloading').hide();//加载图片
        },
        
        //查询
        search: function(){
        	var that = this;
			var parms = {};
			var tem = [];
			parms = tem;
        	
        	var userId = $('#userId').val();
			var userName = $('#userName').val();
			var userType = $('#userType').val();
			var userRegPhone = $('#userRegPhone').val();
			
			if (userId) {
				parms.push({
					name : 'requestParam.like.userId',
					value : '%' + userId + '%'
				});
			}
			if (userName) {
				parms.push({
					name : 'requestParam.like.userName',
					value : '%' + userName + '%'
				});
			}
			if (userType) {
				parms.push({
					name : 'requestParam.like.userType',
					value : '%' + userType + '%'
				});
			}
			if (userRegPhone) {
				parms.push({
					name : 'requestParam.like.userRegPhone',
					value : '%' + userRegPhone + '%'
				});
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
        },
   	   
        
        flushCodeList : function(row, op){
        	window.parent.indexPage.initAllCodeInfo();
        },
		refresh : function(id) {
			var that = this;
			JAjax(that.config.refreshUrl, {'model.id' : id
			}, 'json', function(data) {
				if(data.message!=null&&data.message!=""){
					JSWK.clewBox(data.message, 'clew_ico_succ', 1000);
				}else{
					JSWK.clewBox("刷新成功！", 'clew_ico_succ', 1000);
					that.cache.grid.loadData();// 刷新grid 
				}
			}, function() {
				JSWK.clewBox('刷新数据时发生异常!', 'clew_ico_fail', 1000);
			}, true);
		},
		refreshAll : function() {
			var that = this;
			JAjax(that.config.refreshAllUrl,'json', function(data) {
				if(data.message!=null&&data.message!=""){
					JSWK.clewBox(data.message, 'clew_ico_succ', 1000);
				}else{
					JSWK.clewBox("刷新成功！", 'clew_ico_succ', 1000);
					that.cache.grid.loadData();// 刷新grid
				}
			}, function() {
				JSWK.clewBox('刷新数据时发生异常!', 'clew_ico_fail', 1000);
			}, true);
		},
        //查询重置按钮
        reset : function(){
 	       $('#userId').val("");
 	       $("#userType").val("");
     	   $("#userName").val("");
     	   $("#userRegPhone").val("");
        },
        success_alert : function(data){		        	
	        JSWK.clewBox(data,'clew_ico_succ');
	     },
	     fail_alert : function(data){
	        JSWK.clewBox(data,'clew_ico_fail',failShowTime);
	     }
	};
	
	$(document).ready(function() {
		window.SyncList = new SyncList();
	});
})();   