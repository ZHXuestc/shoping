package cn.itcast.shoping.category;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.shoping.categorysecond.CategorySecond;

public class Category {
	private Integer cid;
	private String cname;
	//一级分类中包含二级分类，一对多
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

}
