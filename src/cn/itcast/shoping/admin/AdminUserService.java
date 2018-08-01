package cn.itcast.shoping.admin;

public class AdminUserService {
	//×¢ÈëadminUserDao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}

}
