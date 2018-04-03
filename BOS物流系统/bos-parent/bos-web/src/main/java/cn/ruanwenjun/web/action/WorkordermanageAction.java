package cn.ruanwenjun.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ruanwenjun.domain.Workordermanage;
import cn.ruanwenjun.service.IWorkordermanageService;
import cn.ruanwenjun.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	
	@Autowired
	private IWorkordermanageService workordermanageService;
	
	/**
	 * 新增工单
	 * @return
	 */
	public String add() {
		workordermanageService.add(model);
		return NONE;
	}
	
	public String pageQuery() {
		workordermanageService.pageQuery(pageBean);
		this.Object2JsonString(pageBean, new String[] {"currentPage","pageSize","detachedCriteria"});
		return NONE;
	}
}
