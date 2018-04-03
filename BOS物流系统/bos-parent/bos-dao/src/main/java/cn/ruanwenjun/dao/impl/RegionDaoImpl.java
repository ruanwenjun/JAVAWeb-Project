package cn.ruanwenjun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ruanwenjun.dao.IRegionDao;
import cn.ruanwenjun.dao.base.BaseDaoImpl;
import cn.ruanwenjun.domain.Region;
import cn.ruanwenjun.utils.PageBean;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	public List<Region> findAllByQ(String q) {
		String hql = "FROM Region r WHERE province LIKE ? OR city LIKE ? OR district LIKE ? OR shortcode LIKE ? OR citycode LIKE ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%");
		return list;
	}

	

}
