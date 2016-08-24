/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 */
(function() {
	var ApplyList = function() {
		this.init();
	};
	ApplyList.prototype = {
		config : {
			queryGridUrl : root+'/apply/getApplyListPage.action',
//			queryGridUrl : root+'/apply/queryList.action',
			queryObjectUrl :  root+'/apply/getApplyMeta.action',
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
			this.initMsg();
			this.dateInit();
			this.loadGird();
		},
		//高度改变
		_heightChanged : function(options){
			var that=window.applyList;
	        if(that.cache.tab){
	          that.cache.tab.addHeight(options.diff);	
	        }
	        if (that.cache.accordion && options.middleHeight - 24 > 0){
	          that.cache.accordion.setHeight(options.middleHeight - 24);	
	        }  
	    },
		initMsg : function() {
			var that=this;
			//加载码表
			//CODE_UTIL.getSelectByCodeType($('#status'), 'IC-APPLY-STAT', 180, 80);
			var stats = CODE_UTIL.getCodeData('IC-APPLY-STAT');
			var tem=[];
			$(stats).each(function(x,n){
				if(n.code=='05'||n.code=='02') return true;
				tem.push(n);
			});
			tem.unshift({'name':'请选择','code':''});
			$('#status').ligerComboBox({
				data: tem,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 140,
	  			selectBoxHeight: 180,
	  			width:140
			});
			CODE_UTIL.getSelectByCodeType($('#applyType'), 'APPLY-TYPE', 130, 80);
			var data = CODE_UTIL.getCodeData('IC-CARD-TYPE');
			var towData = [];
			data.unshift({'name':'请选择','code':''});
			$('#cardType').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 180,
	  			width:180,
	  			onSelected: function (value){
	  				towData = CODE_UTIL.getCodeData(value);
	  				towData.unshift({'name':'请选择','code':''});
	  				$('#openCardOrgCode').ligerGetComboBoxManager().setData(towData);
	  			} 
	  		});
			//发卡地区
	  		towData.unshift({'name':'请选择','code':'-'});
	  		$('#openCardOrgCode').ligerComboBox({
	  			data: towData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 180,
	  			selectBoxHeight: 180,
	  			width:180,
	  			onSelected: function (value){
	  				
	  			} 
	  		});
		},
		dateInit : function(){
			$("#createTimeStart").ligerDateEditor({
				width:130,
				showTime :true,
				format  : "yyyy-MM-dd"
			}); 
			$("#createTimeEnd").ligerDateEditor({
				width:130,
				showTime :true,
				format  : "yyyy-MM-dd"
		    }); 
		},
	   
        loadGird : function(){
	   		 var that=this;
	   		 //////////////生成表格上面的按钮菜单///////////////////////////////
	   		 	var myitems = [];
	   		 	
	   		 var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, [
	   		                                             					'apply-check-01','apply-check-02','apply-check-03', 'apply-info' ]);
	   		 
	   		 
	   		 
	   		 
	   		 
//	   		funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id,['code-edit','code-delete','code-edit-status']);
//	   		funItems = FUNCTION_UTIL.getPageFunction("",['code-edit','code-delete','code-edit-status']);
	   		//////////////生成表格上面的按钮菜单////////////////////////////	
	   		 var options = {};
	   		 var columns = [
	   		    		    { display: '发卡类型', name: 'cardType', width:'8%', align: 'center',
	   		    		    	render:function(row){
	   		    		    		return CODE_UTIL.getCodeName('IC-CARD-TYPE',row.cardType);
	   		    		    	}
	   		    		    },
	   		    			{ display: '发卡地区', name: 'openCardOrgCode', width:'15%',align: 'center',
	   		    				render:function(row){
	   		    					if(row.openCardOrgCode){
	   		    						return CODE_UTIL.getCodeName(row.cardType,row.openCardOrgCode);
	   		    					}
	   		    					return "";
	   		    				}
	   		    			},
	   		    		    { display: '卡数量', name: 'cardNum', width:'9%',align: 'center'},
	   		    		    { display: '注册手机号', name: 'userRegPhone', width:'8%',align: 'center'},
		   		    		{ display: '会员名', name: 'userName', width:'8%',align: 'center'},
	   		    			{ display: '申请时间', name: 'createTime', width:'15%',align: 'center',
	   		    				render:function(row){
	   		    					var time = row.createTime;
	   		    					return getFormatDate(new Date(time), 'yyyy-MM-dd hh:mm:ss'); 
	   		    				}
	   		    			},
	   		    			{ display: '申请类型', name: 'applyType', width:'6%',align: 'center',
	   		    				render:function(row){
	   		    					var str = CODE_UTIL.getCodeName('APPLY-TYPE',row.applyType);
	   		    					return str;
	   		    				}
	   		    			},
	   		    			{ display: '审核状态', name: 'status', width:'8%',align: 'center',
	   		    				render:function(row){
	   		    					status = row.status==null?'':row.status;
	   		    					if(status=='02'){//客服审核通过
	   		    						return CODE_UTIL.getCodeName('IC-APPLY-STAT','03');
	   		    					}else if(status=='05'){//卡务审核通过
	   		    						return CODE_UTIL.getCodeName('IC-APPLY-STAT','06');
	   		    					}else if(status=='08'){
	   		    						return "审核完成";
	   		    					}
	   		    					return CODE_UTIL.getCodeName('IC-APPLY-STAT',row.status);
	   		    				}
	   		    			},
	   		    			{ display: '审核人', name: 'modifier', width:'6%',align: 'left',
	   		    				render:function(row){
	   		    					return row.modifierName;
	   		    				}
	   		    			},
	   		    			{ display: '数据来源', name: 'dataSource', width:'6%',align: 'center',
	   		    				render:function(row){
	   		    					return CODE_UTIL.getCodeName('CARD-DATASOURCE',row.dataSource);
	   		    				}
	   		    			},
	   		    			{ display: '操作', name: '',width:'10%', align: 'center',
	                            render : function (row){
	                            	var str = '';
	                            	var wfs = '';
	                            	// 通过权限唯一标示生成操作列按钮
	    							$(funItems)
	    									.each(
	    											function() {
	    												if ('apply-check-01' == this.permId) {
	    													wfs=row.status;
	    													if(row.status=='00' || row.status=='01'){
	    														str+='<a href="javascript:window.applyList.check(\''+ row.id+'\',\''+wfs+'\')">审核</a>&nbsp;&nbsp;';
	    													}
	    												}
	    												if ('apply-check-02' == this.permId) {
	    													wfs=row.status;
	    													if(row.status=='02' || row.status=='03' || row.status=='04'){
	    														str+='<a href="javascript:window.applyList.check(\''+ row.id+'\',\''+wfs+'\')">审核</a>&nbsp;&nbsp;';
	    													}
	    												}
	    												if ('apply-check-03' == this.permId) {
	    													wfs=row.status;
	    													if(row.status=='05' || row.status=='06' || row.status=='07'){
	    														str+='<a href="javascript:window.applyList.check(\''+ row.id+'\',\''+wfs+'\')">审核</a>&nbsp;&nbsp;';
	    													}
	    												}
	    												if ('apply-info' == this.permId) {
	    													str+='<a href="javascript:window.applyList.showInfo(\''+ row.id+'\')">查看</a>&nbsp;'; 
	    												}
	    											});
	    							return str;
	   		    				}
                          }
	   		    		 	
	   		             ]; 
	   		
            
            //options['enabledEdit']=true;
	   		 options['columns']=columns;
	   		 options['checkbox']=true;
	   		 options['frozenCheckbox']=false;
	   		 options['sortName']='createTime';	
	   		 options['sortOrder']='desc';	 
	   		 //options['parms']=[{name: 'requestParam.equal.pid', value: '0'}]; //设置默认进来的请求参数 
//	   	     options['toolbar']={ items: myitems};//表格上面菜单
	   		 options['pageSize']=15;
	   		 options['usePager']=true;
	   		
	   		 
	   		MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
	   		$('#applyGrid').ligerGrid(options);		 
           that.cache.grid =$('#applyGrid').ligerGetGridManager();          
           $('#applyGrid').css('top','0px');
           $('#pageloading').hide();//加载图片
        },
        
      //查询
        search: function(){
        	var that=this;
        	var parms=[];
	       	var openCardOrgCode =$('#openCardOrgCode_val').val();
	        var status = $('#status').val();
	        var cardType = $('#cardType_val').val();
	        var userRegPhone = $('#userRegPhone1').val(); 
	        var userName = $('#userName').val();
	        var applyType = $('#applyType_val').val();
	        var status = $("#status_val").val();

		    var createTimeStart = getFormatDataToLong($("#createTimeStart").val(),false);
 	        var createTimeEnd = getFormatDataToLong($("#createTimeEnd").val(),true);
			if(createTimeStart!=0&&createTimeEnd!=0 && createTimeStart>=createTimeEnd){
			 JSWK.clewBox('结束时间必须大开始时间','clew_ico_fail');
				 return false;
			}
		    if(createTimeStart>0){
//		       parms['requestParam.startwith.createTime']=createTimeStart;
		       parms.push({name:'requestParam.startwith.createTime',value:createTimeStart});
		    }
		    if(createTimeEnd>0){
//		    	parms['requestParam.endwith.createTime']=createTimeEnd;
		    	parms.push({name:'requestParam.endwith.createTime',value:createTimeEnd});
		    }
     	   	
        	if(openCardOrgCode){
        		parms.push({name:'requestParam.equal.openCardOrgCode',value:openCardOrgCode});
        	}
        	if(status=='03'){
        		parms.push({name:'requestParam.inMap.status',value:status+",02"});
        	}
        	if(status=='06'){
        		parms.push({name:'requestParam.inMap.status',value:status+",05"});
        	}
        	if(status){
        		parms.push({name:'requestParam.equal.status',value:status});
        	}
        	if(cardType){
        		parms.push({name:'requestParam.equal.cardType',value:cardType});
        	}
        	if(applyType){
        		parms.push({name:'requestParam.equal.applyType',value:applyType});
        	}

        	if(userRegPhone){
        		parms.push({name:'requestParam.like.userRegPhone', value:"%"+userRegPhone+"%"});
        	}

        	if(userName){
        		parms.push({name:'requestParam.like.userName', value:"%"+userName+"%"});
        	}
        	
        	that.cache.grid.set('parms', parms);
        	that.cache.grid.changePage('first');
        	that.cache.grid.loadData(true);
        },
   	   
        
        flushCodeList : function(row, op){
        	window.parent.indexPage.initAllCodeInfo();
        },
        
        check:function(id,wfs){//wfs:流程状态01客服02业务03行政
       	 	var that = this;
        	var title = 'IC卡开卡申请';      
        	var url = 'apply-check.jsp';
        	that.cache.applyId=id;
        	that.cache.wfs=wfs;
        	var params = {
					'model.id' : id,
					'model.status':wfs
				};
			JAjax(that.config.queryObjectUrl, params, 'json',
					function(data) {
						var result = data.dataObject;
						if(result=='0'){//有审核人
							JSWK.clewBox('正在审核中！', 'clew_ico_fail', 1000);
						}else if(result=='1'){
							JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
						}else{
//							MALQ_UI.open_button(url, 400, 800,null, window.applyList.cancel, title);
							that.cache.dialog = $.ligerDialog.open({
								url : url,
								height : 500,
								width : 900,
								modal : true,
								title : title,
								isResize:true,
								overflow:'',
								allowClose:true,
								csm_grid : applyList.grid,//关闭窗口后，刷新grid列表
								buttons:[
									         { text: '关闭', 
									           onclick: function (item, dialog) { 
									        	window.applyList.cache.grid.loadData();
									        	window.applyList.cache.dialog.close();
									           } 
									         } 
								         ]
							});
						}
					}, function() {
						JSWK.clewBox('提交数据时发生异常', 'clew_ico_fail', 1000);
					}, true);
        },
        cancel:function(){
        	window.applyList.cache.grid.loadData();
        	window.applyList.cache.dialog.close();
        },
        showInfo:function(id){
       	 	var that = window.applyList;
        	var title = 'IC卡开卡申请详情';      
        	var url = 'apply-info.jsp';
        	var isResize=false;
        	that.cache.codeId=id;
//        	MALQ_UI.open_button(url, 400, 800,null, window.applyList.cancel, title);
           	that.cache.dialog = $.ligerDialog.open({
				url : url,
				height : 450,
				width : 800,
				modal : true,
				title : title,
				showToggle:true,
				isResize:true,
				allowClose:true,
				csm_grid : applyList.grid,//关闭窗口后，刷新grid列表
				buttons:[
					         { text: '关闭', 
					           onclick: function (item, dialog) { 
					        	   window.applyList.cache.grid.loadData();
					        	   window.applyList.cache.dialog.close();
					           } 
					         } 
				         ]
			});
        },
        reset : function(){
        	var that = this;
        	$("#queryForm").find("input[type='text']").val('');
        	$("#queryForm").find("input[type='hidden']").val('');
        },
        success_alert : function(data){		        	
	        JSWK.clewBox(data,'clew_ico_succ');
	     },
	     fail_alert : function(data){
	        JSWK.clewBox(data,'clew_ico_fail',failShowTime);
	     }
	};
	
	$(document).ready(function() {
		window.applyList = new ApplyList();
	});
})();   