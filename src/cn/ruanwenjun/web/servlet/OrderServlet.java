package cn.ruanwenjun.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.ruanwenjun.domain.Cart;
import cn.ruanwenjun.domain.CartItem;
import cn.ruanwenjun.domain.Order;
import cn.ruanwenjun.domain.OrderItem;
import cn.ruanwenjun.domain.Product;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.OrderService;
import cn.ruanwenjun.utils.DataSourceUtils;
import cn.ruanwenjun.utils.PaymentUtil;

public class OrderServlet extends BasicServlet{
	public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//确认订单,将订单信息提交到数据库
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		order.setOrdertime(format.format(new Date()));
		//将order传入到数据库
		try {
			/****************开启事务***************************/
			DataSourceUtils.startTransaction();
			OrderService service = new OrderService();
			List<OrderItem> list = order.getList();
			service.setOrderItem(order);                 //先将order放入数据库
			for (OrderItem orderItem : list) {
				orderItem.setOrder(order);
				service.setOrderItem(orderItem);
			}
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*************调用第三方支付平台*******************/
		// 获得 支付必须基本数据
		String orderid = order.getOid();
		//String money = request.getParameter("money");
		// 银行
		String pd_FrpId = request.getParameter("pd_FrpId");

		// 发给支付公司需要哪些数据
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = orderid;        //商户订单号
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		
		String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
						"&p0_Cmd="+p0_Cmd+
						"&p1_MerId="+p1_MerId+
						"&p2_Order="+p2_Order+
						"&p3_Amt="+p3_Amt+
						"&p4_Cur="+p4_Cur+
						"&p5_Pid="+p5_Pid+
						"&p6_Pcat="+p6_Pcat+
						"&p7_Pdesc="+p7_Pdesc+
						"&p8_Url="+p8_Url+
						"&p9_SAF="+p9_SAF+
						"&pa_MP="+pa_MP+
						"&pr_NeedResponse="+pr_NeedResponse+
						"&hmac="+hmac;

		//重定向到第三方支付平台
		response.sendRedirect(url);
	}
	
	
	/**
	 * 确认订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			//用户未登陆
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}else {
			//将购物车里的购物项封装到订单项中
			Cart cart = (Cart) session.getAttribute("cart");
			Map<String, CartItem> cartMap = cart.getCartMap();
			Order order = new Order();                       //订单对象
			order.setOid(UUID.randomUUID().toString());
			order.setState(0);
			order.setUid(user.getUid());
			order.setTotal(cart.getTotal());
			List<OrderItem> list = new ArrayList<OrderItem>();
			for (Map.Entry<String, CartItem> entry:cartMap.entrySet()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setProduct(entry.getValue().getProduct());
				orderItem.setItemid(UUID.randomUUID().toString());
				orderItem.setOrder(order);
				orderItem.setProductNum(entry.getValue().getProductNum());
				orderItem.setSubTotal(entry.getValue().getSubTotal());
				list.add(orderItem);
			}
			order.setList(list);
			session.setAttribute("order", order);
			response.sendRedirect(request.getContextPath()+"/order_info.jsp");
		}
	}
	/**
	 * 我的订单 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void myOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//从session中拿到order,根据其中的uid查询数据库找到orderList,然后遍历每一个order
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			//用户未登陆
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			//用户登陆了
			String uid = user.getUid();
			OrderService service = new OrderService();
			List<Order> orderList = service.findAllOrderByUid(uid);
			for(Order order : orderList) {
				//为每一个Order封装List<OrderItem> list,还要未每一个orderItem封装Product product
				List<Map<String, Object>> maplist = service.findAllOrderItemByOid(order.getOid());
				//遍历封装
				for(Map<String,Object> map : maplist) {
					try {
						Product product = new Product();
						OrderItem orderItem = new OrderItem();
						BeanUtils.populate(product, map);
						BeanUtils.populate(orderItem, map);
						orderItem.setProduct(product);
						order.getList().add(orderItem);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			session.setAttribute("orderList", orderList);
			response.sendRedirect(request.getContextPath()+"/order_list.jsp");
		}
		
		
		
	}
	
	
	
	
	
}
