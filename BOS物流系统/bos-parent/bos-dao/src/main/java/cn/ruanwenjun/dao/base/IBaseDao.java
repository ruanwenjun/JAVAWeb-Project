package cn.ruanwenjun.dao.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.ruanwenjun.utils.PageBean;

/**
 * 持久层通用接口
 * @author RUANWENJUN
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	
	/**
	 * 增
	 * @param entity
	 */
	public void add(T entity);
	
	/**
	 * 删
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 修改
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据查询语句进行更新
	 * @param query   HQL查询语句
	 * @param objects 代表查询语句中的参数
	 */
	public void executeUptate(String query,Object...objects);
	
	/**
	 * 分页查询
	 * @param pageBean
	 */
	public void pageQuery(PageBean pageBean);
	/**
	 * 保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	/**
	 * 根据离线查询条件查询所有
	 * @param detachedCriteria
	 * @return
	 */
	public List<T> findByCriteria(DetachedCriteria detachedCriteria);
}

