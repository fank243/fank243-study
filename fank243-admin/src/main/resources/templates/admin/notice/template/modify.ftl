<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card" style="padding:10px;">
        <blockquote class="layui-elem-quote">温馨提示：模板中类似 <code><b>#code#</b></code> 中的英文代码为系统保留字段，请勿修改</blockquote>
        <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
            <input type="hidden" name="id" value="${(templateNotice.id)!}">
            <div class="layui-form-item">
                <label class="layui-form-label">模板类别</label>
                <div class="layui-input-block">
                    <input class="layui-input" disabled value="${(templateNotice.type.desc)!}" autocomplete="off" placeholder="请输入">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">模板名称</label>
                <div class="layui-input-block">
                    <input class="layui-input" lay-verify="required" name="name" value="${(templateNotice.name)!}" autocomplete="off" placeholder="请输入">
                </div>
            </div>
            <#if (templateNotice.type.code != TemplateType.SMS.getCode())!false>
            <div class="layui-form-item">
                <label class="layui-form-label">模板主题</label>
                <div class="layui-input-block">
                    <input class="layui-input" lay-verify="required" name="title" value="${(templateNotice.subject)!}" autocomplete="off" placeholder="请输入">
                </div>
            </div>
            </#if>
            <#if (templateNotice.type.code == TemplateType.EMAIL.getCode())!false>
                <div class="layui-form-item">
                    <label class="layui-form-label">电子邮件</label>
                    <div class="layui-input-block">
                        <textarea id="content" name="content" style="display: none">${(templateNotice.content)!}</textarea>
                    </div>
                </div>
           <#else>
                <div class="layui-form-item">
                    <label class="layui-form-label">模板内容</label>
                    <div class="layui-input-block">
                        <textarea name="content" class="layui-textarea" style="height: 230px;">${(templateNotice.content)!}</textarea>
                    </div>
                </div>
            </#if>
            <div class="layui-form-item layui-hide">
                <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['layedit','form',"layer"], function(){
        let form = layui.form;
        let layer = layui.layer;
        let layedit = layui.layedit;

        layedit.build('content',{
            height:200,
            uploadImage: {url: '/common/uploadImgForEditor', type: 'post'}
        });

        //监听提交
        form.on('submit(layuiadmin-app-form-submit)', function(data){
            let field = data.field; //获取提交的字段
            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

            //提交 Ajax 成功后，关闭当前弹层并重载表格
            let index2 = layer.load(1);
            $.ajax({
                url:'/admin/template/modify',
                type:'POST',
                data:field,
                success:function (data) {
                    layer.close(index2);
                    if(!data.success){
                        layer.msg(data.msg,{icon:2});
                        return;
                    }
                    layer.msg(data.msg,{time:1000},function () {
                        parent.layui.table.reload('layTable'); //重载表格
                        parent.layer.close(index); //再执行关闭
                    });
                },
                error:function () {
                    layer.alert('系统繁忙，请稍后重试');
                    layer.close(index2);
                }
            });
        });
    })
</script>