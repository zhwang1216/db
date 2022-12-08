$(function(){	//全选和全不选
    //给全选的复选框添加事件
    $("#all").click(function(){
        // this 全选的复选框
        var userids=this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=id]").each(function(){
            this.checked=userids;
        });
    });
    //给name=box的复选框绑定单击事件
    $("input[name=id]").click(function(){
        if( $("input[name=id]:checked").length/* 获取选中复选框长度 */ == $("input[name=id]").length/* 未选中的长度 */ ){
            $("#all").get(0).checked=true;
        }else{
            $("#all").get(0).checked=false;
        }
    });
});

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
		location.href="DrugSalesUser.html"
	})
	//函数
	function display(pageNoStr,pageSizeStr){
		var drugName = $("#query-drugName").val();
		var storageTime = $("#query-storageTime").val();
		$.ajax({
			url:'/drugSales/selectDrugSales',
			data:{
				pageNoStr:pageNoStr,
				pageSizeStr:pageSizeStr,
				drugName:drugName,
				storageTime:storageTime
			},
			type:'post',
			success:function(json){
				var htmlStr="";
				$.each(json.data.dataList,function(index,obj){
htmlStr+="<tr>"
htmlStr+="<td>"+obj.drugName+"</td>"
htmlStr+="<td>"+obj.price+"</td>"
htmlStr+="<td>"+obj.inventoryQuantity+"</td>"
htmlStr+="<td>"+obj.inventory+"</td>"
htmlStr+="<td>"+obj.storageTime+"</td>"
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
})