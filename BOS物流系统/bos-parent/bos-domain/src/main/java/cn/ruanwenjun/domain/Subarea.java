package cn.ruanwenjun.domain;


/*
 * 分区，多个分区对应一个定区，多个分区对应一个区域
 */
public class Subarea {
	private String id;
	private String addresskey;     //关键字
	private String startnum;       //起始号
	private String endnum;         //终止号
	private String single;         //单双号 ：0 单号：1 双号：2
	private String position;      //位置信息
	private Decidedzone decidedzone;     //所属定区
	private Region region;        //所属区域
	
	
	public String getsubareaId() {
		return id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddresskey() {
		return addresskey;
	}
	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}
	public String getStartnum() {
		return startnum;
	}
	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}
	public String getEndnum() {
		return endnum;
	}
	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}
	public String getSingle() {
		return single;
	}
	public void setSingle(String single) {
		this.single = single;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Decidedzone getDecidedzone() {
		return decidedzone;
	}
	public void setDecidedzone(Decidedzone decidedzone) {
		this.decidedzone = decidedzone;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	

}
