package cn.ruanwenjun.domain;

//数据字典类
public class BaseDict {

	/**
	 * CREATE TABLE `base_dict` (
	  `dict_id` varchar(32) NOT NULL COMMENT '数据字典id(主键)',
	  `dict_type_code` varchar(10) NOT NULL COMMENT '数据字典类别代码',
	  `dict_type_name` varchar(64) NOT NULL COMMENT '数据字典类别名称',
	  `dict_item_name` varchar(64) NOT NULL COMMENT '数据字典项目名称',
	  `dict_item_code` varchar(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
	  `dict_sort` int(10) DEFAULT NULL COMMENT '排序字段',
	  `dict_enable` char(1) NOT NULL COMMENT '1:使用 0:停用',
	  `dict_memo` varchar(64) DEFAULT NULL COMMENT '备注',
	  	PRIMARY KEY (`dict_id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	private String dict_id;
	private String dict_type_code;  //类别代码
	private String dict_type_name;  //类别名称
	private String dict_item_name;  //项目名称
	private String dict_memo;       
	private String dict_item_code;
	private Integer dict_sort;
	private Character dict_enable;
	
	public String getDict_id() {
		return dict_id;
	}
	public void setDict_id(String dict_id) {
		this.dict_id = dict_id;
	}
	public String getDict_type_code() {
		return dict_type_code;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	public String getDict_type_name() {
		return dict_type_name;
	}
	public void setDict_type_name(String dict_type_name) {
		this.dict_type_name = dict_type_name;
	}
	public String getDict_item_name() {
		return dict_item_name;
	}
	public void setDict_item_name(String dict_item_name) {
		this.dict_item_name = dict_item_name;
	}
	public String getDict_memo() {
		return dict_memo;
	}
	public void setDict_memo(String dict_memo) {
		this.dict_memo = dict_memo;
	}
	public String getDict_item_code() {
		return dict_item_code;
	}
	public void setDict_item_code(String dict_item_code) {
		this.dict_item_code = dict_item_code;
	}
	public Integer getDict_sort() {
		return dict_sort;
	}
	public void setDict_sort(Integer dict_sort) {
		this.dict_sort = dict_sort;
	}
	public Character getDict_enable() {
		return dict_enable;
	}
	public void setDict_enable(Character dict_enable) {
		this.dict_enable = dict_enable;
	}
	
	
}
