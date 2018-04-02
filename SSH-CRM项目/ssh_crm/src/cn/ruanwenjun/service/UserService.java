package cn.ruanwenjun.service;

import cn.ruanwenjun.domain.User;

public interface UserService {
	
	/**
	 * 用户登陆
	 * @param u
	 * @return
	 */
	public User login(User u);
	/**
	 * 用户注册
	 * @param u
	 */
	void regist(User u);
}
