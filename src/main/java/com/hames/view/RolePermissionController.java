package com.hames.view;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hames.bean.RolePermission;
import com.hames.enums.RolePermissionStatus;
import com.hames.service.RolePermissionService;
import com.hames.system.auth.Permission;
import com.hames.util.enums.SuccessCode;
import com.hames.util.model.DatatableRequest;
import com.hames.util.model.DatatableResponse;
import com.hames.util.model.JsonResponse;
import com.hames.util.model.SuccessNode;

@Controller
@RequestMapping("/role")
public class RolePermissionController extends GenericView{

	private static final Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

	@Autowired
	private RolePermissionService rolePermissionService;
	
	@RequestMapping("/list")
	public String view(Model model){
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_ROLE_PERMISSION.getPermission())){
			return "error.403";
		}
		return "system.role";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.VIEW_ROLE_PERMISSION.getPermission())){
			return "error.403";
		}
		
		RolePermission rolePermission = null;
		
		if(id == null || id.isEmpty()){
			if(!model.containsAttribute("rolePermission")){
				rolePermission = new RolePermission();
				rolePermission.setStatus(RolePermissionStatus.ACTIVE_ROLE);
			}
		}else{
			rolePermission = rolePermissionService.getById(id);
		}
		
		model.addAttribute("rolePermission", rolePermission);
		model.addAttribute("rolePermissionStatus", RolePermissionStatus.values());
		model.addAttribute("permissions", Permission.values());
		
		return "system.role";
	}
	
	@ResponseBody
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public JsonResponse save(@ModelAttribute RolePermission rolePermission){
		
		JsonResponse response;
		
		if(!SecurityUtils.getSubject().isPermitted(Permission.CREATE_ROLE_PERMISSION.getPermission())){
			throw new AuthorizationException();
		}
		
		logger.debug("Saving Role permission : {}",rolePermission.toString());
		rolePermissionService.save(rolePermission);
		response = new JsonResponse(Boolean.TRUE,new SuccessNode(SuccessCode.ENTITY_SAVED, "Role saved successfully"));
		
		return response;
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return rolePermissionService.getDatatable(datatableRequest);
	}
	
	
	
}
