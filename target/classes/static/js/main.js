
//页面数据展示
$(document).ready(function(){
	//页面加载完成，执行查询方法
	$.ajax({
		"url" : "/customer/selectIdCount", 
		"type" : "post",
		"dataType" : "json",
		"success" : function(json) {
			if( json.state == 200){
				$("#count1").html(json.data)
			} else{
				alert(json.message);
			}
		},
		"error":function(){
			
		}
	});
	//页面加载完成，执行查询方法
	$.ajax({
		"url" : "/supplier/selectIdCount", 
		"type" : "post",
		"dataType" : "json",
		"success" : function(json) {
			if( json.state == 200){
				$("#count2").html(json.data)
			} else{
				alert(json.message);
			}
		},
		"error":function(){
			
		}
	});
	//页面加载完成，执行查询方法
	$.ajax({
		"url" : "/drug/selectIdCount", 
		"type" : "post",
		"dataType" : "json",
		"success" : function(json) {
			if( json.state == 200){
				$("#count3").html(json.data)
			} else{
				alert(json.message);
			}
		},
		"error":function(){
			
		}
	});
	//页面加载完成，执行查询方法
	$.ajax({
		"url" : "/drugSales/selectIdCount", 
		"type" : "post",
		"dataType" : "json",
		"success" : function(json) {
			if( json.state == 200){
				$("#count4").html(json.data)
			} else{
				alert(json.message);
			}
		},
		"error":function(){
			
		}
	});
})

//图表展示
$(document).ready(function(){
	//获取存放图表的地址
	var myChart = echarts.init(document.getElementById('larry-seo-stats'));
    //封装时间格式
    function format(time, format) {
      var t = new Date(time);
      var tf = function (i) {
        return (i < 10 ? '0' : '') + i
      };
      return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
        switch (a) {
          case 'yyyy':
            return tf(t.getFullYear());
            break;
          case 'MM':
            return tf(t.getMonth() + 1);
            break;
          case 'mm':
            return tf(t.getMinutes());
            break;
          case 'dd':
            return tf(t.getDate());
            break;
          case 'HH':
            return tf(t.getHours());
            break;
          case 'ss':
            return tf(t.getSeconds());
            break;
        }
      })
    }
    //转换当前时间格式
    var getDate=new Date();
    console.log(getDate);    //输出Mon Oct 30 2017 14:33:20 GMT+0800 (中国标准时间)
    var datetime=format(getDate,'yyyy-MM-dd');
    console.log(datetime);     //输出2017-10-30
   
    //封装客户图表展示
    function createdTimeYear() {
    	$.ajax({
			"url" : "/customer/selectYearTime", 
			"data" : "createdTime="+ ($("input[name='createdTime']").val() == "" ? datetime :$("input[name='createdTime']").val() ),
			"type" : "post",
			"dataType" : "json",
			"success" : function(json) {
				if( json.state == 200){
					//准备JSON数据
        			var names = [];
        			var brower = [];
        			for(var i=0;i<json.data.length;i++){
            			names.push(json.data[i].month);
            			brower.push(json.data[i].count);
        			}
        			//展示图表
					option = {
						legend: {
					        data:['客户信息统计｛注册量/月份，的数据统计｝']
					    },
					    xAxis: {
					        type: 'category',
					        data: names,
					        axisLabel: {
					            formatter: '{value} 月'
					        },
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [{
					        name:'客户信息统计｛注册量/月份，的数据统计｝',
					    	data: brower ,
					        type: 'line',
					        symbol: 'triangle',
					        symbolSize: 20,
					        lineStyle: {
					            normal: {
					                color: 'green',
					                width: 4,
					                type: 'dashed'
					            }
					        },
					        itemStyle: {
					            normal: {
					                borderWidth: 3,
					                borderColor: 'yellow',
					                color: 'blue'
					            }
					        }
					    }]
					};
					myChart.setOption(option);
				} else{
					alert(json.message);
				}
			}
		});
    }
    
	//页面一加载就执行
	createdTimeYear();
	
	$("#btn-echarts").click(function(){
		createdTimeYear();
	})
})