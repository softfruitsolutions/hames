package hames.view;

import hames.bean.Order;
import hames.bean.Payment;
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
import org.springframework.validation.BindingResult;
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
		ModelUtil.addMessages(model);
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
				order.addPayments(new Payment());
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
	public String save(Model model,@ModelAttribute Order order,BindingResult result){
		
		logger.debug("Saving Order : {} ",order.toString());
		orderService.validate(result, order);
		if(result.hasErrors()){
			return view(model,order.getOrderId());
		}
		
		try{
			orderService.save(order);
			logger.debug("Order created");
			ModelUtil.addSuccess("Order created successfully");	
		}catch(HibernateException e){
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}
		
		ModelUtil.addMessages(model);
		return view(model,null);
	}
}
