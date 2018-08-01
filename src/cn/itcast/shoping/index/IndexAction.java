package cn.itcast.shoping.index;


import java.util.List;

import cn.itcast.shoping.category.Category;
import cn.itcast.shoping.category.CategoryService;
import cn.itcast.shoping.product.Product;
import cn.itcast.shoping.product.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	//ע��categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//ע��productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//��ҳ���ϻ�ȡlistHot
	private List<Product> hotList;
	public List<Product> getHotList() {
		return hotList;
	}
	//��ҳ���ϻ�ȡnewList
	private List<Product> newList;
	public List<Product> getNewList() {
		return newList;
	}

	public String execute(){
		//��ѯ���е�һ������,�ڽ�����ҳ��ʱ���Ҫ��һ����������ݽ�����ʾ
		List<Category> categoryList = categoryService.findAll();
		//��ѯ������Ʒ
		hotList = productService.findHot();
		//��ѯ������Ʒ
		newList = productService.findNew();
		//��categoryList���뵽session��
		//�ѵõ�categoryList���뵽session�оͿ��������е�ҳ���ϻ����Ӧ������
		//ActionContext(com.opensymphony.xwork.ActionContext)��Actionִ��ʱ��������,�����Ŀ��Կ�����һ������(��ʵ�����������������һ��Map����),
		//����ŵ���Action��ִ��ʱ��Ҫ�õ��Ķ���. ���̰߳�ȫ��;
		//ServletActionContext(com.opensymphony.webwork. ServletActionContext),
		//�����ֱ�Ӽ̳�������������ܵ�ActionContext,���ṩ��ֱ����Servlet��ض�����ʵĹ���;
		//������ѭ��ԭ����:���ActionContext�ܹ�ʵ�����ǵĹ���,����þͲ�Ҫʹ��ServletActionContext,
		//�����ǵ�Action������Ҫֱ��ȥ����Servlet����ض���.
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
		return "indexSuccess";
	}

}
