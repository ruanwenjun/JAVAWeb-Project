package cn.ruanwenjun.dao;

import java.util.List;

import cn.ruanwenjun.dao.base.IBaseDao;
import cn.ruanwenjun.domain.Region;

public interface IRegionDao extends IBaseDao<Region> {
	
	/**
	 * 根据Q进行模糊查询
	 * @param q
	 * @return
	 */
	List<Region> findAllByQ(String q);
	
}
