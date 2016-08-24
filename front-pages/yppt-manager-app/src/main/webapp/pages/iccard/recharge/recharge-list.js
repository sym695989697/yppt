/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 */
(function() {
	var RechargeList = function() {
		this.init();
	};
	RechargeList.prototype = {
		config : {
			queryGridUrl : root+'/icrechargeapply/getRechargeListPage.action',
			addRechargeApplayUrl:root+'/icrechargeapply/addRechargeApplay.action',
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
			batch:null,
			applyId:null,
			selectedRows:[],
			parms : {},
			info:null,
			subPageId : null,
			isSubmit : false,
			cardCount:null,
			sumMoney:'0.00',
			useCurrencyCount:null,
			isAMember:true,
			regUserPhone:null,
			memberName:null,
			cardParams:{}
			
		},
		init : function(){
			this.formatDate();
			this.initCode();
			this.loadGird();
		},
		//高度改变
		_heightChanged : function(options){
			var that=window.rechargeList;
	        if(that.cache.tab){
	          that.cache.tab.addHeight(options.diff);	
	        }
	        if (that.cache.accordion && options.middleHeight - 24 > 0){
	          that.cache.accordion.setHeight(options.middleHeight - 24);	
	        }  
	    },
		formatDate : function(){
			$("#createTimeStart").ligerDateEditor({
				width:150,
				showTime :true,
				format  : "yyyy-MM-dd"
			}); 
			$("#createTimeEnd").ligerDateEditor({
				width:150,
				showTime :true,
				format  : "yyyy-MM-dd"
		    }); 
		},
		initCode:function(){
			// 申请渠道
			var applyData = CODE_UTIL.getCodeData('CARD-DATASOURCE');
			applyData.unshift({'name':'请选择','code':'-'});
	  		$('#dataSource').ligerComboBox({
	  			data: applyData,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 150,
	  			selectBoxHeight: 180,
	  			width:150
	  		});
			//申请状态
			var data = CODE_UTIL.getCodeData('RECHARGE-STAT');
			data.unshift({'name':'请选择','code':'-'});
	  		$('#state').ligerComboBox({
	  			data: data,
	  			textField :"name",
	  			valueField :"code",
	  			selectBoxWidth: 150,
	  			selectBoxHeight: 180,
	  			width:150
	  		});
		},
		loadGird : function(){
	   		 var that=this;
	   		 //////////////生成表格上面的按钮菜单///////////////////////////////
	   		var myitems = [];
			var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, [ 'recharge-apply-add']);
			$(funItems).each(function() {
				myitems.push({
					line : true
				});
				var item = {};
				var click = null;
				if ('recharge-apply-add' == this.permId) {
					click = that.batchRecharge;
				}
				item['text'] = this.text;
				item['click'] = click;
				item['icon'] = this.icon;
				myitems.push(item);
			});
			// 获取操作列功能权限
			funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id, ['recharge-apply-view','recharge-apply-process' ]);
	   		//////////////生成表格上面的按钮菜单////////////////////////////	
	   		 var options = {};
	   		 var columns = [
	   		    		    { display: '注册手机号', name: 'userRegPhone', width:'8%',isSort : false, align: 'center'},
	   		    			{ display: '客户名称', name: 'userName', width:'10%',isSort : false,align: 'center'},
	   		    		    { display: '申请时间', name: 'applyTime', width:'14%',isSort : false,align: 'center',
	   		    				render:function(row){
	   		    					var time = row.applyTime;
	   		    					return getFormatDate(new Date(time), 'yyyy-MM-dd hh:mm:ss'); 
	   		    				}
	   		    			},
	   		    		    { display: '申请充值总金额（元）', name: 'totalMoney', width:'12%',isSort : false,align: 'center',
	   		    				render:function(row){
		   		    				return row.totalMoney?parseFloat(row.totalMoney/100).toFixed(2):'0.00';
	   		    				}
	   		    		    },
		   		    		{ display: '实际分配总金额（元）', name: 'actualDivMoney', width:'12%',isSort : false,align: 'center',
		   		    		 	render:function(row){
		   		    		 		return row.actualDivMoney?parseFloat(row.actualDivMoney/100).toFixed(2):'0.00';
	   		    				}
		   		    		},
	   		    			{ display: '充值卡数', name: 'cardNum', width:'5%',isSort : false,align: 'center'},
	   		    			{ display: '充值方式', name: 'payWay', width:'5%',isSort : false,align: 'center',
	   		    				render:function(row){
	   		    					return CODE_UTIL.getCodeName('IC-PAY-WAY',row.payWay);
	   		    				}
	   		    			},
	   		    			{ display: '充值状态', name: 'state', width:'8%',isSort : false,align: 'center',
	   		    				render:function(row){
	   		    					return CODE_UTIL.getCodeName('RECHARGE-STAT',row.state);;
	   		    				}
	   		    			},
	   		    			{ display: '申请渠道', name: 'dataSource', width:'6%',isSort : false,align: 'left',
	   		    				render:function(row){
	   		    					return CODE_UTIL.getCodeName('CARD-DATASOURCE',row.dataSource);
	   		    				}
	   		    			},
	   		    			{ display: '操作人', name: 'approvalPersonName', width:'6%',isSort : false,align: 'center'},
	   		    			{ display: '操作', name: '',width:'10%', isSort : false,align: 'center',
	                            render : function (row){
	                            	var str = '';
	                            	// 通过权限唯一标示生成操作列按钮
	    							$(funItems)
	    									.each(
	    											function() {
	    												if ('recharge-apply-view' == this.permId) {
	    													str += '<a href="javascript:window.rechargeList.show(\''+ row.id+'\')">查看</a>&nbsp;&nbsp;';
	    												}
	    												if ('recharge-apply-process' == this.permId) {
	    													if(row.state=='04' || row.state=='06'){
	    														str += '<a href="javascript:window.rechargeList.handle(\''+ row.id+'\')">处理</a>&nbsp;&nbsp;';
	    													}else{
	    														str+='';
	    													}
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
	   		 options['sortName']='applyTime';	
	   		 options['sortOrder']='desc';	 
	   		 //options['parms']=[{name: 'requestParam.equal.pid', value: '0'}]; //设置默认进来的请求参数 
	   	     options['toolbar']={ items: myitems};//表格上面菜单
	   		 options['pageSize']=30;
	   		 options['usePager']=true;
	   		MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
	   		$('#rechargeGrid').ligerGrid(options);		 
           that.cache.grid =$('#rechargeGrid').ligerGetGridManager();          
           $('#rechargeGrid').css('top','0px');
           $('#pageloading').hide();//加载图片
        },
        
      //查询
        search: function(){
        	var that=this;
	       	var parms=[];
	       	var userRegPhone =$('#userRegPhone').val();
	        var dataSource = $('#dataSource_val').val();
	        var state = $('#state_val').val(); 
    	    var createTimeStart = getFormatDataToLong($("#createTimeStart").val(),false);
 	        var createTimeEnd = getFormatDataToLong($("#createTimeEnd").val(),true);
			if(createTimeStart!=0&&createTimeEnd!=0 && createTimeStart>=createTimeEnd){
			 JSWK.clewBox('结束时间必须大开始时间','clew_ico_fail');
				 return false;
			}
		    if(createTimeStart>0){
//		       parms['requestParam.startwith.applyTime']=createTimeStart;
		    	parms.push({name:'requestParam.startwith.applyTime', value:createTimeStart});
		    }
		    if(createTimeEnd>0){
//		    	parms['requestParam.endwith.applyTime']=createTimeEnd;
		    	parms.push({name:'requestParam.startwith.applyTime', value:createTimeEnd});
		    }
        	
        	if(state){
        		parms.push({name:'requestParam.equal.state', value:state});
        	}

        	if(userRegPhone){
//        		parms['requestParam.like.userRegPhone']="%"+userRegPhone+"%";
        		parms.push({name:'requestParam.like.userRegPhone', value:"%"+userRegPhone+"%"});
        	}

        	if(dataSource){
        		parms.push({name:'requestParam.equal.dataSource', value:dataSource});
        	}
        	
        	that.cache.grid.set('parms', parms);
        	that.cache.grid.changePage('first');
        	that.cache.grid.loadData(true);
        },
        getFormatDataToLong:function(){
        	
        },
        show:function(applyId){
        	var title = '充值详情查看';
			var url = 'recharge-info.jsp?applyId='+applyId;
			var options = {btnOptions:[
		        		               { text: '关闭', onclick: window.rechargeList.cancel }],
		        		               onCloseBefore:window.rechargeList.oncloseDialog_0
		        						};
			this.cache.info = MALQ_UI.open_many_button(url, 450, 900,options, title);
        },
        handle:function(applyId){
        	var title = '分配确认';
			var url = 'recharge-handle.jsp?applyId='+applyId;
			var options = {btnOptions:[
		        		               { text: '关闭', onclick: window.rechargeList.cancel }],
								onCloseBefore:window.rechargeList.oncloseDialog_0
		        						};
			this.cache.dialog = MALQ_UI.open_many_button(url, 450, 900,options, title);
        },
        //批量充值第一部--选择IC卡
        batchRecharge:function(item, dialog){
        	var title = '选择IC卡进行充值';
			var url = 'recharge-batch1.jsp';
			var options = {btnOptions:[
		        		               { text: '下一步', onclick: window.rechargeList.next_2 }],
		        		               onCloseBefore:window.rechargeList.oncloseDialog_0
		        						};
			window.rechargeList.cache.dialog = MALQ_UI.open_many_button(url, 400, 800,options, title);
        },
        //批量充值第二步--输入充值金额
        next_2:function(item, dialog){
        	if(dialog.frame.window.viceCardList){
        		dialog.frame.window.viceCardList.getSelectRowsToParent();
        	}
        	if(window.rechargeList.cache.selectedRows!=null && window.rechargeList.cache.selectedRows.length>0 && window.rechargeList.cache.isAMember){
        		dialog.close();
        		//重置金额和卡数
        		window.rechargeList.cache.cardCount=null;
    			window.rechargeList.cache.sumMoney='0.00';
    			window.rechargeList.cache.regUserPhone=window.rechargeList.cache.selectedRows[0].userRegPhone;
    			window.rechargeList.cache.memberName=window.rechargeList.cache.selectedRows[0].userName;
        		var title = '请输入充值金额';
        		var url = 'recharge-batch2.jsp';
        		window.rechargeList.cache.cardCount=window.rechargeList.cache.selectedRows.length;
        		var options = {btnOptions:[{ text: '上一步', onclick:window.rechargeList.pre_1  },
        		               { text: '下一步', onclick: window.rechargeList.next_3 },
        		               { text: '重置金额', onclick: window.rechargeList.resert_2 }],
        		               onCloseBefore:window.rechargeList.oncloseDialog
        						};
        		window.rechargeList.cache.dialog = MALQ_UI.open_many_button(url, 400, 800,options, title);
        	}
        },
        //批量充值第三步--确定充值申请
        next_3:function(item, dialog){
        	dialog.frame.window.rechargeBatch2.saveMoneyToCache();
        	if(window.rechargeList.cache.sumMoney && window.rechargeList.cache.sumMoney!='0.00'){
        		dialog.close();
        		var title = '确定充值申请';
        		var url = 'recharge-batch3.jsp';
        		var options={
        						btnOptions:[{ text: '上一步', onclick:window.rechargeList.pre_2  },{ text: '确定充值', onclick: window.rechargeList.rechargeSubmit }],
        						onCloseBefore:window.rechargeList.oncloseDialog
        		             };
        		
        		window.rechargeList.cache.dialog = MALQ_UI.open_many_button(url, 400, 800,options,title);
        	}
        },
        //重置第二步金额
        resert_2:function(item, dialog){
        	dialog.frame.window.rechargeBatch2.resert_money();
        },
        //上一步--to 第一步
        pre_1:function(item, dialog){
        	dialog.close();
        	window.rechargeList.batchRecharge();
        },
        //上一步--to 第二步
        pre_2:function(item, dialog){
        	dialog.close();
        	var title = '请输入充值金额';
    		var url = 'recharge-batch2.jsp';
    		var options = {btnOptions:[{ text: '上一步', onclick:window.rechargeList.pre_1  },
    	        		               { text: '下一步', onclick: window.rechargeList.next_3 },
    	        		               { text: '重置金额', onclick: window.rechargeList.resert_2 }],
    	        		               onCloseBefore:window.rechargeList.oncloseDialog
    	        						};
    		window.rechargeList.cache.dialog = MALQ_UI.open_many_button(url, 400, 800,options, title);
        },
        //确定充值--提交
        rechargeSubmit:function(item, dialog){
        	if(window.rechargeList.cache.isSubmit){//防止重复提交标识 
        		return ;
        	}
        	window.rechargeList.cache.isSubmit=true; //修改防止重复提交标识 
        	var that=window.rechargeList;
        	var params={};
        	params['metaBean.rechargeApply.totalMoney']=window.rechargeList.cache.sumMoney*100;//充值金额
        	params['metaBean.rechargeApply.captialMoney']=window.rechargeList.cache.sumMoney*100;//充值金额
        	params['metaBean.rechargeApply.cardNum']=window.rechargeList.cache.cardCount;///充值卡数量
        	params['metaBean.rechargeApply.dataSource']='2';//数据来源 后台
        	$(window.rechargeList.cache.selectedRows).each(function(i,n){
        		if(i==0){
        			params['metaBean.rechargeApply.userId']=n.userId;//用户统一认证id
                	params['metaBean.rechargeApply.userName']=n.userName;//用户名称
                	params['metaBean.rechargeApply.userRegPhone']=n.userRegPhone;//注册手机号
                	params['metaBean.rechargeApply.userLoginName']=n.userRegPhone;//登录用户名
        		}
        		params['metaBean.rechargeDeatils['+i+'].cardId']=n.id;//卡id
            	params['metaBean.rechargeDeatils['+i+'].cardNo']=n.cardNumber;//卡号
            	params['metaBean.rechargeDeatils['+i+'].cardType']=n.cardType;//卡类型
            	params['metaBean.rechargeDeatils['+i+'].vehicleNo']=n.vehicleNo;//车牌号
            	params['metaBean.rechargeDeatils['+i+'].vehicleColor']=n.vehicleColor;//车牌颜色
            	params['metaBean.rechargeDeatils['+i+'].money']=n.rechargeMoney*100;//充值金额
            	params['metaBean.rechargeDeatils['+i+'].cardAreaCode']=n.opencardofficeid;//发卡地区
            	params['metaBean.rechargeDeatils['+i+'].parentCardNumber']=n.parentCardNumber;//主卡卡号
        	});
        	JAjax(that.config.addRechargeApplayUrl,params, 'json', function(data) {
				if(data && data=='1'){
			    	dialog.frame.window.rechargeBatch3.alert_message("充值申请成功，等待充值审核！",window.rechargeList.rechargeSuccess);
				}else{
					dialog.frame.window.rechargeBatch3.alert_message(data,window.rechargeList.rechargeFail);
				}
			}, function() {
				window.rechargeList.cache.isSubmit=false;
				JSWK.clewBox('提交数据时发生异常','clew_ico_fail', failShowTime);
			}, true);
        },
        //充值申请成功提示  右上角关闭 回调 --关闭提示框 清空当前充值申请缓存 关闭窗口
        rechargeSuccess:function(dialog){
        	window.rechargeList.cache.cardCount=null;
			window.rechargeList.cache.sumMoney='0.00';
			window.rechargeList.cache.selectedRows=[];
			window.rechargeList.cache.cardParams={};
	    	window.rechargeList.search();
	    	window.rechargeList.cache.dialog.close();
	    	window.rechargeList.cache.isSubmit=false;
        },
        //充值申请异常提示  右上角关闭 回调 --恢复确认按钮可提交
        rechargeFail:function(dialog){
        	window.rechargeList.cache.isSubmit=false;
        	dialog.close();
        },
        //充值申请过程中 右上角关闭按钮回调 --提示是否确认取消 是：则关闭窗口并清空当前充值申请缓存； 否：则继续
        oncloseDialog:function(dialog){
        	var flag=false;
        	$.ligerDialog.confirm('确定取消该充值申请吗？',
				function(yes) {
					if (yes) {
						window.rechargeList.cache.isSubmit=false;
						window.rechargeList.cache.cardCount=null;
		    			window.rechargeList.cache.sumMoney='0.00';
		    			window.rechargeList.cache.selectedRows=[];
		    			window.rechargeList.cache.cardParams={};
		    			dialog.close();
					}
			});
        },
        //选择IC卡窗口 右上角关闭按钮回调  清空当前充值申请缓存 关闭窗口 
        oncloseDialog_0:function(dialog){
        	window.rechargeList.cache.cardCount=null;
			window.rechargeList.cache.sumMoney='0.00';
			window.rechargeList.cache.selectedRows=[];
			window.rechargeList.cache.cardParams={};
			dialog.close();
        },
        reset : function(){
        	$("#queryForm").find("input[type='text']").val('');
			$("#queryForm").find("input[type='hidden']").val('');
        },
        cancel : function(item, dialog) {
        	window.rechargeList.cache.grid.loadData();
			dialog.close();
		},
        success_alert : function(data){		        	
	        JSWK.clewBox(data,'clew_ico_succ');
	     },
	     fail_alert : function(data){
	        JSWK.clewBox(data,'clew_ico_fail',failShowTime);
	     }
	};
	$(document).ready(function() {
		window.rechargeList = new RechargeList();
	});
})();   