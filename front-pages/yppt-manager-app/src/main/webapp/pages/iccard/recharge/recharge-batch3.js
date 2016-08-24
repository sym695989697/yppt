(function(){
	var RechargeBatch3 = function(){
		this.init();
	};
	RechargeBatch3.prototype={
		init:function(){
			var selectedRows=parent.window.rechargeList.cache.selectedRows;
			var appendStr='';
			$(selectedRows).each(function(i,n){
				if(n){
					var cardNum=this.cardNumber;
					var vCardNoAndColor=(this.vehicleNo?this.vehicleNo:'')+(this.vehicleColor?'-'+CODE_UTIL.getCodeName('V-C-COLOR',this.vehicleColor):'');
					var money=n['rechargeMoney'];
					var removeStr="<a href=\"javascript:window.rechargeBatch3.removeCard("+i+")\" >移除</a>";
					appendStr+='<tr id="tr_'+i+'">';
					appendStr+='<td align="center" style="background:white;height:30px;">'+cardNum+'</td>';
					appendStr+='<td align="center" style="background:white;">'+vCardNoAndColor+'</td>';
					appendStr+='<td align="center" style="background:white;">'+money+'</td>';
					appendStr+='</tr>';
				}
			});
			$("#table_1").append(appendStr);
			$("#sumDiv").css("top",$(window).height()-150);
			$("#cardCount").text(parent.window.rechargeList.cache.cardCount);
			$("#sumMoney").text(parent.window.rechargeList.cache.sumMoney);
			//申请信息
			$("#regUserPhone").text(parent.window.rechargeList.cache.regUserPhone);
			$("#memberName").text(parent.window.rechargeList.cache.memberName);
			$("#applyDataSource").text(CODE_UTIL.getCodeName('CARD-DATASOURCE','2'));
			$("#applyPersonName").text(parent.parent.INDEX_USER_NAME);
		},
		success_alert : function(data){		        	
	        JSWK.clewBox(data,'clew_ico_succ');
	     },
	     fail_alert : function(data){
	        JSWK.clewBox(data,'clew_ico_fail',failShowTime);
	     },
	     alert_message : function(data,onCloseBefore){
	    	 $.ligerDialog.alert(data,onCloseBefore);
	     }
	};
	$(document).ready(function(){
		window.rechargeBatch3 = new RechargeBatch3();
	});
})();