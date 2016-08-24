/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */

var queryParam = []; //查询条件W
(function() {
	var Home = function() {
		this.init();
	};
	Home.prototype = {
		config : {
		},
		cache : {
			parms : {}
		},
		init : function() {
			this.initMsg();
			this.initEvent();
		},
		initEvent:function(){
			// 无卡时跳转到油卡介绍页
			$(".slide_pic").click(function(){
				window.location.href='oil/oil_introduction.jsp';
			});
			
		},
		
		//判断用户是否有卡，没卡显示立即办卡
		initMsg : function() {
			
			//默认加载油品首页
			$("#enterButton").click(function(){
				window.location.href='card/card_apply.jsp';
			});
			
			//加载 我的油卡、累计加油量、上月返利
			var nowdate = new Date();
			nowdate.setMonth(nowdate.getMonth()-1);
			var days = getCountDays(nowdate.getMonth());
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
						 if(data.data[0].countCard!=null&&data.data[0].countCard!=""){
							 if(data.data[0].countCard==0){
								 $("#enterButton").val("立即办卡");
								 $("#enterButton").unbind("click");
								 $("#enterButton").click(function(){
									 window.location.href="card/card_apply.jsp";
									});
							 }
						 }
					 } 
					 
	             }	
				
			});
		}
	};
	

	$(document).ready(function() {
		window.home = new Home();
	});

})();
function getCountDays(curMonth) {
    var curDate = new Date();
   curDate.setMonth(curMonth+1);
   curDate.setDate(0);
   /* 返回当月的天数 */
   return curDate.getDate();
 }


