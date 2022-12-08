	layui.use(['form','upload'],function(){
         var form = layui.form();
	});
$(document).ready(function(){
	var uid = $.cookie("uid");
	var username = $.cookie("username");
	//存放uid,username
	$("input[name='uid']").val(uid);
	$("input[name='username']").val(username);
	var permissions = null;
	permissions = $.cookie("permissions");
	
	//修改密码
	$("#btn-change").click(function() {
		if( uid != null ){
			//页面加载完成，执行查询方法
			$.ajax({
				"url" : permissions == null ? "/customer/getfindByUidPassword" : "/employees/changePassword", 
				"data" : $("#form-change").serialize(),
				"type" : "post",
				"dataType" : "json",
				"success" : function(json) {
					if( json.state == 200){
						 top.location.href="login.html"
						top.location="login.html";
					} else{
						$("#username").val(json.message);
					}
				},
				"error":function(){
					location.href='404.html';
				}
			});
		}else{
			alert("登录已经过期，请重新登录")
			top.location.href="login.html"
		}
	})
	
})	