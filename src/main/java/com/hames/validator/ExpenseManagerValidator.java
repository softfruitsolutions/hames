package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.ExpenseManager;

public class ExpenseManagerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExpenseManager.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
