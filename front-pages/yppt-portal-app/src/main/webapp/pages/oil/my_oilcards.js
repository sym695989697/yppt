/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */

//卡查询使用
var queryParam = []; //查询条件
var rows = 10;
var page = 0;
var moreflag = 0;   //1选择更多 0 查询
var selectUrl = '../../oilcard/queryCardList.action';
var ajaxCardRechargerUrl = '../../recharge/oilCardRechargeDataTransmit.action';
//卡申请使用
var queryParamApply = []; //查询条件
var rowsApply = 10;
var pageApply = 0;
var moreflagApply = 0;   //1选择更多 0 查询
var selectUrlApply = '../../oilcard/queryCardListApply.action';

//修改卡信息使用
var updateOidUrl = '../../oilcard/updateCard.action';
(function() {
	var MyOilcards = function() {
		this.init();
	};
	MyOilcards.prototype = {
		config : {
		},
		cache : {
			parms : {}
		},
		init : function() {
			this.loadGird();
		},
		loadGird : function() {
			//加载卡列表
			page = 1;
			rows = 10;
			getListOilCard();
			//加载卡申请列表
			pageApply = 1;
			rowsApply = 10;
			getListOilCardApply();
		}
	};
	$(document).ready(function() {
		window.myOilcards = new MyOilcards();
		
		$("#selectclick").click(function(){
			moreflag = 0;
			$("#cardList").html("");
			page = 1;
			rows = 10;
			getListOilCard();
		});
		$("#showmore").click(function(){
			moreflag = 1;
			$("#cardadd").remove();
			rows = 10;
			page = page+1;
			getListOilCard();
		});
		
		$("#selectapply").click(function(){
			moreflagApply = 0;
			$("#cardListApply").html("");
			pageApply = 1;
			rowsApply = 10;
			getListOilCardApply();
		});
		$("#showmoreapply").click(function(){
			moreflagApply = 1;
			$("#applyadd").remove();
			rowsApply = 10;
			pageApply = pageApply+1;
			getListOilCardApply();
		});
		
	});

})();

