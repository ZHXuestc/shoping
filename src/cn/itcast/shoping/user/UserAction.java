package cn.itcast.shoping.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动
	private User user = new User();
	public User getModel() {
		return user;
	}
	//从页面上获取验证码
	private String checkCode;
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	//注入userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//先去注册页面
	public String registPage(){
		
		return "registPageSuccess";
	}
	//前台注册功能
	@InputConfig(resultName = "registInput")
	public String regist(){
	   String verifyCode = (String) ServletActionContext.getRequest().getSession().getAttribute("verifycode");
	   if(!(checkCode.equalsIgnoreCase(verifyCode) || verifyCode == null)){
	    	this.addActionError("验证码错误");
	    	return "registInput";
	    }else{
	    	userService.regist(user);
			this.addActionMessage("请到邮箱进行激活");
			return "registSuccess";
	    }
		
	}
	//前台：激活用户
	public String active(){
		User userExit = userService.findByCode(user.getCode());
		if(userExit != null){
			userExit.setState(1);
			userService.update(userExit);
			this.addActionMessage("用户已激活，请去登录");
			return "activeSuccess";
		}else{
			this.addActionMessage("用户激活失败，请重新注册");
			return "activeFail";
		}
	}
	//前台:先跳转到登录页面
	public String loginPage(){
		
		return "loginPageSuccess";
		
	}
	
	//前台：登录功能
	@InputConfig(resultName="loginInput")
	public String login(){
		String verifyCode = (String) ServletActionContext.getRequest().getSession().getAttribute("verifycode");
		if(!checkCode.equalsIgnoreCase(verifyCode) || verifyCode == null){
			this.addActionError("验证码错误！");
			return "loginInput";
		}else{
			User userExit = userService.login(user);
			if(userExit != null){
				ServletActionContext.getRequest().getSession().setAttribute("userExit", userExit);
				return "loginSuccess";
			}else{
				this.addActionError("用户名或密码错误或用户未激活");
				return "loginInput";
			}
		}
		
	}
	
	//使用jQuery异步处理的方式自动查询用户名是否已被注册
	public String checkUserName() throws IOException{
		User userExit = userService.findByUsername(user.getUsername());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(userExit == null){
			response.getWriter().print("<font color='green'>用户名可以使用</font>");
		}else{
			response.getWriter().print("<font color='red'>用户名已被注册</font>");
		}
		return NONE;
		
	}
	
	//退出功能
	public String quit(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		
		return "quitSuccess";
	}
	
}
