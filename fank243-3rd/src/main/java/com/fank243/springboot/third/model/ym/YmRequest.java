package com.fank243.springboot.third.model.ym;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

/**
 * U-Push 顶层参数
 * 
 * @author FanWeiJie
 * @date 2019-12-21 15:24:46
 */
@ToString
@Data
@SuppressWarnings("SpellCheckingInspection")
public class YmRequest {
    /** 必填，应用唯一标识 **/
    private String appkey = "";

    /** 必填，时间戳，10位或者13位均可，时间戳有效期为10分钟 **/
    private String timestamp = System.currentTimeMillis() + "";

    /** 必填，消息发送类型 **/
    private String type = "";

    /** 当type=unicast时, 必填, 表示指定的单个设备 ，当type=listcast时, 必填, 要求不超过500个, 以英文逗号分隔 **/
    private String device_tokens = "";

    /** 当type=customizedcast时, 必填alias的类型, alias_type可由开发者自定义, 开发者在SDK中调用setAlias(alias, alias_type)时所设置的alias_type **/
    private String alias_type = "";
    /**
     * 当type=customizedcast时, 选填(此参数和file_id二选一) 开发者填写自己的alias, 要求不超过500个alias, 多个alias以英文逗号间隔在SDK中调用setAlias(alias,
     * alias_type)时所设置的alias
     **/
    private String alias = "";
    /**
     * 当type=filecast时，必填，file内容为多条device_token，以回车符分割 当type=customizedcast时，选填(此参数和alias二选一)
     * ile内容为多条alias，以回车符分隔。注意同一个文件内的alias所对应 的alias_type必须和接口参数alias_type一致。 使用文件播需要先调用文件上传接口获取file_id，参照"文件上传"
     **/
    private String file_id = "";

    /** 当type=groupcast时，必填，用户筛选条件，如用户标签、渠道等，参考附录G。filter的内容长度最大为3000B） **/
    private String filter = "";

    /** 具体消息内容(Android最大为1840B) **/
    private JSONObject payload;

    /** 发送策略 **/
    private JSONObject policy;
    /**
     * 可选，正式/测试模式。默认为true 测试模式只对“广播”、“组播”类消息生效，其他类型的消息任务（如“文件播”）不会走测试模式
     * 
     * 测试模式只会将消息发给测试设备。测试设备需要到web上添加。
     * 
     * Android:测试设备属于正式设备的一个子集。
     **/
    private String production_mode = "true";

    /** 发送消息描述，建议填写 **/
    private String description = "";

}
