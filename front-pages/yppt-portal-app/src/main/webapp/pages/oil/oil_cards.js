var $exactQueryBtn = $('#exactQueryBtn'),
tplGrid = $('#tplGrid').html(),
ajaxCardListQueryUrl = '../../oilcard/queryCardList.action',
ajaxCardRechargerUrl = '../../recharge/oilCardRechargeDataTransmit.action',
$cardApplyBtn = $('#cardApplyBtn'),
$dataGrid = $('#dataGrid'),
$queryBlock = $('.queryBlock'),
$oilCardRecharge = $('#oilCardRecharge');
var safari = (navigator.userAgent.toLowerCase().indexOf('safari') != -1) ? true : false;
$(function() {
	//初始页面
	//$('#oil_cards').addClass('leftsideBar_curr');
	$('.pageNum').val(1);
	var queryParam = [];
	var searchInput = $('#searchInput').val();
	queryParam.push({name:'requestParam.equal.cardNumber', value:searchInput});
	ajaxCardListQuery(queryParam);
	$cardApplyBtn.click(function() {
		window.location.href = basePath + 'pages/card/card_apply.jsp';
	});
	
	/*精确查询*/
	$exactQueryBtn.click(function(e){
		$('.pageNum').val(1);
		queryParam = [];
		var searchInput = $('#searchInput').val();
		queryParam.push({name:'requestParam.equal.cardNumber', value:encodeURIComponent(searchInput)});
		ajaxCardListQuery(queryParam);
		e.preventDefault();
	});
	/* 渲染分页模板 */
	$dataGrid.on('click', '.pager a', function(e) {
		$('.pageNum').val($(this).attr('p'));
		ajaxCardListQuery(queryParam);
		e.preventDefault();
	});
	//选卡
	$oilCardRecharge.click(function() {
		var param={};
		if($('#dataGrid .c_on').length <= 0) {
			alert('请先勾选油卡再进行充值');
			return false;
		}
		$('#dataGrid .c_on').each(function (i) {
		param['cardRecharges[' + i + '].id'] = $(this).children('#cardId').val();
		param['cardRecharges[' + i + '].cardNO'] = $(this).text();
		param['cardRecharges[' + i + '].carNO'] = $(this).parent().nextAll().eq(2).text();
		param['cardRecharges[' + i + '].balance'] = $(this).parent().nextAll().eq(1).text();
		}); 
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
	});
});

function check_it(e, that) {
	var ls = $('label');
	var inp = $(that).children("input").eq(0);
    if (that.className == 'label_check c_off' || (!safari && inp.checked)) {
    	that.className = 'label_check c_on';
        if(that.id == 'label') {
            for (var i = 0; i < ls.length; i++) {
                var l = ls[i];
                if(l.id != 'label') {
	                l.className = 'label_check c_on';
	                if (safari) {
	                	inp.checked = false;
	                }
                }
            }
        } else {
        	var checkedAll = true;
        	for (var i = 0; i < ls.length; i++) {
                var l = ls[i];
                if(l.id != 'label' && l.className == 'label_check c_off') {
                	checkedAll = false;
                }
            }
        	if(checkedAll){
            	for (var i = 0; i < ls.length; i++) {
                    var l = ls[i];
                    if(l.id == 'label') {
                    	l.className = 'label_check c_on';
                    	if (safari) {
    	                	inp.checked = false;
    	                }
                    }
                }
        	}
        }
        if (safari) {
            //inp.click();
        	inp.checked = false;
            //console.log(document.getElementById('mytest').checked);
        }
    } else {
    	that.className = 'label_check c_off';
        if(that.id == 'label') {
            for (var i = 0; i < ls.length; i++) {
                var l = ls[i];
                if(l.id != 'label') {
	                l.className = 'label_check c_off';
	                if (safari) {
	                	inp.checked = true;
	                }
                }
            }
        } else {
        	for (var i = 0; i < ls.length; i++) {
                var l = ls[i];
                if(l.id == 'label') {
                	l.className = 'label_check c_off';
                	if (safari) {
	                	inp.checked = true;
	                }
                }
            }
        }
        if (safari) {
            //inp.click();
            inp.checked = true; //也会触发事件冒泡
            //inp.setAttribute('checked', false);//不会事件冒泡，但是设置为true时无效。
            //console.log(inp);
            //console.log(this);
        }
    }
}

function turn_radio(e, label) {
    var inp = $(label).children("input").eq(0);
    if (label.className == 'label_radio r_off' || inp.checked) {
    	$(label).parent().children('label');
        var ls = $(label).parent().children('label');
        for (var i = 0; i < ls.length; i++) {
            var l = ls[i];
            if (l.className.indexOf('label_radio') == -1) {
                continue;
            };
            l.className = 'label_radio r_off';
        };
        label.className = 'label_radio r_on';
        if (safari) {
            inp.click();
        };
    } else {
    	label.className = 'label_radio r_off';
        if (safari) {
            inp.click();
        };
    }
}


/* Ajax查询油卡列表方法 */
function ajaxCardListQuery(param) {
	$(".pageSize").val('10');
	var pageParam = $queryBlock.find('.pageParam').serializeArray();
	if(param){
		pageParam = pageParam.concat(param);
	}
	$.ajax({
		url : ajaxCardListQueryUrl,
		data : pageParam,
		dataType : 'JSON',
		headers : {
			'grid' : 1
		}
	}).done(function(data) {
		data.totalPage = parseInt((data.total + data.pageSize - 1)/data.pageSize);
		$dataGrid.html(juicer(tplGrid, data));
		//checkbox
		var ls = $('label');
	    for (var i = 0; i < ls.length; i++) {
	        var l = ls[i];
	        if (l.className.indexOf('label_') == -1) {
	            continue;
	        }
	        var inp = $(l).children("input").eq(0);
	        if (l.className == 'label_check') {
	            l.className = (safari && inp.checked == true || inp.checked) ? 'label_check c_on' : 'label_check c_off';
	            $(l).click(function(e){
	            	check_it(e, this);
	            });
	        }
	        $(inp).click(function(e){
            	e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = true;
            });
	        if (l.className == 'label_radio') {
	            l.className = (safari && inp.checked == true || inp.checked) ? 'label_radio r_on' : 'label_radio r_off';
	            $(l).click(function(e){
	            	check_it(e, this);
	            });
	        }
	    }
	});
}