<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <title>404</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/lib/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/lib/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layadmin-tips">
        <i class="layui-icon" face style="font-size: 200px;">&#xe664;</i>
        <div class="layui-text">
            <h1>
                <span class="layui-anim layui-anim-loop layui-anim-">4</span>
                <span class="layui-anim layui-anim-loop layui-anim-rotate">0</span>
                <span class="layui-anim layui-anim-loop layui-anim-">4</span>
            </h1>
            <#if message??>
                <blockquote class="layui-elem-quote layui-quote-nm">${message}</blockquote>
            <#else >
                <blockquote class="layui-elem-quote layui-quote-nm">您访问的资源不存在</blockquote>
            </#if>
        </div>
    </div>
</div>

</body>
</html>