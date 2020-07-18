<blockquote class="layui-elem-quote">阿里云SDK调用相关凭证，请勿随意改动，以下配置可同时支持短信、OSS等服务的调用</blockquote>
<div class="layui-form" wid100 lay-filter="aliyunForm">
    <div class="layui-form-item">
        <label class="layui-form-label">OSS域名</label>
        <div class="layui-input-block">
            <input type="url" class="layui-input" lay-verify="url" name="aliyun_oss_domain" onmouseover="showTips(this,'请不要以`/`结束')"
                   value="${(sysConfig.aliyun_oss_domain)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">视频点播域名</label>
        <div class="layui-input-block">
            <input type="url" class="layui-input" lay-verify="url" name="aliyun_vod_domain" onmouseover="showTips(this,'请不要以`/`结束')"
                   value="${(sysConfig.aliyun_vod_domain)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">回调地址</label>
        <div class="layui-input-block">
            <input type="url" class="layui-input" lay-verify="url" name="aliyun_oss_callback" value="${(sysConfig.aliyun_oss_callback)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">AccessSecret</label>
        <div class="layui-input-block">
            <input class="layui-input" lay-verify="required" name="aliyun_access_secret" value="${(sysConfig.aliyun_access_secret)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">AccessKeyId</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="aliyun_access_key_id" value="${(sysConfig.aliyun_access_key_id)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">endpoint</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="aliyun_endpoint" value="${(sysConfig.aliyun_endpoint)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">OSS Bucket</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="aliyun_oss_bucket" value="${(sysConfig.aliyun_oss_bucket)!}" autocomplete="off" placeholder="请输入">
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
