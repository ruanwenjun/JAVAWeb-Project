package cn.ruanwenjun.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ruanwenjun.dao.ISubareaDao;
import cn.ruanwenjun.dao.base.BaseDaoImpl;
import cn.ruanwenjun.domain.Subarea;

@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {
	//查询各个定区的分区数目
	public List<Object> findSubareaCountInRegion() {
		String hql = "select r.province ,Count(*) from Subarea s inner join s.region r "
						+ " group by r.province";
		List<Object> list = (List<Object>) this.getHibernateTemplate().find(hql);
		return list;
	}

	
}
