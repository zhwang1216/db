$(function() { //页面加载完成事件
	$("#btn-drug").click(function() {
		$.ajax({
			"url" : "/drugCategory/addDrugCategory",
			"data" : $("#form-DrugCategory").serialize(),
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					alert("添加成功");
					location.href="addDrugCategory.html"
				} else{
					alert(json.message);
				}
			},
			"error":function(){
				location.href='404.html';
			}
		});
		
	});
})