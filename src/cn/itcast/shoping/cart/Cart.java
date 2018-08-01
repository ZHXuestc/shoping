package cn.itcast.shoping.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	// ���ﳵ��Ŷ��������:
	// Map��������Ʒ��ID��ΪMap��key , ��������ΪMap��value
	private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();
	//�ṩһ��map��valueֵ�ļ���
	// �൱����һ������:cartItems,��ҳ���п���ֱ��ͨ��Cart�õ�cartItems������
	public Collection<CartItem> getCartItems(){
		return map.values();//��ȡmap�����е�valueֵCartItem����
	}
	//�ܼ�
	private Double total = 0d;
	public Double getTotal() {//get�����ǿ�����ҳ���ϻ�ȡ
		return total;
	}
	//Ϊ���ﳵ�ṩ��������
    //1.����������ӵ����ﳵ:
	public void addCart(CartItem cartItem){
		int pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//��ѯ�����������Ѿ���pid��Ӧ�Ĺ����Ҫ��ԭ���Ļ����Ͻ�����Ʒ��������С�ƽ�������
			//���õ������������е���Ŀ
			CartItem _cartItem = map.get(pid);
			//Ȼ��������������������¸�ֵ cartItem���¶�����Ŀ��_cartItemԭ�����еĶ�����Ŀ
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal(); 
	}
	//2.ɾ�����ﳵ�е���Ŀ
	public void removeCart(Integer pid){
		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//3.��չ��ﳵ
	public void clear(){
		map.clear();
		total = 0d;
	}

}
