package cn.ruanwenjun.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ruanwenjun.domain.Region;
import cn.ruanwenjun.domain.Subarea;
import cn.ruanwenjun.service.ISubareaService;
import cn.ruanwenjun.web.action.base.BaseAction;

/***
 * 分区管理
 * @author RUANWENJUN
 *
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Autowired
	private ISubareaService subareaService;
	
	private File subareaFile;						//一键导入的文件名
	private String decizedzoneId;					//点击定区查询关联分区业务的定区ID
	public void setDecizedzoneId(String decizedzoneId) {
		this.decizedzoneId = decizedzoneId;
	}

	public void setSubareaFile(File subareaFile) {
		this.subareaFile = subareaFile;
	}

	/**
	 * 添加分区
	 * @return
	 */
	public String add() {
		subareaService.add(model);
		return SUCCESS;
	}
	
	/**
	 * 分区分页查询
	 * @return
	 */
	public String pageQuery() {
		
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		
		//添加关键字模糊查询
		String addresskey = model.getAddresskey();     //关键字
		if(StringUtils.isNoneBlank(addresskey)) {
			detachedCriteria.add(Restrictions.like("addresskey","%"+ addresskey+ "%"));
		}
		
		Region region = model.getRegion();
		//添加省市区的模糊查询
		if(region !=null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict(); 
			if(StringUtils.isNotBlank(province)||StringUtils.isNotBlank(city)||StringUtils.isNotBlank(district)) {
				//需要使用join查询,region是subarea里的属性，r是别名
				detachedCriteria.createAlias("region", "r");
				//添加模糊查询条件----region属性的province属性
				if(StringUtils.isNotBlank(province)){
					detachedCriteria.add(Restrictions.like("r.province", "%"+ province+ "%"));
				}
				//添加模糊查询条件----region属性的city属性
				if(StringUtils.isNotBlank(city)){
					detachedCriteria.add(Restrictions.like("r.city", "%"+ city+ "%"));
				}
				//添加模糊查询条件----region属性的district属性
				if(StringUtils.isNotBlank(district)){
					detachedCriteria.add(Restrictions.like("r.district", "%"+ district+ "%"));
				}
			}
		}
		//将离线查询对象封装到pageBean
		pageBean.setDetachedCriteria(detachedCriteria);
		
		//将pageBean传给service层
		subareaService.pageQuery(pageBean);
		//将pageBean转为JSON字符串格式写给前台页面，注意转JSON时的死循环问题,这里还要解除region懒加载，否则会出现死循环，转JSON的时候由于懒加载region并没有查询
		this.Object2JsonString(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * 分区导出
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		//查询所有分区
		List<Subarea> list = subareaService.findAll();
		
		//写入到文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("分区数据");         //创建表
		//写入标题行
		HSSFRow headRow = sheet.createRow(0);                    //标题行
		HSSFCell cell0 = headRow.createCell(0);
		HSSFCell cell1 = headRow.createCell(1);
		HSSFCell cell2 = headRow.createCell(2);
		HSSFCell cell3 = headRow.createCell(3);
		HSSFCell cell4 = headRow.createCell(4);
		HSSFCell cell5 = headRow.createCell(5);
		HSSFCell cell6 = headRow.createCell(6);
		HSSFCell cell7 = headRow.createCell(7);
		cell0.setCellValue("分区编号");
		cell1.setCellValue("所属省");
		cell2.setCellValue("所属市");
		cell3.setCellValue("所属区域");
		cell4.setCellValue("邮编");
		cell5.setCellValue("起始号");
		cell6.setCellValue("终止号");
		cell7.setCellValue("位置信息");
		//写入数据
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);  
			HSSFCell dcell0 = dataRow.createCell(0);
			HSSFCell dcell1 = dataRow.createCell(1);
			HSSFCell dcell2 = dataRow.createCell(2);
			HSSFCell dcell3 = dataRow.createCell(3);
			HSSFCell dcell4 = dataRow.createCell(4);
			HSSFCell dcell5 = dataRow.createCell(5);
			HSSFCell dcell6 = dataRow.createCell(6);
			HSSFCell dcell7 = dataRow.createCell(7);
			dcell0.setCellValue(subarea.getId());
			dcell1.setCellValue(subarea.getRegion().getProvince());
			dcell2.setCellValue(subarea.getRegion().getCity());
			dcell3.setCellValue(subarea.getRegion().getDistrict());
			dcell4.setCellValue(subarea.getRegion().getPostcode());
			dcell5.setCellValue(subarea.getStartnum());
			dcell6.setCellValue(subarea.getEndnum());
			dcell7.setCellValue(subarea.getPosition());
		}
		
		//设置头
		ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=exportSubarea.xls");
		ServletActionContext.getResponse().setContentType("application/vnd.ms-excel;charset=utf-8");
		//创建输出流
		ServletOutputStream stream = ServletActionContext.getResponse().getOutputStream();
		//导出
		workbook.write(stream);
		return NONE;
	}
	
	/**
	 * 一键导入分区数据
	 * @return
	 * @throws IOException 
	 */
	public String uploadXls() throws IOException {
		//创建输入流
		InputStream stream = new FileInputStream(subareaFile);
		List<Subarea> list = new ArrayList<Subarea>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(stream);
		//获得数据表
		HSSFSheet sheet = workbook.getSheet("Sheet1");
		//解析数据表
		for (Row row : sheet) {
			//第一行跳过
			if(row.getRowNum()==0) {
				continue;
			}
			Subarea subarea = new Subarea();
			String id = row.getCell(0).getStringCellValue();    //分区ID,由于主键设置为自增，则不设置主键
			String regionID = row.getCell(1).getStringCellValue();
			String addresskey = row.getCell(2).getStringCellValue();
			String startnum = row.getCell(3).getStringCellValue();
			String endnum = row.getCell(4).getStringCellValue();
			String single = row.getCell(5).getStringCellValue();
			String position = row.getCell(6).getStringCellValue();
			//封装subarea
			Region region = new Region();
			region.setId(regionID);
			subarea.setRegion(region);
			//subarea.setId(id);
			subarea.setAddresskey(addresskey);
			subarea.setStartnum(startnum);
			subarea.setEndnum(endnum);
			subarea.setSingle(single);
			subarea.setPosition(position);
			list.add(subarea);
		}
		subareaService.saveBatch(list);
		return SUCCESS;
	}
	
	/**
	 * 查找所有没有关联定区的分区，并封装成JSON字符串格式写给页面
	 * @return
	 */
	public String listByAjax() {
		
		List<Subarea> subareaList = subareaService.finaNoDecidedzoneList();
		//转化成JSON字符串并写给前排
		this.Object2JsonString(subareaList, new String[] {"startnum","endnum","single","decidedzone","region"});
		return NONE;
	}
	
	/**
	 * 根据指定定区ID查找关联的分区
	 * @return
	 */
	public String findAllByDecideczoneId() {
		List<Subarea> list = subareaService.findAllByDecideczoneId(decizedzoneId);
		this.Object2JsonString(list, new String[] {"decidedzone","subareas"});
		return NONE;
	}
	
	//查找各个区域的分区分布
	public String showHighChart() {
		List<Object> list = subareaService.findSubareaCountInRegion();
		this.Object2JsonString(list, null);
		return NONE;
	}
}
