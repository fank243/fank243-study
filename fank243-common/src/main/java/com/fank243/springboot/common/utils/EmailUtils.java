package com.fank243.springboot.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.mail.*;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * apache邮件工具类
 *
 * @author FanWeiJie
 * @date 2016年1月25日
 */
@Setter
@Getter
public class EmailUtils {

    /** 邮箱主机地址 **/
    private String hostName;

    /** 发送端口 **/
    private int port;

    /** 发送人昵称 **/
    private String nickName;

    /** 邮箱账号 **/
    private String userName;

    /** 邮箱密码 **/
    private String password;

    public EmailUtils() {}

    /**
     * 邮箱配置
     *
     * @param hostName 主机地址
     * @param port Y端口号
     * @param nickName N 发件人昵称
     * @param userName Y邮箱账号
     * @param password Y 密码
     */
    public EmailUtils(String hostName, int port, String nickName, String userName, String password) {
        super();
        this.hostName = hostName;
        this.port = port;
        this.nickName = nickName;
        this.userName = userName;
        this.password = password;
    }

    /**
     * 发送邮件：简单邮件|纯文本
     *
     * @param recipient 接收人
     * @param nickName 接收人别名
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param ssl 是否使用SSL进行连接
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendSimpleEmail(String recipient, String nickName, String subject, String msg, boolean ssl)
        throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.setFrom(getUserName(), getNickName());
        email.addTo(recipient, nickName, StandardCharsets.UTF_8.name());
        email.setSubject(subject);
        email.setMsg(msg);
        email.setSSLOnConnect(ssl);
        return email.send();
    }

    /**
     * 发送邮件：简单邮件|群发|纯文本
     *
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param ssl 是否使用SSL进行连接
     * @param recipients 接收人列表
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendSimpleEmail(String subject, String msg, boolean ssl, String... recipients) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.setFrom(getUserName(), getNickName());
        email.addTo(recipients);
        email.setSubject(subject);
        email.setMsg(msg);
        email.setSSLOnConnect(ssl);
        return email.send();
    }

    /**
     * 发送邮件：带附件(本地文件)的简单邮件，纯文本
     *
     * @param recipient 接收人
     * @param nickName 接收人别名
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param name 附件名称
     * @param desc 附件描述
     * @param path 附件路径
     * @param ssl 是否使用SSL进行连接
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendAttachmentEmail(String recipient, String nickName, String subject, String msg, String name,
        String desc, String path, boolean ssl) throws EmailException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(desc);
        attachment.setName(name);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.addTo(recipient, nickName, StandardCharsets.UTF_8.name());

        email.setFrom(getUserName(), getNickName());
        email.setSubject(subject);
        email.setMsg(msg);
        email.setSSLOnConnect(ssl);

        // add the attachment
        email.attach(attachment);

        // send the email
        return email.send();
    }

    /**
     * 发送邮件：带附件(本地文件)的简单邮件|群发|纯文本
     *
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param name 附件名称
     * @param desc 附件描述
     * @param path 附件路径
     * @param ssl 是否使用SSL进行连接 param recipients 接收人列表
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendAttachmentEmail(String subject, String msg, String name, String desc, String path, boolean ssl,
        String... recipients) throws EmailException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(path);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(desc);
        attachment.setName(name);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.addTo(recipients);

        email.setFrom(getUserName(), getNickName());
        email.setSubject(subject);
        email.setMsg(msg);
        email.setSSLOnConnect(ssl);

        // add the attachment
        email.attach(attachment);

        // send the email
        return email.send();
    }

    /**
     * 发送邮件：带附件(网络文件)的简单邮件，纯文本
     *
     * @param recipient 接收人
     * @param nickName 接收人别名
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param name 附件名称
     * @param desc 附件描述
     * @param url 网络文件地址
     * @param ssl 是否使用SSL进行连接
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendAttachmentEmail(String recipient, String nickName, String subject, String msg, String name,
        String desc, URL url, boolean ssl) throws EmailException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setURL(url);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(desc);
        attachment.setName(name);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.addTo(recipient, nickName, StandardCharsets.UTF_8.name());
        email.setFrom(getUserName(), getNickName());
        email.setSubject(subject);
        email.setMsg(msg);
        email.setSSLOnConnect(ssl);

        // add the attachment
        email.attach(attachment);

        // send the email
        return email.send();
    }

    /**
     * 发送邮件：带附件(网络文件)的简单邮件 | 群发 | 纯文本
     *
     * @param subject 邮件主题
     * @param msg 邮件内容
     * @param name 附件名称
     * @param desc 附件描述
     * @param url 网络文件地址
     * @param ssl 是否使用SSL进行连接
     * @param recipients 接收人列表
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendAttachmentEmail(String subject, String msg, String name, String desc, URL url, boolean ssl,
        String... recipients) throws EmailException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
        attachment.setURL(url);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription(desc);
        attachment.setName(name);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.addTo(recipients);
        email.setFrom(getUserName(), getNickName());
        email.setSubject(subject);
        email.setMsg(msg);
        email.setSSLOnConnect(ssl);

        // add the attachment
        email.attach(attachment);

        // send the email
        return email.send();
    }

    /**
     * 发送邮件：HTML邮件
     *
     * @param recipient 接收人
     * @param nickName 接收人别名
     * @param subject 邮件主题
     * @param html HTML模板
     * @param text 替代消息(不支持接收html邮箱有效)
     * @param ssl 是否使用SSL进行连接
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendHtmlEmail(String recipient, String nickName, String subject, String html, String text,
        boolean ssl) throws EmailException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        email.addTo(recipient, nickName, StandardCharsets.UTF_8.name());
        email.setFrom(getUserName(), getNickName());
        email.setSubject(subject);
        email.setSSLOnConnect(ssl);
        email.setCharset(StandardCharsets.UTF_8.name());
        // set the html message
        email.setHtmlMsg(html);

        // set the alternative message
        email.setTextMsg(text);

        // send the email
        return email.send();
    }

    /**
     * 发送邮件：HTML邮件 | 群发
     *
     * @param emailList 接收人列表
     * @param subject 邮件主题
     * @param html HTML模板
     * @param text 替代消息(不支持接收html邮箱有效)
     * @param ssl 是否使用SSL进行连接
     * @return 发送ID
     * @author FanWeiJie
     * @date 2015年7月18日
     */
    public String sendHtmlEmail(List<String> emailList, String subject, String html, String text, boolean ssl)
        throws EmailException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setHostName(getHostName());
        email.setSmtpPort(getPort());
        email.setAuthenticator(new DefaultAuthenticator(getUserName(), getPassword()));
        for (String recipient : emailList) {
            email.addTo(recipient);
        }
        email.setFrom(getUserName(), getNickName());
        email.setSubject(subject);
        email.setSSLOnConnect(ssl);

        // set the html message
        email.setHtmlMsg(html);

        // set the alternative message
        email.setTextMsg(text);

        // send the email
        return email.send();
    }
}
