In.ready('jqGrid','queryDataBox','multipleDataBox','select',function() {

	var jqGrid = $("#jqGrid");

	//供应商插件
	$('.query-btn').queryDataBox({
		url:appPath+'/system/json/findSupplierList?temp='+new Date(),//请求地址
		para:{state:1} //请求参数state 1:表示有商务关系，0：表示所有启用的数据
	});
	//多条件查询控件
	$('.conditions-btn').multipleDataBox({
	});
	//清空按钮
	$('.del').click(function(){
		$(this).parent().find('input').val('');
	});


	/**
	 * 编辑按钮
     */
	$("#btn_update").click(function () {
		var selectIds = jQuery("#jqGrid").jqGrid('getGridParam','selarrrow');
		if (selectIds.length == 0){
			alert('请选择一条数据!');
			return false;
		}
		if (selectIds.length > 1) {
			alert('只能选择一条数据!');
			return false;
		}
		window.location.href = "/book/update/" + selectIds;
	});

	/**
	 * 查询按钮
	 */
	$("#queryBtn").bind("click",function(){
		var postData = $('#jqGrid').jqGrid("getGridParam", "postData");
		$.each(postData, function (k, v) {
			delete postData[k];
		});
		jqGrid.jqGrid("setGridParam",{
			page:1,
			postData:queryParam()
		}).triggerHandler("reloadGrid");
	});

	jqGrid.jqGrid({
		styleUI: 'Bootstrap',//设置jqgrid的全局样式为bootstrap样式
	    ajaxGridOptions:{contentType : "application/json; charset=utf-8"},
        mtype:"post",
        datatype: "json",
		url:appPath+'/query',
		prmNames : {rows:"limit", page:"page",sort: "sortField", order:"sortMode"}, //设置请求参数名称映射
		jsonReader: {root:"data", records:"total", total: "totalPage", repeatitems : false}, //设置数据解析名称映射
		postData: queryParam(),
		serializeGridData: function(postData) {
			return JSON.stringify(postData);
		},
		autowidth: true,
		shrinkToFit: true,
		height:"auto",
		colNames:["id","昵称","标题","类型","评分","点赞","状态","注册时间"],
		colModel :[
			{name:"id", index:"id",width:"30"},
			{name:"userNickname", index:"userNickname",align:'center'},
			{name:"commentTitle", index:"commentTitle",align:'center'},
			{name:"objectTypeCode", index:"objectTypeCode",align:'center'},
            {name:"scoreNumber", index:"score_number",align:'center'},
            {name:"agreeNumber", index:"agree_umber",align:'center'},
			{name:"statusCode", index:"statusCode",align:'center'},
			{name:"createTime", index:"create_time",align:'center',formatter:function(cellvalue, options, row){return fooee.format.date.timestampToDate(cellvalue,'yyyy-MM-dd');}},
		],
		rowNum:20,
		rowList:[20,50,100,500],
		viewrecords: true,
		multiselect : true,  //显示checkbox选择框
		rownumbers: true,    //显示左边排名列表
		sortname: 'create_time', 	//设置默认的排序列
		sortorder: 'desc',	//默认排序方式
		pager: '#page',
		loadBeforeSend:function(){
			jqGrid.jqGrid('clearGridData');
		},
		loadError:function(){
			layer.msg("网络错误！",{icon:5});
		},
		onPaging : function(pgButton){
			// $("#tbList").jqGrid('setGridParam',
			// 	{
			// 		datatype:'json',
			// 		postData:queryParam()
			// 	});
		},
		loadComplete : function(data) {
			if(data.error){
				layer.msg(data.message,{icon:5});
			}
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


	/**
	 * 查询参数
	 * @param exportFlags 勾选导出的数据标识(全部导出还是勾选导出)
	 */
	function queryParam(){

		var queryRequest = {};
		queryRequest.queryId = "selectCommentVoByPage";
		queryRequest.queryType = "JQGRID";
		queryRequest.queryParameters = new Array();

		//组装查询参数
		var formData = $("#queryForm").serializeArray();
		$.each(formData,function (i,field) {
			var name = field.name;
			var value = field.value;
			var queryParameter = {};

			//无值不加入条件
			if(name == '' || value == ''){
				return true;
			}

			//昵称处理单条查询还是批量查询
			if(name =='nickNames'){
				if(value.indexOf(",") == -1){
					name = 'nickName';
				}else{
					name = 'nickNames';
				}
			}

			//手机号处理单条查询还是批量查询
			if(name =='mobilePhoneNos'){
				if(value.indexOf(",") == -1){
					name = 'mobilePhoneNo';
				}else{
					name = 'mobilePhoneNos';
				}
			}


			queryParameter.field = name;
			queryParameter.value = value;

			queryRequest.queryParameters.push(queryParameter);
		});

		// return JSON.stringify(queryRequest);
		return queryRequest;
	}
});//In.ready结束

var isFirstQuery = true;//是否是第一次查询