function getListOilCard(){
	setParam();
	searchOilCard();
}
function getListOilCardApply(){
	setParamApply();
	searchOilCardApply();
}
function setParam(){
	// 设置查询条件
	queryParam = [];
	queryParam.push({name:'requestParam.page',value:page});
	queryParam.push({name:'requestParam.rows',value:rows});
	if($("#selecttext").val() && $("#selecttext").val() != null && $("#selecttext").val().trim() != ""){
		queryParam.push({name:'requestParam.equal.cardNumber',value:encodeURIComponent($("#selecttext").val())});
	}
}
function setParamApply(){
	// 设置查询条件
	queryParamApply = [];
	queryParamApply.push({name:'requestParam.page',value:pageApply});
	queryParamApply.push({name:'requestParam.rows',value:rowsApply});
	if($("#selectapplytext").val() && $("#selectapplytext").val() != null && $("#selectapplytext").val().trim() != ""){
		queryParamApply.push({name:'requestParam.equal.cardNumber',value:encodeURIComponent($("#selectapplytext").val())});
	}
}
function searchOilCard() {
	$.ajax({
		url : selectUrl,
		data : queryParam,
		dataType : 'JSON',
		headers : {
			'grid' : 1
		}
	}).done(
			function(data) {
				data.totalPage = parseInt((data.total + data.pageSize - 1)
						/ data.pageSize);
				
				var html = "";
				var k = 0;
				var dataLength = 0;
				if(data.data!=null){
					dataLength = data.data.length;
				}
				//如果结果集数量大于0则需要动态拼接html
				if(dataLength>0){
					for(var i = 1 ; i<dataLength+1; i++){
						if(i%2!=0){  //对2求余，如果为0则表示为当前行的第二个
							if(i<=2){ 
								if(moreflag == 1){
									html += "<div class=\"mybox1\" style=\"margin-top:15px;\">";
								}else{
									html += "<div class=\"mybox1\">";
								}
							}else if(i>2&&i<=4){
								html += "<div class=\"mybox1\" style=\"margin-top:10px;\">";
							}else{
								html += "<div class=\"mybox1\" style=\"margin-top:15px;\">";
							}
						}else{//不为0表示为当前行的第一个
							if(i<=2){
								if(moreflag == 1){
									html += "<div class=\"mybox1 mybox2 ml15\" style=\"margin-top:15px;\">";
								}else{
									html += "<div class=\"mybox1 mybox2 ml15\">";
								}
							}else if(i>2&&i<=4){
								html += "<div class=\"mybox1 mybox2 ml15\" style=\"margin-top:10px;\">";
							}else{
								html += "<div class=\"mybox1 mybox2 ml15\" style=\"margin-top:15px;\">";
							}
						}
						   //拼接内容div
						
						if(data.data[i-1].cardType=="CARD-TYPE-01"){
							html += "<div class=\"box-tit\" >";
							html += "<h3 class='zsy')'>车旺加油卡（中石油）</h3>";
						}else if(data.data[i-1].cardType=="CARD-TYPE-02"){
							html += "<div class=\"box-tit\" >";
							html += "<h3 class='zsh')'>车旺加油卡（中石化）</h3>";
						}
						
							/*html += "<div class=\"box-tit\">";
							html += "<h3>车旺加油卡（中石化）</h3>";*/
							html += "</div>";
							html += "<ul>";
							if(data.data[i-1].cardNumber!=""&&data.data[i-1].cardNumber!="null"&&data.data[i-1].cardNumber!=null){
								html += "<li><span class=\"db w60\">卡&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span class=\"db w120\">"+data.data[i-1].cardNumber+"</span></li>";
							}else{
								html += "<li><span class=\"db w60\">卡&nbsp;&nbsp;&nbsp;&nbsp;号：</span><span class=\"db w120\"></span></li>";
							}
								html += "<li><span class=\"db w60\">卡 余 额：</span><span class=\"db w90\">￥"+data.data[i-1].balance+"</span><span class=\"db w30\" id='mybox'><a href='javascript:void(0);'><div style='position:relative'><img src='../../images/oil/icon4.png'><div>充值或加油后，最新余额在一定时间后更新</div></div></a></span><span class=\"db w50\"><a href='javascript:rechargeCard(\""+data.data[i-1].id+"\",\""+data.data[i-1].cardNumber+"\",\""+data.data[i-1].vehicleNo+"\",\""+data.data[i-1].balance+"\");'>充值</a></span></li>";

							if(data.data[i-1].vehicleNo!=""&&data.data[i-1].vehicleNo!="null"&&data.data[i-1].vehicleNo!=null){
								html += "<li><span class=\"db w60\">绑定车辆：</span><span class=\"db w120\"><p id='carNoText_"+i+"'>"+data.data[i-1].vehicleNo+"</p><input id='carNoInput_"+i+"' type='text' value='"+data.data[i-1].vehicleNo+"' style='width:80px;display:none; margin-top: 6px;'/></span><span class=\"db w30 updateCarNo\" id='car_"+i+"_"+data.data[i-1].id+"'>修改</span><span class=\"db w30 exitCarNo\" id='carexit_"+i+"' style='display:none;'>取消</span></li>";
							}else{
								html += "<li><span class=\"db w60\">绑定车辆：</span><span class=\"db w120\"><p id='carNoText_"+i+"'></p><input id='carNoInput_"+i+"' type='text' value='' style='width:80px;display:none; margin-top: 6px;'/></span><span class=\"db w30 updateCarNo\" id='car_"+i+"_"+data.data[i-1].id+"'>修改</span><span class=\"db w30 exitCarNo\" id='carexit_"+i+"' style='display:none;'>取消</span></li>";
							}
							if(data.data[i-1].telephoneNumber!=""&&data.data[i-1].telephoneNumber!="null"&&data.data[i-1].telephoneNumber!=null){
								html += "<li><span class=\"db w60\">绑定手机：</span><span class=\"db w120\"><p id='phoneText_"+i+"'>"+data.data[i-1].telephoneNumber+"</p><input id='phoneInput_"+i+"' type='text' value='"+data.data[i-1].telephoneNumber+"' style='width:80px;display:none; margin-top: 6px;'/></span><span class=\"db w30 updatePhoneNo\" id='phone_"+i+"_"+data.data[i-1].id+"'>修改</span><span class=\"db w30 exitPhoneNo\" id='phoneexit_"+i+"' style='display:none;'>取消</span></li>";
							}else{
								html += "<li><span class=\"db w60\">绑定手机：</span><span class=\"db w120\"><p id='phoneText_"+i+"'></p><input id='phoneInput_"+i+"' type='text' value='' style='width:80px;display:none; margin-top: 6px;'/></span><span class=\"db w30 updatePhoneNo\" id='phone_"+i+"_"+data.data[i-1].id+"'>修改</span><span class=\"db w30 exitPhoneNo\" id='phoneexit_"+i+"' style='display:none;'>取消</span></li></li>";
							}
							html += "<li><span class=\"db w60\">累计充值：</span><span class=\"db w120\">￥"+data.data[i-1].nowMonthRecharge+"</span><span class=\"db w50\"><a href='javascript:oldTrade(\""+data.data[i-1].cardNumber+"\");'>历史交易</a></span></li>";
							html += "<li><span class=\"db w60\">累计消费：</span><span class=\"db w120\">￥"+data.data[i-1].nowMonthRecharge+"</span></li>";
							html += "</ul>";
							html += "</div>";
							//如果为1表示添加油卡需要在小贴士的前面浮动
	                    if(dataLength==1){
	                    	html += " <div class=\"addcard\" id='cardadd' style=\"margin-left:15px;\">";
	        				html += " <a href=\"../card/card_apply.jsp\"><span class=\"font48 plus\">+</span> ";
	        				html += " <span class=\"font20\">添加油卡</span></a> ";
	        				html += " </div> ";
						}
	                    if(page==1){ //第一页时
	                    	if(i==2||dataLength==1){ //第二个元素拼接完或者数量为1时拼接小贴士
		                    	html += " <div class=\"mytips\"> ";
		                    	html += " <div class=\"tip-cont\"> ";
		                    	html += " <h3>小贴士</h3> ";
		                    	html += " <ul> ";
		                    	html += " <li>累计充值只统计本月</li> ";
		                    	html += " <li>累计消费只统计上月</li> ";
		                    	html += " </ul> ";
		                    	html += " </div> ";
		                    	html += " <img src=\"../../images/oil/cow.png\"> ";
		                    	html += " </div> ";
		                    }
	                    }
	                    
					   }
					  if(dataLength!=1){
						  if(dataLength%2!=0){
							  html += " <div class=\"addcard\" id='cardadd' style=\"margin-left:15px;margin-top:10px;\"> ";
						  }else{
							  html += " <div class=\"addcard\" id='cardadd' style=\"margin-top:10px;\"> ";
						  }
		      				html += " <a href=\"../card/card_apply.jsp\"><span class=\"font48 plus\">+</span> ";
		      				html += " <span class=\"font20\">添加油卡</span></a> ";
		      				html += " </div> ";
					  } 
				}else{//结果集为空则拼接固定的添加油卡和小贴士
					html += " <div class=\"addcard\" id='cardadd'>";
    				html += " <a href=\"../card/card_apply.jsp\"><span class=\"font48 plus\">+</span> ";
    				html += " <span class=\"font20\">添加油卡</span></a> ";
    				html += " </div> ";
    				if(page==1){
    					html += " <div class=\"mytips\" style=\"margin-left:315px;\"> ";
                    	html += " <div class=\"tip-cont\"> ";
                    	html += " <h3>小贴士</h3> ";
                    	html += " <ul> ";
                    	html += " <li>累计充值只统计本月</li> ";
                    	html += " <li>累计消费只统计上月</li> ";
                    	html += " </ul> ";
                    	html += " </div> ";
                    	html += " <img src=\"../../images/oil/cow.png\"> ";
                    	html += " </div> ";
    				}
				}
			    $("#cardList").append(html);
			    if(data.total>(data.start+9)){
			    	$("#showframe").show();
			    }else{
			    	$("#showframe").hide();
			    }
			    
			    $(".updateCarNo").click(function(){
			    	
			    	var that = $(this);
			    	var tag = $(this).attr("id").split("_")[1];
			    	var id = $(this).attr("id").split("_")[2];
			    	if($(this).html()=="修改"){
			    		$("#carNoText_"+tag).hide();
				    	$("#carNoInput_"+tag).show();
				    	$(this).html("确定");
				    	$("#carexit_"+tag).show();
			    	}else{
			    		if(!SimpleValidator.LicenseNumber($("#carNoInput_"+tag).val())){
			    			alert("车牌号码格式不正确！");
			    			return false;
			    		}
				    	var paramUpdate = {};
				    	paramUpdate["id"] = id;
				    	paramUpdate["vehicleNo"] = encodeURIComponent($("#carNoInput_"+tag).val()); 
				    	$.ajax({
				    		url : updateOidUrl,
				    		data :paramUpdate ,
				    		dataType : "JSON"
				    	}).done(function(data){
			    			if(data.dataObject!=null&&data.dataObject!=2){
			    				alert("修改成功！");
			    				$("#carNoText_"+tag).html($("#carNoInput_"+tag).val());
			    				$("#carNoText_"+tag).show();
						    	$("#carNoInput_"+tag).hide();
						    	$("#carexit_"+tag).hide();
						    	that.html("修改");
			    			}else{
			    				alert("修改失败！");
			    			}
			    		});
			    	}
			    });
			    $(".updatePhoneNo").click(function(){
			    	
			    	var that = $(this);
			    	var tag = $(this).attr("id").split("_")[1];
			    	var id = $(this).attr("id").split("_")[2];
			    	if($(this).html()=="修改"){
			    		$("#phoneText_"+tag).hide();
				    	$("#phoneInput_"+tag).show();
				    	$(this).html("确定");
				    	$("#phoneexit_"+tag).show();
			    	}else{
			    		if(!SimpleValidator.phone($("#phoneInput_"+tag).val())){
							alert("手机号格式不正确！");
							return false;
						}
			    		var paramUpdate = {};
				    	paramUpdate["id"] = id;
				    	paramUpdate["telephoneNumber"] = $("#phoneInput_"+tag).val();
				    	$.ajax({
				    		url : updateOidUrl,
				    		data :paramUpdate ,
				    		dataType : "JSON"
				    	}).done(function(data){
			    			if(data.dataObject!=null&&data.dataObject!=2){
			    				alert("修改成功！");
			    				$("#phoneText_"+tag).html($("#phoneInput_"+tag).val());
			    				$("#phoneText_"+tag).show();
						    	$("#phoneInput_"+tag).hide();
						    	$("#phoneexit_"+tag).hide();
						    	that.html("修改");
			    			}else{
			    				alert("修改失败！");
			    			}
			    		});
			    	}
			    });
			    
			    $(".exitCarNo").click(function(){
			    	var that = $(this);
			    	var tag = $(this).attr("id").split("_")[1];
			    	$(this).hide();
			    	$("#carNoText_"+tag).show();
			    	$("#carNoInput_"+tag).hide();
			    	$(this).prev().html("修改");
			    });
			    $(".exitPhoneNo").click(function(){
			    	var that = $(this);
			    	var tag = $(this).attr("id").split("_")[1];
			    	$(this).hide();
			    	$("#phoneText_"+tag).show();
			    	$("#phoneInput_"+tag).hide();
			    	$(this).prev().html("修改");
			    });
			});	
}

