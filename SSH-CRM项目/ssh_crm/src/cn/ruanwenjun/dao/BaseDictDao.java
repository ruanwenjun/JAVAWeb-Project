package cn.ruanwenjun.dao;

import java.util.List;

import cn.ruanwenjun.domain.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict> {

	List<BaseDict> find(String dict_type_code);

}
