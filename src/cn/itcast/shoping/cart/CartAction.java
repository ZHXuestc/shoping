package cn.itcast.shoping.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shoping.product.Product;
import cn.itcast.shoping.product.ProductService;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	//ע��productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//��ȡҳ���ϵ�pid
	private Integer pid;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	//��ȡҳ���ϵ�count
	private Integer count;
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * �������ﳵ�����������session����,�ѹ��ﳵ���뵽session�У��˷�����װ�˴�session�л�ȡ���ﳵ
	 * @return
	 */
	public Cart getCart(HttpServletRequest request){
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			// �����ﳵ������뵽session��Χ
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
    //�����ﳵ�������Ŀ
	public String addCart(){
		CartItem cartItem = new CartItem();
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		cartItem.setCount(count);
		
		//��Ϊ����ҲҪ����õ����ﳵ�����Խ���ȡ���ﳵ��װ��һ������
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = this.getCart(request);
		cart.addCart(cartItem);
		return "addCartSuccess";
	}
	//����pidɾ����Ŀ
	public String deleteCartItem(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.removeCart(pid);
		return "deleteCartItemSuccess";
	}
	
	//��չ��ﳵ
	public String clearCart(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Cart cart = getCart(request);
		cart.clear();
		return "clearCartSuccess";
	}
	/**
	 * �ҵĹ��ﳵ
	 * @return
	 */
	public String myCart(){
		
		return "myCartSuccess";
	}

}
