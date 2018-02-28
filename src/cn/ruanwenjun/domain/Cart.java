package cn.ruanwenjun.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> cartMap = new HashMap<String,CartItem>();
	private double total;      //当前购物车的总金额
	public Map<String, CartItem> getCartMap() {
		return cartMap;
	}
	public void setCartMap(Map<String, CartItem> cartMap) {
		this.cartMap = cartMap;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	

}
