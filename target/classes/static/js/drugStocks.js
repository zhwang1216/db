	//全选和全不选
	$(function(){
		
		display(1,10,0);
		//添加删除点击事件
		$("#btn-delete").click(function(){
			//删除函数
			doDelteObejct();
		});
		
		//添加编辑点击事件
		$("#btn-update").click(function(){
			doCompile();
		});
		
		//添加查询点击事件
		$("#query-action-btn").click(function(){
			display(1,10,0);
		});
		
		//添加查询全部点击事件
		$("#query-action-btn-all").click(function(){
			display(1,10,1);
		});
		
		//添加提交修改点击事件
		$("#btn-update-drugCategory").click(function(){
			
			/* alert($("#modal_id").val());
			alert($("#modal_documentNo").val());
			alert($("#modal_drugName").val());
			alert($("#modal_empName").val());
			alert($("#modal_inventory").val());
			alert($("#modal_inventoryQuantity").val());
			alert($("#modal_price").val());
			alert($("#modal_storageTime").val());
			alert($("#modal_supName").val()); */
			
			/* params = {
				//分页查询
				"pageNoStr" : pageNoStr,
				"pageSizeStr" : pageSizeStr,
				//查询条件
				"documentNo" : documentNo,
			} */
			
			$.ajax({
				//修改url地址
				"url":"/drugstock/update",
				"type":"POST",
				"data":$("#form-DrugCategory").serialize(),
				"dataType":"json",
				"success":function(json){
					if(json.state==200){
						alert("修改成功!!!");
						window.location.reload();
					}else{
						alert(json.message);
					}
				}
			});
			
		});
		
	    $("#all").click(function(){
	        var userids=this.checked;
	        $("input[name=id]").each(function(){
	            this.checked=userids;
	        });
	    });
	    
	    $("input[name=id]").click(function(){
	        if( $("input[name=id]:checked").length == $("input[name=id]").length ){
	            $("#all").get(0).checked=true;
	        }else{
	            $("#all").get(0).checked=false;
	        }
	    });
	    
	});
	
	//函数
	function display(pageNoStr,pageSizeStr,all){
		var documentNo = $("#inp-documentNo").val();
		if(all==1){
			documentNo = "";
		}
		
		params={
				//分页查询
				"pageNoStr":pageNoStr,
				"pageSizeStr":pageSizeStr,
				//查询条件
				"documentNo":documentNo,
		}
		$.ajax({
			"url":"drugstock/findall",
			"data":params,
			"type":'GET',
			"success":function(json){
				var htmlStr="";
				$.each(json.data,function(){
					htmlStr += doCreateTd(this);
				});
				//展示到页面上
				$("tbody").html(htmlStr);
				//页面插件
				//计算总页数
				var totalPages =1;
				if( json.data.count%pageSizeStr == 0 ){
					totalPages = json.data.length/pageSizeStr;
				}else{
					totalPages = parseInt(json.data.length/pageSizeStr)+1;
				}
				
				$("#query-page").bs_pagination({
					currentPage:pageNoStr,	//当前页码
					rowsPerPage:pageSizeStr,	//每页显示条数
					totalRows:json.data.length,	//总条数
				    totalPages: totalPages,//总页数
				    
				    visiblePageLinks:5,	//最多显示的卡片数
				    showGoToPage:true,		//是否显示跳转到几页
				    showRowsPerPage:true,	//是否显示每页显示条数
				    showRowsInfo:true,		//是否显示记录条数
				    //用来监听页号切换的事件，event代表事件，pageobj代表页面信息
				    onChangePage:function(event,pageObj){
				    	display(pageObj.currentPage,pageObj.rowsPerPage,0);
				    }
				    
				});
			}
		})
	};
	
	
		function doDelteObejct(){
			//获取被选中的数据 并且取出里面的id
			var ckdids = $("input[name='id']:checked");
			if( ckdids.size() == 0 ){
				alert("请选择要删除的信息");
				return;
			}
			var ids = "";
			$.each(ckdids,function(){
				ids += this.value+",";
			});
			ids = ids.substr(0,ids.length-1);
			
			if( window.confirm("确定删除吗？？？") ){
				url="drugstock/deletebyid"
				params={
					"Id":ids
				}
				$.ajax({
					"url":url,
					"type":"POST",
					"data":params,
					"success":function(json){
						if(json.state==200){
							alert("操作成功!!!");
						}else{
							alert(json.message);
						}
					}
				})
			}
			// 调用查询方法,用于刷新页面
			display(1,10,0);
		}
		
		function doCompile(){
			//清空下拉选  不清空数据会堆加
			$("#modal_drugName").html("");
			$("#modal_empName").html("");
			$("#modal_supName").html("");
			
			var ckdids = $("input[name='id']:checked");
			
			if( ckdids.size() == 0 ){
				alert("请选择要修改的记录");
				return;
			}
			
			if( ckdids.size() > 1 ){
				alert("修改只能选择一条记录");
				return;
			}
			
			var id = "";
			$.each(ckdids,function(){
				id = this.value;
			});
			
			//首先完成数据回显(直接读取点击的id里面的数据)
			var ckdids = $("input[name='id']:checked")[0];
			$("#modal_id").attr("value",ckdids.value);
			//需要回显的每个字段的id
			//modal_documentNo modal_drugName modal_empName modal_inventory modal_inventoryQuantity modal_price modal_storageTime modal_supName
			
			//先获取需要修改的id的所有val (利用标签选择器,选择兄弟标签)
			var documentNo = $("input[name='id']:checked").parent().next();
			$("#modal_documentNo").val(documentNo.text());
			var drugname = documentNo.next();
			$("#modal_drugName").append("<option value=''>"+drugname.text()+"</option>");
			var empname = drugname.next();
			$("#modal_empName").append("<option value=''>"+empname.text()+"</option>");
			var inventory = empname.next();
			$("#modal_inventory").val(inventory.text());
			var inventoryquantity = inventory.next();
			$("#modal_inventoryQuantity").val(inventoryquantity.text());
			var price = inventoryquantity.next();
			$("#modal_price").val(price.text());
			var storagetime = price.next();
			$("#modal_storageTime").val(storagetime.text().substring(0,10));
			var supname = storagetime.next();
			$("#modal_supName").append("<option value=''>"+supname.text()+"</option>");
			
			//params里面还有后台实体类里面的数据
			params={
				"Id":id
			}
			
			//下拉选
			$.ajax({
				"url":"/drugstock/findid",
				"type":"GET",
				"dataType":"json",
				"success":function(json){
					//List<List<Map<Integer, String>>>
					var i=1;
					var html = "";
					var expression = "";
					$(json.data).each(function(){
						//List<Map<Integer, String>>   数组类型
						$(this).each(function(){
							//Map<Integer, String>   object类型
							
							$.each(this, function(key, values) {
								//if判断是否存在此条数据,如果存在给已经存在的标签添加value
								expression = i == 1 ? "#modal_drugName":i == 2 ? "#modal_supName":"#modal_empName";
								
								if(!($(expression).text() == values)){
									html +="<option value='"+key+"'>"+values+"</option>"
								}else{
									html = "<option value='"+key+"'>"+values+"</option>" + html
								}
								/* if(!($(expression).text() == values)){
									$(expression).append(
										"<option value='"+key+"'>"+values+"</option>"
									);
								} */
								
								/* if($(expression).val() == values){
									alert("进来了");
									//添加value
									//首先找到最大的兄弟元素     $("div:first-child") 匹配是子元素 并且是第一个div子元素
									//$("div:nth-child(n)") n从1开始 匹配是子元素并且是第n个div子元素
									//attr(属性名, 属性值)   //设置属性的值 （为所有匹配的元素设置一个属性值。）
									$("select:nth-child("+i+")").attr("value",key);
								} */
							});
						})
						i++;
						$(expression).html(html);
						html="";
					})
				}
			});
		}
		
		function doCreateTd(obj){
			var tds=
			"<tr>"+
			"<th width='40' class='text-center'>"+"<input name='id' type='checkbox' value='"+obj.drugId+"'>"+"</th>"+
			"<th>"+obj.documentNo+"</th>"+
			"<th>"+obj.drugName+"</th>"+
			"<th>"+obj.empName+"</th>"+
			"<th>"+obj.inventory+"</th>"+
			"<th>"+obj.inventoryQuantity+"</th>"+
			"<th>"+obj.price+"</th>"+
			"<th>"+obj.storageTime+"</th>"+
			"<th>"+obj.supName+"</th>"+
			"</tr>"
			return tds;
		}