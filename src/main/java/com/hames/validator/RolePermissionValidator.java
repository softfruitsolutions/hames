package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.RolePermission;

public class RolePermissionValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return RolePermission.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		RolePermission role = (RolePermission) obj;
		if(role.getRoleName() == null || role.getRoleName().isEmpty()){
			errors.rejectValue("roleName", "", "Role Name Required");
		}
		
		if(role.getStatus() == null){
			errors.rejectValue("status", "", "Invalid Status");
		}
		
		if(role.getPermissions() == null || role.getPermissions().size() <= 0){
			errors.rejectValue("permissions", "", "Permissions required");
		}
		
	}

}
