(function($) {
    $.fn.select=function(options){
        var default_opts = {
            url: null, //远程数据，请求地址
            para: null, //远程数据，请求参数
            selected: null, //设置默认选中的value
            exclude: null, //排除数据的value,数组（排除的数据不再select中显示）
            //defaultdata: [],
            localdata: null //本地数据，优先展示本地数据(开启本地数据，url则无效)
        };

        var opts = $.extend({}, default_opts, options);
        return this.each(function() {
            var opt = $.meta ? $.extend({}, opts, $this.data()) : opts;
            var Box = $(this),
                data = opt.localdata;

                // setting=settings || {},//是否传参
                // url=setting.url,
                // para=setting.para || {},
                // test=setting.test,
                // selected = setting.selected || null,//设置默认选中的value
                // data=setting.localdata || null; //本地数据

            var select={
                //初始化
                init:function(){
                    var _this=this;
                    //判断真假数据
                    if(opt.localdata){
                        _this.create();
                    }
                    //获取url数据
                    if(opt.url&&!opt.localdata) {
                        $.ajax({
                            type:'get',
                            url:opt.url,
                            data: opt.para,
                            dataType:"json",
                            success:function(rs){
                                var state = rs.success;
                                if(state==true) {
                                    //成功
                                    var result = rs || {};
                                    data=result.obj;
                                    _this.create();
                                }else{
                                    //失败
                                    var code=rs.Code,
                                            msg = rs.Msg,
                                            str="";
                                    str="错误代码："+code+"   错误信息："+msg;

                                    alert(str);
                                }
                            },
                            error:function(e){

                                var state = e.status,
                                        msg = "";
                                if(state == "404" || state == "500"){
                                    msg = "服务器繁忙,请稍后在试!";
                                }else{
                                    msg = "无法连接服务器或者数据格式有误";
                                }
                                alert(msg);
                            }
                        });
                    }
                },
                //创建对象
                create:function(){
                    if(Box.attr("data-init") == '1') return       
                    //data = opt.defaultdata.concat(data);
                    for(var i=0;i<data.length;i++){
                        var option=$('<option>'+data[i].name+'</option>');
                        option.attr('value',data[i].value||'');
                        if(data[i].value==opt.selected){
                            option.attr('selected',true);
                        };
                        if(opt.exclude){
                            var exclude_arr = opt.exclude;
                            for (var j = 0; j < exclude_arr.length; j++) {
                                if(data[i].value==exclude_arr[j]){
                                    option = '';
                                }   
                            }
                        }
                        Box.append(option)
                    };
                    Box.attr("data-init","1")
                }
            };
            if(Box.attr("data-init")=='1') return;
            return select.init();
        })
    };
})(jQuery);