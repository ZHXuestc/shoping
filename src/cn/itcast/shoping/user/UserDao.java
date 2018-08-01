package cn.itcast.shoping.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	public void regist(User user) {
		this.getHibernateTemplate().save(user);
	}

	//�޸�user��
	public void update(User userExit) {
		this.getHibernateTemplate().update(userExit);
	}

	//ͨ����֤���ѯ�û�
	public User findByCode(String code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where code=?", code);
		return list.get(0);
	}

	//�����û����������״̬��ѯ�û�
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
