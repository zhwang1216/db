$(document).ready(function(){
		var avatar = $.cookie("avatar");
		if(avatar != null){
			$("#img-avatar").attr("src",avatar);
		}
	});
	
$(document).ready(function(){
	var uid = $.cookie("uid");
	var permissions = null;
	permissions = $.cookie("permissions");
	if( uid != null ){
		//页面加载完成，执行查询方法
		$("#btn-change-avatar").click(function(){
			$.ajax({
				"url" : permissions == null ? "/customer/change_avatar" : "employees/change_avatar",
				"data":new FormData($("#form-change-avatar")[0]),
				"type":"post",
				"contentType":false,
				"processData":false,
				"dataType":"json",
				"success":function(json){
					if(json.state == 200){
						alert("修改成功");
						$("#img-avatar").attr("src",json.data);
						$.cookie("avatar",json.data,{expire:7});
					}else{
						alert(json.message);
					}
				},
				"error":function(){
					alert("您的登录信息已经过期,请重新登录!");
					top.location.href="login.html";
				}
			});
		});
	}else{
		alert("登录已经过期，请重新登录")
		top.location.href="login.html"
	}
})