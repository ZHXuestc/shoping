package cn.itcast.shoping.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shoping.product.Product;
import cn.itcast.shoping.product.ProductService;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//获取页面上的pid
	private Integer pid;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	//获取页面上的count
	private Integer count;
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * 创建购物车，并将其放在session域中,把购物车放入到session中，此方法封装了从session中获取购物车
	 * @return
	 */
	public Cart getCart(HttpServletRequest request){
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			// 将购物车对象放入到session范围
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
    //往购物车中添加条目
	public String addCart(){
		CartItem cartItem = new CartItem();
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		cartItem.setCount(count);
		
		//因为后面也要多次用到购物车，所以讲获取购物车封装成一个方法
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = this.getCart(request);
		cart.addCart(cartItem);
		return "addCartSuccess";
	}
	//根据pid删除条目
	public String deleteCartItem(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.removeCart(pid);
		return "deleteCartItemSuccess";
	}
	
	//清空购物车
	public String clearCart(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.clear();
		return "clearCartSuccess";
	}
	/**
	 * 我的购物车
	 * @return
	 */
	public String myCart(){
		
		return "myCartSuccess";
	}

}
