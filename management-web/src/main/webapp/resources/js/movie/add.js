In.ready('datetimepicker','cropper',function() {

	// 修改自官方demo的js
	var initCropper = function (img, input){
		var $image = img;
		var options = {
			aspectRatio: 2.8/4, // 纵横比
			minCropBoxWidth:280,
			minCropBoxHeight:400,
			viewMode: 2,
			preview: '.img-preview' // 预览图的class名
		};
		$image.cropper(options);
		var $inputImage = input;
		var uploadedImageURL;
		if (URL) {
			// 给input添加监听
			$inputImage.change(function () {
				var files = this.files;
				var file;
				if (!$image.data('cropper')) {
					return;
				}
				if (files && files.length) {
					file = files[0];
					// 判断是否是图像文件
					if (/^image\/\w+$/.test(file.type)) {
						// 如果URL已存在就先释放
						if (uploadedImageURL) {
							URL.revokeObjectURL(uploadedImageURL);
						}
						uploadedImageURL = URL.createObjectURL(file);
						// 销毁cropper后更改src属性再重新创建cropper
						$image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
						$inputImage.val('');
					} else {
						window.alert('请选择一个图像文件！');
					}
				}
			});
		} else {
			$inputImage.prop('disabled', true).addClass('disabled');
		}
	}
	var crop = function(){
		var $image = $('#photo');
		var $target = $('#result');
		$image.cropper('getCroppedCanvas',{
			width:280, // 裁剪后的长宽
			height:400
		}).toBlob(function(blob){
			// 裁剪后将图片放到指定标签
			$target.attr('src', URL.createObjectURL(blob));
		});
	}

	initCropper($('#photo'),$('#input'));

	function convertBase64UrlToBlob(urlData){

		var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

		//处理异常,将ascii码小于0的转换为大于0

		var ab = new ArrayBuffer(bytes.length);

		var ia = new Uint8Array(ab);

		for (var i = 0; i < bytes.length; i++) {          ia[i] = bytes.charCodeAt(i);      }

		return new Blob( [ab] , {type : 'image/png'});

	}

	//以上为图片处理


	/**
	 * 保存信息
	 */
	$("#btn_save").click(function () {
		var formData = new FormData();

		//获取图片信息
		var $image = $('#photo');
		var data = $image.cropper('getCroppedCanvas',{width:280,height:400});
		if(data){
			var blob = convertBase64UrlToBlob(data.toDataURL());
			formData.append('file', blob, "photo.jpg");
		}

		//获取基本信息
		var formArray = $("#form_object").serializeArray();

		$.each(formArray,function (index,item) {
			if(item.name && item.value){
				// alert(item.name + ':' + item.value);
				formData.append(item.name, item.value);
			}
		});

		//发送请求
		// var data = new FormData($("#formUpload")[0]);
		$.ajax({
			type : "post",
			url:baseUrl + "movie",
			data:formData,
			cache: false,
			contentType: false,
			processData: false,
			beforeSend:function () {
				$("#loading").modal("show");
				$("#btn_save").button("loading");
			},
			success:function (data) {
				$("#loading").modal("hide");
				$("#btn_save").button("reset");
				if(data.error){
					layer.alert(data.message);
					return false;
				}
				layer.alert("操作成功！",function () {
					window.location.href = "manage";
					return false;
				});
			},
			error:function () {
				layer.alert("网络异常！");
				$("#loading").modal("hide");
				$("#btn_save").button("reset");
			}
		});
	});


	$('#datetimepicker').datetimepicker({
		language: 'cn',//显示中文
		autoclose: true,
		minView: "month"//设置只显示到月份
	});

});//In.ready结束


