package cn.ruanwenjun.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.ruanwenjun.bean.PageBean;
import cn.ruanwenjun.domain.Customer;
import cn.ruanwenjun.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private Customer customer = new Customer();
	private Integer pageSize;
	private Integer currentPage;
	private CustomerService cs;
	private String fromLinkman;
	//获得列表
	public String list() {
		if(fromLinkman!=null&&!fromLinkman.equals("true")) {
			fromLinkman=null;
		}
		//1.接收前台的参数，客户名称，pageSize,currentPage(完成)
		//2.完成离线Criteria的创建
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		if(customer!=null) {
			//System.out.println(customer.getCust_name());
			if(!StringUtils.isBlank(customer.getCust_name())) {
				dc.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));			
			}
		}
		//3.调用service的方法，传入参数dc,currentPage,pageSize,返回一个pageBean
		PageBean pb = cs.findPageBean(dc,currentPage,pageSize);
		//4.将list加入request域
		ActionContext.getContext().put("pageBean", pb);
		ActionContext.getContext().put("fromLinkman", fromLinkman);
		
		//5.转发到页面
		return "list";
	}
	
	//添加
	public String add() {
		//接收添加客户的参数
		cs.save(customer);
		//重定向到list
		return "toList";
	}
	//到编辑页面
	public String toEdit() {
		//调用service处理查找业务
		Customer c = cs.find(customer.getCust_id());
		//将customer对象加入request域
		ActionContext.getContext().put("customer", c);
		//转发到编辑页面
		return "edit";
	}
	//更新
	public String update() {
		System.out.println(customer);
		cs.update(customer);
		//重定向到list
		return "toList";
	}
	public String countIndustry() {
		//
		List<Object[]> countList = cs.countIndustry();
		//将list加入到request域
		System.out.println(countList);
		ActionContext.getContext().put("countList", countList);
		//转发到客户行业统计页面
		return "industryCountList";
	}
	
	
	
	public void setCs(CustomerService cs) {
		this.cs = cs;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Customer getModel() {
		return customer;
	}


	public void setFromLinkman(String fromLinkman) {
		this.fromLinkman = fromLinkman;
	}

	
}
