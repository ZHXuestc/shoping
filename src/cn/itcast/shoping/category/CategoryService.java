package cn.itcast.shoping.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoping.utils.PageBean;

@Transactional
public class CategoryService {
	//注入categoryDao
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}
	//添加一级分类
	public void save(Category category) {
		categoryDao.save(category);
		
	}
	//根据cid查询对应的一级分类
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
	}
	//修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
	}
	//根据cid删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
	

}
