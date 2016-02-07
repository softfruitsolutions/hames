package com.hames.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.bean.ExpenseCategory;

public class ExpenseCategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExpenseCategory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ExpenseCategory ec = (ExpenseCategory) target;
		
		if(ec.getCategoryName() == null || ec.getCategoryName().isEmpty()){
			errors.rejectValue("categoryName", "", "Category Name Required");
		}
		
	}

}
