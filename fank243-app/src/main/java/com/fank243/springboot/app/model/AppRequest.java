package com.fank243.springboot.app.model;

import com.fank243.springboot.core.enums.DeviceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * App 请求实体
 * 
 * @author FanWeiJie
 * @date 2019-11-27 10:48:28
 */
@ApiModel(description = "请求实体")
@ToString
@Data
public class AppRequest implements Serializable {

    /** App 版本号 **/
    @NotBlank(message = "App版本号必传")
    @ApiModelProperty(hidden = true)
    private String version;

    /** 服务端接口版本号 **/

    @NotNull(message = "Api版本号必传")
    @ApiModelProperty(hidden = true)
    private Integer apiVer;

    /** 请求时间戳 **/
    @NotNull(message = "时间戳必传")
    @ApiModelProperty(hidden = true)
    private Long timestamp = 0L;

    /** 设备类型，{@link com.fank243.springboot.core.enums.DeviceType} **/
    @NotNull(message = "设备类型必传")
    @ApiModelProperty(hidden = true)
    private DeviceType deviceType;

    /** 设备唯一标识符 **/
    @Length(min = 20, max = 60, message = "设备号格式错误")
    @NotBlank(message = "设备号必传")
    @ApiModelProperty(hidden = true)
    private String deviceNumber;

    /** 加密数据 **/
    @NotBlank(message = "载荷参数必传")
    @ApiModelProperty(hidden = true)
    private String payload = "";

    /** MD5签名值 **/
    @Pattern(regexp = "[A-Za-z0-9]{32}", message = "签名验证不通过")
    @NotBlank(message = "签名验证不通过")
    @ApiModelProperty(hidden = true)
    private String signature;

    public void setDeviceType(Integer deviceType) {
        this.deviceType = DeviceType.getEnum(deviceType);
    }
}
