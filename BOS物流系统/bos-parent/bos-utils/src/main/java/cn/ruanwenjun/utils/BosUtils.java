package cn.ruanwenjun.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.ruanwenjun.domain.User;

/**
 * BOS项目工具类
 * @author RUANWENJUN
 *
 */
public class BosUtils {
	
	/**
	 * 获得session
	 * @return
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	/***
	 * 获得当前登陆的用户
	 * @return
	 */
	public static User getCurrentLoginUser() {
		return (User) getSession().getAttribute("loginUser");
	}
	
	
}
