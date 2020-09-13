package com.fank243.springboot.admin.config.freemarker.dict;

import lombok.Data;

/**
 * @author FanWeiJie
 * @date 2020-09-13 13:52:10
 */
@Data
public class DictModel {

    /** 类型 */
    private String eleType;

    /** 字典标识 */
    private String dictType;

    /** 对象属性名【需要进行对象属性获取】 */
    private String fieldName;

}
