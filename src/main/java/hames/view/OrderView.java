package hames.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import hames.bean.Order;
import hames.core.view.AbstractView;
import hames.enums.OrderStatusEnum;
import hames.service.CustomerService;
import hames.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderView extends AbstractView{

	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
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
				order.setCreatedDate(new Date());
				order.setOrderStatus(OrderStatusEnum.DRAFT.getValue());
				model.addAttribute("order", order);
			}
		}else{
			order = orderService.findOne(id);
			model.addAttribute("order", order);
		}
		
		model.addAttribute("customers", customerService.findAll());
		return getTitleDefinition(model);
	}

}
