package cn.ruanwenjun.dao;

import cn.ruanwenjun.domain.User;

public interface UserDao extends BaseDao<User> {
	/**
	 * 根据用户名查找用户
	 * @param user_code
	 * @return 
	 */
	User findByUserCode(String user_code);

	

}
