package cn.ruanwenjun.service;


import cn.ruanwenjun.domain.Decidedzone;
import cn.ruanwenjun.utils.PageBean;

public interface IDecidedzoneService {
	/**
	 * 保存定区，并关联分区
	 * @param model  需要保存的定区
	 * @param subareaId  需要关联的分区ID
	 */
	public void save(Decidedzone model, String[] subareaId);
	
	/**
	 * 分页查询定区
	 * @param pageBean
	 * @return 
	 */
	public void pageQuery(PageBean pageBean);

}
