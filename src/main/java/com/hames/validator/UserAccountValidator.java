package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.system.auth.UserAccount;

public class UserAccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccount.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserAccount userAccount = (UserAccount) target;
		
		if(userAccount.getUsername() == null || userAccount.getUsername().isEmpty()){
			errors.rejectValue("username", "", "Username required");
		}
		
		if(userAccount.getPassword() == null || userAccount.getPassword().isEmpty()){
			errors.rejectValue("password", "", "Password required");
		}
		
		if(userAccount.getRoleId() == null || userAccount.getRoleId().isEmpty()){
			errors.rejectValue("rolePermission", "", "Role required");
		}
		
		if(userAccount.getStaffId() == null || userAccount.getStaffId().isEmpty()){
			errors.rejectValue("staffId", "", "Staff required");
		}
	}

}
