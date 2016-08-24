var ajaxBillingInfoListQueryUrl = '../../billing/addBillingInfo.action';
var deleteUrl = '../../billing/deleteBillingInfoById.action';
var $queryBlock = $('.queryBlock');
var tplGrid = $('#tplGrid').html();
var $dataGrid = $('#dataGrid');

$(function() {
	
	var Billinginfo = function() {
		this.init();
	};
	
	Billinginfo.prototype = {
		config : {
		},
		cache : {
			parms : {}
		},
		init : function() {
			this.initMsg();
		},
		initMsg : function() {
			
			//发票类型
			CODE.getSelectByCodeType('billingType','IC-INVOICE-TYPE');
			//初始页面
			var dwyyzzPicImg = {};
			var nszzPicImg = {};
			var zzjgdmPicImg = {};
			var swdjPicImg = {};
			var kpfjPicImg = {};
			// 图片上传
			loadScript("/js/ctfo_fileUpload.js", function() {
				// 上传头像图片
				dwyyzzPicImg = new CtfoFileUpload({
					containerId : "dwyyzzPicId_file",
					isAutoRender : true,
					fileName : "dwyyzzImg"
				});
				dwyyzzPicImg.init();
				
				// 上传头像图片
				nszzPicImg = new CtfoFileUpload({
					containerId : "nszzPicId_file",
					isAutoRender : true,
					fileName : "nszzImg"
				});
				nszzPicImg.init();
				
				// 上传头像图片
				zzjgdmPicImg = new CtfoFileUpload({
					containerId : "zzjgdmPicId_file",
					isAutoRender : true,
					fileName : "zzjgdmImg"
				});
				zzjgdmPicImg.init();
				
				// 上传头像图片
				swdjPicImg = new CtfoFileUpload({
					containerId : "swdjPicId_file",
					isAutoRender : true,
					fileName : "swdjImg"
				});
				swdjPicImg.init();
				
				// 上传头像图片
				kpfjPicImg = new CtfoFileUpload({
					containerId : "kpfjPicId_file",
					isAutoRender : true,
					fileName : "kpfjImg"
				});
				kpfjPicImg.init();
			});
			$("#form").Validform({
				tiptype:2,	
				ajaxPost:true,
				url :"../../billing/addBillingInfo.action",
				 beforeSubmit : function() {
					var residue = $("#residue").text();
					if(residue=="0"){
						alert("发票信息已经超过10条,请检查！");
						return false;
					}
					
					var billingTitle = $("#billingTitle").val();
					if(reallyLength(billingTitle)>50){
						alert("发票抬头已经超过25个汉字,请检查！");
						return false;
					}
					
					var billingType = $("#billingType").val();
					if(billingType == "02"){
						if($('input[name=dwyyzzImg]').val() == null || $('input[name=dwyyzzImg]').val() == ""){
							alert("请上传单位营业执照！");
							return false;
						}
						if( $('input[name=nszzImg]').val() == null || $('input[name=nszzImg]').val() == ""){
							alert("请上传一般纳税人资格证！");
							return false;
						}
						if($('input[name=zzjgdmImg]').val() == null || $('input[name=zzjgdmImg]').val() == ""){
							alert("请上传组织机构代码证！");
							return false;
						}
						if($('input[name=swdjImg]').val() == null || $('input[name=swdjImg]').val() == ""){
							alert("请上传税务登记证！");
							return false;
						}
						if($('input[name=kpfjImg]').val() == null || $('input[name=kpfjImg]').val() == ""){
							alert("请上传开票信息附件！");
							return false;
						}
					}
					
					
					$('input[type=submit]').removeClass('billing_titlesbm');
					$('input[type=submit]').addClass('billing_titlesbm2');
					$('input[type=submit]').attr('disabled', true);
				 },
				 callback : function(data) {
					 $('input[type=submit]').removeClass('billing_titlesbm2');
					 $('input[type=submit]').addClass('billing_titlesbm');
					 $('input[type=submit]').removeAttr('disabled');
					 if (data && data.dataObject && data.dataObject == "0") {
						 $("#form").find("input[type='text']").val('');
						 $("#form").find("input[type='hidden']").val('');
						 $("#billingType").val('');
						 $('div[name=container]').html('');
						 alert("新增发票信息成功!");
						 $(".pageNum").val('1');
							$(".pageSize").val('10');
							var pageParam = $queryBlock.find('.pageParam').serializeArray();
							$.ajax({
								url : '../../billing/queryBillingInfoList.action',
								data : pageParam,
								dataType : 'JSON',
								headers : {
									'grid' : 1
								}
							}).done(function(data) {
								$("#exit").html(data.total+"");
								$("#residue").html((10-data.total)+"");
								data.totalPage = parseInt((data.total + data.pageSize - 1)/data.pageSize);
								$dataGrid.html(juicer(tplGrid, data));
							});
					 } else {
						 alert("新增发票信息失败!");
					 }
				}
				
			});	
			
			$(".pageNum").val('1');
			$(".pageSize").val('10');
			var pageParam = $queryBlock.find('.pageParam').serializeArray();
			$.ajax({
				url : '../../billing/queryBillingInfoList.action',
				data : pageParam,
				dataType : 'JSON',
				headers : {
					'grid' : 1
				}
			}).done(function(data) {
				$("#exit").html(data.total+"");
				$("#residue").html((10-data.total)+"");
				data.totalPage = parseInt((data.total + data.pageSize - 1)/data.pageSize);
				$dataGrid.html(juicer(tplGrid, data));
			});
			
			$("#billingType").change(function(){
				var billingType = $("#billingType").val();
				//普通发票
				if(billingType == "01"){
//					var tabke = document.getElementByIdx_x('tableid');
//					tabke.rows[0].style.display = tabke.rows[0].style.display = "none";
				    $('#tableid').css('display', 'none');  
				    $('#tableid').css('display', 'display');  
				}else{
					  $('#tableid').show();  
				}
			});
		},
		del:function(id){
			confirm('确定要删除吗？', function(yes) {
				if('yes'){
					$.ajax({
						url : '../../billing/deleteBillingInfoById.action',
						data : {"id":id},
						dataType : 'JSON',
						type: "POST",
						success: function(data){
							alert(data.message);
							$(".pageNum").val('1');
							$(".pageSize").val('10');
							var pageParam = $queryBlock.find('.pageParam').serializeArray();
							$.ajax({
								url : '../../billing/queryBillingInfoList.action',
								data : pageParam,
								dataType : 'JSON',
								headers : {
									'grid' : 1
								}
							}).done(function(data) {
								$("#exit").html(data.total+"");
								$("#residue").html((10-data.total)+"");
								data.totalPage = parseInt((data.total + data.pageSize - 1)/data.pageSize);
								$dataGrid.html(juicer(tplGrid, data));
							});
						}
					});
				}
			});
		}
	};
	
	$(document).ready(function() {
		window.billinginfo = new Billinginfo();
		
	});
	
});


function reallyLength(str){
	return str.replace(/[\u0391-\uFFE5]/g,"aa").length;
}
