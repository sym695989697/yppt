$(function(){
	var reasonId = parent.window.goldTradeList.cache.reasonId;
	var userName = parent.window.goldTradeList.cache.userName;
	var userRegPhone = parent.window.goldTradeList.cache.userRegPhone;
	
	$('#juserName').html(userName);
	$('#juserRegPhone').html(userRegPhone);
	var params = {
		reasonId :reasonId	
	};
	JAjax(root + "/goldTrade/getGoldTradeDetailsPage.action", params, 'json',function(data) {
		var griddata = data.data;
		//旺金币抵扣
		var creditMoney = 0;
		for(var i=0;i<griddata.length;i++){
			if(griddata[i].creditMoney!=null){
				creditMoney=griddata[i].creditMoney;
			}
		}
		//付款类型
		var payWay;
		for(var i=0;i<griddata.length;i++){
			if(griddata[i].payWay!=null){
				payWay=griddata[i].payWay;
			}
		}
		//支付金额
		var captialMoney = 0;
		for(var i=0;i<griddata.length;i++){
			if(griddata[i].captialMoney!=null){
				captialMoney=griddata[i].captialMoney;
				break;
			}
		}
		
		//付款总金额
		var totalMoney=0;
		for(var i=0;i<griddata.length;i++){
			if(griddata[i].totalMoney!=null){
				totalMoney=griddata[i].totalMoney;
				break;
			}
		}
		
		$('#creditMoney').html(creditMoney);//旺金币抵扣
		debugger;
		$('#payWay').html(payWay==null || payWay=='CREDIT'?'支付金额':CODE_UTIL.getCodeName('IC-PAY-WAY', row.payWay));//支付方式
		$('#captialMoney').html(captialMoney);//支付方式 
		$('#totalMoney').html(totalMoney);//充值总额

		var grid = $("#maingrid").ligerGrid({
            columns: [
                { name: 'cardNo', display: '卡号', width: 280 },
                { name: 'vehicleNo', display: '绑定车辆', width: 220 },
                { name: 'money', display: '充值金额(元)', width: 220 }
            ],
            data: { Rows: griddata },
            usePager: false
     	}); 
		 
	});;
	         
});