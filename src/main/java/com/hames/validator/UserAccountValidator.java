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
		
	}

}
