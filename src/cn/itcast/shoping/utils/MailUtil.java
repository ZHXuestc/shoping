package cn.itcast.shoping.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮箱发送工具类
 * @author Administrator
 *
 */
public class MailUtil {
	
	public static void sendMail(String to,String code) throws AddressException, MessagingException{
		// 1.Session对象.连接(与邮箱服务器连接)
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("18238805580@163.com", "zhx1007348916");
			}
		};
		
		Session session = Session.getInstance(props, auth);
		/*
		 * 2. 创建MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("18238805580@163.com"));//设置发件人
		msg.setRecipients(RecipientType.TO, "1007348916@qq.com");//设置收件人
		
		msg.setSubject("来自SHOPING激活邮件");
		//使用本地主机ip或127.0.0.1都可以
		msg.setContent("<h1>来自SHOPING的官网激活邮件</h1><h3><a href='http://192.168.0.114:8080/shoping/user_active.action?code="+code+"'>http://192.168.1.161/sshshop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		/*
		 * 3. 发
		 */
		Transport.send(msg);
		
	}

}
