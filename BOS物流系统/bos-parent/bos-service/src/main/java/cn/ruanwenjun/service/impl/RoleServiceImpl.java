package cn.ruanwenjun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.IRoleDao;
import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.domain.Role;
import cn.ruanwenjun.service.IRoleService;
import cn.ruanwenjun.utils.PageBean;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月1日 上午11:00:47
*/
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private IRoleDao roleDao;
	
	//添加角色，并赋予权限
	public void add(String functionIds, Role model) {
		roleDao.add(model);
		//解析权限id
		String[] split = functionIds.split(",");
		for (String string : split) {
			Function f = new Function(string);
			model.getFunctions().add(f);
		}
	}

	//分页查找角色
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}
	
	//查找所有角色
	public List<Role> findAll() {
		List<Role> list = roleDao.findAll();
		return list;
	}
}
