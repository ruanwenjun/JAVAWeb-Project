package cn.ruanwenjun.shiro;

import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import cn.ruanwenjun.dao.IFunctionDao;
import cn.ruanwenjun.dao.IUserDao;
import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.domain.User;


public class BosRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IFunctionDao functionDao;
	//根据登陆的用户动态授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//获得当前用户
		User user = (User) principals.getPrimaryPrincipal();
		List<Function> list = null;
		//查找用户的权限的code
		if(user != null) {
			if(user.getUsername().equals("admin")) {
				list = functionDao.findAll();
			}else {
				list = functionDao.findAuthorizationByUserID(user.getId());
			}
		}
		
		if(list != null && list.size() > 0) {
			for(Function f : list) {
				info.addStringPermission(f.getCode());
			}
		}
		return info;
	}
	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		User user = userDao.findUserByUsernameAndPassword(usernamePasswordToken.getUsername());
		if(user==null) {
			return null;
		}
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

}
