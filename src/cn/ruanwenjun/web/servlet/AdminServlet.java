package cn.ruanwenjun.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.ruanwenjun.domain.Category;
import cn.ruanwenjun.domain.Order;
import cn.ruanwenjun.domain.Product;
import cn.ruanwenjun.service.AdminService;
/**
 * 后台页面的servlet
 * @author RUANWENJUN
 *
 */
public class AdminServlet extends BasicServlet{
	/**
	 * 获得所有订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void getAllOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		AdminService service = new AdminService();
		List<Order> orderList = service.getAllOrderList();
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
	}
	/**
	 * 根据订单id查询订单信息，包括商品图片，名字，价格，数量，小计，并以json字符串返回给前台页面
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void findOrderInfoByOid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/************模拟延迟
		 * try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String oid = request.getParameter("oid");
		AdminService service = new AdminService();
		List<Map<String,Object>>mapList = service.findOrderInfoByOid(oid);
		Gson gson = new Gson();
		String json = gson.toJson(mapList);
		System.out.println(json);
		response.setContentType("text/html;charset=UTF-8 ");
		response.getWriter().write(json);
	}
	/**
	 * 获得所有商品列表
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void getAllProductList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		AdminService service = new AdminService();
		List<Product> productList = service.getAllProductList();
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
	}
	/**
	 * 获得所有商品类别
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void getAllCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminService service = new AdminService();
		List<Category> categoryList = service.getAllCategory();
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		response.setContentType("texl/html; charset=UTF-8");
		response.getWriter().write(json);
	}

}
