package cn.ruanwenjun.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ruanwenjun.domain.Staff;
import cn.ruanwenjun.service.IStaffService;
import cn.ruanwenjun.utils.PageBean;
import cn.ruanwenjun.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 取派员管理
 * @author RUANWENJUN
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private IStaffService staffService;
	
	private String ids;     //需要删除的取派员的ID
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	/**
	 * 添加取派员
	 * @return
	 */
	public String add() {
		staffService.save(model);
		return SUCCESS;
	}
	
	/**
	 *批量删除取派员 
	 * @return
	 */
	@RequiresPermissions("staff-delete")
	public String deleteBatch() {
		System.out.println(ids);
		staffService.deleteBatch(ids);
		
		return SUCCESS;
	}
	/**
	 * 分页查找
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {
		
		//调用service层方法进行分页查询
		staffService.pageQuery(pageBean);
		this.Object2JsonString(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","decidedzones"});
		return NONE;
	}
	
	/**
	 *修改取派员 
	 * @return
	 */
	public String edit() {
		//根据ID从数据库中查询到staff
		Staff staff = staffService.findById(model.getId());
		//用前台的数据覆盖staff中的数据
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		//更新
		staffService.update(staff);
		return SUCCESS;
	}
	
	/**
	 * 查找到所有未被删除的取派员然后将ID和name封装成JSON字符串传给前台
	 * @return
	 */
	public String listByAjax() {
		//查找所有未被删除的staff
		List<Staff> staffList = staffService.findNotDeleteStaffList();
		//将staffList转换成JSON字符串并写给页面
		this.Object2JsonString(staffList, new String[]{ "telephone","haspda","deltag","standard","decidedzones","station"});
		return NONE;
	}
	
	
}
