package cn.ruanwenjun.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.ruanwenjun.dao.AdminDao;
import cn.ruanwenjun.domain.Category;
import cn.ruanwenjun.domain.Order;
import cn.ruanwenjun.domain.Product;



public class AdminService {
	/**
	 * 获得所有订单
	 * @return
	 */
	public List<Order> getAllOrderList() {
		AdminDao dao = new AdminDao();
		List<Order> orderList = null;
		try {
			orderList = dao.getAllOrderList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	/**
	 * 查找指定id的订单的信息
	 * @param oid
	 * @return
	 */
	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminDao dao = new AdminDao();
		List<Map<String, Object>>mapList = null;
		try {
			mapList = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}
	/**
	 * 获得所有商品
	 * @return
	 */
	public List<Product> getAllProductList() {
		AdminDao dao = new AdminDao();
		List<Product> productList = null;
		try {
			productList = dao.getAllProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	/**
	 * 获得所有商品类别
	 * @return
	 */
	public List<Category> getAllCategory() {
		AdminDao dao = new AdminDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.getAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	/**
	 * 添加商品
	 * @param product
	 */
	public void addProduct(Product product) {
		AdminDao dao = new AdminDao();
		try {
			dao.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
