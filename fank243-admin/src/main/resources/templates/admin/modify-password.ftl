<#include "/layout/res.ftl"/>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">修改密码</div>
                <div class="layui-card-body" pad15>

                    <div class="layui-form" lay-filter="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">当前密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="oldPassword" lay-verify="required" lay-verType="tips" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="password" lay-verify="required" lay-verType="tips" autocomplete="off" id="LAY_password" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">6-20位(可由英文、数字、下划线、英文句点)</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">确认新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="rePassword" lay-verify="required" lay-verType="tips" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="btnPwd">确认修改</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    layui.use(['form','layer'],function () {
       var form = layui.form;
       var layer = layui.layer;

       form.on("submit(btnPwd)",function (data) {
           var field = data.field;
          $.ajax({
             url:"/admin/modify-password",
              type:"post",
              data:field,
              success:function (data) {
                  if(data.code !== 200){
                      layer.msg(data.msg,{icon:2});
                      return;
                  }
                  layer.msg(data.msg,{time:2000},function () {
                      window.parent.location.href = '/admin/logout';
                  });
              },
              error:function () {
                  layer.alert(data.msg);
              }
          });
       });
    });
</script>