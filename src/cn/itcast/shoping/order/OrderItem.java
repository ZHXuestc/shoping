package cn.itcast.shoping.order;

import cn.itcast.shoping.product.Product;

public class OrderItem {
	private Integer itemid;
	private Double subtotal;//小计
	private Integer count;//数量
	//订单条目中应该包含商品的信息，订单条目和商品是一对多的关系，但是订单条目中是包含商品的，所以要按多对一进行配置
	private Product product;
	//订单项所属的订单,订单项和订单是多对一关系
	private Order order;
	
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
