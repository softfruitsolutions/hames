package com.hames.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.inventory.model.Product;
import com.hames.inventory.validator.ProductValidator;
import com.hames.service.GenericServiceImpl;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService{

	@Override
	public Validator getValidator() {
		return new ProductValidator();
	}

	@Override
	public String save(Product product) {
		validate(product);
		
		//Setting auditable details
		product.setAudit();
		
		return "";//super.save(product);
	}

}
