var createMessageTemplate = root + '/messageTemplate/createMessageTemplate.action';
var initMessageTemplateGrid = root + '/messageTemplate/initMessageTemplateGrid.action';
var stopMessageTemplate = root + '/messageTemplate/stopMessageTemplate.action';
var startMessageTemplate = root + '/messageTemplate/startMessageTemplate.action';
var updateMessageTemplate = root + '/messageTemplate/updateMessageTemplate.action';
var deleteMessageTemplate = root + '/messageTemplate/deleteMessageTemplate.action';
var modifySignUrl = root + '/messageTemplate/modifySign.action';
var querySignUrl = root + '/messageTemplate/querySign.action';

function vTemplate(){
	if(!$('#templateCode').val()){
		JSWK.clewBox('请输入短信模板编码', 'clew_ico_fail', 2000);
		return false;
	}else{
		for(var i = 1 ; i < ($('#table_template tbody').children().length - 1) ; i++){
			var tr = $('#table_template tbody').children().eq(i);
			var td = tr.children().eq(0);
			if(td.text() == $('#templateCode').val()){
				JSWK.clewBox('已存在相同的短信模板编码', 'clew_ico_fail', 2000);
				return false;
			}
		}
	}
	if(!$('#templateName').val()){
		JSWK.clewBox('请输入短信模板名称', 'clew_ico_fail', 2000);
		return false;
	}else{
		for(var i = 1 ; i < ($('#table_template tbody').children().length - 1) ; i++){
			var tr = $('#table_template tbody').children().eq(i);
			var td = tr.children().eq(1);
			if(td.text() == $('#templateName').val()){
				JSWK.clewBox('已存在相同的短信模板名称', 'clew_ico_fail', 2000);
				return false;
			}
		}
	}
	if(!$('#content').val()){
		JSWK.clewBox('请输入短信内容', 'clew_ico_fail', 2000);
		return false;
	}
	return true;
}

function add(){
	if(vTemplate() == true){
		var param = {};
		param["requestParam.equal.templateCode"] = $('#templateCode').val();
		param["requestParam.equal.templateName"] = $('#templateName').val();
		param["requestParam.equal.content"] = $('#content').val();
		JAjax(createMessageTemplate, param, "json", function(data){
			if(data && data.message=="1"){
				JSWK.clewBox('添加短信模板成功', 'clew_ico_succ', 1000);
				$('#templateCode').val('');
				$('#templateName').val('');
				$('#content').val('');
				initGrid();
			}else{
				JSWK.clewBox('添加短信模板发生异常', 'clew_ico_fail', 1000);
			}
		}, function(){JSWK.clewBox('添加短信模板发生异常', 'clew_ico_fail', 1000);}, false);
	}
}

function stop(id){
	$.ligerDialog.confirm("是否确定停止？",function(r){
		if(r){
			var param = {};
			param["requestParam.equal.id"] = id;
			JAjax(stopMessageTemplate, param, "json", function(data){
				if(data && data.message=="1"){
					JSWK.clewBox('停止短信模板成功', 'clew_ico_succ', 1000);
					initGrid();
				}else{
					JSWK.clewBox('停止短信模板发生异常', 'clew_ico_fail', 1000);
				}
			}, function(){JSWK.clewBox('停止短信模板发生异常', 'clew_ico_fail', 1000);}, false);
		}
	});
}

function start(id){
	$.ligerDialog.confirm("是否确定启动？",function(r){
		if(r){
			var param = {};
			param["requestParam.equal.id"] = id;
			JAjax(startMessageTemplate, param, "json", function(data){
				if(data && data.message=="1"){
					JSWK.clewBox('启动短信模板成功', 'clew_ico_succ', 1000);
					initGrid();
				}else{
					JSWK.clewBox('启动短信模板发生异常', 'clew_ico_fail', 1000);
				}
			}, function(){JSWK.clewBox('启动短信模板发生异常', 'clew_ico_fail', 1000);}, false);
		}
	});
}

function update(id){
	if($('input[id=\'' + id + '\']').val() == $('span[id=\'' + id + '\']').html()){
		JSWK.clewBox('修改内容与原内容一致', 'clew_ico_fail', 1000);
		return;
	}
	if($('input[id=\'' + id + '\']').val() == ''){
		JSWK.clewBox('修改内容不能为空', 'clew_ico_fail', 1000);
		return;
	}
	$.ligerDialog.confirm("是否确定修改？",function(r){
		if(r){
			var param = {};
			param["requestParam.equal.id"] = id;
			param["requestParam.equal.content"] = $('input[id=\'' + id + '\']').val();
			JAjax(updateMessageTemplate, param, "json", function(data){
				if(data && data.message=="1"){
					JSWK.clewBox('修改短信模板成功', 'clew_ico_succ', 1000);
					initGrid();
				}else{
					JSWK.clewBox('修改短信模板发生异常', 'clew_ico_fail', 1000);
				}
			}, function(){JSWK.clewBox('修改短信模板发生异常', 'clew_ico_fail', 1000);}, false);
		}
	});
}

