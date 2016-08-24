var ajaxMyAddressUrl = '../../recieveraddress/queryAddressList.action';
var addMyAddressUrl = '../../recieveraddress/addAddress.action';
var num = 0;

$(function() {
	//加载以保存地址列表
	
	$("#recieverName").val("");
	$("#phoneNum").val("");
	$("#addressmore").val("");
	$("#zipCode").val("");
	$(".addrselector .addr em").html("请选择");
	
	
	$.ajax({
		url : ajaxMyAddressUrl,
		data : null,
		dataType : 'JSON'
	}).done(
			function(data) {
				num = 0;
				if (data && data != null && data.data && data.data != null) {
					var html = "";
					num = data.data.length;
					for ( var i = 0; i < data.data.length; i++) {
						if (i / 2 == 0) {
							html += "<tr>";
						} else {
							html += "<tr class=\"table_bg\">";
						}
						html += "<td id='recieverName_"+i+"' class=\"first_td\">"+ data.data[i].recieverName + "</td>";
						html += "<td id='phoneNum_"+i+"'>" + data.data[i].phoneNum + "</td>";
						if(data.data[i].province){
							html += "<td id='address_"+i+"' width='300px;'>" + threeLevelName(data.data[i].province.substr(0,2),data.data[i].city.substr(0,4),data.data[i].district)+"-"+data.data[i].address + "</td>";
						}else{
							html += "<td width='400px;'>"+data.data[i].address + "</td>";
						}
						html += "<td id='id_"+i+"' style='display:none;'>" + data.data[i].id + "</td>";
						html += "<td id='province_"+i+"' style='display:none;'>" + data.data[i].province + "</td>";
						html += "<td id='city_"+i+"' style='display:none;'>" + data.data[i].city + "</td>";
						html += "<td id='district_"+i+"' style='display:none;'>" + data.data[i].district + "</td>";
						html += "<td id='zipCode_"+i+"' style='display:none;'>" + data.data[i].zipCode + "</td>";
						html += "<td id='address1_"+i+"' style='display:none;'>" + data.data[i].address + "</td>";
						if(data.data[i].isOften==0){
							html += "<td><a class=\"color_sizeD useAddress\" id='useAddress_"+i+"' ></a></td>";
						}else{
							html += "<td><a class=\"color_sizeD useAddress\" id='useAddress_"+i+"' href='javascript:useAddress(\""+i+"\");'>使用</a></td>";
						}
						html += "<td><a class=\"color_sizeD updateAddress\" id='updateAddress_'"+i+"' href='javascript:updateAddress(\""+i+"\");'>修改</a></td>";
						if(data.data[i].isOften==0){
							html += "<td><a class=\"color_sizeD \" ></a></td>";
						}else{
							html += "<td><a class=\"color_sizeD \"  href='javascript:deleteAddress(\""+data.data[i].id+"\");'>删除</a></td>";
						}
						html += "</tr>";
					}
					$("#addressList").html(html);
				}
				$("#addressTitle").html("已保存"+num+"条地址信息，还可新增"+(10-num)+"条");
			});
	
	$("#addressLevel").addrDropmenu({
		"level" : 3
	}); //二级联动

	
	
	$("#addAddress").click(function(){
		var param ={};
		
		
		if($("#addressId").val()==""||$("#addressId").val()==null){
			if($(".useAddress").length>=10){
				alert("地址不能超过10个！");
				return false;
			}
		}
		if($("#recieverName").val()==null||$("#recieverName").val()==""){
			alert("收件人必填！");
			return false;
		}
		if(reallyLength($("#recieverName").val())>50){
			alert("收件人不能超过50字符！");
			return false;
		}
		if($("#phoneNum").val()==null||$("#phoneNum").val()==""){
			alert("手机号必填！");
			return false;
		}else{
			if(!SimpleValidator.phone($("#phoneNum").val())){
				alert("手机号格式不正确！");
				return false;
			}
		}
		
		if(($("#provinceCode").val()==null||$("#provinceCode").val()=="")||
				($("#cityCode").val()==null||$("#cityCode").val()=="")||
				  ($("#countyCode").val()==null||$("#countyCode").val()=="")){
			alert("地区填写不完整！");
			return false;
		}
		if($("#addressmore").val()==null||$("#addressmore").val()==""){
			alert("详细地区必填！");
			return false;
		}
		if(reallyLength($("#addressmore").val())>200){
			alert("详细地址不能超过200字符！");
			return false;
		}
		if($("#zipCode").val()==null||$("#zipCode").val()==""){
			alert("邮编必填！");
			return false;
		}
		if(!SimpleValidator.postalcode($("#zipCode").val())){
			alert("邮编格式不正确！");
			return false;
		}
		
		param["recieverName"]=encodeURIComponent($("#recieverName").val());
		param["phoneNum"]=$("#phoneNum").val();
		param["province"]=$("#provinceCode").val();
		param["city"]=$("#cityCode").val();
		param["district"]=$("#countyCode").val();
		param["address"]=encodeURIComponent($("#addressmore").val());
		param["zipCode"]=$("#zipCode").val();
		param["id"]=$("#addressId").val();
		$("#addAddress").attr('disabled',true);
		$("#addAddress").css({"background-color":"#CDC0B0"});
		
		
	
		
		$.ajax({
			url : addMyAddressUrl,
			data : param,
			dataType : 'JSON'
		}).done(
				function(data) {
					if(data.dataObject!=null&&data.dataObject!=2){
						alert("操作成功!");
						$.ajax({
							url : ajaxMyAddressUrl,
							data : null,
							dataType : 'JSON'
						}).done(
								function(data) {
									num = 0;
									if (data && data != null && data.data && data.data != null) {
										var html = "";
										num = data.data.length;
										for ( var i = 0; i < data.data.length; i++) {
											if (i / 2 == 0) {
												html += "<tr>";
											} else {
												html += "<tr class=\"table_bg\">";
											}
											html += "<td id='recieverName_"+i+"' class=\"first_td\">"+ data.data[i].recieverName + "</td>";
											html += "<td id='phoneNum_"+i+"'>" + data.data[i].phoneNum + "</td>";
											if(data.data[i].province){
												html += "<td id='address_"+i+"' width='400px;'>" + threeLevelName(data.data[i].province.substr(0,2),data.data[i].city.substr(0,4),data.data[i].district)+"-"+data.data[i].address + "</td>";
											}else{
												html += "<td width='400px;'>"+data.data[i].address + "</td>";
											}
											html += "<td id='id_"+i+"' style='display:none;'>" + data.data[i].id + "</td>";
											html += "<td id='province_"+i+"' style='display:none;'>" + data.data[i].province + "</td>";
											html += "<td id='city_"+i+"' style='display:none;'>" + data.data[i].city + "</td>";
											html += "<td id='district_"+i+"' style='display:none;'>" + data.data[i].district + "</td>";
											html += "<td id='zipCode_"+i+"' style='display:none;'>" + data.data[i].zipCode + "</td>";
											html += "<td id='address1_"+i+"' style='display:none;'>" + data.data[i].address + "</td>";
											if(data.data[i].isOften==0){
												html += "<td><a class=\"color_sizeD useAddress\" id='useAddress_"+i+"' ></a></td>";
											}else{
												html += "<td><a class=\"color_sizeD useAddress\" id='useAddress_"+i+"' href='javascript:useAddress(\""+i+"\");'>使用</a></td>";
											}
											html += "<td><a class=\"color_sizeD updateAddress\" id='updateAddress_'"+i+"' href='javascript:updateAddress(\""+i+"\");'>修改</a></td>";
											if(data.data[i].isOften==0){
												html += "<td><a class=\"color_sizeD \" ></a></td>";
											}else{
												html += "<td><a class=\"color_sizeD \"  href='javascript:deleteAddress(\""+data.data[i].id+"\");'>删除</a></td>";
											}
											html += "</tr>";
										}
										$("#addressList").html(html);
									
										
										$("#addressId").val("");
										$("#recieverName").val("");
										$("#phoneNum").val("");
										$("#addressmore").val("");
										$("#zipCode").val("");
										$(".addrselector .addr em").html("请选择");
										$("#title_new").html("新增地址信息");
									}
									$("#addressTitle").html("已保存"+num+"条地址信息，还可新增"+(10-num)+"条");
								});
					}else{
						alert("操作失败，请联系管理员！");
					}
					$("#addAddress").attr('disabled',false);
					$("#addAddress").css({"background-color":"#FF7200"});
				});
				
		
	});
});
function updateAddress(tag) {
	
	$("#title_new").html("修改地址信息");
	var id = $("#id_"+tag).html();
	var recieverName = $("#recieverName_"+tag).html();
	var phoneNum = $("#phoneNum_"+tag).html();
	var addressmore = $("#address1_"+tag).html();
	var zipCode = $("#zipCode_"+tag).html();
	var level1 = $("#province_"+tag).html().substr(0,2);
	var level2 = $("#city_"+tag).html().substr(0,4);
	var level3 = $("#district_"+tag).html();

	var address = threeLevelName(level1,level2,level3);
	$("#recieverName").val(recieverName);
	$("#phoneNum").val(phoneNum);
	$("#zipCode").val(zipCode);
	$("#addressmore").val(addressmore);
	$("#addressLevel .addr em").html(address);
	$("#provinceCode").val(level1);
	$("#cityCode").val(level2);
	$("#countyCode").val(level3);
	$("#addressId").val(id);
};

