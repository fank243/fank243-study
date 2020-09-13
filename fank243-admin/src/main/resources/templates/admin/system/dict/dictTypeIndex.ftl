<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">字典名称</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="dictName" autocomplete="off" placeholder="字典名称"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">字典类型</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" name="dictType" autocomplete="off" placeholder="字典类型"/>
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-inline">
                        <select name="status" class="layui-select">
                            <option value="">--请选择--</option>
                            <option value="0">正常</option>
                            <option value="1">禁用</option>
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

            <table id="layTable" class="layui-table" lay-data="{url:'/admin/system/dict/dictTypeList', page:true, toolbar:'#toolbarDemo'}" lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
                    <th lay-data="{field:'id', width:80, sort: true}">序号</th>
                    <th lay-data="{field:'dictName',width:120}">字典名称</th>
                    <th lay-data="{field:'dictType',width:180,templet:'#tempDictType'}">字典类型</th>
                    <th lay-data="{field:'statusCn',width:120}">状态</th>
                    <th lay-data="{field:'remark',width:200}">备注</th>
                    <th lay-data="{field:'createTime',width:120}">创建时间</th>
                    <th lay-data="{field:'updateTime',width:150}">修改时间</th>
                    <th lay-data="{fixed:'right',width:220, align:'center', toolbar: '#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>

            <script type="text/html" id="tempDictType">
                <a style="color: cadetblue;cursor: pointer;" lay-href="/admin/system/dict/dictDataIndex?dictType={{d.dictType}}" lay-text="字典数据-{{d.dictName}}">{{d.dictType}}</a>
            </script>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-href="/admin/system/dict/dictDataIndex?dictType={{d.dictType}}" lay-text="字典数据-{{d.dictName}}"><i class="layui-icon layui-icon-list"></i>列表</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.config({
        base: '/lib/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index','form','table','laypage'], function () {
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
                        title:'添加类型',
                        maxmin:true,
                        area: ['650', '500'],
                        btn: ['确定', '取消'],
                        content:'/admin/system/dict/addDictType',
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
                    title:'编辑类型',
                    maxmin:true,
                    area: ['650', '500'],
                    btn: ['确定', '取消'],
                    content:'/admin/system/dict/editDictType/' + id,
                    yes: function(index, layero){
                        //点击确认触发 iframe 内容中的按钮提交
                        let submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
            }
        });
    });
</script>