/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
var tplGrid = $('#tplGrid').html();
var $dataGrid = $('#dataGrid');
var paytype = 0; //交易类型 0没选择 1充值 2加油
var paystatus = 0; //交易状态 0 没选择 1交易成功 2 处理中 3充值失败
var queryParam = []; //查询条件
var queryHistory = '1';
$queryBlock = $('.queryBlock');
var selectUrl = '../../recharge/queryOilCardRechargeList.action';
var selectUrl = '';
(function() {
	var OilTransaction = function() {
		this.init();
	};
	OilTransaction.prototype = {
		config : {
		},
		cache : {
			parms : {}
		},
		init : function() {
			this.initMsg();
			this.loadGird();
		},
		initMsg : function() {
			// 给查询条件选择月份绑定事件
			$(".choosemonth").click(function() {
				// 先去掉被选中class
				$(".choosemonth").each(function() {
					if ($(this).hasClass("litab_curr")) {
						$(this).removeClass("litab_curr");
					}
				});
				// 给点击的加上class
				$(this).addClass("litab_curr");
				// 给month赋值
				if ($(this).hasClass("litab_a")) {
					queryHistory = "1";
					//给日期框赋值  ---当前月
					$("#getStartDate1").val(getFormatDate(new Date(), "yyyy-MM-01"));
					$("#getStartDate2").val(getSmpFormatNowDate(false));
				}  else if ($(this).hasClass("litab_c")) {
					queryHistory = '2';
					//给日期赋值----上个月
					var nowdate = new Date();
					nowdate.setMonth(nowdate.getMonth()-1);
					var days = getCountDays(nowdate.getMonth());
					$("#getStartDate1").val(getFormatDate(nowdate, "yyyy-MM-01"));
					$("#getStartDate2").val(getFormatDate(nowdate, "yyyy-MM-"+days));
				} else {
					queryHistory = "1";
					//给日期框赋值  ---当前月---默认值
					$("#getStartDate1").val(getFormatDate(new Date(), "yyyy-MM-01"));
					$("#getStartDate2").val(getSmpFormatNowDate(false));
				}

				getList();
			});

			// 给交易类型绑定事件
			$(".paytype").click(function() {
				// 先移除选中class
				$(".paytype").each(function() {
					if ($(this).hasClass("litab_curr")) {
						$(this).removeClass("litab_curr");
					}
				});
				// 给被点击的加上被选中class
				$(this).addClass("litab_curr");
				// 给交易类型赋值
				if ($(this).hasClass("litab_a")) {
					paytype = 1;
				} else if ($(this).hasClass("litab_c")) {
					paytype = 2;
				} else if ($(this).hasClass("litab_c")){
					paytype = 3;
				} else {
					paytype = 0;
				}
				getList();
			});

			// 给交易状态绑定事件
			$(".paystatus").click(function() {
				// 先去掉选中的class
				$(".paystatus").each(function() {
					if ($(this).hasClass("litab_curr")) {
						$(this).removeClass("litab_curr");
					}
				});
				// 给被点击的加上选中class
				$(this).addClass("litab_curr");

				// 给paystatus赋值
				if ($(this).hasClass("litab_a")) {
					paystatus = 1;  //成功
				} else if ($(this).hasClass("litab_b")) {
					paystatus = 2;  //处理中
				} else if ($(this).hasClass("litab_c")) {
					paystatus = 3;  //失败
				} else {
					paystatus = 0;  //所有
				}
				getList();
			});
			//初始化数据
			//累计充值-累计消费
			$.ajax({
				type:"POST",
				data:{},
				url:"../../userstat/queryUserStat.action",
				datatype:"JSON",
				success:function(data){
				   if(data && data.dataObject){
						    var accountRecharge = converMoneyToPage(data.dataObject.rechargeMoney);
						    var accountExpense = converMoneyToPage(data.dataObject.consumeMoney);
						    //初始化累计充值
	 	            	    var accountRechargeStrs= new Array();
	 	            	    accountRechargeStrs = accountRecharge.split("\.");
	 	            	    $("#accountRechargeIntPart").prepend(accountRechargeStrs[0]+".");
	 	            	    $("#accountRechargeDePart").html(accountRechargeStrs[1]);
	 	            	    //初始化累计消费
	 	            	    var accountExpenseStrs= new Array();
	 	            	    accountExpenseStrs = accountExpense.split("\.");
	 	            	    $("#accountExpenseIntPart").prepend(accountExpenseStrs[0]+".");
	 	            	    $("#accountExpenseDePart").html(accountExpenseStrs[1]);
				   }else{
					   //alert("初始化页面数据失败！");
				   }
			    }
		    });
			
			
			$('.pageNum').val(1);
			//初始化时间为当月
//			$("#getStartDate1").val(getFormatDate(new Date(), "yyyy-MM-01"));
//			$("#getStartDate2").val(getSmpFormatNowDate(false));
			var nowdate = new Date();
//			nowdate.setMonth(nowdate.getMonth()-1);
			nowdate.setMonth(nowdate.getMonth());
			var days = getCountDays(nowdate.getMonth());
			$("#getStartDate1").val(getFormatDate(nowdate, "yyyy-MM-01"));
			$("#getStartDate2").val(getFormatDate(nowdate, "yyyy-MM-"+days));
			if($("#getStartDate1").val() && $("#getStartDate1").val() != null && $("#getStartDate1").val().trim() != ""){
				queryParam.push({name:'requestParam.startwith.operateTime',value:getFormatDataToLong($("#getStartDate1").val())});
			}
			if($("#getStartDate2").val() && $("#getStartDate2").val() != null && $("#getStartDate2").val().trim() != ""){
				queryParam.push({name:'requestParam.endwith.operateTime',value:getFormatDataToLong($("#getStartDate2").val())});
			}
			paytype = 1;
			queryParam.push({name:'requestParam.equal.paytype',value:paytype});
			
		},

		loadGird : function() {
			//数据对象模板
			getList();
			//search();
			/* 渲染分页模板 */
			$dataGrid.on('click', '.pager a', function(e) {
				$('.pageNum').val($(this).attr('p'));
				search();
				e.preventDefault();
			});
		}
	};
	
	
	

	$(document).ready(function() {
		window.oilTransaction = new OilTransaction();
		
	});

})();

