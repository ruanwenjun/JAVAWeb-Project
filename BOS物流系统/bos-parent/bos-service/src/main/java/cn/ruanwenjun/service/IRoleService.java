package cn.ruanwenjun.service;


import java.util.List;

import cn.ruanwenjun.domain.Role;
import cn.ruanwenjun.utils.PageBean;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月1日 上午10:59:59
*/
public interface IRoleService {

	public void add(String functionIds, Role model);

	public void pageQuery(PageBean pageBean);

	public List<Role> findAll();

}
