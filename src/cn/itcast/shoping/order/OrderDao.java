package cn.itcast.shoping.order;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shoping.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{

	public Integer save(Order order) {
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		//System.out.println("*********************" + oid);
		return oid;
	}

	public Order findByOid(Integer oid) {
		Order order = (Order) this.getHibernateTemplate().get(Order.class, oid);
		return order;
	}

	public void update(Order currentOrder) {
		this.getHibernateTemplate().update(currentOrder);
		
	}

	//根据uid查询订单
	public List<Order> findByUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<>(hql, new Object[] {uid}, begin, limit));
		return list;
	}

	//根据uid查询订单总记录数
	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order where uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		return list.get(0).intValue();
	}

}
