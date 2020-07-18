<div class="layui-form" wid100 lay-filter="redisForm">
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" class="layui-btn" id="btnSys" value="刷新系统配置缓存">
        </div>
    </div>
</div>


<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer;

       $("#btnSys").click(function () {
           refreshCache(1);
       });

       function refreshCache(type) {
           layer.load(1);
           $.ajax({
               type:"GET",
               url:"/admin/site/refreshCache",
               data:{"type":type},
               success:function (data) {
                   layer.closeAll();
                   if(data.code !== 200){
                       layer.msg(data.msg,{icon:2});
                       return;
                   }
                   layer.msg(data.msg);
               },
               error:function () {
                   layer.closeAll();
                   layer.alert("系统繁忙，请稍后重试");
               }
           });
       }
    });
</script>
