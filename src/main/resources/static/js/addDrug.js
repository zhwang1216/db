$(function() { //页面加载完成事件,药品类别
	$(document).ready(function() {
		$.ajax({
			"url" : "/drug/selectDrugCategory",
			"type" : "get",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					 $(json.data).each(function () {
                     	$("select[name='categoryId']").append( 
							"<option value="+this.categoryId+">"+this.categoryName+"</option>"
						);
					})
				} else{
					alert(json.message);
				}
			}
		});
	});
})

$(function() { //页面加载完成事件
	$("#btn-drug").click(function() {
		$.ajax({
			"url" : "/drug/addDrug",
			"data" : $("#form-Drug").serialize(),
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					alert("添加成功");
					location.href="addDrug.html"
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