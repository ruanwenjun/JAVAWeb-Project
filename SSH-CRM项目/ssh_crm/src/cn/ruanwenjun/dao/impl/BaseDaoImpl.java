package cn.ruanwenjun.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.ruanwenjun.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	
	public BaseDaoImpl() {
		
		//获得运行时的带泛型的父类
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得泛型类型
		 Type[] type = pt.getActualTypeArguments();
		 clazz = (Class) type[0];
		
	}
	
	
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	public void delete(T t) {
		getHibernateTemplate().delete(t);
		
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
		
	}

	public Integer findTotalCount(DetachedCriteria dc) {
		
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
		if(list!=null&&list.size()>0) {
			return list.get(0).intValue();
		}else {
			return 0;			
		}
	}

	
	public T findById(Serializable id) {
		
		return (T) getHibernateTemplate().get(clazz, id);
	}

	public List<T> findPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		
		List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
		return list;
		
	}


	

}
