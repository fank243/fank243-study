<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list"
             style="padding: 20px 30px 0 0;">
            <div class="layui-form-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">菜单名称</label>
                    <div class="layui-input-block">
                        <input class="layui-input" lay-verType="tips" lay-verify="required" name="name" value=""
                               autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">菜单类型</label>
                    <div class="layui-input-block">
                        <input type="radio" name="type" value="${PermissionType.BUTTON.name()}" title="${PermissionType.BUTTON.desc}" checked/>
                        <input type="radio" name="type" value="${PermissionType.MENU.name()}" title="${PermissionType.MENU.desc}" />
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">父菜单</label>
                    <div class="layui-input-inline">
                        <select name="pid" lay-filter="firstMenu">
                            <option value="0">请选择菜单</option>
                            <#list permissionList as perm>
                                <option value="${perm.id}" <#if (perm.id == sid0)!false>selected</#if>>${perm.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="spid" id="secondMenu" lay-filter="secondMenu">
                            <option value="0">请选择菜单</option>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select name="tpid" id="thirdMenu">
                            <option value="0">请选择菜单</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">权限</label>
                    <div class="layui-input-block">
                        <input class="layui-input" name="permission" value="" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">菜单图标</label>
                    <div class="layui-input-inline">
                        <input class="layui-input" id="iconPicker" name="icon" value="" autocomplete="off"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">链接地址</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="uri" value="" autocomplete="off" placeholder="请输入访问URI"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">外部连接</label>
                    <div class="layui-input-block">
                        <input type="radio" name="external" value="0" title="否" checked>
                        <input type="radio" name="external" value="1" title="是"/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">序号</label>
                    <div class="layui-input-block">
                        <input type="number" class="layui-input" lay-verify="required" lay-verType="tips" name="sort" autocomplete="off" placeholder="请输入">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-hide">
                <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
            </div>
        </div>
    </div>
</div>

<script>
    layui.config({
        base: "/lib/layuiadmin/"  // 配置你下载的iconFonts文件夹路径
    }).extend({
        iconPicker: 'modules/iconPicker/iconPicker'
    }).use(['form', 'iconPicker'], function () {
        let form = layui.form;
        let iconPicker = layui.iconPicker;

        iconPicker.render({
            // 选择器，推荐使用input
            elem: '#iconPicker',
            // 数据类型：fontClass/layui_icon，
            type: 'fontClass',
            // 是否开启搜索：true/false，默认true
            search: true,
            // 是否开启分页：true/false，默认true
            page: true,
            // 每页显示数量，默认12
            limit: 12,
            // 每个图标格子的宽度：'43px'或'20%'
            cellWidth: '43px',
            // 点击回调
            click: function (data) {
            },
            // 渲染成功后的回调
            success: function (d) {
            }
        });

        // 父菜单
        form.on("select(firstMenu)", function (data) {
            let pid = data.value;
            if (pid <= 0) {
                $("#secondMenu").html("<option value='0'>请选择菜单</option>");
                $("#thirdMenu").html("<option value='0'>请选择菜单</option>");
                form.render();
                return;
            }
            loadChildMenu("#secondMenu", pid);
        });
        // 父菜单
        form.on("select(secondMenu)", function (data) {
            let pid = data.value;
            if (pid <= 0) {
                $("#secondMenu").html("<option value='0'>请选择菜单</option>");
                $("#thirdMenu").html("<option value='0'>请选择菜单</option>");
                form.render();
                return;
            }
            loadChildMenu("#thirdMenu", pid);
        });

        //监听提交
        form.on('submit(layuiadmin-app-form-submit)', function (data) {
            let field = data.field; //获取提交的字段
            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

            //提交 Ajax 成功后，关闭当前弹层并重载表格
            layer.load(1);
            $.ajax({
                url: '/admin/permission/add',
                type: 'POST',
                data: field,
                success: function (data) {
                    layer.closeAll();
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return;
                    }
                    layer.msg(data.msg, {time: 1000}, function () {
                        parent.layui.table.reload('layTable'); //重载表格
                        parent.layer.close(index); //再执行关闭
                    });
                },
                error: function () {
                    layer.closeAll();
                    layer.alert('系统繁忙，请稍后重试');
                }
            });
        });

        function loadChildMenu(elmId, pid) {
            $.ajax({
                url: "/admin/permission/getChildMenu/" + pid,
                type: "GET",
                success: function (data) {
                    if (!data.success) {
                        layer.msg(data.msg, {icon: 2});
                        return false;
                    }
                    let menus = data.payload;
                    if (menus.length <= 0) {
                        form.render();
                        $(elmId).html("<option value='0'>请选择菜单</option>");
                        $("#thirdMenu").html("<option value='0'>请选择菜单</option>");
                        form.render();
                        return false;
                    }

                    let option = "<option value='0'>请选择菜单</option>";
                    for (let i = 0; i < menus.length; i++) {
                        option += "<option value='" + menus[i].id + "'>" + menus[i].name + "</option>";
                    }
                    $(elmId).html(option);
                    form.render();
                },
                error: function () {
                    layer.msg("系统繁忙,请稍后再试", {icon: 2});
                }
            });
        }

        form.render();
    });
</script>