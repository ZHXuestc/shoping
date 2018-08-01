package cn.itcast.shoping.admin;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	//注入adminUserService
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//模型驱动
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	
	/**
	 * 后台完成登录
	 */
	public String login(){
		AdminUser _adminUser = adminUserService.login(adminUser);
		//把_adminUser放入到session域当中，在登录成功时可以显示用户
		ServletActionContext.getRequest().getSession().setAttribute("_adminUser", _adminUser);
		if(_adminUser == null){
			this.addActionMessage("用户名或密码错误");
			return "loginFail";
		}
		return "loginSuccess";
	}

}
