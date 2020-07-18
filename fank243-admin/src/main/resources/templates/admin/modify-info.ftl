<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">修改个人资料</div>
                <div class="layui-card-body" pad15>
                    <div class="layui-form" lay-filter="">
                        <input type="hidden" name="id" value="${sysUser.id}">
                        <input type="hidden" name="isVerifyMobile" value="false">
                        <input type="hidden" name="isVerifyEmail" value="false">
                        <div class="layui-form-item">
                            <label class="layui-form-label">我的角色</label>
                            <div class="layui-input-inline">
                               <input type="text" class="layui-input" onclick="showTips(this,'不可修改哦')" style="width:200%" value="${roleNames!}" readonly>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" value="${sysUser.username!''}" readonly onclick="showTips(this,'不可修改哦')" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="realname" value="${sysUser.realname!''}" lay-verify="required" class="layui-input" autocomplete="off" placeholder="请输入姓名">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">微信号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="wxNumber" value="${(sysUser.wxNumber)!}" autocomplete="off" placeholder="请输入微信号" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机</label>
                            <div class="layui-input-inline">
                                <input type="number" oninput="if(value.length>11)value=value.slice(0,11)" name="mobile" value="${sysUser.mobile}" lay-verify="phone" autocomplete="off" class="layui-input">
                            </div>
                            <button class="layui-btn" id="btnSmsCode" lay-filter="btnSmsCode">发送验证码</button>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">短信验证码</label>
                            <div class="layui-input-inline">
                                <input type="number" name="smsCode" value="" autocomplete="off" class="layui-input" <#if !isProd>placeholder="测试环境不发短信不验证"</#if>>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-inline">
                                <input type="text" name="email" value="${(sysUser.email)!}" autocomplete="off" class="layui-input">
                            </div>
                            <button class="layui-btn" id="btnEmailCode" lay-filter="btnEmailCode">发送验证码</button>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱验证码</label>
                            <div class="layui-input-inline">
                                <input type="number" name="emailCode" value="" autocomplete="off" class="layui-input" <#if !isProd>placeholder="测试环境不发邮件不验证"</#if>>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="setmyinfo">确认修改</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    let times = 60;
    layui.use(['layer', 'form'], function () {
        let layer = layui.layer, form = layui.form;

        //发送验证码
        $("#btnSmsCode").click(function () {
            let mobile = $("input[name='mobile']").val();
            if(mobile === "${(sysUser.mobile)!''}"){
                layer.tips('未更换手机号码，无需发送验证码',this, {tipsMore: true});
                return;
            }
            sendSms("#btnSmsCode", mobile,"${TemplateCode.VER_CODE.name()}", 60);
        });


        //发送邮件验证码
        $("#btnEmailCode").click(function () {
            let email = $("input[name='email']").val();
           if(email === "${(sysUser.email)!''}"){
               layer.tips('未更换邮箱，无需发送验证码',this, {tipsMore: true});
               return;
           }
           sendEmail("#btnEmailCode", email,"${TemplateCode.VER_CODE.name()}", 60);
        });

        form.on("submit(setmyinfo)",function (data) {
            let field = data.field; //获取提交的字段

            let mobile = '${(sysUser.mobile)!""}',_mobile = $("input[name='mobile']").val();
            let email = '${(sysUser.email)!''}',_email = $("input[name='email']").val();
            let isVerifyMobile = $("input[name='isVerifyMobile']").val();
            let isVerifyEmail = $("input[name='isVerifyEmail']").val();

            if(isVerifyMobile === 0 && mobile !== _mobile){
                layer.msg("请验证手机号码",{icon:2});
                return;
            }
            if(isVerifyEmail === 0 && email !== _email){
                layer.msg("请验证电子邮箱",{icon:2});
                return;
            }

            layer.load(1);
            $.ajax({
               url:"/admin/modify-info",
               type:"POST",
               data:field,
               success:function (data) {
                   layer.closeAll();
                   if(!data.success){
                       layer.msg(data.msg,{icon:2});
                       return;
                   }
                   layer.msg(data.msg);
               },
               error:function () {
                   layer.alert("系统繁忙，请稍后重试");
               }
            });
        });
    });

</script>