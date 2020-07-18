/**
 * 上传视频至 Aliyun OSS
 * @param file 视频文件
 */
function uploadVideoToOss(file) {
    $.getJSON("/common/getAliyunOss", {"type": 1}, function (data) {
        if (!data.success) {
            layer.msg(data.msg, {icon: 2});
            return;
        }
        let policy = data.payload;
        let filename = new Date().getTime() + getSuffix(file.name);
        let formData = new FormData();

        //注意formData里append添加的键的大小写
        formData.append('key', policy.dir + "/" + filename);  //存储在oss的文件路径
        formData.append('policy', policy.policy);  //policy
        formData.append('OSSAccessKeyId', policy.accessId);  //accessKeyId
        formData.append('success_action_status', "200");  //成功后返回的操作码
        formData.append('Signature', policy.signature);   //签名
        formData.append("file", file);

        let url = policy.host;
        layer.msg("正在上传至阿里云OSS，可能需要一点点时间哦！");
        let index = layer.load(1);
        setTimeout(function () {
            let videoId = $("input[name='videoId']").val();
            $.ajax({
                url: url,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    layer.close(index);
                    if (data === "") {
                        let ossUrl = "/" + policy.dir + "/" + filename;
                        $("input[name='url']").val(ossUrl);
                        layer.msg("视频已上传至OSS");
                        return;
                    }
                    window.localStorage.setItem("REQ_" + videoId, JSON.stringify(convertFormdataToJson(formData)));
                    window.localStorage.setItem("Resp_" + videoId, JSON.stringify(data));
                    layer.alert("视频上传失败，请联系技术人员");
                },
                error: function (data) {
                    layer.close(index);
                    window.localStorage.setItem("REQ_" + videoId, JSON.stringify(convertFormdataToJson(formData)));
                    window.localStorage.setItem("Resp_" + videoId, JSON.stringify(data));
                    layer.alert("视频上传失败，请联系技术人员");
                }
            });
        }, 500);
    });
}

/**
 * 上传Apk至 Aliyun OSS
 * @param version apk版本号
 * @param file 视频文件
 */
function uploadApkToOss(version, file) {
    $.getJSON("/common/getAliyunOss", {"type": 2}, function (data) {
        if (!data.success) {
            layer.msg(data.msg, {icon: 2});
            return;
        }
        let policy = data.payload;
        let filename = "jboot-" + version + ".apk";
        let formData = new FormData();

        //注意formData里append添加的键的大小写
        formData.append('key', policy.dir + "/" + filename);  //存储在oss的文件路径
        formData.append('policy', policy.policy);  //policy
        formData.append('OSSAccessKeyId', policy.accessId);  //accessKeyId
        formData.append('success_action_status', "200");  //成功后返回的操作码
        formData.append('Signature', policy.signature);   //签名
        formData.append("file", file);

        let url = policy.host;
        layer.msg("正在上传至阿里云OSS，可能需要一点点时间哦！");
        let index = layer.load(1);
        setTimeout(function () {
            $.ajax({
                url: url,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    layer.close(index);
                    if (data === "") {
                        let ossUrl = url + "/" + policy.dir + "/" + filename;
                        $("#downloadUrl").val(ossUrl);
                        layer.msg("Apk已上传至OSS");
                        return;
                    }
                    layer.alert("Apk上传失败，请联系技术人员");
                },
                error: function (data) {
                    layer.close(index);
                    layer.alert("Apk上传失败，请联系技术人员");
                }
            });
        }, 500);
    });
}


