package cn.ruanwenjun.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Data;

import cn.ruanwenjun.crm.ICustomerService;
import cn.ruanwenjun.dao.IDecidedzoneDao;
import cn.ruanwenjun.dao.INoticebillDao;
import cn.ruanwenjun.dao.IWorkbillDao;
import cn.ruanwenjun.domain.Decidedzone;
import cn.ruanwenjun.domain.Noticebill;
import cn.ruanwenjun.domain.Staff;
import cn.ruanwenjun.domain.Workbill;
import cn.ruanwenjun.service.INoticebillService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

	
	@Autowired
	private INoticebillDao noticebillDao;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IWorkbillDao workbillDao;
	
	public void add(Noticebill model) {
		//先根据地址查询定区
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(model.getPickaddress());
		if(decidedzoneId !=null ) {
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);	
			if(decidedzone !=null) {
				Staff staff = decidedzone.getStaff();
				if(staff !=null) {
					model.setStaff(staff);
					//则可以自动分单
					model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
					Workbill workbill = new Workbill();
					workbill.setNoticebill(model);
					workbill.setStaff(staff);
					workbill.setType(Workbill.TYPE_1);
					workbill.setPickstate(Workbill.PICKSTATE_NO);
					workbill.setBuildtime(new Timestamp(new Date().getTime()));
					workbill.setAttachbilltimes(0);
					workbill.setRemark(model.getRemark());
					//保存工单
					workbillDao.add(workbill);
				}else {
					model.setOrdertype(Noticebill.ORDERTYPE_MAN);
				}
			}else {
				model.setOrdertype(Noticebill.ORDERTYPE_MAN);
			}
		}else {
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
		noticebillDao.add(model);
	}

}
