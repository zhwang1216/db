/* 
	$("#demo").attr("style","display:none;");//隐藏div
	$("#demo").attr("style","display:block;");//显示div
*/
$(document).ready(function(){
	var username = $.cookie("username");
	if( username != null ){
		$("#login-username").html("账户名："+username);
	}
	var permissions = null;
	permissions = $.cookie("permissions");
	
	/*$("#System-management")	//系统管理
	$("#Supplier-management")	//供货商管理
	$("#Bill-management")	//账单管理
	$("#Staff-management")	//员工管理
	$("#Customer-management")	//客户管理
	$("#Drug-administration")	//药品管理
	$("#To-buy-drugs")	//购买药品
*/	
	if( permissions == null ){
		$("#Supplier-management").attr("style","display:none;");
		$("#Bill-management").attr("style","display:none;");
		$("#Staff-management").attr("style","display:none;");
		$("#Customer-management").attr("style","display:none;");
		$("#Drug-administration").attr("style","display:none;");
	}else if( permissions == 1 ){
		$("#Staff-management").attr("style","display:none;");
		$("#To-buy-drugs").attr("style","display:none;");
	}else{
		$("#To-buy-drugs").attr("style","display:none;");

	}
	
	//退出功能
	$("#btn-exit").click(function(){
		$.ajax({
			"url" : "/index/exit", 
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					top.location.href="login.html"
				}
			}
		});
	})
	
}); 