package cn.itcast.shoping.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	public void regist(User user) {
		this.getHibernateTemplate().save(user);
	}

	//修改user表
	public void update(User userExit) {
		this.getHibernateTemplate().update(userExit);
	}

	//通过验证码查询用户
	public User findByCode(String code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where code=?", code);
		return list.get(0);
	}

	//根据用户名，密码和状态查询用户
	public User login(User user) {
		List<User> list = this.getHibernateTemplate().find("from User where username=? and password=? and state=?",user.getUsername(),user.getPassword(),1);
		if(list.size() != 0){
			return list.get(0);
		}
		return null;
	}

	public User findByUsername(String username) {
		List<User> list = this.getHibernateTemplate().find("from User where username=?", username);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
