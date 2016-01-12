package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.StaffRole;

public class StaffRoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return StaffRole.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		StaffRole role = (StaffRole) obj;
		if(role.getRoleName() == null || role.getRoleName().isEmpty()){
			errors.rejectValue("roleName", "", "Role Name Required");
		}
		
		if(role.getStatus() == null){
			errors.rejectValue("status", "", "Invalid Status");
		}
		
	}

}
