<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">模板类型</label>
                    <div class="layui-input-inline">
                        <select class="layui-select" name="type">
                            <option value="">--请选择--</option>
                            <#list TemplateType.values() as templateType>
                                <option value="${templateType.code!}">${templateType.desc}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">关键字</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="keyword" autocomplete="off" placeholder="模板名称/模板代码">
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
            <table class="layui-table" lay-data="{url:'/admin/template/templateList', page:true, id:'layTable'}"
                   lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true}">序号</th>
                    <th lay-data="{field:'typeCn',width:100}">模板类型</th>
                    <th lay-data="{field:'name',width:100}">模板名称</th>
                    <th lay-data="{field:'code',width:120}">模板代码</th>
                    <th lay-data="{field:'aliyunCode',width:130}">阿里云短信代码</th>
                    <th lay-data="{field:'title',width:180}">模板主题</th>
                    <th lay-data="{field:'content',width:300,templet:'#btnContent'}">模板内容</th>
                    <th lay-data="{field:'available',width:110,templet:'#btnStat'}">状态</th>
                    <th lay-data="{field:'gmtModified',width:150}">最近修改时间</th>
                    <th lay-data="{fixed:'right',width:100, align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="btnContent">
                {{#  if(d.type == "${TemplateType.EMAIL.name()}"){ }}
                {{#  } else { }}
                {{d.content}}
                {{#  } }}
            </script>
            <script type="text/html" id="btnStat">
                {{#  if(d.available){ }}
                <input type="checkbox" name="switch" lay-skin="switch" lay-filter="laySwitch" lay-text="正常|禁用"
                       data-id="{{d.id}}" value="{{d.available}}" checked>&nbsp;&nbsp;&nbsp;
                {{#  } else { }}
                <input type="checkbox" name="switch" lay-skin="switch" lay-filter="laySwitch" lay-text="正常|禁用"
                       data-id="{{d.id}}" value="{{d.available}}">&nbsp;&nbsp;&nbsp;
                {{#  } }}
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" lay-text="修改模板-{{d.name}}">
                    <i class="layui-icon layui-icon-edit"></i>编辑
                </a>
            </script>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'table', 'laypage'], function () {
        let table = layui.table;
        let form = layui.form;

        //监听搜索
        form.on('submit(btnSearch)', function (data) {
            let field = data.field;

            //执行重载
            table.reload('layTable', {
                where: field
            });
        });

        //监听行工具事件
        table.on('tool(layTable)', function (obj) {
            let data = obj.data;
            let templdateId = data.id;
            layer.open({
                type: 2,
                title: '编辑模板',
                maxmin: true,
                area: ['750', '660'],
                btn: ['确定', '取消'],
                content: '/admin/template/modify/' + templdateId,
                yes: function (index, layero) {
                    //点击确认触发 iframe 内容中的按钮提交
                    let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                    submit.click();
                }
            });
        });

        //监听状态开关
        form.on('switch(laySwitch)', function (data) {
            let templateId = $(this).data("id");
            $.ajax({
                url: "/admin/template/modify-status/" + templateId,
                type: "PUT",
                success: function (data) {
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return;
                    }
                    layer.msg(data.msg, {time: 1000}, function () {
                        table.reload('layTable');
                    });
                },
                error: function () {
                    layer.alert("系统繁忙，请稍后重试");
                }
            });
        });
    });


</script>