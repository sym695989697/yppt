/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
var safari = (navigator.userAgent.toLowerCase().indexOf('safari') != -1) ? true : false;
var totalwang = '0.00';
var paywang = '0.00';
var paynum = 0.00;
// var isShow = false;
(function() {
	$("#affrimForm").Validform({
		ajaxPost : true,
		url :"../../recharge/rechargeApply.action",
		callback : function(d) {
			if(d && d != null && d.dataObject && d.dataObject != null){
				var result =  d.dataObject;
				if(result.orderId) {
					$('#orderId').val(result.orderId);
					if(result.onlyCredit) {
						//跳转到油卡充值流程页面
						window.location.href = basePath + 'pages/oil/recharge_result.jsp?id='+result.orderId;
					} else {
						if(result.url) {
							//跳转到支付中心
							
							window.location.href = result.url;
							windows.close();
							//window.open(result.url);
							
						} else {
							alert("充值申请支付失败!");
						}
					}
				} else {
					alert("充值申请生成业务订单失败!");
				}
			}else{
				alert("充值申请发送异常，请重新提交!");
			}
		}
	});
	var CardRechargeWay = function() {	
		this.init();
	};
	CardRechargeWay.prototype = {
		init : function() {
			//
			$("#wangjinbi").bind('change',function(){
				var v = $(this).val();
				if(v && v >totalwang){
					alert("输入旺金币大于可用旺金币！");
				}else{
					paywang = v;
					changeCredit();
				}
			});
			// 加载充值总额
			$(".inputmoney").each(function(){
				var amount = $(this).html();
				if(amount && amount != null && amount.trim() != null && amount.trim() != "null") {
					paynum = paynum + parseFloat(amount);
				}
			});
			$("#inputpaynum").html(paynum.toFixed(2));
            $("#zhangfuyue").html(paynum.toFixed(2));
       	    $("input[name='paychecknum2']").val(paynum.toFixed(2));
			// 加载旺金币
			$.ajax({
				type: "POST",
	             url: "../../creditaccount/queryCreditAccountBalanceavailableFormat.action",
	             data: {},
	             dataType: "json",
	             success: function(data){
	            	 if(data && data!= null && data.dataObject && data.dataObject != null){
	            	    // 缓存账户余额
	            		paywang = data.dataObject;
	            		totalwang = parseFloat(data.dataObject);
	            	 }
	            	 $("#totalWangjinbi").html(totalwang);
	            	 $("#wangjinbi").val(paywang);
	            	 $("input[name='paychecknum1']").val(paywang);
	            	 changeCredit();
	             }	
			});
		}
	};
	
	$(document).ready(function() {
		window.cardRechargeWay = new CardRechargeWay();
		$(".selectDiv").each(function(){
			var divWidth = $(this).css("width");
			backwidth = divWidth.substr(0,divWidth.length-2)-40;
			
			var backStr = "#fff url(\"../../images/common/down-angle.gif\") ";
			backStr =backStr+backwidth+"px";
			backStr =backStr+" 12px no-repeat";
			$(this).css({"background":backStr});
			$(this).children("select").css({"width":backwidth+60,"color":"#B4B4B4"});
		});
		$(".rightsideBar .datetimelang2 input").css({"background":"url('../../images/oil/sideBar_icon3.png') no-repeat scroll 260px 7px #FFF"});
	});
})();

changeCredit = function(){
	if(paywang >0){
		$("#paycheck1").val("on");
	}
	paywang = parseFloat(paywang);
	if(paynum >= paywang) {
		var needPay = paynum - parseFloat(paywang);
		if(needPay > 0 ){
			$("#paycheck2").val("on");
		}
		$("#wangjinbi").val(paywang.toFixed(2));
		$("input[name='paychecknum1']").val(paywang.toFixed(2));
		$("#zhangfuyue").html(needPay.toFixed(2));
		$("input[name='paychecknum2']").val(needPay.toFixed(2));
	} else {
		$("#wangjinbi").val(paynum.toFixed(2));
		$("input[name='paychecknum1']").val(paynum.toFixed(2));
		$("#zhangfuyue").html("0.00");
		$("input[name='paychecknum2']").val("0.00");
	}
//	$("input[name='paychecknum1']").val(paywang.toFixed(2));
};
