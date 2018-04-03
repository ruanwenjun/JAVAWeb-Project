package cn.ruanwenjun.web.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ruanwenjun.crm.Customer;
import cn.ruanwenjun.crm.ICustomerService;
import cn.ruanwenjun.domain.Decidedzone;
import cn.ruanwenjun.service.IDecidedzoneService;
import cn.ruanwenjun.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	@Autowired
	private ICustomerService proxy;          //客户服务
	
	private String[] subareaId;              //页面提交的分区ID
	private String decidedzoneId;            //当前选中的定区ID
	private List<Integer> customerIds;            //关联客户提交的客户ID
	

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	
	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	/**
	 * 添加定区
	 * @return
	 */
	
	public String add() {
		decidedzoneService.save(model,subareaId);
		return SUCCESS;
	}
	/**
	 * 分页查询定区
	 * @return
	 */
	public String pageQuery() {
		decidedzoneService.pageQuery(pageBean);
		this.Object2JsonString(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","subareas","decidedzones"});
		return NONE;
	}
	/**
	 * 查询所有未关联定区的客户
	 * @return
	 */
	public String findCustomerNotAssociat() {
		
		List<Customer> list = proxy.findCustomerNotAssociat();
		//将list转成JSON字符串格式写给页面
		this.Object2JsonString(list, new String[] {"station","address","decidedzone_id"});
		return NONE;
	}
	/**
	 * 根据定区ID查询关联定区的客户
	 * @return
	 */
	public String findCustomerHasAssociat() {
		List<Customer> list = proxy.findCustomerHasAssociat(decidedzoneId);
		this.Object2JsonString(list, new String[]{"address","decidedzone_id"});
		return NONE;
	}
	/**
	 * 提交修改客户关联信息
	 * @return
	 */
	public String submitAssociat() {
		if(customerIds !=null) {
			proxy.editCustomerAssociat(customerIds, model.getId());
		}else {
			//指定定区的关联的客户都解除关联
			proxy.removeCustomerAssociat(model.getId());
		}
		return SUCCESS;
	}
	
	
}
