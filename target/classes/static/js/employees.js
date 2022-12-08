$(function () { //全选和全不选
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
    $("input[name=id]")
        .click(
            function () {
                if ($("input[name=id]:checked").length/* 获取选中复选框长度 */ == $("input[name=id]").length/* 未选中的长度 */) {
                    $("#all").get(0).checked = true;
                } else {
                    $("#all").get(0).checked = false;
                }
            });
});
/**
 * 员工信息显示列表
 */
//$(function() { //页面加载完成事件
$(document).ready(function () {
    showEmpList();
});

function getAll() {
    showEmpList();
}

var list;

function showEmpList() {
    $.ajax({
        "url": "/employees/selectEmployees",
        "type": "get",
        "dataType": "json",
        "success": function (json) {
            if (json.state == 200) {
                list = json.data;
                $("#allemp").empty();
                for (var i = 0; i < list.length; i++) {
                    var html = '<tr>'
                        + '<td width="40" class="text-center"><input type="checkbox" value=#{uid}  ></td>'
                        + '<td>#{username}</td>'
                        + '<td>#{gender}</td>'
                        + '<td>#{age}</td>'
                        + '<td>#{phone}</td>'
                        + '<td>#{email}</td>'
                        + '<td>#{card}</td>'
                        + '<td>#{isDelete}</td>'
                        + '<td><a onclick="changeInfo(#{uid})" class="btn btn-xs btn-info" data-toggle="modal" data-target="#myModal"><span class="fa fa-edit"></span> 修改</a>&nbsp;&nbsp;'
                        + '<a onclick="deleteByUid(#{uid})" class="btn btn-xs add-del btn-info" ><span class="fa fa-trash-o"></span> 删除</a></td>'
                        + '</tr>'
                    html = html.replace(/#{uid}/g, list[i].uid);
                    html = html.replace("#{username}", list[i].username);
                    html = html.replace("#{gender}", list[i].gender == 1 ? "男" : "女");
                    html = html.replace("#{age}", list[i].age);
                    html = html.replace("#{phone}", list[i].phone);
                    html = html.replace("#{email}", list[i].email);
                    html = html.replace("#{card}", list[i].card);
                    html = html.replace("#{isDelete}", list[i].isDelete == 0 ? "在职" : "离职");
                    $("#allemp").append(html);
                }
            } else {
                alert(json.message);
            }
        }
    });
}

function deleteByUid(uid) {
    $.ajax({
        "url": "/employees/getOutEmp",
        "data": "uid=" + uid,
        "type": "POST",
        "dataType": "json",
        "success": function (json) {
            if (json.state == 200) {
                showEmpList();
            } else {
            }
        },
        "error": function () {
            location.href = '404.html'
        }
    });
}

function change_emp_info() {
    $.ajax({
        "url": "/employees/change_EmpInfo",
        "data": $("#form-change").serialize(),
        "type": "post",
        "dataType": "json",
        "success": function (json) {
            if (json.state == 200) {
                alert("修改成功!");
                location.reload();
            } else {
            }
        },
        "error": function () {
            location.href = '404.html'
        }
    });
}

function changeInfo(uid) {
    $.ajax({
        "url": "/employees/show_EmpInfo",
        "data": "uid=" + uid,
        "type": "get",
        "dataType": "json",
        "success": function (json) {
            if (json.state == 200) {
                $("#empname").val(json.data.username);
                $("#phone").val(json.data.phone);
                $("#email").val(json.data.email);
                $("#age").val(json.data.age);
                $("#cardBank").val(json.data.cardBank);
                $("#card").val(json.data.card);
                $("#uid").val(json.data.uid);

            } else {
            }
        },
        "error": function () {
            location.href = '404.html'
        }
    });
}

function getByUsername() {
    $.ajax({
        "url": "/employees/getByUsername",
        "data": "username=" + $("#username").val(),
        "type": "get",
        "dataType": "json",
        "success": function (json) {
            if (json.state == 200) {
                list = json.data;
                $("#allemp").empty();
                for (var i = 0; i < list.length; i++) {
                    var html = '<tr>'
                        + '<td width="40" class="text-center"><input type="checkbox" value=#{uid}  ></td>'
                        + '<td>#{username}</td>'
                        + '<td>#{gender}</td>'
                        + '<td>#{age}</td>'
                        + '<td>#{phone}</td>'
                        + '<td>#{email}</td>'
                        + '<td>#{card}</td>'
                        + '<td>#{isDelete}</td>'
                        + '<td>' +
                        '<a onclick="changeInfo(#{uid})" class="btn btn-xs btn-info" data-toggle="modal" data-target="#myModal"><span class="fa fa-edit"></span> 修改</a>&nbsp;&nbsp;'
                        + '<a onclick="deleteByUid(#{uid})" class="btn btn-xs add-del btn-info" ><span class="fa fa-trash-o"></span> 删除</a></td>'
                        + '</tr>'
                    html = html.replace(/#{uid}/g, list[i].uid);
                    html = html.replace("#{username}", list[i].username);
                    html = html.replace("#{gender}", list[i].gender == 1 ? "男" : "女");
                    html = html.replace("#{age}", list[i].age);
                    html = html.replace("#{phone}", list[i].phone);
                    html = html.replace("#{email}", list[i].email);
                    html = html.replace("#{card}", list[i].card);
                    html = html.replace("#{isDelete}", list[i].isDelete == 0 ? "在职" : "离职");
                    $("#allemp").append(html);
                }
            } else {
            }
        },
        "error": function () {
            location.href = '404.html'
        }
    });
}