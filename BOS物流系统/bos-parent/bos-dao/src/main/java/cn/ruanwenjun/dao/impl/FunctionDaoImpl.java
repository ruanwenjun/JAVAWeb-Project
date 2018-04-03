package cn.ruanwenjun.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ruanwenjun.dao.IFunctionDao;
import cn.ruanwenjun.dao.base.BaseDaoImpl;
import cn.ruanwenjun.domain.Function;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年3月30日 下午3:37:48
*/
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
	//查找所有一级权限
	public List<Function> findAllFirstFunction(){
		String hql = "FROM Function f WHERE f.parentFunction IS NULL";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}
	
	//查找所有菜单项
	public List<Function> findAllMenu() {
		String hql = "SELECT DISTINCT f FROM Function f WHERE f.generatemenu = '1' ORDER BY zindex ASC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}
	//根据用户ID查找权限菜单项
	public List<Function> findFunctionByUserID(String id) {
		String hql = "SELECT DISTINCT f FROM Function f INNER JOIN f.roles r INNER JOIN r.users u WHERE u.id =? AND f.generatemenu = '1' ORDER BY zindex ASC";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
		return list;
	}
	//根据用户ID查找权限
	public List<Function> findAuthorizationByUserID(String id) {
		String hql = "SELECT DISTINCT f FROM Function f INNER JOIN f.roles r INNER JOIN r.users u WHERE u.id =? ";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
		return null;
	}

}
