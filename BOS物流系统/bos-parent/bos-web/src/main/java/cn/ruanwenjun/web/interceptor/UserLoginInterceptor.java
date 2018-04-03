package cn.ruanwenjun.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.utils.BosUtils;

/**
 * 用户登陆拦截器
 * @author RUANWENJUN
 *
 */
public class UserLoginInterceptor extends MethodFilterInterceptor{

	protected String doIntercept(ActionInvocation invocation) throws Exception {
		/*ActionProxy proxy = invocation.getProxy();
		String actionName = proxy.getActionName();
		String namespace = proxy.getNamespace();
		System.out.println(namespace+actionName);*/
		
		//从session中获取用户，即判断是否有用户登陆
		User user = BosUtils.getCurrentLoginUser();
		if(user == null) {
			//没有用户登陆,跳转到登陆页面
			return "login";
		}
		//有用户登陆,放行
		return invocation.invoke();
	}

}
