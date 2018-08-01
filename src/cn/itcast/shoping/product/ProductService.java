package cn.itcast.shoping.product;

import java.util.List;

import cn.itcast.shoping.utils.PageBean;

public class ProductService {
	//ע��ProductDao
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public List<Product> findHot() {
		
		return productDao.findHot();
	}
	public List<Product> findNew() {
	
		return productDao.findNew();
	}
	//ʹ�÷�ҳ�ķ�ʽ��ѯĳ�Լ������µ�������Ʒ
	public PageBean<Product> findByPage(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(page);
		Integer limit = 12;
		pageBean.setLimit(limit);
		//��ѯĳһ�����µ��ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount/limit;
		}else{
			totalPage = totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//��ѯĳһ�����µĶ�����Ʒ,ʹ�÷�ҳ�ķ�ʽ
		int begin = (page - 1)*limit;
		List<Product> list = productDao.findByPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//��ѯĳһ���������µ�������Ʒ
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(page);
		Integer limit = 4;
		pageBean.setLimit(limit);
		//��ѯĳ�������µ��ܼ�¼��
		int totalCount = 0;
        totalCount = productDao.findCountByCsid(csid);
        pageBean.setTotalCount(totalCount);
        int totalPage = 0;
        if(totalCount % limit == 0){
        	totalPage = totalCount/limit;
        }else{
        	totalPage = totalCount/limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        //����csid��ѯĳһ���������µ�������Ʒ
        int begin = (page - 1)*limit;
        List<Product> list = productDao.findByCsid(csid,begin,limit);
        pageBean.setList(list);
        
		return pageBean;
	}
	public Product findByPid(Integer pid) {
		
		return productDao.findByPid(pid);
	}

}
