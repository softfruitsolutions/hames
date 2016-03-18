package com.hames.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.inventory.model.ProductGroup;
import com.hames.inventory.validator.ProductGroupValidator;
import com.hames.service.GenericServiceImpl;

@Service
public class ProductGroupServiceImpl extends GenericServiceImpl<ProductGroup> implements ProductGroupService{

	@Override
	public Validator getValidator() {
		return new ProductGroupValidator();
	}

	@Override
	public String save(ProductGroup t) {
		//Validate group
		validate(t);
		
		//Setting audit details
		t.setAudit();
		
		return super.save(t);
	}
}