function getList(){
	setParam();
	if(paytype==1){
		ajaxRechargeQuery();
	}else if(paytype==2){
		ajaxUseQuery();
	}
	search();
}

/* Ajax查询充值消费总额方法 */
function ajaxRechargeQuery() {
	$.ajax({
//		url : '../../oilcard/getRechargeTotal.action',
		url : '',
		data : queryParam,
		dataType : 'JSON'
	}).done(function(data) {
		if(data && data.dataObject && data.dataObject != null) {
				$('#rechargeTotal').html(data.dataObject);
			} else {
				$('#rechargeTotal').html('0.00');
		}
	});
}
function ajaxUseQuery() {
	$.ajax({
//		url : '../../oilcard/getFuelTotal.action',
		url : '',
		data : queryParam,
		dataType : 'JSON'
	}).done(function(data) {
		if(data && data.dataObject && data.dataObject != null ) {
				$('#expenseTotal').html(data.data[0].expenseTotal);
			} else {
				$('#expenseTotal').html('0.00');
			}
	});
}

function setParam(){
	// 设置查询条件
	$('.pageNum').val(1);
	queryParam = [];
	queryParam.push({name:'requestParam.equal.queryHistory',value: queryHistory});
	if($("#getStartDate1").val() && $("#getStartDate1").val() != null && $("#getStartDate1").val().trim() != ""){
		queryParam.push({name:'requestParam.startwith.operateTime',value:getFormatDataToLong($("#getStartDate1").val())});
	}
	if($("#getStartDate2").val() && $("#getStartDate2").val() != null && $("#getStartDate2").val().trim() != ""){
		queryParam.push({name:'requestParam.endwith.operateTime',value:getFormatDataToLong($("#getStartDate2").val())});
	}
	//添加高级查询条件
	if(!$("#ishightselect").hasClass("active")){
		if(paytype!=0){
			queryParam.push({name:'requestParam.equal.paytype',value:paytype});
		}
		if(paystatus!=0){
			queryParam.push({name:'requestParam.equal.paystatus',value:paystatus});
		}
		if($("#threeswitch").val()!=null&&$("#threeswitch").val()!=""&&$("#threeswitch").val()!="请输入卡号/车牌号/手机号"){
			queryParam.push({name:'requestParam.equal.threeswitch',value:encodeURIComponent($("#threeswitch").val())});
		}
	}
}
function getCountDays(curMonth) {
    var curDate = new Date();
   curDate.setMonth(curMonth+1);
   curDate.setDate(0);
   /* 返回当月的天数 */
   return curDate.getDate();
 }
