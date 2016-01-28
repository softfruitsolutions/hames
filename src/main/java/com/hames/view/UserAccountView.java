package com.hames.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hames.service.UserAccountService;
import com.hames.system.auth.UserAccount;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping("/useraccount")
public class UserAccountView extends AbstractView{
	
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping("/view")
	private String view(Model model, @RequestParam(value="username",required=false) String username){
		model.addAttribute("menu", "useraccount");
		
		if(username == null || username.isEmpty()){
			if(!model.containsAttribute("userAccount")){
				model.addAttribute("userAccount", new UserAccount());
			}
		}
		
		return "system.auth.useraccount";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	private String save(Model model, @ModelAttribute UserAccount userAccount){
		userAccountService.save(userAccount);
		ModelUtil.addSuccess("User Account saved successfully");
		return view(model, null);
	}

}
