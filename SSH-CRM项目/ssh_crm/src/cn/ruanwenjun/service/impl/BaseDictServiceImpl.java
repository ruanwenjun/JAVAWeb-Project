package cn.ruanwenjun.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.ruanwenjun.dao.BaseDictDao;
import cn.ruanwenjun.domain.BaseDict;
import cn.ruanwenjun.service.BaseDictService;


@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=true)
public class BaseDictServiceImpl implements BaseDictService {
	private BaseDictDao bdd;
	
	public List<BaseDict> find(String dict_type_code) {
		
		return bdd.find(dict_type_code);
	}

	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}
	
}
