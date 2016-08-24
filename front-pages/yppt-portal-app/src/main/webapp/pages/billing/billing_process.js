
(function() {
	
	var Billingprocess = function() {
		this.init();
	};
	Billingprocess.prototype = {
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
	 					}else if(data.dataObject.status=="0"){//
	 						planHtml += " <td class=\"pro_icon5\"></td> ";
	 						planHtml += " <td class=\"pro_icon4\"></td> ";
	 						planHtml += " <td class=\"pro_icon3\"></td> ";
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
					$('#modifyTime').html(data.dataObject.modifyTime == null ? "" :getSmpFormatDateByLong(data.dataObject.modifyTime, false));
					
            	    $('#receiverName').html(data.dataObject.receiverName+"   " + data.dataObject.receiverPhoneNum);
            	    $('#receiverArea').html(data.dataObject.receiverAreaId);
	         				
	             }	
			});
		}
	};

	$(document).ready(function() {
		window.billingprocess = new Billingprocess();
		
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
