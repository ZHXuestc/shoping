package cn.itcast.shoping.order;

import cn.itcast.shoping.product.Product;

public class OrderItem {
	private Integer itemid;
	private Double subtotal;//С��
	private Integer count;//����
	//������Ŀ��Ӧ�ð�����Ʒ����Ϣ��������Ŀ����Ʒ��һ�Զ�Ĺ�ϵ�����Ƕ�����Ŀ���ǰ�����Ʒ�ģ�����Ҫ�����һ��������
	private Product product;
	//�����������Ķ���,������Ͷ����Ƕ��һ��ϵ
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
