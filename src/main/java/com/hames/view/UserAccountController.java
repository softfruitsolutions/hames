package com.hames.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hames.exception.RolePermissionException;
import com.hames.exception.StaffException;
import com.hames.service.RolePermissionService;
import com.hames.service.StaffService;
import com.hames.service.UserAccountService;
import com.hames.system.auth.Permission;
import com.hames.system.auth.UserAccount;
import com.hames.util.enums.ErrorCode;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.ErrorNode;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;

@Controller
@RequestMapping("/useraccount")
public class UserAccountController extends GenericView{
	
	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired private UserAccountService userAccountService;
	@Autowired private StaffService staffService;
	@Autowired private RolePermissionService rolePermissionService;
	
	@RequestMapping("/view")
	private String view(Model model, @RequestParam(value="username",required=false) String username){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_USER_ACCOUNT.getPermission())){
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
	
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	private JsonResponse save(Model model, @ModelAttribute UserAccount userAccount){
		
		JsonResponse response;
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_USER_ACCOUNT.getPermission())){
			throw new AuthorizationException();
		}
		
		logger.debug("Saving User Account");
		userAccountService.save(userAccount);
		response = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "User account saved successfully"));

		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(StaffException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleStaffException(Exception e){
		logger.debug("Staff Exception : {}",e.getMessage());
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR,HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}

	@ResponseBody
	@ExceptionHandler(RolePermissionException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleRoleException(Exception e){
		logger.debug("Role Exception : {}",e.getMessage());
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR,HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}

}
