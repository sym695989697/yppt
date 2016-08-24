/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月14日 下午2:33:27 
 */
(function() {
	var ViceCardList = function() {
		this.init();
	};
	ViceCardList.prototype = {
		config : {
			queryUrl : root + '/viceCard/queryViceCardListPage.action'
		},
		cache : {
			subPageId : null,
			grid : null,
			dialog : null,
			accordion : null,
			tree : null,
			tab : null,
			editId : null,
			showId : null,
			searchParms : {},
			alert_message:null
		},
		init : function() {
			this.render();
			this.loadGird();
			CODE_UTIL._initCodeData();
		},
		render : function() {
			var that = this;

			// 布局
			$('#layout1').ligerLayout({
				leftWidth : '210',
				allowLeftResize : false,
				height : '100%',
				heightDiff : -1,
				space : 4,
				onHeightChanged : that._heightChanged
			});
			$('.l-layout-header').show();

			$('.l-layout-header-toggle').css('z-index', '2');

			// $(window).scrollTop() + $(window).height()

		},

		// 高度改变
		_heightChanged : function(options) {
			var that = window.viceCardList;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		},

		loadGird : function() {
			var that = this;
			var options = {};
			var notShow=true;
			var cardParams=parent.window.rechargeList.cache.cardParams;
			var serchParams=[];
			serchParams.push({name:'requestParam.like.userId',value:'%%'});
			if(cardParams.userRegPhone){
				notShow=false;
				$("#userRegPhone").val(cardParams.userRegPhone);
				serchParams.push({name:'requestParam.like.userRegPhone',value:'%'+cardParams.userRegPhone+'%'});
			}
			if(cardParams.vehicleNo){
				notShow=false;
				$("#vehicleNo").val(cardParams.vehicleNo);
				serchParams.push({name:'requestParam.like.vehicleNo',value:'%'+cardParams.vehicleNo+'%'});
			}
			if(cardParams.cardNumber){
				notShow=false;
				$("#cardNumber").val(cardParams.cardNumber);
				serchParams.push({name:'requestParam.like.cardNumber',value:'%'+cardParams.cardNumber+'%'});
			}
			if(cardParams.memberName){
				notShow=false;
				$("#memberName").val(cardParams.memberName);
				serchParams.push({name:'requestParam.like.userName',value:'%'+cardParams.memberName+'%'});
			}
			var columns = [
					{
						display : '卡号',
						name : 'cardNumber',
						align : 'center',
						width : '20%',
						isSort : false,
						render : function(row) {
							 return '<a href="javascript:window.viceCardList.showOrgDetail(\''+ row.id+ '\');">'
							 + row.cardNumber + '</a>';
						}
					},
					{
						display : '车牌号',
						name : 'vehicleNo',
						align : 'center',
						width : '20%',
						isSort : false,
						render:function(row){
							if(row.vehicleNo){
								var color=CODE_UTIL.getCodeName('V-C-COLOR',row.vehicleColor);
								if(color){
									return row.vehicleNo+"-"+color;
								}
								return row.vehicleNo;
							}
							else{
								return "";
							}
						}
					},
					{
						display : '注册手机号',
						name : 'userRegPhone',
						align : 'center',
						width : '20%',
						isSort : false
					 
					},
					{//暂无对应字段
						display : '会员名称',
						name : 'userName',
						align : 'center',
						width : '20%',
						isSort : false
					},
					{
						display : '卡类型',
						name : 'cardType',
						align : 'center',
						width : '14%',
						isSort : false,
						render:function(row){
							if(row.cardType)
								return CODE_UTIL.getCodeName('IC-CARD-TYPE',row.cardType);
							else
								return "";
						}
					}
					 ];
			options['delayLoad']=notShow;
			options['columns'] = columns;
			options['checkbox'] = true;
			options['isChecked']=that.__isChecked;
			options['onCheckRow']=that.__onCheckRow;
			options['onCheckAllRow']=that.__onCheckAllRow;
			options['frozenCheckbox'] = false;
			// options['sortName'] = 'code';
			options['parms'] = serchParams; // 设置默认进来的请求参数
			options['usePager'] = true;
			 options['pageSize']=10;
			MALQ_UI.setGridOptions(options, that.config.queryUrl);

			$('#viceCardList').ligerGrid(options);
			that.cache.grid = $('#viceCardList').ligerGetGridManager();
			$('#viceCardList').css('top', '3px');
			$('#pageloading').hide();// 加载图片
		},
		__isChecked: function(rowdata) {
        	var that = window.viceCardList;
	       	 var result = false;
	       	 $(parent.window.rechargeList.cache.selectedRows).each(function(){
	       		 if(this.id == rowdata.id){
	       			 result = true;return;
	       		 }
	       	 });
			 return result;			
       },
       __onCheckRow : function(checked,data,rowid,rowdata){
        	 if(checked){
        		 parent.window.rechargeList.cache.selectedRows.push(data);
	       	 }else{
	       		var tem = [];
         		$(parent.window.rechargeList.cache.selectedRows).each(function(){
         			if(this.id == data.id)return true;
         			tem.push(this);
         		});
         		parent.window.rechargeList.cache.selectedRows = tem;
	       	 }
       },
       __onCheckAllRow : function(checked,element){
    	   var selectRow=window.viceCardList;
    	   var datas=window.viceCardList.cache.grid.getData();
    	   if(checked){
    		   $(datas).each(function(i,n){
    			   var isExs=false;
    			   var grid_id=n.id;
    			   $(parent.window.rechargeList.cache.selectedRows).each(function(j,m){
        			   if(grid_id==m.id){
        				   isExs=true;
        				   return false;
        			   }
        		   });
    			   if(isExs) {
    				   return false;
    			   }else{
    				   parent.window.rechargeList.cache.selectedRows.push(n); 
    			   }
    		   });
    	   }else{
    		   var tem = [];
    		   $(parent.window.rechargeList.cache.selectedRows).each(function(i,n){
    			   var isExs=false;
    			   var selectedId=n.id;
    			   $(datas).each(function(j,m){
        			   if(selectedId==m.id){
        				   isExs=true;
        				   return false;
        			   }
        		   });
    			   if(isExs) {
    				   return true;
    			   }else{
    				   tem.push(n); 
    			   }
    		   });
    		   parent.window.rechargeList.cache.selectedRows = tem;
    	   }
       },
		// 刷新Grid内容
		refreshGrid : function() {
			var that = window.viceCardList;
			that.cache.grid.loadData(true);
		},
		// 一般查询
		search : function() {
			var that = this;
			var userRegPhone = $("#userRegPhone").val();//注册手机号
			var vehicleNo = $("#vehicleNo").val(); //车牌号
			var memberName = $("#memberName").val(); //会员名称
			var cardNumber = $("#cardNumber").val(); //卡号
			var parms = {};
			parent.window.rechargeList.cache.cardParams['userRegPhone']=userRegPhone;
			parent.window.rechargeList.cache.cardParams['vehicleNo']=vehicleNo;
			parent.window.rechargeList.cache.cardParams['cardNumber']=cardNumber;
			parent.window.rechargeList.cache.cardParams['memberName']=memberName;
			if(userRegPhone){
				parms['requestParam.like.userRegPhone'] ="%"+userRegPhone+"%";
			}
			if(vehicleNo){
				parms['requestParam.like.vehicleNo']="%"+vehicleNo+"%";
			}
			if(memberName){
				parms['requestParam.like.userName']="%"+memberName+"%";
			}
			if(cardNumber){
				parms['requestParam.like.cardNumber']="%"+cardNumber+"%";
			}
			if(!(parent.window.rechargeList.cache.cardParams.cardNumber
					||parent.window.rechargeList.cache.cardParams.vehicleNo
					||parent.window.rechargeList.cache.cardParams.userRegPhone
					||parent.window.rechargeList.cache.cardParams.memberName
					)){
				window.viceCardList.alert('请输入查询条件','clew_ico_fail');
				retrun ;
			}
			//会员ID为Null的卡号过滤条件
			parms['requestParam.like.userId']="%%";
			that.cache.grid.set('parms', parms);
			that.cache.grid.changePage('first');
			that.cache.grid.loadData(true);
		},
		getSelectRowsToParent:function(){
			var selectedRows = parent.window.rechargeList.cache.selectedRows;
			var isSelected=false;
			$(selectedRows).each(function(i,n){
				if(n){
					isSelected=true;
					return false;
				}
			});
			if(!isSelected){
				window.viceCardList.alert('请选择IC卡','clew_ico_fail');
				return ;
			}
			parent.window.rechargeList.cache.isAMember=true;
			$(selectedRows).each(function(i,n){
				if(n){
					var regPhone=n.userRegPhone;
					$(selectedRows).each(function(j,m){
						if(this.userRegPhone!=regPhone){
							parent.window.rechargeList.cache.isAMember=false;
							return false;
						}
						this.rechargeMoney=null;
					});
					if(!parent.window.rechargeList.cache.isAMember){
						return false;
					}
				}
			});
			if(!parent.window.rechargeList.cache.isAMember){
				window.viceCardList.alert('只能选择同一会员的卡进行充值','clew_ico_fail');
			}
		},
		resetForm:function(){
			$("#queryForm").find("input[type='text']").val('');
			$("#queryForm").find("input[type='hidden']").val('');
		},
		alert : function(mes, css) {
			JSWK.clewBox(mes, css, failShowTime);
		}
	};

	$(document).ready(function() {
		window.viceCardList = new ViceCardList();
	});

})();
