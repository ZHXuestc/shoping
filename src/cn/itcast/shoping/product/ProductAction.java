package cn.itcast.shoping.product;

import java.util.List;

import cn.itcast.shoping.category.Category;
import cn.itcast.shoping.category.CategoryService;
import cn.itcast.shoping.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	
	//ģ������
	private Product product = new Product();
	public Product getModel() {
		return product;
	}

	//ע��productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//ע��categoryService
	public CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//��ȡҳ���ϵ�cid
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//��ҳ���ϻ�ȡcid���ڷ�ҳ����һҳ�еõ�cid
	public Integer getCid() {
		return cid;
	}
	//��ȡҳ���ϵ�page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//��ҳ���ϻ�ȡpageBean
	private PageBean<Product> pageBean;
	public PageBean<Product> getPageBean() {
		return pageBean;
	}
	//��ȡҳ���ϵ�csid
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	//��ҳ���ϻ�ȡcsid���ڷ�ҳ����һҳ�еõ�csid
	public Integer getCsid() {
		return csid;
	}
	
	//ͨ��һ�������cid��ѯ��ѯһ�������µ����ж�������
	public String findByCid(){
		List<Category> categoryList = categoryService.findAll();
		//ѹջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
	    pageBean = productService.findByPage(cid,page);
		return "findByCidSuccess";
		
	}
	//ͨ�����������ѯĳһ���������µ�������Ʒ
	public String findByCsid(){
		List<Category> categoryList = categoryService.findAll();
		//ѹջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
	    pageBean = productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}
	//ͨ��pid��ѯ������Ʒ��Ϣ
	public String findByPid(){
		List<Category> categoryList = categoryService.findAll();
		//ѹջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		product = productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	
}
