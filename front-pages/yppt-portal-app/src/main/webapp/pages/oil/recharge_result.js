$(
	function() {

		$("#startTime").html(getSmpFormatDateByLong(new Date().getTime(), false));
		$("#pb01").addClass("pro_icon1");
		$("#pb02").addClass("pro_icon2");
		$("#pb03").addClass("pro_icon3");
		
	$.ajax({
		url : "../../recharge/getRechargeById.action",
		data : {"id":id},
		dataType : 'JSON'
	}).done(function(data) {
		var dt=data.dataObject;
		if(data&&dt){
			
			$("#startTime").html(getSmpFormatDateByLong(dt.applyTime, false));
			
//			if(dt.state=='01'||dt.state=='03'){
//				$("#pb01").addClass("pro_icon5");
//				$("#pb02").addClass("pro_icon4");
//				$("#pb03").addClass("pro_icon3");
//			}
//			
//			
//			if(dt.state=='04'||dt.state=='06'||dt.state=='07'){
				$("#pb01").addClass("pro_icon1");
				$("#pb02").addClass("pro_icon2");
				$("#pb03").addClass("pro_icon3");
//			}
//			
//			if(dt.state=='05'){
//				$("#pb01").addClass("pro_icon1");
//				$("#pb02").addClass("pro_icon6");
//				$("#pb03").addClass("pro_icon7");
//				$("#endTime").html(getSmpFormatDateByLong(dt.approvalTime, false));
//			}
		}
	});
});

