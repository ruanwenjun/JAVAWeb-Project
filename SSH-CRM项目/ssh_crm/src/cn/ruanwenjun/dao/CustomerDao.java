package cn.ruanwenjun.dao;

import java.util.List;

import cn.ruanwenjun.domain.Customer;

public interface CustomerDao extends BaseDao<Customer> {

	List<Object[]> countIndustry();
	
	

}
