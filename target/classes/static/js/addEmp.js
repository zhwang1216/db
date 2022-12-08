	$("#btn-addemp").click(function(){
		$.ajax({
			"url":"/employees/addEmp",
			"data":$("#form-addemp").serialize(),
			"type":"POST",
			"dataType":"json",
			"success":function(json){
				if(json.state == 200){
					alert("添加成功");
					location.reload();
				}else{
					alert(json.message);
				}
			},
		});
	});