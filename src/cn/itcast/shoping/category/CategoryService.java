package cn.itcast.shoping.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoping.utils.PageBean;

@Transactional
public class CategoryService {
	//ע��categoryDao
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}
	//���һ������
	public void save(Category category) {
		categoryDao.save(category);
		
	}
	//����cid��ѯ��Ӧ��һ������
	public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
	}
	//�޸�һ������
	public void update(Category category) {
		categoryDao.update(category);
	}
	//����cidɾ��һ������
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
	

}
