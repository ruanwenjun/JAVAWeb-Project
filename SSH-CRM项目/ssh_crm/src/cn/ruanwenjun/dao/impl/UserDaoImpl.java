package cn.ruanwenjun.dao.impl;




import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.ruanwenjun.dao.UserDao;
import cn.ruanwenjun.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User>  implements UserDao {

	public User findByUserCode(String user_code) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code", user_code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);
		if(list!=null&&list.size()>0) {
			return list.get(0);
		}else {
			return null;			
		}
	}

	
}
