var ajaxBillingInfoListQueryUrl = '../../billing/queryBillingInfoList.action',
ajaxAddrInfoQueryUrl = '../../recieveraddress/queryStockAddress.action',
$dataGrid = $('#dataGrid'),
tplGrid = $('#tplGrid').html(),
$changeAddr = $('#changeAddr'),
$addAddr = $('#addAddr');
$(function() {
	//初始页面
	$("#form").Validform({
		tiptype:2,	
		ajaxPost:true,
		url :"../../billing/billingApply.action",
		datatype:{//传入自定义datatype类型  
			"billingMoney" : function(gets,obj,curform,regxp){  
				var billingMoney = $('#billingMoney').val();
				if (billingMoney && billingMoney != null) {
					if (billingMoney.search(/^\d*(?:\.\d{0,2})?$/) == -1) {
						return "请输入合法的开票金额！";
					}
					if (billingMoney == 0) {
						return "金额应大于0,请重新输入！";
					} 
					if(parseInt(billingMoney) > 9999999){
						return "输入金额不能超出7位数！";
					}
				} else {
					return "请输入开票金额！";
				}
				return true;
			}
		 },
		 beforeSubmit : function() {
			$('input[type=submit]').removeClass('billing_titlesbm');
			$('input[type=submit]').addClass('billing_titlesbm2');
			$('input[type=submit]').attr('disabled', true);
			var billingInfoId = $('#billingInfoId').val();
			if(!billingInfoId || billingInfoId=="") {
				alter("请先完善发票抬头！");
				return false;
			}
		 },
		 callback : function(data) {
			 $('input[type=submit]').removeClass('billing_titlesbm2');
			 $('input[type=submit]').addClass('billing_titlesbm');
			 $('input[type=submit]').removeAttr('disabled');
			 if (data && data.dataObject) {
				 alert("开票申请成功!", function() {window.location.href = basePath + 'pages/billing/billing_process.jsp?id='+data.dataObject;});
			 } else if(data && data.dataObject && (data.dataObject == "1"||data.dataObject == "2")){
				 alert("开票申请失败!");
			 }
		}
		
	});	
	
	/* Ajax查询开票信息列表方法 */
	$.ajax({
		url : ajaxBillingInfoListQueryUrl,
		data : {},
		dataType : 'JSON',
		headers : {
			'grid' : 1
		}
	}).done(function(data) {
		$dataGrid.html(juicer(tplGrid, data));
		//选择发票信息
		$('#billingInfoId').val($('.inv-selected').children('input').val());
		$('#dataGrid li').click(function() {
			$('#billingInfoId').val($(this).children().children('input').val());
			if(!$(this).children('a').hasClass('inv-selected')) {
				$(this).children('a').addClass('inv-selected');
			}
			$(this).siblings().children('a').removeClass('inv-selected');
		});
	});
	/* Ajax查询地址信息方法 */
	$.ajax({
		url : ajaxAddrInfoQueryUrl,
		data : {},
		dataType : 'JSON',
		headers : {
			'grid' : 1
		}
	}).done(function(data) {
		if(data && data.data && data.data.length > 0) {
			var addr = data.data[0];
			if(addr.id) {
				$('#recAddressId').val(addr.id);
			}
			if(addr.recieverName) {
				$('#receiveName').html(addr.recieverName);
				$('#recName').val(addr.recieverName);
			}
			if(addr.phoneNum) {
				$('#receiveMobi').html(addr.phoneNum);
				$('#recMobi').val(addr.phoneNum);
			}
			var level1="",level2="",level3="";
			if(addr.province) {
				$('#recProvince').val(addr.province);
				level1 = addr.province.substr(0,2);
			}
			if(addr.city) {
				$('#recCity').val(addr.city);
				level2 = addr.city.substr(0,4);
			}
			if(addr.district) {
				$('#recDistrict').val(addr.district);
				level3 = addr.district;
			}
			var address = threeLevelName(level1,level2,level3);
			$('#receivePCD').html(address +" "+addr.address);
			
			if(addr.address) {
				$('#recAddress').val(addr.address);
			}
			if(addr.zipCode){
				$('#receiveZipCode').html(addr.zipCode);
				$('#recZipCode').val(addr.zipCode);
			}
			$('#addAddr').hide(); 
			$('#changeAddr').show(); 
		}else{
			$('#addAddr').show(); 
			$('#changeAddr').hide();
		}
		//选择发票信息
	});
	//变更收件人地址信息
	$changeAddr.click(function() {
		window.open(basePath + 'pages/oil/oil_address_info_main.jsp?pagetype=2');
	});
	$addAddr.click(function() {
		window.open(basePath + 'pages/oil/oil_address_info_main.jsp?pagetype=2');
	});
	
	
});

function setBillingInfo(){
	window.location.href = basePath + 'pages/billing/billing_info.jsp';
}



function threeLevelName(level1,level2,level3){
	var address = "";
	//查询省
	if(level1 && level1!="") {
		for ( var i = 0; i < postCodeJson["00"].length; i++) {
			var that = postCodeJson["00"][i];
			if (that.hasOwnProperty(level1)) {
				address = address + " " +that[level1];
				break;
			}
		}
	}
	

	//查询市
	if(level2 && level2!=""){
		for ( var i = 0; i < postCodeJson[level1].length; i++) {
			var that = postCodeJson[level1][i];
			if (that.hasOwnProperty(level2)) {
				address = address + " " +that[level2];
				break;
			}
		}
	}

	//查询县/区
	if(level3 && level3!="") {
		for ( var i = 0; i < postCodeJson[level2].length; i++) {
			var that = postCodeJson[level2][i];
			if (that.hasOwnProperty(level3)) {
				address = address + " " +that[level3];
				break;
			}
		}
	}

	return address;
}