package com.tqmars.cardrecycle.infrastructure.email;

import com.tqmars.cardrecycle.infrastructure.StringTools.PropertiesFileTool;
import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import org.apache.commons.mail.*;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by jjh on 3/7/17.
 */
public class TqEmail {
    @Async
    public static void sendWithHtml(String subject, String htmlMsg) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(PropertiesFileTool.readByKey("smtp"));
        email.setSmtpPort(Integer.parseInt(PropertiesFileTool.readByKey("smtpPort")));
        email.setAuthenticator(new DefaultAuthenticator(PropertiesFileTool.readByKey("emailAccount"), PropertiesFileTool.readByKey("emailPwd")));
        email.setSSLOnConnect(false);
        email.setCharset("utf-8");
        try {
            email.setFrom(PropertiesFileTool.readByKey("emailFrom"));
            email.setSubject(subject);
            email.setHtmlMsg(htmlMsg);
            email.addTo(PropertiesFileTool.readByKey("emailTo"));
            email.send();
        } catch (EmailException e) {
            LoggerFactory.getLogger().error("email component err:", e);
        }

    }

    @Async
    public static void send(String subject, String msg) {
        Email email = new SimpleEmail();
        email.setHostName(PropertiesFileTool.readByKey("smtp"));
        email.setSmtpPort(Integer.parseInt(PropertiesFileTool.readByKey("smtpPort")));
        email.setAuthenticator(new DefaultAuthenticator(PropertiesFileTool.readByKey("emailAccount"), PropertiesFileTool.readByKey("emailPwd")));
        email.setSSLOnConnect(false);
        email.setCharset("utf-8");
        try {
            email.setFrom(PropertiesFileTool.readByKey("emailFrom"));
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(PropertiesFileTool.readByKey("emailTo"));
            email.send();
        } catch (EmailException e) {
            LoggerFactory.getLogger().error("email component err:", e);
        }
    }
}
