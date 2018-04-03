package cn.ruanwenjun.dao;

import java.util.List;

import cn.ruanwenjun.dao.base.IBaseDao;
import cn.ruanwenjun.domain.Function;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年3月30日 下午3:37:15
*/
public interface IFunctionDao extends IBaseDao<Function> {

	public List<Function> findAllMenu();

	public List<Function> findFunctionByUserID(String id);

	public List<Function> findAuthorizationByUserID(String id);

	public List<Function> findAllFirstFunction();

}
