package cn.ruanwenjun.domain;

public class CartItem {
	private Product product ;
	private int productNum;
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProNum(int productNum) {
		this.productNum = productNum;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
