<#macro layout title>
    <!DOCTYPE html>
    <html lang="zh">
    <head>
        <meta charset="utf-8">
        <title>${siteName!'JBoot'} 后台管理员系统</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">

        <link rel="shortcut icon" type="image/png" href="/static/images/favicon.ico">
        <link rel="stylesheet" href="/lib/layuiadmin/layui/css/layui.css" media="all">
        <link rel="stylesheet" href="/lib/layuiadmin/style/admin.css" media="all">
    </head>
    <body class="layui-layout-body">
    <div id="LAY_app">
        <div class="layui-layout layui-layout-admin">
            <#--头部-->
            <#include 'header.ftl'/>

            <!-- 侧边菜单 -->
            <#include 'side-menu.ftl'/>

            <!-- 页面标签 -->
            <#include 'pagetabs.ftl'/>

            <!-- 主体内容 -->
            <div class="layui-body" id="LAY_app_body">
                <div class="layadmin-tabsbody-item layui-show">
                    <iframe src="/admin/dashboard" frameborder="0" class="layadmin-iframe"></iframe>
                </div>
            </div>

            <!-- 辅助元素，一般用于移动设备下遮罩 -->
            <div class="layadmin-body-shade" layadmin-event="shade"></div>
        </div>
    </div>

    <script type="text/javascript" src="/lib/layuiadmin/layui/layui.js"></script>
    <script type="text/javascript">
        layui.config({
            base: '/lib/layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
        }).use('index');
    </script>
    </body>
    </html>
</#macro>