<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-tab layui-tab-brief">
            <ul class="layui-tab-title">
                <li class="layui-this">网站设置</li>
                <li>短信配置</li>
                <li>邮箱配置</li>
                <li>阿里云配置</li>
                <li>友盟配置</li>
                <li>App管理</li>
                <li>缓存管理</li>
            </ul>
            <div class="layui-tab-content">
                <!--网站设置-->
                <div class="layui-tab-item layui-show">
                    <#include 'site-site.ftl'>
                </div>
                <!--短信配置-->
                <div class="layui-tab-item">
                    <#include 'site-sms.ftl'>
                </div>
                <!--邮箱配置-->
                <div class="layui-tab-item">
                    <#include 'site-email.ftl'>
                </div>
                <!--阿里云配置-->
                <div class="layui-tab-item">
                    <#include 'site-aliyun.ftl'>
                </div>
                <!--友盟配置-->
                <div class="layui-tab-item">
                    <#include 'site-ym.ftl'>
                </div>
                <!--App管理-->
                <div class="layui-tab-item">
                    <#include 'site-app.ftl'>
                </div>
                <!--缓存管理-->
                <div class="layui-tab-item">
                    <#include 'site-redis.ftl'>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['element'], function () {

    });
</script>