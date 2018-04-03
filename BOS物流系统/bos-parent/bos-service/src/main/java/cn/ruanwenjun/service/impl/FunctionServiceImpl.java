package cn.ruanwenjun.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.IFunctionDao;
import cn.ruanwenjun.domain.Function;
import cn.ruanwenjun.service.IFunctionService;
import cn.ruanwenjun.utils.MD5Utils;
import cn.ruanwenjun.utils.PageBean;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年3月30日 下午3:34:31
*/
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	@Autowired
	private IFunctionDao functionDao;
	public void save(Function model) {
		Function parentFunction = model.getParentFunction();
		if(parentFunction != null && parentFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.add(model);
	}
	//查找所有一级权限
	public List<Function> findAllFirstFunction() {
		return functionDao.findAllFirstFunction();
	}

	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
	//查找所有权限
	public List<Function> findAll() {
		List<Function> list = functionDao.findAll();
		return list;
	}

}
