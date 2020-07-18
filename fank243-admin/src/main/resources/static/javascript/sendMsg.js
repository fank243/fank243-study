/**
 * 发送短信
 * @param btnEle 发送验证码按钮ID，格式：#btnSmsCode
 * @param mobile 手机号码
 * @param templateCode 通知模板代码，{@code com.fank243.springboot.core.enums.TemplateCode}
 * @param times 倒计时时长
 */
function sendSms(btnEle, mobile, templateCode, times) {
    $(btnEle).attr("disabled", "disabled").html("发送中...");
    $.ajax({
        url: "/common/sendSms",
        type: "POST",
        async: false,
        data: {"mobile": mobile, "templateCode": templateCode},
        success: function (data) {
            let flag = callback(btnEle, times, data);
            if (flag) {
                $("input[name='isVerifyMobile']").val(1);
            }
        },
        error: function () {
            layer.alert("系统繁忙，请稍后再试");
        }
    });
    return false;
}

/**
 * 发送邮件
 * @param btnEle 发送验证码按钮ID，格式：#btnEmailCode
 * @param email 邮箱地址
 * @param templateCode 通知模板代码，{@code com.fank243.springboot.core.enums.TemplateCode}
 * @param times 倒计时时长
 */
function sendEmail(btnEle, email, templateCode, times) {
    $(btnEle).attr("disabled", "disabled").html("发送中...");
    $.ajax({
        url: "/common/sendEmail",
        type: "POST",
        data: {"email": email, "templateCode": templateCode},
        success: function (data) {
            let flag = callback(btnEle, times, data);
            if (flag) {
                $("input[name='isVerifyEmail']").val(1);
            }
        },
        error: function () {
            layer.alert("系统繁忙，请稍后再试");
        }
    });
}

/**
 * 发送验证码回调
 * @param btnEle 发送验证码按钮ID
 * @param times 验证码倒计时时长
 * @param data 回调参数
 */
function callback(btnEle, times, data) {
    let initTime = times;
    if (data.code !== 200) {
        $(btnEle).removeAttr("disabled").html("发送验证码");
        layer.msg(data.msg, {icon: 2});
        return false;
    }
    let interval = setInterval(function () {
        times--;
        if (times <= 0) {
            times = initTime;
            clearInterval(interval);
            $(btnEle).removeAttr("disabled").removeClass("layui-btn-disabled").html("发送验证码");
        } else {
            $(btnEle).addClass("layui-btn-disabled").html(times);
        }
    }, 1000);
    layer.msg(data.msg);
    return true;
}




