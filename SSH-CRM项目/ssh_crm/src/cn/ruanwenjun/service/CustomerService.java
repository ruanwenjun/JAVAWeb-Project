package cn.ruanwenjun.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.ruanwenjun.bean.PageBean;
import cn.ruanwenjun.domain.Customer;


public interface CustomerService {
	/**
	 * 分页
	 * @param dc
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean findPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	
	/**
	 * 保存客户
	 * @param customer
	 */
	void save(Customer customer);
	/**
	 * 查找客户
	 * @param cust_id
	 * @return
	 */
	Customer find(Serializable cust_id);
	/**
	 * 修改客户
	 * @param customer
	 */
	void update(Customer customer);

	List<Object[]> countIndustry();

}
