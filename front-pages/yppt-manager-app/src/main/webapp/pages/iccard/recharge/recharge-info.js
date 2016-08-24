/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 */
(function(){
	var RechargeInfo = function(){
		this.init();
	};
	
	RechargeInfo.prototype = {
		config :{
			queryRechargeInfoUrl : root + '/icrechargeapply/queryRechargeInfo.action'
		},
		cache :{
			applyId:null,
			grid:null
		},
		init : function(){
			var applyId = window.location.search.substring(1).split("=")[1];
			this.cache.applyId = applyId ;
			this.loadGrid();
		},
		loadGrid : function(){
			var that = this;
			var options = {};
			var parms = {};
			parms['applyId'] =that.cache.applyId;
			var columns = [
			               {display:'IC卡号',name:'cardNo',width:'25%',align:'center'},
			               {display:'车牌号',name:'vehicleNo',width:'25%',align:'center',
			               	   render:function(row){
			            		   var vehicleColor = row.vehicleColor;
			            		   var vehicleNo = row.vehicleNo;
			            		   if(vehicleColor){
			            			   vehicleColor = CODE_UTIL.getCodeName('V-C-COLOR',vehicleColor);
			            			   return vehicleNo+'-'+vehicleColor;
			            		   }
		   		    		 		return vehicleNo;
	   		    				}
			               },
			               {display:'充值金额（元）',name:'money',width:'25%',align:'center',
		   		    		 	render:function(row){
		   		    		 		return row.money?parseFloat(row.money/100).toFixed(2):'0.00';
	   		    				}},
			               {display:'实际分配金额',name:'actualDivMoney',width:'25%',align:'center',
			   		    		 	render:function(row){
			   		    		 		return row.actualDivMoney?parseFloat(row.actualDivMoney/100).toFixed(2):'0.00';
		   		    				}},
			              ];
			options['columns']=columns;
			options['usePager']=false;
			options['height']='30%';
			options['parms']=parms;
			options['onAfterShowData']=that.onAfterShowData;
			MALQ_UI.setGridOptions(options,that.config.queryRechargeInfoUrl);
			that.cache.grid=$("#cardInfoGrid").ligerGrid(options);
		},
		onAfterShowData :function(data){
			
			if(!data.dataObject) return;
			var rechargeApply = data.dataObject.rechargeApply;
			var dataSource=CODE_UTIL.getCodeName('CARD-DATASOURCE',rechargeApply.dataSource);
			$("#cardNum").text(rechargeApply.cardNum==null?"":rechargeApply.cardNum);
			$("#totalMoney").text(rechargeApply.totalMoney==null?"0.00":(parseFloat(rechargeApply.totalMoney)/100).toFixed(2));
			$("#creditNum").text(rechargeApply.creditNum==null?"0.00":(parseFloat(rechargeApply.creditNum)/100).toFixed(2));
			$("#actualDivMoney").text(rechargeApply.actualDivMoney==null?"0.00":(parseFloat(rechargeApply.actualDivMoney)/100).toFixed(2));
			$("#userRegPhone").text(rechargeApply.userRegPhone==null?"":rechargeApply.userRegPhone);
			$("#userName").text(rechargeApply.userName==null?"":rechargeApply.userName);
			$("#dataSource").text(dataSource);
			$("#applyPersonName").text(rechargeApply.applyPersonName==null?"":rechargeApply.applyPersonName);
			if(rechargeApply.certFile){
				$("#certFile").attr("href",rechargeApply.certFile);
				$("#certFile").show();
//				$("#voucher").show();
			}else{
				$("#certFile").hide();
				$("#voucher").text("无凭证文件");
			}
//			$("#_certFile").attr("href",rechargeApply.certFile==null?"":rechargeApply.certFile);
		},
		showBigImg :function(o){
			$("#showBigImg").html("<img  src=\""+o.src.replace('min_','max_')+"\" style=\"width:700px;height:500px;\"/>").show().offset({top:($(window).height()-$("#showBigImg").outerHeight())/2+$(document).scrollTop(),left:($(window).width()-$("#showBigImg").outerWidth())/2+$(document).scrollLeft()});
		}
	};
	$(function(){
//		debugger;
		var rechargeInfo = new RechargeInfo();
		window.rechargeInfo = rechargeInfo;
	});
})();