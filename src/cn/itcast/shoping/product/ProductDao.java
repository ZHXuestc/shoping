package cn.itcast.shoping.product;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shoping.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	/**
	 * ��ҳ��ѯ��ÿҳ��ʾ10��
	 * @return
	 */
	public List<Product> findHot() {
		//��ʽһ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * ��ҳ��ѯ��ÿҳ��ʾ10��
	 * @return
	 */
	public List<Product> findNew() {
		//��ʽ��
		//ʹ�ù�����PageHibernateCallback���з�ҳ��ѯ
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product order by pdate desc", null, 0, 10));
		return list;
	}
	//ͳ��ĳ�������µ���Ʒ������:
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		return list.get(0).intValue();
	}
	//ʹ�÷�ҳ�ķ�����ѯĳһ�����µ�������Ʒ��������
	public List<Product> findByPage(Integer cid, int begin, Integer limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid =?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<>(hql, new Object[]{cid}, begin, limit));
		return list;
	}
    //ͨ��csid��ѯ�ܼ�¼��
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		return list.get(0).intValue();
	}
    //ͨ��ĳһ���������csid��ѯ��Ӧ����Ʒ
	public List<Product> findByCsid(Integer csid,Integer begin,Integer limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<>(hql, new Object[]{csid}, begin, limit));
		return list;
	}

	//����pid��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		List<Product> list = this.getHibernateTemplate().find("from Product where pid=?", pid);
		//System.out.println("*************************"+list.get(0));
		return list.get(0);
	}

}
