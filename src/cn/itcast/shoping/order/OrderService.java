package cn.itcast.shoping.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shoping.utils.PageBean;

@Transactional
public class OrderService {
	//ע��orderDao
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
	//��ҳ��ѯ�û��Ķ���
	public PageBean<Order> findByPage(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//���õ�ǰҳ
		pageBean.setCurrentPage(page);
		//����ÿҳ��ʾ��¼��
		int limit = 4;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount  = orderDao.findCountByUid(uid);
		//������ҳ��
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		int begin = (page - 1)*limit;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//����uid��ѯ���ж���
		List<Order> list = orderDao.findByUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

}
