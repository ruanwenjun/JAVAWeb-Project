package cn.ruanwenjun.ssm_crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.ruanwenjun.ssm_crm.dao.ICustomerDao;
import cn.ruanwenjun.ssm_crm.domain.BaseDict;
import cn.ruanwenjun.ssm_crm.domain.Customer;
import cn.ruanwenjun.ssm_crm.domain.QueryVo;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月11日 下午7:37:30
*/
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;
	
	//查询字典列表
	public List<BaseDict> findBaseDictByCode(String code) {
		return customerDao.findBaseDictByCode(code);
	}
	
	//查询满足条件的总数目
	public Integer selectTotalCountByQueryVo(QueryVo vo) {
		return customerDao.selectTotalCountByQueryVo(vo);
	}
	//条件查询
	public List<Customer> selectCustomerListByQueryVo(QueryVo vo) {
		return customerDao.selectCustomerListByQueryVo(vo);
	}
	//根据ID查询对象
	public Customer findCustomerById(Integer id) {
		
		return customerDao.findCustomerById(id);
	}
	//保存客户
	public void saveEdit(Customer customer) {
		customerDao.saveEdit(customer);
	}

}
