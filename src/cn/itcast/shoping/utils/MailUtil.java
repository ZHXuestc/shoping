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
 * ���䷢�͹�����
 * @author Administrator
 *
 */
public class MailUtil {
	
	public static void sendMail(String to,String code) throws AddressException, MessagingException{
		// 1.Session����.����(���������������)
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
		 * 2. ����MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("18238805580@163.com"));//���÷�����
		msg.setRecipients(RecipientType.TO, "1007348916@qq.com");//�����ռ���
		
		msg.setSubject("����SHOPING�����ʼ�");
		//ʹ�ñ�������ip��127.0.0.1������
		msg.setContent("<h1>����SHOPING�Ĺ��������ʼ�</h1><h3><a href='http://192.168.0.114:8080/shoping/user_active.action?code="+code+"'>http://192.168.1.161/sshshop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		/*
		 * 3. ��
		 */
		Transport.send(msg);
		
	}

}
