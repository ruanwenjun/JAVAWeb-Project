package cn.ruanwenjun.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ruanwenjun.domain.Product;


/**
 * 这个基类设计十分巧妙，当子类Servlet被访问时会调用service方法，子类中没有写service方法，那么就会调用父类的service方法，而父类的
 * service方法中，this.getClass得到的是当前调用该service方法的子类的字节码对象。从而节省代码。
 * @author RUANWENJUN
 *
 */
public class BasicServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if(method!=null) {
			try {
				Method method2 = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				method2.invoke(this, request,response);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