function searchOilCardApply() {
	$.ajax({
		url : selectUrlApply,
		data : queryParamApply,
		dataType : 'JSON',
		headers : {
			'grid' : 1
		}
	}).done(
			function(data) {
				data.totalPage = parseInt((data.total + data.pageSize - 1)
						/ data.pageSize);
				
				var html = "";
				var k = 0;
				var dataLength = 0;
				if(data.data!=null){
					dataLength = data.data.length;
				}
				//如果结果集数量大于0则需要动态拼接html
				if(dataLength>0){
					for(var i = 1 ; i<dataLength+1; i++){
						if(i%2!=0){  //对2求余，如果为0则表示为当前行的第二个
							if(i<=2){ 
								if(moreflagApply == 1){
									html += "<div class=\"mybox1\" style=\"margin-top:15px;\">";
								}else{
									html += "<div class=\"mybox1\">";
								}
							}else if(i>2&&i<=4){
								html += "<div class=\"mybox1\" style=\"margin-top:10px;\">";
							}else{
								html += "<div class=\"mybox1\" style=\"margin-top:15px;\">";
							}
						}else{//不为0表示为当前行的第一个
							if(i<=2){
								if(moreflagApply == 1){
									html += "<div class=\"mybox1 mybox2 ml15\" style=\"margin-top:15px;\">";
								}else{
									html += "<div class=\"mybox1 mybox2 ml15\">";
								}
							}else if(i>2&&i<=4){
								html += "<div class=\"mybox1 mybox2 ml15\" style=\"margin-top:10px;\">";
							}else{
								html += "<div class=\"mybox1 mybox2 ml15\" style=\"margin-top:15px;\">";
							}
						}
						
						
						   //拼接内容div
						if(data.data[i-1].cardType=="CARD-TYPE-01"){
							html += "<div class=\"box-tit\" >";
							html += "<h3 class='zsy')'>车旺加油卡（中石油）</h3>";
						}else if(data.data[i-1].cardType=="CARD-TYPE-02"){
							html += "<div class=\"box-tit\" >";
							html += "<h3 class='zsh')'>车旺加油卡（中石化）</h3>";
						}else{
							html += "<div class=\"box-tit\" >";
							html += "<h3 class='zsy')'>车旺加油卡（中石油）</h3>";
						}
						
						
							/*html += "<div class=\"box-tit\">";
							html += "<h3>车旺加油卡（中石化）</h3>";*/
						if(data.data[i-1].state=="10"){
							html += "<span>审核失败</span>";
						}else{
							html += "<span>办理中</span>";
						}
							html += "</div>";
							html += "<ul>";
							
							html +=" <li><span class=\"db w60\">提交时间：</span><span class=\"db w120\">"+getSmpFormatDateByLong(data.data[i-1].createTime,true)+"</span></li> ";
							if(data.data[i-1].vehicleNo){
								html +=" <li><span class=\"db w60\">绑定车辆：</span><span class=\"db w120\">"+data.data[i-1].vehicleNo+"</span></li> ";
							}else{
								html +=" <li><span class=\"db w60\">绑定车辆：</span><span class=\"db w120\"></span></li> ";
							}
							if(data.data[i-1].phoneNum){
								html +=" <li><span class=\"db w60\">绑定手机：</span><span class=\"db w120\">"+data.data[i-1].phoneNum+"</span></li> ";
							}else{
								html +=" <li><span class=\"db w60\">绑定手机：</span><span class=\"db w120\"></span></li> ";
							}
							html +=" <li><span class=\"db w60\"><a href=\"my_oilcards_detail.jsp?cardApplyDetailId="+data.data[i-1].id+"\">查看详情</a></span></li> ";
							html += "</ul>";
							html += "</div>";
							//如果为1表示添加油卡需要在小贴士的前面浮动
	                    if(dataLength==1){
	                    	html += " <div class=\"addcard\" id='applyadd' style=\"margin-left:15px;\">";
	        				html += " <a href=\"../card/card_apply.jsp\"><span class=\"font48 plus\">+</span> ";
	        				html += " <span class=\"font20\">添加油卡</span></a> ";
	        				html += " </div> ";
						}
	                    if(pageApply==1){ //第一页时
	                    	if(i==2||dataLength==1){ //第二个元素拼接完或者数量为1时拼接小贴士
	                    	html += " <div class=\"mytips\"> ";
	                    	html += " <div class=\"tip-cont tip-cont1\"> ";
	                    	html += " <h3>小贴士</h3> ";
	                    	html += " <ul> ";
	                    	html += " <li>如果长时间未收到卡片<br>可拨打客服电话：</li> ";
	                    	html += " <li><span class=\"orange\">4000966666</span> 进行咨询</li> ";
	                    	html += " </ul> ";
	                    	html += " </div> ";
	                    	html += " <div class=\"cow\"><img src=\"../../images/oil/cow.png\"></div> ";
	                    	html += " </div> ";
		                    }
	                    }
	                    
					   }
					  if(dataLength!=1){
						  if(dataLength%2!=0){
							  html += " <div class=\"addcard\" id='applyadd' style=\"margin-left:15px;margin-top:10px;\"> ";
						  }else{
							  html += " <div class=\"addcard\" id='applyadd' style=\"margin-top:10px;\"> ";
						  }
		      				html += " <a href=\"../card/card_apply.jsp\"><span class=\"font48 plus\">+</span> ";
		      				html += " <span class=\"font20\">添加油卡</span></a> ";
		      				html += " </div> ";
					  } 
				}else{//结果集为空则拼接固定的添加油卡和小贴士
					html += " <div class=\"addcard\" id='applyadd'>";
    				html += " <a href=\"../card/card_apply.jsp\"><span class=\"font48 plus\">+</span> ";
    				html += " <span class=\"font20\">添加油卡</span></a> ";
    				html += " </div> ";
    				if(pageApply==1){
    					html += " <div class=\"mytips\" style=\"margin-left:315px;\"> ";
                    	html += " <div class=\"tip-cont tip-cont1\"> ";
                    	html += " <h3>小贴士</h3> ";
                    	html += " <ul> ";
                    	html += " <li>如果长时间未收到卡片<br>可拨打客服电话：</li> ";
                    	html += " <li><span class=\"orange\">4000966666</span> 进行咨询</li> ";
                    	html += " </ul> ";
                    	html += " </div> ";
                    	html += " <div class=\"cow\"><img src=\"../../images/oil/cow.png\"></div> ";
                    	html += " </div> ";
    				}
				}
			    $("#cardListApply").append(html);
			    if(data.total>(data.start+9)){
			    	$("#showframeApply").show();
			    }else{
			    	$("#showframeApply").hide();
			    }
			});	
}

function rechargeCard(id,cardNumber,carNo,balance){
		var param={};
		param['cardRecharges[0].id'] = id;
		param['cardRecharges[0].cardNO'] = cardNumber;
		param['cardRecharges[0].carNO'] = carNo;
		param['cardRecharges[0].balance'] = balance;
		$.ajax({
			url : ajaxCardRechargerUrl,
			data : param,
			dataType : 'JSON',
			type : 'POST'
		}).done(function(data) {
			if(data && data.dataObject && data.dataObject == "0") {
				window.location.href = basePath + 'pages/card/card_recharge.jsp';
			} else {
				alert(data.message);
			}
		});
}
function oldTrade(cardNumber){
	window.location.href="oil_transaction.jsp?cardNumber="+cardNumber;
}
