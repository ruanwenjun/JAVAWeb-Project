package cn.ruanwenjun.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.UserService;
import cn.ruanwenjun.utils.MailUtils;

public class UserServlet extends BasicServlet {
	public void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 从前台接收数据,并封装成User
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		// 手动封装
		user.setUid(UUID.randomUUID().toString());
		user.setTelephone("123");
		user.setState(0); // 0代表默认是未激活
		String activeCode = UUID.randomUUID().toString(); // 激活码
		user.setCode(activeCode);
		// 将User传给service层进行注册
		UserService service = new UserService();
		boolean isRegister = service.register(user);
		if (isRegister) {
			// 注册成功,发送激活邮件，跳转到激活页面
			try {
				MailUtils.sendMail(user.getEmail(),
						"请点击进行激活<a href='http://localhost:8080/Shop/activeUser?activeCode=" + activeCode + "'>"
								+ "http://localhost:8080/Shop/user?method=activeUser&activeCode=" + activeCode + "</a>");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
		} else {
			// 注册失败，跳转到失败页面
			response.sendRedirect(request.getContextPath() + "/registerFail.jsp");
		}
	}

	public void activeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 激活用户
		String activeCode = request.getParameter("activeCode");
		UserService service = new UserService();
		int activeUser = service.activeUser(activeCode);
		if (activeUser > 0) {
			// 激活成功
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write("恭喜您，激活成功,点击跳转到登陆页面<a href='" + request.getContextPath() + "login.jsp'></a>");
		} else {
			// 激活失败
			response.getWriter().write("激活失败,请重新激活或联系管理员");
		}
	}

	public void isUsernameExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		UserService service = new UserService();
		Boolean isExist = service.isExist(username);
		response.getWriter().write("{\"isExist\":" + isExist + "}");
	}

	public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> properties = request.getParameterMap();
		User loginUser = new User();
		try {
			BeanUtils.populate(loginUser, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		UserService service = new UserService();
		User user = service.login(loginUser);
		if (user == null) {
			// 表示用户不存在
			request.getSession().setAttribute("loginInfo", "false"); // 将登陆信息存到session中
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} else {
			if (user.getState() == 0) {
				// 表示用户尚未激活
				request.getSession().setAttribute("loginInfo", "notActive");
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			} else {
				// 表示登陆成功
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath());
			}
		}
	}

	public void quitUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 退出当前登陆
		request.getSession().setAttribute("user", null);
		response.sendRedirect(request.getContextPath());
	}

	public void checkCodeVarify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String checkCode = (String) request.getParameter("checkCode");
		Boolean isRight = checkCode.equals(request.getSession().getAttribute("checkcode_session"));
		response.getWriter().write("{\"isRight\":" + isRight + "}");
	}
}
