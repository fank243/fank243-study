<script>
    layui.use(["layer", 'upload'], function () {
        let layer = layui.layer;
        let upload = layui.upload;

        upload.render({
            elem: '#layUpload'
            , url: '/common/uploadImg'
            , accept: 'images'
            , method: 'get'
            , acceptMime: 'image/*'
            , before: function () {
                layer.msg("正在上传，请稍后...");
                layer.load(1);
            }
            , done: function (res) {
                layer.closeAll();
                if (res.code !== 200) {
                    layer.alert(res.msg);
                    return;
                }
                layer.msg(res.msg);
                $(this.item).prev("div").children("input").val(res.payload);
                $("#showImg").attr("src", "${imgBaseUrl!''}" + res.payload);
            }
            , error: function () {
                layer.closeAll();
                layer.msg("上传出错了，请稍后再试！")
            }
        });
    });
</script>