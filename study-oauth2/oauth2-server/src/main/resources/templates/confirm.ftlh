<!DOCTYPE html>
<html lang="en">
<head>
    <title>统一认证授权中心</title>
    <#include "common/resources.ftlh">

    <style>
        .main-body {
            top: 50%;
            left: 50%;
            position: absolute;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            overflow: hidden;
        }

        .login-main .login-bottom .center .item input {
            display: inline-block;
            width: 227px;
            height: 22px;
            padding: 0;
            position: absolute;
            border: 0;
            outline: 0;
            font-size: 14px;
            letter-spacing: 0;
        }

        .login-main .login-bottom .center .item .icon-1 {
            background: url(${basePath}/auth/images/icon-login.png) no-repeat 1px 0;
        }

        .login-main .login-bottom .center .item .icon-2 {
            background: url(${basePath}/auth/images/icon-login.png) no-repeat -54px 0;
        }

        .login-main .login-bottom .center .item .icon-3 {
            background: url(${basePath}/auth/images/icon-login.png) no-repeat -106px 0;
        }

        .login-main .login-bottom .center .item .icon-4 {
            background: url(${basePath}/auth/images/icon-login.png) no-repeat 0 -43px;
            position: absolute;
            right: -10px;
            cursor: pointer;
        }

        .login-main .login-bottom .center .item .icon-5 {
            background: url(${basePath}/auth/images/icon-login.png) no-repeat -55px -43px;
        }

        .login-main .login-bottom .center .item .icon-6 {
            background: url(${basePath}/auth/images/icon-login.png) no-repeat 0 -93px;
            position: absolute;
            right: -10px;
            margin-top: 8px;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .icon-nocheck {
            display: inline-block;
            width: 10px;
            height: 10px;
            border-radius: 2px;
            border: solid 1px #9abcda;
            position: relative;
            top: 2px;
            margin: 1px 8px 1px 1px;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .icon-check {
            margin: 0 7px 0 0;
            width: 14px;
            height: 14px;
            border: none;
            background: url(${basePath}/auth/images/icon-login.png) no-repeat -111px -48px;
        }

        .login-main .login-bottom .center .item .icon {
            display: inline-block;
            width: 33px;
            height: 22px;
        }

        .login-main .login-bottom .center .item {
            width: 288px;
            height: 35px;
            border-bottom: 1px solid #dae1e6;
            margin-bottom: 35px;
        }

        .login-main {
            width: 428px;
            position: relative;
            float: left;
        }

        .login-main .login-top {
            height: 117px;
            background-color: #148be4;
            border-radius: 12px 12px 0 0;
            font-family: SourceHanSansCN-Regular, serif;
            font-size: 30px;
            font-weight: 400;
            font-stretch: normal;
            letter-spacing: 0;
            color: #fff;
            line-height: 117px;
            text-align: center;
            overflow: hidden;
            -webkit-transform: rotate(0);
            -moz-transform: rotate(0);
            -ms-transform: rotate(0);
            -o-transform: rotate(0);
            transform: rotate(0);
        }

        .login-main .login-top .bg1 {
            display: inline-block;
            width: 74px;
            height: 74px;
            background: #fff;
            opacity: .1;
            border-radius: 0 74px 0 0;
            position: absolute;
            left: 0;
            top: 43px;
        }

        .login-main .login-top .bg2 {
            display: inline-block;
            width: 94px;
            height: 94px;
            background: #fff;
            opacity: .1;
            border-radius: 50%;
            position: absolute;
            right: -16px;
            top: -16px;
        }

        .login-main .login-bottom {
            width: 428px;
            background: #fff;
            border-radius: 0 0 12px 12px;
            padding-bottom: 53px;
        }

        .login-main .login-bottom .center {
            width: 288px;
            margin: 0 auto;
            padding-top: 40px;
            padding-bottom: 15px;
            position: relative;
        }

        .login-main .login-bottom .tip {
            clear: both;
            height: 16px;
            line-height: 16px;
            width: 288px;
            margin: 0 auto;
        }

        body {
            background: url(${basePath}/auth/images/loginbg.png) 0% 0% / cover no-repeat;
            position: static;
            font-size: 12px;
        }

        input::-webkit-input-placeholder {
            color: #a6aebf;
        }

        input::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #a6aebf;
        }

        input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #a6aebf;
        }

        input:-ms-input-placeholder { /* Internet Explorer 10-11 */
            color: #a6aebf;
        }

        input:-webkit-autofill { /* 取消Chrome记住密码的背景颜色 */
            -webkit-box-shadow: 0 0 0 1000px white inset !important;
        }

        html {
            height: 100%;
        }

        .login-main .login-bottom .tip {
            clear: both;
            height: 16px;
            line-height: 16px;
            width: 288px;
            margin: 0 auto;
        }

        .login-main .login-bottom .tip .login-tip {
            font-family: MicrosoftYaHei, serif;
            font-size: 12px;
            font-weight: 400;
            font-stretch: normal;
            letter-spacing: 0;
            color: #9abcda;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .forget-password {
            font-stretch: normal;
            letter-spacing: 0;
            color: #1391ff;
            text-decoration: none;
            position: absolute;
            right: 62px;
        }

        .login-main .login-bottom .login-btn {
            width: 288px;
            height: 40px;
            background-color: #1E9FFF;
            border-radius: 16px;
            margin: 24px auto 0;
            text-align: center;
            line-height: 40px;
            color: #fff;
            font-size: 14px;
            letter-spacing: 0;
            cursor: pointer;
            border: none;
        }

        .login-main .login-bottom .center .item .validateImg {
            position: absolute;
            right: 1px;
            cursor: pointer;
            height: 36px;
            border: 1px solid #e6e6e6;
        }

        .footer {
            left: 0;
            bottom: 0;
            color: #fff;
            width: 100%;
            position: absolute;
            text-align: center;
            line-height: 30px;
            padding-bottom: 10px;
            text-shadow: #000 0.1em 0.1em 0.1em;
            font-size: 14px;
        }

        .padding-5 {
            padding: 5px !important;
        }

        .footer a, .footer span {
            color: #fff;
        }

        @media screen and (max-width: 428px) {
            .login-main {
                width: 360px !important;
            }

            .login-main .login-top {
                width: 360px !important;
            }

            .login-main .login-bottom {
                width: 360px !important;
            }
        }
    </style>
