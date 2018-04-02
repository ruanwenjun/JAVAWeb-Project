package cn.ruanwenjun.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;

import cn.ruanwenjun.domain.Category;
import cn.ruanwenjun.domain.Product;
import cn.ruanwenjun.service.AdminService;
/**
 * 添加商品的servlet
 * @author RUANWENJUN
 *
 */
public class AdminAddProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1.创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2.获得核心类
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding("UTF-8");
			//3.解析request
			List<FileItem> parseRequest = fileUpload.parseRequest(request);
			Map<String,Object> map = new HashMap<String,Object>();
			String realPath = this.getServletContext().getRealPath("upload");
			//4.遍历文件项
			for (FileItem fileItem : parseRequest) {
				if(fileItem.isFormField()) {
					//表示是普通表单项
					String fieldName = fileItem.getFieldName();     //获得表单项名字
					String string = fileItem.getString("UTF-8");  //获得表单项值
					map.put(fieldName, string);
				}else {
					//表示是文件项
					String name = fileItem.getName();                        //获得文件名字
					InputStream in = fileItem.getInputStream();
					OutputStream out = new FileOutputStream(realPath+"/"+name);
					IOUtils.copy(in, out);
					in.close();
					out.close();
					fileItem.delete();
					map.put("pimage", "upload/"+name);
				}
			}
			
			// 手动封装pid,pdate,pflag
			Category category = new Category();
			Product product = new Product();
			BeanUtils.populate(product, map);
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			product.setPid(UUID.randomUUID().toString());
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			product.setPdate(formate.format(new Date()));
			product.setPflag(0);
			AdminService service = new AdminService();
			service.addProduct(product);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
