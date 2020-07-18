<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="keyword" autocomplete="off" placeholder="角色名称/角色描述"/>
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

            <table id="layTable" class="layui-table" lay-data="{url:'/admin/role/role', page:true, toolbar:'#toolbarDemo'}" lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true}">序号</th>
                    <th lay-data="{field:'name'}">角色名称</th>
                    <th lay-data="{field:'description'}">角色描述</th>
                    <th lay-data="{field:'available',templet:'#tempAvailable'}">状态</th>
                    <th lay-data="{fixed:'right',width:250, align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="tempAvailable">
                {{#  if(d.available){ }}
                    <button class="layui-btn layui-btn-xs">正常</button>
                {{#  } else { }}
                    <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
                {{#  } }}
            </script>

            <script type="text/html" id="barDemo">
                {{#  if(d.available){ }}
                    <input type="checkbox" name="switch" lay-skin="switch" lay-filter="laySwitch" lay-text="正常|禁用" data-id="{{d.id}}" value="{{d.available}}" checked>&nbsp;&nbsp;&nbsp;
                {{#  } else { }}
                    <input type="checkbox" name="switch" lay-skin="switch" lay-filter="laySwitch" lay-text="正常|禁用" data-id="{{d.id}}" value="{{d.available}}">&nbsp;&nbsp;&nbsp;
                {{#  } }}
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="authorize"><i class="layui-icon layui-icon-auz"></i>授权</a>
            </script>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form','table','laypage'], function () {
        let table = layui.table;
        let form = layui.form;

        //监听搜索
        form.on('submit(btnSearch)', function(data){
            let field = data.field;

            //执行重载
            table.reload('layTable', {
                where: field
            });
        });

        //头工具栏事件
        table.on('toolbar(layTable)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id);
            if (obj.event === 'add') {
                layer.open({
                    type:2,
                    title:'添加角色',
                    maxmin:true,
                    area: ['400', '300'],
                    btn: ['确定', '取消'],
                    content:'role/add',
                    yes: function(index, layero){
                        //点击确认触发 iframe 内容中的按钮提交
                        var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
            }
        });

        //监听行工具事件
        table.on('tool(layTable)', function(obj){
            let data = obj.data;
            let id = data.id;
            if(obj.event === 'edit'){
                $.getJSON("role/get/" + id, {}, function (data) {
                    if (!data.success) {
                        layer.alert("角色不存在");
                        return false;
                    }
                });
                layer.open({
                    type:2,
                    title:'编辑角色',
                    maxmin:true,
                    area: ['400', '300'],
                    btn: ['确定', '取消'],
                    content:'role/modify/' + id,
                    yes: function(index, layero){
                        //点击确认触发 iframe 内容中的按钮提交
                        let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
            }
            if(obj.event === 'authorize'){
                $.getJSON("role/get/" + id, {}, function (data) {
                    if (!data.success) {
                        layer.alert("角色不存在");
                        return false;
                    }
                });

                layer.open({
                    type:2,
                    title:"角色授权",
                    area: ["600","500"],
                    btn: ['确定', '取消'],
                    content:'role/authorize/' + id,
                    yes: function(index, layero){
                        //点击确认触发 iframe 内容中的按钮提交
                        let submit = layero.find('iframe').contents().find("#LAY-auth-tree-submit");
                        submit.click();
                    }
                });
            }
        });

        //监听状态开关
        form.on('switch(laySwitch)', function (data) {
            let roleId = $(this).data("id");
            layer.load(1);
            $.ajax({
                url: "/admin/role/modify-status/" + roleId,
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
    });


</script>