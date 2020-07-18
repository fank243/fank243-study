<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list"
             style="padding: 20px 30px 0 0;">
            <input type="hidden" name="id" value="${(sysRole.id)!}">
            <div class="layui-form-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-block">
                        <input class="layui-input" lay-verify="required" name="name" value="${(sysRole.name)!}" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色描述</label>
                    <div class="layui-input-block">
                        <input class="layui-input" lay-verify="required" name="description" value="${(sysRole.description)!}" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-hide">
                <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确定">
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form'], function(){
        let form = layui.form;

        //监听提交
        form.on('submit(layuiadmin-app-form-submit)', function(data){
            let field = data.field; //获取提交的字段
            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

            //提交 Ajax 成功后，关闭当前弹层并重载表格
            layer.load(1);
            $.ajax({
                url:'/admin/role/addOrModify',
                type:'POST',
                data:field,
                success:function (data) {
                    layer.closeAll();
                    if(data.code !== 200){
                        layer.msg(data.msg,{icon:2});
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function () {
                        parent.layui.table.reload('layTable'); //重载表格
                        parent.layer.close(index); //再执行关闭
                    });
                },
                error:function () {
                    layer.closeAll();
                    layer.alert('系统繁忙，请稍后重试');
                }
            });
        });
    })
</script>