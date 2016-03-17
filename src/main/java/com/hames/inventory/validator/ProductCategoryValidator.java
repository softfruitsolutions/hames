package com.hames.inventory.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hames.inventory.model.ProductCategory;

public class ProductCategoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductCategory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProductCategory category = (ProductCategory) target;
		
		if(category.getProductCategoryName() == null || category.getProductCategoryName().isEmpty()){
			errors.rejectValue("productCategoryName", "", "Product Category required");
		}
		
	}

}
