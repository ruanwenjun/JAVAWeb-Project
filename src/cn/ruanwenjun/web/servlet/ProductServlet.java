package cn.ruanwenjun.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import cn.ruanwenjun.domain.Category;
import cn.ruanwenjun.domain.PageBean;
import cn.ruanwenjun.domain.Product;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.ProductService;
import cn.ruanwenjun.service.UserService;
import cn.ruanwenjun.utils.MailUtils;

public class ProductServlet extends BasicServlet {
	
	
	public void product_list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String cid = request.getParameter("cid");
		ProductService service = new ProductService();
		PageBean pageBean = new PageBean();
		int currentPage =1;               //当前页
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int currentCount =12;             //每页显示的商品数目 
		int totalCount = service.findAllProductByCid(cid);       //当前类别的总商品数目
		int index = 0 ;          //第一个的索引
		if(currentPage!=1) {
			index = (currentPage-1)*currentCount-1;
		}
		List<Product> currentProductList = service.findPageBeanProductList(index,currentCount,cid);
		int totalPage = (int) Math.ceil((1.0*totalCount/currentCount));   //总页数
		/******************封装pageBean*********************************************/
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(currentProductList);
		pageBean.setTotalCount(totalCount);
		
		/***********************从Cookie中获得浏览历史******************************/	
		Cookie[] cookies = request.getCookies();
		List<Product> historyProductList = new ArrayList<Product>();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("historyPid")) {
				String str = cookie.getValue();
				String[] split = str.split("-");
				for(int i=0;i<split.length;i++) {
					Product hisPro = service.findProductByPid(split[i]);
					historyProductList.add(hisPro);	
					if(i==5) {
						break;
					}
				}
			}
		}
		/*********************转发***************************************/
		request.setAttribute("historyProductList", historyProductList);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid",cid);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}
	public void product_info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		ProductService service = new ProductService();
		Product product = service.findProductByPid(pid);
		Category category = service.findCategoryByCid(cid);
		StringBuffer sb = new StringBuffer();
		Cookie[] cookies = request.getCookies();
		List<Product> historyProductList =new ArrayList<Product>();
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("historyPid")) {
				String str = cookie.getValue();         //应该是3-1-2这种格式的
				String[] split = str.split("-");
				List<String> asList = Arrays.asList(split);
				LinkedList<String> linkedList = new LinkedList<>(asList);
				if(linkedList.contains(pid)) {
					linkedList.remove(pid);
				}
				for(int i =0;i<linkedList.size();i++) {
					sb.append(linkedList.get(i));
					if(i<linkedList.size()-1) {
						sb.append("-");
					}
				}
			}
		}
		String historyPid =pid;
		if(sb.length()!=0) {
			historyPid +="-"+sb.toString(); 
		}
		Cookie cookie = new Cookie("historyPid", historyPid);
		response.addCookie(cookie);
		product.setCategory(category);
		request.setAttribute("product", product);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}
	
	
	
	public void productCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Category> categoryList = service.findAllCategory();
		//将categoryList转为json字符串
		Gson gson = new Gson();
		String json = gson.toJson(categoryList);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(json);
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Product> hotProductList = service.findHotProduct();
		List<Product> newProductList = service.findNewProduct();
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList",newProductList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	
	
}

