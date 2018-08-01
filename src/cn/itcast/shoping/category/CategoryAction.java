package cn.itcast.shoping.category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	//ģ������
	private Category category = new Category();
	public Category getModel() {
		return category;
	}

	//ע��categoryService
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * ��̨��ѯ����һ������,ʹ�÷�ҳ��ѯ
	 */
	public String adminFindAll(){
		
		List<Category> cList = categoryService.findAll();
		//ѹջ
		ActionContext.getContext().getValueStack().set("cList",cList);
		
		return "adminFindAllSuccess";
	}
	/**
	 * ��̨���һ������
	 */
	public String add(){
		categoryService.save(category);
		
		return "addSuccess";
	}

	/**
	 * ��̨ȥ�޸�һ�������ҳ��
	 */
	public String edit(){
		//�ȸ���cid��ѯһ������,������,ע����Ե�ʱ����ֱ�Ӵ��ʵ�Ӧ�ô�category�л�ȡ�������޷�����
		category = categoryService.findByCid(category.getCid());
		
		return "editSuccess";
	}
	/**
	 * ��̨�޸�һ������
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
	/**
	 * ����cidɾ��һ������
	 * ���Ҫ����ɾ���Ļ�Ҫ�Ȳ���һ�²��ܼ���ɾ��
	 */
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	

}
