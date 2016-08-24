var ajaxOilCardApplyDetailQueryUrl = '../../oilcard/queryOilCardDetail.action';
var picurl = [];
$(function() {
	//初始页面
	/* Ajax查询开卡详情方法 */
	$.ajax({
		url : ajaxOilCardApplyDetailQueryUrl,
		data : {"cardApplyDetailId":cardApplyDetailId},
		dataType : 'JSON'
	}).done(function(data) {
		if(data && data != null && data.dataObject && data.dataObject != null) {
			if(data.dataObject.iCCardApply && data.dataObject.iCCardApply != null) {
				var planHtml = "";
				if(data.dataObject.iCCardApply.status=="10"){
				 	planHtml += " <tr><td class=\"pb10\" style='text-align:left;'>办卡状态：失败</td></tr> ";
				 	if(data.dataObject.iCCardApply.mark){
				 		planHtml += " <tr><td class=\"pb10\">失败原因："+data.dataObject.iCCardApply.mark+"，如有问题可拨打客服电话进行咨询 4000966666</td></tr> ";
				 	}else{
				 		planHtml += " <tr><td class=\"pb10\">失败原因：，如有问题可拨打客服电话进行咨询 4000966666</td></tr> ";
				 	}
				}else{
					planHtml += " <tr><td class=\"pb10\">已提交申请</td><td class=\"pb10\">卡片办理</td> ";
				    planHtml += " <td class=\"pb1\">办理成功</td></tr> ";
					planHtml += " <tr> ";
					
						planHtml += " <td class=\"pro_icon1\"></td> ";
						planHtml += " <td class=\"pro_icon2\"></td> ";
						planHtml += " <td class=\"pro_icon3\"></td> ";
					
					planHtml += "<td></td></tr>";
					
					planHtml += " <tr> ";
					planHtml += " <td class=\"pt10 pl10 pr10 vt\" id=\"createTime\"></td> ";
					planHtml += " <td class=\"pt10 pl10 pr10 vt\">1-3个工作日</td> ";
					planHtml += " <td class=\"pt10 pl10 pr10 vt\"></td> ";
					planHtml += " </tr> ";
				}
				$("#planState").html(planHtml);
				
				$('#createTime').html(getSmpFormatDateByLong(data.dataObject.iCCardApply.createTime, false));
				
				$('#cardType').html(CODE.getCodeName('IC-CARD-TYPE',data.dataObject.iCCardApply.cardType));
				var oftenArea = data.dataObject.iCCardApply.oftenArea.split(",");
				var areaText = "";
				for(var i = 0;i<oftenArea.length;i++){
					if(oftenArea[i]!=null&&oftenArea[i]!=""){
						if(i==(oftenArea.length-2)){
							areaText += getProArea(oftenArea[i].substr(0,2));
						}else{
							areaText += getProArea(oftenArea[i].substr(0,2))+"，";
						}
					}
				}
				$('#oftenArea').html(areaText);
				$('#userName').html(data.dataObject.iCCardApply.applyUserName);
				$('#idNo').html(data.dataObject.iCCardApply.idNo);
				$('#receiverName').html(data.dataObject.iCCardApply.receiverName);
				$('#receiverPhoneNum').html(data.dataObject.iCCardApply.receiverPhoneNum);
				$('#receiverArea').html(threeLevelName(data.dataObject.iCCardApply.province.substr(0,2),data.dataObject.iCCardApply.city.substr(0,4),data.dataObject.iCCardApply.district)+" "+data.dataObject.iCCardApply.address);
				$('#zipCode').html(data.dataObject.iCCardApply.zipCode);
			}
			if(data.dataObject.iCCardApplyDetail && data.dataObject.iCCardApplyDetail != null && data.dataObject.iCCardApplyDetail.length > 0) {
				
				var detailHtml = "";
				
				for(var i = 0;i<data.dataObject.iCCardApplyDetail.length;i++){
					
					detailHtml +=" <div class='pt20'> ";
					detailHtml +=" <div class='rightsideBar_title'> ";
					detailHtml +=" <h3 class='fb f14 inline fl'>卡片信息</h3> ";
					detailHtml +=" </div> ";
					detailHtml +=" <div class='border_rightBar pb30 pt30'> ";
					detailHtml +=" <div class='lists-form'> ";
					detailHtml +=" <table> ";
					detailHtml +=" <tr> ";
					detailHtml +=" <td class='lab'><span class='required'></span>车牌号：</td> ";
					detailHtml +=" <td class='fld'>"+data.dataObject.iCCardApplyDetail[i].vehicleNo+"</td> ";
					detailHtml +=" <td class='mark'></td> ";
					detailHtml +=" </tr> ";
					detailHtml +=" <tr> ";
					detailHtml +=" <td class='lab'><span class='required'></span>行驶证：</td> ";
					
					if(data.dataObject.iCCardApplyDetail[i].vehicleLicense){
						var maxurl = data.dataObject.iCCardApplyDetail[i].vehicleLicense.replace("mid","max");
						detailHtml +=" <td class='fld'>" +
								"<a href = \"javascript:void(0)\" onclick =" +
								"\"document.getElementById('light"+i+"').style.display='block';document.getElementById('fade"+i+"').style.display='block'\"><img" +
								" src=\""+data.dataObject.iCCardApplyDetail[i].vehicleLicense+"\" style='border:#e7e7e7 1px solid;padding:2px;'></a><div id=\"light"+i+"\" class=\"white_content1\"><a href = \"javascript:void(0)\" onclick =" +
								"\"document.getElementById('light"+i+"').style.display='none';document.getElementById('fade"+i+"').style.display='none'\"><img class=\"mr\"" +
								"src=\"../../images/handle/close.png\"></a><img src=\""+maxurl+"\" width=\"100%\"></div>" +
								"<div id=\"fade"+i+"\" class=\"black_overlay1\"></div>" +
								"</td> ";
					}
					detailHtml +=" <td class='mark'></td> ";
					detailHtml +=" </tr> ";
					detailHtml +=" <tr> ";
					detailHtml +=" <td class='lab'><span class='required'></span>绑定手机：</td> ";
					detailHtml +=" <td class='fld'>"+data.dataObject.iCCardApplyDetail[i].phoneNum+"</td> ";
					detailHtml +=" <td class='mark'></td> ";
					detailHtml +=" </tr> ";
					detailHtml +=" <tr> ";
					detailHtml +=" <td class='lab'><span class='required'></span>短信提醒：</td> ";
					detailHtml +=" <td class='fld'>"+CODE.getCodeName('IS-SEND-MESSAGE',data.dataObject.iCCardApplyDetail[i].acceptMessage)+"</td> ";
					detailHtml +=" <td class='mark'></td> ";
					detailHtml +=" </tr></table></div></div></div> ";
					
				}
				
				$("#cardApply").html(detailHtml);
				
				
			}
		}
	});
});
function getProArea(level1){
	var address = "";
	for ( var i = 0; i < postCodeJson["00"].length; i++) {
		var that = postCodeJson["00"][i];
		if (that.hasOwnProperty(level1)) {
			address = address + that[level1];
			break;
		}
	}
	return address;
}
function threeLevelName(level1,level2,level3){
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
			address = address + that[level2];
			break;
		}
	}
	//查询县/区
	for ( var i = 0; i < postCodeJson[level2].length; i++) {
		var that = postCodeJson[level2][i];
		if (that.hasOwnProperty(level3)) {
			address = address + that[level3];
			break;
		}
	}
	return address;
}