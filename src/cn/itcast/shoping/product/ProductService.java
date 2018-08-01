package cn.itcast.shoping.product;

import java.util.List;

import cn.itcast.shoping.utils.PageBean;

public class ProductService {
	//注入ProductDao
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
	//使用分页的方式查询某以及分类下的所有商品
	public PageBean<Product> findByPage(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(page);
		Integer limit = 12;
		pageBean.setLimit(limit);
		//查询某一分类下的总记录数
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
		//查询某一分类下的多有商品,使用分页的方式
		int begin = (page - 1)*limit;
		List<Product> list = productDao.findByPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//查询某一二级分类下的所有商品
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(page);
		Integer limit = 4;
		pageBean.setLimit(limit);
		//查询某二分类下的总记录数
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
        //根据csid查询某一二级分类下的所有商品
        int begin = (page - 1)*limit;
        List<Product> list = productDao.findByCsid(csid,begin,limit);
        pageBean.setList(list);
        
		return pageBean;
	}
	public Product findByPid(Integer pid) {
		
		return productDao.findByPid(pid);
	}

}
