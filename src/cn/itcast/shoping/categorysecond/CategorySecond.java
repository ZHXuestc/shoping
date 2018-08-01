package cn.itcast.shoping.categorysecond;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.shoping.category.Category;
import cn.itcast.shoping.product.Product;

public class CategorySecond {
	private Integer csid;
	private String csname;
	//���������һ�������Ƕ��һ�Ĺ�ϵ
	private Category category;
	//�����������Ʒ��һ�Զ�
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
