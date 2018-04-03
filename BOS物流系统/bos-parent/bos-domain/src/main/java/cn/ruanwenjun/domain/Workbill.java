package cn.ruanwenjun.domain;

import java.sql.Timestamp;

/**
 * 工单
 */

public class Workbill implements java.io.Serializable {

	// Fields

	private String id;
	private Noticebill noticebill;
	private Staff staff;
	private String type;//工单类型:新、追、改、销
	private String pickstate;//取件状态:未取件、取件中、已取件
	private Timestamp buildtime;
	private Integer attachbilltimes;  //追单次数
	private String remark;              //备注

	public static final String  TYPE_1 = "新单";
	public static final String  TYPE_2 = "追单";
	public static final String  TYPE_3 = "改单";
	public static final String  TYPE_4 = "销单";
	
	public static final String  PICKSTATE_NO = "未取件";
	public static final String  PICKSTATE_RUNNING = "取件中";
	public static final String  PICKSTATE_YES = "已取件";
	
	// Constructors

	/** default constructor */
	public Workbill() {
	}

	/** minimal constructor */
	public Workbill(String id, Timestamp buildtime) {
		this.id = id;
		this.buildtime = buildtime;
	}

	/** full constructor */
	public Workbill(String id, Noticebill noticebill, Staff staff, String type,
			String pickstate, Timestamp buildtime, Integer attachbilltimes,
			String remark) {
		this.id = id;
		this.noticebill = noticebill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachbilltimes = attachbilltimes;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Noticebill getNoticebill() {
		return this.noticebill;
	}

	public void setNoticebill(Noticebill noticebill) {
		this.noticebill = noticebill;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPickstate() {
		return this.pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	public Timestamp getBuildtime() {
		return this.buildtime;
	}

	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}

	public Integer getAttachbilltimes() {
		return this.attachbilltimes;
	}

	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}