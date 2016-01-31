package com.hames.view;

import javax.servlet.http.HttpSession;

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

import com.hames.exception.RolePermissionException;
import com.hames.exception.StaffException;
import com.hames.exception.ValidationException;
import com.hames.service.RolePermissionService;
import com.hames.service.StaffService;
import com.hames.service.UserAccountService;
import com.hames.system.auth.UserAccount;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping("/useraccount")
public class UserAccountView extends AbstractView{
	
	private static final Logger logger = LoggerFactory.getLogger(UserAccountView.class);

	@Autowired private UserAccountService userAccountService;
	@Autowired private StaffService staffService;
	@Autowired private RolePermissionService rolePermissionService;
	
	@RequestMapping("/view")
	private String view(Model model, @RequestParam(value="username",required=false) String username){
		
		if(!SecurityUtils.getSubject().isPermitted("admin:useraccount:view")){
			return "error.403";
		}
		
		model.addAttribute("menu", "useraccount");
		
		if(username == null || username.isEmpty()){
			if(!model.containsAttribute("userAccount")){
				model.addAttribute("userAccount", new UserAccount());
			}
			model.addAttribute("staffs", staffService.getAllActiveStaffs());
			model.addAttribute("rolePermissions", rolePermissionService.getActiveRolePermissions());
			model.addAttribute("userAccounts", userAccountService.getUserAccounts());
		}
		
		return "system.auth.useraccount";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	private String save(Model model, @ModelAttribute UserAccount userAccount,HttpSession session){
		
		if(!SecurityUtils.getSubject().isPermitted("admin:useraccount:create")){
			return "error.403";
		}
		
		try{
			userAccountService.save(userAccount);
			ModelUtil.addSuccess("User Account saved successfully");
			session.removeAttribute("userAccount");
		}catch(ValidationException e){
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}catch(StaffException e){
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}catch (RolePermissionException e) {
			logger.debug(e.getMessage());
			ModelUtil.addError(e.getMessage());
		}
		
		return view(model, null);
	}

}
