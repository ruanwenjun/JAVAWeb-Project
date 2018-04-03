package cn.ruanwenjun.service;

import java.util.List;

import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.utils.PageBean;

public interface IUserService {
	
	public User login(User user);
	
	public void editPassword(String id, String password);

	public void add(User model, String[] roleId);

	public void pageQuery(PageBean pageBean);

	public List<Function> findMenu();

}
