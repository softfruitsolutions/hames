package com.hames.inventory.service;

import org.springframework.validation.Validator;

import com.hames.inventory.model.Product;
import com.hames.service.GenericService;

public class ProductServiceImpl extends GenericService implements ProductService{

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getEntityClass() {
		return Product.class;
	}
	
	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findByProductId(String productId) {
		// TODO Auto-generated method stub
		
	}

	

}
