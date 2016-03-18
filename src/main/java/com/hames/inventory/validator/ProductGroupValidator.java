package com.hames.inventory.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.inventory.model.ProductGroup;

public class ProductGroupValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductGroup.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProductGroup group = (ProductGroup) target;
		
		if(group.getProductGroupName() == null || group.getProductGroupName().isEmpty()){
			errors.rejectValue("productGroupName", "", "Group name required");
		}
		
	}

}
