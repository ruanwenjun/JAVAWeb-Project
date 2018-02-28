package cn.ruanwenjun.service;

import java.sql.SQLException;
import java.util.List;

import cn.ruanwenjun.domain.Product;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.dao.ProductDao;
import cn.ruanwenjun.dao.UserDao;

public class UserService {
	/**
	 * 用户注册业务
	 * @param user
	 * @return
	 */
	public boolean register(User user) {
		UserDao dao = new UserDao();
		Boolean isRegister = false;
		try {
			isRegister = dao.register(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isRegister;
	}
	/**
	 * 用户激活
	 * @param activeCode
	 */
	public int activeUser(String activeCode) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.activeUser(activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
		
	}
	/**
	 * 用户名是否存在
	 * @param username
	 * @return
	 */
	public Boolean isExist(String username) {
		UserDao dao = new UserDao();
		Boolean isExist = false;
		try {
			isExist = dao.isExist(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	public User login(User loginUser) {
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.isRight(loginUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	

}
