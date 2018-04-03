package cn.ruanwenjun.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 区域，一个区域对应多个分区
 * @author RUANWENJUN
 *
 */
public class Region {
	private String id;         //区域编号
	private String province;   //省份
	private String city;       //城市
	private String district;   //区域
	private String postcode;   //邮编
	private String shortcode;  
	private String citycode;
	private Set<Subarea> subareas = new HashSet<Subarea>();       //分区
	
	public Region() {
		
	}
	//构造器
	public Region(String id, String province, String city, String district, String postcode, String shortcode,
			String citycode, Set<Subarea> subareas) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}
	//传给前台的name
	public String getName() {
		return province + " " +city + " " + district;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getShortcode() {
		return shortcode;
	}
	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public Set<Subarea> getSubareas() {
		return subareas;
	}
	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}
}
