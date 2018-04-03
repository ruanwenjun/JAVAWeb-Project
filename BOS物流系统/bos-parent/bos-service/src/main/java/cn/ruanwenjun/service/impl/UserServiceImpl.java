package cn.ruanwenjun.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.IFunctionDao;
import cn.ruanwenjun.dao.IUserDao;
import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.domain.Role;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.IUserService;
import cn.ruanwenjun.utils.BosUtils;
import cn.ruanwenjun.utils.MD5Utils;
import cn.ruanwenjun.utils.PageBean;

@Service
@Transactional()
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IFunctionDao functionDao;
	//用户登陆
	@Transactional(readOnly=true)
	public User login(User user) {
		//使用MD5加密密码
		String userpassword = MD5Utils.md5(user.getPassword());			
		//从数据库中根据用户名和密码查找用户
		User u = userDao.findUserByUsernameAndPassword(user.getUsername(),userpassword);
		if(u != null) {
			return u;
		}else {
			return null;
		}
	}
	
	//修改密码
	public void editPassword(String id, String userpassword) {
		userpassword = MD5Utils.md5(userpassword);
		userDao.executeUptate("user.editPassword",userpassword,id);
	}
	
	//新增用户
	public void add(User model, String[] roleId) {
		//判断用户名是否存在
		String username = model.getUsername();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("username", username));
		List<User> user = userDao.findByCriteria(dc);
		if(user != null && user.size()>0) {
			throw new RuntimeException("用户已存在");
		}
		
		String userpassword = MD5Utils.md5(model.getPassword());
		model.setPassword(userpassword);
		userDao.add(model);
		if(roleId != null && roleId.length > 0) {
			for (String id : roleId) {
				Role role = new Role(id);
				model.getRoles().add(role);
			}
		}
		
	}
	
	//分页查找
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}
	
	//根据当前登陆的用户动态加载权限菜单
	public List<Function> findMenu() {
		User user = BosUtils.getCurrentLoginUser();
		List<Function>list = null;
		if(user != null) {
			if(user.getUsername().equals("admin")) {
				list = functionDao.findAllMenu();
			}else {
				//根据用户ID查找权限
				list = functionDao.findFunctionByUserID(user.getId());
			}
		}
		return list;
	}

}
