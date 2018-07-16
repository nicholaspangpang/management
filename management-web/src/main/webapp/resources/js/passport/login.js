$(function () {

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

    $("#btn_login").click(function () {
        var data = $("#form_login").serializeObject();
		var returnUrl = $("#returnUrl").val();

        $.ajax({
            type:"post",
            url:"login",
            data:JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dateType:"json",
			beforeSend:function () {
				$("#btn_login").attr("disabled",true);
			},
            success:function (data) {
				if(data.error){
					layer.alert(data.message);
					$("#btn_login").removeAttr("disabled");
					return false;
				}
				window.location.href = "main";
            },
            error:function () {
                alert("error");
            }
        });
    });

});

