/**
 * 插件集合
 * date：6.30.2014
 * author:龚法曾
 * dialog api:http://www.baidufe.com/component/jDialog/api.html
 * juicer api:http://juicer.name/docs/docs_zh_cn.html
 * cookie api:https://github.com/carhartl/jquery-cookie
 * placeholder api:https://github.com/mathiasbynens/jquery-placeholder
 * messager api：http://www.loveweb8.com/plus/demo.php?aid=113
 **/
/*dialog 弹出框插件*/
var jDrag=function(c,u){var n=function(f){if(f.handle){var e=c.extend({},f),m=c(e.handle),q=e.target&&c(e.target),n=null,p=(e.type||"").toString().toUpperCase();m[0].onselectstart=function(){return!1};m.attr("unselectable","on").css("MozUserSelect","none");var a=null,b=null,d=function(a){var d=a.pageX-b[0],g=a.pageY-b[1];if(q){var h={};"Y"!==p&&(h.left=n.left+d);"X"!==p&&(h.top=n.top+g);c.isEmptyObject(h)||q.css(h)}if(c.isFunction(e.onMove))e.onMove(a,d,g)},r=function(b){c(document).unbind("mousemove",d).unbind("mouseup",r);a=!1;if(c.isFunction(e.onUp))e.onUp(b)},t=function(l){a&&r();if(c.isFunction(e.onDown))e.onDown(l);q&&(n={left:q[0].offsetLeft,top:q[0].offsetTop});b=[l.pageX,l.pageY];c(document).bind("mousemove",d).bind("mouseup",r)};e.handle.bind("mousedown",t);this.remove=function(){c(document).unbind("mousemove",d);c(document).unbind("mousemove",r);c(document).unbind("mousedown",t)};this.destroy=function(){m.unbind("mousedown",t)}}};return{init:function(c){return new n(c)}}}(jQuery),jMask=function(c,u){var n=function(f){var e=this;this.cfg=c.extend({zIndex:1024,resizable:!0},f);this.element=c('<div class="j-dialog-mask '+(this.cfg.className||"")+'"/>').appendTo(document.body).css({display:"none",zIndex:this.cfg.zIndex,width:this.width(),height:this.height()});this.cfg.show&&this.show();this.resizeFunc=function(){e.css("width",e.width());e.css("height",e.height());e.triggerHandler("resize")};this.cfg.resizable&&c(window).bind("resize",this.resizeFunc)};n.prototype={constructor:n,show:function(){this.element.show.apply(this.element,arguments);this._processTages(1)},hide:function(){this.element.hide.apply(this.element,arguments);this._processTages(0)},width:function(){return c("body").width()},height:function(){return Math.max(c("body").height(),c(window).height())},css:function(){this.element.css.apply(this.element,arguments)},triggerHandler:function(){this.element.triggerHandler.apply(this.element,arguments)},bind:function(){this.element.bind.apply(this.element,arguments)},remove:function(){this._processTages(0);this.element&&this.element.remove();c(window).unbind("resize",this.resizeFunc);for(var f in this)delete this[f]},_processTages:function(f){var e=this,m=navigator.userAgent.toLowerCase();if(/msie/.test(m)&&!/opera/.test(m))if(e.special=e.special||[],f)0<e.special.length||(f=c("SELECT"),this.cfg.safety&&(f=f.filter(function(c){return 0==e.cfg.safety.find(this).length})),f.each(function(){var f=c(this);e.special.push({dom:this,css:f.css("visibility")});f.css("visibility","hidden")}));else for(f=0,m=e.special.length;f<m;f++)c(e.special[f].dom).css("visibility",e.special[f].css||""),e.special[f].dom=null}};return{init:function(c){return new n(c)}}}(jQuery),jDialog=function(c,u){var n=function(){var a=navigator.userAgent.toLowerCase();return{version:(a.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/)||[])[1],safari:/webkit/.test(a),opera:/opera/.test(a),msie:/msie/.test(a)&&!/opera/.test(a),mozilla:/mozilla/.test(a)&&!/(compatible|webkit)/.test(a)}}(),f=n.msie&&6==parseInt(n.version,10),e="show close resize hide enterKey escKey".split(" "),m=[],q={modal:!0,title:null,content:null,width:300,height:null,minWidth:200,minHeight:60,maxWidth:null,maxHeight:null,padding:"10px",fixed:!0,left:null,top:null,closeable:!0,hideOnClose:!1,draggable:!0,contentType:null,zIndex:1024,resizable:!1,autoShow:!0,autoMiddle:!0,autoClose:0,showShadow:!0,showTitle:!0,textAlign:"inherit",buttonAlign:"right",dialogClassName:null,maskClassName:null,wobbleEnable:!1,closeOnBodyClick:!1,stopScroll:!0,buttons:[],events:{}},s=function(a){m.push(this);this.cfg=c.extend({},q,a);this.dom={element:null,buttons:[]};this._init()};s.prototype={constructor:s,_tempVars:{},title:function(a){return(a||"").length?(this.dom.element.find(".j-dialog-title>span.j-dialog-txt").html(a||""),this):this.dom.element.find(".j-dialog-title>span.j-dialog-txt").html()},content:function(a){return(a||"").length?(this.dom.element.find(".j-dialog-body").html(a).css({"text-align":this.cfg.textAlign}),this):c(".j-dialog-body",this.dom.element).html()},width:function(a){return 0<=parseInt(a,10)?(this.dom.element.css("width",a),this):parseInt(this.dom.element.css("width"),10)},height:function(a){if(a=parseInt(a,10)||0){var b=0,d=(this.cfg.padding||"").split(/\s+/);switch(d.length){case 4:case 3:b+=(parseInt(d[0],10)||0)+(parseInt(d[2],10)||0);break;case 2:case 1:b+=2*(parseInt(d[0],10)||0)}b+=35;this.cfg.minHeight&&(a=Math.max(a,this.cfg.minHeight));this.cfg.maxHeight&&(a=Math.min(a,this.cfg.maxHeight));a-=b;c(".j-dialog-body",this.dom.element).css("height",a);return this}return parseInt(this.dom.element.css("height"),10)},middle:function(){var a=c(document),b=c(window),d=this.cfg.fixed&&!f?[0,0]:[a.scrollLeft(),a.scrollTop()],a=d[0]+(b.width()-this.dom.element.outerWidth())/2,b=d[1]+(b.height()-this.dom.element.outerHeight())/2;return this.position({left:a,top:0<=b?b:0})},position:function(a){return a.left&&!isNaN(parseInt(a.left,10))||a.top&&!isNaN(parseInt(a.top,10))?(!a.left||isNaN(parseInt(a.left,10))?this.dom.element.css({top:a.top}):!a.top||isNaN(parseInt(a.top,10))?this.dom.element.css({left:a.left}):this.dom.element.css({left:a.left,top:a.top}),this.triggerHandler("resize"),this):this.dom.element.offset()},show:function(){this.dom.element.show.apply(this.dom.element,arguments);this.mask&&(this.mask.cfg.safety=this.dom.element,this.mask.show.apply(this.mask,arguments));if(this.cfg.autoClose){var a=this;setTimeout(function(){a.close()},parseInt(this.cfg.autoClose,10)||3E3)}if(this.cfg.stopScroll)c(document).on("mousewheel",function(){return!1});this.triggerHandler("show");return this},hide:function(){this.dom.element.hide();this.mask&&this.mask.hide.apply(this.mask,arguments);this.cfg.stopScroll&&c(document).off("mousewheel");return this},close:function(){if(!this.dom.element[0])return this;this.triggerHandler("close");c(window).unbind("resize",this._tempVars.onResize);this.cfg.stopScroll&&c(document).off("mousewheel");if(this.cfg.hideOnClose)return this.hide(),this;this.mask&&this.mask.remove();this._tempVars.dragObj&&this._tempVars.dragObj.remove();this.dom.element.remove();for(var a=0,b=m.length;a<b;a++)if(m[a]==this){m.splice(a,1);break}return this},buttons:function(a){var b=this;if(a&&0<a.length){this.cfg.buttons=a;var d=[];c.each(a,function(a,b){var c="j-dialog-btn";"highlight"==b.type&&(c+=" x-highlight");d.push('<input type="button" class="'+c+'" value="'+(b.text||"\u786e\u5b9a")+'" />')});var r=c('<div class="j-dialog-buttons"></div>').appendTo(this.dom.element).html(d.join(""));r.css("text-align",this.cfg.buttonAlign||"right");var e=this.dom.buttons=c("input[type=button]",r);c.each(a,function(a,d){var g=c(e[a]);g.click(function(a){d.handler&&"function"==typeof d.handler&&d.handler.call(g[0],g,b)});f||g.hover(function(a){a="x-hover";g.hasClass("x-highlight")&&(a="x-hlhover");g.addClass(a)},function(a){g.removeClass("x-hover").removeClass("x-hlhover")})});return this}return this.dom.buttons},bind:function(){this.dom.element.bind.apply(this.dom.element,arguments);return this},triggerHandler:function(){this.dom.element.trigger.apply(this.dom.element,arguments);return this},_init:function(){var a=this;this.cfg.showTitle||(this.cfg.draggable=!1);isNaN(parseInt(this.cfg.top))&&isNaN(parseInt(this.cfg.left))&&!this.cfg.anchor||(this.cfg.autoMiddle=!1);var b="j-dialog "+(this.cfg.dialogClassName?this.cfg.dialogClassName:"");!f&&this.cfg.fixed&&(b+=" j-dialog-fix");this.cfg.showShadow&&(b+=" j-dialog-shadow");this._addMask();this.dom.element=c('<div class="'+b+'"></div>').css({zIndex:this.cfg.zIndex,display:"none"}).appendTo(document.body).focus();this._setupTitleBar().title(this.cfg.title);this._setupContent().content(this.cfg.content);this.buttons(this.cfg.buttons);this.width(this.cfg.width);this.height(this.cfg.height);c("#j-dialog-body",this.dom.element).css("padding",this.cfg.padding);c.each(e,function(b,c){a.cfg.events[c]&&a.dom.element.bind(c,{dialog:a},a.cfg.events[c])});this._setupEscKey()._setupEnterKey()._setupBodyClick();this.cfg.autoShow&&this.show();if(this.cfg.anchor){this._tempVars.domAnchor=c(this.cfg.anchor.target);if(!this._tempVars.domAnchor[0])throw Error('The "anchor.target" must be a HTMLElement instance!');this._setupTriangle()}else this.cfg.autoMiddle?this.middle():this.position({left:this.cfg.left,top:this.cfg.top});this._tempVars.onResize=function(){c(window).unbind("resize",a._tempVars.onResize);a.cfg.autoMiddle&&a.middle();a.triggerHandler("resize");a.sizeTimer=setTimeout(function(){c(window).bind("resize",a._tempVars.onResize)},10)};c(window).bind("resize",this._tempVars.onResize)},_addMask:function(){var a=this;if(this.cfg.modal){var b={};this.cfg.maskClassName&&(b.className=this.cfg.maskClassName);b.zIndex=this.cfg.zIndex;this.mask=jMask.init(b);this.cfg.wobbleEnable&&(this._tempVars.cssAnimationGoing=!1,this.mask.element.click(function(b){if(a._tempVars.cssAnimationGoing)return!1;a.dom.element.addClass("j-ani-wobble");a._tempVars.cssAnimationGoing=!0;setTimeout(function(){a.dom.element.removeClass("j-ani-wobble");a._tempVars.cssAnimationGoing=!1},1100)}))}return a},_setupBodyClick:function(){var a=this;if(!a.cfg.closeOnBodyClick)return this;var b=function(b){c.contains(a.dom.element[0],b.target)||a.close()};a.bind("show",function(a){c(document).bind("mousedown",b)});c.each(["hide","close"],function(d,e){a.bind(e,function(a){c(document).unbind("mousedown",b)})});return a},_setupTitleBar:function(){var a=this;if(this.cfg.closeable){var b=c('<a href="#" class="j-dialog-close" title="\u5173\u95ed">&nbsp;</a>').appendTo(this.dom.element).bind({click:function(b){a.cfg.hideOnClose?a.hide():a.close();b.preventDefault();b.stopPropagation()},mousedown:function(a){a.preventDefault();a.stopPropagation()}});this.cfg.showTitle||b.addClass("btn-without-title")}if(!this.cfg.showTitle)return a;this._tempVars.titleBar=c('<div class="j-dialog-title"><span class="j-dialog-txt"></span></div>').appendTo(this.dom.element);this.cfg.draggable&&(this._tempVars.titleBar.addClass("j-draggable"),this._tempVars.dragObj=jDrag.init({handle:a._tempVars.titleBar,target:a.dom.element,onDown:function(){a._setupHackDiv(1);a.dom.element.addClass("j-user-select")},onUp:function(){a._setupHackDiv(0);a.dom.element.removeClass("j-user-select")}}));return a},_setupHackDiv:function(a){a?0<c("IFRAME",this.dom.element).length&&(a=c(".j-dialog-content",this.dom.element),this.hack_div=(this.hack_div||c("<div></div>")).appendTo(a).css({position:"absolute",left:0,top:0,cursor:"move",display:"block",width:this.dom.element.outerWidth(),height:this.dom.element.outerHeight()})):this.hack_div&&this.hack_div.css("display","none");return this},_setupEscKey:function(){var a=this,b=function(b){27==b.keyCode&&a.triggerHandler("escKey")};c(a.dom.element).bind("show",function(a){c(document).bind("keydown",b)});c.each(["hide","close"],function(d,e){c(a.dom.element).bind(e,function(a){c(document).unbind("keydown",b)})});return a},_setupEnterKey:function(){var a=this,b=function(b){13!=b.keyCode&&10!=b.keyCode||a.triggerHandler("enterKey")};c(a.dom.element).bind("show",function(a){c(document).bind("keydown",b)});c.each(["hide","close"],function(d,e){c(a.dom.element).bind(e,function(a){c(document).unbind("keydown",b)})});return a},_setupTriangle:function(){this._tempVars.triangle=c('<div class="j-triangle"><div class="t-border"></div><div class="t-inset"></div></div>').appendTo(this.dom.element).addClass("anchor-"+{left:"right",right:"left",top:"bottom",bottom:"top"}[{left:"left","left-top":"left","left-bottom":"left",top:"top","top-left":"top","top-right":"top",right:"right","right-top":"right","right-bottom":"right",bottom:"bottom","bottom-left":"bottom","bottom-right":"bottom"}[this.cfg.anchor.position||"right"]]);var a={},b={},d=this._tempVars.domAnchor.offset(),e=parseInt(this._tempVars.domAnchor.width(),10),f=parseInt(this._tempVars.domAnchor.height(),10),l=parseInt(this.width(),10),k=parseInt(this.height(),10),g=c.extend({top:0,left:0,right:0,bottom:0},this.cfg.anchor.offset||{}),h=parseInt(this.cfg.anchor.trianglePosFromStart,10)||0;switch((this.cfg.anchor.position||"right").toLowerCase()){case "top":a={top:0,left:h?h:(l-24)/2};b={top:d.top-k-12-g.top,left:d.left+(e-l)/2+g.left};break;case "top-left":a={top:0,left:h?h:(l-24)/2};b={top:d.top-k-12-g.top,left:d.left+g.left};break;case "top-right":a={top:0,left:h?l-24-h:(l-24)/2};b={top:d.top-k-12-g.top,left:d.left+e-l+g.right};break;case "right":a={top:h?h-k:-(k+24)/2,left:-24};b={top:d.top+(f-k)/2+g.top,left:d.left+e+12+g.right};break;case "right-top":a={top:h?h-k:-(k+24)/2,left:-24};b={top:d.top+g.top,left:d.left+e+12+g.right};break;case "right-bottom":a={top:h?-24-h:-(k+24)/2,left:-24};b={top:d.top+f-k-g.bottom,left:d.left+e+12+g.right};break;case "bottom":a={top:-(k+24),left:h?h:(l-24)/2};b={top:d.top+f+12+g.bottom,left:d.left+(e-l)/2+g.left};break;case "bottom-left":a={top:-(k+24),left:h?h:(l-24)/2};b={top:d.top+f+12+g.bottom,left:d.left+g.left};break;case "bottom-right":a={top:-(k+24),left:h?l-24-h:(l-24)/2};b={top:d.top+f+12+g.bottom,left:d.left+e-l+g.right};break;case "left":a={top:h?h-k:-(k+24)/2,left:l};b={top:d.top+(f-k)/2+g.top,left:d.left-l-12-g.left};break;case "left-top":a={top:h?h-k:-(k+24)/2,left:l};b={top:d.top+g.top,left:d.left-l-12-g.left};break;case "left-bottom":a={top:h?-24-h:-(k+24)/2,left:l},b={top:d.top+f-k-g.bottom,left:d.left-l-12-g.left}}this._tempVars.triangle.css(a);this.position(b);this.dom.element.css("overflow","visible");return this},_setupContent:function(){"iframe"===this.cfg.contentType&&(this.cfg.content=c("<iframe></iframe>").css({width:"100%",height:"100%",border:"none"}).attr({src:this.cfg.content,frameBorder:0}));var a=c('<div class="j-dialog-content"><div class="j-dialog-body" id="j-dialog-body"></div></div>'),b=this;this.dom.element.append(a);if(this.cfg.resizable){var d=c(".j-dialog-content",b.dom.element);this.cfg.minWidth=this.cfg.minWidth||0;this.cfg.minHeight=this.cfg.minHeight||0;c.each(["es"],function(a,e){var f=c('<div class="resizable-'+e+'"><div></div></div>');b.dom.element.append(f);var k=null,g=null;jDrag.init({handle:f,onDown:function(a){b._setupHackDiv(1);k=parseInt(b.dom.element.width());g=parseInt(d.height());b.dom.element.addClass("j-user-select")},onMove:function(a,c,e){a=k+c;var f=g+e;b.cfg.minWidth&&a<b.cfg.minWidth&&0>c||b.cfg.maxWidth&&a>b.cfg.maxWidth&&0<c||b.dom.element.width(a);b.cfg.minHeight&&f<b.cfg.minHeight&&0>e||b.cfg.maxHeight&&f>b.cfg.maxHeight&&0<e||d.height(f);c=b.dom.element.outerWidth();e=b.dom.element.outerHeight();b.hack_div&&b.hack_div.css({width:c,height:e})},onUp:function(a){b._setupHackDiv(0);b.dom.element.removeClass("j-user-select")}})})}return b}};var p=function(a){a=c.extend({},a||{});return new s(a)};return{version:"1.3",dialog:p,alert:function(a,b,d){d=d||{};b=c.extend({type:"highlight",handler:function(a,b){b.close()}},b||{});d=c.extend({wobbleEnable:!0},d,{content:a,buttons:[].concat(b),title:d.title?d.title:"\u63d0\u793a"});return p(d)},confirm:function(a,b,d,e){e=e||{};b=c.extend({type:"highlight",handler:function(a,b){b.close()}},b||{});d=c.extend({text:"\u53d6\u6d88",handler:function(a,b){b.close()}},d||{});e=c.extend({wobbleEnable:!0},e,{content:a,buttons:[].concat([b,d]),title:e.title?e.title:"\u786e\u8ba4"});return p(e)},message:function(a,b){b=b||{};b=c.extend({content:a,padding:"20px 10px 20px 10px",textAlign:"center"},b,{showTitle:!1});return p(b)},tip:function(a,b,d){d=d||{};d=c.extend({padding:"20px 10px 20px 10px",textAlign:"center",width:"auto",anchor:{target:null,position:"right"}},d,{content:a,anchor:b,showTitle:!1,showShadow:!1,modal:!1,fixed:!1});return p(d)},iframe:function(a,b){b=b||{};b=c.extend({content:a,title:"\u7a97\u53e3",width:600,height:300},b,{contentType:"iframe"});return p(b);}}}(jQuery);
/*juicer 模板引擎 插件*/
(function(){var c=function(){var e=[].slice.call(arguments);e.push(c.options);if(e[0].match(/^\s*#([\w:\-\.]+)\s*$/igm)){e[0].replace(/^\s*#([\w:\-\.]+)\s*$/igm,function(h,i){var f=document;var g=f&&f.getElementById(i);e[0]=g?(g.value||g.innerHTML):h;});}if(arguments.length==1){return c.compile.apply(c,e);}if(arguments.length>=2){return c.to_html.apply(c,e);}};var d={escapehash:{"<":"&lt;",">":"&gt;","&":"&amp;",'"':"&quot;","'":"&#x27;","/":"&#x2f;"},escapereplace:function(e){return d.escapehash[e];},escaping:function(e){return typeof(e)!=="string"?e:e.replace(/[&<>"]/igm,this.escapereplace);},detection:function(e){return typeof(e)==="undefined"?"":e;}};var b=function(e){if(typeof(console)!=="undefined"){if(console.warn){console.warn(e);return;}if(console.log){console.log(e);return;}}throw (e);};var a=function(h,f){h=h!==Object(h)?{}:h;if(h.__proto__){h.__proto__=f;return h;}var g=function(){};var j=Object.create?Object.create(f):new (g.prototype=f,g);for(var e in h){if(h.hasOwnProperty(e)){j[e]=h[e];}}return j;};c.__cache={};c.version="0.6.5-stable";c.settings={};c.tags={operationOpen:"{@",operationClose:"}",interpolateOpen:"\\${",interpolateClose:"}",noneencodeOpen:"\\$\\${",noneencodeClose:"}",commentOpen:"\\{#",commentClose:"\\}"};c.options={cache:true,strip:true,errorhandling:true,detection:true,_method:a({__escapehtml:d,__throw:b,__juicer:c},{})};c.tagInit=function(){var f=c.tags.operationOpen+"each\\s*([^}]*?)\\s*as\\s*(\\w*?)\\s*(,\\s*\\w*?)?"+c.tags.operationClose;var h=c.tags.operationOpen+"\\/each"+c.tags.operationClose;var i=c.tags.operationOpen+"if\\s*([^}]*?)"+c.tags.operationClose;var j=c.tags.operationOpen+"\\/if"+c.tags.operationClose;var n=c.tags.operationOpen+"else"+c.tags.operationClose;var o=c.tags.operationOpen+"else if\\s*([^}]*?)"+c.tags.operationClose;var k=c.tags.interpolateOpen+"([\\s\\S]+?)"+c.tags.interpolateClose;var l=c.tags.noneencodeOpen+"([\\s\\S]+?)"+c.tags.noneencodeClose;var m=c.tags.commentOpen+"[^}]*?"+c.tags.commentClose;var g=c.tags.operationOpen+"each\\s*(\\w*?)\\s*in\\s*range\\(([^}]+?)\\s*,\\s*([^}]+?)\\)"+c.tags.operationClose;var e=c.tags.operationOpen+"include\\s*([^}]*?)\\s*,\\s*([^}]*?)"+c.tags.operationClose;c.settings.forstart=new RegExp(f,"igm");c.settings.forend=new RegExp(h,"igm");c.settings.ifstart=new RegExp(i,"igm");c.settings.ifend=new RegExp(j,"igm");c.settings.elsestart=new RegExp(n,"igm");c.settings.elseifstart=new RegExp(o,"igm");c.settings.interpolate=new RegExp(k,"igm");c.settings.noneencode=new RegExp(l,"igm");c.settings.inlinecomment=new RegExp(m,"igm");c.settings.rangestart=new RegExp(g,"igm");c.settings.include=new RegExp(e,"igm");};c.tagInit();c.set=function(f,j){var h=this;var e=function(i){return i.replace(/[\$\(\)\[\]\+\^\{\}\?\*\|\.]/igm,function(l){return"\\"+l;});};var k=function(l,m){var i=l.match(/^tag::(.*)$/i);if(i){h.tags[i[1]]=e(m);h.tagInit();return;}h.options[l]=m;};if(arguments.length===2){k(f,j);return;}if(f===Object(f)){for(var g in f){if(f.hasOwnProperty(g)){k(g,f[g]);}}}};c.register=function(g,f){var e=this.options._method;if(e.hasOwnProperty(g)){return false;}return e[g]=f;};c.unregister=function(f){var e=this.options._method;if(e.hasOwnProperty(f)){return delete e[f];}};c.template=function(e){var f=this;this.options=e;this.__interpolate=function(g,l,i){var h=g.split("|"),k=h[0]||"",j;if(h.length>1){g=h.shift();j=h.shift().split(",");k="_method."+j.shift()+".call({}, "+[g].concat(j)+")";}return"<%= "+(l?"_method.__escapehtml.escaping":"")+"("+(!i||i.detection!==false?"_method.__escapehtml.detection":"")+"("+k+")) %>";};this.__removeShell=function(h,g){var i=0;h=h.replace(c.settings.forstart,function(n,k,m,l){var m=m||"value",l=l&&l.substr(1);var j="i"+i++;return"<% ~function() {for(var "+j+" in "+k+") {if("+k+".hasOwnProperty("+j+")) {var "+m+"="+k+"["+j+"];"+(l?("var "+l+"="+j+";"):"")+" %>";}).replace(c.settings.forend,"<% }}}(); %>").replace(c.settings.ifstart,function(j,k){return"<% if("+k+") { %>";}).replace(c.settings.ifend,"<% } %>").replace(c.settings.elsestart,function(j){return"<% } else { %>";}).replace(c.settings.elseifstart,function(j,k){return"<% } else if("+k+") { %>";}).replace(c.settings.noneencode,function(k,j){return f.__interpolate(j,false,g);}).replace(c.settings.interpolate,function(k,j){return f.__interpolate(j,true,g);}).replace(c.settings.inlinecomment,"").replace(c.settings.rangestart,function(m,l,n,k){var j="j"+i++;return"<% ~function() {for(var "+j+"="+n+";"+j+"<"+k+";"+j+"++) {{var "+l+"="+j+"; %>";}).replace(c.settings.include,function(l,j,k){return"<%= _method.__juicer("+j+", "+k+"); %>";});if(!g||g.errorhandling!==false){h="<% try { %>"+h;h+='<% } catch(e) {_method.__throw("Juicer Render Exception: "+e.message);} %>';}return h;};this.__toNative=function(h,g){return this.__convert(h,!g||g.strip);};this.__lexicalAnalyze=function(k){var j=[];var o=[];var n="";var g=["if","each","_","_method","console","break","case","catch","continue","debugger","default","delete","do","finally","for","function","in","instanceof","new","return","switch","this","throw","try","typeof","var","void","while","with","null","typeof","class","enum","export","extends","import","super","implements","interface","let","package","private","protected","public","static","yield","const","arguments","true","false","undefined","NaN"];var m=function(r,q){if(Array.prototype.indexOf&&r.indexOf===Array.prototype.indexOf){return r.indexOf(q);}for(var p=0;p<r.length;p++){if(r[p]===q){return p;}}return -1;};var h=function(p,i){i=i.match(/\w+/igm)[0];if(m(j,i)===-1&&m(g,i)===-1&&m(o,i)===-1){if(typeof(window)!=="undefined"&&typeof(window[i])==="function"&&window[i].toString().match(/^\s*?function \w+\(\) \{\s*?\[native code\]\s*?\}\s*?$/i)){return p;}if(typeof(global)!=="undefined"&&typeof(global[i])==="function"&&global[i].toString().match(/^\s*?function \w+\(\) \{\s*?\[native code\]\s*?\}\s*?$/i)){return p;}if(typeof(c.options._method[i])==="function"||c.options._method.hasOwnProperty(i)){o.push(i);return p;}j.push(i);}return p;};k.replace(c.settings.forstart,h).replace(c.settings.interpolate,h).replace(c.settings.ifstart,h).replace(c.settings.elseifstart,h).replace(c.settings.include,h).replace(/[\+\-\*\/%!\?\|\^&~<>=,\(\)\[\]]\s*([A-Za-z_]+)/igm,h);for(var l=0;l<j.length;l++){n+="var "+j[l]+"=_."+j[l]+";";}for(var l=0;l<o.length;l++){n+="var "+o[l]+"=_method."+o[l]+";";}return"<% "+n+" %>";};this.__convert=function(h,i){var g=[].join("");g+="'use strict';";g+="var _=_||{};";g+="var _out='';_out+='";if(i!==false){g+=h.replace(/\\/g,"\\\\").replace(/[\r\t\n]/g," ").replace(/'(?=[^%]*%>)/g,"\t").split("'").join("\\'").split("\t").join("'").replace(/<%=(.+?)%>/g,"';_out+=$1;_out+='").split("<%").join("';").split("%>").join("_out+='")+"';return _out;";return g;}g+=h.replace(/\\/g,"\\\\").replace(/[\r]/g,"\\r").replace(/[\t]/g,"\\t").replace(/[\n]/g,"\\n").replace(/'(?=[^%]*%>)/g,"\t").split("'").join("\\'").split("\t").join("'").replace(/<%=(.+?)%>/g,"';_out+=$1;_out+='").split("<%").join("';").split("%>").join("_out+='")+"';return _out.replace(/[\\r\\n]\\s+[\\r\\n]/g, '\\r\\n');";return g;};this.parse=function(h,g){var i=this;if(!g||g.loose!==false){h=this.__lexicalAnalyze(h)+h;}h=this.__removeShell(h,g);h=this.__toNative(h,g);this._render=new Function("_, _method",h);this.render=function(k,j){if(!j||j!==f.options._method){j=a(j,f.options._method);}return i._render.call(this,k,j);};return this;};};c.compile=function(g,f){if(!f||f!==this.options){f=a(f,this.options);}try{var h=this.__cache[g]?this.__cache[g]:new this.template(this.options).parse(g,f);if(!f||f.cache!==false){this.__cache[g]=h;}return h;}catch(i){b("Juicer Compile Exception: "+i.message);return{render:function(){}};}};c.to_html=function(f,g,e){if(!e||e!==this.options){e=a(e,this.options);}return this.compile(f,e).render(g,e._method);};typeof(module)!=="undefined"&&module.exports?module.exports=c:this.juicer=c;})();
/*cookie 插件*/
(function(a){if(typeof define==='function'&&define.amd){define(['jquery'],a)}else if(typeof exports==='object'){a(require('jquery'))}else{a(jQuery)}}(function($){var k=/\+/g;function encode(s){return m.raw?s:encodeURIComponent(s)}function decode(s){return m.raw?s:decodeURIComponent(s)}function stringifyCookieValue(a){return encode(m.json?JSON.stringify(a):String(a))}function parseCookieValue(s){if(s.indexOf('"')===0){s=s.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,'\\')}try{s=decodeURIComponent(s.replace(k,' '));return m.json?JSON.parse(s):s}catch(e){}}function read(s,a){var b=m.raw?s:parseCookieValue(s);return $.isFunction(a)?a(b):b}var m=$.cookie=function(a,b,c){if(b!==undefined&&!$.isFunction(b)){c=$.extend({},m.defaults,c);if(typeof c.expires==='number'){var d=c.expires,t=c.expires=new Date();t.setTime(+t+d*864e+5)}return(document.cookie=[encode(a),'=',stringifyCookieValue(b),c.expires?'; expires='+c.expires.toUTCString():'',c.path?'; path='+c.path:'',c.domain?'; domain='+c.domain:'',c.secure?'; secure':''].join(''))}var e=a?undefined:{};var f=document.cookie?document.cookie.split('; '):[];for(var i=0,l=f.length;i<l;i++){var g=f[i].split('=');var h=decode(g.shift());var j=g.join('=');if(a&&a===h){e=read(j,b);break}if(!a&&(j=read(j))!==undefined){e[h]=j}}return e};m.defaults={};$.removeCookie=function(a,b){if($.cookie(a)===undefined){return false}$.cookie(a,'',$.extend({},b,{expires:-1}));return!$.cookie(a)}}));
/*ie9以下模拟placeholder执行*/
(function(m,g,d){function r(b){var a={},c=/^jQuery\d+$/;d.each(b.attributes,function(b,d){d.specified&&!c.test(d.name)&&(a[d.name]=d.value)});return a}function h(b,a){var c=d(this);if(this.value==c.attr("placeholder")&&c.hasClass("placeholder"))if(c.data("placeholder-password")){c=c.hide().next().show().attr("id",c.removeAttr("id").data("placeholder-id"));if(!0===b)return c[0].value=a;c.focus()}else this.value="",c.removeClass("placeholder"),this==n()&&this.select()}function l(){var b,a=d(this),c=this.id;if(""==this.value){if("password"==this.type){if(!a.data("placeholder-textinput")){try{b=a.clone().attr({type:"text"})}catch(e){b=d("<input>").attr(d.extend(r(this),{type:"text"}))}b.removeAttr("name").data({"placeholder-password":a,"placeholder-id":c}).bind("focus.placeholder",h);a.data({"placeholder-textinput":b,"placeholder-id":c}).before(b)}a=a.removeAttr("id").hide().prev().attr("id",c).show()}a.addClass("placeholder");a[0].value=a.attr("placeholder")}else a.removeClass("placeholder")}function n(){try{return g.activeElement}catch(b){}}var f="[object OperaMini]"==Object.prototype.toString.call(m.operamini),k="placeholder"in g.createElement("input")&&!f,f="placeholder"in g.createElement("textarea")&&!f,e=d.fn,p=d.valHooks,q=d.propHooks;k&&f?(e=e.placeholder=function(){return this},e.input=e.textarea=!0):(e=e.placeholder=function(){this.filter((k?"textarea":":input")+"[placeholder]").not(".placeholder").bind({"focus.placeholder":h,"blur.placeholder":l}).data("placeholder-enabled",!0).trigger("blur.placeholder");return this},e.input=k,e.textarea=f,e={get:function(b){var a=d(b),c=a.data("placeholder-password");return c?c[0].value:a.data("placeholder-enabled")&&a.hasClass("placeholder")?"":b.value},set:function(b,a){var c=d(b),e=c.data("placeholder-password");if(e)return e[0].value=a;if(!c.data("placeholder-enabled"))return b.value=a;""==a?(b.value=a,b!=n()&&l.call(b)):c.hasClass("placeholder")?h.call(b,!0,a)||(b.value=a):b.value=a;return c}},k||(p.input=e,q.value=e),f||(p.textarea=e,q.value=e),d(function(){d(g).delegate("form","submit.placeholder",function(){var b=d(".placeholder",this).each(h);setTimeout(function(){b.each(l)},10)})}),d(m).bind("beforeunload.placeholder",function(){d(".placeholder").each(function(){this.value=""})}))})(this,document,jQuery);
/*addrDropmenu 插件 made by gfz*/
(function(c){c.fn.addrDropmenu=function(g){function n(a){var c="";if(a)for(var d in a)for(var e in a[d])c+='<li><a c="'+e+'">'+a[d][e]+"</a></li>";return c}var b={initTabStr:"\u8bf7\u9009\u62e9",delyTime:50,overTime:600,level:3},b=c.extend({},b,g),d=b.initTabStr,D=b.delyTime,E=b.overTime,w=b.level;g=['<div class="addr"><em>'+d+"</em><i></i></div>",'<dl class="dn"><dt>','<a class="tab crt"><em>'+d+"</em><i></i></a>",'<a class="tab dn"><em>'+d+"</em><i></i></a>",'<a class="tab dn"><em>'+d+"</em><i></i></a>",'<a class="close" title="\u5173\u95ed"></a><a class="reset">\u91cd\u7f6e</a></dt><dd class="dib"></dd><dd class="dib dn"></dd><dd class="dib dn"></dd></dl>'].join("");this.addClass("addrselector");this.prepend(g);this.find("dd:first").html(n(postCodeJson["00"]));return this.each(function(){var a=c(this),h=a.find(".addr"),b=a.find("dl"),e=a.find("dt"),l=e.find(".tab"),m=a.find("dd"),f=a.find("input:hidden"),g=l.eq(0),x=l.eq(1),p=l.eq(2),a=m.eq(0),q=m.eq(1),r=m.eq(2),y=f.eq(0),s=f.eq(1),z=f.eq(2),t=f.eq(3),u=f.eq(4),v=f.eq(5),A=y.val(),B=z.val(),C=u.val();e.on("click",".close",function(){c(this).text();var k=s.val()+t.val()+v.val();h.removeClass("hover").find("em").text(k||d);b.hide(D)});a.on("click","a",function(){var k=c(this).attr("c"),a=c(this).text();f.val("");h.find("em").text(a);y.val(k);s.val(a);p.addClass("dn");g.find("em").text(a);1!=w?(k=n(postCodeJson[k]),q.html(k),x.trigger("click").find("em").text(d)):e.find(".close").trigger("click")});q.on("click","a",function(){var a=c(this).attr("c"),b=c(this).text(),f=u.add(v);x.find("em").text(b);z.val(a);t.val(b);f.val("");b=s.val()+t.val();h.find("em").text(b);2!=w?(a=n(postCodeJson[a]))?(r.html(a),p.trigger("click").find("em").text(d)):e.find(".close").trigger("click"):e.find(".close").trigger("click")});r.on("click","a",function(){var a=c(this).attr("c"),b=c(this).text();p.find("em").text(b);u.val(a);v.val(b);e.find(".close").trigger("click")});e.on("click",".tab",function(){var a=c(this),b=l.index(this);a.removeClass("dn").addClass("crt").siblings().removeClass("crt");m.addClass("dn").eq(b).removeClass("dn")});A&&a.find("a[c="+A+"]").trigger("click");B&&q.find("a[c="+B+"]").trigger("click");C&&r.find("a[c="+C+"]").trigger("click");e.on("click",".reset",function(){f.val("").trigger("change");g.trigger("click").find("em").text(d);l.not(g).addClass("dn");h.find("em").text(d)});m.on("click","a",function(){f.trigger("change")});h.click(function(){b.trigger("mouseenter")}).mouseleave(function(){b.trigger("mouseleave")});b.mouseenter(function(){h.addClass("hover");b.stop(!0).show()}).mouseleave(function(){b.delay(E).hide(0,function(){h.removeClass("hover")})})})}})(jQuery);
/*右下消息提示框*/
//(function(b){function d(){var c=b('<div id="messager" style="width:'+a.width+'px; height:'+a.height+'px; display:none;"></div>'),d=b('<div class="msg_title"><span class="msg_title_close" title="\u5173\u95ed"> </span>'+a.title+'</div>'),e=b('<div class="msg_content">'+a.content+'</div>');c.append(d).append(e).addClass(a.className);b(document.body).append(c)}var a;b.messager={defaults:{version:'1.0',className:'messager',title:'\u4fe1\u606f\u63d0\u793a',content:'loading...',width:200,height:100,time:3E3,autoClose:!0,animsType:'slide',animsSpeed:600},show:function(c){if(!b('#messager').is('div')){'string'===typeof c&&(c={content:c});a=b.extend({},b.messager.defaults,c);'object'===typeof a.content&&(a.content=b(a.content).html());d();switch(a.animsType){case 'slide':b('#messager').slideDown(a.animsSpeed);break;case 'fade':b('#messager').fadeIn(a.animsSpeed);break;case 'show':b('#messager').show(a.animsSpeed);break;default:b('#messager').slideDown(a.animsSpeed)}b('#messager .msg_title_close').click(function(){b.messager.hide()});!1!==a.autoClose&&setTimeout('$.messager.hide()',a.time)}},hide:function(){if(void 0!==a){switch(a.animsType){case 'slide':b('#messager').slideUp(a.animsSpeed);break;case 'fade':b('#messager').fadeOut(a.animsSpeed);break;case 'show':b('#messager').hide(a.animsSpeed);break;default:b('#messager').slideUp(a.animsSpeed)}setTimeout('$("#messager").remove();',a.animsSpeed)}}}})(jQuery);
/*juicer 分页的模板暂不渲染*/
var tplPagerStr = '<div class="pager">{@if page>1}<a p="1">首页</a><a p="${page-1}">上一页</a>{@/if}{@if(parseInt((page-1)/5)+1)*5<totalPages}{@each i in range(parseInt((page-1)/5)*5+1,(parseInt((page-1)/5)+1)*5+1)}{@if page!=i}<a p="${i}">${i}</a>{@else}<span>${i}</span>{@/if}{@/each}{@else}{@each i in range(parseInt((page-1)/5)*5+1,totalPages+1)}{@if page!=i}<a p="${i}">${i}</a>{@else}<span>${i}</span>{@/if}{@/each}{@/if}{@if page!=totalPages}<a p="${parseInt(page)+1}">下一页</a><a p="${totalPages}">尾页</a>{@/if}</div>';
/*车辆类型json对象*/
//var vehicleTypeJson={'50':'平板车','51':'爬梯车','52':'栏板车','53':'自卸车','54':'高栏车','55':'厢式货车','56':'集装箱运输车','57':'商品车运输车','58':'飞翼车','59':'保温车','60':'冷藏车','61':'罐车','62':'工程车','98':'面包车','99':'其他'};
//var vehicleTypeArray=[{k:'50',v:'平板车'},{k:'51',v:'爬梯车'},{k:'52',v:'栏板车'},{k:'53',v:'自卸车'},{k:'54',v:'高栏车'},{k:'55',v:'厢式货车'},{k:'56',v:'集装箱运输车'},{k:'57',v:'商品车运输车'},{k:'58',v:'飞翼车'},{k:'59',v:'保温车'},{k:'60',v:'冷藏车'},{k:'61',v:'罐车'},{k:'62',v:'工程车'},{k:'98',v:'面包车'},{k:'99',v:'其他'}];
/*车辆长度Json对象*/
//var vehicleLengthJson={'0':{'min':'','max':''},'1':{'min':'','max':'6'},'2':{'min':'6','max':'9'},'3':{'min':'9','max':'11'},'4':{'min':'11','max':'15'},'5':{'min':'15','max':'18'},'6':{'min':'18','max':''},'7':{'min':'18','max':'23'},'8':{'min':'23','max':''},'10':{'len':'6.2'},'11':{'len':'6.8'},'12':{'len':'8.6'},'13':{'len':'9.6'},'14':{'len':'12.5'},'15':{'len':'13'},'16':{'len':'16'},'17':{'len':'17.5'},'18':{'len':'4.2'},'19':{'len':'5.8'},'20':{'len':'7.2'},'21':{'len':'10'},'30':{'len':'4.8'},'31':{'len':'5.0'},'32':{'len':'5.2'}};
/*车辆载重Json对象*/
//var vehicleLoadJson={'0':{'min':'','max':''},'1':{'min':'','max':'8'},'2':{'min':'8','max':'10'},'3':{'min':'10','max':'15'},'4':{'min':'15','max':'20'},'5':{'min':'20','max':'25'},'6':{'min':'25','max':'30'},'7':{'min':'30','max':''}};
/*用户json对象,如果登录了就有值*/
var userObjectJson = {'name':$.cookie('loginName'),'role':$.cookie('userType'),'status':$.cookie('userStatus'),'member':$.cookie('memberType'),'url':getRootPath(),'comCheckStatus':$.cookie('comCheckStatus'),'perCheckStatus':$.cookie('perCheckStatus'),'currUserId':$.cookie('userId')};
/*Jquery地址栏参数转换Json*/
$.par2Json=function(c){var g={},b=c.split("&"),e=decodeURIComponent,f,a;$.each(b,function(d,h){h=h.split("=");f=e(h[0]);a=e(h[1]);g[f]=!g[f]?a:[].concat(g[f]).concat(a)});return g};
/*页面头尾增加模板方式渲染 不需要渲染的 给body 增加类<body class="noIframe">*/
//if (!$('body').hasClass('noIframe')) {
//	$('body').not('.noIframe').prepend(juicer(headerTplStr + orangeBarTplStr).render(userObjectJson))
//	.append(juicer(footerTplStr).render(userObjectJson));
//}

//全国省份车牌
//var SYS_VEHICLE_SF={"京":"京","津":"津","冀":"冀","晋":"晋","蒙":"蒙","辽":"辽","吉":"吉","黑":"黑","沪":"沪","苏":"苏","浙":"浙","皖":"皖","闽":"闽","赣":"赣","鲁":"鲁","豫":"豫","鄂":"鄂","湘":"湘","粤":"粤","桂":"桂","琼":"琼","渝":"渝","川":"川","贵":"贵","云":"云","藏":"藏","陕":"陕","甘":"甘","青":"青","宁":"宁","新":"新","台":"台"};

/*加载侧边菜单*/
//function loadToolbar(){
//	if ($('#main_content').length) {
//		userObjectJson.flag=$.cookie('flag');	//根据cookie内的flag参数判断是车主(2)还是货主(1)
//		$('#main_content').prepend(juicer(menuTplStr).render(userObjectJson));
//	}
//}

/*登陆后检查站内信数量，顶部导航显示*/
//if (userObjectJson.role) {
//	$.get(getRootPath()+'/messageInside/message-inside!findMessageCountUnread.action',function(d){
//		$('#navMsgNum').text(d);
//	});
//}

/*ie9以下模拟placeholder执行*/
$(function(){
	if($('input, textarea') && $('input, textarea').length >0){
		$('input, textarea').placeholder();
	}
});

/*得到根目录*/
function getRootPath(){
	var p = location.pathname.split('/'),w='';
	//用来判断url是否有项目名
	(p[1]=='PortalAppNew') && (w = '/'+p[1]);
	return location.protocol + '//' + location.host + w;
}

/**
* 获取url参数的方法
* name：参数的key
* return：参数的value
*/
//function getUrlParam(name){
//  var reg = new RegExp('(^|&)'+escape(name)+'=([^&]*)(&|$)','i');
//  var r = location.search.substr(1).match(reg);
//  return (r!= null)?unescape(r[2]):'';
//}

//切换标签通用方法
//~function(){
//  var $tabs = $('.tabWarp').find('.tabHd');
//  if(!$tabs.length){return;}	//如果没标签的页面就不往下执行
//  $tabs.on('click','span',function(e){
//    var $t = $(this),idx = $t.parent().find('span').index(this);
//    var $bd = $t.closest('.tabWarp').find('.tabBd');
//    $t.addClass('crt').siblings().removeClass('crt');
//    $bd.addClass('none').eq(idx).removeClass('none');
//  });
//}();

/**
* 显示后台提示消息
* txt：要显示的文本
* top：滚动条的高度
**/
//function showMsg(txt,top){
//	var $error= $('.err_wrap');
//	if(top){$('html').animate({scrollTop:top},200);}	//缓慢置顶特效
//  if(!$error.length){return;}	//如果没错误提示的页面就不往下执行
//  $error.find('.err_box').text(txt);
//  $error.is(':animated') && $error.stop(true,true);	//清空连续点击的延迟队列
//  $error.show().delay(1500).fadeOut(200);
//}

/*文本域的可输入文字计算*/
//function textCounter(field,maxlimit){
//  if(field.value.length > maxlimit) field.value = field.value.substring(0,maxlimit);
//  else $('.remLen').val(maxlimit - field.value.length);
//}

/*juicer的得到分页号码的扩展函数 d:当前页，h:总页数，g:每篇页数*/
function getPageNumbers(d,h,g){var c='<div class="pager">';var f=parseInt(g/2);var e=d-f;if(e<=0){e=1}var a=e+g-1;if(a>h){a=h}if(h-e<g-1){e=a-g+1;if(e<=0){e=1}}if(d>1){c+='<a p="1">首页</a><a p="'+(d-1)+'">上一页</a>'}for(var b=e;b<=a;b++){if(d!=b){c+='<a p="'+b+'">'+b+"</a>"}else{c+="<span>"+b+"</span>"}}if(d!=h){c+='<a p="'+(d+1)+'">下一页</a>'}c+="</div>";return c}
/*码表信息函数*/
function getCodeName(code,codeType){
	return CODE.getCodeName(codeType, code);
}
/*地区信息函数*/
function getAreaName(code){
	if(code && code != null) {
		var city = code.substring(0,4)+'00';
		var province = code.substring(0,2)+'0000';
		var cityObj = AREA_CODE_UTIL.getZoneObjByCode(city);
		var provinceObj = AREA_CODE_UTIL.getZoneObjByCode(province);
		return provinceObj.text+cityObj.text;
	}
}

/*省份信息函数*/
function getProvinceName(code){
	if(code && code != null) {
		var province = code.substring(0,2)+'0000';
		var provinceObj = AREA_CODE_UTIL.getZoneObjByCode(province);
		return provinceObj.text;
	}
}

/*金额格式化函数*/
function converMoneyToPage(num){
	if(num && num != null) {
		return Number(num).div(100);
	} else {
		return '0.00';
	}
}

/*油格式化函数*/
function converOilToPage(num){
	if(num && num != null) {
		return accDiv(num, 100);
	} else {
		return '0';
	}
}

juicer.register('getPageNumbers', getPageNumbers); //注册自定义分页号码函数
juicer.register('getSmpFormatDateByLong', getSmpFormatDateByLong); //注册日期格式化函数
juicer.register('getCodeName', getCodeName); //注册码表信息函数
juicer.register('getAreaName', getAreaName); //注册地区信息函数
juicer.register('getProvinceName', getProvinceName); //注册省份信息函数
juicer.register('converMoneyToPage', converMoneyToPage); //注册金额格式化函数
juicer.register('converOilToPage', converOilToPage); //注册油格式化函数
/**
* 新版重写window.alert
*/
window.alert = function(m, callback) {
	jDialog.alert(m,{},{
	    showShadow: false,      // 不显示对话框阴影
	    buttonAlign : 'center',
	    events : {
	        close : function(evt){
	        	if(callback){callback();}
	        },
	        enterKey : function(evt){
	        	if(callback){callback();}
	        }
	    }
	});
};
/**
* 新版重写window.confirm
*/
window.confirm = function(m, ycall,ncall,t) {
	jDialog.confirm(m,{
		    handler:function(button,dialog){
		      if( ycall )  {ycall();}
		      dialog.close();
		    }
		    
		},{
			handler:function(button,dialog){
		    	if( ncall )  {ncall();}
		    	dialog.close();
		    }
		},{
	    	title : function(){
	    		return t|| "";
	    	}
		});
};

/*每次请求增加ajax等待图标*/
var loadingImgStr = '<img class="ajaxloading" src="' + getRootPath() + '/images/common/loading.gif" width="32" height="31">';
$(document).ajaxSend(function(event, jqxhr, settings){
	var hd = settings.headers;	//loading小图片的ajax增加一个headers头来判断
	if (hd && hd.grid) {
		$('#dataGrid').html(loadingImgStr);
	}
});


/*ajax 权限判断*/
$(document).ajaxComplete(function( event, xhr, settings ) {
	switch(xhr.status){
	case 0:
//		var arrStr = document.cookie.split(";");
//		var flag = 0;
//		for(var i = 0;i < arrStr.length;i ++){
//			var temp = arrStr[i].split("=");
//			if($.trim(temp[0]) == "loginName"){
//				flag=1;
//			}
//		}
//		if (flag==0){
//			//alert("用户未登录登录！", function(){location.href=getRootPath()+"/pages/member/member_login.html";});
//			//TODO DELETE COOKIE
//			$.removeCookie('userType',{path:'/'});
//			$.removeCookie('flag',{path:'/'});
//			$.removeCookie('loginName',{path:'/'});
//			$.removeCookie('memberType',{path:'/'});
//			location.href=getRootPath()+"/pages/oil/oil_home.jsp";
//		}
		break;
	case 400:
		$("link").remove();
		$("body").html("<h1>系统出错了-服务器内部异常</h1>");
		break;
	case 404:
		$("body").html("<iframe scrolling='no' frameborder='0' src='http://yibo.iyiyun.com/js/yibo404/key/1' width='640' height='462' style='display:block;'></iframe>");	
		break;
	case 500:
		alert("系统异常，请联系系统管理员！");
		break;
	case 601:
		//TODO DELETE COOKIE
		$.removeCookie('userType',{path:'/'});
		$.removeCookie('flag',{path:'/'});
		$.removeCookie('loginName',{path:'/'});
		$.removeCookie('memberType',{path:'/'});
		alert("用户未登录！", function(){location.href=getRootPath()+"/pages/oil/oil_home.jsp";});
		break;
	case 602:
		//TODO DELETE COOKIE
		$.removeCookie('userType',{path:'/'});
		$.removeCookie('flag',{path:'/'});
		$.removeCookie('loginName',{path:'/'});
		$.removeCookie('memberType',{path:'/'});
		alert("用户已被停用！", function(){location.href=getRootPath()+"/pages/oil/oil_home.jsp";});
		break;		
	case 600:
		alert("没有权限或用户未认证，请认证后重新登录！",function(){location.href=getRootPath()+"/pages/oil/oil_home.jsp";});
		break;
	case 800:
		alert("调用呼叫中心失败，请联系呼叫中心客服！");
		break;
	}
});

/*登出*/
//function logout(){
//	$.removeCookie('userType',{path:'/'});
//	$.removeCookie('flag',{path:'/'});
//	var url=getRootPath()+'/noAuth/member/login!logout.action';
//	var UAA_CAS_CONFIG = {
//
//	};
//	$.ajax({
//		type : "get",
//		async:false,
//		url : getRootPath()+"/noAuth/member/login!casConfig.action",
//		dataType : "json",
//		error : function() {
//			alert('加载登录配置失败');
//		},
//		success : function(config) {
//			UAA_CAS_CONFIG = config;
//		}
//	});
//	$.post(url,function(d){
//		if(d&&d.code){
//			location.href=UAA_CAS_CONFIG.casRemoteLogoutUrl;
//		}
//	});
//}

/*收藏本站*/
//function AddFavorite() {
//	var title="车旺",url=location.href;
//	try {window.external.addFavorite(url, title);}
//	catch (e) {
//		try { window.sidebar.addPanel(title, url,'');} 
//		catch (e) {
//			alert("抱歉，您所使用的浏览器无法完成此操作。加入收藏失败，请使用Ctrl+D进行添加");
//		}
//	}
//}

var g_require = {};
/**
 * load javascript file 
 * @param u:javascript url 
 * @param c :callback function
 * example:  loadScript("a.js",function(){})
 * 非阻塞模式加载js文件
 */
function loadScript(u, c) {
	if (typeof g_require[u] != "undefined") {
		if (c) {
			c.call();
		}
		return;
	}
	g_require[u] = true;
	var s = document.createElement("script");
	s.ansyc = 'async';
	s.type = "text/javascript";
	s.src = getRootPath() + u + "?v=g_version";
	var f = document.getElementsByTagName("script")[0];
	f ? f.parentNode.insertBefore(s, f) : document.body.appendChild(s);
	if (!!window.ActiveXObject) {
		s.onreadystatechange = function() {
			if (this.readyState === "loaded" || this.readyState === "complete") {
				// Handle memory leak in IE
				s.onreadystatechange = null;
				if (c) {
					c.call();
				}
			}
		};
	} else {
		s.onload = function() {
			s.onload = null;
			if (c) {
				c.call();
			}
		};

	}
	return undefined;

};
/**
 * @param u :url array  example: ["a.js","b.js"]
 * @param c:callback function
 * example:loadScripts(["a.js","b.js"],function(){})
 * 非阻塞模式加载js数组文件
 */
function loadScripts(u, c) {
	var n = 0;
	for ( var i = 0; i < u.length; i++) {
		loadScript(u[i], function() {
			n++;
			if (n == u.length) {
				if (c) {
					c.call();
				}
			}
		});
	}
};
/**
 * load style file 
 * @param s :css url
 */
function loadCss(u, c) {
	var s = document.createElement("link");
	s.rel = 'stylesheet';
	s.type = "text/css";
	s.href = getRootPath() + u + "?v=g_version";
	document.getElementsByTagName("head")[0].appendChild(s);
	if (!!window.ActiveXObject) {
		s.onreadystatechange = function() {
			if (this.readyState === "loaded" || this.readyState === "complete") {
				s.onreadystatechange = null;
				if (c) {
					c.call();
				}
			}
		};
	} else {
		s.onload = function() {
			s.onload = null;
			if (c) {
				c.call();
			}
		};

	}
};

//获得当前时间 2014-07-18 17:01:15 兼容浏览器呦
function getDate(){var a,b,c;return a=new Date,b=a.getFullYear().toString()+"-",c=a.getMonth()+1,b+=(c>9?"":"0")+c+"-",c=a.getDate(),b+=(c>9?"":"0")+c+" ",c=a.getHours(),b+=(c>9?"":"0")+c+":",c=a.getMinutes(),b+=(c>9?"":"0")+c+":",c=a.getSeconds(),b+=(c>9?"":"0")+c}

/*保证数据有效性*/
function getValid(data, defaultValue){
	defaultValue = defaultValue || "";
	if(data != null){
		if(typeof data == 'string'){
			var d = data.toLowerCase();
			if(d !='' && d!='null' && d!='undefined'){
				return data;
			}
		}else{
			return data;
		}
	}
	return defaultValue;
};

/* 验证字符长度,中文长度为2，英文字母为1 */
function validateCharLength(str)
{
	var l = 0;
	var chars = str.split("");
	for ( var i = 0; i < chars.length; i++)
	{
		if (chars[i].charCodeAt(0) < 299)
			l++;
		else
			l += 2;
	}
	return l;
};

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

function getFormatDataToLong(mydate)
{
	if (mydate == undefined || mydate == null || mydate.length<1) {
       return 0;
    }
	
	var dDate = new Date(Date.parse(mydate.replace(/-/g, "/")));
	return dDate.getTime();
}

// 除法函数，用来得到精确的除法结果
// 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
// 调用：accDiv(arg1,arg2)
// 返回值：arg1除以arg2的精确结果
function accDiv(arg1,arg2){
	var t1=0,t2=0,r1,r2;
	try {
		t1=arg1.toString().split(".")[1].length
	} catch(e) {}
	
	try {
		t2=arg2.toString().split(".")[1].length
	} catch(e) {}
	with(Math) {
		r1=Number(arg1.toString().replace(".",""))
		r2=Number(arg2.toString().replace(".",""))
		return (r1/r2)*pow(10,t2-t1);
	}
}

// 给Number类型增加一个div方法，调用起来更加 方便。
Number.prototype.div = function (arg){
	return accDiv(this, arg).toFixed(2);
}

var _bdhmProtocol = ((" https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Ff5469f91404f8612bcf19105b086a581' type='text/javascript'%3E%3C/script%3E"));


//给left菜单添加class使用
if(!typeof(pageTag)=="undefined"){
	$(".leftsideBar_border a").eq(pageTag-1).addClass("leftsideBar_curr").siblings().removeClass("leftsideBar_curr");
}

/*******************解决IE8下不支持trim()问题*********************/
String.prototype.trim = function(){ return Trim(this);};
function LTrim(str)
{
    var i;
    for(i=0;i<str.length;i++)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(i,str.length);
    return str;
}
function RTrim(str)
{
    var i;
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(0,i+1);
    return str;
}
function Trim(str)
{
    return LTrim(RTrim(str));
}
