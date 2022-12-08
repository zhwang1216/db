$(document).ready(function(){
	var uid = $.cookie("uid");
	var permissions = null;
	permissions = $.cookie("permissions");
	if( uid != null ){
		//页面加载完成，执行查询方法
		$.ajax({
			"url" : permissions == null ? "/customer/getfindByUid" : "/employees/show_EmpInfo", 
			"data" : "uid="+uid,
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					$("input[name='username']").val(json.data.username);
					$("input[name='gender']").val( json.data.gender==1?"男":"女" );
					$("input[name='age']").val(json.data.age);
					$("input[name='phone']").val(json.data.phone);
					$("input[name='email']").val(json.data.email);
					// $("input[name ='address']").val(json.data.address);
					$("input[name='cardBank']").val(json.data.cardBank);
					$("input[name='card']").val(json.data.card);
					$("img[name='avatar']").attr("src",json.data.avatar==null?"images/default.jpg":json.data.avatar);
					permissions == null ? $("input[name='address']").val(json.data.address):"";
				} else{
					alert(json.message);
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
