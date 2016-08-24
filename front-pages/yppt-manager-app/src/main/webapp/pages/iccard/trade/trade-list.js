/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var TradeList = function() {
		this.init();
	};
	TradeList.prototype = {
		config : {
			queryGridUrl : root+'/trade/queryListPage.action',
			subHeight : subHeight,
			subWidth : subWidth
			
		},
		cache : {
			grid : null,
			accordion : null,
			tab :null,
			node : null,
			dialog : null,
			parms : {},
			subPageId : null,
			pCodeId : null
		},
		init : function(){
			this.initMsg();
			this.dateInit();
			this.loadGird();
		},
		render : function(){
			
		    
		},
		//高度改变
		_heightChanged : function(options){
			var that=window.tradeList;
	        if(that.cache.tab){
	          that.cache.tab.addHeight(options.diff);	
	        }
	        if (that.cache.accordion && options.middleHeight - 24 > 0){
	          that.cache.accordion.setHeight(options.middleHeight - 24);	
	        }  
	    },
		initMsg : function() {
			var that=this;
			//CODE_UTIL.getCodeSelect_pc($('#shengArea'),$('#shiArea'),180,80);
			CODE_UTIL.getSelectByCodeType($('#tradeType'), 'IC_MAIN_TRADE_TYPE', 180, 80);

			CODE_UTIL.getSelectByCodeType($('#merchantCode'), 'IC_SYSH', 180, 80);
			CODE_UTIL.getSelectByCodeType($('#org'), '5577', 180, 80);

		},
		dateInit : function(){
			$("#tradeTime1").ligerDateEditor({
				width:130,
				showTime :true,
				format  : "yyyy-MM-dd"
			}); 
			$("#tradeTime2").ligerDateEditor({
				width:130,
				showTime :true,
				format  : "yyyy-MM-dd"
		    }); 
		},
	   
        loadGird : function(){
	   		 var that=this;
	   		 //////////////生成表格上面的按钮菜单///////////////////////////////
	   		/// 	var myitems = [];
//	   		 var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id,['code-add']);
//	   		 var funItems = FUNCTION_UTIL.getPageFunction("",['trade-export']);
//	   		 $(funItems).each(function(){  
//	   				myitems.push({line:true});
//	   				var item = {};
//	   				var click = null;
//	   				//if('trade-export'==this.permId){
//	   					click = that.add;
//	   				//}
//	   				item['text']=this.text;
//	   				item['click']=click;
//	   				item['icon']=this.icon;
//	   				myitems.push(item);
//	   			});
//	   		 
	   		 
//	   		 var myitems = [ {
//
//	 	   		text : '导出',
//
//	 	   		icon : 'up',
//
//	 	   		click : function() {
//	 	   		   that.output();
//	 	   		}
//	 	   		}] ;
	   		 
	   		 

		   		var myitems = [];
		   		 var funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id,['trade-export']);
		   		 $(funItems).each(function(){  
		   				myitems.push({line:true});
		   				var item = {};
		   				var click = null;
		   				if('trade-export'==this.permId){
		   					click = that.output;
		   				}
		   				item['text']=this.text;
		   				item['click']=click;
		   				item['icon']=this.icon;
		   				myitems.push(item);
		   			});
		   		 
	   		 
//	   		funItems = FUNCTION_UTIL.getPageFunction(window.frameElement.id,['code-edit','code-delete','code-edit-status']);
     		//funItems = FUNCTION_UTIL.getPageFunction("",['code-edit','code-delete','code-edit-status']);
	   		//////////////生成表格上面的按钮菜单////////////////////////////	
	   		 var options = {};
	   		 var columns = [
	   		    			{ display: '卡号', name: 'cardNum', width:'18%',align: 'center'},
	   		    			{ display: '所属组织', name: 'ownOrg', width:'10%',align: 'left',
	   		    			
	   		    				render : function(row) {
		   		 					var ownOrg= row.ownOrg;
		   		 					return  CODE_UTIL.getCodeName('5577',String(ownOrg));
		   		 				}	
	   		    			
	   		    			},
	   		    		    { display: '发卡机构', name: 'merchantName', width:'10%', align: 'center',
	   		    				render : function(row) {
		   		 					var openCardOfficeCode= row.openCardOfficeCode;
		   		 					return  CODE_UTIL.getCodeName('IC_SYSH',String(openCardOfficeCode));
		   		 				}		
	   		    		    },
//	   		    			{ display: '交易类型', name: 'tradeName',width:'10%', align: 'center'},		    			                   				 	     
	   		    			{ display: '交易类型', name: 'tradeType',width:'10%', align: 'center',
	   		    				render : function(row) {
		   		 					var tradeType= row.tradeType;
		   		 					return  CODE_UTIL.getCodeName('IC_MAIN_TRADE_TYPE',String(tradeType));
		   		 				}		
	   		    			},		    			                   				 	     
	   		    		 	{ display: '交易金额（元）', name: 'tradeMoney',width:'10%', align: 'center',
	   		    				render : function(row) {
		   		 					var tradeMoney= row.tradeMoney;
		   		 					if(tradeMoney!=null&&tradeMoney!=""){
		   		 					  return (parseFloat(tradeMoney)/100).toFixed(2);
		   		 					}else{
		   		 						return "0";
		   		 					}
		   		 				

		   		 				}		
	   		    		 	},
	   		    		 	{ display: '交易后余额', name: 'cardBalance',width:'10%', align: 'center',
	   		    		 	
	   		    		 	render : function(row) {
	   		 					var cardBalance= row.cardBalance;
	   		 					if(cardBalance!=null&&cardBalance!=""){
	   		 					  return (parseFloat(cardBalance)/100).toFixed(2);
	   		 					}else{
	   		 					return "0";
	   		 					}
	   		 				

	   		 				}	
	   		    		 	},
	   		    		 	{ display: '交易时间', name: 'tradeTime',width:'10%', align: 'center',
	   		    		 	
	   		    		 	render : function(row){
   		                		if(row.tradeTime!=null){
   		                			return getSmpFormatDateByLong(row.tradeTime,true);
   		                		}else{
   		                			return "";
   		                		}
   		    					
   		    				}
	   		    		 	
	   		    		 	},
	   		    		 	{ display: '交易地点', name: 'tradeAdress',width:'10%', align: 'center'},
	   		    		 	{ display: '交易状态', name: 'tradeState',width:'8%', align: 'center'
	   		    		 		,
	   		    		 		
	   		    		 	render : function(row) {
	   		 					var tradeState= row.tradeState;
	   		 					if(tradeState='0'){
	   		 						return "成功";
	   		 					}else{
	   		 						return "";
	   		 					}
	   		 				}	
	   		    		 	}

	   		    		 	
	   		    		 	
	   		             ];
	   		 //options['enabledEdit']=true;
	   		 options['columns']=columns;
	   		 options['checkbox']=true;
	   		 options['frozenCheckbox']=false;
	   		 //options['sortName']='name';		 
	   		 //options['parms']=[{name: 'requestParam.equal.pid', value: '0'}]; //设置默认进来的请求参数 
	   		 options['toolbar']={ items: myitems};//表格上面菜单
	   		options['pageSize']=15;
	   	    options['sortName']='tradeTime';	
		    options['sortOrder']='desc';
	   		MALQ_UI.setGridOptions(options, that.config.queryGridUrl);
	   		$('#tradeGrid').ligerGrid(options);		 
            that.cache.grid =$('#tradeGrid').ligerGetGridManager();          
            $('#tradeGrid').css('top','0px');
            $('#pageloading').hide();//加载图片
        },
        
      //查询
        search: function(){
        	var that=this;
        	debugger;
        	
        	var parms=that.cache.grid.get('parms');
        	var tem=[];
	       	$(parms).each(function(){
	       		if(this.name=='requestParam.equal.cardNum' 
	       			|| this.name=='requestParam.equal.tradeType'
	       			|| this.name=='requestParam.equal.openCardOfficeCode'
		       	    || this.name=='requestParam.equal.ownOrg'
		       	    ||this.name=='requestParam.startwith.tradeTime' 
		   	       	|| this.name=='requestParam.endwith.tradeTime'
		
	       		)
	       			return true;
	       		tem.push(this);
	       	});
	       	parms=tem;
	       	
	        	var cardNum = $('#cardNum').val();   	   	
	        	var tradeType = $('#tradeType_val').val(); 
	        	var org = $('#org_val').val();
	        	var merchantCode = $('#merchantCode_val').val();
	        	
        	 var tradeTime1 = getFormatDataToLong($("#tradeTime1").val());
     	     var tradeTime2 = getFormatDataToLong(getTimeEnd(new Date($("#tradeTime2").val())));

			if(tradeTime1&&tradeTime1!="NaN"&&tradeTime2&&tradeTime2!="NaN"&&tradeTime1>tradeTime2){
			 JSWK.clewBox('结束时间必须大开始时间','clew_ico_succ');
				 return false;
			}
		    if(tradeTime1&&tradeTime1!="NaN"){
		       parms.push({name:'requestParam.startwith.tradeTime',value:tradeTime1});
		    }
		    if(tradeTime2&&tradeTime2!="NaN"){
		      parms.push({name:'requestParam.endwith.tradeTime',value:tradeTime2});
		    }
     	       
        	if(merchantCode){
        		parms.push({name:'requestParam.equal.openCardOfficeCode', value:merchantCode});
        	}
        	if(cardNum){
        		parms.push({name:'requestParam.equal.cardNum', value:cardNum});
        	}
        	if(tradeType){
        		parms.push({name:'requestParam.equal.tradeType', value:tradeType});
        	}
        	if(org){
        		parms.push({name:'requestParam.equal.ownOrg', value:org});
        	}
        	
        	if(tradeType){
        		parms.push({name:'requestParam.equal.tradeType', value:tradeType});
        	}
        	that.cache.grid.set('parms', parms);
        	that.cache.grid.changePage('first');
        	that.cache.grid.loadData(true);
        },
   	   
        output : function(){
	    	  // $("#loadingExcel").show();
	    	   var parms=window.tradeList.cache.grid.get('parms');
	         	var parmStr = "";
	         	var i = 0;
	  	       	$(parms).each(function(){
	  	       	
	  	       		
	  	       	if(this.name=='requestParam.equal.cardNum' 
	       			|| this.name=='requestParam.equal.tradeType'
	       			|| this.name=='requestParam.equal.openCardOfficeCode'
		       	    || this.name=='requestParam.equal.ownOrg'
		       	    ||this.name=='requestParam.startwith.tradeTime' 
		   	       	|| this.name=='requestParam.endwith.tradeTime'
		
	       		)
	  	       		
	  	       		
	  	       		if(this.value&&i>0){
	  	       			if(this.value!="请选择"){
			  	       	    parmStr+="&"+this.name+"="+this.value;
	  	       			}
		  	       		
	  	       		}else{
	  	       			if(this.value&&i==0){
		  	       			if(this.value!="请选择"){
			  	  	       		parmStr+=this.name+"="+this.value;
		  	       			}
	  	       			}
	  	       		}
	  	       		i++;
	  	            	
	  	       	});
	    	   var excelUrl = root + '/trade/outputTradeList.action';   
	    	   if(parmStr!=""){
	    		   excelUrl+="?"+parmStr;      
	    	   }
	    	  $("#loadExcel").attr("src",excelUrl);
	    	   //window.location.href=excelUrl;
	    	  // $("#loadingExcel").hide();
	    	  }, 
        flushCodeList : function(row, op){
        	window.parent.indexPage.initAllCodeInfo();
        },
        
        
        
        reset : function(){
	       	 $('#cardNum').val('');
	       	 $("#tradeTime1").val('');
    	   $("#tradeTime2").val('');
    	   $('#tradeType').ligerGetComboBoxManager().selectValue('');
    	   $('#org').ligerGetComboBoxManager().selectValue('');
    	   //$('#codeName').ligerGetComboBoxManager().selectValue('');
    	   $('#merchantCode').ligerGetComboBoxManager().selectValue('');
    	   
        },
        success_alert : function(data){		        	
	        JSWK.clewBox(data,'clew_ico_succ');
	     },
	     fail_alert : function(data){
	        JSWK.clewBox(data,'clew_ico_fail',failShowTime);
	     }
	};
	
	function  getTimeEnd(date){
       	var year = date.getFullYear();
       	var month = date.getMonth();
       	var day = date.getDate();
       	var todayTime = new Date(year, month, day);
       	var date = new Date(todayTime.getTime());
       	var getMonth = ((date.getMonth()+1)<10)?("0"+(date.getMonth()+1)):(date.getMonth()+1);
       	var getDate = ((date.getDate()+1)<10)?("0"+(date.getDate()+1)):(date.getDate()+1);
       	var nextDay = date.getFullYear() + "-" + getMonth + "-" + getDate;
       	return nextDay;
       }
	
	$(document).ready(function() {
		debugger;
		window.tradeList = new TradeList();
	});
})();   