package cn.ruanwenjun.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.ruanwenjun.domain.Region;
import cn.ruanwenjun.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 表现层通用代码
 * @author RUANWENJUN
 *
 * @param <T> 实体对象
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	//实体模型对象
	protected T model;
	public T getModel() {
		return model;
	}
	//离线查询对象
	private DetachedCriteria detachedCriteria = null;
	public void setPage(int page) {
		//将前台传来的的page注入进pageBean
		pageBean.setCurrentPage(page);
	}
	public void setRows(int rows) {
		//将前台传来的的rows注入进pageBean
		pageBean.setPageSize(rows);
	}
	//分页查找的pageBean
	protected PageBean pageBean = new PageBean();
	public static final String HOME = "home";
	
	
	//通过构造函数动态获取实体类型
	public BaseAction() {	
		//获得带泛型的父类类型
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得实际的泛型类型
		Type[] actualTypeArguments = superClass.getActualTypeArguments();
		Class<T> clazz = (Class<T>) actualTypeArguments[0];
		
		//动态设置离线查询对象，并封装进pageBean 
		detachedCriteria = DetachedCriteria.forClass(clazz);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			//通过反射为model创建实例
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将对象转换成json字符串并写给前台
	 * @param o 需要转换的对象
	 * @param excludes 不需要转换的对象名
	 */
	protected void Object2JsonString(Object java,String[] excludes) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		String json = JSONObject.fromObject(java,config).toString();
		try {
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将List转换成json字符串并写给前台
	 * @param o 需要转换的对象
	 * @param excludes 不需要转换的对象名
	 */
	protected void Object2JsonString(List java,String[] excludes) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		String json = JSONArray.fromObject(java, config).toString();
		try {
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
