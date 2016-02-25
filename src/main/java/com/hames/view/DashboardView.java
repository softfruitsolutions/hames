package com.hames.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hames.service.CustomerService;
import com.hames.service.SaleOrderService;

@Controller
public class DashboardView extends GenericView {
	
	@Autowired CustomerService customerService;
	@Autowired SaleOrderService saleOrderService;

	@RequestMapping(value = "/dashboard")
	public String dashboard(Model model) {
		
		model.addAttribute("menu", "dashboard");
		
		model.addAttribute("customerCount", customerService.getCustomerCount());
		model.addAttribute("saleOrderCount", saleOrderService.getSaleOrderCount());
		
		return "dashboard";
	}
	
}
