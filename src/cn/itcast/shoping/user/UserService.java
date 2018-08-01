package cn.itcast.shoping.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoping.utils.MailUtil;
import cn.itcast.shoping.utils.UUIDUtil;

@Transactional
public class UserService {
	//注入UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//业务层注册功能
	public void regist(User user) {
		user.setState(0);//0代表未激活   1代表激活状态
		String code = UUIDUtil.getUUID()+UUIDUtil.getUUID();
		user.setCode(code);
		userDao.regist(user);
		//发送邮件
		try {
			MailUtil.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//根据验证码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	//对user表进行修改
	public void update(User userExit) {
		userDao.update(userExit);
		
	}
	//登录前查询用户
	public User login(User user) {
		return userDao.login(user);
	}
	public User findByUsername(String username) {
		
		return userDao.findByUsername(username);
	}

}
