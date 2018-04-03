package cn.ruanwenjun.domain;

import java.util.Date;

/**
 * 工作单
 */

public class Workordermanage implements java.io.Serializable {

	// Fields

	private String id;
	private String arrivecity;
	private String product;
	private Integer num;
	private Double weight;
	private String floadreqr;
	private String prodtimelimit;
	private String prodtype;
	private String sendername;
	private String senderphone;
	private String senderaddr;
	private String receivername;
	private String receiverphone;
	private String receiveraddr;
	private Integer feeitemnum;
	private Double actlweit;
	private String vol;
	private String managerCheck;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public Workordermanage() {
	}

	/** minimal constructor */
	public Workordermanage(String id) {
		this.id = id;
	}

	/** full constructor */
	public Workordermanage(String id, String arrivecity, String product,
			Integer num, Double weight, String floadreqr, String prodtimelimit,
			String prodtype, String sendername, String senderphone,
			String senderaddr, String receivername, String receiverphone,
			String receiveraddr, Integer feeitemnum, Double actlweit,
			String vol, String managerCheck, Date updatetime) {
		this.id = id;
		this.arrivecity = arrivecity;
		this.product = product;
		this.num = num;
		this.weight = weight;
		this.floadreqr = floadreqr;
		this.prodtimelimit = prodtimelimit;
		this.prodtype = prodtype;
		this.sendername = sendername;
		this.senderphone = senderphone;
		this.senderaddr = senderaddr;
		this.receivername = receivername;
		this.receiverphone = receiverphone;
		this.receiveraddr = receiveraddr;
		this.feeitemnum = feeitemnum;
		this.actlweit = actlweit;
		this.vol = vol;
		this.managerCheck = managerCheck;
		this.updatetime = updatetime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArrivecity() {
		return this.arrivecity;
	}

	public void setArrivecity(String arrivecity) {
		this.arrivecity = arrivecity;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getFloadreqr() {
		return this.floadreqr;
	}

	public void setFloadreqr(String floadreqr) {
		this.floadreqr = floadreqr;
	}

	public String getProdtimelimit() {
		return this.prodtimelimit;
	}

	public void setProdtimelimit(String prodtimelimit) {
		this.prodtimelimit = prodtimelimit;
	}

	public String getProdtype() {
		return this.prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getSendername() {
		return this.sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getSenderphone() {
		return this.senderphone;
	}

	public void setSenderphone(String senderphone) {
		this.senderphone = senderphone;
	}

	public String getSenderaddr() {
		return this.senderaddr;
	}

	public void setSenderaddr(String senderaddr) {
		this.senderaddr = senderaddr;
	}

	public String getReceivername() {
		return this.receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getReceiverphone() {
		return this.receiverphone;
	}

	public void setReceiverphone(String receiverphone) {
		this.receiverphone = receiverphone;
	}

	public String getReceiveraddr() {
		return this.receiveraddr;
	}

	public void setReceiveraddr(String receiveraddr) {
		this.receiveraddr = receiveraddr;
	}

	public Integer getFeeitemnum() {
		return this.feeitemnum;
	}

	public void setFeeitemnum(Integer feeitemnum) {
		this.feeitemnum = feeitemnum;
	}

	public Double getActlweit() {
		return this.actlweit;
	}

	public void setActlweit(Double actlweit) {
		this.actlweit = actlweit;
	}

	public String getVol() {
		return this.vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getManagerCheck() {
		return this.managerCheck;
	}

	public void setManagerCheck(String managerCheck) {
		this.managerCheck = managerCheck;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}