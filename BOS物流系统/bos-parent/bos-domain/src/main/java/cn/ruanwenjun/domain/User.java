package cn.ruanwenjun.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 */

public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String password;
	private Double salary;
	private Date birthday;
	private String gender;
	private String station;
	private String telephone;
	private String remark;
	private Set noticebills = new HashSet(0);
	private Set<Role> roles = new HashSet<Role>(0);
	
	public String getRoleName() {
		StringBuilder sb = new StringBuilder();
		if(roles != null && roles.size() > 0) {
			for (Role role : roles) {
				sb.append(role.getName());
				sb.append("    ");
			}
		}
		return sb.toString();
	}
	public String getBirthdayString() {
		if(birthday != null) {
			return new SimpleDateFormat("yyyy-mm-dd").format(birthday);
		}
		return "暂无数据";
	}
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String id) {
		this.id = id;
	}

	/** full constructor */
	public User(String id, String username, String password, Double salary,
			Date birthday, String gender, String station, String telephone,
			String remark, Set noticebills, Set roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.birthday = birthday;
		this.gender = gender;
		this.station = station;
		this.telephone = telephone;
		this.remark = remark;
		this.noticebills = noticebills;
		this.roles = roles;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getNoticebills() {
		return this.noticebills;
	}

	public void setNoticebills(Set noticebills) {
		this.noticebills = noticebills;
	}

	public Set getRoles() {
		return this.roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

}