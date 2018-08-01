package cn.itcast.shoping.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shoping.utils.PageHibernateCallback;

public class CategoryDao extends HibernateDaoSupport{

	/**
	 * Dao���ѯ���е�һ������
	 * @return
	 */
	public List<Category> findAll() {
		List<Category> list = this.getHibernateTemplate().find("from Category");
		return list;
	}

	/**
	 * dao�����һ������
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
		
	}

	/**
	 * dao��ѯcid��Ӧ��һ������
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
	 * dao���޸�һ������
	 * @param category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
		
	}

	/**
	 * dao��ɾ��һ������
	 * @param cid
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
		
	}


}
