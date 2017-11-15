package com.fcsservice.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by YE on 2017/10/27 17:00.
 */
public class EmailUtil implements Runnable{
    String to,subject,msg;

    /**
     *
     * @param to 邮件接收者地址
     * @param subject 邮件标题
     * @param msg 邮件正文
     * @return true发送成功|false发送失败
     */
    public EmailUtil(String to, String subject, String msg) {
        this.to = to;
        this.subject = subject;
        this.msg = msg;
    }

    public void run() {
        Properties props = new Properties();
        // 邮件传输的协议
        props.put("mail.transport.protocol", "smtp");
        // 连接的邮件服务器
        props.put("mail.host", "smtp.163.com");
        // 发送人
        props.put("mail.from", "fcsapp4@163.com");

        msg = "这是一份来自轻纺商圈APP的验证邮件,您的验证码是:"+msg+"\t(请在60秒内验证)";

        // 第一步：创建Session
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        try {
            // 第二步：获取邮件传输对象
            Transport ts = session.getTransport();
            // 连接邮件服务器
            ts.connect("fcsapp4@163.com", "fcsapp4");
            // 第三步: 创建邮件消息体
            MimeMessage message = new MimeMessage(session);
            // 设置邮件的主题
            message.setSubject(subject);
            // 设置邮件的内容
            message.setContent(msg, "text/html;charset=utf-8");
            // 第四步：设置发送昵称
            String nick = "";
            try {
                nick = javax.mail.internet.MimeUtility.encodeText("FCSAPP");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //设置发件人地址
            message.setFrom(new InternetAddress(nick + " <fcsapp4@163.com>"));
            message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(nick + " <fcsapp4@163.com>"));
            message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.saveChanges();
            // 第五步：设置接收人信息
            ts.sendMessage(message,message.getAllRecipients());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
