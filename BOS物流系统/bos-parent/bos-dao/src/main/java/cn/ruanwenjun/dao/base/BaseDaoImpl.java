package cn.ruanwenjun.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.ruanwenjun.utils.PageBean;

/**
 * 通用持久层实现类
 * @author RUANWENJUN
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	//实际的泛型类型
	private Class<T> entityClass;
	
	//注入sessionFactory，根据类型注解
	@Resource
	public  void setMySessionFactory(SessionFactory sessionFactory) {
		//调用父类的方法注入spring工厂中的sessionFactoryBean的sessionFactory
		super.setSessionFactory(sessionFactory);
	}
	//构造器
	public BaseDaoImpl() {
		//获得带泛型的父类
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得实际的泛型类型
		Type[] actualType = superClass.getActualTypeArguments();
		entityClass =  (Class<T>) actualType[0];
	}
	
	public void add(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	public List<T> findAll() {
		String hql = "from "+entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	public void executeUptate(String query, Object... objects) {
		//获得当前的session，根据配置文件获得query
		Query q = getSessionFactory().getCurrentSession().getNamedQuery(query);
		int num = 0;
		//为query注入参数
		for (Object object : objects) {
			q.setParameter(num++, object);
		}
		q.executeUpdate();
		
	}

	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//根据当前条件查找总记录数
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> total = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
		if(total!=null&&total.size()>0) {
			pageBean.setTotal(total.get(0).intValue());			
		}else {
			pageBean.setTotal(0);
		}
		
		//根据当前条件分页查找
		detachedCriteria.setProjection(null);
		int firstResult = (currentPage-1)*pageSize;  
		//设置查找到的对象的封装方式----多表查询的时候对象属性里面包含另一属性需要设置，否则会封装成两个对象
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, pageSize);
		//将查找到的list封装进pageBean
		pageBean.setRows(list);
	}

	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public List findByCriteria(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	


}
