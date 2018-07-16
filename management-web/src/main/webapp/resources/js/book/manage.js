In.ready('jqGrid','queryDataBox','multipleDataBox','select',function() {

	//供应商插件
	$('.query-btn').queryDataBox({
		url:appPath+'/system/json/findSupplierList?temp='+new Date(),//请求地址
		para:{state:1} //请求参数state 1:表示有商务关系，0：表示所有启用的数据
	});
	//多条件查询控件
	$('.conditions-btn').multipleDataBox({
	});
	//多条件查询ISBN控件
	$('.conditions-isbn-btn').multipleDataBox({
	});
	//多条件查询商品名称
	$('.conditions-bookTitles-btn').multipleDataBox({
	});
	//清空按钮
	$('.del').click(function(){
		$(this).parent().find('input').val('');
	});

	/**
	 * 查询按钮
	 */
	$("#queryBtn").bind("click",function(){
		$("#tbList").jqGrid("setGridParam",{
			ajaxGridOptions:{contentType : "application/json; charset=utf-8"},
			mtype:"post",
			datatype:'json',
			prmNames : {rows:"limit"}, //请求参数
			page:1,
			postData:queryParam()
		}).triggerHandler("reloadGrid");
	});

	$("#tbList").jqGrid({
	    ajaxGridOptions:{contentType : "application/json; charset=utf-8"},
        mtype:"post",
        datatype: "json",
		url:appPath+'/query',
		prmNames : {rows:"limit"}, //请求参数
		autowidth: true,
		shrinkToFit: true,
		height:"100%",
		colNames:["id","isbn","书名","定价","作者","出版社","出版日期","创建时间"],
		colModel :[
			{name:"id", index:"id", hidden:true},
			{name:"isbn", index:"isbn", hidden:true},
			{name:"bookName", index:"bookName",width:'12%',align:'center'},
			{name:"bookPrice", index:"bookPrice",width:'4%',align:'center'},
			{name:"authorName", index:"authorName",width:'6%',align:'center'},
			{name:"pressName", index:"pressName",width:'4%',align:'center'},
			{name:"publishDate", index:"publishDate",width:'12%',align:'center'},
			{name:"createTime", index:"createTime",width:'4%',align:'center'},
		],
		rowNum:20,
		rowList:[20,50,100,500],
		viewrecords: true,
		multiselect : true,  //显示checkbox选择框
		rownumbers: true,    //显示左边排名列表
		pager: '#page',
		jsonReader: {repeatitems : false}, //设置数据方式
		postData: queryParam(),
		loadBeforeSend:function(){
			$("#tbList").jqGrid('clearGridData');
		},
		loadError:function(){
			layer.msg("系统报错！",{icon:5});
		},
		onPaging : function(pgButton){
			$("#tbList").jqGrid('setGridParam',{datatype:'json',postData:{reqData:queryParam(),_search1:false}});
		},
		loadComplete : function(data) {
		}
	});
	$("[data-toggle='popover']").popover();




	$(function(){
		//展开收起搜索
		$('#open').toggle(
				function() {
					$(this).parents('.screenBox').find('.left ul .hide').show();
					$(this).find('i').removeClass('open');
					$(this).find('i').addClass('close');
					$(this).find('b').text('收 起');
				},function(){
					$(this).parents('.screenBox').find('.left ul .hide').hide();
					$(this).find('i').removeClass('close');
					$(this).find('i').addClass('open');
					$(this).find('b').text('展 开');
				}
		);
		/*$(".more-btn").on("click",function(){
		 var $this = $(this);
		 var $mbox = $this.parents(".row-fluid").eq(0).next(".more-box");
		 if($mbox.is(":visible")){
		 $mbox.slideUp();
		 $(this).text("自定义查询（展开▼）")
		 }else{
		 $mbox.slideDown();
		 $(this).text("自定义查询（收起▲）")
		 }
		 });*/
	});
});//In.ready结束

var isFirstQuery = true;//是否是第一次查询
/**
 * 查询参数
 * @param exportFlags 勾选导出的数据标识(全部导出还是勾选导出)
 */
function queryParam(){
    var queryRequest = {};
    queryRequest.queryId = "selectByPage";
	queryRequest.queryType = "JQGRID";
	queryRequest.page = 1;
	queryRequest.limit = 20;
	queryRequest.queryParameters = new Array();

	//组装查询参数
	var formData = $("form").serializeArray();
	$.each(formData,function (i,field) {
		var name = field.name;
		var value = field.value;
		var queryParameter = {};

		// alert(name+':'+value);

		//无值不加入条件
		if(name == '' || value == ''){
			return true;
		}

		//isbn处理单条查询还是批量查询
		if(name =='isbns'){
			if(value.indexOf(",") == -1){
				name = 'isbn';
			}else{
				name = 'isbns';
			}
		}

		//书名处理单条查询还是批量查询
		if(name =='bookNames'){
			if(value.indexOf(",") == -1){
				name = 'bookName';
			}else{
				name = 'bookNames';
			}
		}


		queryParameter.field = name;
		queryParameter.value = value;

		queryRequest.queryParameters.push(queryParameter);
	});

	return JSON.stringify(queryRequest);
}
