package cn.ruanwenjun.ssm_crm.dao;

import java.util.List;

import cn.ruanwenjun.ssm_crm.domain.BaseDict;
import cn.ruanwenjun.ssm_crm.domain.Customer;
import cn.ruanwenjun.ssm_crm.domain.QueryVo;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月11日 下午7:40:33
*/
public interface ICustomerDao {

	public List<BaseDict> findBaseDictByCode(String code);

	public Integer selectTotalCountByQueryVo(QueryVo vo);

	public List<Customer> selectCustomerListByQueryVo(QueryVo vo);

	public Customer findCustomerById(Integer id);

	public void saveEdit(Customer customer);

}
