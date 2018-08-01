package cn.itcast.shoping.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shoping.utils.PageHibernateCallback;

public class CategoryDao extends HibernateDaoSupport{

	/**
	 * Dao层查询所有的一级分类
	 * @return
	 */
	public List<Category> findAll() {
		List<Category> list = this.getHibernateTemplate().find("from Category");
		return list;
	}

	/**
	 * dao层添加一级分类
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
		
	}

	/**
	 * dao查询cid对应的一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		List<Category> list = this.getHibernateTemplate().find("from Category where cid=?",cid);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * dao层修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
		
	}

	/**
	 * dao层删除一级分类
	 * @param cid
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
		
	}


}
