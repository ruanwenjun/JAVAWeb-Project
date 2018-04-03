package cn.ruanwenjun.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.IStaffDao;
import cn.ruanwenjun.domain.Staff;
import cn.ruanwenjun.service.IStaffService;
import cn.ruanwenjun.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
	@Autowired
	private IStaffDao dao;
	
	public void save(Staff model) {
		dao.add(model);
	}
	
	@Transactional(readOnly=true)
	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
	}
	
	public void deleteBatch(String ids) {
		String[] idList = ids.split(",");
		for (String id : idList) {
			dao.executeUptate("staff.deleteBatch", id);
		}
	}
	
	public void update(Staff model) {
		dao.update(model);
	}
	
	@Transactional(readOnly=true)
	public Staff findById(String id) {
		return dao.findById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Staff> findNotDeleteStaffList() {
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		dc.add(Restrictions.eq("deltag", "0"));
		return dao.findByCriteria(dc);
	}

}
