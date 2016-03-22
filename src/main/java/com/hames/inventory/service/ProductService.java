package com.hames.inventory.service;

import com.hames.inventory.model.Product;
import com.hames.service.GenericService;

public interface ProductService extends GenericService<Product> {
	
	/**
	 * Get Next Product No
	 * @return
	 */
	public String getNextProductCode();

}
