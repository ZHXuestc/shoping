package cn.itcast.shoping.cart;

import cn.itcast.shoping.product.Product;

/**
 * 订单对象，购物项
 * @author Administrator
 *
 */
public class CartItem {
	//商品对象
	private Product product;
	//商品数量
	private Integer count; 
	//商品小计
	private Double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	//小计不需要外部手动设置，没有set方法就不能手动设置。只要调用get方法,
	//或者一获取小计这个属性，这个值就已经计算好了
	public Double getSubtotal() {
		return count*product.getShop_price();
	}
	

}
