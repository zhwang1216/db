	$(document).ready(function(){
		$.ajax({
			"url":"/drugstock/findid",
			"type":"GET",
			"dataType":"json",
			"success":function(json){
				//List<List<Map<Integer, String>>>
				var i=1;
				$(json.data).each(function(){
					//List<Map<Integer, String>>   数组类型
					$(this).each(function(){
						//Map<Integer, String>   object类型
						$.each(this, function(key, values) {
							//console.log(key); 输出key
							//console.log(values);  输出values
							$(i == 1 ? "#drg_id":i == 2 ? "#sup_id":"#emp_id").append(
								"<option value='"+key+"'>"+values+"</option>"
							);
						});
					})
					i++;
				})
			}
		});

		$("#btn-add").click(function() {
			//发出异常请求,并处理结果
			//url:将请求提交到哪里去
			//data:提交的请求参数,例如username=root&password=1234
			//type:提交方式
			//dataType:服务器端即将响应的数据类型,取值可以是"json","xml","text"例如服务器端响应的可能是application/json,该属性值应该是"json"
			//success:当服务器成功响应时(Http响应吗是2xx)的回调函数
			$.ajax({
				"url" : "/drugstock/insert",
				"data" : $("#form-add").serialize(),
				"type" : "POST",
				"dataType" : "json",
				"success" : function(json) {
					if (json.state == 200) {
						alert("添加成功!!!");
					} else {
						alert(json.message);
					}
				},
				"error":function(){
					alert("登录信息已经过期,请重新登录!!!");
					location.href="login.html";
				}
			});
		});
	});