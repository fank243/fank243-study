<style>
    .layui-form-label{
        width:155px;
    }
    .layui-input-block{
        margin-left: 180px;
    }

</style>
<div class="layui-form" wid100 lay-filter="aliyunForm">
    <div class="layui-form-item">
        <label class="layui-form-label">U-Push URL</label>
        <div class="layui-input-block">
            <input class="layui-input" lay-verify="required" name="ym_api_url" value="${(sysConfig.ym_api_url)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>Android 配置</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">AppKey</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="ym_android_app_key" value="${(sysConfig.ym_android_app_key)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">Master Secret</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="ym_android_master_secret" value="${(sysConfig.ym_android_master_secret)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>IOS 配置</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">AppKey</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="ym_ios_app_key" value="${(sysConfig.ym_ios_app_key)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">Master Secret</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="ym_ios_master_secret" value="${(sysConfig.ym_ios_master_secret)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnAliyun" value="确定">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnAliyun2" value="确定并刷新缓存">
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer, form = layui.form;

        form.on("submit(btnAliyun)", function (data) {
            var field = data.field;
            field['type'] = 0;
            submit(field);
        });
        form.on("submit(btnAliyun2)", function (data) {
            var field = data.field;
            field['type'] = 1;
            submit(field);
        });
        function submit(data) {
            layer.load(1);
            $.ajax({
                type:"POST",
                url:"/admin/site/modify",
                contentType: "application/json; charset=utf-8",
                data:JSON.stringify(data),
                success:function (data) {
                    layer.closeAll();
                    if(data.code !== 200){
                        layer.msg(data.msg,{icon:2});
                        return;
                    }
                    layer.msg(data.msg);
                },
                error:function () {
                    layer.closeAll();
                    layer.alert("系统繁忙，请稍后重试");
                }
            });
        }
    });
</script>
