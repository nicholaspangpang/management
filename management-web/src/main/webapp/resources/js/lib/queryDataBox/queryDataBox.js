/*弹窗查询组件*/
(function($) {
	$.fn.queryDataBox = function(options) {
		var default_opts = {
			title: "查询",
			event_type: 'click', 
			reload: false, //二次加载(为true时，无论query的值，页面在渲染和点击按钮都执行ajax)
			query: false, //输入框模糊查询开关（true时页面渲染执行ajax，false时点击按钮执行ajax），目前模糊查询只支持供应商模式
			type: null, //类型是供应商还是普通，通用:'common'，供应商：缺省或null，两个类型数据结构不一样
			reqtype: 'POST', //请求方式 'GET','POST'
			localdata: undefined, //本地数据 
			showType: 'code', //显示类型 code：显示供应商id，取输入框value值， name，显示供应商名称，取输入框的data-code属性值
			selectall: false, //全选开关
			mode : null,//单选模式，缺省 多选模式
			zIndex: null,//层高
			callback: null //回调函数
		};

		//供应商数据结构：
		//{success:true,msg:"成功",obj:[{"supplierId":"S0002200492","supplierName":"江西美术出版社有限责任公司","supplierShortName":"江美"},{"supplierId":"S0002200493","supplierName":"人民邮电出版社","supplierShortName":"人邮"}]}
		//普通人名数据结构：
		//{success:true,msg:"成功",obj:[{"code":"FENGQINGYANG","name":"风清扬"},{"code":"JANGMEILI","name":"江美丽"}]}

		var opts = $.extend({}, default_opts, options);
		return this.each(function() {
			var opt = $.meta ? $.extend({}, opts, $this.data()) : opts;

			if(opt.type && opt.type=="common"){
				var div = [
					'         <div class="query_data_area">',
					'           <div class="l_so_area">查询：<input class="l_so" type="text" value="" >',
					'            <span class="l_so_chk"></span>',
					'           </div>',
					'           <div class="l_left" onselectstart="return false">',
					'           {@if success==true}',
					'               {@each obj as it,index}',
					'               <label rid="${it.code}" rname="${it.name}" class="l_lab" title="${it.name}"><span style="display:none"></span><span style="display:none">${it.name}</span> ${it.name} <span class="l_span"> >></span><span class="r_span"> <<</span></label>',
					'               {@/each}',
					'           {@/if}',
					'           </div>',
					'           <div class="l_right" onselectstart="return false">',
					'           </div>',
					'         </div>',

				].join("");
			}else{
				var div = [
					'         <div class="query_data_area">',
					'           <div class="l_so_area">查询：<input class="l_so" type="text" value="" >',
					'            <span class="l_so_chk"></span>',
					'           </div>',
					'           <div class="l_left" onselectstart="return false">',
					'           {@if success==true}',
					'               {@each obj as it,index}',
					'               <label rid="${it.supplierId}" rname="${it.supplierName}" class="l_lab" title="${it.supplierName}"><span style="display:none">${it.supplierShortName}</span><span style="display:none">${it.supplierName}</span> ${it.supplierId}_${it.supplierName} <span class="l_span"> >></span><span class="r_span"> <<</span></label>',
					'               {@/each}',
					'           {@/if}',
					'           </div>',
					'           <div class="l_right" onselectstart="return false">',
					'           </div>',
					'         </div>',
					
				].join("");
			};

			//if ($('#query_data_tpl').length == 0) $(div).appendTo('body');
			var $this = $(this);
			var gettpl = div;
			var fun = function(data) {
				var $inputs = $($this.parent().find("input[type=text]") || '.un_input');

				var $s = $('<div id="query_model_box">加载中<div>');
				$this.bind('click', showBox);
				function showBox() {
					var $btn = $(this);
					$input = $($btn.parent().find("input[type=text]") || '.un_input');

					var d = dialog({
						title: opt.title,
						//fixed: true,
						width: 660,
						zIndex: opt.zIndex,
						//content: $s[0],
						onshow: function(){
							reqData();
						},
						okValue: '确定',
						ok: function() {
							var code = $('.l_right .l_lab').map(function() {
								return $.trim($(this).attr('rid'));
							}).get().join(",");
							var name = $('.l_right .l_lab').map(function() {
								return $.trim($(this).attr('rname'));
							}).get().join(",");
							if(opt.showType==='code'){
								$input.attr("data-name",name);
								$input.attr("data-code",code).val(code);
							}else if(opt.showType==='name'){
								$input.attr("data-name",name);
								$input.attr("data-code",code).val(name);
							};
							$(this).dialog("close");
							if(typeof(opt.callback)=='function'){
								opt.callback(name,code)
							}
						},
						cancelValue: '取消',
						cancel: function() {}
					});
					d.showModal();

					/**
					 * [数据初始化]
					 */
					function dataRender(res){
						var html;
						if(typeof(res)!=='string'){
							var data = res || {};
							if(typeof(opt.localdata) === "object" ){
								data.success = true;
								data.obj = opt.localdata;
							}
							html = juicer(gettpl, data);
						}else{
							html = '<div style="position:absolute; width:120px; left:50%; top:50%; margin:-16px 0 0 -60px; text-align:center; color:#999">数据请求失败</div>';
						};
						$s.html(html);
						//setTimeout(function(){
						d.content($s.prop("outerHTML"));
						//},500)
						dataSerialize()
					};

					/**
					 * 页面数据序列化
					 */
					function dataSerialize(){
						var _check = '<input id="ck_all" class="check_allbtn" style="margin-left:30px;vertical-align: middle;" type="checkbox" value=""> <label for="ck_all" style="display:inline-block; vertical-align: middle; font-size:12px">分管供应商</label>';
						if (opt.selectall) $(".l_so_chk").html(_check);

						var layero = $('#query_model_box');
						//左侧点击事件
						layero.find('.l_left .l_lab').bind(opt.event_type, function() {
							var $tab = $(this);
							if(!opt.mode){
								$tab.clone().appendTo(layero.find('.l_right'));
								$tab.addClass('active');
							}else if(opt.mode == 'single'){
								if(layero.find(".active").length == 0){
									$tab.clone().appendTo(layero.find('.l_right'));
									$tab.addClass('active');
								}
							}
						});
						//右侧点击事件
						layero.find('.l_right').delegate('.l_lab', opt.event_type, function() {
							var $tab = $(this);
							$('.l_lab[rid=' + $tab.attr('rid') + ']').removeClass('active');
							$tab.remove();
						});
						layero.find('.l_so').bind('keyup change', function() {
							var $so = $(this),
								key = $.trim($so.val());
							key = key.toUpperCase();

							if (key.length == 0) {
								$('.l_left .l_lab').removeClass('none');
							} else {
								$('.l_left .l_lab').addClass('none');
								layero.find('.l_left .l_lab:contains(' + key + ')').removeClass('none');
							}
						});

						var right_val;
						if(opt.showType==='code'){
							right_val = $input.val().split(',');
						}else if(opt.showType==='name'){
							right_val = $input.attr("data-code");   //通过属性获取
							if(right_val) {
								right_val = right_val.split(',')
							}else{
								right_val = [];
							};
						};

						if (right_val.length > 0) {

							$.each(right_val, function(k, v) {
								if ($('.l_left .l_lab[rid=' + (v || 'n') + ']').length > 0) $('.l_left .l_lab[rid=' + (v || 'n') + ']').trigger(opt.event_type);
							});

						};

						layero.find(".check_allbtn").bind('click', function() {
							var $this = $(this);
							if ($this[0].checked == false) {
								$this[0].checked == true
								$('.l_right .l_lab').trigger(opt.event_type);
							} else {
								$this[0].checked == false;
								var $tab = $('#query_model_box').find('.l_left .l_lab');
								$tab.clone().appendTo($('#query_model_box').find('.l_right'));
								$tab.addClass('active');
							}
						})
					};
					function reqData(){
						if (!opt.localdata && !opt.query || opt.reload) {
							$this.unbind('click');
							$.ajax({
								url:opt.url,
								data:opt.para,
								type:opt.reqtype,
								cache:true,
								dataType:'json',
								beforeSend: function(){
									//WG.loading.show()
								},
								success: function(res){
									//setTimeout(function(){
										//WG.loading.hide()
									//}, 500)
									//WG.loading.hide()
									$this.bind('click', showBox);
									var data = res || {};
									//setTimeout(function(){
										dataRender(data)
									//}, 5000)
								},
								error: function(){
									//WG.loading.hide()
									$this.bind('click', showBox);
									setTimeout(function(){
										dataRender("error");
									}, 500)
									//alert("数据请求失败");
								}
							})
						}else{
							//setTimeout(function(){
								dataRender(data)
							//}, 500)
						}
					}
				};

				//if(!opt.type){
					if (!opt.query||!data) return;

					//找到输入框
					$inputs.autocomplete(data.obj,{
						max: 50,    //列表里的条目数
						minChars: 0,    //自动完成激活之前填入的最小字符
						width: $inputs.width()+12,     //提示的宽度，溢出隐藏
						scrollHeight: 300,   //提示的高度，溢出显示滚动条
						matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
						autoFill: false,    //自动填充
						formatItem: function(row, i, max) {
							return row.supplierName;
						},
						formatMatch: function(row, i, max) {
							return row.supplierName;
						},
						formatResult: function(row) {
							return row.supplierId;      
						}
					}).result(function(event, row, formatted) {
						//选择后的回电函数
					});
				//}
				//return;
			}
			if (!opt.localdata && opt.url && opt.query || opt.reload) {
				$.ajax({
					url:opt.url,
					data:opt.para,
					type:opt.reqtype,
					dataType:'json',
					success: function(res){
						data = res || {};
						fun(data);
					},
					error: function(){
						fun();
					}
				})
			} else {
				fun();
			}
		})
		//return this;
	};
})(jQuery);