<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select name="logType" class="layui-select">
                            <option value="">日志类别</option>
                            <#list AppLogType.values() as logType>
                                <option value="${logType.name()}">${logType.desc}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select name="logLevel" class="layui-select">
                            <option value="">日志等级</option>
                            <#list LogLevel.values() as logLevel>
                                <option value="${logLevel.name()}">${logLevel.name()}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="keyword" autocomplete="off" placeholder="姓名/手机号码">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select name="deviceType" class="layui-select">
                            <option value="">设备类型</option>
                            <#list DeviceType.values() as deviceType>
                                <option value="${deviceType.name()}">${deviceType.desc}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="deviceNumber" autocomplete="off" placeholder="设备号">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="logDesc" autocomplete="off" placeholder="日志描述">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="requestUri" autocomplete="off" placeholder="请求地址">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="sessionId" autocomplete="off" placeholder="Session ID">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" name="requestId" autocomplete="off" placeholder="Request ID">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="number" min="200" class="layui-input" name="responseStatus" autocomplete="off"
                               placeholder="HTTP 响应码">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="number" min="200" class="layui-input" name="resultCode" autocomplete="off"
                               placeholder="Result Code">
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input class="layui-input" style="width: 200%" id="date" name="date" autocomplete="off" placeholder="时间">
                    </div>
                </div>
                <div class="layui-inline" style="margin-left: 180px">
                    <button class="layui-btn layuiadmin-btn-list" lay-submit lay-filter="btnSearch" id="btnSearch">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <table class="layui-table"
                   lay-data="{width:'100%',url:'/admin/system/logger/app/logList', page:true, id:'layTable'}"
                   lay-filter="layTable">
                <thead>
                <tr>
                    <th lay-data="{field:'id', width:80, align: 'center', sort: true}">序号</th>
                    <th lay-data="{field:'realname',width:100}">姓名</th>
                    <th lay-data="{field:'mobile',width:150}">手机号码</th>
                    <th lay-data="{field:'logLevel',width:100}">日志等级</th>
                    <th lay-data="{field:'logTypeCn',width:100}">日志类别</th>
                    <th lay-data="{field:'logDesc',width:180}">日志描述</th>
                    <th lay-data="{field:'requestUri',width:230}">请求地址</th>
                    <th lay-data="{field:'requestMethod',width:100}">请求方法</th>
                    <th lay-data="{field:'responseStatus',width:120}">HTTP 响应码</th>
                    <th lay-data="{field:'resultCode',width:120}">Result Code</th>
                    <th lay-data="{field:'deviceTypeCn',width:100}">设备类型</th>
                    <th lay-data="{field:'deviceNumber',width:300}">设备号</th>
                    <th lay-data="{field:'requestIp',width:150}">请求IP</th>
                    <th lay-data="{field:'requestIpAddr',width:150}">请求IP归属地</th>
                    <th lay-data="{field:'sessionId',width:300}">Session ID</th>
                    <th lay-data="{field:'requestId',width:300}">Request ID</th>
                    <th lay-data="{field:'requestTime',width:180}">请求时间</th>
                    <th lay-data="{field:'responseTime',width:180}">响应时间</th>
                    <th lay-data="{field:'times',width:100}">延时(毫秒)</th>
                    <th lay-data="{field:'gmtCreated',width:180}">创建时间</th>
                    <th lay-data="{fixed:'right',width:180, align:'center',toolbar:'#barDemo'}">操作</th>
                </tr>
                </thead>
            </table>
            <script type="text/html" id="barDemo">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="request">请求参数</a>
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="response">响应参数</a>
            </script>
        </div>
    </div>
</div>

<div id="body" class="layui-card-body layui-hide">
    <div class='layui-code' lay-title="参数" lay-height="200" lay-skin="" lay-encode="json"></div>
</div>

<script type="text/javascript">
    layui.use(['form', 'table','code', 'laypage', 'laydate'], function () {
        let table = layui.table;
        let form = layui.form;
        let laydate = layui.laydate;

        laydate.render({
            elem: "#date",
            type: "datetime",
            format: "yyyy-MM-dd HH:mm:ss"
            ,range: '~' //或 range: '~' 来自定义分割字符
        });

        //监听搜索
        form.on('submit(btnSearch)', function (data) {
            let field = data.field;
            //执行重载
            table.reload('layTable', {where: field});
        });

        //监听行工具事件
        table.on('tool(layTable)', function(obj){
            let data = obj.data;
            if(obj.event === 'request'){
                let requestBody = data.requestBody;
                $("#body").removeClass("layui-hide");
                $("#body").children("div").html(requestBody);
                layui.code();
                layer.open({
                    type: 1,
                    btn:["确定"],
                    maxmin:true,
                    title:"请求参数",
                    area:["40%","60%"],
                    content: $("#body"),
                    yes: function(index, layero){
                        $("#body").addClass("layui-hide");
                        layer.close(index)
                    },
                    cancel: function(index, layero){
                        $("#body").addClass("layui-hide");
                    }
                });
            }else if(obj.event === 'response'){
                let responseBody = data.responseBody;
                $("#body").removeClass("layui-hide");
                $("#body").children("div").html(responseBody);
                layui.code();
                layer.open({
                    type: 1,
                    btn:["确定"],
                    maxmin:true,
                    title:"响应参数",
                    area:["40%","60%"],
                    content: $("#body"),
                    yes: function(index, layero){
                        $("#body").addClass("layui-hide");
                        layer.close(index)
                    },
                    cancel: function(index, layero){
                        $("#body").addClass("layui-hide");
                    }
                });
            }
        });
    });


</script>