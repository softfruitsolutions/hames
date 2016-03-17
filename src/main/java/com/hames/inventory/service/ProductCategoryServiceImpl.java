package com.hames.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.inventory.model.ProductCategory;
import com.hames.inventory.validator.ProductCategoryValidator;
import com.hames.service.GenericServiceImpl;

@Service
public class ProductCategoryServiceImpl extends GenericServiceImpl<ProductCategory> implements ProductCategoryService{

	@Override
	public Validator getValidator() {
		return new ProductCategoryValidator();
	}

	@Override
	public String save(ProductCategory t) {
		//Validate category
		validate(t);
		
		//Setting audit details
		t.setAudit();
		
		return super.save(t);
	}
}
