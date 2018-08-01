package cn.itcast.shoping.product;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shoping.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	/**
	 * 分页查询，每页显示10个
	 * @return
	 */
	public List<Product> findHot() {
		//方式一
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * 分页查询，每页显示10个
	 * @return
	 */
	public List<Product> findNew() {
		//方式二
		//使用工具类PageHibernateCallback进行分页查询
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product order by pdate desc", null, 0, 10));
		return list;
	}
	//统计某个分类下的商品的总数:
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		return list.get(0).intValue();
	}
	//使用分页的方法查询某一分类下的所有商品，并返回
	public List<Product> findByPage(Integer cid, int begin, Integer limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid =?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<>(hql, new Object[]{cid}, begin, limit));
		return list;
	}
    //通过csid查询总记录数
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		return list.get(0).intValue();
	}
    //通过某一二级分类的csid查询对应的商品
	public List<Product> findByCsid(Integer csid,Integer begin,Integer limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<>(hql, new Object[]{csid}, begin, limit));
		return list;
	}

	//根据pid查询商品
	public Product findByPid(Integer pid) {
		List<Product> list = this.getHibernateTemplate().find("from Product where pid=?", pid);
		//System.out.println("*************************"+list.get(0));
		return list.get(0);
	}

}
