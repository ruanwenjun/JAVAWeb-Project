package cn.ruanwenjun.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.ruanwenjun.dao.OrderDao;
import cn.ruanwenjun.domain.Order;
import cn.ruanwenjun.domain.OrderItem;

public class OrderService {
	/**
	 * 将订单项写入数据库
	 * @param orderItem
	 */
	public void setOrderItem(OrderItem orderItem) {
		OrderDao dao = new OrderDao();
		try {
			dao.setOrderItem(orderItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 将订单写入数据库
	 * @param order
	 */
	public void setOrderItem(Order order) {
		OrderDao dao = new OrderDao();
		try {
			dao.setOrderItem(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 改变订单的支付状态
	 * @param orderId
	 */
	public void changeOrderState(String orderId) {
		OrderDao dao = new OrderDao();
		try {
			dao.changeOrderState(orderId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据用户名ID查找所有的订单
	 * @param uid
	 * @return
	 */
	public List<Order> findAllOrderByUid(String uid) {
		OrderDao dao = new OrderDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrderByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	/**
	 * 根据oid从数据库中查询orderItem,并且还有product等数据
	 * @param oid
	 */
	public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
		OrderDao dao = new OrderDao();
		List<Map<String, Object>> maplist = null;
		try {
			 maplist = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maplist;
	}
}
