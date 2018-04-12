package cn.ruanwenjun.ssm_crm.domain;
/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月11日 下午8:08:01
*/
public class QueryVo {
	private Integer page;   //当前页数
	private Integer rows = 10;   //当前页面大小
	private String cust_name; //客户名称
	private String cust_source;
	private String cust_industry;
	private String cust_level;
	private Integer start = 0;
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_source() {
		return cust_source;
	}
	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}
	public String getCust_industry() {
		return cust_industry;
	}
	public void setCust_industry(String cust_industry) {
		this.cust_industry = cust_industry;
	}
	public String getCust_level() {
		return cust_level;
	}
	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
}
