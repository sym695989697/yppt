/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */

var addressProArray = new Array();
var cardNum = 1;  //卡片数量---默认进来为1
var ajaxMyAddressUrl = '../../recieveraddress/queryStockAddress.action';
var oilTypeCode = CODE.getCodeData("IC-CARD-TYPE");
var offenUseText = "请选择";
$(function() {
	
	
	//清理缓存
	$("#provinceCode").val("");
	$("#provinceName").val("");
	//$("#isAgree").attr("checked",false);
	
	
	
	//油卡类型
	CODE.getSelectByCodeType('cardType','IC-CARD-TYPE');
	var headPicImg = {};
	// 图片上传
	loadScript("/js/ctfo_fileUpload.js", function() {
		// 上传头像图片
		headPicImg = new CtfoFileUpload({
			containerId : "payPicId_file1",
			isAutoRender : true,
			fileName : "payImg1"
		});
		headPicImg.init();
	});
		$("#oftenUseAddress").addrDropmenu({ "level" : 1 });  //二级联动
		
		$(".addrselector .addr em").css({"color":"black"});
		
		$("#changeAddress").click(function(){
			window.open("../oil/oil_address_info_main.jsp?pagetype=1");
		});
		
		//给常用地址赋值
		$.ajax({
			url : ajaxMyAddressUrl,
			data : null,
			dataType : 'JSON'
		}).done(
				function(data) {
					if (data && data != null && data.data && data.data != null) {
						var html = "";
						for ( var i = 0; i < 1; i++) {
							$("#addressId").html(data.data[i].id);
							$("#addressProvince").html(data.data[i].province);
							$("#addressCity").html(data.data[i].city);
							$("#addressCounty").html(data.data[i].district);
							$("#addressMore").html(data.data[i].address);
							$("#phoneNum").html(data.data[i].phoneNum);
							$("#zipCode").html(data.data[i].zipCode);
							$("#receiverName").html(data.data[i].recieverName);
							$("#text1").html(data.data[i].recieverName+" "+data.data[i].phoneNum);
							$("#text2").html(threeLevelName(data.data[i].province.substr(0,2),data.data[i].city.substr(0,4),data.data[i].district));
							$("#text3").html(data.data[i].address+" "+data.data[i].zipCode);
						}
	               	}else{
	               		$("#changeAddress").val(" 新增地址");
	               	}
				});
		
		$("#isAgree").click(function(){
			if(($("input[type='checkbox']").attr("checked")=="checked")){
					$("#openCard").attr('disabled',false);
					$("#openCard").css({"background-color":"#FF7200"});	
			}else{
				 $("#openCard").attr('disabled',true);
					$("#openCard").css({"background-color":"#CDC0B0"});
			}
		});
		
		$("#openCard").click(function(){
			
			if($(".carddiv").length==0){
				alert("申请开通的卡片数量不能为空！");
				return false;
			}
			if($(".carddiv").length>10){
				alert("申请开通的卡片数量不能超过10张！");
				return false;
			}
			
			var oidType = $("#selectOilType #cardType option:selected").val();
			//从常用收件地址中获取
			var oftenArea = "";
		    if(addressProArray.length==0){
				alert("常用地区不能为空");
				return false;
			}
			for(var ofteni = 0 ; ofteni < addressProArray.length ; ofteni++){
				oftenArea += addressProArray[ofteni]+"0000,";
			}
			var name = $("#name").val();
			var presonN = $("#presonN").val();
			//参数校验
			if(!checkDataValid()){
				return false;
			}
			
			
			var param ={};
			param["iCCardApply.cardType"]=oidType;
			param["iCCardApply.receiverAreaId"]=$("#addressId").html();
			param["iCCardApply.oftenArea"]=oftenArea;
			param["iCCardApply.receiverName"]=$("#receiverName").html();
			param["iCCardApply.applyUserName"]=name;
			param["iCCardApply.idNo"]=presonN;
			param["iCCardApply.receiverPhoneNum"]=$("#phoneNum").html();
			param["iCCardApply.zipCode"]=$("#zipCode").html();
			param["iCCardApply.province"]=$("#addressProvince").html();
			param["iCCardApply.city"]=$("#addressCity").html();
			param["iCCardApply.district"]=$("#addressCounty").html();
			param["iCCardApply.address"]=$("#addressMore").html();
			param["iCCardApply.cardNum"]=$(".carddiv").length;
            var ii= 0;
            
            var picflag = 0; //校验行驶证图片 1为有一个为空
            $(".carddiv").each(function(){
            	var tag = $(this).attr("id").substr(6,$(this).attr("id").length);
				param["iCCardApplyDetail["+ii+"].vehicleNo"]=$("#carNumber"+tag).val();
				param["iCCardApplyDetail["+ii+"].acceptMessage"]=$("#isSend"+tag+" option:selected").val();
				param["iCCardApplyDetail["+ii+"].phoneNum"]=$("#telephone"+tag).val();
				if($("input[name=payImg"+tag+"]").val()==null||$("input[name=payImg"+tag+"]").val()==""){
					picflag = picflag+1;  
				}else{
					param["iCCardApplyDetail["+ii+"].vehicleLicense"]=($("input[name=payImg"+tag+"]").val()).substr(4,$("input[name=payImg"+tag+"]").val().length);
				}
				ii = ii+1;
              });
            
           //行驶证校验
            if(picflag>0){
            	alert("请确保所有卡片行驶证图片都已上传");
            	return false;
            }
            
            $("#openCard").attr('disabled',true);
    		$("#openCard").css({"background-color":"#CDC0B0"});
			$.ajax({
				type: "POST",
	             url: "../../oilcard/openCardApply.action",
	             data: param,
	             dataType: "json",
	             success: function(data){
	            	if(data.dataObject==2){
	            		alert("开卡申请失败请联系管理员!");
	            	}else{
	            		alert("开卡申请成功！");
	            		window.location.href="card_result.jsp?cardNum="+cardNum;
	            	}
	            	$("#openCard").attr('disabled',false);
					$("#openCard").css({"background-color":"#FF7200"});	
	             }	
			});
		});
		
		$(".selectDiv").each(function(){
			var divWidth = $(this).css("width");
			backwidth = divWidth.substr(0,divWidth.length-2)-40;
			$(this).children("select").css({"width":backwidth+60,"color":"black"});
		});
		
		
		//常用地区  start -------------------------------
        $(".dn").mouseleave(function(){
		        	var provinceCode = $("#provinceCode").val();
		        	var provinceName = $("#provinceName").val();
		        	var html = "";
		        	if(provinceCode!=null&&provinceName!=null&&
		        			provinceCode!=""&&provinceName!=""){
		                if(addressProArray.length==0){ //数组为空直接添加
		                	addressProArray[0] = provinceCode;
		                	html = "<li id='"+provinceCode+"_province'>"+provinceName+"<span><img id='"+provinceCode+"' style='cursor:pointer;' src=\"../../images/handle/close2.png\"></span></li>";
			        	}else{
			        				//先循环list判断市级code是否相同即可，不相同表明新增
			        		if(addressProArray.length<3){
			        			var isExitProflag = 0;
			        			for(var i = 0 ; i < addressProArray.length ; i++){
			        				if(provinceCode==addressProArray[i]){
			        					isExitProflag = 1;
			        					break;
			        				}
			        			}
			        			if(isExitProflag == 0){
			        				addressProArray[addressProArray.length] = provinceCode;
			                    	html = "<li id='"+provinceCode+"_province'>"+provinceName+"<span><img id='"+provinceCode+"' src=\"../../images/handle/close2.png\"></span></li>";
			        			}
			        		}
			        	}
		        	}
		        	if(html!=""&&html!=null){
		        		$("#oftenusebq").append(html);
		            	$("#"+provinceCode).click(function(){
		            		$("#"+provinceCode+"_province").remove();
		            		deleteArrayData(addressProArray,provinceCode);
		            		//清理缓存
		            		$("#provinceCode").val("");
		            		$("#provinceName").val("");
		            		if(addressProArray.length==0){
		            			 if($("#oftenUseAddress").next().is("span")){
		             	        	$("#oftenUseAddress").next().remove();
		             			}
		            			$("#area").hide();
		             			$("#oftenUseAddress").after("<span class=\"w160 notValid\" style=\"margin-left: 18px;margin-top:10px;color: red;\">请选择常用地区</span>");
		            		}
		            		
		        		});
		        	}
		        	
        });
        
      //常用地区  end -------------------------------
        
      //办卡基础信息绑定校验 start--------------------------
      $("#cardType").change(function(){
    	  var oilType = $("#selectOilType #cardType option:selected").val();
    	  if(oilType!=""&&oilType!="请选择"){
    		  $("#selectOilType").next().remove();
    		  var oilTypeDiscrible = "";
    		  for(var i = 0 ; i<oilTypeCode.length;i++){
    			  if(oilTypeCode[i].code==oilType){
    				  oilTypeDiscrible = oilTypeCode[i].description;
    			  }
    		  }
    		  $("#selectOilType").after("<span class=\"w160\" style=\"margin-left: 10px; width: 300px;\">"+oilTypeDiscrible+"</span>");
    	  }else{
    		  $("#selectOilType").next().remove();
    		  $("#selectOilType").after("<span class=\"w160 notValid\" style=\"margin-left: 18px; color: red;\">请选择油卡类型</span>");
    	  }
      });
      $(".dn").mouseleave(function(){
    	  if(addressProArray.length==0){
    		  if($("#oftenUseAddress").next().is("span")){
    	        	$("#oftenUseAddress").next().remove();
    			}
    			$("#oftenUseAddress").after("<span class=\"w160 notValid\" style=\"margin-left: 18px;margin-top:10px;color: red;\">请选择常用地区</span>");
    	  }else{
    			  if($("#oftenUseAddress").next().is("span")){
    	  	        	$("#oftenUseAddress").next().remove();
    	  			}
    	  }
      });
      $("#name").change(function(){
    	  var name = $("#name").val().trim();
    	  if(name==""||name==null){
    		  if($("#name").next().is("span")){
  	        	$("#name").next().remove();
	  			}
	  		  $("#name").after("<span class=\"w160 notValid\" style=\"margin-left: 10px;margin-top:10px;color: red;\">请输入姓名</span>");
    	  }else if(reallyLength(name)>50){
    		  $("#name").after("<span class=\"w160 notValid\" style=\"margin-left: 10px;margin-top:10px;color: red;\">姓名不能超过50个字符</span>"); 
			}else{
    		  if($("#name").next().is("span")){
    	        	$("#name").next().remove();
  	  			}
    	  }
    	 
      });
      $("#presonN").change(function(){
    	  var presonN = $("#presonN").val().trim();
    	  if(presonN==""||presonN==null){
    		  if($("#presonN").next().is("span")){
  	        	$("#presonN").next().remove();
	  			}
	  		  $("#presonN").after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入身份证号</span>");
    	  }else if(!SimpleValidator.cardNumber(presonN)){
    		  if($("#presonN").next().is("span")){
    	        	$("#presonN").next().remove();
  	  			}
    		  $("#presonN").after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">身份证号格式不正确</span>");
    	  }else{
    		  if($("#presonN").next().is("span")){
  	        	$("#presonN").next().remove();
	  			}
    	  }
    	 
      });
      
      $(".carNumber").change(function(){
          var carNumber = $(this).val().trim();
      	  if(carNumber==""||carNumber==null){
      		  if($(this).next().is("span")){
    	        	$(this).next().remove();
    	  			}
    	  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入车牌号</span>");
      	  }else if(!SimpleValidator.LicenseNumber(carNumber)){
	   	  	  if($(this).next().is("span")){
		        	$(this).next().remove();
		  			}
		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">车牌号格式不正确</span>");
 	  	  }else{
      		  if($(this).next().is("span")){
      	        	$(this).next().remove();
    	  			}
      	  }
        }); 
      
      $(".telephone").change(function(){
          var telephone = $(this).val().trim();
          if(telephone==""||telephone==null){
        	  $(this).next().remove();
    		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入手机号</span>");
    	  }else if(!SimpleValidator.phone(telephone)){
    		  if($(this).next().is("span")){
		        	$(this).next().remove();
		  			}
		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">手机号格式不正确</span>");
    	  }else{
    		  $(this).next().remove();
        	  $(this).after("<span class=\"w160\">此手机号用于交易短信提醒</span>");
    	  }
        }); 
      
    //办卡基础信息绑定校验 end--------------------------   
    //卡片关闭事件绑定 start----------------------------
      $(".closecard").click(function(){
    	  //先获取是第几个卡片
    	  var tag = $(this).attr("id").substr(0,1);
    	  //然后拼接卡片div的id并且删除
    	  $("#close_"+tag).remove();
    	  $("#numcardDiv").html("已经添加"+$(".carddiv").length+"张卡片");
      });
    //卡片关闭事件绑定 end------------------------------ 
    //卡片新增事件绑定 start----------------------------  
      $("#addcardDiv").click(function(){
    	  
    	  if($(".carddiv").length>0){
    		  cardNum = parseInt($(".carddiv").last().attr("id").split("_")[1]);
    	  }else{
    		  cardNum = 0;
    	  }
    	  var position = cardNum+1;
    	  
    	  if($(".carddiv").length>=10){
    		  alert("超过一次开卡最多张数！");
    		  return false;
    	  }
    	
		    	 
    	  var html = "";
    	  html += " <div class='carddiv card-box2' id='close_"+position+"'> ";
    	  html += " <div class='card-box2-tit'> ";
    	  html += " <ul class='close-pro'> ";
    	  html += " <li>卡片<img src='../../images/handle/close1.png' style='cursor: pointer;'><div>确定要删除吗？<br><a  id='"+position+"_closecard' class='closecard'>确定</a>  <a class='closeeixt'>取消</a></div></li> ";
    	  html += " </ul></div> ";
    	  html += " <div class='cb'></div> ";
    	  html += " <form> ";
    	  html += " <span class='tex-wid90'><font color='red'>*</font>车牌号：</span><input type='text' class='myinput carNumber' id='carNumber"+position+"'> ";
    	  html += " <div class='cb'></div> ";
    	  html += " <span class='tex-wid90'><font color='red'>*</font>行驶证：</span><div id='payPicId_file"+position+"' class='fl img_tops'></div>";
    	  html += " <div class='cb'></div> ";
    	  html += " <span class='tex-wid90'><font color='red'>*</font>绑定手机：</span><input type='text' class='myinput telephone' id='telephone"+position+"'><span class='w160'>此手机号用于交易短信提醒</span> ";
    	  html += " <div class='cb'></div> ";
    	  html += " <span class='tex-wid90'><font color='red'>*</font>短信提醒：</span> ";
    	  html += " <div class='selectDiv' style='position:relative;'> ";
    	  html += " <select id='isSend"+position+"'> ";
    	  html += " <option value='1'>开</option> ";
    	  html += " <option value='0'>关</option> ";
    	  html += " </select></div><div class='cb'></div> ";
    	  html += " </form></div> ";
    	  if(cardNum==0){
    		  $("#basic_box").after(html);
    	  }else{
    		  $(".carddiv").last().after(html);
    	  }
    	  $("#numcardDiv").html("已经添加"+$(".carddiv").length+"张卡片");
    	  //重新绑定卡片删除对话框展示
    	  $(".close-pro li img").unbind("click");
    	  $(".closeeixt").unbind("click");
    	  $(".close-pro li img").click(function(){
    		  $(this).parent().find("div").show();
    		 });
    	  $(".closeeixt").click(function(){
    			 $(this).parent().parent().find("div").hide();
    		 });
    	  //重新绑定卡片关闭事件
    	  $(".closecard").unbind("click");
    	  
    	  $(".closecard").live('click',function(){
        	  //先获取是第几个卡片
        	  var tag = $(this).attr("id").split("_")[0];
        	  //然后拼接卡片div的id并且删除
        	  $("#close_"+tag).remove();
        	  $("#numcardDiv").html("已经添加"+$(".carddiv").length+"张卡片");
          });
    	  //重新绑定下拉框样式
    	  $(".selectDiv").each(function(){
  			var divWidth = $(this).css("width");
  			backwidth = divWidth.substr(0,divWidth.length-2)-40;
  			$(this).children("select").css({"width":backwidth+60,"color":"#B4B4B4"});
  		});
			
    	  
    	  var headPicImg = {};
    		// 图片上传
    		loadScript("/js/ctfo_fileUpload.js", function() {
    			// 上传头像图片
    			headPicImg = new CtfoFileUpload({
    				containerId : "payPicId_file"+position,
    				isAutoRender : true,
    				fileName : "payImg"+position
    			});
    			headPicImg.init();
    		});
    		
    		//卡片内部字段校验start--------------------------
    	    $(".carNumber").change(function(){
    	      var carNumber = $(this).val().trim();
    	  	  if(carNumber==""||carNumber==null){
    	  		  if($(this).next().is("span")){
    		        	$(this).next().remove();
    		  			}
    		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入车牌号</span>");
    	  	  }else if(!SimpleValidator.LicenseNumber(carNumber)){
    	   	  	  if($(this).next().is("span")){
  		        	$(this).next().remove();
  		  			}
  		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">车牌号格式不正确</span>");
     	  	  }else{
    	  		  if($(this).next().is("span")){
    	  	        	$(this).next().remove();
    		  			}
    	  	  }
    	    }); 
    	    $(".telephone").change(function(){
    	          var telephone = $(this).val().trim();
    	          if(telephone==""||telephone==null){
    	        	  $(this).next().remove();
    	    		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入手机号</span>");
    	    	  }else{
    	    		  $(this).next().remove();
    	        	  $(this).after("<span class=\"w160\">此手机号用于交易短信提醒</span>");
    	    	  }
    	        }); 
    	      
    	    //卡片内部字段校验end----------------------------	
    	    $(".telephone").val(mobile);
      });  
    //卡片新增事件绑定 end----------------------------    
    
      $("#name").val(userName);
      if(mobile!=null&&mobile!=""&&mobile!="null"){
    	  $(".telephone").val(mobile);
      }
     if(idNo!=null&&idNo!=""&&idNo!="null"){
    	 $("#presonN").val(idNo);
     }
     
});

