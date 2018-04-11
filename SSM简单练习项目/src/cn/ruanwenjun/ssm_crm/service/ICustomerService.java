package cn.ruanwenjun.ssm_crm.service;

import java.util.List;

import cn.ruanwenjun.ssm_crm.domain.BaseDict;
import cn.ruanwenjun.ssm_crm.domain.Customer;
import cn.ruanwenjun.ssm_crm.domain.QueryVo;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月11日 下午7:37:01
*/
public interface ICustomerService {

	List<BaseDict> findBaseDictByCode(String string);

	Integer selectTotalCountByQueryVo(QueryVo vo);

	List<Customer> selectCustomerListByQueryVo(QueryVo vo);

}
