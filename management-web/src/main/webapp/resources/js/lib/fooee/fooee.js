/**
 * 公共操作
 * author:庞振华 2013-07-12
 */

var strInfoLoading 		= "加载中...";
var strInfoNoData 		= "暂时没有数据...";
var strInfoPleaseCheck  = "请检查该项输入";
var strErrorReadData 	= "数据读取错误.请刷新或稍后重试..";
var strErrorOvertime	= "连接超时，请稍后重试！";
var emailRegex = /^[\w!\#\$\%\&\*\+-\=\?\{\|\}~\.]+@[-\w]+\.[-\w\.]+$/;
var nameRegex  = /[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*/;
var POPUP_MARGIN_TOP 	= 0;
var POPUP_MARGIN_LEFT 	= 5;
var POPUP_SHOW_SPEED	= 300;

var com;
if (!com){com = {};};
com.fooee = {};
com.fooee.fgcms = {};
com.fooee.fgcms.index = {};

(function($){
	window.fooee = {
		asyn : true,  //是否异步 默认为true异步
		/*
		 * ajax处理
		 * 庞振华(20130720)
		 * 修改增加默认为同步操作(可设置),避免同时调用的混乱(庞振华20130721)
		 */
		doAjax : function(p_url , p_data , p_callback , p_callback_error){
			//alert(p_data.showDiv);
			$.ajax({
				async : this.asyn,
				url : p_url ,
				data : p_data ,
				type : "post" ,
				dataType : "json" ,
				timeout:10000,
                contentType:"application/json;charset=utf-8",
				success : function(data){
					p_callback(data , p_data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					p_callback_error(XMLHttpRequest , p_data);
				}
			});
		},
		
		/*
		 * 客户端表单验证
		 * 庞振华(20130725)
		 */
		validation:{
			validator : {
				/*空值验证*/
				isNonEmpty : function($field){
					var val = jQuery.trim($field.val());
					if (val && val.length) {
						return !!val.length;
					} else {
						return !!val;
					}
				},
				/*数字验证*/
				isNumber : function($field){
					return /^\d+$/.test($field.val());
				},
				/*浮点验证*/
				isFloat: function($field){
					return /^\d+(.\d+)?$/.test($field.val());
				},
				/*邮箱验证*/
				isValidEmail: function($field) {

					var val = $.trim($field.val());

					return emailRegex.test(val);
				},
				/*选填邮箱*/
				isOptionalEmail : function($field){
					return $field.val() == '' || fooee.validation.validator.isValidEmail($field.val()) ;
				},
				/*手机号码验证*/
				isValidPhone : function($field){
					//var validPhone = /^((\(\d{3}\))|(\d{3}\-))?13[0-9]\d{8}|15[89]\d{8}|18\d{9}$/.test($field.val());
					var validPhone = /^((\(\d{3}\))|(\d{3}\-))?13[0-9]\d{8}|15\d{9}|18\d{9}|14\d{9}$/.test($field.val());
					return validPhone;
				},
				/*银行卡验证*/
				isBankCardNumber : function($field){
					var val = $field.val();
					val = val.replace(/\s+/g,'');
					return /^\d{19}$/.test( val );
				},
				/*选择验证*/
				isChecked: function($field) {
					return $field.is(':checked');
				},
				/*对比验证*/
				matchesOtherField: function($otherField) {
					return function($field) {
						return $field.val() == $otherField.val();
					};
				},
				/*密码强度*/
				isGoodPassword: function($field) {
					var password      = $field.val();
					var isBadPassword = false;
					var badPasswords  = [
						"password", "123456", "12345678", "1234567",
						"111111", "654321", "696969", "abc123"
					];
					
					if (password == '' || password == null){
						return false;
					}
					
					$.each(badPasswords, function(index, value) {
						if(value === password) {
							isBadPassword = true;
							return;
						}
					});

					return !isBadPassword;
				},
				/*中文姓名验证*/
				isRealName : function($field){
					var val = $.trim($field.val());
					return nameRegex.test(val);
				}
			},

			checkValid: function($field, validityCheck, $otherInvalidFields) {
				var isValid = validityCheck($field);
				$field.add($otherInvalidFields)[isValid ? 'removeClass' : 'addClass']('invalid');

                var $errorMessage = $('.error-box [for_name="' + $field.attr('name') + '"]');
				$errorMessage[isValid ? 'hide' : 'show']();
				return isValid;
			},
			
			checkFormValidity : function(desc){
				var $form = desc.form;
				var valid = true;

				$.each(desc.fields , function(i , field){
					var $field = $form.find('[name="' + field.name + '"]');
					var $element = field.visibleElement || $field;
					var validators = field.validators || [{check : field.check, message : field.message}];
					var $errorPopup = $element.data('$errorPopup');
					var fieldIsValid = true;
					var changeClass = field.changeClass;

					$.each(validators, function(index , validator){
						var fieldPassed = validator.check($field);
						validator.message = validator.message || strInfoPleaseCheck;
						if (fieldIsValid && !fieldPassed){
							fieldIsValid = false;
							if ($errorPopup){
								$errorPopup = fooee.validation._updateErrorFloat($element , validator.message);
								$element.data('$errorPopup' , $errorPopup);
							}else{
								$errorPopup = fooee.validation._createErrorFloat($element , validator.message);
								$element.data('$errorPopup' , $errorPopup);
							}
						}
						
						if (changeClass){
							$element[fieldIsValid ? 'removeClass' : 'addClass']('invalid');
						}

						if (fieldIsValid){
							//第一次不会有该对象
							$errorPopup && $errorPopup.hide(POPUP_SHOW_SPEED);
						}else{
							valid = false;
						}
						
					});
				});
				return valid;
			},

			markFieldValid : function($field, valid, message, $location) {
				var $errorPopup = $field.data('$errorPopup');
				if (valid) {
					$field.removeClass('invalid');
					if ($errorPopup) {
						$errorPopup.hide(POPUP_SHOW_SPEED);
					}
				} else {
					$field.addClass('invalid');
					if (!$errorPopup) {
						$errorPopup = fooee.validation._createErrorFloat(message, $location || $field);
						$field.data('$errorPopup', $errorPopup);
					}
					fooee.validation._placeErrorPopup($errorPopup);
				}
			},

			//创建表单验证器
			createFormValidator : function(desc){
				var valid = fooee.validation.checkFormValidity(desc);
				if (valid)
				{
				}
				return valid;
			},

			_createErrorFloat : function($element , errorMessage){
				//先检测是否对象是否有数据,为了前台js直接操作popup
				var $errorPopup = $element.data('$errorPopup');
				if ($errorPopup){
					$errorPopup.find('.message').text(errorMessage);
					$errorPopup.show(POPUP_SHOW_SPEED);
					return $errorPopup;
				}
				//结束
				
				var $float = $('<div class="error-float"><div class="message"/><div class="locator"/><div class="close"/></div>').appendTo(document.body);
				$float.find('.message').text(errorMessage);
				$float.click(function(){
					$float.hide(POPUP_SHOW_SPEED);
				});
				$float.data('$element' , $element);
				//显示设置位置
				//这里应该直接设置位置而不是focus才设置....需要考虑
				//$element.focus(function() {
				//	if ($element.hasClass('invalid') && !$element.hasClass('no-popup')) {
						$float.show(POPUP_SHOW_SPEED);
						fooee.validation._setErrorPopupPosition($float);
				//	}
				//});
						$element.data('$errorPopup' , $float);
				return $float;
			},
			
			repositionErrorFloats : function() {
				$('.error-float:visible').each(function() {
					fooee.validation._setErrorPopupPosition($(this));
				});
			},

			_updateErrorFloat : function($element , errorMessage){
				$errorPopup = $element.data('$errorPopup');
				$errorPopup.find('.message').text(errorMessage);
				fooee.validation._setErrorPopupPosition($errorPopup);
				return $errorPopup;
			},

			//set place 
			//数值对但无缘无故会小跳一下....  -_-  .............
			//好像是因为element边框原因
			_setErrorPopupPosition : function($popup){
				var $element = $popup.data('$element');
				if (!$element.is(':visible'))
				{
					$popup.hide(POPUP_SHOW_SPEED);
					return;
				}
				$popup.show(POPUP_SHOW_SPEED);
				var popupWidth = $popup.width();
				var popupHeight= $popup.height();
				var elementOffset = $element.offset();
				var elementWidth = $element.width();
				var windowWidth = $(window).width();

				var popupOffset = {};
				popupOffset.top = elementOffset.top + POPUP_MARGIN_TOP;
				
				if ( (elementOffset.left + elementWidth + popupWidth) < windowWidth){
					popupOffset.left = elementOffset.left + elementWidth + POPUP_MARGIN_LEFT;
				}else if (elementOffset.left > popupWidth){
					popupOffset.left = elementOffset.left + elementWidth + POPUP_MARGIN_LEFT;
				}else{
					popupOffset.left = elementOffset.left + elementWidth + POPUP_MARGIN_LEFT;
				}
				//alert(popupOffset.top);
				$popup.offset(popupOffset);
			}
		},
		
		/*
		 * ajax分页
		 * 庞振华(20130805)
		 */
		pager : {
			para : null,		//参数对象
			page : null ,		//当前页码
			pageSize : null , 	//每页显示
			totalRecord : null ,//总记录数
			pager : null , 		//显示对象
			callBackExecute : null , //点击分页回调
			totalLink : 10,
			categoryId : null,
			execute : function(){
				var page = fooee.pager.page;
				var pageSize = this.pageSize;;
				var categoryId = this.categoryId;
				var totalRecord = this.totalRecord;
				var pager = this.pager;
				var totalLink = this.totalLink;
				
				pager.empty();
				
				//计算页数
				var totalPage = Math.ceil( totalRecord / pageSize );

				if (totalPage <= 1){
					return false;
				}
				
				//首页
				if (page == 1){
					pager.append("<span class='noClick'>首页</span>");
				}else{
					var first = $("<a href='javascript:void(0);' first='"+ 1 +"' >首页</a>").click(function(){
						fooee.pager.callBackExecute( parseInt( $(this).attr("first") ) );
						return false;
					});
					pager.append(first);
				}
				
				//上一页
				if (page == 1){
					pager.append("<span class='noClick'>上一页</span>");
				}else{
					var pre = $("<a href='?page="+ (page-1) +"' execute='"+ fooee.pager.callBackExecute.name +"' page='"+ (page-1) +"' onclick='return false;'>上一页</a>").click(function(){
						fooee.pager.callBackExecute( parseInt( $(this).attr("page") ) );
						return false;
					});
					pager.append(pre);
				}
				
				//计算显示的开始页码和结束页码
				var tempNumStart = 1;
				var tempNumEnd = totalPage;
				
				tempNumStart = Math.max(1, page - parseInt(totalLink/2));
				tempNumEnd = Math.min(totalPage, tempNumStart + totalLink - 1);
				tempNumStart = Math.max(1, tempNumEnd - totalLink + 1); 
				
				for(i = tempNumStart; i<= tempNumEnd; i++){
					if(i == page){
						pager.append("<span class='noClick'>" + i + "</span>");
					}else{
						var link = $("<a href='javascript:void(0);' page='"+ parseInt(i) +"'>" + i + "</a>").click(function(){
							fooee.pager.callBackExecute( parseInt( $(this).attr("page") ) );
							return false;
						});
						pager.append(link);
					}
				}
				
				//记录信息
				pager.append(" <span class='curyema'>"+ page +"/"+ totalPage +"</span> ");
				
				//下一页
				if (page >= totalPage){
					pager.append("<span class='noClick'>下一页</span>");
				}else{
					var next = $("<a href='javascript:void(0);' next='"+ parseInt((page+1)) +"'>下一页</a>").click(function(){
						fooee.pager.callBackExecute( parseInt( $(this).attr("next") ) );
						return false;
					});
					pager.append(next);
				}

				//尾页
				if (page >= totalPage){
					pager.append("<span class='noClick'>尾页</span>");
				}else{
					var last = $("<a href='javascript:void(0);' last='"+ totalPage +"'>尾页</a>").click(function(){
						fooee.pager.callBackExecute( parseInt( $(this).attr("last") ) );
						return false;
					});
					pager.append(last);
				}
				
				//生成页码
				// var tempPage = null;
				// for(i = 1 ; i <= totalPage ; i++ ){
				// 	if (i == page){
				// 		tempPage = tempPage + "<option value='"+ i +"' selected>"+ i +"</option>";
				// 	}else{
				// 		tempPage = tempPage + "<option value='"+ i +"'>"+ i +"</option>";
				// 	}
				// }
				// var selectPage = $("<select>"+ tempPage +"</select>").change(function(){
				// 	fooee.pager.callBackExecute( parseInt( $(this).children('option:selected').val() ) );
				// 	return false;
				// });
				// pager.append(selectPage);

				/**
				 * 总数据统计
				 */
				pager.append("<span class='noClick'>共"+ totalRecord +"条</span>");
				
				//点击分页后刷新货币符号
				//fooee.formatMoney();
			}
		},
		
		/*
		 *格式化工具
		 *庞振华(20130725)
		 */
		format : {
			//格式化日期时间
			formatDatetime2 : function (obj){
				var myDate = new Date(obj);
				var year = myDate.getFullYear();
				var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
				var day = ("0" + myDate.getDate()).slice(-2);
				var hour = ("0" + myDate.getHours()).slice(-2);
				
				return year + '-' + month + '-' + day;
			},
		
			//修正后的格式化日期时间方法,直接解析字符串,结果类似 2013-01-01
			formatDatetime : function (obj){
				if(obj == null){
					return null;
				}
				str = obj.replace("T"," ");
				str = str.split(" ");
				str = str[0].split("-");
				//var date = new Date();
				return str[0] + '-' + str[1] + '-' + str[2];
				
				//date.setUTCFullYear(str[0], str[1]-1 , str[2]);
				//alert(date);
				//date.setUTCHours(0, 0, 0, 0);
				//return date;
			},
			
			//格式化时间 结果类似 : 2013-01-01 10:10:01
			formatDatetimeWithTime : function (obj){
				str = obj.replace("T"," ");
				return str;
			},

			date : {

                /**
				 * 时间戳转换为日期格式
                 * @param timestamp	时间戳
                 * @param format	格式 yyyy:MM:dd h:m:s
                 * @returns {string}
                 */
				timestampToDate:function (timestamp, format) {
					// 补全为13位?为什么要补
					// var arrTimestamp = (timestamp + '').split('');
					// for (var start = 0; start < 13; start++) {
					// 	if (!arrTimestamp[start]) {
					// 		arrTimestamp[start] = '0';
					// 	}
					// }
					// timestamp = arrTimestamp.join('') * 1;

					var datetime = new Date(timestamp);

					var date = {
						"M+": datetime.getMonth() + 1,
						"d+": datetime.getDate(),
						"h+": datetime.getHours(),
						"m+": datetime.getMinutes(),
						"s+": datetime.getSeconds(),
						"q+": Math.floor((datetime.getMonth() + 3) / 3),
						"S+": datetime.getMilliseconds()
					};
					if (/(y+)/i.test(format)) {
						format = format.replace(RegExp.$1, (datetime.getFullYear() + '').substr(4 - RegExp.$1.length));
					}
					for (var k in date) {
						if (new RegExp("(" + k + ")").test(format)) {
							format = format.replace(RegExp.$1, RegExp.$1.length == 1 ?
								date[k] : ("00" + date[k]).substr(("" + date[k]).length));
						}
					}
					return format;
                },
                /**
				 * 时间戳转换为字符串
				 * 如：刚刚、一分钟前、一小时前、一天前、超过一年直接显示年月日
                 * @param timestamp
                 * @returns {*}
                 */
				timestampToStr:function (timestamp) {
                    // 补全为13位
                    var arrTimestamp = (timestamp + '').split('');
                    for (var start = 0; start < 13; start++) {
                        if (!arrTimestamp[start]) {
                            arrTimestamp[start] = '0';
                        }
                    }
                    timestamp = arrTimestamp.join('') * 1;

                    var minute = 1000 * 60;
                    var hour = minute * 60;
                    var day = hour * 24;
                    var halfamonth = day * 15;
                    var month = day * 30;
                    var now = new Date().getTime();
                    var diffValue = now - timestamp;

                    // 如果本地时间反而小于变量时间
                    if (diffValue < 0) {
                        return '不久前';
                    }

                    // 计算差异时间的量级
                    var monthC = diffValue / month;
                    var weekC = diffValue / (7 * day);
                    var dayC = diffValue / day;
                    var hourC = diffValue / hour;
                    var minC = diffValue / minute;

                    // 数值补0方法
                    var zero = function (value) {
                        if (value < 10) {
                            return '0' + value;
                        }
                        return value;
                    };

                    // 使用
                    if (monthC > 12) {
                        // 超过1年，直接显示年月日
                        return (function () {
                            var date = new Date(timestamp);
                            return date.getFullYear() + '年' + zero(date.getMonth() + 1) + '月' + zero(date.getDate()) + '日';
                        })();
                    } else if (monthC >= 1) {
                        return parseInt(monthC) + "个月前";
                    } else if (weekC >= 1) {
                        return parseInt(weekC) + "周前";
                    } else if (dayC >= 1) {
                        return parseInt(dayC) + "天前";
                    } else if (hourC >= 1) {
                        return parseInt(hourC) + "小时前";
                    } else if (minC >= 1) {
                        return parseInt(minC) + "分钟前";
                    }
                    return '刚刚';
                }

			},
			
			//格式化货币显示
			formatCurrency : function(element , roundToDecimalPlace){
				$(element).formatCurrency({ roundToDecimalPlace:roundToDecimalPlace });
			}
		},
		
		/*
		 * 格式化货币
		 */
		formatMoney : function(){
			//设置全站货币格式 
			fooee.format.formatCurrency(".m");
			fooee.format.formatCurrency(".m0" , 0);
		},
		
		/*
		 *删除确认提示
		 *庞振华(20130802)
		 */
		confirmDel : function(){
			if (confirm("删除后不可恢复,确定要删除吗？")){
				return true;
			}else{
				return false;
			}
		},
		
		/**
		 * 转换字符串为对象
		 */
		stringToObj : function(str){
			var rplData = str.split("&");
			var b = {};
			for(i=0; i<rplData.length; i++){
				var rplRec = rplData[i].split("=");
				var name = rplRec[0];
				var value = rplRec[1];
	 				if(null != value && "" != value){
	 					b[name] = decodeURI(value);
	 				}
			}
			return b;
		},
		
		/**
		 * 表单转为对象
		 */
		formToObj : function(form){
			var str = form.serialize();
			var rplData = str.split("&");
			var b = {};
			for(i=0; i<rplData.length; i++){
				var rplRec = rplData[i].split("=");
				var name = rplRec[0];
				var value = rplRec[1];
	 				if(null != value && "" != value){
	 					b[name] = decodeURI(value);
	 				}
			}
			return b;
		},

		/**
		 * 获得字符串实际长度，中文2，英文1
		 * 修改为英文和中文统一计算为1
         */
		strlen:function strlen(str) {
			var regExp = new RegExp(" ","g");
			str = str.replace(regExp , "");
			str = str.replace(/\r\n/g,"");
			var realLength = 0, len = str.length, charCode = -1;
			for (var i = 0; i < len; i++) {
				charCode = str.charCodeAt(i);
				// if (charCode >= 0 && charCode <= 128) realLength += 1;
				// else realLength += 2;
				realLength += 1;
			}
			return realLength;
		},

		/**
		 * 正则表达式 替换括号,尖括号等,包括换行符
		 * @param s
         * @returns {*}
         */
		replaceHtml:function replaceHtml(str){
			str = str.replace(new RegExp("&",'g'), "&amp;");
			str = str.replace(new RegExp("<",'g'), "&lt;");
			str = str.replace(new RegExp(">",'g'), "&gt;");
			str = str.replace(new RegExp("\n",'g'), "<br/>");
			str = str.replace(new RegExp("\r\n",'g'), "<br/>");
			return str;
		},

		/**
		 * cookie操作
		 */
		cookie : {
			/**
			 * 获取指定名称的cookie的值
			 * @param name
			 * @returns {string}
			 */
			get : function (name) {
				var arrstr = document.cookie.split("; ");
				for(var i = 0;i < arrstr.length;i ++){
					var temp = arrstr[i].split("=");
					if(temp[0] == name) return unescape(temp[1]);
				}
			},
			/**
			 * 设置cookie
			 * @param name
			 * @param value
			 * @param expried 毫秒
			 */
			set : function (name, value, expried) {
				var exp = new Date();
				exp.setTime(exp.getTime() + expried);
				document.cookie = name + "="+ escape (value) + ((expried==null) ? "" : ";expires="+exp.toGMTString());
			}
		},

		/**
		 * html转码
		 */
		html : {
			encode : function (str) {
				var s = "";
				if(str.length == 0) return "";
				s = str.replace(/&/g,"&amp;");
				s = s.replace(/</g,"&lt;");
				s = s.replace(/>/g,"&gt;");
				s = s.replace(/ /g,"&nbsp;");
				s = s.replace(/\'/g,"&#39;");
				s = s.replace(/\"/g,"&quot;");
				return s;
			},
			decode : function (str) {
				var s = "";
				if(str.length == 0) return "";
				s = str.replace(/&amp;/g,"&");
				s = s.replace(/&lt;/g,"<");
				s = s.replace(/&gt;/g,">");
				s = s.replace(/&nbsp;/g," ");
				s = s.replace(/&#39;/g,"\'");
				s = s.replace(/&quot;/g,"\"");
				return s;
			}
		}
	};
})(jQuery);



function readVerificationCode(readUrl, elementID){
	readUrl = (readUrl!=null && readUrl!="") ? readUrl : "";
	elementID = (elementID!=null && elementID!="") ? elementID : "verificationCode";
	document.getElementById(elementID).src = readUrl + "Action/VerificationCodeAction!service.action";
}