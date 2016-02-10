package com.hames.view;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hames.bean.Customer;
import com.hames.enums.PartyStatus;
import com.hames.enums.PartyType;
import com.hames.exception.ValidationException;
import com.hames.service.CustomerService;
import com.hames.system.auth.Permission;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.peer.ModelUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/customer")
public class CustomerView extends GenericView {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerView.class);
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/list")
	public String list(Model model){
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_CUSTOMER.getPermission())){
			return "error.403";
		}
		activeMenu(model, "customer");
		return "customer.list";
	}
	
	@RequestMapping(value = "/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_CUSTOMER.getPermission())){
			return "error.403";
		}
		
		activeMenu(model, "customer");
		
		Customer customer = null;
		if(id == null || id.isEmpty()){
			if(!model.containsAttribute("customer")){
				customer = new Customer();
				customer.setPartyType(PartyType.CUSTOMER);
				model.addAttribute("customer", customer);
			}
		}else{
			customer = customerService.getCustomerById(id);
			model.addAttribute("customer", customer);
		}
		
		model.addAttribute("customerStatus", PartyStatus.values());
		
		return "customer";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Model model,@ModelAttribute Customer customer){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_CUSTOMER.getPermission())){
			return "error.403";
		}
		
		try{
			customerService.saveCustomer(customer);
			ModelUtil.addSuccess("Customer saved successfully");	
		}catch(ValidationException e){
			logger.error("Validation errors are present.");
			return view(model,null);
		}
		
		return list(model);
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return customerService.getDatatable(datatableRequest);
	}
}
