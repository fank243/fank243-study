<blockquote class="layui-elem-quote">仅支持阿里云短信服务，配置其他短信服务商账号无效</blockquote>
<div class="layui-form" wid100 lay-filter="smsForm">
    <div class="layui-form-item">
        <label class="layui-form-label">短信签名</label>
        <div class="layui-input-block">
            <input class="layui-input" lay-verify="required" name="sms_sign_name" value="${(sysConfig.sms_sign_name)!'蚂蚁星工厂'}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">日发送最大量</label>
        <div class="layui-input-inline">
            <input type="number" class="layui-input" lay-verify="required|number" name="sms_day_max" value="${(sysConfig.sms_day_max)!10}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">每日向同一手机号码发送的最大数量</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnSms" value="确定">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnSms2" value="确定并刷新缓存">
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer, form = layui.form;

        form.on("submit(btnSms)", function (data) {
            var field = data.field;
            field['type'] = 0;
            submit(field);
        });
        form.on("submit(btnSms2)", function (data) {
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
