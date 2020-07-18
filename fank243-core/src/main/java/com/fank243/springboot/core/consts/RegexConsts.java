package com.fank243.springboot.core.consts;

/**
 * 正则表达式常量池
 * 
 * @author FanWeiJie
 * @date 2020-04-17 19:50:00
 */
public class RegexConsts {

    /** 正则：姓名 **/
    public static final String REGEXP_REALNAME = "[\\u4E00-\\u9FA5]{2,4}";

    /** 正则：5个及以上中文 **/
    public static final String REGEXP_MORE5 = "[\\u4E00-\\u9FA5]{5,}";

    /** 正则：上传图片相对路径 **/
    public static final String REGEXP_IMAGE =
        "/images/(((dev|test)/|)*20[2-9][0-9]((0[1-9])|(1[0-2]))/\\d{10,13}|([a-zA-Z0-9]+))\\.(jpg|jpeg|png|gif|bmp)+";

    /** 正则：手机号码 **/
    public static final String REGEXP_MOBILE = "1[3-9]\\d{9}";

    /** 正则：常见爬虫攻击UA **/
    public static final String REGEXP_UA =
        "FeedDemon|JikeSpider|Indy Library|Alexa Toolbar|AskTbFXTV|AhrefsBot|CrawlDaddy|CoolpadWebkit|Java|Feedly|UniversalFeedParser|ApacheBench|Microsoft URL Control|Swiftbot|ZmEu|oBot|jaunty|Python-urllib|lightDeckReports Bot|YYSpider|DigExt|YisouSpider|HttpClient|MJ12bot|heritrix|EasouSpider|LinkpadBot|Ezooms|";

}
