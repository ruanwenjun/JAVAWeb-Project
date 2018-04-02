package cn.ruanwenjun.web;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.UserService;



public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User u = new User();
	private UserService us;
	
	public String login() {	
		try {
			User user = us.login(u);
			ActionContext.getContext().getSession().put("user", user);
			//登陆成功
			return SUCCESS;
		} catch (Exception e) {
			//登陆失败
			System.out.println(e.getMessage());
			ActionContext.getContext().put("error",e.getMessage());
			return "loginError";
		}
		
	}
	
	public String regist() {
		
		try {
			us.regist(u);
			//不抛出异常即注册成功，重定向到登陆页面
			return "toLogin";
		} catch (Exception e) {
			//抛出异常表示用户名已经存在，将异常信息添加，转发到注册页面
			ActionContext.getContext().put("registError", e.getMessage());
			return "registError";
		}
	}
	public String quit() {
		//清除session域中的user
		ActionContext.getContext().getSession().put("user", null);
		//重定向到首页
		return "toIndex";
	}
	
	
	
	
	public void setUs(UserService us) {
		this.us = us;
	}

	public User getModel() {
		return u;
	}
}
