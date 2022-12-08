/**
 * dataTables
 */
$(function() {
	//页面一加载，执行的查询数据
	display(1,10);
	//点击查询按钮
	$("#query-action-btn").click(function(){
		display(1,$("#query-page").bs_pagination('getOption','rowsPerPage'));
	});
	//查询全部
	$("#query-action-btn-all").click(function(){
		location.href="LogOperation.html"
	})
	//函数
	function display(pageNoStr,pageSizeStr){
		var category = $("#query-category").val();
		var location = $("#query-location").val();
		var time = $("#query-time").val();
		$.ajax({
			url:'/logOperation/selectLogOperation',
			data:{
				pageNoStr:pageNoStr,
				pageSizeStr:pageSizeStr,
				category:category,
				location:location,
				time:time
			},
			type:'post',
			success:function(json){
				var htmlStr="";
				$.each(json.data.dataList,function(index,obj){
					htmlStr+="<tr>"
					htmlStr+="<td>"+obj.level+"</td>"
					htmlStr+="<td>"+obj.category+"</td>"
					htmlStr+="<td>"+obj.thread+"</td>"
					htmlStr+="<td>"+obj.time+"</td>"
					htmlStr+="<td>"+obj.location+"</td>"
					htmlStr+="<td>"+obj.note+"</td>"
					htmlStr+="</tr>"
				});
				//展示到页面上
				$("tbody").html(htmlStr);
				//页面插件
				//计算总页数
				var totalPages =1;
				if( json.data.count%pageSizeStr == 0 ){
					totalPages = json.data.count/pageSizeStr;
				}else{
					totalPages = parseInt(json.data.count/pageSizeStr)+1;
				}
				
				$("#query-page").bs_pagination({
					currentPage:pageNoStr,	//当前页码
					rowsPerPage:pageSizeStr,	//每页显示条数
					totalRows:json.data.count,	//总条数
				    totalPages: totalPages,//总页数
				    
				    visiblePageLinks:5,	//最多显示的卡片数
				    showGoToPage:true,		//是否显示跳转到几页
				    showRowsPerPage:true,	//是否显示每页显示条数
				    showRowsInfo:true,		//是否显示记录条数
				    //用来监听页号切换的事件，event代表事件，pageobj代表页面信息
				    onChangePage:function(event,pageObj){
				    	display(pageObj.currentPage,pageObj.rowsPerPage);
				    }
				    
				});
			}
		})
	};
});