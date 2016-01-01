package hames.view;

import hames.bean.Order;
import hames.bean.Payment;
import hames.bean.exception.OrderException;
import hames.bean.exception.ValidationException;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.enums.OrderStatusEnum;
import hames.service.CustomerService;
import hames.service.OrderService;

import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderView.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	
	
	@Override
	public String getTitleDefinition(Model model) {
		return "order";
	}
	
	
	@RequestMapping("/orderview")
	public String view(Model model, @RequestParam(value="id",required=false) Long id){
		
		model.addAttribute("menu", "order");
		
		Order order = null;
		if(id == null || id == 0){
			if(!model.containsAttribute("order")){
				order = new Order();
				order.setOrderDate(new DateTime());
				order.setOrderStatus(OrderStatusEnum.DRAFT.getValue());
				
				//Adding Payment to Order
				Payment payment = new Payment();
				payment.setPaymentDate(new DateTime());
				order.addPayments(payment);
				
				model.addAttribute("order", order);
			}
		}else{
			order = orderService.findOne(id);
			model.addAttribute("order", order);
		}
		
		model.addAttribute("customers", customerService.findAll());
		return getTitleDefinition(model);
	}

	@RequestMapping("/ordersave")
	public String save(Model model,@ModelAttribute Order order){
		
		try{
			orderService.processOrder(order);
			ModelUtil.addSuccess("Order created successfully");	
		}catch(HibernateException e){
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}catch (ValidationException e) {
			logger.error("Validation errors are present");
		}catch (OrderException e) {
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}
		
		return view(model,null);
	}
}
