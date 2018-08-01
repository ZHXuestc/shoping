package cn.itcast.shoping.admin;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdminUserDao extends HibernateDaoSupport{

	public AdminUser login(AdminUser adminUser) {
		List<AdminUser> list = this.getHibernateTemplate().find("from AdminUser where adminUsername=? and adminPassword=?", adminUser.getAdminUsername(),adminUser.getAdminPassword());
		if(list.size() >0){
			return list.get(0);
		}
		return null;
	}
	

}
