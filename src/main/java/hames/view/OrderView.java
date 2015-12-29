package hames.view;

import hames.bean.Order;
import hames.bean.Payment;
import hames.bean.exception.ValidationException;
import hames.core.bean.ModelUtil;
import hames.core.system.ReportEngine;
import hames.core.view.AbstractView;
import hames.enums.OrderStatusEnum;
import hames.service.CustomerService;
import hames.service.OrderService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

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

/*import antlr.collections.List;*/

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
		
		try{
			orderService.processOrder(order);
			ModelUtil.addSuccess("Order created successfully");	
		}catch(HibernateException e){
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}catch (ValidationException e) {
			logger.error("Validation errors are present");
		}
		
		return view(model,null);
	}
	
	@RequestMapping("/orderReport")
	public void downloadReport(){
		
		List<Order> orders = orderService.findAll();
		JRDataSource dataSource = new JRBeanCollectionDataSource(orders);
		ReportEngine.buildReport(dataSource, "order.jrxml", null);
		
	}
}
