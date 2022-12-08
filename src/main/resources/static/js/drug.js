$(function () {	//全选和全不选
    //给全选的复选框添加事件
    $("#all").click(function () {
        // this 全选的复选框
        var userids = this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=id]").each(function () {
            this.checked = userids;
        });
    });
    //给name=box的复选框绑定单击事件
    $("input[name=id]").click(function () {
        if ($("input[name=id]:checked").length/* 获取选中复选框长度 */ == $("input[name=id]").length/* 未选中的长度 */) {
            $("#all").get(0).checked = true;
        } else {
            $("#all").get(0).checked = false;
        }
    });
});

/**
 * dataTables
 */
$(function () {
    //页面一加载，执行的查询数据
    display(1, 10);
    //点击查询按钮
    $("#query-action-btn").click(function () {
        display(1, $("#query-page").bs_pagination('getOption', 'rowsPerPage'));
    });
    //查询全部
    $("#query-action-btn-all").click(function () {
        location.href = "drug.html"
    })

    //函数
    function display(pageNoStr, pageSizeStr) {
        var categoryId = $("#query-categoryId").find("option:selected").val();
        var drugName = $("#query-drugName").val();
        var unit = $("#query-unit").val();
        var origin = $("#query-origin").val();
        $.ajax({
            url: '/drug/selectDrug',
            data: {
                pageNoStr: pageNoStr,
                pageSizeStr: pageSizeStr,
                categoryId: categoryId,
                drugName: drugName,
                unit: unit,
                origin: origin
            },
            type: 'post',
            success: function (json) {
                var htmlStr = "";
                $.each(json.data.dataList, function (index, obj) {
                    htmlStr += "<tr>"
                    htmlStr += "<td width='40' class='text-center'><input type='checkbox' value='" + obj.id + "' name='id'  ></td>"
                    htmlStr += "<td>" + obj.drugName + "</td>"
                    htmlStr += "<td>" + obj.barCode + "</td>"
                    htmlStr += "<td>" + obj.referred + "</td>"
                    htmlStr += "<td>" + obj.categoryName + "</td>"
                    htmlStr += "<td>" + obj.specifications + "</td>"
                    htmlStr += "<td>" + obj.pleasedTo + "</td>"
                    htmlStr += "<td>" + obj.salesPrice + "</td>"
                    htmlStr += "<td>" + obj.inventory + "</td>"
                    htmlStr += "<td>" + obj.totalSales + "</td>"
                    htmlStr += "<td>" + obj.unit + "</td>"
                    htmlStr += "<td>" + obj.origin + "</td>"
                    htmlStr += "<td>" + obj.drugNote + "</td>"
                    htmlStr += "</tr>"
                });
                //展示到页面上
                $("tbody").html(htmlStr);
                //页面插件
                //计算总页数
                var totalPages = 1;
                if (json.data.count % pageSizeStr == 0) {
                    totalPages = json.data.count / pageSizeStr;
                } else {
                    totalPages = parseInt(json.data.count / pageSizeStr) + 1;
                }

                $("#query-page").bs_pagination({
                    currentPage: pageNoStr,	//当前页码
                    rowsPerPage: pageSizeStr,	//每页显示条数
                    totalRows: json.data.count,	//总条数
                    totalPages: totalPages,//总页数

                    visiblePageLinks: 5,	//最多显示的卡片数
                    showGoToPage: true,		//是否显示跳转到几页
                    showRowsPerPage: true,	//是否显示每页显示条数
                    showRowsInfo: true,		//是否显示记录条数
                    //用来监听页号切换的事件，event代表事件，pageobj代表页面信息
                    onChangePage: function (event, pageObj) {
                        display(pageObj.currentPage, pageObj.rowsPerPage);
                    }

                });
            }
        })
    };
    //药品类别
    $(document).ready(function () {
        $.ajax({
            "url": "/drug/selectDrugCategory",
            "type": "get",
            "dataType": "json",
            "success": function (json) {
                if (json.state == 200) {
                    $(json.data).each(function () {
                        $("select[name='categoryId']").append(
                            "<option value='" + this.categoryId + "'>" + this.categoryName + "</option>"
                        );
                    })
                } else {
                    alert(json.message);
                }
            }
        });
    });

//修改数据，根据药品类别id，查询药品数据
    $("#btn-update").click(function () {
        var ckdids = $("input[name='id']:checked");
        if (ckdids.size() == 0) {
            alert("请选择要修改的记录");
            return;
        }
        if (ckdids.size() > 1) {
            alert("修改只能选择一条记录");
            return;
        }
        var id = "";
        $.each(ckdids, function (index, obj) {
            id = obj.value;
        })
        $.ajax({
            "url": "/drug/findId",
            "data": "id=" + id,
            "type": "post",
            "dataType": "json",
            "success": function (json) {
                if (json.state == 200) {
                    $("input[name='id']").val(json.data.id);
                    $("input[name='drugName']").val(json.data.drugName);
                    $("input[name='referred']").val(json.data.referred);
                    $("input[name='specifications']").val(json.data.specifications);
                    $("input[name='unit']").val(json.data.unit);
                    $("input[name='salesPrice']").val(json.data.salesPrice);
                    $("input[name='inventory']").val(json.data.inventory);
                    $("input[name='totalSales']").val(json.data.totalSales);
                    $("input[name='origin']").val(json.data.origin);
                    $("textarea[name='drugNote']").val(json.data.drugNote);
                } else {
                    alert(json.message);
                }
            },
            "error": function () {
                location.href = '404.html'
            }
        });
    });
    //修改药品类别数据
    $("#btn-update-drug").click(function () {
        $.ajax({
            "url": "/drug/updateIdDrug",
            "data": $("#form-Drug").serialize(),
            "type": "post",
            "dataType": "json",
            "success": function (json) {
                if (json.state == 200) {
                    location.href = "drug.html"
                } else {
                    alert(json.message);
                }
            },
            "error": function () {
                location.href = '404.html';
            }
        });

    });
//删除数据，根据药品类别id，
    $("#btn-delete").click(function () {
        var ckdids = $("input[name='id']:checked");
        if (ckdids.size() == 0) {
            alert("请选择要删除的条数");
            return;
        }
        var id = "";	//拼接id
        $.each(ckdids, function (index, obj) {
            id += obj.value + ","
        })
        id = id.substr(0, id.length - 1);
        if (window.confirm("确定删除吗？？？")) {
            $.ajax({
                "url": "/drug/deleteIdDrug",
                "data": "id=" + id,
                "type": "post",
                "dataType": "json",
                "success": function (json) {
                    if (json.state == 200) {
                        display(1, $("#query-page").bs_pagination('getOption', 'rowsPerPage'));
                    } else {
                        alert(json.message);
                    }
                },
                "error": function () {
                    location.href = '404.html';
                }
            });
        }
    });
})
//数据导出
$(document).ready(function () {
    //药品类别
    $("#query-excel").click(function () {
        var categoryId = $("#query-categoryId").find("option:selected").val();
        var drugName = $("#query-drugName").val();
        var unit = $("#query-unit").val();
        var origin = $("#query-origin").val();
        console.log(categoryId);
        console.log(drugName);
        console.log(unit);
        console.log(origin);
        $.ajax({
            "url": "/excel/excelDrug",
            "data": "categoryId=" + categoryId + "&drugName=" + drugName + "&unit=" + unit + "&origin=" + origin,
            "type": "post",
            "dataType": "json",
            "success": function (json) {
                if (json.state == 200) {
                    alert("成功");
                } else {
                    alert(json.message);
                }
            }
        });
    });
})