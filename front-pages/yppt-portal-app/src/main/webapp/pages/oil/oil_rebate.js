/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */

var queryParam = []; //查询条件
var tplGrid = $('#tplGrid').html();
var $dataGrid = $('#dataGrid');
$queryBlock = $('.queryBlock');
$oilCardRecharge = $('#oilCardRecharge');
$inTotal = $('#inTotal');
$outTotal = $('#outTotal');
(function() {
	$oilCardRecharge.click(function() {
		window.location.href = basePath + 'pages/oil/oil_cards.jsp';
	});
	var TheRebate = function() {	
		this.init();
	};
	TheRebate.prototype = {
		config : {
		},
		init : function() {
			this.initMsg();	
			this.loadGird();
		},
		initMsg : function() {
			//油卡返利初始化 旺金币-累计返利 - 进账总额 - 出账总额
			$.ajax({
				type:"POST",
				data:{},
				url:"../../userstat/queryUserStat.action",
				datatype:"JSON",
				success:function(data){
					if(data && data != null && data.dataObject && data.dataObject.goldCoinNum){
					   $("#accumRebate").html(data.dataObject.goldCoinNum);
				   }
			    }
		    });
			//加载旺金币
			$.ajax({
				type: "POST",
	             url: "../../creditaccount/queryCreditAccountBalanceavailable.action",
	             data: {},
	             dataType: "json",
	             success: function(data){
	             //缓存账户余额
		             if(data && data != null && data.dataObject && data.dataObject != null) {
		            	$("#wangGold").html(data.dataObject);
		             }
	             }	
			});
		},
		
		loadGird : function(){
			$("#startTime").val(getFormatDate(new Date(), "yyyy-MM-01"));
			$("#endTime").val(getSmpFormatNowDate(false));
			queryParam.push({name:'requestParam.startwith.operateTime',value:getFormatDataToLong($("#startTime").val())});
			queryParam.push({name:'requestParam.endwith.operateTime',value:getFormatDataToLong($("#endTime").val())});
			setParm();
			select();
			ajaxAccountIOQuery(queryParam);
			/* 渲染分页模板 */
			$dataGrid.on('click', '.pager a', function(e) {
				$('.pageNum').val($(this).attr('p'));
				select();
				e.preventDefault();
			});
       },
       search : function(){
    	   setParm();
    	   select();
    	   ajaxAccountIOQuery(queryParam);
       }
	};
	function setParm(){
		$('.pageNum').val(1);
		queryParam = [];
		if($("#startTime").val() && $("#startTime").val() != null && $("#startTime").val().trim() != ""){
			queryParam.push({name:'requestParam.startwith.operateTime',value:getFormatDataToLong($("#startTime").val())});
		}
		if($("#endTime").val() && $("#endTime").val() != null && $("#endTime").val().trim() != ""){
			queryParam.push({name:'requestParam.endwith.operateTime',value:getFormatDataToLong($("#endTime").val())});
		}
		var val = $("#classfiy select").find("option:selected").val();
		if(val!=null&&val!=""&&val!=-1){
			queryParam.push({name:'requestParam.equal.model',value:val});
		}
	}
	function select(){
		$(".pageSize").val('10');
		var pageParam = $queryBlock.find('.pageParam').serializeArray();
		if (queryParam) {
			pageParam = pageParam.concat(queryParam);
		}
		$.ajax({
			url : "../../rebate/queryOilCardRebateList.action",
			data : pageParam,
			dataType : 'JSON',
			headers : {
				'grid' : 1
			}
		}).done(
				function(data) {
					if(data && data != null) {
						data.totalPage = parseInt((data.total + data.pageSize - 1)
								/ data.pageSize);
						$dataGrid.html(juicer(tplGrid, data));
					}
				});
	}
	/* Ajax查询账户进出帐方法 */
	function ajaxAccountIOQuery(param) {
		$.ajax({
			url : "../../rebate/getOilCardRebateIO.action",
			data : param,
			dataType : 'JSON'
		}).done(function(data) {
			if(data && data.data && data.data.length > 0 ) {
				
				if(data.data[0].INPUT && data.data[0].INPUT != null) {
					$inTotal.html(data.data[0].INPUT);
				} else {
					$inTotal.html(0);
				}
				if(data.data[0].OUTPUT && data.data[0].OUTPUT != null) {
					$outTotal.html(data.data[0].OUTPUT);
				} else {
					$outTotal.html(0);
				}
			}
		});
	}
	$(document).ready(function() {
		window.theRebate = new TheRebate();
	});
	
})();
