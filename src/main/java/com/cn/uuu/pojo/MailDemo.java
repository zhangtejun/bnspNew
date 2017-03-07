package com.cn.uuu.pojo;

/**
 * Created by zhangchi on 16/11/21.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;


public class MailDemo {


    static ApplicationContext actx = new ClassPathXmlApplicationContext(
            "spring-mvc.xml");
    static MailSender sender = (MailSender) actx.getBean("mailSender");
    static SimpleMailMessage mailMessage = (SimpleMailMessage) actx.getBean("mailMessage");
    public static void main(String args[]) throws MessagingException {
        mailMessage.setSubject("你好");
        mailMessage.setText("这个是一个通过Spring框架来发送邮件的小程序");
        mailMessage.setTo("zhangchijob@126.com.com");
        sender.send(mailMessage);
    }
}