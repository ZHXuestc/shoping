package cn.itcast.shoping.categorysecond;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.shoping.category.Category;
import cn.itcast.shoping.product.Product;

public class CategorySecond {
	private Integer csid;
	private String csname;
	//二级分类和一级分类是多对一的关系
	private Category category;
	//二级分类和商品是一对多
	private Set<Product> product = new HashSet<Product>();
	
	public Set<Product> getProduct() {
		return product;
	}
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}

}
