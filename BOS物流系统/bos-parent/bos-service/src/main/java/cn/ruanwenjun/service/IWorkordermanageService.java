package cn.ruanwenjun.service;

import cn.ruanwenjun.domain.Workordermanage;
import cn.ruanwenjun.utils.PageBean;

public interface IWorkordermanageService {
	/**
	 * 新增工单
	 * @param model
	 */
	public void add(Workordermanage model);

	public void pageQuery(PageBean pageBean);

}
