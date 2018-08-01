package cn.itcast.shoping.order;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shoping.cart.Cart;
import cn.itcast.shoping.cart.CartItem;
import cn.itcast.shoping.user.User;
import cn.itcast.shoping.utils.PageBean;
import cn.itcast.shoping.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport{
	private String pd_FrpId;//接收支付通道编码
	//付款成功后需要的参数
	private String r3_Amt;
	private String r6_Order;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	
	//注入orderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//在页面上获取order
	private Order order;
	public Order getOrder() {
		return order;
	}
	//封装属性order
	public void setOrder(Order order) {
		this.order = order;
	}
	//接收页面上的page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//接收页面上的uid
	private Integer uid;
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	//在页面上显示pageBean
	private PageBean<Order> pageBean;
	public PageBean<Order> getPageBean() {
		return pageBean;
	}
	//从页面上获取oid
	private Integer oid;
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	
	//保存订单到数据库
	public String saveOrder(){
		order = new Order();
		order.setOrdertime(new Date());
		//设置订单状态
		order.setState(1);//1.代表为付款 2.代表已付款 3.代表已发货 4.代表已签收
		//从session中得到用户信息
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userExit");
        if(user == null){
        	this.addActionMessage("你还没有登录，请先登录");
        	return "msg";
        }
        order.setUser(user);
        //从session中获取购物车
        Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        if(cart == null){
        	this.addActionMessage("您还没有购物，请先去购物");
			return "msg";
        }
        //设置总价
    	order.setTotal(cart.getTotal());
    	//从车中获取条目的集合
    	Collection<CartItem> cartItems = cart.getCartItems();
    	//对集合进行遍历，并把条目中的信息设置到orderItem中
    	for(CartItem cartItem:cartItems){
    		//创建orderItem对象用来存订单信息
        	OrderItem orderItem = new OrderItem();//对象的创建必须要在for循环中，否则只能保存一个orderItem对象
    		orderItem.setCount(cartItem.getCount());
    		orderItem.setSubtotal(cartItem.getSubtotal());
    		orderItem.setProduct(cartItem.getProduct());
    		//订单项关联订单，order就是上面封装的order，订单项就有了订单的信息
        	orderItem.setOrder(order);
        	//订单关联订单项,此时订单中就有了订单项，也就有了订单向里面的信息
        	order.getOrderItems().add(orderItem);
    	}
        //保存订单的同时返回订单的oid用于设置订单的oid
        Integer oid = orderService.save(order);
        cart.clear();
		return "saveOrderSuccess";
	}

	/**
	 * 支付订单的方法
	 * @throws IOException 
	 **/
	public String payOrder() throws IOException{
		//跟新表之前先要通过oid查询订单表，表单提交的时候把页面中与order相关数据传递过来，设置到currOrder中
		Order currentOrder = orderService.findByOid(order.getOid());
		currentOrder.setAddr(order.getAddr());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		orderService.update(currentOrder);
		
		//使用易宝支付需要的的参数
		// 定义付款的参数:
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://113.54.230.33:8080/shoping/order_callBack.action";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,pd_FrpId, pr_NeedResponse, keyValue);
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		System.out.println(sb.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.sendRedirect(sb.toString());
    	return NONE;
		
	}
	/**
	 * 付款成功后的回调方法
	 */
	public String callBack(){
		//通过订单编号查询订单
		Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		//修改订单状态
		currentOrder.setState(2);
		orderService.update(currentOrder);
		this.addActionMessage("订单付款成功!订单号:"+r6_Order+" 付款金额:"+r3_Amt);
		
		return "msg";
	}
	
	/**
	 * 查询我的订单
	 * 通过uid查询该用户下的所有订单
	 */
	public String myOrder(){
		pageBean = orderService.findByPage(uid,page);
		return "myOrderSuccess";
	}
	
	/**
	 * 为付款的订单提供付款的方法
	 */
	public String findByOid(){
		//在页面上进行回显，是不能写成Order order = ,要把Order类型去掉
		order = orderService.findByOid(oid);
		return "findByOidSuccess";
	}
	
	
}
