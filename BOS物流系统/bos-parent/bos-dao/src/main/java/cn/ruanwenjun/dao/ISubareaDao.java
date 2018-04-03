package cn.ruanwenjun.dao;

import java.util.List;

import cn.ruanwenjun.dao.base.IBaseDao;
import cn.ruanwenjun.domain.Subarea;

public interface ISubareaDao extends IBaseDao<Subarea> {

	public List<Object> findSubareaCountInRegion();

}
