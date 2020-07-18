<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="keyword" autocomplete="off" placeholder="请输入">
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
            <table class="layui-table" lay-data="{url:'/admin/pushrecord/pushrecord', page:true, id:'layTable'}" lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true}">序号</th>
                    <th lay-data="{field:'username'}">用户名</th>
                    <th lay-data="{field:'deviceType'}">设备类型</th>
                    <th lay-data="{field:'deviceToken'}">设备识别码</th>
                    <th lay-data="{field:'ip'}">IP地址</th>
                    <th lay-data="{field:'ipAddr'}">IP归属地</th>
                    <th lay-data="{field:'title'}">通知标题</th>
                    <th lay-data="{field:'content'}">通知内容</th>
                    <th lay-data="{field:'gmtCreated'}">发送时间</th>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form','table','laypage',"laydate"], function () {
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