/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年5月14
 */

(function() {
	var Welcome = function() {
		this.init();
	};
	
	Welcome.prototype = {
		config : {
			pieDiv1Id:'pieChart',
			pieDiv1Width:  null,
			pieDiv1Height: null,
			
			pieDiv2Id:'pieChart2',
			pieDiv2Width: null,
			pieDiv2Height: null,
			
			barDivId: "barChart",
			barDivWidth: null,
			barDivHeight:null,
			
			lineDivId:"lineChart",
			lineDivWidth: null,
			lineDivHeight: null
		},
		
		cache : {
			chartsData:{"data":null , "paramsObject":null}
		},
		
		intiChartsDiv:function(){
		    var that=this;
			that.config.pieDiv1Width=  $("#"+that.config.pieDiv1Id).width();
		    that.config.pieDiv1Height= $("#"+that.config.pieDiv1Id).height();
			
		    that.config.pieDiv2Width=  $("#"+that.config.pieDiv2Id).width();
		    that.config.pieDiv2Height= $("#"+that.config.pieDiv2Id).height();
			
		    that.config.barDivWidth= $("#"+that.config.barDivId).width();
		    that.config.barDivHeight=$("#"+that.config.barDivId).height();
			
		    that.config.lineDivWidth= $("#"+that.config.lineDivId).width();
		    that.config.lineDivHeight= $("#"+that.config.lineDivId).height();
		},
		
		init : function(){
		    var that=this;
		    this.initSelectInput();
		    that.intiChartsDiv();
			this.render();
		},
		
	    resizeCharts:function(){
	    	var that=this;
	    	
	    	that.intiChartsDiv();
		    
		    that.buildPieChart(that.cache.chartsData['data']);
		    that.buildPieChart2(that.cache.chartsData['data']);
		    that.buildBarChart(that.cache.chartsData['data'],that.cache.chartsData['paramsObject']);
			that.buildLineChart(that.cache.chartsData['data']);
		},

		initSelectInput:function(){
			var that=this;
			$("#layout1").ligerLayout({
				 leftWidth: 210,isLeftCollapse:'true',height: '100%',heightDiff:-1,space:0  //, onHeightChanged: that._heightChanged 
			});
			
			$("#layout1 .l-layout-left .l-layout-header .l-layout-header-toggle").bind("click", function(){
				that.resizeCharts();
			});
			$("#layout1 .l-layout-collapse-left").bind("click", function(){
				that.resizeCharts();
			});
			
			$("#accordion1").ligerAccordion();	
			CODE_UTIL.getSelectByCodeType($('#day'), 'CHARTS_DAY', 208, 100);
			
			var dictData = CODE_UTIL.getCodeData('CHARTS_DAY');
            if(dictData && dictData.length>0){
            	$('#day').ligerGetComboBoxManager().selectValue(dictData[0]['code']);
            }else{
            	$('#day').ligerGetComboBoxManager().selectValue(5);
            }
			
			$("#day").ligerComboBox({
				width:208,
				onSelected: function (newvalue){
//					$("#layout1").ligerGetLayoutManager().setLeftCollapse(true);
					
					that.intiChartsDiv();
					
					that.render();
				}
			});
		},
		render : function(){
			var that = this;
			var day=$("#day").ligerGetComboBoxManager().getValue();
			
			var dictData = CODE_UTIL.getCodeData('OPERATOR_EXECTIME');
			var execTime="1";
			if(dictData && dictData.length>0){
				execTime=dictData[0]['code'];
            }
            
			
			$("#pageloading").show(); 
	        JAjax(root+'/logJson/getModelActionCount.action?requestParam.equal.day='+day+"&requestParam.equal.execTime="+execTime, null, "json", function(data) {
 			   if(data){
 				    var paramsObject={};
				    paramsObject['day']=day;
				    paramsObject['execTime']=execTime;
				    
				    that.cache.chartsData['data']=data;
				    that.cache.chartsData['paramsObject']=paramsObject;
				    
				    that.buildPieChart(data);
				    that.buildPieChart2(data);
				    that.buildBarChart(data,paramsObject);
					that.buildLineChart(data);
			   }
 			  $("#pageloading").hide();
	        	
			}, 
			function(){
				$("#pageloading").hide();
				JSWK.clewBox('首页图表数据加载失败!','clew_ico_fail',failShowTime);
			}, true); 
  			
		},
		
		buildBarChart:function(data,paramsObject){
			var that=this;
			var params={};
			params['chartType']="../FusionCharts/Charts/StackedColumn3D.swf ";
			params['chartId']="barId1" ;
			params['divId']= that.config.barDivId;
			params['chartCanvesWidth']=that.config.barDivWidth;
			params['chartCanvesHeight']=that.config.barDivHeight;
			params['day']=paramsObject['day'];
			
			var beforeData1Array=[];
			var beforeData2Array=[];
			if(data && data['beforeCurDayBar']){
			    var dataBean=data['beforeCurDayBar'];
			    for(var dataProp in  dataBean){
			    	var subDataBean1={} ;
			    	var subDataBean2={} ;
			    	subDataBean1['label']=dataProp.split('~')[0];
			    	subDataBean2['value']=dataBean[dataProp];
			    	beforeData1Array.push(subDataBean1);
			    	beforeData2Array.push(subDataBean2);
			    }
			}
			
			
			var afterData1Array=[];
			var afterData2Array=[];
			if(data && data['afterCurDayBar']){
			    var dataBean=data['afterCurDayBar'];
			    for(var dataProp in  dataBean){
			    	var subDataBean1={} ;
			    	var subDataBean2={} ;
			    	subDataBean1['label']=dataProp.split('~')[0];
			    	subDataBean2['value']=dataBean[dataProp];
			    	afterData1Array.push(subDataBean1);
			    	afterData2Array.push(subDataBean2);
			    }
			}
			
			
			params['jsonData']={
				    "chart": {
//				        "caption": "柱状图",
				        "palette": "2",
				        "animation": "1",
				        "formatnumberscale": "0",
				        "ShowAboutMenuItem":"0",
				        "pieslicedepth": "30",
				        "startingangle": "125",
				        "legendPosition":"RIGHT",
				        "showBorder":"1",
				        "borderColor":'AAAAAA'
				    },
				    "categories":[
				              	{      "category":beforeData1Array
//				              					[
//				           						{          "label":"用户"        },        
//				           						{          "label":"角色"        },        
//				           						{          "label":"组织"        },        
//				           						{          "label":"权限"        }          
//				           						]    
				           	}       
				    ],
				    "dataset":[
				           	{      "seriesname":params['day']+"天前数据",      
				           				 "data":beforeData2Array
				           					 
//				           				[
//				           				     {          "value":"27400"        },        
//				           				     {          "value":"29800"        },        
//				           				     {          "value":"25800"        },        
//				           				     {          "value":"26800"        }          
//				           				 ]    
				           	},    
				           	{      "seriesname":params['day']+"天内数据",      
				           	       "data":afterData2Array
//				           	    	[
//				           	           {          "value":"10000"        },        
//				           	           {          "value":"11500"        },        
//				           	           {          "value":"12500"        },        
//				           	           {          "value":"15000"        }
//				           	       ]    
				           	 }  
				      ] 
				 	}   ;
			
			this._buildChart(params);
		},
		
		buildPieChart:function(data){
			var that=this;
			var params={};
			params['chartType']="../FusionCharts/Charts/Pie3D.swf";
			params['chartId']="pieId1" ;
			params['divId']=that.config.pieDiv1Id;
			params['chartCanvesWidth']=that.config.pieDiv1Width;
			params['chartCanvesHeight']=that.config.pieDiv1Height;
			
			var dataArray=[];
			if(data && data['afterCurDayPie']){
			    var dataBean=data['afterCurDayPie'];
			    for(var dataProp in  dataBean){
			    	var subDataBean={} ;
			    	subDataBean['label']=dataProp.split('~')[0];
			    	subDataBean['value']=dataBean[dataProp];
			    	dataArray.push(subDataBean);
			    }
			}
			params['jsonData']={
			    "chart": {
//			        "caption": "饼图",
			        "palette": "2",
			        "animation": "1",
			        "formatnumberscale": "0",
			        "ShowAboutMenuItem":"0",
			        "pieslicedepth": "30",
			        "startingangle": "125",
			        "legendposition": "right",
//			        "legendCaption":"模块",
			        "showBorder":"1",
			        "showLegend":"1",
			        "showLabels":"1",
			        "showValues":"1",
			        "borderColor":'AAAAAA'
			    },
			    "data": dataArray,
			    "styles": {
			        "definition": [
			            {
			                "type": "font",
			                "name": "CaptionFont",
			                "size": "15",
			                "color": "666666"
			            },
			            {
			                "type": "font",
			                "name": "SubCaptionFont",
			                "bold": "0"
			            }
			        ],
			        "application": [
			            {
			                "toobject": "caption",
			                "styles": "CaptionFont"
			            },
			            {
			                "toobject": "SubCaption",
			                "styles": "SubCaptionFont"
			            }
			        ]
			    }
			    
			 	}    ;
			
			this._buildChart(params);
		},
		
		buildPieChart2:function(data){
			var that=this;
			var params={};
			params['chartType']="../FusionCharts/Charts/Pie3D.swf";
			params['chartId']="pieId2" ;
			params['divId']=that.config.pieDiv2Id;
			params['chartCanvesWidth']=that.config.pieDiv2Width;
			params['chartCanvesHeight']=that.config.pieDiv2Height;
			
			var dataArray=[];
			if(data && data['afterCurDayGTExecTimePie']){
			    var dataBean=data['afterCurDayGTExecTimePie'];
			    for(var dataProp in  dataBean){
			    	var subDataBean={} ;
			    	subDataBean['label']=dataProp.split('~')[0];
			    	subDataBean['value']=dataBean[dataProp];
			    	dataArray.push(subDataBean);
			    }
			}
			params['jsonData']={
			    "chart": {
//			        "caption": "饼图",
			        "palette": "2",
			        "animation": "1",
			        "formatnumberscale": "0",
			        "ShowAboutMenuItem":"0",
			        "pieslicedepth": "30",
			        "startingangle": "125",
			        "legendposition": "right",
//			        "legendCaption":"模块",
			        "showBorder":"1",
			        "borderColor":'AAAAAA',
			        "showLegend":"1",
			        "showLabels":"1",
			        "showValues":"1"
			    },
			    "data": dataArray,
			    "styles": {
			        "definition": [
			            {
			                "type": "font",
			                "name": "CaptionFont",
			                "size": "15",
			                "color": "666666"
			            },
			            {
			                "type": "font",
			                "name": "SubCaptionFont",
			                "bold": "0"
			            }
			        ],
			        "application": [
			            {
			                "toobject": "caption",
			                "styles": "CaptionFont"
			            },
			            {
			                "toobject": "SubCaption",
			                "styles": "SubCaptionFont"
			            }
			        ]
			    }
			    
			 	}    ;
			
			this._buildChart(params);
		},
		
		buildLineChart:function(data){
			var that=this;
			var params={};
			params['chartType']="../FusionCharts/Charts/MSLine.swf";
			params['chartId']="lineId1"  ;
			params['divId']=that.config.lineDivId;
			params['chartCanvesWidth']=that.config.lineDivWidth;
			params['chartCanvesHeight']=that.config.lineDivHeight;
			
			var data1Array=[];
			var data2Array=[];
			if(data && data['afterCurDayLine']){
			    var dataBean=data['afterCurDayLine'];
			    for(var dataProp in  dataBean){
			    	var subDataBean1={} ;
			    	var subDataBean2={} ;
			    	subDataBean1['label']=(dataProp.split('~')[0]).substring(5);
			    	subDataBean2['value']=dataBean[dataProp];
			    	data1Array.push(subDataBean1);
			    	data2Array.push(subDataBean2);
			    }
			}
			
			params['jsonData']={
			    "chart": {
//			        "caption": "线图",
			        "palette": "2",
			        "animation": "1",
			        "formatnumberscale": "0",
			        "ShowAboutMenuItem":"0",
			        "pieslicedepth": "30",
			        "startingangle": "125",
			        "bgcolor": "FFFFFF",
			        "borderColor":'AAAAAA',
			        "showBorder":"1",
//			        "showLegend":"1",
//			        "showLabels":"1",
//			        "showValues":"1",
//			        "legendposition": "right",
//			        "legendCaption":"模块",
			        "labelDisplay":'ROTATE',
			        "slantLabels":"1"
			    },
			    "categories": [
			                   {
			                       "category":data1Array 
//			                    	   [
//			                           { "label": "Jan" },
//			                           { "label": "Feb" },
//			                           { "label": "Mar" },
//			                           { "label": "Apr" },
//			                           { "label": "May" },
//			                           { "label": "Jun" },
//			                           { "label": "Jul" },
//			                           { "label": "Aug" }
//			                           ]
			                   }
			               ],
			               "dataset": [
			                   {
			                       "data": data2Array
//			                       [
//			                           { "value": "-6"  },
//			                           { "value": "-15" },
//			                           { "value": "3"   },
//			                           { "value": "12"  },
//			                           { "value": "32"  },
//			                           { "value": "44"  },
//			                           { "value": "52"  },
//			                           { "value": "50"  }
//			                       ]
			                   }
			               ]
			 	}    ;
			
			this._buildChart(params);
		},
		
		_buildChart:function(params){
			if(FusionCharts(params['chartId'])){
				FusionCharts(params['chartId']).dispose();
			}
			
			var myChart = new FusionCharts(params['chartType'] , params['chartId'] , params['chartCanvesWidth'], params['chartCanvesHeight']);
			myChart.configure("ChartNoDataText", "本图暂无数据，无法展示效果！");
			myChart.setJSONData(params['jsonData']);
	    	myChart.render(params['divId']);
			
			
//			if(!FusionCharts(params['chartId'])){
//				
//				var myChart = new FusionCharts(params['chartType'] , params['chartId'] , params['chartCanvesWidth'], params['chartCanvesHeight']);
//				myChart.setJSONData(params['jsonData']);
//				myChart.configure("ChartNoDataText", "本图暂无数据，无法展示效果！");
//		    	myChart.render(params['divId']);
//			}else{
//				
//				var chartObject=FusionCharts(params['chartId']);
////				chartObject.resizeTo( params['chartCanvesWidth'], params['chartCanvesHeight']);
//				chartObject.setJSONData(params['jsonData']);
//			}

		} ,
		// 高度改变
		_heightChanged : function(options) {
			var that = this;
			if (that.cache.tab) {
				that.cache.tab.addHeight(options.diff);
			}
			if (that.cache.accordion && options.middleHeight - 24 > 0) {
				that.cache.accordion.setHeight(options.middleHeight - 24);
			}
		}
		
		
	};
	
	$(document).ready(function() {
		FusionCharts.setCurrentRenderer('javascript');
		window.welcome = new Welcome();
	});
	
	
})();