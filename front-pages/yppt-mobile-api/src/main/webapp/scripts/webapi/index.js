
var indexLogs = function(){
	
	this.grid = null;
	
	$("#layout").ligerLayout({
		leftWidth : 200
	});
	
	data = [];
	data1 = [];
	var o1 = {
		text : "日志",
		index : "log"
	};
	var children11 = {
		text : "手机服务端日志",
		index : "webapiLog"
	};
	
	data1.push(children11);
	
	o1.children = data1;
	data.push(o1);
	
	url = root+"/webapiLog/log.html";
	

};
indexLogs.prototype= {
		init:function(){
			this.initLeftTree();
			this.showGrid();
		},
		showGrid:function(){
			var that = this;
			that.grid = $("#grid")
			.ligerGrid({
				columns : [
						{
							display : '服务编码',
							name : 'action',
							minWidth : 20,
							width : '8%',
							align : 'center',
							isSort : false
						},
						{
							display : '请求类型',
							name : 'channelCode',
							minWidth : 20,
							width : '8%',
							align : 'center',
							isSort : false
						},
						{
							display : '请求参数',
							name : 'request',
							minWidth : 20,
							width : '29%',
							align : 'left',
							isSort : false,
							render : function(item) {
								if (item.request != null && item.request.length>50) {
									return item.request.substring(0,50)+"...";
								} else {
									return item.request;
								}
							}
						},
						{
							display : '返回参数',
							name : 'response',
							minWidth : 20,
							width : '29%',
							align : 'left',
							isSort : false,
							render : function(item) {
								if (item.response != null && item.response.length>50) {
									return item.response.substring(0,50)+"...";
								} else {
									return item.response;
								}
							}
						},
						{
							display : '状态',
							name : 'state',
							minWidth : 20,
							width : '8%',
							align : 'center',
							isSort : false,
							render : function(item) {
								if (item.state == 'success') {
									return '成功';
								} else {
									return '失败';
								}
							}
						},
						{
							display : '创建日期',
							name : 'handleTime',
							minWidth : 20,
							width : '14%',
							align : 'center',
							isSort : false
							/*render : function(item) {
								debugger;
								var d = new Date(parseFloat(item.time));
								return d.getFullYear()
										+ "-"
										+ (((d.getMonth() + 1) >= 10) ? (d.getMonth() + 1)
												: ("0" + (d.getMonth() + 1)))
										+ "-"
										+ ((d.getDate() >= 10) ? d.getDate() : ("0" + d
												.getDate()))
										+ " "
										+ ((d.getHours() >= 10) ? d.getHours() : ("0" + d
												.getHours()))
										+ ":"
										+ ((d.getMinutes() >= 10) ? d.getMinutes()
												: ("0" + d.getMinutes()))
										+ ":"
										+ ((d.getSeconds() >= 10) ? d.getSeconds()
												: ("0" + d.getSeconds()));
							}*/
						}],
			
				root : 'Rows', // 数据源字段名
				record : 'Total',
				dataAction : 'server',// 表示数据从服务器来的，而不是来自js文件
				url : url,
				pageSizeOptions : [ 10, 15, 20, 30, 50, 100 ],
				pageSize : 30,
				usePager : true,
				width : '100%',
				height : '100%',
				pageParmName : 'page',
				pagesizeParmName : 'pageSize',
				detail : {
					onShowDetail : that.showDetail,
					height : 'auto'
					
				}
			});
		},
		initLeftTree:function() {
			var that = this;
			$("#tree").empty();
			$("#tree").ligerTree({
				nodeWidth : 200,
				data : data,
				checkbox : false,
				idFieldName : 'id',
				isExpand : true,
				slide : false,
				onSelect : function(node) {
					if (node.data.index == 'webapiLog') {
						that.showGrid();
					}
					
				}
			});
		},
		showDetail:function(row, detailPanel, callback){
			$(detailPanel).text("");
			var html = '<div style="width:93%;">'
				+ '<div style="float: left;width:45%;">请求参数：</br><textarea rows="24" cols="85">'
				+ row.request
				+ '</textarea></div>'
				+ '<div style="float: right;width:45%;">响应参数：</br><textarea rows="24" cols="85">'
				+ row.response
				+ '</textarea></div>' + '</div>';
			$(detailPanel).append($(html).css('margin', 10));
		},
		query:function(){
			para = {};
			if ($('#servCode').val()) {
				para['servCode'] = $('#servCode').val();
			}
			if ($('#status').val() != -1) {
				para['statusType'] = $('#status').val();
			}
					
			this.grid.setOptions({
				parms : para
			});
			this.grid.changePage("first");
			this.grid.loadData(true);
		},
		reset:function(){
			//
		}
};


$(function() {
	var indexLog = new indexLogs();
	indexLog.init();
	window.indexLog = indexLog;
	
});