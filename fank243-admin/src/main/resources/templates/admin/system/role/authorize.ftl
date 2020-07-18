<#include "/layout/res.ftl"/>
<div class="layui-fluid">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">选择权限</label>
            <div class="layui-input-block">
                <div id="LAY-auth-tree-index"></div>
            </div>
        </div>
        <div class="layui-form-item layui-hide">
            <input type="button" lay-submit lay-filter="LAY-auth-tree-submit" id="LAY-auth-tree-submit" value="确定">
        </div>
    </form>
</div>

<script type="text/javascript">
    layui.config({
        base: '/lib/layuiadmin/' //静态资源所在路径
    }).extend({
        authtree: 'modules/authtree/authtree' //主入口模块
    }).use(['form', 'table', "authtree"], function () {
        let form = layui.form;
        let layer = layui.layer;
        //参考文档：https://gitee.com/wangerzi/layui-authtree
        let authtree = layui.authtree;

        layer.load(1);
        $.ajax({
            url: "/admin/role/authorize?roleId=${roleId}",
            type: "GET",
            success: function (data) {
                layer.closeAll();
                if (!data.success) {
                    layer.msg(data.msg, {icon: 2});
                    return;
                }
                let payload = data.payload;
                let trees = authtree.listConvert(payload.list, {
                    primaryKey: 'id'
                    , startPid: 0
                    , parentKey: 'pid'
                    , nameKey: 'name'
                    , valueKey: 'id'
                    , checkedKey: payload.checkedId
                    , disabledKey: payload.disabledId
                });
                authtree.render('#LAY-auth-tree-index', trees, {
                    inputname: 'permIds[]',
                    layfilter: 'lay-check-auth',
                    autowidth: true,
                    openchecked: true, //展开选中的菜单
                    openall: false, // 全部展开
                });

            },
            error: function () {
                layer.closeAll();
                layer.alert("系统繁忙，请稍后再试");
            }
        });

        //监听提交
        form.on('submit(LAY-auth-tree-submit)', function (data) {
            let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

            let permIds = authtree.getChecked('#LAY-auth-tree-index');
            permIds = permIds.join(",");

            layer.load(1);
            $.ajax({
                url: "/admin/role/authorize/${roleId}",
                type: "POST",
                data: {"permIds": permIds},
                success: function (data) {
                    layer.closeAll();
                    if (data.code !== 200) {
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
            })
        });
    });
</script>