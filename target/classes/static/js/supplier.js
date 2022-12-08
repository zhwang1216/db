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
        location.href = "supplier.html"
    })

    //函数
    function display(pageNoStr, pageSizeStr) {
        var username = $("#query-supplier-name").val();
        var phone = $("#query-supplier-phone").val();
        var email = $("#query-supplier-email").val();
        $.ajax({
            url: '/supplier/selectSupplier',
            data: {
                pageNoStr: pageNoStr,
                pageSizeStr: pageSizeStr,
                username: username,
                phone: phone,
                email: email,
            },
            type: 'post',
            success: function (json) {
                var htmlStr = "";
                $.each(json.data.dataList, function (index, obj) {
                    htmlStr += "<tr>"
                    htmlStr += "<td width='40' class='text-center'><input type='checkbox' value='" + obj.uid + "' name='id'  ></td>"
                    htmlStr += "<td>" + obj.username + "</td>"
                    htmlStr += "<td>" + obj.phone + "</td>"
                    htmlStr += "<td>" + obj.email + "</td>"
                    htmlStr += "<td>" + obj.address + "</td>"
                    htmlStr += "<td>" + obj.cardBank + "</td>"
                    htmlStr += "<td>" + obj.card + "</td>"
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
    //修改数据，根据供货商id，查询供货商数据数据
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
        var uid = "";
        $.each(ckdids, function (index, obj) {
            uid = obj.value;
        })
        $.ajax({
            "url": "/supplier/info",
            "data": "uid=" + uid,
            "type": "get",
            "dataType": "json",
            "success": function (json) {
                console.log(json.data.username);
                if (json.state == 200) {
                    $("input[name='uid']").val(json.data.uid);
                    $("input[name='username']").val(json.data.username);
                    $("input[name='phone']").val(json.data.phone);
                    $("input[name='email']").val(json.data.email);
                    $("input[name='address']").val(json.data.address);
                    $("input[name='cardBank']").val(json.data.cardBank);
                    $("input[name='card']").val(json.data.card);
                } else {
                    alert(json.message);
                }
            },
            "error": function () {
                location.href = '404.html';
            }
        });
    });
    //修改供应商数据
    $("#btn-update-supplier").click(function () {
        $.ajax({
            "url": "/supplier/change_info",
            "data": $("#form-supplier").serialize(),
            "type": "post",
            "dataType": "json",
            "success": function (json) {
                if (json.state == 200) {
                    location.href = "supplier.html"
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
        var ids = "";	//拼接id
        $.each(ckdids, function (index, obj) {
            ids += obj.value + ","
        })
        ids = ids.substr(0, ids.length - 1);
        if (window.confirm("确定删除吗？？？")) {
            $.ajax({
                "url": "/supplier/change_isDelete",
                "data": "uids=" + ids,
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