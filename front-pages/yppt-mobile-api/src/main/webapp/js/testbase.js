/**
 * 基础js用来给测试页面调用
 */
(function() {
	var TestBase = function() {
	};
	TestBase.prototype = {
		config : {
		  tokenIdUrl : "http://myhost:8088/yppt-mobile-webapi/business/call.html?param=",
		  tokenId : ""
		},
		testUrl : function(){
			var that = this;
			var url = that.config.tokenIdUrl+$("#testurl").val();
			if(url==null||url==""){
				alert("请输入调用地址！");
			}else{
				$.ajax({
					 type: "POST",
		             url: url,
		             dataType: "json",
		             success: function(data){
		            	 var body = JSON.stringify(data);
		            	 $("#result").html(body);
		             }
				})
			}
		}
	};
	$(document).ready(function() {
		window.testBase = new TestBase();
	});
})();
