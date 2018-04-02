package cn.ruanwenjun.bean;

import java.util.List;

public class PageBean {
	
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalCount;
	private Integer totalPage;
	private List<?> list ;
	
	public PageBean(Integer currentPage, Integer pageSize, Integer totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
		if(currentPage==null) {
			this.currentPage=1;
		}
		if(pageSize==null) {
			this.pageSize=3;
		}
		
		this.totalPage=(this.totalCount+this.pageSize-1)/this.pageSize;
	}
	
	public Integer getCurrentIndex(int currentPage,int pageSize) {
		return (currentPage-1)*pageSize;		
	}
	
	
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [list=" + list + "]";
	}
	
	
	
	
	
	
}
