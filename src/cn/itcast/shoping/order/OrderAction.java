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
	private String pd_FrpId;//����֧��ͨ������
	//����ɹ�����Ҫ�Ĳ���
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
	
	//ע��orderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//��ҳ���ϻ�ȡorder
	private Order order;
	public Order getOrder() {
		return order;
	}
	//��װ����order
	public void setOrder(Order order) {
		this.order = order;
	}
	//����ҳ���ϵ�page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//����ҳ���ϵ�uid
	private Integer uid;
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	//��ҳ������ʾpageBean
	private PageBean<Order> pageBean;
	public PageBean<Order> getPageBean() {
		return pageBean;
	}
	//��ҳ���ϻ�ȡoid
	private Integer oid;
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	
	
	//���涩�������ݿ�
	public String saveOrder(){
		order = new Order();
		order.setOrdertime(new Date());
		//���ö���״̬
		order.setState(1);//1.����Ϊ���� 2.�����Ѹ��� 3.�����ѷ��� 4.������ǩ��
		//��session�еõ��û���Ϣ
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userExit");
        if(user == null){
        	this.addActionMessage("�㻹û�е�¼�����ȵ�¼");
        	return "msg";
        }
        order.setUser(user);
        //��session�л�ȡ���ﳵ
        Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        if(cart == null){
        	this.addActionMessage("����û�й������ȥ����");
			return "msg";
        }
        //�����ܼ�
    	order.setTotal(cart.getTotal());
    	//�ӳ��л�ȡ��Ŀ�ļ���
    	Collection<CartItem> cartItems = cart.getCartItems();
    	//�Լ��Ͻ��б�����������Ŀ�е���Ϣ���õ�orderItem��
    	for(CartItem cartItem:cartItems){
    		//����orderItem���������涩����Ϣ
        	OrderItem orderItem = new OrderItem();//����Ĵ�������Ҫ��forѭ���У�����ֻ�ܱ���һ��orderItem����
    		orderItem.setCount(cartItem.getCount());
    		orderItem.setSubtotal(cartItem.getSubtotal());
    		orderItem.setProduct(cartItem.getProduct());
    		//���������������order���������װ��order������������˶�������Ϣ
        	orderItem.setOrder(order);
        	//��������������,��ʱ�����о����˶����Ҳ�����˶������������Ϣ
        	order.getOrderItems().add(orderItem);
    	}
        //���涩����ͬʱ���ض�����oid�������ö�����oid
        Integer oid = orderService.save(order);
        cart.clear();
		return "saveOrderSuccess";
	}

	/**
	 * ֧�������ķ���
	 * @throws IOException 
	 **/
	public String payOrder() throws IOException{
		//���±�֮ǰ��Ҫͨ��oid��ѯ���������ύ��ʱ���ҳ������order������ݴ��ݹ��������õ�currOrder��
		Order currentOrder = orderService.findByOid(order.getOid());
		currentOrder.setAddr(order.getAddr());
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
		orderService.update(currentOrder);
		
		//ʹ���ױ�֧����Ҫ�ĵĲ���
		// ���帶��Ĳ���:
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
	 * ����ɹ���Ļص�����
	 */
	public String callBack(){
		//ͨ��������Ų�ѯ����
		Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		//�޸Ķ���״̬
		currentOrder.setState(2);
		orderService.update(currentOrder);
		this.addActionMessage("��������ɹ�!������:"+r6_Order+" ������:"+r3_Amt);
		
		return "msg";
	}
	
	/**
	 * ��ѯ�ҵĶ���
	 * ͨ��uid��ѯ���û��µ����ж���
	 */
	public String myOrder(){
		pageBean = orderService.findByPage(uid,page);
		return "myOrderSuccess";
	}
	
	/**
	 * Ϊ����Ķ����ṩ����ķ���
	 */
	public String findByOid(){
		//��ҳ���Ͻ��л��ԣ��ǲ���д��Order order = ,Ҫ��Order����ȥ��
		order = orderService.findByOid(oid);
		return "findByOidSuccess";
	}
	
	
}
