/* 弹窗查询组件 ISBN */
(function($) {
	$.fn.multipleDataBox = function(options) {
		var default_opts = {
			title: "查询",
			event_type: 'click',
			query: false, //模糊查询开关
			zIndex: null,//层高
			selectall: false,
			trim:true,  //默认清除输入内容中间的空格，false 不清除中间空格
			callback: null //回调函数
		}

		var opts = $.extend({}, default_opts, options);
		return this.each(function() {
			var opt = $.meta ? $.extend({}, opts, $this.data()) : opts;




			var div = ['<script id="query_data_tpl" type="text/html">',
				'         <div class="query_data_area">',
				'         ',
				'         </div>',
				'      </script>'
			].join("");
			//if ($('#query_data_tpl').length == 0) $(div).appendTo('body');
			var $this = $(this);
			var $input = $($this.parent().find("input[type=text]") || '.un_input');

			//var gettpl = $('#query_data_tpl').html();
			var data = {};
			var fun = function() {
				if (opt.test) {
					data = $.parseJSON('{"success":true,"msg":"sssss","obj":[{"isbn":"0002200492"},{"isbn":"0002200493"},{"isbn":"0002200495"},{"isbn":"0002200496"},{"isbn":"0002200497"},{"isbn":"0002200498"},{"isbn":"0002200499"}]}');
				}

				if ($('#multiple_model_box').length == 0) $('<div id="multiple_model_box" style="display:none"><div>').appendTo('body');
				$this.unbind('click').bind('click', function() {
					var $btn = $(this);
					var myRender = function() {

						var html = [
							'<div class="conbox">',
							'	<p style="padding:5px; color:#999">可写入多项查询条件，每个条件一行<br />如果想取消某个条件，请直接删除输入框中的那行条件</p>',
							'	<textarea  style="margin:0 auto; display:block; width:95%; height:125px;"></textarea>',
							'</div>',
						].join("");


						var d = dialog({
							title: opt.title,
							//fixed: true,
							width: 500,
							zIndex: opt.zIndex,
							content: html,
							onshow: function() {
								var textarea_text = $input.val().split(",").join("\n")
								$(".conbox textarea").val(textarea_text)
							},
							okValue: '确定',
							ok: function() {
								var val = $.trim($(".conbox textarea").val());
									val = val.split(/\r?\n/).join(",");
								if(opt.trim){
									val = val.replace(/\s/g,"");
								}
								$input.val(val);
								if(typeof(opt.callback)=='function'){
									return opt.callback(val)
								}
							},
							cancelValue: '取消',
							cancel: function() {},
							button: [{
								value: '清除',
								callback: function() {
									$(".conbox textarea").val("");
									return false;
								}
							}]
						});
						d.showModal();
					}
					myRender();
				});

				//找到输入框
				if (!opt.query) return;
				$input.autocomplete(data.obj, {
					max: 50, //列表里的条目数
					minChars: 0, //自动完成激活之前填入的最小字符
					width: $input.width() + 12, //提示的宽度，溢出隐藏
					scrollHeight: 300, //提示的高度，溢出显示滚动条
					matchContains: true, //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
					autoFill: false, //自动填充
					formatItem: function(row, i, max) {
						return row.isbn;
					},
					formatMatch: function(row, i, max) {
						return row.isbn;
					},
					formatResult: function(row) {
						return row.isbn;
					}
				}).result(function(event, row, formatted) {
					//选择后的回电函数
				});

				return;
			}
			if (opt.query && opt.url) {
				if (opt.get) {
					$.get(opt.url, opt.para || {}, function(json) {
						data = json || {};
						fun();
					}, 'json').error(function() {
						fun();
					});
				} else {
					$.post(opt.url, opt.para || {}, function(json) {
						data = json || {};
						fun();
					}, 'json').error(function() {
						fun();
					});
				}
			} else {
				fun();
			}
			return this;
		})
	};
})(jQuery);