/**
 * 省、市、县显示及动态显示方法
 * 依赖areaData.js,使用时请引入;
 */
var AREA_CODE_UTIL = {
		//初始化区划信息
	    initAreaCodeInfo: function(){
	    	var that = this;
	    	if(!that.getParentZoneData()){//如果拿不到zoneDate,则初始化
		    	if(window.ZoneData && window.ZoneData.length>0){
			    		var data = that.convertZoneData(window.ZoneData);
			    		if(data.length>0)
			    			window.ZoneDataTree = data;
		    	}else{   		   		
/*		    		var url = root+'/codeJson/code!getAllCodeList.action';	
		    		JAjax(url,{}, "json", function(data){   			
		    			data = that.getZoneData(data);
		    			window.ZoneData = data;  			
		    		}, function(){alert('初始化代码信息异常!');}, true);*/
		    	}
	    	}
	    },
	    /**
	     * 转换ZoneData为包含pid,id,text的类对象的数组,其中pid:(父结点id值)
	     * @param data 简单的zoneData数据对象
	     * @returns {Array}
	     */
	    convertZoneData: function(data){
	    	var pid,code,tem;
			var zd = [];
			$(data).each(function(){
				if(this.pid && this.pid.length>0){
					return true;
				}
				code = $.trim(this.c);
				if('0000' == code.substring(code.length-4,code.length))
					pid = '0';
				else if('00' == code.substring(code.length-2,code.length))
					pid = code.substring(0,2)+'0000';
				else
					pid = code.substring(0,4)+'00';
				tem = {};
				tem['id']=code;
				tem['text']=$.trim(this.n);
				tem['pid']=pid;
				zd.push(tem);
			});
			return zd;
	    },
		// 获取省市县地区数据
		getParentZoneData: function(){
	  		var redata = null;
	  		if(window && window.ZoneDataTree){
	  			redata = window.ZoneDataTree;
	  		}else if(redata==null && parent.window && parent.window.ZoneDataTree){
	  			redata = parent.window.ZoneDataTree;
	  		}else if(redata==null && parent.parent.window && parent.parent.window.ZoneDataTree){
	  			redata = parent.parent.window.ZoneDataTree;
	  		}else if(redata==null && parent.parent.parent.window && parent.parent.parent.window.ZoneDataTree){
	  			redata = parent.parent.parent.window.ZoneDataTree;
	  		}else if(redata==null && parent.parent.parent.parent.window && parent.parent.parent.parent.window.ZoneDataTree){
	  			redata = parent.parent.parent.parent.window.ZoneDataTree;
	  		}else if(redata==null && parent.parent.parent.parent.parent.window && parent.parent.parent.parent.parent.window.ZoneDataTree){
	  			redata = parent.parent.parent.parent.parent.window.ZoneDataTree;
	  		}
	  		return redata;
	  	},
		/**
		 * 根据编码获取数据
		 * @param pCode 地区编码: 父节点编码
		 * @returns {Array}
		 */
	  	getZoneData : function(pCode){
	  		var that = this;
	  		var myData = [];
	  		var data = this.getParentZoneData();
	  		pCode = pCode?$.trim(pCode):'';
	  		if(pCode){
		  		$(data).each(function(){
		  			if(this.pid == pCode){
						myData.push(this);
		  			}
		  		});
	  		}
	  		myData.sort(that.sortfunction);
	  		return myData;
	  	},	  	
	  	sortfunction: function(x,y){
	  		return x.id - y.id;
	  	},
	  	/**
	  	 * 获取地区数据节点对象
	  	 * @param code 地区编码
	  	 * @returns 地区数据结点对象
	  	 */
	  	getZoneObjByCode : function(code){  		
	  		var data = this.getParentZoneData();
	  		var obj;
	  		code = code?$.trim(code):'';
	  		if(code){
		  		$(data).each(function(){
		  			if(this.id == code){	  				
		  				obj = this;
		  				return false;
		  			}
		  		});
	  		}
	  		return obj;
	  	},
		/** 根据省份code动态显示市
		 * @param province 省元素的id
		 * @param city 市元素的id
		 * @param districtId 县元素的id
		 */
		selectChangeCity:function(provinceId, cityId ,districtId){
			this.selectCity(provinceId, cityId);
			//清空县一级select框
			$("#" + districtId).html("<option value=''>市</option>");
		},
		/** 根据市code动态显示县
		 * @param province 省元素的id
		 * @param city 市元素的id
		 * @param districtId 县元素的id
		 */
		selectChangeDistrict:function(provinceId, cityId ,districtId){
			this.selectDistrict(provinceId, cityId ,districtId);
		},
		/** 初始化省的select框
		 * @param province 省元素的id
		 * @param city 市元素的id
		 * @param districtId 县元素的id
		 * @param provinceCode 默认选中项
		 */
	    selectProvince:function(provinceId, provinceCode){
			var option = "<option value=''>省</option>";
			var reg = /^\d{2}0{4}$/;
			/*try{
				JAjax(contextPath + '/ccCodeJson/getAreaCodeFromCatch.action', null, "json", function(data) {
					if(data != null){
						
						$(data).each(function(){
							if(reg.exec(this.c)){
								if(this.c == provinceCode){
									option +="<option value='"+this.c+"' selected>"+this.n+"</option>";
								}else{
									option +="<option value='"+this.c+"'>"+this.n+"</option>";
								}
							}
						});
					}
				}, function() {
					alert('省市区初始化发生异常');
				}, false);	
			}catch(e){
				alert(e);
			}*/
			var data = this.getZoneData("0");
			$(data).each(function(){
				if(this.id == provinceCode){
					option +="<option value='"+this.id+"' selected>"+this.text+"</option>";
				}else{
					option +="<option value='"+this.id+"'>"+this.text+"</option>";
				}
				
			});
			$("#"+provinceId).html(option);
		},
		//市
		/** 初始化市的select框,默认省有值
		 * @param province 省元素的id
		 * @param city 市元素的id
		 * @param districtId 县元素的id
		 * @param cityCode 默认选中项
		 */
		selectCity:function(provinceId, cityId, cityCode){
			var option = "<option value=''>市</option>";
//			var reg = /^\d{3}[1-9]{1}0{2}$/;
			var province = $("#"+provinceId).val();
//			try{
//			JAjax(contextPath + '/ccCodeJson/getAreaCodeFromCatch.action', {"province":province}, "json", function(data) {
//				if(data != null){
//					$(data).each(function(){
//						if(reg.exec(this.c)){
//							if(this.c == cityCode){
//								option +="<option value='"+this.c+"' selected>"+this.n+"</option>";
//							}else{
//								option +="<option value='"+this.c+"'>"+this.n+"</option>";
//							}
//						}
//					});
//				}
//			}, function() {
//				alert('省市区初始化发生异常');
//			}, false);
//			}catch(e){
//				alert(e);
//			}
			var data = this.getZoneData(province);
			$(data).each(function(){
				if(this.id == cityCode){
					option +="<option value='"+this.id+"' selected>"+this.text+"</option>";
				}else{
					option +="<option value='"+this.id+"'>"+this.text+"</option>";
				}
			});
			$("#"+cityId).html(option);
		},
		//县
		/** 初始化市的select框,默认省,市有值
		 * @param province 省元素的id
		 * @param city 市元素的id
		 * @param districtId 县元素的id
		 * @param districtCode 默认选中项
		 */
		selectDistrict:function(provinceId, cityId ,districtId, districtCode){
			var option = "<option value=''>县</option>";
			var reg = /^\d{6}$/;
			var province = $("#" + provinceId).val();
			var city = $("#" + cityId).val();
//			JAjax(contextPath + '/ccCodeJson/getAreaCodeFromCatch.action', {"province":province ,"city":city}, "json", function(data) {
//				if(data != null){
//					$(data).each(function(){
//						if(reg.exec(this.c)){
//							if(this.c == districtCode){
//								option +="<option value='"+this.c+"' selected>"+this.n+"</option>";
//							}else{
//								option +="<option value='"+this.c+"'>"+this.n+"</option>";
//							}
//						}
//					});
//				}
//			}, function() {
//				JSWK.clewBox('省市区初始化发生异常', 'clew_ico_fail', CSM_failAlertTime);
//			}, false);
			var data = this.getZoneData(city);
			$(data).each(function(){
				if(this.id == districtCode){
					option +="<option value='"+this.id+"' selected>"+this.text+"</option>";
				}else{
					option +="<option value='"+this.id+"'>"+this.text+"</option>";
				}
			});
			$("#"+districtId).html(option);
		},
		//根据code查询地址
		getAddressByCode : function(province, city,district){
			//类型全相同 使用全等号
			var value = "";
//			var parmes = parmes = {"province" : province, "city":city,"district":district };
//			JAjax(contextPath + '/ccCodeJson/refreshZoneDataCatch.action', parmes, "html", function(data) {
//				if(data){
//					value = data;
//				}
//			}, function() {
//				JSWK.clewBox('查询地址失败！', 'clew_ico_fail', CSM_failAlertTime);
//			}, false);
			var provinceObj = this.getZoneObjByCode(province);
			var cityObj = this.getZoneObjByCode(city);
			var districtObj = this.getZoneObjByCode(district);
			if(provinceObj){
				value += provinceObj.text;
			}
			if(cityObj){
				value += cityObj.text;
			}
			if(districtObj){
				value += districtObj.text;
			}
			return value;
		},
		getZoneNameByCode: function(zoneCode){
			var value ="";
			var zoneObj = this.getZoneObjByCode(zoneCode);
			if(zoneObj){
				value += zoneObj.text;
			}
			return value;
		}
};
//初始化省市县代码
$(function(){AREA_CODE_UTIL.initAreaCodeInfo();});
