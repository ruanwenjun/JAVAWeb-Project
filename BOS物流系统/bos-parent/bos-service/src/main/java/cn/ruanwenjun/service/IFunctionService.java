package cn.ruanwenjun.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.utils.PageBean;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年3月30日 下午3:33:23
*/
public interface IFunctionService {

	public void save(Function model);

	public List<Function> findAllFirstFunction();

	public void pageQuery(PageBean pageBean);

	public List<Function> findAll();

}
