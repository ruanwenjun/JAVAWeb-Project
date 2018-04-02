package cn.ruanwenjun.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T>  {
	
	/**
	 * 增加对象
	 * @param t 
	 */
	void save(T t);
	
	/**
	 * 删除对象
	 * @param t
	 */
	void delete(T t);
	
	/**
	 * 更新对象
	 * @param t
	 */
	void update(T t);
	/**
	 * 查询所有数目
	 * @param dc
	 * @return
	 */
	Integer findTotalCount(DetachedCriteria dc);
	
	/**
	 * 根据ID查询对象
	 * @param s
	 * @return
	 */
	T findById(Serializable s);
	
	/**
	 * 分页查
	 * @param dc
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<T> findPageList(DetachedCriteria dc, Integer start, Integer pageSize);
	

}
