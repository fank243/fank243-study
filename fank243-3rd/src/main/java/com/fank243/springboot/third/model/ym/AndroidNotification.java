package com.fank243.springboot.third.model.ym;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 安卓消息
 * 
 * @author FanWeiJie
 * @date 2019-12-21 15:43:10
 */
@NoArgsConstructor
@Data
public class AndroidNotification {

    /** notification **/
    public AndroidNotification(String displayType, String ticker, String title, String text) {
        this.displayType = displayType;
        this.ticker = ticker;
        this.title = title;
        this.text = text;
    }

    /** message **/
    public AndroidNotification(String displayType, String custom) {
        this.displayType = displayType;
        this.custom = custom;
    }

    /** 消息类型 **/
    private String displayType;

    /** 必填，通知栏提示文字 **/
    private String ticker = "";

    /** 必填，通知标题 **/
    private String title = "";

    /** 必填，通知文字描述 **/
    private String text = "";

    /** 可选，通知栏拉开后左侧图标ID，R.drawable.[largeIcon]， **/
    private String icon = "";

    /** 通知栏拉开后左侧图标ID，R.drawable.[largeIcon]， **/
    private String largeIcon = "";

    /** 可选，通知栏大图标的URL链接。该字段的优先级大于largeIcon。 **/
    private String img = "";

    /** 自定义通知声音 **/
    private String sound = "";

    /** 通知到达设备后的提醒方式，注意，"true/false"为字符串 **/
    private String builder_id = "";

    /** 收到通知是否震动，默认为"true" **/
    private String play_vibrate = "true";

    /** 收到通知是否闪灯，默认为"true" **/
    private String play_lights = "true";

    /** 收到通知是否发出声音，默认为"true" **/
    private String play_sound = "true";

    /** 点击"通知"的后续行为，默认为打开app。 **/
    private String after_open = "go_app";

    /** 通知栏点击后跳转的URL，要求以http或者https开头 **/
    private String url = "";

    /** 通知栏点击后打开的Activity **/
    private String activity = "";

    /**
     * 用户自定义内容，可以为字符串或者JSON格式
     * 
     * 当display_type=message时, 必填
     * 
     * 当display_type=notification且after_open=go_custom时，必填
     **/
    private String custom = "";

    public JSONObject getJson() {
        JSONObject payload = new JSONObject();

        JSONObject body = new JSONObject();
        if ("message".equalsIgnoreCase(this.displayType)) {
            body.put("custom", this.custom);
        } else {
            body.put("ticker", this.ticker);
            body.put("title", this.title);
            body.put("text", this.text);
        }

        payload.put("display_type", this.displayType);
        payload.put("body", body);
        return payload;
    }
}
