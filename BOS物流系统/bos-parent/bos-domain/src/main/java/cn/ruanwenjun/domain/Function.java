package cn.ruanwenjun.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 */

public class Function implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Function parentFunction;	//当前权限的上级权限
	private String code;            	//关键字 staff-edit类似
	private String description;
	private String page;				//路径
	private String generatemenu;		//是否生成菜单，1：是 0：否
	private Integer zindex;             //优先级
	private Set<Role> roles = new HashSet<Role>(0); //当前权限对应的多个角色
	private Set<Function> children = new HashSet<Function>(0);//当前权限的下级权限
	//上一级ID
	public String getpId() {
		if(parentFunction == null) {
			return "0";
		}
		return parentFunction.getId();
	}
	public Function() {}
	public Function(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public String getText() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Function getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Function> getChildren() {
		return children;
	}
	public void setChildren(Set<Function> children) {
		this.children = children;
	}
}