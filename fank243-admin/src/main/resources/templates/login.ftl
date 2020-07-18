<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>登入 - ${siteName!'JBoot'}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

    <link rel="shortcut icon" type="image/png" href="/static/images/favicon.ico">
    <link rel="stylesheet" href="/lib/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/lib/layuiadmin/style/login.css" media="all">

    <script type="text/javascript" src="/lib/layuiadmin/layui/layui.js"></script>
    <script type="text/javascript" src="/lib/jquery-3.4.1.min.js"></script>
</head>
<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>${siteName!'JBoot'}</h2>
            <p>欢迎使用${siteName!'JBoot'}后台管理系统</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username"
                       for="LAY-user-login-username"></label>
                <input aria-label="" type="text" id="username" name="username" lay-verify="required"
                       placeholder="用户名" class="layui-input"/>
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"
                       for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required"
                       placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div id="slider"></div>
            </div>
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" title="记住我">
                <a href="//github.com/fank243" target="_blank" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">Github</a>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="login" id="btnLogin">登 入</button>
            </div>
        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p>${copyright!}</p>
    </div>

</div>

<script type="text/javascript">
    layui.config({
        base: '/lib/layuiadmin/' //静态资源所在路径
    }).extend({
        sliderVerify: 'modules/sliderVerify/sliderVerify' //主入口模块
    }).use(['form','sliderVerify'], function () {
        let form = layui.form;
        let layer = layui.layer;

        let sliderVerify = layui.sliderVerify,
            $ = layui.jquery,
            slider = sliderVerify.render({
            elem: '#slider',
            onOk: function(){//当验证通过回调
            }
        });
        //提交
        form.on('submit(login)', function (data) {
            if(!slider.isOk()){
                layer.msg("请先通过滑块验证");
                return false;
            }
            layer.load(1);
            $.ajax({
                url: "/login",
                type: "POST",
                data: data.field,
                success: function (data) {
                    layer.closeAll();
                    if (!data.success) {
                        layer.msg(data.msg);
                        return false;
                    }
                    location.href = '/admin/console.html';
                },
                error:function(){
                    layer.closeAll();
                    layer.msg("系统繁忙，请稍后再试",{icon:2});
                }
            });
            return true;
        });
    });
</script>
</body>
</html>