function deleteArrayData(array,code){
	var baseArray = new Array();
	for(var arrayi=0;arrayi<array.length;arrayi++){
		if(array[arrayi]==code){
			array.splice(arrayi,1);
		}
	}
	//重新整理数组
	for(var arrayy=0;arrayy<array.length;arrayy++){
		if(array[arrayy]!=null&&array[arrayy]!=""){
			baseArray[baseArray.length]=array[arrayy];
		}
	}
	array = baseArray;
	
}
//校验全部数据--select按钮使用
function checkDataValid(){
	
	if($(".notValid").length>0){
		alert("开卡申请还未填写完整");
		return false;
	}else{
		
		var flagii = 0;
		var oilType = $("#selectOilType #cardType option:selected").val();
		var name = $("#name").val();
		var carNumber = $("#carNumber").val();
		var telephone = $("#telephone").val();
		var isSend = $("#isSend option:selected").val();
		var presonN = $("#presonN").val();
		if(oilType==""||oilType=="请选择"){
			$("#selectOilType").next().remove();
			$("#selectOilType").after("<span class=\"w160\" style=\"margin-left: 18px; color: red;\">请选择油卡类型</span>");
			alert("开卡申请还未填写完整");
			return false;
		}
		if(addressProArray.length==0){
	        if($("#oftenUseAddress").next().is("span")){
	        	$("#oftenUseAddress").next().remove();
			}
			$("#oftenUseAddress").after("<span class=\"w160\" style=\"margin-left: 18px;margin-top:10px;color: red;\">请选择常用地区</span>");
			alert("开卡申请还未填写完整");
			return false;
		}
		if(name==""||name==null){
			 if($("#name").next().is("span")){
		        	$("#name").next().remove();
				}
			$("#name").after("<span class=\"w160\" style=\"margin-left: 10px;margin-top:10px;color: red;\">请输入姓名</span>");
			alert("开卡申请还未填写完整");
			return false;
		}else if(reallyLength(name)>50){
  		  $("#name").after("<span class=\"w160 notValid\" style=\"margin-left: 10px;margin-top:10px;color: red;\">姓名不能超过50个字符</span>"); 
		}
		if(presonN==""||presonN==null){
			 if($("#presonN").next().is("span")){
		        	$("#presonN").next().remove();
				}
			$("#presonN").after("<span class=\"w160\" style=\"margin-left: 10px;color: red;\">请输入身份证号</span>");
			alert("开卡申请还未填写完整");
			return false;
		}else if(!SimpleValidator.cardNumber(presonN)){
			 if($("#presonN").next().is("span")){
 	        	$("#presonN").next().remove();
	  			}
		  $("#presonN").after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">身份证号格式不正确</span>");
		  alert("开卡申请还未填写完整");
		  return false;
	  }
		
		 $(".carNumber").each(function(){
   	      var carNumber = $(this).val().trim();
   	  	  if(carNumber==""||carNumber==null){
   	  		  if($(this).next().is("span")){
   		        	$(this).next().remove();
   		  			}
   		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入车牌号</span>");
   		  		alert("开卡申请还未填写完整");
   		  	    flagii = flagii + 1;
   		  		return false;
   	  	  }else if(!SimpleValidator.LicenseNumber(carNumber)){
	   	  	  if($(this).next().is("span")){
		        	$(this).next().remove();
		  			}
		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">车牌号格式不正确</span>");
		  		alert("开卡申请还未填写完整");
		  	    flagii = flagii + 1;
		  		return false;
   	  	  }else{
   	  		  if($(this).next().is("span")){
   	  	        	$(this).next().remove();
   		  			}
   	  	  }
   	    }); 
   	    $(".telephone").each(function(){
   	          var telephone = $(this).val().trim();
   	          if(telephone==""||telephone==null){
   	        	  $(this).next().remove();
   	    		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">请输入手机号</span>");
   	    		  if(flagii==0){  //确保只有弹出一次为空提示
   	    			  alert("开卡申请还未填写完整");
   	    		  }
   		  		return false;
   	    	  }else if(!SimpleValidator.phone(telephone)){
   	    		  if($(this).next().is("span")){
  		        	$(this).next().remove();
  		  			}
  		  		  $(this).after("<span class=\"w160 notValid\" style=\"margin-left: 10px;color: red;\">手机号格式不正确</span>");
  		  		 if(flagii==0){  //确保只有弹出一次为空提示
  	    			  alert("开卡申请还未填写完整");
  	    		  }
  		  		return false;
      	    }else{
   	    		  $(this).next().remove();
   	        	  $(this).after("<span class=\"w160\">此手机号用于交易短信提醒</span>");
   	    	  }
   	        }); 
   	    
   	    if(flagii>0){
   	    	return false;
   	    }else{
   	    	return true;
   	    }
	}
			
}
//开卡头提示信息
function test(){
	var d=document.getElementById('close');
	d.parentNode.removeChild(d);
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
function reallyLength(str){
	return str.replace(/[\u0391-\uFFE5]/g,"aa").length;
}