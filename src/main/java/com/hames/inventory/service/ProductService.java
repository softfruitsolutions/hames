package com.hames.inventory.service;

import com.hames.inventory.model.Product;

public interface ProductService {

	public void save(Product product);
	
	public void findByProductId(String productId);
}
