package cn.ruanwenjun.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 定区，多个定区对应一个取派员，一个定区对应多个分区
 * @author RUANWENJUN
 *
 */
public class Decidedzone {

	private String id;
	private String name;
	private Staff staff;
	private Set<Subarea> subareas = new HashSet<Subarea>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Set<Subarea> getSubareas() {
		return subareas;
	}
	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}
		
}
