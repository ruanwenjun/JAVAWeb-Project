package cn.ruanwenjun.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ruanwenjun.dao.IUserDao;
import cn.ruanwenjun.dao.base.BaseDaoImpl;
import cn.ruanwenjun.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	public User findUserByUsernameAndPassword(String username, String userpassword) {
		String hql ="FROM User where username=? AND password =?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username,userpassword);
		if(list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	//通过用户名
	public User findUserByUsernameAndPassword(String username) {
		String hql ="FROM User where username=?";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
		if(list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}



	

}
