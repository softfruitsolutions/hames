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

		ExpenseManager em = (ExpenseManager) target;
		
		if(em.getExpenseDate() == null){
			errors.rejectValue("expenseDate", "", "Expense Date required");
		}
		
		if(em.getPayeeName() == null || em.getPayeeName().isEmpty()){
			errors.rejectValue("payeeName", "", "Payee required");
		}
		
		if(em.getExpenseCategory() == null){
			errors.rejectValue("expenseCategory.categoryId", "", "Expense category required");
		}
		
		if(em.getExpenseCategory().getCategoryId() == null || em.getExpenseCategory().getCategoryId().isEmpty()){
			errors.rejectValue("expenseCategory.categoryId", "", "Expense category required");
		}
		
	}

}
