//数据对象模板
var $reSelectCard = $('#reSelectCard'),
$comfirmRecharge = $('#comfirmRecharge'),
$rechargeAmount = $('#rechargeAmount'),
$batchRecharge = $('#batchRecharge'),
ajaxCardRechargerUrl = '../../recharge/oilCardRechargeDataTransmit.action';
$(function() {
	//重新选卡
	$reSelectCard.click(function() {
		window.location.href = basePath + 'pages/oil/oil_cards.jsp';
	});
	//计算充值总额
	$(".rechargeAmount").on('input propertychange',function(){
			var amount = $(this).val();
			if (amount && amount != null) {
				if (amount.search(/^\d*(?:\.\d{0,2})?$/) == -1) {
					$('#dataGrid .rechargeAmount').blur();
					alert("输入的充值金额不合法,请重新输入!");
					return false;
				} 
				if(amount > 950000){
					alert("单卡充值最大金额950000.00元,请重新输入!");
					return false;
				}
			}
		//计算充值总额并展示
		countRechargeAmount();
	});
	//批量充值
	$batchRecharge.on('input propertychange',function(){
		var batchRechargeAmount = $(this).val();
		if (batchRechargeAmount && batchRechargeAmount != null) {
			if (batchRechargeAmount.search(/^\d*(?:\.\d{0,2})?$/) == -1) {
				$(this).blur();
				alert("输入的批量充值金额不合法,请重新输入!");
				return false;
			}
			if(batchRechargeAmount > 950000){
				alert("单卡充值最大金额950000.00元,请重新输入!");
				return false;
			}
		}
		
		$('#dataGrid .rechargeAmount').each(function (i) {
			$(this).val(batchRechargeAmount);
		}); 
		//计算充值总额并展示
		countRechargeAmount();
	});
	//取消批量充值
	$(".rechargeAmount").on('click', function () {
		$batchRecharge.val('');
	});
	//取消单个充值
	$(".color_sizeD").click(function() {
		$(this).parent().parent().remove();
		//计算充值总额并展示
		countRechargeAmount();
	});
	//充值
	$comfirmRecharge.click(function() {
		var param={};
		var valid = true;
		
		if($('#dataGrid tr').length <= 0){
			alert("没有选择充值卡！");
			return false;
		}
		
		if($('#rechargeAmount').html()=="0.00"){
			alert("充值总额不能为0！");
			return false;
		}
		
		$('#dataGrid tr').each(function (i) {
			var rechargeAmount = $(this).children("td").eq(3).children("input:text").val();
			if (rechargeAmount && rechargeAmount != null) {
				if (rechargeAmount.search(/^\d*(?:\.\d{0,2})?$/) == -1) {
					alert("第" + (++i) + "张油卡输入的充值金额不合法,请重新输入!");
					valid = false;
					return false;
				} 
				if (parseFloat(rechargeAmount) == 0) {
					alert("第" + (++i) + "张油卡输入的充值金额为0,请重新输入!");
					valid = false;
					return false;
				}
			} else {
				alert("第" + (++i) + "张油卡未输入输入充值金额,请重新输入!");
				valid = false;
				return false;
			}
			param['cardRecharges[' + i + '].id'] = $(this).children("td").eq(0).children("input:hidden").val();
			param['cardRecharges[' + i + '].cardNO'] = $(this).children("td").eq(0).text();
			param['cardRecharges[' + i + '].carNO'] = $(this).children("td").eq(1).text();
			param['cardRecharges[' + i + '].balance'] = $(this).children("td").eq(2).text();
			param['cardRecharges[' + i + '].rechargeAmount'] = rechargeAmount;
		}); 
		if(!valid){
			return false;
		}
		$('#comfirmRecharge').removeClass('btn_bra2');
		$('#comfirmRecharge').addClass('btn_bra3');
		$('#comfirmRecharge').attr('disabled', true);
		$.ajax({
			url : ajaxCardRechargerUrl,
			data : param,
			dataType : 'JSON',
			type : 'POST'
		}).done(function(data) {
			$('#comfirmRecharge').removeClass('btn_bra3');
			$('#comfirmRecharge').addClass('btn_bra2');
			$('#comfirmRecharge').removeAttr('disabled');
			if(data && data.dataObject && data.dataObject == "0") {
				window.location.href = basePath + 'pages/card/card_recharge_way.jsp';
			} else if(data && data.dataObject && data.dataObject == "2"){
				alert(data.message);
			} else {
				alert("操作失败！");
			}
		});
	});
});
//计算充值总额并展示
function countRechargeAmount() {
	var rechargeAmount = 0.00;
	$('#dataGrid .rechargeAmount').each(function (i) {
		var amount = $(this).val();
		if(amount && amount != null && amount.trim() != null) {
			rechargeAmount = rechargeAmount + parseFloat(amount);
		}
	}); 
	$rechargeAmount.html(rechargeAmount.toFixed(2));
}
