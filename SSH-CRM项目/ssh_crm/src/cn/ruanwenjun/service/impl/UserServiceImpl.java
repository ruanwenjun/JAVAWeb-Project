package cn.ruanwenjun.service.impl;


import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.UserDao;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.UserService;
import cn.ruanwenjun.utils.MD5Utils;


@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class UserServiceImpl implements UserService {
	private UserDao dao;
	
	@Override
	public User login(User u) {
		
		User user = dao.findByUserCode(u.getUser_code());
		if(user==null) {
			throw new RuntimeException("用户不存在");
		}else if(!user.getUser_password().equals(MD5Utils.md5(u.getUser_password()))) {
			throw new RuntimeException("密码错误");
		}else {
			return user;
		}
	}
	
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void regist(User u) {
		User user = dao.findByUserCode(u.getUser_code());
		if(user!=null) {
			//用户名已存在
			throw new RuntimeException("该用户名已存在");
		}else {
			//使用MD5对用户密码进行加密
			u.setUser_password(MD5Utils.md5(u.getUser_password()));
			//用户名不存在，进行保存
			dao.save(u);
		}
	}
	
	
	
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

















}
