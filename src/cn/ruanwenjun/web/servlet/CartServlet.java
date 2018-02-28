package cn.ruanwenjun.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.ruanwenjun.domain.Cart;
import cn.ruanwenjun.domain.CartItem;
import cn.ruanwenjun.domain.Product;
import cn.ruanwenjun.service.ProductService;

public class CartServlet extends BasicServlet{
	public void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pid = request.getParameter("pid");
		ProductService service = new ProductService();
		Product product = service.findProductByPid(pid);
		int proNum = Integer.parseInt(request.getParameter("proNum"));
		HttpSession session = request.getSession();
		/***************** 更新session域中的购物车对象***************************/
		Cart cart = (Cart) session.getAttribute("cart");  
		if(cart==null) {
			cart = new Cart();
		}
		Map<String, CartItem> cartMap = cart.getCartMap();
		if(cartMap.containsKey(pid)) {
			CartItem cartItem = cartMap.get(pid);
			//购物车中已经包含了该商品
			cartItem.setProNum(cartItem.getProductNum()+proNum);
			cartItem.setSubTotal(cartItem.getSubTotal()+product.getShop_price()*proNum);
			cartMap.put(pid, cartItem);
		}else {
			//购物车中没有该商品
			CartItem cartItem = new CartItem();
			cartItem.setProNum(proNum);
			cartItem.setProduct(product);
			cartItem.setSubTotal(product.getShop_price()*proNum);
			cartMap.put(pid, cartItem);
		}
		cart.setTotal(cart.getTotal()+product.getShop_price()*proNum);
		cart.setCartMap(cartMap);
		session.setAttribute("cart", cart);
		//重定向到cart.jsp页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
	public void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*********清空session中的cart**********/
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Map<String, CartItem> cartMap = cart.getCartMap();
		cartMap.clear();
		cart.setCartMap(cartMap);
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Map<String, CartItem> cartMap = cart.getCartMap();
		if(cartMap.containsKey(pid)) {
			CartItem cartItem = cartMap.get(pid);
			cartMap.remove(pid);
			cart.setTotal(cart.getTotal()-cartItem.getSubTotal());
			cart.setCartMap(cartMap);
			session.setAttribute("cart", cart);
		}
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	
}