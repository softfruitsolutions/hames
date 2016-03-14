package com.hames.inventory.dao;

import com.hames.inventory.model.Product;
import com.hames.mongo.GenericDaoImpl;

public class ProductDaoImpl extends GenericDaoImpl<Product>{

	private static final String COLLECTION_NAME = "product"; 
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

}
