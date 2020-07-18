<div class="layui-form" wid100 lay-filter="appForm">
    <div class="layui-form-item">
        <label class="layui-form-label">App 名称</label>
        <div class="layui-input-block">
            <input class="layui-input" id="appName" lay-verify="required" name="app_name"
                   value="${(sysConfig.app_name)!}" autocomplete="off" placeholder="请输入">
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>Android 配置</legend>
        </fieldset>
        <label class="layui-form-label">Android 版本号</label>
        <div class="layui-input-block">
            <input class="layui-input" id="version" lay-verify="required" name="android_version"
                   value="${(sysConfig.android_version)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">Apk 下载地址</label>
        <div class="layui-input-block">
            <input type="url" id="downloadUrl" class="layui-input" lay-verify="required|url" name="android_download_url"
                   value="${(sysConfig.android_download_url)!}" autocomplete="off" placeholder="请输入">
            <br>
            <div class="layui-upload-drag" id="btnUpload" onmouseover="showTips(this,'上传前请务必填写为最新版本号，重复版本号上传将会被覆盖')">
                <i class="layui-icon"></i>
                <p>点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">强制升级版本号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" onclick="showTips(this,'多个版本之间使用英文逗号分隔')"
                   name="android_force_upgrade_version" value="${(sysConfig.android_force_upgrade_version)!}">
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>IOS 配置</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">IOS 版本号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required" name="ios_version"
                   value="${(sysConfig.ios_version)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">IOS 商城地址</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required|url" name="ios_download_url"
                   value="${(sysConfig.ios_download_url)!}" autocomplete="off" placeholder="请输入">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">强制升级版本号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" onclick="showTips(this,'多个版本之间使用英文逗号分隔')"
                   name="ios_force_upgrade_version" value="${(sysConfig.ios_force_upgrade_version)!}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnApp" value="确定">
            <input type="button" class="layui-btn" lay-submit lay-filter="btnApp2" value="确定并刷新缓存">
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form', 'layer', 'upload'], function () {
        var layer = layui.layer, form = layui.form, upload = layui.upload;

        upload.render({
            elem: '#btnUpload'
            , accept: 'file'
            , size: 50 * 1024
            , exts: 'apk'
            , method: 'get'
            , auto: false
            , choose: function (obj) {
                obj.preview(function (index, file, result) {
                    var version = $("#version").val();
                    uploadApkToOss(version, file);
                });
            }
        });

        form.on("submit(btnApp)", function (data) {
            var field = data.field;
            field['type'] = 0;
            submit(field);
        });
        form.on("submit(btnApp2)", function (data) {
            var field = data.field;
            field['type'] = 1;
            submit(field);
        });

        function submit(data) {
            layer.load(1);
            $.ajax({
                type: "POST",
                url: "/admin/site/modify",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                success: function (data) {
                    layer.closeAll();
                    if (data.code !== 200) {
                        layer.msg(data.msg, {icon: 2});
                        return;
                    }
                    layer.msg(data.msg);
                },
                error: function () {
                    layer.closeAll();
                    layer.alert("系统繁忙，请稍后重试");
                }
            });
        }
    });
</script>
