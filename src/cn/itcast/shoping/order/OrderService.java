package cn.itcast.shoping.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoping.utils.PageBean;

@Transactional
public class OrderService {
	//注入orderDao
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public Integer save(Order order) {
		
		return orderDao.save(order);
	}
	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}
	public void update(Order currentOrder) {
		orderDao.update(currentOrder);
		
	}
	//分页查询用户的订单
	public PageBean<Order> findByPage(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setCurrentPage(page);
		//设置每页显示记录数
		int limit = 4;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount  = orderDao.findCountByUid(uid);
		//设置总页数
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		int begin = (page - 1)*limit;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//根据uid查询所有订单
		List<Order> list = orderDao.findByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

}
