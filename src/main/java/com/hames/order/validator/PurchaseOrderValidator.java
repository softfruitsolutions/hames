package com.hames.order.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.order.model.PurchaseOrder;

public class PurchaseOrderValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}

}
