package cn.ruanwenjun.service;

import java.sql.SQLException;
import java.util.List;

import cn.ruanwenjun.dao.ProductDao;
import cn.ruanwenjun.domain.Category;
import cn.ruanwenjun.domain.Product;

public class ProductService {
	/**
	 * 找到热门商品
	 * @return
	 */
	public List<Product> findHotProduct() {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hotProductList;
	}
	/**
	 * 找到最新商品
	 * @return
	 */
	public List<Product> findNewProduct() {
		ProductDao dao = new ProductDao();
		List<Product> newProductList = null;
		try {
			newProductList = dao.findNewProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newProductList;
	}
	/**
	 * 找所有商品类别
	 * @return
	 */
	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	/**
	 * 根据商品类别cid找商品
	 * @param pid
	 * @return
	 */
	public List<Product> findProductByCid(String cid) {
		ProductDao dao = new ProductDao();
		List<Product> productList = null;
		try {
			productList = dao.findProductByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	/**
	 * 根据cid找到所有商品的数目
	 * @return
	 */
	public int findAllProductByCid(String cid) {
		ProductDao dao = new ProductDao();
		int totalCount = 0;
		try {
			totalCount = dao.findAllProductByCid(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}
	/**
	 * 分页查找
	 * @param index
	 * @param currentCount
	 * @return
	 */
	public List<Product> findPageBeanProductList(int index, int currentCount,String cid) {
		ProductDao dao = new ProductDao();
		List<Product> productList = null;
		try {
			productList = dao.findPageBeanProductList(index,currentCount,cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	/**
	 * 根据商品id找到商品
	 * @param pid
	 * @return
	 */
	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	/**
	 * 根据分类ID找分类
	 * @param cid
	 * @return
	 */
	public Category findCategoryByCid(String cid) {
		ProductDao dao = new ProductDao();
		Category category = null;
		try {
			category = dao.findCategoryByCid(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
}
