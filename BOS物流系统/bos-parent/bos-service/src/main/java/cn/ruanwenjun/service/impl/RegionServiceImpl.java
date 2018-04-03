package cn.ruanwenjun.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.IRegionDao;
import cn.ruanwenjun.domain.Region;
import cn.ruanwenjun.service.IRegionService;
import cn.ruanwenjun.utils.PageBean;


@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	@Autowired
	private IRegionDao regionDao;
	
	public void saveBatch(List<Region> regionList) {
		//遍历regionList，对每一个region都执行一次saveOrUpdata操作
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}
	
	@Transactional(readOnly=true)
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}
	
	@Transactional(readOnly=true)
	public List<Region> finaAllRegion(String q) {
		List<Region> regionList = null;
		if(StringUtils.isNoneBlank(q)) {
			//当Q部位空的时候调用模糊查询方法
			regionList = regionDao.findAllByQ(q);
		}else {
			//查询全部
			regionList = regionDao.findAll();
		}
		return regionList;
	}

}
