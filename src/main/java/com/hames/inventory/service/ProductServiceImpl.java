package com.hames.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.hames.inventory.model.Product;
import com.hames.service.GenericServiceImpl;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService{

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}


}
