package com.hames.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hames.bean.UserUtil;
import com.hames.service.CustomerService;

@Controller
public class DashboardView {
	
	@Autowired CustomerService customerService;

	@RequestMapping(value = "/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("staffUtil",UserUtil.staff);
		model.addAttribute("customerCount", customerService.getCustomerCount());
		return "dashboard";
	}
	
}
