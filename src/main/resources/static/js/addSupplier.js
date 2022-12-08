	$("#btn-addnew").click(function() {
		//发出异常请求,并处理结果
		//url:将请求提交到哪里去
		//data:提交的请求参数,例如username=root&password=1234
		//type:提交方式
		//dataType:服务器端即将响应的数据类型,取值可以是"json","xml","text"例如服务器端响应的可能是application/json,该属性值应该是"json"
		//success:当服务器成功响应时(Http响应吗是2xx)的回调函数
		$.ajax({
			"url":"/supplier/addnew",
			"data":$("#form-addnew").serialize(),
			"type":"POST",
			"dataType":"json",
			"success":function(json){
				if(json.state == 200){
					alert("添加成功!!!");
				}else{
					alert(json.message);
				}
			}
		});
	});