var baseUrl = appPath+'/';

In.add('fooee',{path:baseUrl+'resources/js/lib/fooee/fooee.js',type:'js',charset:'utf-8'});
In.add('layer',{path:baseUrl+'resources/js/lib/layer/layer.js',type:'js',charset:'utf-8'});
In.add('bootstrap',{path:baseUrl+'resources/js/lib/bootstrap/bootstrap.min.js',type:'js',charset:'utf-8'});
In.add('juicer',{path:baseUrl+'resources/js/lib/juicer/juicer-min.js',type:'js',charset:'utf-8'});

In.add('select',{path:baseUrl+'resources/js/lib/fooee/select.js',type:'js',charset:'utf-8'});


// In.add('datetimepicker-language',{path:baseUrl+'resources/js/lib/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js',type:'js',charset:'utf-8'});
In.add('datetimepicker-css',{path:baseUrl+'resources/js/lib/datetimepicker/css/bootstrap-datetimepicker.min.css',type:'css',charset:'utf-8'});
In.add('datetimepicker',{path:baseUrl+'resources/js/lib/datetimepicker/bootstrap-datetimepicker.min.js',type:'js',charset:'utf-8',rely:['datetimepicker-css']});

In.add('json',{path:baseUrl+'resources/js/lib/jquery/jquery.json-2.4.js',type:'js',charset:'utf-8'});

In.add('artDialog-css',{path:baseUrl+'resources/js/lib/artDialog/css/art-dialog.css',type:'css'});
In.add('artDialog',{path:baseUrl+'resources/js/lib/artDialog/dialog-min.js',type:'js',charset:'utf-8',rely:['artDialog-css']});

In.add('autocomplete-css',{path:baseUrl+'resources/js/lib/autocomplete/jquery.autocomplete.css',type:'css'});
In.add('autocomplete',{path:baseUrl+'resources/js/lib/autocomplete/jquery.autocomplete.js',type:'js',charset:'utf-8',rely:['autocomplete-css']});

In.add('multipleDataBox',{path:baseUrl+'resources/js/lib/multipleDataBox/multipleDataBox.js',type:'js',charset:'utf-8',rely:['juicer','artDialog']});

In.add('queryDataBox-css',{path:baseUrl+'resources/js/lib/queryDataBox/queryDataBox.css',type:'css'});
In.add('queryDataBox',{path:baseUrl+'resources/js/lib/queryDataBox/queryDataBox.js',type:'js',charset:'utf-8',rely:['juicer','artDialog','queryDataBox-css','autocomplete','json']});

In.add('localeCn',{path:baseUrl+'resources/js/lib/jqGrid/js/i18n/grid.locale-cn.js',type:'js',charset:'utf-8'});
In.add('jqueryUi-css',{path:baseUrl+'resources/js/lib/jqGrid/css/ui-lightness/jquery-ui-1.8.14.custom.css',type:'css'});
In.add('jqGrid-css',{path:baseUrl+'resources/js/lib/jqGrid/css/ui.jqgrid.css',type:'css'});
In.add('jqGrid',{path:baseUrl+'resources/js/lib/jqGrid/js/jquery.jqGrid-4.4.3.min.js',type:'js',charset:'utf-8',rely:['jqGrid-css','jqueryUi-css','bootstrap','localeCn']});

In.add('cropper',{path:baseUrl+'resources/js/lib/cropper/cropper-3.1.3.min.js',type:'js',charset:'utf-8',rely:['cropper-css']});
In.add('cropper-css',{path:baseUrl+'resources/js/lib/cropper/cropper-3.1.3.min.css',type:'css',charset:'utf-8'});

/***文件下载工具***/
//In.add('fileUtil',{path:baseUrl+'js/customUtils/fileUtil.js',type:'js',charset:'utf-8'});


function getcookie(objname){//获取指定名称的cookie的值
	var arrstr = document.cookie.split("; ");
	for(var i = 0;i < arrstr.length;i ++){
		var temp = arrstr[i].split("=");
		if(temp[0] == objname) return unescape(temp[1]);
	}
}

In.ready('layer','bootstrap','fooee','juicer',function () {

	//juicer设置边界符号，避免与velocity冲突
	juicer.set({
		'tag::interpolateOpen': '#{',
		'tag::interpolateClose': '}'
	});

	//juicer转换头像函数,使用：#{i.pictureAddress|head,'30_30'}
	var replaceHead = function(item, type){
		return item.replace("0_0",type);
	};
	juicer.register('head', replaceHead);


	/*
	 * jquery序列化表单数据
	 */
    jQuery.prototype.serializeObject=function(){
        var obj=new Object();
        $.each(this.serializeArray(),function(index,param){
            if(!(param.name in obj)){
                obj[param.name]=param.value;
            }
        });
        return obj;
    };

    /**
	 * 读取登陆信息
	 */
    if( $("#reloadUserInfo").val() == 1 ){
		var sso_ticket = getcookie('sso_ticket');
		if(sso_ticket){
			$.ajax({
				url: baseUrl+"getUserInfo",
				success : function(data){
					if (data) {
                        var userInfoTpl = $(".user_info_tpl").html();
                        var html = juicer(userInfoTpl, data);
                        $("#divUserInfo").html(html);
					}
				}
			});
		}
	}

	/**
	 * 读取菜单信息
     */
	$.ajax({
		url : baseUrl + "menuTree",
		success : function (data) {
			var tpl = $("#menu_tree_tpl").html();
			var html = juicer(tpl, data);
			$("#menu_tree").html(html);
		}
	});

});















/*

 */
window.onload = function() {
	WGinit()
};

function WGinit(){
	if(!$){
		WGinit()
	}else{
		WG.init();
	}
};

window.WG = {
	init: function(T) {
		T = T || this;
		for (var p in T) {
			T[p] && T[p].init && T[p].init();
		};
	},
	stopBubble: function(e) {
		// 如果传入了事件对象，那么就是非ie浏览器  
		if (e && e.stopPropagation) {
			//因此它支持W3C的stopPropagation()方法  
			e.stopPropagation();
		} else {
			//否则我们使用ie的方法来取消事件冒泡  
			window.event.cancelBubble = true;
		}
	}
};


WG.tabSetUrl = {
	init: function(){
		this.load()
	},
	load: function(callback){

	}
};
