package cn.ruanwenjun.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 分页查找的工具对象
 * @author RUANWENJUN
 *
 */
public class PageBean {
	private int currentPage;                       //当前页码
	private int pageSize;                          //当前页面大小
	private int total;                             //总记录数
	private List rows;                             //查询到的列表
	private DetachedCriteria detachedCriteria;     //查询条件
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
}