function del(id){
	$.ligerDialog.confirm("是否确定删除？",function(r){
		if(r){
			var param = {};
			param["requestParam.equal.id"] = id;
			JAjax(deleteMessageTemplate, param, "json", function(data){
				if(data && data.message=="1"){
					JSWK.clewBox('删除短信模板成功', 'clew_ico_succ', 1000);
					initGrid();
				}else{
					JSWK.clewBox('删除短信模板发生异常', 'clew_ico_fail', 1000);
				}
			}, function(){JSWK.clewBox('删除短信模板发生异常', 'clew_ico_fail', 1000);}, false);
		}
	});
}

function modifySign(){
	$.ligerDialog.confirm("是否确定修改？",function(r){
		if(r){
			var param = {};
			param["requestParam.equal.sign"] = $('#sign').val();
			JAjax(modifySignUrl, param, "json", function(data){
				if(data && data.message=="1"){
					JSWK.clewBox('修改短信签名成功', 'clew_ico_succ', 1000);
				}else{
					JSWK.clewBox('修改短信签名发生异常', 'clew_ico_fail', 1000);
				}
			}, function(){JSWK.clewBox('修改短信签名发生异常', 'clew_ico_fail', 1000);}, false);
		}
	});
}

function querySign(){
	JAjax(querySignUrl, null, "json", function(data){
		if(data && data.data!=null && data.data!='null'){
			$('#sign').val(data.data[0].sign);
			JSWK.clewBox('查询短信签名成功', 'clew_ico_succ', 1000);
		}else{
			JSWK.clewBox('查询短信签名发生异常', 'clew_ico_fail', 1000);
		}
	}, function(){JSWK.clewBox('查询短信签名发生异常', 'clew_ico_fail', 1000);}, true);
}


function changeDisplay(id){
	$('span[id=\'' + id + '\']').css('display','none');
	$('input[id=\'' + id + '\']').css('display','block');
	$('input[id=\'' + id + '\']').focus();
}

function inputOnBlur(id){
	$('span[id=\'' + id + '\']').css('display','block');
	$('input[id=\'' + id + '\']').val($('span[id=\'' + id + '\']').html());
	$('input[id=\'' + id + '\']').css('display','none');
}

function initGrid(){
	JAjax(initMessageTemplateGrid, null, "json", function(data){
		if(data && data.data!=null && data.data!='null'){
			table_tr = '';
			for(var i = 0 ; i < data.data.length ; i++){
				id = data.data[i].id;
				templateCode = data.data[i].templateCode;
				templateName = data.data[i].templateName;
				content = data.data[i].content;
				state = data.data[i].state;
//				table_tr += '<tr><td align="center" height="40">' + templateCode + '</td><td align="center">' + templateName + '</td><td align="center"><span id=\'' + id + '\' title=\'' + content + '\' onclick="javascript:changeDisplay(\'' + id + '\')" style="cursor: pointer;">' + content + '</span><input id="' + id + '" name="' + id + '" type="text" size="100" value="' + content + '" style="display: none;" onblur="javasscript:inputOnBlur(\'' + id + '\')"></input></td><td align="center">' + ((state == "1") ? "<label style=\"color: #0000ff\">启用</label>" : "<label style=\"color: #ff0000\">停止</label") + '</td>';
				table_tr += '<tr><td align="center" height="40">' + templateCode + '</td><td align="center">' + templateName + '</td><td align="center"><span id=\'' + id + '\' title=\'' + content + '\' onclick="javascript:changeDisplay(\'' + id + '\')" style="cursor: pointer;">' + ((content.length > 50) ? (content.substring(0, 50) + '...') : content) + '</span><input id="' + id + '" name="' + id + '" type="text" size="100" value="' + content + '" style="display: none;"></input></td><td align="center">' + ((state == "1") ? "<label style=\"color: #00ff00\">启用</label>" : "<label style=\"color: #ff0000\">停止</label") + '</td>';
				if(state == "1"){
					table_tr += '<td align="center"><a href="javascript:stop(\'' + id + '\')">停止</a>&nbsp;&nbsp;&nbsp;<a href="javascript:update(\'' + id + '\')">修改</a>&nbsp;&nbsp;&nbsp;<a href="javascript:del(\'' + id + '\')">删除</a></td></tr>';
				}else{
					table_tr += '<td align="center"><a href="javascript:start(\'' + id + '\')">启动</a>&nbsp;&nbsp;&nbsp;<a href="javascript:update(\'' + id + '\')">修改</a>&nbsp;&nbsp;&nbsp;<a href="javascript:del(\'' + id + '\')">删除</a></td></tr>';
				}
			}
			$('#table_template tr').eq(1).nextAll().remove();
			$('#table_template').append(table_tr);
		}
	}, function(){JSWK.clewBox('初始化短信模板列表发生异常', 'clew_ico_fail', 1000);}, false);
}

// *************************************************主方法**************************************

$(function() {
	querySign();
	initGrid();
});