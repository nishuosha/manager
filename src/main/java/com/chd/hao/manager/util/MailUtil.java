package com.chd.hao.manager.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by zhanghao68 on 2018/5/9
 */
public class MailUtil {


    private static JavaMailSender createSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.163.com");
        sender.setPort(25);
        sender.setUsername("15991353376@163.com");
        sender.setPassword("zhanghao19964915");
        sender.setDefaultEncoding("UTF-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }


    public static void sendMail(String to, String subject, String content) throws MessagingException {
        JavaMailSender mailSender = createSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        try {
            helper.setFrom("15991353376@163.com","车位预定系统");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        helper.setTo(to);
        message.setSubject(subject);
        message.setText(content, "UTF-8");
        mailSender.send(message);
    }

}
