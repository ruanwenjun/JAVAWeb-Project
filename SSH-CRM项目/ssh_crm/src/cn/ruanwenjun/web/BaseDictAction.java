package cn.ruanwenjun.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import cn.ruanwenjun.domain.BaseDict;
import cn.ruanwenjun.service.BaseDictService;


public class BaseDictAction extends ActionSupport  {
	private String dict_type_code;
	private BaseDictService bds;
	
	public String execute() throws Exception {
		//根据dict_type_code查找到BaseDict  List
		List<BaseDict> list = bds.find(dict_type_code);
		
		//将List转换为json格式的字符串
		Gson gson = new Gson();
		
		String json = gson.toJson(list);
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}




	public void setBds(BaseDictService bds) {
		this.bds = bds;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
	
	

}
