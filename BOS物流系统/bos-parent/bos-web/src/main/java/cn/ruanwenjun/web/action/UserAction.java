package cn.ruanwenjun.web.action;


import java.io.IOException;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.domain.User;
import cn.ruanwenjun.service.IUserService;
import cn.ruanwenjun.utils.BosUtils;
import cn.ruanwenjun.utils.MD5Utils;
import cn.ruanwenjun.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private String checkcode;       //验证码
	private String[] roleId;        //角色ID
	
	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
	@Autowired
	private IUserService userService;
	/***
	 * 用户登陆
	 * @return
	 */
	//使用shiro方式认证
	public String login() {
		//先判断验证码是否正确
		String validateCheckcode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//验证码正确
		if(StringUtils.isNotBlank(checkcode)&&checkcode.equals(validateCheckcode)) {
			Subject subject = SecurityUtils.getSubject();        //代表当前对象，状态为未认证
			//创建一个用户密码令牌
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
			try {
				//登陆校验
				subject.login(token);
			} catch (Exception e) {
				addActionError("用户名或密码不正确");
				return LOGIN;
			}
			User user = (User) subject.getPrincipal();   //获得User对象
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			return HOME;
		}else {
			//验证码不正确
			addActionError("验证码不正确");
			return LOGIN;			
		}
	}
	/***
	 * 用户登陆
	 * @return
	 */
	/*public String login() {
		
		//先判断验证码是否正确
		String validateCheckcode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//验证码正确
		if(checkcode.equals(validateCheckcode)) {
			//验证码输入正确
			User user = userService.login(model);
			if(user != null) {
				//登陆成功
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return HOME;
			}else {
				//登陆失败
				addActionError("用户名或密码错误");
				return LOGIN;				
			}
		}else {
			//验证码不正确
			addActionError("验证码不正确");
			return LOGIN;			
		}
	}*/
	/**
	 * 用户注销
	 * @return
	 */
	public String logout() {
		
		return LOGIN;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws IOException 
	 */
	public String editPassword() throws IOException {
		//修改密码是否成功的标志，1代表成功，0代表失败
		String result = "1";
		String password = model.getPassword();
		User user = BosUtils.getCurrentLoginUser();
		try {
			//调用service层方法修改密码
			userService.editPassword(user.getId(),password);	
		} catch (Exception e) {
			e.printStackTrace();
			//修改失败
			result = "0";
		}
		ServletActionContext.getResponse().setContentType("html/text;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(result);
		return NONE;
	}
	
	//添加用户,并关联角色
	public String add() {
		try {
			userService.add(model, roleId);
		} catch (Exception e) {
			addActionError("用户名已存在");
			return "add";
		}
		return SUCCESS;
	}
	//分页查询用户
	public String pageQuery() {
		userService.pageQuery(pageBean);
		this.Object2JsonString(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","noticebills"
											,"roles","telephone",""});
		return NONE;
	}
	
	//根据登陆的用户动态加载菜单
	public String menuAjax() {
		List<Function> list = userService.findMenu();
		this.Object2JsonString(list, new String[] {"parentFunction","code","description",
										"generatemenu","roles","children"});
		return NONE;
	}
	
}
