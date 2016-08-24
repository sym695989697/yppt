///***************yyyy-MM-dd 转成 毫秒数**********
 function showToDB(dataTime) {
	var arry = dataTime.split("-");
	var t=(parseInt(arry[1],10)-1);
	if(t<10){
		arry[1]='0'+t;
	}else{
		arry[1]=''+t;
	}
	var now = new Date(arry[0], arry[1], arry[2]);
	return now.getTime();
}
//扩展Date的format方法 
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
//Utc转换成时间格式
 function utcToDate(n_utc){
	 if (n_utc && n_utc.toString().length == 13 ){ 
	    var date = new Date();
	    date.setTime(parseInt(n_utc) + (8 * 3600 * 1000));
	    
	    var s = date.getUTCFullYear() + "-";
	    if ((date.getUTCMonth() + 1) < 10) {
	        s += "0" + (date.getUTCMonth() + 1) + "-";
	    }
	    else {
	        s += (date.getUTCMonth() + 1) + "-";
	    }
	    if (date.getUTCDate() < 10) {
	        s += "0" + date.getUTCDate();
	    }
	    else {
	        s += date.getUTCDate();
	    }
	    
	    return s;
	}else{
	  return "";	
	}
};
//时间格式转换成Utc
function dateToUtc(c_date){
    if (!c_date){
       return 1;
    }
        
    var tempArray = c_date.split("-");
    if (tempArray.length != 3) {
        alert("你输入的日期格式不正确,正确的格式:2000-05-01");
        return 0;
    }
    var date = new Date(tempArray[0], tempArray[1] - 1, tempArray[2], 23, 59, 59);
    return parseInt("" + date.getTime()); 
 };
/**  
*转换日期对象为日期字符串  
* @param date 日期对象  
* @param isFull 是否为完整的日期数据,  
*               为true时, 格式如"2000-03-05 01:05:04"  
*               为false时, 格式如 "2000-03-05"  
* @return 符合要求的日期字符串  
*/  
function getSmpFormatDate(date, isFull) {
    var pattern = "";
    if (isFull == true || isFull == undefined) {
        pattern = "yyyy-MM-dd hh:mm:ss";
    } else {
        pattern = "yyyy-MM-dd";
    }
    return getFormatDate(date, pattern);
}
/**  
*转换当前日期对象为日期字符串  
* @param date 日期对象  
* @param isFull 是否为完整的日期数据,  
*               为true时, 格式如"2000-03-05 01:05:04"  
*               为false时, 格式如 "2000-03-05"  
* @return 符合要求的日期字符串  
*/  

function getSmpFormatNowDate(isFull) {
    return getSmpFormatDate(new Date(), isFull);
}
/**  
*转换long值为日期字符串  
* @param l long值  
* @param isFull 是否为完整的日期数据,  
*               为true时, 格式如"2000-03-05 01:05:04"  
*               为false时, 格式如 "2000-03-05"  
* @return 符合要求的日期字符串  
*/  

function getSmpFormatDateByLong(l, isFull) {
    return getSmpFormatDate(new Date(l), isFull);
}
/**  
*转换long值为日期字符串  
* @param l long值  
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss  
* @return 符合要求的日期字符串  
*/  

function getFormatDateByLong(l, pattern) {
    return getFormatDate(new Date(l), pattern);
}
/**  
*转换日期对象为日期字符串  
* @param l long值  
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss  
* @return 符合要求的日期字符串  
*/  
function getFormatDate(date, pattern) {
    if (date == undefined) {
        date = new Date();
    }
    if (pattern == undefined) {
        pattern = "yyyy-MM-dd hh:mm:ss";
    }
    return date.format(pattern);
}

function getFormatDataToLong(mydate,isEnd)
{
	if (mydate == undefined || mydate == null || mydate.length<1) {
       return 0;
    }
	
	var dDate = new Date(Date.parse(mydate.replace(/-/g, "/")));
	var dDateTime=dDate.getTime();
	if(isEnd){
		dDateTime=dDateTime+86400000-1;
	}
	return dDateTime;
}
/**
 * 毫秒转换成时间格式
 * @param time
 * @returns {String}
 */
function getHHmmssTime_ByLong(time){
	
	var days=Math.floor(time/(24*3600*1000));
	//计算出小时数
	var leave1=time%(24*3600*1000);
	var hours=Math.floor(leave1/(3600*1000));
	//计算相差分钟数
	var leave2=leave1%(3600*1000);
	var minutes=Math.floor(leave2/(60*1000));
	//计算相差秒数
	var leave3=leave2%(60*1000);
	var seconds=Math.round(leave3/1000);
	hours += days*24;
	hours = hours < 10?("0"+hours):hours;
	minutes = minutes < 10?("0"+minutes):minutes;
	seconds = seconds < 10?("0"+seconds):seconds;
	return hours+":"+minutes+":"+seconds;
}

/**
 * 比较两个时间的大小(方法比较简单，有需要的可以进行扩展)
 * @param time1 time2
 * @returns {string}
 */
function dateCompare(time1,time2){
	if(time1 > time2){
		return "1";
	}else if(time1 == time2){
		return "1";
	}else{
		return "0";
	}
}

