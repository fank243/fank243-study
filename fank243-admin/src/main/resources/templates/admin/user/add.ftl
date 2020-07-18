<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list"
             style="padding: 20px 30px 0 0;">
            <div class="layui-form-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input class="layui-input" lay-verify="required" name="username" oninput="$(this).substr(20)" autocomplete="off"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-block">
                        <input type="number" maxlength="11" class="layui-input" lay-verify="required|phone" name="mobile" oninput="$(this).substr(11)"  placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">昵称</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" lay-verify="required" name="nickname" oninput="$(this).substr(20)" style="width: 150%;" placeholder="请输入">
                    </div>
                    <button type="button" style="margin-left: 100px;" class="layui-btn layui-btn-primary" onclick="randomNickname()">
                        随机生成
                    </button>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">头像</label>
                    <div class="layui-input-inline">
                        <input type="url" class="layui-input" lay-verify="required" style="width: 150%;" name="photo"
                               readonly value="${IConsts.DEFAULT_PHOTO}">
                    </div>
                    <button type="button" style="margin-left: 100px;" class="layui-btn layui-btn-primary"
                            id="layUpload">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-inline">
                        <img id="showImg" alt="" src="${IConsts.DEFAULT_PHOTO}" width="70%" height="25%" onerror="this.src='${IConsts.DEFAULT_PHOTO}'">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-hide">
                <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit"
                       value="确认添加">
                <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit"
                       value="确认编辑">
            </div>
        </div>
    </div>
</div>

<!--图片上传组件-->
<#include "/admin/uploadImage.ftl">

<script>
    layui.use(['form'], function () {
        let form = layui.form;

        randomNickname();

        //监听提交
        form.on('submit(layuiadmin-app-form-submit)', function (data) {
            let field = data.field; //获取提交的字段
            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

            //提交 Ajax 成功后，关闭当前弹层并重载表格
            layer.load(1);
            $.ajax({
                url: '/admin/user/add',
                type: 'POST',
                data: field,
                success: function (data) {
                    layer.closeAll();
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return;
                    }
                    layer.msg(data.msg, {time: 1000}, function () {
                        parent.layui.table.reload('layTable'); //重载表格
                        parent.layer.close(index); //再执行关闭
                    });
                },
                error: function () {
                    layer.closeAll();
                    layer.alert('系统繁忙，请稍后重试');
                }
            });
        });

        form.render();
    })

    function randomNickname() {
        let randomStr = Math.random().toString().slice(-6);
        $("input[name='nickname']").val("JBoot" + randomStr);
    }
</script>