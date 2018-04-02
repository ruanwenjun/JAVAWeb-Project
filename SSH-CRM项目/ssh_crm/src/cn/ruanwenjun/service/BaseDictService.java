package cn.ruanwenjun.service;

import java.util.List;

import cn.ruanwenjun.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> find(String dict_type_code);

}
