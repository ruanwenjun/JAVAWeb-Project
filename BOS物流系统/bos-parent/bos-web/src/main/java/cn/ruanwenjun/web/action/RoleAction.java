package cn.ruanwenjun.web.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.ruanwenjun.domain.Role;
import cn.ruanwenjun.service.IRoleService;
import cn.ruanwenjun.web.action.base.BaseAction;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月1日 上午9:52:40
*/
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	@Autowired
	private IRoleService roleService;
	private String functionIds;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}
	//添加角色
	public String add() {
		roleService.add(functionIds,model);
		return SUCCESS;
	}
	
	//分页查找所有角色
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		this.Object2JsonString(pageBean, new String[] {"currentPage","pageSize","detachedCriteria",
										"functions","users","code"});
		return NONE;
	}
	
	//查找所有角色
	public String ajaxList() {
		List<Role> list = roleService.findAll();
		this.Object2JsonString(list, new String[] {"code","description","functions","users"});
		return NONE;
		
	}
	
	
	
	
}