function threeLevelName(level1,level2,level3){
	var address = "";
	//查询省
	for ( var i = 0; i < postCodeJson["00"].length; i++) {
		var that = postCodeJson["00"][i];
		if (that.hasOwnProperty(level1)) {
			address = address + " " +that[level1];
			break;
		}
	}
	//查询市
	for ( var i = 0; i < postCodeJson[level1].length; i++) {
		var that = postCodeJson[level1][i];
		if (that.hasOwnProperty(level2)) {
			address = address + " " +that[level2];
			break;
		}
	}
	//查询县/区
	for ( var i = 0; i < postCodeJson[level2].length; i++) {
		var that = postCodeJson[level2][i];
		if (that.hasOwnProperty(level3)) {
			address = address + " " +that[level3];
			break;
		}
	}
	return address;
}

function useAddress(tag){
	
	var id = $("#id_"+tag).html();
	var recieverName = $("#recieverName_"+tag).html();
	var phoneNum = $("#phoneNum_"+tag).html();
	var addressmore = $("#address1_"+tag).html();
	var zipCode = $("#zipCode_"+tag).html();
	var level1 = $("#province_"+tag).html().substr(0,2);
	var level2 = $("#city_"+tag).html().substr(0,4);
	var level3 = $("#district_"+tag).html();

	var address = threeLevelName(level1,level2,level3);
	
	//pagetype=1为开卡，2为开票
	if(pagetype==1){
		window.opener.document.getElementById("addressId").innerHTML=id; 
		window.opener.document.getElementById("text1").innerHTML=recieverName+" "+phoneNum; 
		window.opener.document.getElementById("text2").innerHTML=address; 
		window.opener.document.getElementById("text3").innerHTML=addressmore; 
		window.opener.document.getElementById("phoneNum").innerHTML=phoneNum; 
		window.opener.document.getElementById("zipCode").innerHTML=zipCode; 
		window.opener.document.getElementById("addressProvince").innerHTML=level1;
		window.opener.document.getElementById("addressCity").innerHTML=level2; 
		window.opener.document.getElementById("addressCounty").innerHTML=level3; 
		window.opener.document.getElementById("addressMore").innerHTML=addressmore; 
		window.opener.document.getElementById("receiverName").innerHTML=recieverName; 
	}else if(pagetype==2){
		
		window.opener.document.getElementById("recAddressId").innerHTML=id; 
		window.opener.document.getElementById("receiveName").innerHTML=recieverName; 
		window.opener.document.getElementById("receiveMobi").innerHTML=phoneNum; 
		window.opener.document.getElementById("receivePCD").innerHTML=address + " " +addressmore; 
//		window.opener.document.getElementById("receiveAddress").innerHTML=addressmore; 
		window.opener.document.getElementById("receiveZipCode").innerHTML=zipCode; 
		window.opener.document.getElementById("recName").value=recieverName; 
		window.opener.document.getElementById("recMobi").value=phoneNum; 
		window.opener.document.getElementById("recProvince").value=level1; 
		window.opener.document.getElementById("recCity").value=level2; 
		window.opener.document.getElementById("recDistrict").value=zipCode; 
		window.opener.document.getElementById("recAddress").value=addressmore; 
		window.opener.document.getElementById("recZipCode").value=zipCode; 
	}
		
	midifyAdress(id);
}

