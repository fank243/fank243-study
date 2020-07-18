<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">一级菜单</label>
                    <div class="layui-input-inline">
                        <select name="pid" lay-filter="firstMenu">
                            <option value="">请选择菜单</option>
                            <#list permissionList as perm>
                                <option value="${perm.id}">${perm.name}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">二级菜单</label>
                    <div class="layui-input-inline">
                        <select name="spid" id="secondMenu" lay-filter="secondMenu">
                            <option value="">请选择菜单</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">关键字</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="keyword" autocomplete="off" placeholder="权限名称/权限/权限地址"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="btnSearch" id="btnSearch">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
                    <button class="layui-btn layuiadmin-btn-list" lay-event="add">添加</button>
                </div>
            </script>

            <table id="layTable" class="layui-table"
                   lay-data="{url:'/admin/permission/permissionList', page:true, toolbar:'#toolbarDemo'}" lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true}">序号</th>
                    <th lay-data="{field:'name',width:120}">权限名称</th>
                    <th lay-data="{field:'pidName',width:120}">父权限名称</th>
                    <th lay-data="{field:'typeCn'}">权限类型</th>
                    <th lay-data="{field:'permission'}">权限</th>
                    <th lay-data="{field:'uri'}">菜单链接</th>
                    <th lay-data="{field:'icon'}">图标</th>
                    <th lay-data="{field:'sort',sort:true}">排序</th>
                    <th lay-data="{field:'available',sort:true,templet:'#tempAvailable'}">状态</th>
                    <th lay-data="{fixed:'right',width:150, align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="tempAvailable">
                {{#  if(d.available){ }}
                <input type="checkbox" name="switch" lay-skin="switch" lay-filter="laySwitch" lay-text="正常|禁用"
                       data-id="{{d.id}}" value="{{d.available}}" checked>&nbsp;&nbsp;&nbsp;
                {{# } else { }}
                <input type="checkbox" name="switch" lay-skin="switch" lay-filter="laySwitch" lay-text="正常|禁用"
                       data-id="{{d.id}}" value="{{d.available}}">&nbsp;&nbsp;&nbsp;
                {{#  } }}
            </script>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">
                    <i class="layui-icon layui-icon-edit"></i>编辑
                </a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
                    <i class="layui-icon layui-icon-delete"></i>删除
                </a>
            </script>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'table', 'laypage'], function () {
        let table = layui.table;
        let form = layui.form;

        // 父菜单
        form.on("select(firstMenu)", function (data) {
            let pid = data.value;
            if (pid <= 0) {
                $("#secondMenu").html("<option value='0'>请选择菜单</option>");
                $("#thirdMenu").html("<option value='0'>请选择菜单</option>");
                form.render();
                return;
            }
            loadChildMenu("#secondMenu", pid);
        });

        //监听搜索
        form.on('submit(btnSearch)', function (data) {
            let field = data.field;

            //执行重载
            table.reload('layTable', {
                where: field
            });
        });

        //头工具栏事件
        table.on('toolbar(layTable)', function (obj) {
            if (obj.event === 'add') {
                layer.open({
                    type: 2,
                    title: '添加菜单',
                    maxmin: true,
                    area: ['780', '600'],
                    btn: ['确定', '取消'],
                    content: 'permission/add',
                    yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
            }
        });

        //监听行工具事件
        table.on('tool(layTable)', function (obj) {
            let data = obj.data;
            let id = data.id;
            if (obj.event === 'edit') {
                $.getJSON("permission/get/" + id, {}, function (data) {
                    if (!data.success) {
                        layer.alert("权限不存在");
                        return false;
                    }
                });
                layer.open({
                    type: 2,
                    title: '编辑菜单',
                    maxmin: true,
                    area: ['800', '620'],
                    btn: ['确定', '取消'],
                    content: 'permission/modify/' + id,
                    yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
            }
            if (obj.event === 'del') {
                layer.confirm("确定要删除权限菜单【" + data.name + "】？", function () {
                    $.ajax({
                        url: "/admin/permission/delete/" + id,
                        type: "DELETE",
                        success: function (data) {
                            if (!data.success) {
                                layer.msg(data.msg, {icon: 2});
                                return;
                            }
                            layer.msg(data.msg, {time: 2000}, function () {
                                location.reload();
                            });
                        },
                        error: function () {
                            layer.alert("系统繁忙，请稍后重试");
                        }
                    });
                });
            }
        });

        //监听状态开关
        form.on('switch(laySwitch)', function (data) {
            let id = $(this).data("id");
            layer.load(1);
            $.ajax({
                url: "/admin/permission/modify-status/" + id,
                type: "PUT",
                success: function (data) {
                    layer.closeAll();
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return;
                    }
                    layer.msg(data.msg, {time: 1000}, function () {
                        layui.table.reload('layTable');
                    });
                },
                error: function () {
                    layer.closeAll();
                    layer.alert("系统繁忙，请稍后重试");
                }
            });
        });

        function loadChildMenu(elmId, pid) {
            $.ajax({
                url: "/admin/permission/getChildMenu/" + pid,
                type: "GET",
                success: function (data) {
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return false;
                    }
                    let menus = data.payload;
                    if (menus.length <= 0) {
                        form.render();
                        $(elmId).html("<option value='0'>请选择菜单</option>");
                        $("#thirdMenu").html("<option value='0'>请选择菜单</option>");
                        form.render();
                        return false;
                    }

                    let option = "<option value='0'>请选择菜单</option>";
                    for (let i = 0; i < menus.length; i++) {
                        option += "<option value='" + menus[i].id + "'>" + menus[i].name + "</option>";
                    }
                    $(elmId).html(option);
                    form.render();
                },
                error: function () {
                    layer.msg("系统繁忙,请稍后再试", {icon: 2});
                }
            });
        }
    });


</script>