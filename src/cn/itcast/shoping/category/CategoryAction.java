package cn.itcast.shoping.category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	//模型驱动
	private Category category = new Category();
	public Category getModel() {
		return category;
	}

	//注入categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * 后台查询所有一级分类,使用分页查询
	 */
	public String adminFindAll(){
		
		List<Category> cList = categoryService.findAll();
		//压栈
		ActionContext.getContext().getValueStack().set("cList",cList);
		
		return "adminFindAllSuccess";
	}
	/**
	 * 后台添加一级分类
	 */
	public String add(){
		categoryService.save(category);
		
		return "addSuccess";
	}

	/**
	 * 后台去修改一级分类的页面
	 */
	public String edit(){
		//先根据cid查询一级分类,并回显,注意回显的时候不能直接传词典应该从category中获取，或者无法回显
		category = categoryService.findByCid(category.getCid());
		
		return "editSuccess";
	}
	/**
	 * 后台修改一级分类
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	/**
	 * 根据cid删除一级分类
	 * 如果要级联删除的话要先查找一下才能级联删除
	 */
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	

}
