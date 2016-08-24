/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */

var tplGrid = $('#tplGrid').html();
var $dataGrid = $('#dataGrid');
var queryParam = []; //查询条件W
var selectUrl = '../../trade/queryOilCardTradeListForHomePage.action';
(function() {
	var OilHome = function() {
		this.init();
	};
	OilHome.prototype = {
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
			//上个月
			var that = this;
			var nowdate = new Date();
			nowdate.setMonth(nowdate.getMonth()-1);
			var days = getCountDays(nowdate.getMonth());
			$("#preMonthTime").html(getFormatDate(nowdate, "MM-01") + "/" + getFormatDate(nowdate, "MM-"+days));
			
			$(".f14").click(function(){
				$(".f14").each(function(){
					$(this).removeClass("title_tabcurr");
				});
				$(this).addClass("title_tabcurr");
				
				if($(this).hasClass("addoil")){
					//调用油品的数据
					selectUrl = '../../trade/queryOilCardTradeListForHomePage.action';
					search();
				}
				if($(this).hasClass("paycharger")){
					//调用充值记录的数据
					selectUrl = '../../recharge/queryOilCardRechargeList.action';
					search();
				}
			});
			
			//加载 我的油卡、累计加油量、上月返利
			var queryHomePageDataParam = [];
			var startTimeLong = getFormatDataToLong(getFormatDate(nowdate, "yyyy-MM-01"));
			var endTimeLong = getFormatDataToLong(getFormatDate(nowdate, "yyyy-MM-"+days));
			queryHomePageDataParam.push({name:'requestParam.equal.model',value:'1'});
			queryHomePageDataParam.push({name:'requestParam.startwith.operateTime',value : startTimeLong});
			queryHomePageDataParam.push({name:'requestParam.endwith.operateTime',value : endTimeLong});
			$.ajax({
				url:"../../oilcard/getOilHomePageData.action",
				dataType:'JSON',
				data : queryHomePageDataParam,
				success: function(data){
					 if(data && data != null && data.data && data.data != null && data.data.length > 0){
						 $("#cardNum").html(data.data[0].countCard);
						 if(data.data[0].countCard>0){
							 $("#open_card").html("添加油卡");
						 }
						 $("#allAddOil").html(converOilToPage(data.data[0].countOil));
						 $("#lastMonthRe").html(data.data[0].countCredit);
					 } 
	             }	
				
			});
		},

		loadGird : function() {
			//数据对象模板
			search();
		}
	};
	function search() {
		var that = this;
		$(".pageSize").val('5');
		$(".pageNum").val('1');
		var pageParam = $('.pageParam').serializeArray();
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
					if(data && data.total ) {
						/*$("#homeShowRows").html(data.total);*/
					}
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
	

	$(document).ready(function() {
		window.oilHome = new OilHome();
		
		$("#cardNum").click(function(){
			window.location.href="my_oilcards.jsp";
		});
	});

})();
function getCountDays(curMonth) {
    var curDate = new Date();
   curDate.setMonth(curMonth+1);
   curDate.setDate(0);
   /* 返回当月的天数 */
   return curDate.getDate();
 }
