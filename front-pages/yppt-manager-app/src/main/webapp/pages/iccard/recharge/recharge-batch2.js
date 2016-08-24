(function(){
	var RechargeBatch2 = function(){
		this.init();
	};
	RechargeBatch2.prototype={
		init:function(){
			$("#sumDiv").css("top",$(window).height()-40);
			var selectedRows=parent.window.rechargeList.cache.selectedRows;
			var appendStr='';
			$(selectedRows).each(function(i,n){
				if(n){
					var cardNum=this.cardNumber;
					var vCardNoAndColor=(this.vehicleNo?this.vehicleNo:'')+(this.vehicleColor?'-'+CODE_UTIL.getCodeName('V-C-COLOR',this.vehicleColor):'');
					var moneyInput='<input type="text" id="rechargeMoney'+this.id+'" class="moneyInput" value="'+(this.rechargeMoney?this.rechargeMoney:"")+'"/>';
					var removeStr="<a href=\"javascript:window.rechargeBatch2.removeCard('"+this.id+"')\" id=\""+this.id+"\" >移除</a>";
					appendStr+='<tr id="tr_'+this.id+'">';
					appendStr+='<td align="center" style="background:white;height:30px;">'+cardNum+'</td>';
					appendStr+='<td align="center" style="background:white;">'+vCardNoAndColor+'</td>';
					appendStr+='<td align="center" style="background:white;">'+moneyInput+'</td>';
					appendStr+='<td align="center" style="background:white;">'+removeStr+'</td>';
					appendStr+='</tr>';
				}
			});
			$("#cardCount").text(parent.window.rechargeList.cache.cardCount?parent.window.rechargeList.cache.cardCount:0);
			$("#sumMoney").text(parent.window.rechargeList.cache.sumMoney?parent.window.rechargeList.cache.sumMoney:'0.00');
			$("#table_1").append(appendStr);
			//单条金额文本框事件绑定
			$('.moneyInput').on('keyup',function(i){
				if(isNaN(this.value)||this.value.trim()==''){
					$(this).val('');
				}
				$("#batchMoney").val('');
				if(this.value.indexOf('.')==-1 || this.value.indexOf('.')>=this.value.length-3){
					window.rechargeBatch2.sumData();
				}
			});
			$('.moneyInput').on('blur',function(){
				if(this.value){
					if(this.value.indexOf('.')==0){
						var value='0'+this.value;
						$(this).val(value);
					}
					if(this.value.substring(this.value.length-1)=='.'){
						var value=this.value+'00';
						$(this).val(value);
					}else if(this.value.indexOf('.')==-1){
						var value=this.value+'.00';
						$(this).val(value);
					}else if(this.value.indexOf('.')==this.value.length-2){
						var value=this.value+'0';
						$(this).val(value);
					}else if(this.value.indexOf('.')<this.value.length-3){
						var value=this.value.substring(0,(this.value.indexOf('.')+3));
						$(this).val(value);
					}
					if(this.value=='0.00'){
						$(this).val('');
					}else if(parseFloat(this.value)>950000.00){
						$(this).val('950000.00');
					}else if(parseFloat(this.value)<1.00){
						//$(this).val('1.00');
					}
				}
				window.rechargeBatch2.sumData();
			});
			//批量充值文本框事件绑定
			$("#batchMoney").on('keyup',function(i){
				if(isNaN(this.value)||$(this).val().trim()==''){
					$(this).val('');
				}
				if(this.value=='0.00'){
					$(this).val('');
				}else if(parseFloat(this.value)>950000.00){
					$(this).val('950000.00');
				}else if(parseFloat(this.value)<1.00){
					//$(this).val('1.00');
				}
				$('.moneyInput').val(this.value);
				if(this.value.indexOf('.')==-1 || this.value.indexOf('.')>=this.value.length-3){
					window.rechargeBatch2.sumData();
				}
			});
			$('#batchMoney').on('blur',function(){
				if(this.value){
					if(this.value.substring(this.value.length-1)=='.'){
						var value=this.value+'00';
						$(this).val(value);
					}else if(this.value.indexOf('.')==-1){
						var value=this.value+'.00';
						$(this).val(value);
					}else if(this.value.indexOf('.')==this.value.length-2){
						var value=this.value+'0';
						$(this).val(value);
					}else if(this.value.indexOf('.')<this.value.length-3){
						var value=this.value.substring(0,(this.value.indexOf('.')+3));
						$(this).val(value);
					}
				}
				$('.moneyInput').val(this.value);
				window.rechargeBatch2.sumData();
			});
		},
		removeCard:function(removeId){
			var tem=[];
			$(parent.window.rechargeList.cache.selectedRows).each(function(i,n){
				if(n.id==removeId) return true;
				tem.push(n);
			});
			parent.window.rechargeList.cache.selectedRows=tem;
			$("#tr_"+removeId).remove();
			window.rechargeBatch2.sumData();
			if(parent.window.rechargeList.cache.selectedRows.length==0){
				parent.window.rechargeList.pre_1(null,parent.window.rechargeList.cache.dialog);
			}
		},
		sumData:function(){
			var selectedRows=parent.window.rechargeList.cache.selectedRows;
			var cardCount=0;
			$(selectedRows).each(function(i,n){
				if(n){
					cardCount++;
				}
			});
			$("#cardCount").text(cardCount);
			var sumMoney=0;
			$(".moneyInput").each(function(i,n){
				if(n.value){
					sumMoney+=n.value*100;
				}
			});
			sumMoney = sumMoney/100;
			$("#sumMoney").text((sumMoney+'').indexOf('.')==-1?(sumMoney+'.00'):(sumMoney+'').indexOf('.')>(sumMoney+'').length-3?(sumMoney+'0'):(sumMoney+''));
		},
		resert_money:function(){
			$("input[type='text']").val('');
			window.rechargeBatch2.sumData();
		},
		saveMoneyToCache:function(){
			var flag=true;
			$(parent.window.rechargeList.cache.selectedRows).each(function(i,n){
				if(n){
					var value = $("#rechargeMoney"+n.id).val();
					if (value){
						parent.window.rechargeList.cache.selectedRows[i]['rechargeMoney']=value;
					}else{
						$("#rechargeMoney"+n.id).focus();
						flag=false;
						return false;
					}
				}
			});
			if(!flag){
				this.fail_alert("请输入充值金额");
				return ;
			}
			parent.window.rechargeList.cache.cardCount=$("#cardCount").text();
			parent.window.rechargeList.cache.sumMoney=$("#sumMoney").text();
		},
		success_alert : function(data){		        	
	        JSWK.clewBox(data,'clew_ico_succ');
	     },
	     fail_alert : function(data){
	        JSWK.clewBox(data,'clew_ico_fail',failShowTime);
	     }
	};
	$(document).ready(function(){
		window.rechargeBatch2 = new RechargeBatch2();
	});
})();