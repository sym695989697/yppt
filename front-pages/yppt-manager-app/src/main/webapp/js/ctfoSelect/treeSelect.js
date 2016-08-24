/*
 * All rights Reserved, Sinoiov Technology Co., LTD.
 * Copyright(C) 2012-2014
 * 2013年1月23日
 */

(function($) {
	/**
	 * @param args legerUI树初始化参数
	 * @param treeHeader 树头
	 * @param nullAbleHeader 树头是否可为空
	 */
	$.fn.treeSelect=function(args, treeHeader, nullAbleHeader){
		var _treeSelect=new treeSelect();
		if(treeHeader || nullAbleHeader){
			_treeSelect._config.treeHeader = treeHeader;
		}
		_treeSelect._defaultParams={};
		$.extend(_treeSelect._defaultParams, args);
		_treeSelect._defaultParams["thisObj"]=this.get(0);
		_treeSelect._init();
		return _treeSelect;
	};
	var treeSelect=function(){};
	treeSelect.prototype={
		_config:{
			hasStaticTreeHeader:false,//@by rao yongbing @for query treeNode name
			treeHeader:{id:'0',code:'root',codeLevel:'1',name:'码表信息',pid:'rootroot', isexpand:true}
		},
		_init:function(){
			this._render();
			this._initTree();
		},
		_render:function(){
			var g=this; 
			var p=this._defaultParams;
			var d =this._defaultParams.thisObj;
			var _html ="";
			var searchHtml = '&nbsp;<input type="text" id="treeSelect-search-input" style="width: '+(p.width-80)+'px;margin-left: 5px;" /> &nbsp; <input type="button" id="treeSelect-search-btn" value="查询"/>';	
			_html +="<div style='margin-top: 10px;'>"+(p.isHideSearch ? "": searchHtml)+"</div><div style='margin-top: 0px;margin-left: 5px;'><div id='treeSelect-tree'>加载数据失败...</div></div>";
			$(d).css("width",this._defaultParams.width+"px");
			$(d).css("top",(this._defaultParams.top?this._defaultParams.top:0)+"px");
			var tabHtml = "<div title='"+p.title+"'><div class='l-grid-body l-grid-body2 l-scroll' style='height:"+($(window).height()-40)+"px;width:100%;'>"+_html+"</div></div>";
			$(d).html(tabHtml); 
			$(d).ligerTab({
				changeHeightOnResize : true,
				contextmenu : false
			});
			$("#treeSelect-search-btn").bind('click', function() {
				
				if(g._config.treeHeader){
					g._config.hasStaticTreeHeader=true;
				}
				
				g._initTree();
			});
		},
		_remove:function(tree, node){
			tree.remove(node.target);
		},
		_initTree:function(){
			var g=this, p=this._defaultParams;
			p.params = p.params || {};
			var name=$('#treeSelect-search-input').val();
			if(name){
				p.params['requestParam.like.name']='%' +name +'%';
			}else{
				p.params['requestParam.like.name']='';
			}
			
			var e = $('#treeSelect-tree');
			e.empty();
		
			e.ligerTree({
				slide : true,
				nodeWidth : p.width-60,
				checkbox : p.checkbox || false,
				idFieldName :p.idFieldName || 'id',
			    parentIDFieldName :p.parentIDFieldName || 'parentId',
				textFieldName : p.textFieldName || 'name',
				attribute : p.attribute || [ 'name', 'id' ],
				//data : p.data || [],
				onBeforeExpand : function(node){
					if(p.onBeforeExpand){
						p.onBeforeExpand(node, e.ligerGetTreeManager());
					}else{
						g.__loadData(e.ligerGetTreeManager(), node.target, p.url,{'requestParam.equal.pid': node.data.id});
					}	
				},
				onSelect : function(node){
				},
				onClick : function(node){
					p.onClick.call(this,node);
				},
				onError : function() {
					e.text('数据加载失败！');
				}
			});
			
			g.__loadData(e.ligerGetTreeManager(), null, p.url, p.params);
			
		},
		_isChildren : function(obj){
        	var result = true;
        	var data = CODE_UTIL.getParentCodeData();
        	 $(data).each(function(){ 
        		 if(this.pid == obj.id){
        			 result = false;
        			 return false;
        		 }
        	 });
        	return result; 
        },
		__loadData: function (tree, node, url, param,hasStaticTreeHeader){
			var g = this;
			if(g._defaultParams.data && g._defaultParams.data.length>0){
				tree.data=[];
				tree.setData(g._defaultParams.data);
			}else{
				tree.loading.show();
	            var ajaxtype = param ? 'post' : 'get';
	            param = param || [];
	           
	            //请求服务器
	            $.ajax({
	                type: ajaxtype,
	                url: url,
	                data: param,
	                dataType: 'json',
	                timeout :  180000,
	                success: function (data){
	                    if (!data) return;
                    	var newdata=[];
	                    $(data).each(function(){
	                    	var dd = tree?tree.getDataByID(this.id):null;
	                    	if(this.id=='0' || dd)return true;
	                    	dd = this;
	                    	if(g._isChildren(dd)){
								this['isexpand']=true;
	                    	}else{
	                    		this['children']=[];
								this['isexpand']=false;
	                    	}
							newdata.push(this);
	    				});
	                   
	                    if(!g._config.hasStaticTreeHeader){//@by rao yongbing @for query treeNode name
		                   
	                    	if(!tree.getDataByID('0') && g._config.treeHeader){
		                    	newdata.push(g._config.treeHeader);
		                    }
	                    }
	                    
	                  //@by rao yongbing @for query treeNode name
//	                    if(g._config.hasStaticTreeHeader){
//	                    	
//	                    	if(param['requestParam.like.name']==''){
//	                    		
//	                    		if(!tree.getDataByID('0') && g._config.treeHeader){
//			                    	newdata.push(g._config.treeHeader);
//			                    }
//	                    	};
//	                    }
	                    
	                    
	                    data = newdata;
	                    tree.loading.hide();
	                    tree.append(node, data);
	                    tree.trigger('success', [data]);
	                },
	                error: function (XMLHttpRequest, textStatus, errorThrown){
	                    try{
	                        tree.loading.hide();
	                        tree.trigger('error', [XMLHttpRequest, textStatus, errorThrown]);
	                    }
	                    catch (e){

	                    }
	                }
	            });
			}            
        }
	};
})(jQuery);