package cn.ruanwenjun.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.ruanwenjun.dao.BaseDictDao;
import cn.ruanwenjun.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

	public List<BaseDict> find(String dict_type_code) {
		DetachedCriteria dc =DetachedCriteria.forClass(BaseDict.class);
		dc.add(Restrictions.eq("dict_type_code", dict_type_code));
		
		return (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
	}

	
}
