package com.hames.inventory.service;

import org.springframework.validation.Validator;

import com.hames.inventory.model.Product;
import com.hames.service.GenericServiceImpl;

public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService{

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}


}
