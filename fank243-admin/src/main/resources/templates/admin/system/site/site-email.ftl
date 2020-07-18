<div class="layui-form" wid100 lay-filter="emailForm">
    <div class="layui-form-item">
        <label class="layui-form-label">SMTP服务器</label>
        <div class="layui-input-inline">
            <input class="layui-input" lay-verify="required" name="email_smtp" value="${(sysConfig.email_smtp)!}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">如：smtp.163.com</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">SMTP端口号</label>
        <div class="layui-input-inline">
            <input type="number" class="layui-input" lay-verify="required|number" name="email_port" value="${(sysConfig.email_port)!}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">一般为25或465</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发件人邮箱</label>
        <div class="layui-input-inline">
            <input class="layui-input" lay-verify="required" name="email_username" value="${(sysConfig.email_username)!}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">如：kefu@163.com</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">发件人昵称</label>
        <div class="layui-input-inline">
            <input class="layui-input" lay-verify="required" name="email_nickname" value="${(sysConfig.email_nickname)!}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">如：${siteName!'JBoot'}</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">授权密码</label>
        <div class="layui-input-inline">
            <input class="layui-input" lay-verify="required" name="email_password" value="${(sysConfig.email_password)!}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">如开启了客户端密码，需要填写客户端密码而非邮箱登录密码</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">日发送最大量</label>
        <div class="layui-input-inline">
            <input type="number" class="layui-input" lay-verify="required|number" name="email_day_max" value="${(sysConfig.email_day_max)!30}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">每日向同一电子邮箱发送的最大数量</div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnEmail" value="确定">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnEmail2" value="确定并刷新缓存">
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer, form = layui.form;

        form.on("submit(btnEmail)", function (data) {
            var field = data.field;
            field['type'] = 0;
            submit(field);
        });
        form.on("submit(btnEmail2)", function (data) {
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
