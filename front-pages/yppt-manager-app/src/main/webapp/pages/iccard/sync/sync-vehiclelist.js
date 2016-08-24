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
			queryGridUrl : root+'/syncVehicle/getSyncListPage.action',
			refreshUrl : root+'/syncVehicle/updateVehicleById.action',
			refreshAllUrl : root + '/syncVehicle/updateVehicleAll.action',
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
			var click = function(){
				window.SyncList.refreshAll();
			};
			item['text'] = "刷新所有记录";
			item['click'] = click;
			item['icon'] = "refresh";
			myitems.push(item);
	   		 
	   		//var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, ['apply-check-01','apply-check-02','apply-check-03', 'apply-info' ]);
	   		 
	   		//////////////生成表格上面的按钮菜单////////////////////////////	
	   		 var options = {};
	   		 var columns = [
	   		                { display: '卡号', name: 'cardNumber', width:'15%', align: 'center'},
	   		    		    { display: '车辆ID', name: 'vehicleId', width:'20%', align: 'center'},
	   		    		    { display: '车牌号', name: 'vehicleNo', width:'10%',align: 'center',
	   							render:function(row){
	   								if(row.vehicleNo){
	   									var color=CODE_UTIL.getCodeName('V-C-COLOR',row.vehicleColor);
	   									if(color){
	   										return row.vehicleNo+"-"+color;
	   									}
	   									return row.vehicleNo;
	   								}
	   								else{
	   									return "";
	   								}
	   							}
	   		    			},
	   		    		    { display: '车辆行驶证', name: 'vehicleLicense', width:'15%',align: 'center',
	   		    				render:function(row){
	   		    					if(row.vehicleLicense){
	   		    						return '<a href="'+row.vehicleLicense+'" target="_blank">查看</a>'; 
	   		    					}
	   		    				}
	   		    		    },
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
        	
        	var vehicleId = $('#vehicleId').val();
			var vehicleNo = $('#vehicleNo').val();
			var vehicleColor = $('#vehicleColor').val();
			var vehicleLicense = $('#vehicleLicense').val();
			
			if (vehicleId) {
				parms.push({
					name : 'requestParam.like.vehicleId',
					value : '%' + vehicleId + '%'
				});
			}
			if (vehicleNo) {
				parms.push({
					name : 'requestParam.like.vehicleNo',
					value : '%' + vehicleNo + '%'
				});
			}
			if (vehicleColor) {
				parms.push({
					name : 'requestParam.like.vehicleColor',
					value : '%' + vehicleColor + '%'
				});
			}
			if (vehicleLicense) {
				parms.push({
					name : 'requestParam.like.vehicleLicense',
					value : '%' + vehicleLicense + '%'
				});
			}
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
        },
   	   
        
        flushCodeList : function(row, op){
        	window.parent.indexPage.initAllCodeInfo();
        },
        // 在修改页面显示要修改的记录的信息
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
				JSWK.clewBox('刷新数据时发生异常,车辆ID为空!', 'clew_ico_fail', 1000);
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
 	       $('#vehicleId').val("");
 	       $("#vehicleNo").val("");
     	   $("#vehicleColor").val("");
     	   $("#vehicleLicense").val("");
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