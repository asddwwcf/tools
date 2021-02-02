package com.mine.tool.mail.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class EmailUtil {

    @Value("${email.host}")
    private String host;
    @Value("${email.port}")
    private String port;
    @Value("${email.username}")
    private String username;
    @Value("${email.passwd}")
    private String passwd;

    /**
     * 发送邮件
     * @param fromEmail 发件人Email
     * @param toEmail 收件人Email
     * @param subject 主题
     * @param msg 内容
     * @return
     *      true: send successfully
     *      false: send fail
     */
    public boolean send(String fromEmail, String toEmail, String subject, String msg) {
        try {
            Email email = new SimpleEmail();
            // 设置邮件服务器信息
            email.setHostName(host);
            email.setSSLOnConnect(true);
            email.setSslSmtpPort(port);
            email.setAuthentication(username, passwd);

            // 设置额外信息
            email.setCharset("utf-8");
            // 开启debug调试输出信息
            email.setDebug(true);

            // 设置邮件的发件人、主题、内容、收件人
            email.setFrom(fromEmail);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(toEmail);

            // 发送邮件
            String msgId = email.send();
            log.info("邮件已发送：{}", msgId);
            return true;
        } catch (EmailException e) {
            log.info("邮件发送失败：{}", e.getMessage());
            return false;
        }
    }
}