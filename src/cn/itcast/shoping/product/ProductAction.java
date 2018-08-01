package cn.itcast.shoping.product;

import java.util.List;

import cn.itcast.shoping.category.Category;
import cn.itcast.shoping.category.CategoryService;
import cn.itcast.shoping.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	
	//模型驱动
	private Product product = new Product();
	public Product getModel() {
		return product;
	}

	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//注入categoryService
	public CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//获取页面上的cid
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//在页面上获取cid用于分页中下一页中得到cid
	public Integer getCid() {
		return cid;
	}
	//获取页面上的page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//在页面上获取pageBean
	private PageBean<Product> pageBean;
	public PageBean<Product> getPageBean() {
		return pageBean;
	}
	//获取页面上的csid
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//在页面上获取csid用于分页中下一页中得到csid
	public Integer getCsid() {
		return csid;
	}
	
	//通过一级分类的cid查询查询一级分类下的所有二级分类
	public String findByCid(){
		List<Category> categoryList = categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
	    pageBean = productService.findByPage(cid,page);
		return "findByCidSuccess";
		
	}
	//通过二级分类查询某一二级分类下的所有商品
	public String findByCsid(){
		List<Category> categoryList = categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
	    pageBean = productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}
	//通过pid查询单个商品信息
	public String findByPid(){
		List<Category> categoryList = categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		product = productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	
}