function search() {
	
	if(paytype==1){ //充值
		 selectUrl = '../../recharge/queryOilCardRechargeList.action';
	//	 selectUrl = '';
	}else if(paytype==2){//加油
	   	 selectUrl = '../../trade/queryOilCardTradeList.action';
	}
	
	$(".pageSize").val('10');
	var pageParam = $queryBlock.find('.pageParam').serializeArray();
	if (queryParam) {
		pageParam = pageParam.concat(queryParam);
	}
	$.ajax({
		url : selectUrl,
		data : pageParam,
		dataType : 'JSON',
		headers : {
			'grid' : 1
		}
	}).done(
			function(data) {
				data.totalPage = parseInt((data.total + data.pageSize - 1)
						/ data.pageSize);
				$dataGrid.html(juicer(tplGrid, data));
				
				 $('.bubbleInfo').each(function () {
			            var distance = 10;
			            var time = 50;
			            var hideDelay = 10;
			            var hideDelayTimer = null;
			            var beingShown = false;
			            var shown = false;
			            var trigger = $('.trigger', this);
			            var info = $('.popup', this).css('opacity', 0);
			            

			            $([trigger.get(0), info.get(0)]).mouseover(function () {
			                if (hideDelayTimer) clearTimeout(hideDelayTimer);
			                if (beingShown || shown) {
			                    // don't trigger the animation again
			                    return;
			                } else {
			                    // reset position of info box
			                    beingShown = true;

			                    info.css({
			                        top: 25,
			                        left: -110,
			                        display: 'block'
			                    }).animate({
			                        top: '=' + distance + 'px',
			                        opacity: 1
			                    }, time, 'swing', function() {
			                        beingShown = false;
			                        shown = true;
			                    });
			                }

			                return false;
			            }).mouseout(function () {
			                if (hideDelayTimer) clearTimeout(hideDelayTimer);
			                hideDelayTimer = setTimeout(function () {
			                    hideDelayTimer = null;
			                    info.animate({
			                        top: '-=' + distance + 'px',
			                        opacity: 0
			                    }, time, 'swing', function () {
			                        shown = false;
			                        info.css('display', 'none');
			                    });

			                }, hideDelay);

			                return false;
			            });
			        });
			
	
			 $('.bubbleInfo2').each(function () {
		         var distance = 10;
		         var time = 50;
		         var hideDelay = 10;
		         var hideDelayTimer = null;
		         var beingShown = false;
		         var shown = false;
		         var trigger = $('.trigger2', this);
		         var info = $('.popup2', this).css('opacity', 0);
		
		
		         $([trigger.get(0), info.get(0)]).mouseover(function () {
		             if (hideDelayTimer) clearTimeout(hideDelayTimer);
		             if (beingShown || shown) {
		                 // don't trigger the animation again
		                 return;
		             } else {
		                 // reset position of info box
		                 beingShown = true;
		
		                 info.css({
		                     top: 30,
		                     left: -69,
		                     display: 'block'
		                 }).animate({
		                     top: '=' + distance + 'px',
		                     opacity: 1
		                 }, time, 'swing', function() {
		                     beingShown = false;
		                     shown = true;
		                 });
		             }
		
		             return false;
		         }).mouseout(function () {
		             if (hideDelayTimer) clearTimeout(hideDelayTimer);
		             hideDelayTimer = setTimeout(function () {
		                 hideDelayTimer = null;
		                 info.animate({
		                     top: '-=' + distance + 'px',
		                     opacity: 0
		                 }, time, 'swing', function () {
		                     shown = false;
		                     info.css('display', 'none');
		                 });
		
		             }, hideDelay);
		
		             return false;
		         });
			 });
			});
}
