package com.fank243.study.common.core.constants;

/**
 * 正则表达式常量池
 * 
 * @author FanWeiJie
 * @since 2022-09-23 16:22:43
 */
public class RegexConstants {

    /** 手机号码 **/
    public static final String MOBILE = "^1[3-9]\\d{9}$";

    /** 六位数字 **/
    public static final String NUMBER_SIX = "^\\d{6}$";

    /** 换行符、空格符 **/
    public static final String BLANK_CHAR = "\\s*|\t|\r|\n";

    /** USER_AGENT **/
    public static final String USER_AGENT =
        """
            FeedDemon|JikeSpider|Indy Library|Alexa Toolbar|AskTbFXTV|AhrefsBot|CrawlDaddy|CoolpadWebkit|Java|Feedly|UniversalFeedParser|ApacheBench|Microsoft URL Control|Swiftbot|ZmEu|oBot|jaunty|Python-urllib|lightDeckReports Bot|YYSpider|DigExt|YisouSpider|HttpClient|MJ12bot|heritrix|EasouSpider|LinkpadBot|Ezooms|^$
             """;
}
