$(document).ready(function(){
	var avatar = $.cookie("avatar");
	if(avatar != null){
		$("#img-avatar").attr("src",avatar);
	}
	
	var uid = $.cookie("uid");
	var permissions = null;
	permissions = $.cookie("permissions");
	if( uid != null ){
		//修改数据
		$("#btn-change").click(function(){
			$.ajax({
				"url" : permissions == null ? "/customer/updateCustomer" : "/employees/change_EmpInfo", 
				"data": $("#form-change").serialize(),
				"type":"post",
				"dataType":"json",
				"success":function(json){
					if(json.state == 200){
						alert("修改成功");
						$("#img-avatar").attr("src",json.data);
						$.cookie("avatar",json.data,{expire:7});
					}else{
						alert("错误信息："+json.message);
					}
				},
				"error":function(){
					location.href='404.html';
				}
			});
		});
		//数据回显
		//页面加载完成，执行查询方法
		$.ajax({
			"url" : permissions == null ? "/customer/getfindByUid" : "/employees/show_EmpInfo", 
			"data" : "uid="+uid,
			"type":"get",
			"dataType":"json",
			"success":function(json){
				if(json.state == 200){
					//将服务器响应的用户数据显示到相应位置
					$("#username").val(json.data.username);
					$("#phone").val(json.data.phone);
					$("#email").val(json.data.email);
					$("#age").val(json.data.age);
					$("#cardBank").val(json.data.cardBank);
					$("#card").val(json.data.card);
					$("#uid").val(json.data.uid);
				}else{
					alert("错误信息："+json.message);
				}
			}
		});
	}else{
		alert("登录已经过期，请重新登录")
		top.location.href="login.html"
	}
})