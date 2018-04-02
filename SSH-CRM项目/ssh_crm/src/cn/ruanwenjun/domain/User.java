package cn.ruanwenjun.domain;

import java.util.HashSet;
import java.util.Set;

public class User {
	private Long user_id;
	private String user_name;    //用户昵称
	private String user_password; //用户密码
	private String user_code;    //用户名
	
	private Set<SaleVisit> saleVists = new HashSet<SaleVisit>();
	
	
	
	public Set<SaleVisit> getSaleVists() {
		return saleVists;
	}
	public void setSaleVists(Set<SaleVisit> saleVists) {
		this.saleVists = saleVists;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", user_code=" + user_code + "]";
	}
	
	
	
	
}