/**
 * 修改使用常用地址
 */
function midifyAdress(id){
	
	$.ajax({
		 url: '../../recieveraddress/modifiedRecieverAddressInfoOften.action',
        data: {"id":id},
		dataType : 'JSON'
	}).done(function(data){
		
		if(data.dataObject!=null&&data.dataObject!=2){
			window.close();
		}else{
			alert("设置常用地址失败！");
		}
	});
}

function deleteAddress(id){
	window.confirm("确认要删除？",
	function deletefunction(){
		
		$.ajax({
			 url: '../../recieveraddress/deleteAddress.action',
	         data: {"id":id},
			dataType : 'JSON'
		}).done(function(data){
	        	 if(data.dataObject!=null&&data.dataObject!=2){
						alert("删除成功!");
						$.ajax({
							url : ajaxMyAddressUrl,
							data : null,
							dataType : 'JSON'
						}).done(
								function(data) {
									num = 0;
									if (data && data != null && data.data && data.data != null) {
										var html = "";
										num = data.data.length;
										for ( var i = 0; i < data.data.length; i++) {
											if (i / 2 == 0) {
												html += "<tr>";
											} else {
												html += "<tr class=\"table_bg\">";
											}
											html += "<td id='recieverName_"+i+"' class=\"first_td\">"+ data.data[i].recieverName + "</td>";
											html += "<td id='phoneNum_"+i+"'>" + data.data[i].phoneNum + "</td>";
											if(data.data[i].province){
												html += "<td id='address_"+i+"' width='400px;'>" + threeLevelName(data.data[i].province.substr(0,2),data.data[i].city.substr(0,4),data.data[i].district)+"-"+data.data[i].address + "</td>";
											}else{
												html += "<td width='400px;'>"+data.data[i].address + "</td>";
											}
											html += "<td id='id_"+i+"' style='display:none;'>" + data.data[i].id + "</td>";
											html += "<td id='province_"+i+"' style='display:none;'>" + data.data[i].province + "</td>";
											html += "<td id='city_"+i+"' style='display:none;'>" + data.data[i].city + "</td>";
											html += "<td id='district_"+i+"' style='display:none;'>" + data.data[i].district + "</td>";
											html += "<td id='zipCode_"+i+"' style='display:none;'>" + data.data[i].zipCode + "</td>";
											html += "<td id='address1_"+i+"' style='display:none;'>" + data.data[i].address + "</td>";
											if(data.data[i].isOften==0){
												html += "<td><a class=\"color_sizeD useAddress\" id='useAddress_"+i+"' ></a></td>";
											}else{
												html += "<td><a class=\"color_sizeD useAddress\" id='useAddress_"+i+"' href='javascript:useAddress(\""+i+"\");'>使用</a></td>";
											}
											html += "<td><a class=\"color_sizeD updateAddress\" id='updateAddress_'"+i+"' href='javascript:updateAddress(\""+i+"\");'>修改</a></td>";
											if(data.data[i].isOften==0){
												html += "<td><a class=\"color_sizeD \" ></a></td>";
											}else{
												html += "<td><a class=\"color_sizeD \"  href='javascript:deleteAddress(\""+data.data[i].id+"\");'>删除</a></td>";
											}
											
											html += "</tr>";
										}
										$("#addressList").html(html);
										$("#addressId").val("");
										$("#recieverName").val("");
										$("#phoneNum").val("");
										$("#addressmore").val("");
										$("#zipCode").val("");
										$(".addrselector .addr em").html("请选择");
										$("#title_new").html("新增地址信息");
									}
									$("#addressTitle").html("已保存"+num+"条地址信息，还可新增"+(10-num)+"条");
								});
					}else{
						alert("删除失败，请联系管理员！");
					}
	         }	
		
		);
	} );
}

function reallyLength(str){
	return str.replace(/[\u0391-\uFFE5]/g,"aa").length;
}