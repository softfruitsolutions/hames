package com.hames.view;

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

import com.hames.bean.RolePermission;
import com.hames.enums.RolePermissionStatus;
import com.hames.exception.ValidationException;
import com.hames.service.RolePermissionService;
import com.hames.system.auth.Permission;
import com.hames.util.DatatableRequest;
import com.hames.util.DatatableResponse;
import com.hames.util.ModelUtil;

@Controller
@RequestMapping("/role")
public class RolePermissionView extends AbstractView{

	private static final Logger logger = LoggerFactory.getLogger(RolePermissionView.class);

	@Autowired
	private RolePermissionService rolePermissionService;
	
	@RequestMapping("/list")
	public String view(Model model){
		return "system.role.list";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam(value="id",required=false) String id){
		
		activeMenu(model, "rolepermission");
		
		RolePermission rolePermission = null;
		
		if(id == null || id.isEmpty()){
			rolePermission = new RolePermission();
			rolePermission.setStatus(RolePermissionStatus.ACTIVE_ROLE);
		}else{
			rolePermission = rolePermissionService.getRoleById(id);
		}
		
		model.addAttribute("rolePermission", rolePermission);
		model.addAttribute("rolePermissionStatus", RolePermissionStatus.values());
		model.addAttribute("permissions", Permission.values());
		
		return "system.role";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Model model,@ModelAttribute RolePermission rolePermission){
		
		try{
			rolePermissionService.saveRolePermission(rolePermission);
			ModelUtil.addSuccess("Role saved successfully");
		}catch(ValidationException e){
			logger.debug("Validation errors are present");
			return view(model, rolePermission.getRoleId());
		}
		
		return view(model);
	}
	
	@RequestMapping("/datatable")
	public @ResponseBody DatatableResponse viewDatatable(@ModelAttribute DatatableRequest datatableRequest){
		return rolePermissionService.getDatatable(datatableRequest);
	}
	
}
