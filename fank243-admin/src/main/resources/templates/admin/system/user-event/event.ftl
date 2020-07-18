<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">事件类型</label>
                    <div class="layui-input-inline">
                        <select name="eventType" class="layui-select" lay-search="">
                            <option value="">--请选择--</option>
                            <#list UserEventType.values() as eventType>
                                <option value="${eventType.name()}">${eventType.desc}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">开始日期</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" id="beginDate" name="beginDate" readonly autocomplete="off" placeholder="请选择">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">结束日期</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" id="endDate" name="endDate" readonly autocomplete="off" placeholder="请选择">
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
            <table class="layui-table" lay-data="{url:'/admin/user-event/event', page:true, id:'layTable'}" lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{field:'id', width:80, align: 'center', sort: true}">序号</th>
                    <th lay-data="{field:'type'}">事件类型</th>
                    <th lay-data="{field:'username'}">管理员</th>
                    <th lay-data="{field:'ip'}">操作IP</th>
                    <th lay-data="{field:'ipAddr'}">IP归属地</th>
                    <th lay-data="{field:'deviceType'}">登录设备</th>
                    <th lay-data="{field:'deviceNumber'}">设备识别码</th>
                    <th lay-data="{field:'remark'}">备注</th>
                    <th lay-data="{field:'gmtCreated'}">操作时间</th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form','table','laypage','laydate'], function () {
        let table = layui.table;
        let form = layui.form;
        let laydate = layui.laydate;

        laydate.render({
            elem:"#beginDate",
            type:"date",
            format:"yyyy-MM-dd"
        });
        laydate.render({
            elem:"#endDate",
            type:"date",
            format:"yyyy-MM-dd"
        });

        //监听搜索
        form.on('submit(btnSearch)', function(data){
            let field = data.field;

            //执行重载
            table.reload('layTable', {
                where: field
            });
        });
    });


</script>