package com.fank243.springboot.admin.model.vo;

import com.fank243.springboot.admin.model.Update;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户表单
 * 
 * @author FanWeiJie
 * @date 2020-03-28 00:01:19
 */
@ToString
@Data
public class UserVO implements Serializable {

    @Min(value = 1, message = "用户不存在", groups = Update.class)
    @NotNull(message = "用户ID不可为空", groups = Update.class)
    private Long id;

    @Pattern(regexp = "[a-zA-Z0-9_-]{4,12}", message = "用户名由4-12位英文数字下划线组成")
    @NotBlank(message = "用户名不可为空")
    private String username;

    @Length(min = 3, max = 20, message = "昵称长度在3-20位之间")
    @NotBlank(message = "请填写昵称")
    private String nickname;

    @Pattern(regexp = "1[3-9]\\d{9}", message = "手机号码格式不正确")
    @NotBlank(message = "手机号码不可为空")
    private String mobile;

    @Length(min = 8, max = 255, message = "请上传头像")
    @NotBlank(message = "请上传头像")
    private String photo;

    @NotNull(message = "")
    private Integer status;
}
