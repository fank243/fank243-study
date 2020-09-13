<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">关键字</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="keyword" autocomplete="off" placeholder="用户名/姓名/手机号码"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="layui-select">
                            <option value="">--请选择--</option>
                            <#list dictService.getType('user_status') as status>
                                <option value="${status.dictValue}">${status.dictLabel}</option>
                            </#list>
                        </select>
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

            <table id="layTable" class="layui-table" lay-data="{url:'/admin/user/userList', page:true, toolbar:'#toolbarDemo'}" lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true}">序号</th>
                    <th lay-data="{field:'username',width:120}">用户名</th>
                    <th lay-data="{field:'mobile',width:120}">手机号码</th>
                    <th lay-data="{field:'nickname',width:120}">昵称</th>
                    <th lay-data="{field:'lastLoginTime',width:120}">最近登录时间</th>
                    <th lay-data="{field:'lastLoginIp',width:120}">最近登录IP</th>
                    <th lay-data="{field:'lastLoginIpAddr',width:120}">IP归属地</th>
                    <th lay-data="{field:'loginErrCount',width:150}">连续登录错误次数</th>
                    <th lay-data="{field:'loginLockTime',width:120}">登录锁定时间</th>
                    <th lay-data="{field:'gmtCreated',width:120}">添加时间</th>
                    <th lay-data="{field:'gmtModified',width:150}">修改时间</th>
                    <th lay-data="{field:'status',width:110,templet:'#tempStatus'}">状态</th>
                    <th lay-data="{fixed:'right',width:180, align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="tempStatus">
                <@dict eleType="template" dictType="user_status" fieldName="status"/>
            </script>

            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="reset"><i class="layui-icon layui-icon-password"></i>重置密码</a>
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
            table.reload('layTable', {where: field});
        });

        //头工具栏事件
        table.on('toolbar(layTable)', function(obj){
            switch(obj.event){
                case 'add':
                    layer.open({
                        type:2,
                        title:'添加用户',
                        maxmin:true,
                        area: ['650', '500'],
                        btn: ['确定', '取消'],
                        content:'user/add',
                        yes: function(index, layero){
                            //点击确认触发 iframe 内容中的按钮提交
                            let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                            submit.click();
                        }
                    });
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(layTable)', function(obj){
            let data = obj.data;
            let id = data.id;
            if(obj.event === 'edit'){
                layer.open({
                    type:2,
                    title:'编辑用户',
                    maxmin:true,
                    area: ['650', '500'],
                    btn: ['确定', '取消'],
                    content:'/admin/user/modify/' + id,
                    yes: function(index, layero){
                        //点击确认触发 iframe 内容中的按钮提交
                        let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
            }
            if(obj.event === 'reset'){
                layer.confirm("确定要对用户【"+data.username+"】进行密码重置？",function () {
                    layer.load(1);
                    $.ajax({
                        url:"/admin/user/reset-password/" + id,
                        type:"PUT",
                        success:function (data) {
                            if(!data.success){
                                layer.closeAll();
                                layer.msg(data.msg,{icon:2});
                                return;
                            }
                            layer.msg(data.msg,{time:2000},function () {
                                table.reload('layTable');
                            });
                        },
                        error:function () {
                            layer.closeAll();
                            layer.alert("系统繁忙，请稍后重试");
                        }
                    });
                });
            }
        });

        //监听状态开关
        form.on('switch(laySwitch)', function (data) {
            let sysUserId = $(this).data("id");
            layer.load(1);
            $.ajax({
                url: "/admin/user/modify-status/" + sysUserId + "/" + data.value,
                type: "PUT",
                success: function (data) {
                    layer.closeAll();
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return;
                    }
                    layer.msg(data.msg, {time: 1000}, function () {
                        table.reload('layTable');
                    });
                },
                error: function () {
                    layer.closeAll();
                    layer.alert("系统繁忙，请稍后重试");
                }
            });
        });

        form.render();
    });
</script>