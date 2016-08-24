var tplGrid = $('#tplGrid').html();
var $dataGrid = $('#dataGrid');
var status = '0'; //发票状态 0没选择  2开票通过 1待审核3开票失败
var queryParam = []; //查询条件
$queryBlock = $('.queryBlock');
var selectUrl = '../../billing/queryBillingApplyList.action';
(function() {
	
	var Billings = function() {
		this.init();
	};
	Billings.prototype = {
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
					//给日期框赋值  ---当前月
					$("#startDate").val(getFormatDate(new Date(), "yyyy-MM-01"));
					$("#endDate").val(getSmpFormatNowDate(false));
				}  else if ($(this).hasClass("litab_c")) {
					//给日期赋值----上个月
					var nowdate = new Date();
					nowdate.setMonth(nowdate.getMonth()-1);
					var days = getCountDays(nowdate.getMonth());
					$("#startDate").val(getFormatDate(nowdate, "yyyy-MM-01"));
					$("#endDate").val(getFormatDate(nowdate, "yyyy-MM-"+days));
				} else {
					//给日期框赋值  ---当前月---默认值
					$("#startDate").val(getFormatDate(new Date(), "yyyy-MM-01"));
					$("#endDate").val(getSmpFormatNowDate(false));
				}

				getList();
			});
			
			$("#startDate").click(function() {
				// 先去掉被选中class
				$(".choosemonth").each(function() {
					if ($(this).hasClass("litab_curr")) {
						$(this).removeClass("litab_curr");
					}
				});
			});
			
			$("#endDate").click(function() {
				// 先去掉被选中class
				$(".choosemonth").each(function() {
					if ($(this).hasClass("litab_curr")) {
						$(this).removeClass("litab_curr");
					}
				});
			});
			
			//查询累计开票金额
			$.ajax({
				type: "POST",
	             url: '../../billing/getTotalOpenMoney.action',
	             data: {},
	             dataType: "json",
	             success: function(data){
            	    var total = data.dataObject;
            	    //累计开票金额
            	    var totalOpenMoneyArr= new Array();
            	    totalOpenMoneyArr = total.split("\.");
            	    $("#totalOpenMoneyIntPart").prepend(totalOpenMoneyArr[0]+".");
            	    $("#totalOpenMoneyDePart").html(totalOpenMoneyArr[1]);
	             }	
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
					status = '4';
				} else if ($(this).hasClass("litab_b")) {
					status = '1,2';
				} else if ($(this).hasClass("litab_c")){
					status = '3,5';
				} else {
					status = 0;
				}
				getList();
			});

			$('.pageNum').val(1);
			//初始化时间为当月
			$("#startDate").val(getFormatDate(new Date(), "yyyy-MM-01"));
			$("#endDate").val(getSmpFormatNowDate(false));
			if($("#startDate").val() && $("#startDate").val() != null && $("#startDate").val().trim() != ""){
				queryParam.push({name:'requestParam.startwith.createTime',value:getFormatDataToLong($("#startDate").val())});
			}
			if($("#endDate").val() && $("#endDate").val() != null && $("#endDate").val().trim() != ""){
				queryParam.push({name:'requestParam.endwith.createTime',value:getFormatDataToLong($("#endDate").val())});
			}
			if(status != '0'){
				queryParam.push({name:'requestParam.inMap.status',value:status});
			}
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
		window.billings = new Billings();
		
	});

})();

function getList(){
	setParam();
	search();
}


function setParam(){
	// 设置查询条件
	$('.pageNum').val(1);
	queryParam = [];
	if($("#startDate").val() && $("#startDate").val() != null && $("#startDate").val().trim() != ""){
		queryParam.push({name:'requestParam.startwith.createTime',value:getFormatDataToLong($("#startDate").val())});
	}
	if($("#endDate").val() && $("#endDate").val() != null && $("#endDate").val().trim() != ""){
		queryParam.push({name:'requestParam.endwith.createTime',value:getFormatDataToLong($("#endDate").val())});
	}
	
	if(status != '0'){
		queryParam.push({name:'requestParam.inMap.status',value:status});
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
	}).done(function(data) {
		data.totalPage = parseInt((data.total + data.pageSize - 1)/data.pageSize);
		$dataGrid.html(juicer(tplGrid, data));
		
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

/***
 * 分转元，保留两位小数
 * @param fen
 * @returns {String}
 */
function convertFen2Yuan(fen,flg){
	var ret='0.00';
	if(fen){
		ret = fmoney(fen/100, 2);
	}
	return ret;
}

/***
 * 分转元，保留N位小数
 * @param s
 * @returns {String}
 */
function fmoney(s, n){
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   var t = "";
   for(var i = 0; i < l.length; i ++ ) {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}
