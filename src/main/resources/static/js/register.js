$(function() { //页面加载完成事件
	$("#btn-reg").click(function() {
		$.ajax({
			"url" : "/customer/reg",
			"data" : $("#form-reg").serialize(),
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					location.href="login.html"
				} else{
					/* alert(json.message); */
					$("#message").html(json.message);
				}
			},
			"error":function(){
				/* alert("填写信息有错误"); */
				$("#error").html(json.message);
			}
		});
		
	});
})