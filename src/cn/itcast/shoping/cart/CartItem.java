package cn.itcast.shoping.cart;

import cn.itcast.shoping.product.Product;

/**
 * �������󣬹�����
 * @author Administrator
 *
 */
public class CartItem {
	//��Ʒ����
	private Product product;
	//��Ʒ����
	private Integer count; 
	//��ƷС��
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
	//С�Ʋ���Ҫ�ⲿ�ֶ����ã�û��set�����Ͳ����ֶ����á�ֻҪ����get����,
	//����һ��ȡС��������ԣ����ֵ���Ѿ��������
	public Double getSubtotal() {
		return count*product.getShop_price();
	}
	

}
