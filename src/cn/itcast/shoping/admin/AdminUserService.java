package cn.itcast.shoping.admin;

public class AdminUserService {
	//ע��adminUserDao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}

}