</head>
<body>
<div class="main-body">
    <div class="login-main">
        <div class="login-top">
            <span>统一认证授权</span>
            <span class="bg1"></span>
            <span class="bg2"></span>
        </div>
        <form class="layui-form login-bottom">
            <input type="hidden" name="client_id" value="${clientId!}">
            <input type="hidden" name="scope" value="${scope!}">
            <div class="center">
                <blockquote class="layui-elem-quote">登录后开发者将获得以下权限</blockquote>
                <ul>
                    <li>用户信息</li>
                </ul>
            </div>
            <div class="layui-form-item" style="text-align:center; width:100%;height:100%;margin:0px;">
                <button class="login-btn" lay-submit="" lay-filter="confirm">确认登录</button>
                <button class="login-btn" style="background-color: red" lay-submit="" lay-filter="cancel">取消</button>
            </div>
        </form>
    </div>
</div>

<div class="footer">
    ©2022 Study版权所有
</div>

<script>
    layui.use(['layer', 'form', 'jquery'], function () {
        const $ = layui.jquery, form = layui.form, layer = layui.layer;

        // 确认授权
        form.on("submit(confirm)", function (data) {
            $.ajax({
                url: "/oauth2/doConfirm",
                type: "POST",
                data: data.field,
                success: function (resp) {
                    if (!resp.success) {
                        layer.msg(resp.message, {icon: 2});
                        return false;
                    }
                    layer.msg('授权成功', {icon: 1, time: 1000}, function () {
                        location.reload();
                    });
                }
            })
            return false;
        });

        // 取消授权
        form.on("submit(cancel)", function (data) {
            layer.msg('您已拒绝授权', {icon: 7, time: 1000}, function () {
                let redirectUri = getQueryUriVariable(window.location.search);
                redirectUri = getQueryVariable4UriStr(redirectUri, "redirect_uri");
                window.location.href = redirectUri + "?code=&message=" + encodeURIComponent("用户拒绝授权");
            });
            return false;
        });
    });
</script>
</body>
</html>