package cn.ruanwenjun.service;

import java.util.List;

import cn.ruanwenjun.domain.Region;
import cn.ruanwenjun.utils.PageBean;

public interface IRegionService {
	/**
	 * 一键保存区域
	 * @param regionList
	 */
	public void saveBatch(List<Region> regionList);
	
	/**
	 * 分页查找区域
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	
	/**
	 * 查找所有区域
	 * @param q 模糊查询的参数
	 * @return 
	 */
	public List<Region> finaAllRegion(String q);

}
