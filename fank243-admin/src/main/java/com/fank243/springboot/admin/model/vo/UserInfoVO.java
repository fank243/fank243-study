package com.fank243.springboot.admin.model.vo;

import com.fank243.springboot.admin.model.Update;
import com.fank243.springboot.core.enums.Gender;
import com.fank243.springboot.core.enums.UserSource;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Enumerated;
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
public class UserInfoVO implements Serializable {

    @Min(value = 1, message = "用户不存在", groups = Update.class)
    @NotNull(message = "用户ID不可为空", groups = Update.class)
    private Long id;

    @Pattern(regexp = "[\\u4E00-\\u9FA5]{2,4}", message = "姓名必须为2-4个汉字")
    @NotBlank(message = "姓名不可为空")
    private String realname;

    @Length(min = 3, max = 20, message = "微信账号格式不正确")
    @NotBlank(message = "请填写微信账号")
    private String wxNumber;

    @NotNull
    @Enumerated
    private Gender gender;

    @Pattern(regexp = "[0-9]{6,10}", message = "QQ号码格式不正确")
    @NotBlank(message = "请填写QQ号码")
    private String qq;

    @Length(min = 3, max = 20, message = "微信账号格式不正确")
    @NotBlank(message = "请填写微信账号")
    private String weibo;

    @NotNull
    @Enumerated
    private UserSource source;

    @Length(min = 10, max = 200, message = "居住地址过于简单")
    @NotBlank(message = "请填写居住地址")
    private String addr;

    public void setGender(int gender) {
        this.gender = Gender.getEnum(gender);
    }

    public void setSource(int source) {
        this.source = UserSource.getEnum(source);
    }

}
