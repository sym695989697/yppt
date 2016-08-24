$(function(){	
	
	/*banner*/
	var banner=$(".oil_small_banner");
	var slide=$("#slide_banner");
	var slide_ul=$(".slide_ul");
	var sld_li=slide.find("li");
	var sld_img=sld_li.find(" a img");
	var index=0;
	var len=sld_li.length;	
	var lbtn1=$('<a href="javascript:;" class="lbtn" ></a>');
	var rbtn1=$('<a href="javascript:;" class="rbtn" ></a>');	
	slide_ul.before(lbtn1).after(rbtn1); 
	var win=$(window);
	slide.css("height",win.width()/4.3);
	win.resize(function(){
		slide.css("height",win.width()/4.3);
		})
	var lbtn=$(".lbtn");
	var rbtn=$(".rbtn"); 
	var titleBar=$("<div id='titleBar'></div>");
	titleBar.insertAfter(slide);
	for(i=0;i<len;i++){
		var sld_span=$("<span>");
		sld_span.appendTo(titleBar);
		}
		
	var title=$("#titleBar");
	var sld_span=title.find("span");
	title.css("margin-left",(-len*61)/2)		
	
	var slide_show=function(i){
		sld_li.stop(true,true).fadeOut().eq(i).stop(true,true).fadeIn();
		sld_span.removeClass("hover").eq(i).addClass("hover");
		}
	slide_show(0);
	sld_span.mouseenter(function(){
		var inx=sld_span.index(this);
		if(inx!=index){
			index=inx;
			slide_show(inx);
			}		
		})
	
	rbtn.click(function(){
		index=index>=len-1?0:++index;
		
		slide_show(index);
		})
	lbtn.click(function(){
		index=index<=0?len-1:--index;
		slide_show(index);
		})
	/*
	*****这里是自动轮播，如果想用就取消注释
	var autoPlay=function(){
		timer=setInterval(function(){
			index=index>=len-1?0:++index;
		    slide_show(index);
			},5000)
		
		}
		autoPlay();
		banner.hover(function(){clearInterval(timer)},autoPlay)*/
	
	/*开始的结束括号*/})