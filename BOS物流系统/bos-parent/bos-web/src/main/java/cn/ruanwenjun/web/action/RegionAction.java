package cn.ruanwenjun.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.ruanwenjun.domain.Region;
import cn.ruanwenjun.service.IRegionService;
import cn.ruanwenjun.utils.PageBean;
import cn.ruanwenjun.utils.PinYin4jUtils;
import cn.ruanwenjun.web.action.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 区域管理
 * @author RUANWENJUN
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	@Autowired
	private IRegionService regionService;

	//属性驱动获得文件
	private File regionFile;
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	//前台传来的参数---->>选择区域中的输入内容
	private String q;
	public void setQ(String q) {
		this.q = q;
	}

	/**
	 * 区域文件XLS一键导入
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String XlsUpload() throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		//获得表
		HSSFSheet sheet = workbook.getSheet("Sheet1");
		List<Region> regionList = new ArrayList<Region>();
		//遍历表获得每一行
		for (Row row : sheet) {
			//舍弃第一行
			if(row.getRowNum() == 0) {
				continue;
			}else {
				//封装数据
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city = row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				Region  region = new Region(id, province, city, district, postcode, null, null, null);
				
				//生成shortcode和citycode
				province = province.substring(0, province.length()-1);
				city = city.substring(0, city.length()-1);
				district = district.substring(0, district.length()-1);
				String[] shortcode = PinYin4jUtils.getHeadByString(province+city+district);
				String join = StringUtils.join(shortcode, "");
				String citycode = PinYin4jUtils.hanziToPinyin(city,"");
				//封装shortcode和citycode
				region.setShortcode(join);
				region.setCitycode(citycode);
				
				regionList.add(region);
			}
		}
		//将封装后的regionList传给service层进行存储
		regionService.saveBatch(regionList);
		return NONE;
	}
	
	/**
	 * 区域分页查找
	 * @return
	 */
	public String pageQuery(){
		
		regionService.pageQuery(pageBean);
		//如果不去除subareas则会出现死循环报错
		this.Object2JsonString(pageBean,new String[]{"currentPage,pageSize,detachedCriteria","subareas"} );
		return NONE;
	}
	/**
	 * 选择区域，用JSON字符串格式传给前台
	 * @return
	 * @throws IOException 
	 */
	public String findRegionAjax() throws IOException {
		List<Region> list = regionService.finaAllRegion(q);
		
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"province","city","district","postcode","shortcode","citycode","subareas"});
		//json格式为{id: ,name: }
		String json = JSONArray.fromObject(list, config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8 ");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}
	
}
