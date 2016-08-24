/**
 * 信息显示窗口
 * 
 * @author  by malq 
 * @date    2012-06-11
 */
var JSWK = {
    clewBox: function(e, d, c) {
    	$("#JSWK_clewBoxDiv").remove();
        if (Jid("JSWK_clewBoxDiv")) {
            var b = "<span id='mode_clew' style='position:absolute;z-index: 10000;' class='clewBox_layer'>";
            b += "<span class='" + d + "'></span>" + e + "<span class='clew_end'></span>";
            b += "</span></div>";
            Jid("JSWK_clewBoxDiv").innerHTML = b;
            Jid("JSWK_clewBoxDiv").style.display = "";
        } else {
            var a = document.createElement("div");
            a.setAttribute("id", "JSWK_clewBoxDiv");
            a.className = "clewBox_layer_wrap";
            var b = "<span id='mode_clew' style='z-index: 10000;' class='clewBox_layer'>";
            b += "<span class='" + d + "'></span>" + e + "<span class='clew_end'></span>";
            b += "</span></div>";
            a.innerHTML = b;
            document.body.appendChild(a);
        }
        setTimeout(function() {
            if (Jid("JSWK_clewBoxDiv")) {
                Jid("JSWK_clewBoxDiv").style.display = "none";
            }
        },
        c ? c: 2000);
    },
    
	getRadio: function(id) {
		return "<div style=\"position:relative;top:30%;\"><input type=\"radio\" value=\""+id+"\" name=\"selectRadio\"  id=\"selectRadio\"/></div>";
	},
	getCheckbox: function(id) {
		return "<div style=\"position:relative;top:30%;\"><input type=\"checkbox\" value=\""+id+"\" name=\"selectCheckbox\" id=\"selectCheckbox\"/></div>";
	},
    
    setSelectValue: function(id, value) {
    	$('#'+id).val(value);  	
    	var text = $('#'+id).find("option:selected").text();
    	var newId = '#'+id+'_txt';
    	$(newId).val(text);
    }
};

function Jid(a) {
    if (typeof a == "string") {
        var b = document.getElementById(a);
        //b = JSWK.nodeProtoType(b);
        return b;
    } else {
        return a;
    }
}

