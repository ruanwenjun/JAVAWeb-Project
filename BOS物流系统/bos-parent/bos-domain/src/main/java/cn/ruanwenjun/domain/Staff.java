package cn.ruanwenjun.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 取派员，一个取派员对应多个定区
 * @author RUANWENJUN
 *
 */
public class Staff {
	
	private String id;
	private String name;
	private String telephone;
	private String haspda = "0";   //是否有PDA的表示，1表示有
	private String deltag = "0";	//是否被删除的标志，1表示删除
	private String standard;        
	private Set<Decidedzone> decidedzones = new HashSet<Decidedzone>();
	private String station;         //所属公司
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getHaspda() {
		return haspda;
	}
	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public Set<Decidedzone> getDecidedzones() {
		return decidedzones;
	}
	public void setDecidedzones(Set<Decidedzone> decidedzones) {
		this.decidedzones = decidedzones;
	}
	
	

}
