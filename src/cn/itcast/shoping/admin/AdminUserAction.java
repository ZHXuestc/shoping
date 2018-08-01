package cn.itcast.shoping.admin;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	//ע��adminUserService
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	//ģ������
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	
	/**
	 * ��̨��ɵ�¼
	 */
	public String login(){
		AdminUser _adminUser = adminUserService.login(adminUser);
		//��_adminUser���뵽session���У��ڵ�¼�ɹ�ʱ������ʾ�û�
		ServletActionContext.getRequest().getSession().setAttribute("_adminUser", _adminUser);
		if(_adminUser == null){
			this.addActionMessage("�û������������");
			return "loginFail";
		}
		return "loginSuccess";
	}

}
