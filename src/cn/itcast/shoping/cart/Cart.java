package cn.itcast.shoping.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	// 购物车存放多个购物项:
	// Map集合用商品的ID作为Map的key , 购物项作为Map的value
	private Map<Integer,CartItem> map = new HashMap<Integer,CartItem>();
	//提供一个map的value值的集合
	// 相当于有一个属性:cartItems,在页面中可以直接通过Cart得到cartItems中数据
	public Collection<CartItem> getCartItems(){
		return map.values();//获取map中所有的value值CartItem对象
	}
	//总计
	private Double total = 0d;
	public Double getTotal() {//get方法是可以在页面上获取
		return total;
	}
	//为购物车提供三个方法
    //1.将购物项添加到购物车:
	public void addCart(CartItem cartItem){
		int pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			//查询到购物项中已经了pid对应的购物项，要在原来的基础上将该商品的数量和小计进行增加
			//先拿到购物项中已有的条目
			CartItem _cartItem = map.get(pid);
			//然后给这个购物项的数量重新赋值 cartItem是新订单条目，_cartItem原来就有的订单条目
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal(); 
	}
	//2.删除购物车中的条目
	public void removeCart(Integer pid){
		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	//3.清空购物车
	public void clear(){
		map.clear();
		total = 0d;
	}

}
