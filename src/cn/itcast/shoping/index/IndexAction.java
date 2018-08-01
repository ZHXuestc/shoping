package cn.itcast.shoping.index;


import java.util.List;

import cn.itcast.shoping.category.Category;
import cn.itcast.shoping.category.CategoryService;
import cn.itcast.shoping.product.Product;
import cn.itcast.shoping.product.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	//注入categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//在页面上获取listHot
	private List<Product> hotList;
	public List<Product> getHotList() {
		return hotList;
	}
	//在页面上获取newList
	private List<Product> newList;
	public List<Product> getNewList() {
		return newList;
	}

	public String execute(){
		//查询所有的一级分类,在进入首页的时候就要把一级分类的数据进行显示
		List<Category> categoryList = categoryService.findAll();
		//查询热门商品
		hotList = productService.findHot();
		//查询最新商品
		newList = productService.findNew();
		//将categoryList放入到session中
		//把得到categoryList放入到session中就可以在所有的页面上获得相应的数据
		//ActionContext(com.opensymphony.xwork.ActionContext)是Action执行时的上下文,上下文可以看作是一个容器(其实我们这里的容器就是一个Map而已),
		//它存放的是Action在执行时需要用到的对象. 是线程安全的;
		//ServletActionContext(com.opensymphony.webwork. ServletActionContext),
		//这个类直接继承了我们上面介绍的ActionContext,它提供了直接与Servlet相关对象访问的功能;
		//我们遵循的原则是:如果ActionContext能够实现我们的功能,那最好就不要使用ServletActionContext,
		//让我们的Action尽量不要直接去访问Servlet的相关对象.
		ActionContext.getContext().getSession().put("categoryList", categoryList);
		//ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
		return "indexSuccess";
	}

}
