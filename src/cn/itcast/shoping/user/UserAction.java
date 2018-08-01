package cn.itcast.shoping.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//ģ������
	private User user = new User();
	public User getModel() {
		return user;
	}
	//��ҳ���ϻ�ȡ��֤��
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	//ע��userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//��ȥע��ҳ��
	public String registPage(){
		
		return "registPageSuccess";
	}
	//ǰ̨ע�Ṧ��
	@InputConfig(resultName = "registInput")
	public String regist(){
	   String verifyCode = (String) ServletActionContext.getRequest().getSession().getAttribute("verifycode");
	   if(!(checkCode.equalsIgnoreCase(verifyCode) || verifyCode == null)){
	    	this.addActionError("��֤�����");
	    	return "registInput";
	    }else{
	    	userService.regist(user);
			this.addActionMessage("�뵽������м���");
			return "registSuccess";
	    }
		
	}
	//ǰ̨�������û�
	public String active(){
		User userExit = userService.findByCode(user.getCode());
		if(userExit != null){
			userExit.setState(1);
			userService.update(userExit);
			this.addActionMessage("�û��Ѽ����ȥ��¼");
			return "activeSuccess";
		}else{
			this.addActionMessage("�û�����ʧ�ܣ�������ע��");
			return "activeFail";
		}
	}
	//ǰ̨:����ת����¼ҳ��
	public String loginPage(){
		
		return "loginPageSuccess";
		
	}
	
	//ǰ̨����¼����
	@InputConfig(resultName="loginInput")
	public String login(){
		String verifyCode = (String) ServletActionContext.getRequest().getSession().getAttribute("verifycode");
		if(!checkCode.equalsIgnoreCase(verifyCode) || verifyCode == null){
			this.addActionError("��֤�����");
			return "loginInput";
		}else{
			User userExit = userService.login(user);
			if(userExit != null){
				ServletActionContext.getRequest().getSession().setAttribute("userExit", userExit);
				return "loginSuccess";
			}else{
				this.addActionError("�û��������������û�δ����");
				return "loginInput";
			}
		}
		
	}
	
	//ʹ��jQuery�첽����ķ�ʽ�Զ���ѯ�û����Ƿ��ѱ�ע��
	public String checkUserName() throws IOException{
		User userExit = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(userExit == null){
			response.getWriter().print("<font color='green'>�û�������ʹ��</font>");
		}else{
			response.getWriter().print("<font color='red'>�û����ѱ�ע��</font>");
		}
		return NONE;
		
	}
	
	//�˳�����
	public String quit(){
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		
		return "quitSuccess";
	}
	
}
