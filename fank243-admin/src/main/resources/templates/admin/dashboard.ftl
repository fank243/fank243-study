<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-header">快捷方式</div>
                        <div class="layui-card-body">
                            <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                                <div carousel-item>
                                    <ul class="layui-row layui-col-space10">
                                        <li class="layui-col-xs3">
                                            <a lay-href="/admin/modify-info">
                                                <i class="layui-icon layui-icon-set"></i>
                                                <cite>我的资料</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="/admin/modify-password">
                                                <i class="layui-icon layui-icon-password"></i>
                                                <cite>修改密碼</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="/admin/user-event">
                                                <i class="layui-icon layui-icon-log"></i>
                                                <cite>用户事件</cite>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs3">
                                            <a lay-href="/admin/sysuser-event">
                                                <i class="layui-icon layui-icon-log"></i>
                                                <cite>管理员事件</cite>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-header">待办事项</div>
                        <div class="layui-card-body">
                            <div class="layui-carousel layadmin-carousel layadmin-backlog">
                                <div carousel-item>
                                    <ul class="layui-row layui-col-space10">
                                        <li class="layui-col-xs6">
                                            <a lay-href="/admin/video/videoIndex?status=0" lay-text="待审文章" class="layadmin-backlog-body">
                                                <h3>待审视频</h3>
                                                <p><cite>${(countMap.videoCount)!0}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs6">
                                            <a lay-href="/admin/app/video/commentIndex?status=0" lay-text="待审评论" class="layadmin-backlog-body">
                                                <h3>待审评论</h3>
                                                <p><cite>${(countMap.commentCount)!0}</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                    <#--   <ul class="layui-row layui-col-space10">
                                           <li class="layui-col-xs6">
                                               <a href="javascript:;" class="layadmin-backlog-body">
                                                   <h3>待审友情链接</h3>
                                                   <p><cite style="color: #FF5722;">5</cite></p>
                                               </a>
                                           </li>
                                       </ul>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-header">版本信息</div>
                        <div class="layui-card-body">
                            <table class="layui-table">
                                <colgroup>
                                    <col>
                                    <col>
                                </colgroup>
                                <tbody>
                                <tr>
                                    <td>当前版本</td>
                                    <td>
                                        v1.0.0
                                        <a href="https://www.yuque.com/?#" target="_blank" style="padding-left: 15px;color: #01AAED;">更新日志</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>系统信息</td>
                                    <td>
                                        <span id="system"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>客户端</td>
                                    <td>
                                        <span id="browser"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>当前时间</td>
                                    <td>
                                        <span id="currDate"></span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#--用户增长趋势图-->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">
                        用户增长趋势
                    </div>
                    <div class="layui-card-body">
                        <div id="userEchart" style="width: 100%;min-height:400px;margin-top: 20px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#--用户动态、管理员动态-->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    用户动态
                    <a lay-href="/admin/user/userManage/eventIndex" class="layui-a-tips" lay-text="操作日志">More</a>
                </div>
                <div class="layui-card-body layadmin-takerates">
                    <dl class="layuiadmin-card-status">
                        <#if userEventList??>
                            <#list userEventList as userEvenet>
                                <dd>
                                    <div style="float: right;width: 100%;">
                                        <span style="color:#01AAED;"><i>${(userEvenet.nicknameCn)!}</i></span>
                                        <span style="margin-left: 10px;">${(userEvenet.remark)!}</span>
                                    </div>
                                    <div style="float: right;width: 100px;">
                                        <span>${(userEvenet.dateStr)!}</span>
                                    </div>
                                </dd>
                            </#list>
                        </#if>
                    </dl>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    管理员动态
                    <a lay-href="/admin/system/manager/eventIndex" class="layui-a-tips" lay-text="操作日志">More</a>
                </div>
                <div class="layui-card-body layadmin-takerates">
                    <dl class="layuiadmin-card-status">
                        <#if adminEventList??>
                            <#list adminEventList as adminEvenet>
                                <dd>
                                    <div style="float: right;width: 100%;">
                                        <span style="color:#01AAED;"><i>${(adminEvenet.realname)!}</i></span>
                                        <span style="margin-left: 10px;">${(adminEvenet.remark)!}</span>
                                    </div>
                                    <div style="float: right;width: 100px;">
                                        <span>${(adminEvenet.dateStr)!}</span>
                                    </div>
                                </dd>
                            </#list>
                        </#if>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.config({
        base: '/lib/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
    }).use(['index', 'console','form','laydate', 'echarts'], function () {
        let browser = getBrowserInfo();
        let browserInfo = browser.client.name + "/" + browser.client.version;
        // 浏览器
        $("#browser").html(browserInfo);
        // 系统信息
        $("#system").html(getOsInfo());
        // 计时器
        countdown("currDate", new Date());
    });
</script>