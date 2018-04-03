package cn.ruanwenjun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.IWorkordermanageDao;
import cn.ruanwenjun.domain.Workordermanage;
import cn.ruanwenjun.service.IWorkordermanageService;
import cn.ruanwenjun.utils.PageBean;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService{
	
	@Autowired
	private IWorkordermanageDao workordermanageDao;
	
	
	public void add(Workordermanage model) {
		workordermanageDao.add(model);
	}


	public void pageQuery(PageBean pageBean) {
		workordermanageDao.pageQuery(pageBean);
	}

}
