package cn.ruanwenjun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.bean.PageBean;
import cn.ruanwenjun.dao.CustomerDao;
import cn.ruanwenjun.domain.Customer;
import cn.ruanwenjun.service.CustomerService;

@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao;
	
	


	public PageBean findPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1.根据dc查找到所有的数目
		dc.setProjection(Projections.rowCount());
		int totalCount =  dao.findTotalCount(dc);
		dc.setProjection(null);
		//2.封装pagebean
		PageBean pageBean = new PageBean(currentPage,pageSize,totalCount);
		//3.分页查询list
		
		List list = dao.findPageList(dc,pageBean.getCurrentIndex(pageBean.getCurrentPage(), pageBean.getPageSize()),pageBean.getPageSize());		
		pageBean.setList(list);
		
		return pageBean;
	}

	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(Customer customer) {
		dao.save(customer);
	}
	
	public Customer find(Serializable cust_id) {		
		return dao.findById(cust_id);
	}
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
	public void update(Customer customer) {
		dao.update(customer);
	}
	
	@Override
	public List<Object[]> countIndustry() {
		//调用Dao从数据库中查找
		return dao.countIndustry();
	}
	
	
	
	
	
	
	
	
	
	
	public void setDao(CustomerDao dao) {
		this.dao = dao;
	}






}
