<div class="layui-form" wid100 lay-filter="siteForm">
    <div class="layui-form-item">
        <label class="layui-form-label">网站名称</label>
        <div class="layui-input-block">
            <input class="layui-input" lay-verify="required" name="site_name" value="${(sysConfig.site_name)!}"
                   autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">网站域名</label>
        <div class="layui-input-block">
            <input type="url" class="layui-input" lay-verify="url" name="site_url" onmouseover="showTips(this,'请不要以`/`结束')"
                   value="${(sysConfig.site_url)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">Api授权凭证</label>
        <div class="layui-input-inline" style="width: 300px;">
            <input type="text" class="layui-input" name="yapi_access_token" value="${(sysConfig.yapi_access_token)!}" autocomplete="off" placeholder="请输入">
        </div>
        <div class="layui-form-mid layui-word-aux">提示：用于在线调测App Api，生产环境请谨慎开启</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">最大文件上传</label>
        <div class="layui-input-inline" style="width: 80px;">
            <input type="number" name="file_size" lay-verify="number" value="${(sysConfig.file_size)!2}" class="layui-input">
        </div>
        <div class="layui-input-inline layui-input-company">MB</div>
        <div class="layui-form-mid layui-word-aux">提示：最大值不能超过10MB</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上传文件类型</label>
        <div class="layui-input-inline" style="width: 50%;">
            <input type="text" name="mine_type" value="${(sysConfig.mine_type)!}" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">提示：请使用 “|” 分割，中间不能有空格，暂不支持除图片以外的类型</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">默认密码</label>
        <div class="layui-input-inline">
            <input type="text" name="default_pwd" value="${(sysConfig.default_pwd)!}" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">提示：用于创建管理员、用户、重置密码的默认密码</div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">版权信息</label>
        <div class="layui-input-block">
            <textarea name="site_copyright" class="layui-textarea" placeholder="请输入">${(sysConfig.site_copyright)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnSite" value="确定">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnSite2" value="确定并刷新缓存">
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer, form = layui.form;

        form.on("submit(btnSite)", function (data) {
            var field = data.field;
            field['type'] = 0;
            submit(field);
        });
        form.on("submit(btnSite2)", function (data) {
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
