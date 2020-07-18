package com.fank243.springboot.third.model.ym;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 苹果消息
 * 
 * @author FanWeiJie
 * @date 2019-12-21 15:43:10
 */
@NoArgsConstructor
@Data
public class IosNotification {

    private String title;
    private String subtitle;
    private String body;
    private String badge;
    private String sound;
    private String category;

    public IosNotification(String title, String subtitle, String body) {
        this.title = title;
        this.subtitle = subtitle;
        this.body = body;
    }

    public JSONObject getJson() {
        JSONObject payload = new JSONObject();
        JSONObject aps = new JSONObject();
        JSONObject alert = new JSONObject();

        alert.put("title", this.title);
        alert.put("subtitle", this.subtitle);
        alert.put("body", this.body);

        aps.put("alert", alert);
        payload.put("aps", aps);
        return payload;
    }
}
