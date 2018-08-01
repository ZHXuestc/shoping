package cn.itcast.shoping.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoping.utils.MailUtil;
import cn.itcast.shoping.utils.UUIDUtil;

@Transactional
public class UserService {
	//ע��UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//ҵ���ע�Ṧ��
	public void regist(User user) {
		user.setState(0);//0����δ����   1������״̬
		String code = UUIDUtil.getUUID()+UUIDUtil.getUUID();
		user.setCode(code);
		userDao.regist(user);
		//�����ʼ�
		try {
			MailUtil.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//������֤���ѯ�û�
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//��user������޸�
	public void update(User userExit) {
		userDao.update(userExit);
		
	}
	//��¼ǰ��ѯ�û�
	public User login(User user) {
		return userDao.login(user);
	}
	public User findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}

}
