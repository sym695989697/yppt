/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 */
(function(){
	var RechargeHandle = function(){
		this.init();
	};
	
	RechargeHandle.prototype = {
		config :{
			queryRechargeInfoUrl : root + '/icrechargeapply/queryRechargeInfoWithHandler.action',
			handleRechargeUrl : root + '/icrechargeapply/handleRecharge.action'
		},
		cache :{
			applyId:null,
			grid:null
		},
		init : function(){
			var applyId = window.location.search.substring(1).split("=")[1];
			this.cache.applyId = applyId ;
			$("#applyId").val(applyId);
			this.loadGrid();
			this.submit();
		},
		loadGrid : function(){
			var that = this;
			var options = {};
			var parms = {};
			parms['applyId'] =that.cache.applyId;
			var columns = [
			               {display:'发卡地区',name:'',width:'16%',align:'center',
				            	render:function(row){
				            		if(row.cardAreaCode){
				            			return CODE_UTIL.getCodeName(row.cardType,row.cardAreaCode);
				            		}
				            		return "";
				               }
			               },
			               {display:'主卡卡号',name:'parentCardNumber',width:'16%',align:'center'},
			               {display:'IC卡号',name:'cardNo',width:'16%',align:'center'},
			               {display:'车牌号',name:'vehicleNo',width:'16%',align:'center',
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
			               {display:'充值金额（元）',name:'money',width:'16%',align:'center',
		   		    		 	render:function(row){
		   		    		 		return row.money?parseFloat(row.money/100).toFixed(2):'0.00';
	   		    				}},
			               {display:'实际分配金额',name:'actualDivMoney',width:'16%',align:'center',
			   		    		 	render:function(row){
			   		    		 		return row.actualDivMoney?parseFloat(row.actualDivMoney/100).toFixed(2):'0.00';
		   		    				}},
			              ];
			options['columns']=columns;
			options['usePager']=false;
			options['height']='30%';
			options['parms']=parms;
			options['onBeforeShowData']=that.onBeforeShowData;
			options['onAfterShowData']=that.onAfterShowData;
			MALQ_UI.setGridOptions(options,that.config.queryRechargeInfoUrl);
			that.cache.grid=$("#cardInfoGrid").ligerGrid(options);
		},
		onBeforeShowData :function(data){
			if(data.dataObject && data.dataObject=="0"){
				$.ligerDialog.warn("已有审核人！",function(){
					parent.rechargeList.cache.dialog.close();
				});
			}
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
//			$("#certFile").attr("src",rechargeApply.certFile==null?"":rechargeApply.certFile);
//			$("#_certFile").attr("href",rechargeApply.certFile==null?"":rechargeApply.certFile);
		},
		showBigImg :function(o){
			$("#showBigImg").html("<img  src=\""+o.src.replace('min_','max_')+"\" style=\"width:700px;height:500px;\"/>").show().offset({top:($(window).height()-$("#showBigImg").outerHeight())/2+$(document).scrollTop(),left:($(window).width()-$("#showBigImg").outerWidth())/2+$(document).scrollLeft()});
		},
		submit : function(){
			var that = this;
			var options = {
				url :that.config.handleRechargeUrl,
				type :'POST',
				success :function(data){
					 var start = data.indexOf(">");  
		             if(start != -1) {  
		               var end = data.indexOf("<", start + 2);  
		               if(end != -1) {  
		                  data = data.substring(start + 2, end-1);  
		                }  
		             }
		             if(data != "0" && data.trim()!=""){
		            	 parent.rechargeList.success_alert('操作成功');
		             }else{
		            	 parent.rechargeList.fail_alert('操作失败！');
		             }
					
					parent.rechargeList.cache.grid.loadData();
					parent.rechargeList.cache.dialog.close();
				},
				error:function(data){
					$.ligerDialog.warn("处理失败");
					parent.rechargeList.cache.grid.loadData();
					parent.rechargeList.cache.dialog.close();
				}
			};
			$("#apply").live('click',function(){
					$("#myForm").ajaxForm(options);
					$("#myForm").submit();
					$("#apply").attr("disabled",true);
			});
			
		}
	};
	$(function(){
//		debugger;
		var rechargeHandle = new RechargeHandle();
		window.rechargeHandle = rechargeHandle;
	});
})();