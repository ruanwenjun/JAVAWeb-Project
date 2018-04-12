package cn.ruanwenjun.ssm_crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ruanwenjun.common.utils.Page;
import cn.ruanwenjun.ssm_crm.domain.BaseDict;
import cn.ruanwenjun.ssm_crm.domain.Customer;
import cn.ruanwenjun.ssm_crm.domain.QueryVo;
import cn.ruanwenjun.ssm_crm.service.ICustomerService;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月11日 下午6:47:31
 */

@Controller
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	// 跳转到页面
	@RequestMapping(value = "customer/list.action")
	public String list(Model model, QueryVo vo) {
		// 从字典表里查询客户来源002---fromType
		// 所属行业001----industryType
		// 客户级别006----levelType
		List<BaseDict> fromType = customerService.findBaseDictByCode("002");
		List<BaseDict> industryType = customerService.findBaseDictByCode("001");
		List<BaseDict> levelType = customerService.findBaseDictByCode("006");
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);

		Page<Customer> page = new Page<Customer>();

		// 获取前台传来的数据
		if (vo != null) {
			Integer currentpage = vo.getPage();
			if (currentpage != null) {
				page.setPage(currentpage);
				Integer start = (currentpage - 1) * vo.getRows();
				vo.setStart(start);
			}
			if (vo.getCust_industry() != null) {
				String cust_industry = vo.getCust_industry().trim();
				vo.setCust_industry(cust_industry);
			}
			if (vo.getCust_level() != null) {
				String cust_level = vo.getCust_level().trim();
				vo.setCust_level(cust_level);
			}
			if (vo.getCust_name() != null) {
				String cust_name = vo.getCust_name().trim();
				vo.setCust_name(cust_name);
			}
			if (vo.getCust_source() != null) {
				String cust_source = vo.getCust_source().trim();
				vo.setCust_source(cust_source);
			}
		} else {
			vo = new QueryVo();
		}
		vo.setRows(10);
		// 查询总数目
		Integer count = customerService.selectTotalCountByQueryVo(vo);
		page.setTotal(count);
		
		List<Customer> list = customerService.selectCustomerListByQueryVo(vo);
		page.setRows(list);
		
		model.addAttribute("page",page);
		model.addAttribute("cust_industry",vo.getCust_industry());
		model.addAttribute("cust_level",vo.getCust_level());
		model.addAttribute("cust_source",vo.getCust_source());
		model.addAttribute("cust_name", vo.getCust_name());

		return "customer";
	}
	
	//查询页面,根据ID查询客户然后返回给前台页面
	@RequestMapping(value="customer/edit.action")
	public @ResponseBody Customer toEdit(Integer id) {
		Customer customer = customerService.findCustomerById(id);
		return customer;
	}
	
	//修改客户
	@RequestMapping(value="customer/update.action")
	public @ResponseBody String saveEdit(Customer customer) {
		customerService.saveEdit(customer);
		return "";
	}
	
	
}
