var ajaxImgUrlByImgIdQueryUrl = '../../file/queryImgUrlByImgId.action';

(function() {
	
	var Billingdetail = function() {
		this.init();
	};
	Billingdetail.prototype = {
		config : {
		},
		cache : {
			parms : {}
		},
		init : function() {
			this.initMsg();
		},
		initMsg : function() {
			//查询累计开票金额
			$.ajax({
				 type: "POST",
	             url: '../../billing/getBillingApplyById.action',
	             data : {"id":id},
	             dataType: "json",
	             success: function(data){
	            	 var planHtml = "";
	            	 if(data.dataObject.status=="3"){
	 				 	planHtml += " <tr><td class=\"pb10\" style='text-align:left;'>开票状态：开票失败</td></tr> ";
	 				 	if(data.dataObject.mark){
	 				 		planHtml += " <tr><td class=\"pb10\">失败原因："+data.dataObject.mark+"，如有问题可拨打客服电话进行咨询 4000966666</td></tr> ";
	 				 	}else{
	 				 		planHtml += " <tr><td class=\"pb10\">失败原因：，如有问题可拨打客服电话进行咨询 4000966666</td></tr> ";
	 				 	}
	 				}else{
	 					planHtml += " <tr><td class=\"pb10\">已提交申请</td><td class=\"pb10\">信息审核</td> ";
	 				    planHtml += " <td class=\"pb1\">开票成功</td></tr> ";
	 					planHtml += " <tr> ";
	 					if(data.dataObject.status=="2"){//开票成功
	 						planHtml += " <td class=\"pro_icon1\"></td> ";
	 						planHtml += " <td class=\"pro_icon6\"></td> ";
	 						planHtml += " <td class=\"pro_icon7\"></td> ";
	 					}else{
	 						planHtml += " <td class=\"pro_icon1\"></td> ";
	 						planHtml += " <td class=\"pro_icon2\"></td> ";
	 						planHtml += " <td class=\"pro_icon3\"></td> ";
	 					}
	 					planHtml += "<td></td></tr>";
	 					
	 					planHtml += " <tr> ";
	 					planHtml += " <td class=\"pt10 pl10 pr10 vt\" id=\"createTime\"></td> ";
	 					planHtml += " <td class=\"pt10 pl10 pr10 vt\">1-3个工作日</td> ";
	 					planHtml += " <td class=\"pt10 pl10 pr10 vt\" id=\"modifyTime\"></td> ";
	 					
	 					planHtml += " </tr> ";
	 				}
	 				$("#planState").html(planHtml);
	            	 
	 				$('#createTime').html(getSmpFormatDateByLong(data.dataObject.createTime, false));
	 				if(data.dataObject.status=="2"){
	 					$('#modifyTime').html(data.dataObject.modifyTime == null ? "" :getSmpFormatDateByLong(data.dataObject.modifyTime, false)+
		 						"<br/>"+data.dataObject.expressCompany+"   " + data.dataObject.expressOrderNo);
	 				}
     				$('#createTime_detail').html(getSmpFormatDateByLong(data.dataObject.createTime, false));
            	    $('#invoiceName').html(data.dataObject.invoiceName);
            	    $('#invoiceMoney').html(data.dataObject.invoiceMoney+"元");
            	    $('#receiverName').html(data.dataObject.receiverName+"   " + data.dataObject.receiverPhoneNum);
            	    
            	    var _province = data.dataObject.province.substr(0,2);
            	    var _city = data.dataObject.city.substr(0,4);
            	    
            	    var receiverArea = getReceiverAreaName(_province,_city,data.dataObject.district) + "-"+data.dataObject.address +"<br/>"+data.dataObject.zipCode;
            	    $('#receiverArea').html(receiverArea);
	         				
	             }	
			});
		}
	};

	$(document).ready(function() {
		window.billingdetail = new Billingdetail();
		
	});

})();


function getSecondArea(level1,level2){
	var address = "";
	for ( var i = 0; i < postCodeJson[level1].length; i++) {
		var that = postCodeJson[level1][i];
		if (that.hasOwnProperty(level2)) {
			address =  that[level2];
			break;
		}
	}
	return address;
}

function getReceiverAreaName(level1,level2,level3){
	var address = "";
	//查询省
	for ( var i = 0; i < postCodeJson["00"].length; i++) {
		var that = postCodeJson["00"][i];
		if (that.hasOwnProperty(level1)) {
			address = address + that[level1];
			break;
		}
	}
	//查询市
	for ( var i = 0; i < postCodeJson[level1].length; i++) {
		var that = postCodeJson[level1][i];
		if (that.hasOwnProperty(level2)) {
			address = address + " "+that[level2];
			break;
		}
	}
	//查询县/区
	for ( var i = 0; i < postCodeJson[level2].length; i++) {
		var that = postCodeJson[level2][i];
		if (that.hasOwnProperty(level3)) {
			address = address + " "+that[level3];
			break;
		}
	}
	return address;
}
