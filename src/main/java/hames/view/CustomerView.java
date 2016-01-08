package hames.view;

import hames.bean.Customer;
import hames.bean.exception.ValidationException;
import hames.core.bean.ModelUtil;
import hames.core.view.AbstractView;
import hames.enums.CustomerStatusEnum;
import hames.service.CustomerService;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CustomerView extends AbstractView {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerView.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public String getTitleDefinition(Model model) {
		return "customer";
	}
	
	@RequestMapping(value = "/customerview")
	public String view(Model model, @RequestParam(value="id",required=false) Long id){
		activeMenu(model, "customer");
		
		Customer customer = null;
		if(id == null || id == 0){
			if(!model.containsAttribute("customer")){
				customer = new Customer();
				model.addAttribute("customer", customer);
			}
		}else{
			customer = customerService.findOne(id);
			model.addAttribute("customer", customer);
		}
		
		model.addAttribute("customerStatus", CustomerStatusEnum.values());
		
		return getTitleDefinition(model);
	}
	
	@RequestMapping("/customersave")
	public String save(Model model,@ModelAttribute Customer customer){
		
		try{
			customerService.save(customer);
			ModelUtil.addSuccess("Customer saved successfully");	
		}catch(HibernateException e){
			logger.error(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}catch(ValidationException e){
			logger.error("Validation errors are present.");
		}
		
		return view(model,null);
	}
}
